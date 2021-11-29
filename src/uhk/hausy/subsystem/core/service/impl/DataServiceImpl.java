package uhk.hausy.subsystem.core.service.impl;

import uhk.hausy.subsystem.core.flatBuffer.transformer.TransformerServer;
import uhk.hausy.subsystem.core.flatBuffer.transformer.TransformerSubsystem;
import uhk.hausy.subsystem.core.model.*;
import uhk.hausy.subsystem.core.service.DataService;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by tobou on 16.10.2016.
 */
public class DataServiceImpl implements DataService {

    public short processData(Map<Short, Node> nodes, byte[] bytes) {
        short id = TransformerSubsystem.fbDataToObjects(bytes, nodes);
        return id;
    }

    public byte[] transformData(byte[] bytes) {
        return TransformerServer.fbDataSubsystemToFbDataServer(bytes);
    }

    public List<byte[]> setMessages(Node node, Map<Short, Node> nodes, List<GroupRule> groupRules) {
        List<byte[]> messages = new ArrayList<>();
        for(GroupRule groupRule : groupRules) {
            if(groupRule.isActive()) {
                for(Rule rule : groupRule.getRules()) {
                    if(rule.isActive() && rule.isForNode(node) && rule.evaluate()) {
                        for(Action action : rule.getActions()) {
                            Channel channel = action.getDevice().getChannel();
                            channel.setData(action.getValues());
                            node.setRulesApplied(true);
                        }
                    }
                }
            }
        }
        Iterator iterator = nodes.entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry thisEntry = (Map.Entry) iterator.next();
            Node nodeThis = (Node) thisEntry.getValue();
            if(nodeThis.isRulesApplied()) {
                messages.add(TransformerSubsystem.nodeToFbData(nodeThis));
                nodeThis.setRulesApplied(false);
            }
        }
        return messages;
    }


}
