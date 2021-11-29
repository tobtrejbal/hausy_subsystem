package uhk.hausy.subsystem.core.flatBuffer.transformer;

import com.google.flatbuffers.FlatBufferBuilder;
import uhk.hausy.subsystem.core.app.AppConfig;
import uhk.hausy.subsystem.core.model.*;
import uhk.hausy.subsystem.core.flatBuffer.objects.server.*;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by tobou on 14.10.2016.
 */
public class TransformerServer {

    public static void fbNodesToObjects(byte[] bytes, List<NodeType> nodeTypes, List<Node> nodes) {
        NodesFB nodesFB = NodesFB.getRootAsNodesFB(ByteBuffer.wrap(bytes));
        for(int i = 0; i < nodesFB.nodesLength(); i++) {
            nodes.add(createNodeFromFB(nodesFB.nodes(i), nodeTypes));
        }
    }

    public static void fbConfigurationToObjects(byte[] bytes, List<NodeType> nodeTypes, List<Node> nodes, List<GroupRule> groupRules, List<Device> devices) {
        ConfigurationFB configurationFB = ConfigurationFB.getRootAsConfigurationFB(ByteBuffer.wrap(bytes));
        for(int i = 0; i < configurationFB.typesLength(); i++) {
            nodeTypes.add(createTypeFromFB(configurationFB.types(i)));
        }
        for(int i = 0; i < configurationFB.nodesLength(); i++) {
            nodes.add(createNodeFromFB(configurationFB.nodes(i), nodeTypes));
        }
        for(int i = 0; i < configurationFB.devicesLength(); i++) {
            for(Node node : nodes) {
                for(Channel channel : node.getChannels()) {
                    if(channel.getId().equals(configurationFB.devices(i).channelId())) {
                        devices.add(createDeviceFromFB(configurationFB.devices(i), channel));
                    }
                }
            }
        }
        for(int i = 0; i < configurationFB.groupRulesLength(); i++) {
            groupRules.add(createGroupRuleFromFB(configurationFB.groupRules(i), devices));
        }
    }

    public static boolean isMyConfig(byte[] bytes) {
        ConfigurationFB configurationFB = ConfigurationFB.getRootAsConfigurationFB(ByteBuffer.wrap(bytes));
        if(configurationFB.subsystemId() == AppConfig.getInstance().getSubsystemId()) {
            return true;
        }
        return false;
    }

    public static void fbDevicesToObjects(byte[] bytes, Map<Short, Node> nodes, List<Device> devices) {
        DevicesFB  devicesFB = DevicesFB.getRootAsDevicesFB(ByteBuffer.wrap(bytes));
        for(int i = 0; i < devicesFB.devicesLength(); i++) {
            Iterator iterator = nodes.entrySet().iterator();
            while(iterator.hasNext()) {
                Node node = (Node) iterator.next();
                for(Channel channel : node.getChannels()) {
                    if(channel.getId().equals(devicesFB.devices(i).channelId())) {
                        devices.add(createDeviceFromFB(devicesFB.devices(i), channel));
                    }
                }
            }
        }
    }

    public static byte[] objectsToPingFB() {
        FlatBufferBuilder flatBufferBuilder = new FlatBufferBuilder();
        int pingFB = PingFB.createPingFB(flatBufferBuilder, AppConfig.getInstance().getSubsystemId());
        flatBufferBuilder.finish(pingFB);
        return flatBufferBuilder.sizedByteArray();
    }

    public static byte[] objectsToConfigurationRequestFB() {
        FlatBufferBuilder flatBufferBuilder = new FlatBufferBuilder();
        int configurationRequest = ConfigurationRequestFB.createConfigurationRequestFB(flatBufferBuilder, AppConfig.getInstance().getSubsystemId());
        flatBufferBuilder.finish(configurationRequest);
        return flatBufferBuilder.sizedByteArray();
    }

