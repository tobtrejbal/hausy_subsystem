package uhk.hausy.subsystem.core.service;

import uhk.hausy.subsystem.core.model.GroupRule;
import uhk.hausy.subsystem.core.model.Node;

import java.util.List;
import java.util.Map;

/**
 * Created by tobou on 14.11.2016.
 */
public interface DataService {

    short processData(Map<Short, Node> nodes, byte[] bytes);

    byte[] transformData(byte[] bytes);

    List<byte[]> setMessages(Node node, Map<Short, Node> nodes, List<GroupRule> groupRules);

}
