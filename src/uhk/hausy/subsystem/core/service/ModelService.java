package uhk.hausy.subsystem.core.service;

import uhk.hausy.subsystem.core.model.Device;
import uhk.hausy.subsystem.core.model.GroupRule;
import uhk.hausy.subsystem.core.model.Node;

import java.util.List;
import java.util.Map;

/**
 * Created by tobou on 14.11.2016.
 */
public interface ModelService {

    /**
     * saves configuration from flatbuffers and loads it into runtime variables. Configuration consists
     * of nodes, types, devices and rules. Then create Flatbuffer with configuration for c++ layer.
     * @param nodesMap Runtime map of nodes.
     * @param rules Runtime list of rules.
     * @param bytes Flatbuffer with configuration.
     * @return FlatBuffer with
     */
    byte[] saveConfig(Map<Short, Node> nodesMap, Map<Integer, Device> devices, List<GroupRule> rules, byte[] bytes);

    /**
     * Load configuration from local database and loads it into runtime variables. Configuration consists
     * of nodes, types, devices and rules. Then create Flatbuffer with configuration for c++ layer.
     * @param nodesMap Runtime map of nodes.
     * @param rules Runtime list of rules.
     * @return FlatBuffer with configuration for c++ layer.
     */
    byte[] getConfig(Map<Short, Node> nodesMap, Map<Integer, Device> devices, List<GroupRule> rules);

    /**
     *
     * @param rules
     * @param nodes
     * @param bytes
     */
    void updateRules(List<GroupRule> rules, Map<Short, Node> nodes, byte[] bytes);

    /**
     *
     * @param nodes
     * @param bytes
     */
    void saveDevices(Map<Short, Node> nodes, Map<Integer, Device> devices, byte[] bytes);

    /**
     *
     * @param nodesMap
     * @param bytes
     * @return
     */
    List<Node> assignAddresses(Map<Short,Node> nodesMap, byte[] bytes);

    void clearDbs();

}