    public static byte[] fbDataSubsystemToFbDataServer(byte[] bytes) {
        FlatBufferBuilder flatBufferBuilder = new FlatBufferBuilder();
        uhk.hausy.subsystem.core.flatBuffer.objects.subsystem.DataFB dataFBSub = uhk.hausy.subsystem.core.flatBuffer.objects.subsystem.DataFB.getRootAsDataFB(ByteBuffer.wrap(bytes));

        int[] channelDataOffs = new int[dataFBSub.channelDataLength()];

        for(int i = 0; i < dataFBSub.channelDataLength(); i++) {
            uhk.hausy.subsystem.core.flatBuffer.objects.subsystem.ChannelDataFB channelDataFB = dataFBSub.channelData(i);
            int[] data = new int[channelDataFB.dataLength()];
            for(int j = 0; j < channelDataFB.dataLength(); j++) {
                data[j] = channelDataFB.data(j);
            }
            int dataOff =  ChannelDataFB.createDataVector(flatBufferBuilder, data);
            channelDataOffs[i] = ChannelDataFB.createChannelDataFB(flatBufferBuilder, dataOff,channelDataFB.channelId());

        }
        int channelDataOff = DataFB.createChannelDataVector(flatBufferBuilder, channelDataOffs);
        int dataOff = DataFB.createDataFB(flatBufferBuilder, AppConfig.getInstance().getSubsystemId(), channelDataOff, dataFBSub.address());
        flatBufferBuilder.finish(dataOff);
        return flatBufferBuilder.sizedByteArray();
    }

    public static void fbGroupListToObjects(byte[] bytes, List<GroupRule> groupRules, List<Device> devices) {
        GroupListFB groupListFB = GroupListFB.getRootAsGroupListFB(ByteBuffer.wrap(bytes));
        for(int i = 0; i < groupListFB.groupsLength(); i++) {
            groupRules.add(createGroupRuleFromFB(groupListFB.groups(i), devices));
        }
    }

    public static byte[] objectsToNodesFB(List<Node> nodes) {
        FlatBufferBuilder flatBufferBuilder = new FlatBufferBuilder();
        int[] nodesOffs = new int[nodes.size()];
        for(int i = 0; i < nodes.size(); i++) {
            Node n = nodes.get(i);
            n.setMacAddress(" ");
            nodesOffs[i] = createNodeFB(flatBufferBuilder, n);
        }
        int nodesOff = NodesFB.createNodesVector(flatBufferBuilder, nodesOffs);
        int node = NodesFB.createNodesFB(flatBufferBuilder, AppConfig.getInstance().getSubsystemId(), nodesOff);
        flatBufferBuilder.finish(node);
        return flatBufferBuilder.sizedByteArray();
    }

    public static byte[] objectsToTypesFB(List<NodeType> nodeTypes) {
        FlatBufferBuilder flatBufferBuilder = new FlatBufferBuilder();
        int[] typesOffs = new int[nodeTypes.size()];
        for(int i = 0; i < nodeTypes.size(); i++) {
            typesOffs[i] = createTypeFB(flatBufferBuilder, nodeTypes.get(i));
        }
        int typesOff = TypesFB.createTypesVector(flatBufferBuilder, typesOffs);
        int type = TypesFB.createTypesFB(flatBufferBuilder, AppConfig.getInstance().getSubsystemId(), typesOff);
        flatBufferBuilder.finish(type);
        return flatBufferBuilder.sizedByteArray();
    }

