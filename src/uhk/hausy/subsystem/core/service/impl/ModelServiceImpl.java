package uhk.hausy.subsystem.core.service.impl;

import uhk.hausy.subsystem.core.HausyCore;
import uhk.hausy.subsystem.core.database.base.DbsManager;
import uhk.hausy.subsystem.core.database.base.TransactionManager;
import uhk.hausy.subsystem.core.database.dao.DaoDevice;
import uhk.hausy.subsystem.core.database.dao.DaoGroupRule;
import uhk.hausy.subsystem.core.database.dao.DaoNode;
import uhk.hausy.subsystem.core.database.dao.DaoNodeType;
import uhk.hausy.subsystem.core.database.dao.impl.*;
import uhk.hausy.subsystem.core.flatBuffer.transformer.TransformerServer;
import uhk.hausy.subsystem.core.model.*;
import uhk.hausy.subsystem.core.flatBuffer.transformer.TransformerSubsystem;
import uhk.hausy.subsystem.core.service.ModelService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by tobou on 16.10.2016.
 * blablabli
 */
public class ModelServiceImpl implements ModelService {

    private DaoNode daoNode =  new DaoNodeImpl();
    private DaoNodeType daoNodeType = new DaoNodeTypeImpl();
    private DaoDevice daoDevice = new DaoDeviceImpl();
    private DaoGroupRule daoGroupRule = new DaoGroupRuleImpl();
    private TransactionManager transactionManager = TransactionManager.getInstance();

    public byte[] saveConfig(Map<Short, Node> nodeMap, Map<Integer,Device> deviceMap, List<GroupRule> rules, byte[] bytes) {
        try {
            List<Node> nodes = new ArrayList<>();
            List<NodeType> nodeTypes = new ArrayList<>();
            List<Device> devices = new ArrayList<>();
            List<GroupRule> groupRules = new ArrayList<>();
            TransformerServer.fbConfigurationToObjects(bytes, nodeTypes, nodes, groupRules, devices);
            System.out.println(nodes.get(0).getMacAddress()+"adresa");
            transactionManager.startTransaction();
            daoNodeType.persistMultiple(nodeTypes);
            daoNode.persistMultiple(nodes);
            daoDevice.persistMultiple(devices);
            daoGroupRule.persistMultiple(groupRules);
            transactionManager.endTransaction();
            load(nodeMap, deviceMap, rules);
            return TransformerSubsystem.objectsToNodesTypesFB(nodeTypes, nodes);
        } catch (Exception ex) {
            ex.printStackTrace();
            transactionManager.rollbackTransaction();
            return null;
        }
    }

    public byte[] getConfig(Map<Short, Node> nodeMap, Map<Integer,Device> deviceMap, List<GroupRule> rules) {
        try {
            transactionManager.startTransaction();
            List<NodeType> nodeTypes = daoNodeType.list();
            List<Node> nodes = daoNode.list();
            transactionManager.endTransaction();
            load(nodeMap, deviceMap, rules);
            return TransformerSubsystem.objectsToNodesTypesFB(nodeTypes, nodes);
        } catch (Exception ex) {
            ex.printStackTrace();
            transactionManager.rollbackTransaction();
            return null;
        }
    }

    public void updateRules(List<GroupRule> rules, Map<Short, Node> nodeMap, byte[] bytes) {
        try {
            transactionManager.startTransaction();
            List<GroupRule> groupRules = new ArrayList<>();
            TransformerServer.fbGroupListToObjects(bytes, groupRules, daoDevice.list());
            daoGroupRule.persistMultiple(groupRules);
            transactionManager.endTransaction();
            loadRules(rules, nodeMap);
        } catch (Exception ex) {
            ex.printStackTrace();
            transactionManager.rollbackTransaction();
        }
    }

    public void saveDevices(Map<Short, Node> nodeMap, Map<Integer,Device> deviceMap,  byte[] bytes) {
        try {
            transactionManager.startTransaction();
            List<Device> devicesList = new ArrayList<>();
            TransformerServer.fbDevicesToObjects(bytes, nodeMap, devicesList);
            daoDevice.persistMultiple(devicesList);
            transactionManager.endTransaction();
            loadDevices(nodeMap, deviceMap);
        } catch (Exception ex) {
            ex.printStackTrace();
            transactionManager.rollbackTransaction();
        }
    }

    public List<Node> assignAddresses(Map<Short,Node> nodeMap, byte[] bytes) {
        try {
            transactionManager.startTransaction();
            List<NodeType> nodeTypes = daoNodeType.list();
            transactionManager.endTransaction();
            List<Node> nodes = new ArrayList<>();
            TransformerSubsystem.fbNodesToObjects(bytes, nodeTypes, nodes);
            for(Node node : nodes) {
                transactionManager.startTransaction();
                node.setId(daoNode.generateId());
                transactionManager.endTransaction();
            }
            loadNodes(nodeMap);
            return nodes;
        } catch (Exception ex) {
            ex.printStackTrace();
            transactionManager.rollbackTransaction();
            return null;
        }
    }

    @Override
    public void clearDbs() {
        DbsManager dbsManager = DbsManager.getInstance();
        dbsManager.clearConfig();
    }

    private void load(Map<Short,Node> nodeMap, Map<Integer,Device> deviceMap, List<GroupRule> groupRules) {
        loadNodes(nodeMap);
        loadDevices(nodeMap, deviceMap);
        loadRules(groupRules, nodeMap);
    }

    private void loadNodes(Map<Short,Node> nodes) {
        try {
            transactionManager.startTransaction();
            nodes.clear();
            for(Node n : daoNode.list()) {
                nodes.put(n.getId(), n);
            }
            for(Node n : daoNode.list()) {
                System.out.println(n.toString());
            }
            transactionManager.endTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            transactionManager.rollbackTransaction();
        }
    }

    public void loadRules(List<GroupRule> rules, Map<Short, Node> nodeMap) {
        try {
            transactionManager.startTransaction();
            rules.clear();
            for(GroupRule g : daoGroupRule.list(nodeMap)) {
                rules.add(g);
            }
            for(GroupRule groupRule : rules) {
                for(Rule rule : groupRule.getRules()) {
                    rule.parseRuleFromString(rule.getOriginalExpression());
                    System.out.println(rule.toString());
                }
            }
            transactionManager.endTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            transactionManager.rollbackTransaction();
        }
    }

    public void loadDevices(Map<Short, Node> nodes, Map<Integer, Device> deviceMap) {
        try {
            transactionManager.startTransaction();
            Iterator iterator = nodes.entrySet().iterator();
            while(iterator.hasNext()) {
                Map.Entry thisEntry = (Map.Entry) iterator.next();
                Node node = (Node) thisEntry.getValue();
                for(Channel channel : node.getChannels()) {
                    //channel.setDevice(null);
                }
            }
            List<Device> devicesList = daoDevice.list(nodes);
            deviceMap.clear();
            for(Device device : devicesList) {
                deviceMap.put(device.getId(), device);
            }
            transactionManager.endTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            transactionManager.rollbackTransaction();
        }
    }


}