    public static byte[] objectsToConfigurationFB(List<NodeType> nodeTypes, List<Node> nodes, List<GroupRule> groupRules, List<Device> devices) {
        FlatBufferBuilder flatBufferBuilder = new FlatBufferBuilder();
        int[] nodesOffs = new int[nodes.size()];
        int[] typesOffs = new int[nodeTypes.size()];
        int[] devicesOffs = new int[devices.size()];
        int[] groupRulesOffs = new int[groupRules.size()];
        for(int i = 0; i < nodes.size(); i++) {
            Node n = nodes.get(i);
            n.setMacAddress(" ");
            nodesOffs[i] = createNodeFB(flatBufferBuilder, n);
        }
        for(int i = 0; i < nodeTypes.size(); i++) {
            NodeType t = nodeTypes.get(i);
            typesOffs[i] = createTypeFB(flatBufferBuilder,t);
        }
        for(int i = 0; i < devices.size(); i++) {
            devicesOffs[i] = createDeviceFB(flatBufferBuilder, devices.get(i));
        }
        for(int i = 0; i < groupRules.size(); i++) {
            groupRulesOffs[i] = createGroupRuleFB(flatBufferBuilder, groupRules.get(i));
        }
        int nodesOff = ConfigurationFB.createNodesVector(flatBufferBuilder, nodesOffs);
        int typesOff = ConfigurationFB.createTypesVector(flatBufferBuilder, typesOffs);
        int devicesOff = ConfigurationFB.createDevicesVector(flatBufferBuilder, devicesOffs);
        int groupRulesOff = ConfigurationFB.createGroupRulesVector(flatBufferBuilder, groupRulesOffs);
        int totalOff = ConfigurationFB.createConfigurationFB(flatBufferBuilder, AppConfig.getInstance().getSubsystemId(), typesOff, nodesOff, devicesOff, groupRulesOff);
        flatBufferBuilder.finish(totalOff);
        return flatBufferBuilder.sizedByteArray();
    }

    public static byte[] objectsToGroupListFB(List<GroupRule> groupRules) {
        FlatBufferBuilder flatBufferBuilder = new FlatBufferBuilder();
        int[] groupsOffs = new int[groupRules.size()];
        for(int i = 0; i < groupRules.size(); i++) {
            groupsOffs[i] = createGroupRuleFB(flatBufferBuilder, groupRules.get(i));
        }
        int groupsOff = GroupListFB.createGroupsVector(flatBufferBuilder, groupsOffs);
        int totalOff = GroupListFB.createGroupListFB(flatBufferBuilder, AppConfig.getInstance().getSubsystemId(), groupsOff);
        flatBufferBuilder.finish(totalOff);
        return flatBufferBuilder.sizedByteArray();
    }

    public static byte[] objectsToDevicesFB(List<Device> devices) {
        FlatBufferBuilder flatBufferBuilder = new FlatBufferBuilder();
        int[] devicesOffs = new int[devices.size()];
        for(int i = 0; i < devices.size(); i++) {
            devicesOffs[i] = createDeviceFB(flatBufferBuilder, devices.get(i));
        }
        int devicesOff = DevicesFB.createDevicesVector(flatBufferBuilder, devicesOffs);
        int totalOff = GroupListFB.createGroupListFB(flatBufferBuilder, AppConfig.getInstance().getSubsystemId(), devicesOff);
        flatBufferBuilder.finish(totalOff);
        return flatBufferBuilder.sizedByteArray();
    }

    private static int createTypeFB(FlatBufferBuilder flatBufferBuilder, NodeType t) {
        int offset = TypeFB.createTypeFB(flatBufferBuilder, t.getId(), t.getAccessType(), t.getLatency(), t.getDataRange(), t.getDataCount(), t.getMinDelay());
        return offset;
    }

    private static int createNodeFB(FlatBufferBuilder flatBufferBuilder, Node n) {
        int[] channelsOffs = new int[n.getChannels().size()];
        for(int i = 0; i < n.getChannels().size(); i++) {
            channelsOffs[i] = createChannelFB(flatBufferBuilder, n.getChannels().get(i));
        }
        int channelsOff = NodeFB.createChannelsVector(flatBufferBuilder, channelsOffs);
        int offset = NodeFB.createNodeFB(flatBufferBuilder, n.getId(), n.getNodeType().getId(), n.getNodeType().getId(), channelsOff, flatBufferBuilder.createString(n.getMacAddress()));
        return offset;
    }

    private static int createChannelFB(FlatBufferBuilder flatBufferBuilder, Channel c) {
        int offset = ChannelFB.createChannelFB(flatBufferBuilder, flatBufferBuilder.createString(c.getId()), c.getNode().getId(), c.getChannelNumber());
        return offset;
    }

    private static int createDeviceFB(FlatBufferBuilder flatBufferBuilder, Device d) {
        int offset = DeviceFB.createDeviceFB(flatBufferBuilder, d.getId(), flatBufferBuilder.createString(d.getName()), flatBufferBuilder.createString(d.getChannel().getId()));
        return offset;
    }

    private static int createGroupRuleFB(FlatBufferBuilder flatBufferBuilder, GroupRule groupRule) {
        int[] rulesOffs = new int[groupRule.getRules().size()];
        for(int i = 0; i < groupRule.getRules().size(); i++) {
            rulesOffs[i] = createRuleFB(flatBufferBuilder, groupRule.getRules().get(i));
        }
        int rulesOff = GroupRuleFB.createRulesVector(flatBufferBuilder, rulesOffs);
        int offset = GroupRuleFB.createGroupRuleFB(flatBufferBuilder, flatBufferBuilder.createString(groupRule.getName()), rulesOff, groupRule.isActive());
        return offset;
    }

    private static int createRuleFB(FlatBufferBuilder flatBufferBuilder, Rule rule) {
        int[] conditionsOffs = new int[rule.getConditions().size()];
        for(int i = 0; i < rule.getConditions().size(); i++) {
            conditionsOffs[i] = createConditionFB(flatBufferBuilder, rule.getConditions().get(i));
        }
        int[] actionsOffs = new int[rule.getActions().size()];
        for(int i = 0; i < rule.getActions().size(); i++) {
            actionsOffs[i] = createActionFB(flatBufferBuilder, rule.getActions().get(i));
        }
        int conditionsOff = RuleFB.createConditionsVector(flatBufferBuilder, conditionsOffs);
        int actionsOff = RuleFB.createActionsVector(flatBufferBuilder, actionsOffs);
        int offset = RuleFB.createRuleFB(flatBufferBuilder, rule.getId(), flatBufferBuilder.createString(rule.getName()),rule.isActive(),
                flatBufferBuilder.createString(rule.getOriginalExpression()),conditionsOff,actionsOff);
        return offset;
    }

    private static int createConditionFB(FlatBufferBuilder flatBufferBuilder, Condition condition) {
        int valuesOff = ConditionFB.createValuesVector(flatBufferBuilder, condition.getValues());
        int operatorsOff = ConditionFB.createOperatorsVector(flatBufferBuilder, condition.getOperators());
        int offset = ConditionFB.createConditionFB(flatBufferBuilder, condition.getId(),
                condition.getRule().getId(), condition.getDevice().getId(),
                flatBufferBuilder.createString(condition.getName()), operatorsOff, valuesOff);
        return offset;
    }

    private static int createActionFB(FlatBufferBuilder flatBufferBuilder, Action action) {
        int valuesOff = ActionFB.createValuesVector(flatBufferBuilder, action.getValues());
        int offset = ActionFB.createActionFB(flatBufferBuilder, action.getId(), action.getDevice().getId(),
                action.getRule().getId(), flatBufferBuilder.createString(action.getName()), valuesOff);
        return offset;
    }

    private static NodeType createTypeFromFB(TypeFB typeFB) {
        NodeType nodeType = new NodeType();
        nodeType.setId(typeFB.id());
        nodeType.setDataCount(typeFB.dataCount());
        nodeType.setDataRange(typeFB.dataRange());
        nodeType.setLatency(typeFB.latency());
        nodeType.setAccessType(typeFB.accessType());
        nodeType.setMinDelay(typeFB.minDelay());
        return nodeType;
    }

    private static Node createNodeFromFB(NodeFB nodeFB, List<NodeType> nodeTypes) {
        Node node = new Node();
        node.setId(nodeFB.id());
        for(NodeType nodeType : nodeTypes) {
            if(nodeType.getId() == nodeFB.typeId()) {
                node.setNodeType(nodeType);
            }
        }
        node.setChannelCount((byte) nodeFB.channelsLength());
        node.setChannels(new ArrayList<>());
        for(int i = 0; i < node.getChannelCount(); i++) {
            createChannelFromFB(nodeFB.channels(i), node);
        }
        node.setMacAddress(nodeFB.macAddress());
        return node;
    }

    private static Channel createChannelFromFB(ChannelFB channelFB, Node node) {
        Channel channel = new Channel();
        channel.setId(channelFB.id());
        channel.setChannelNumber(channelFB.channelNumber());
        channel.setNode(node);
        channel.setData(new int[node.getNodeType().getDataCount()]);
        node.getChannels().add(channel);
        return channel;
    }

    private static Device createDeviceFromFB(DeviceFB deviceFB, Channel channel) {
        Device device = new Device();
        device.setChannel(channel);
        channel.setDevice(device);
        device.setId(deviceFB.id());
        device.setName(deviceFB.name());
        return device;
    }

    private static GroupRule createGroupRuleFromFB(GroupRuleFB groupRuleFB, List<Device> devices) {
        GroupRule groupRule = new GroupRule();
        groupRule.setName(groupRuleFB.name());
        groupRule.setActive(groupRuleFB.active());
        groupRule.setRules(new ArrayList<>());
        for(int i = 0; i < groupRuleFB.rulesLength(); i++) {
            createRuleFromFB(groupRuleFB.rules(i), groupRule, devices);
        }
        return groupRule;
    }

    private static Rule createRuleFromFB(RuleFB ruleFB, GroupRule groupRule, List<Device> devices) {
        Rule rule = new Rule();
        rule.setId(ruleFB.id());
        rule.setName(ruleFB.name());
        rule.setActive(ruleFB.active());
        rule.setOriginalExpression(ruleFB.originalExpression());
        rule.setConditions(new ArrayList<>());
        for(int i = 0; i < ruleFB.conditionsLength(); i++) {
            for(Device device : devices) {
                if(device.getId() == ruleFB.conditions(i).deviceId()) {
                    createConditionFromFB(ruleFB.conditions(i), rule, device);
                }
            }
        }
        rule.setActions(new ArrayList<>());
        for(int i = 0; i < ruleFB.actionsLength(); i++) {
            for(Device device : devices) {
                if (device.getId() == ruleFB.actions(i).deviceId()) {
                    createActionFromFB(ruleFB.actions(i), rule, device);
                }
            }
        }
        rule.setGroupRule(groupRule);
        groupRule.getRules().add(rule);
        return rule;
    }

    private static Condition createConditionFromFB(ConditionFB conditionFB, Rule rule, Device device) {
        Condition condition = new Condition();
        condition.setId(conditionFB.id());
        condition.setName(conditionFB.name());
        condition.setRule(rule);
        byte[] operators = new byte[conditionFB.operatorsLength()];
        for(int i = 0; i < conditionFB.operatorsLength(); i++) {
            operators[i] = conditionFB.operators(i);
        }
        int[] values = new int[conditionFB.valuesLength()];
        for(int i = 0; i < conditionFB.valuesLength(); i++) {
            values[i] = conditionFB.values(i);
        }
        condition.setOperators(operators);
        condition.setDevice(device);
        condition.setValues(values);
        rule.getConditions().add(condition);
        return condition;
    }

    private static Action createActionFromFB(ActionFB actionFB, Rule rule, Device device) {
        Action action = new Action();
        action.setRule(rule);
        action.setId(actionFB.id());
        action.setName(actionFB.name());
        int[] values = new int[actionFB.valuesLength()];
        for(int i = 0; i < actionFB.valuesLength(); i++) {
            values[i] = actionFB.values(i);
        }
        action.setDevice(device);
        action.setValues(values);
        rule.getActions().add(action);
        return action;
    }

}
