package uhk.hausy.subsystem.core.flatBuffer.transformer;

import com.google.flatbuffers.FlatBufferBuilder;
import uhk.hausy.subsystem.core.HausyCore;
import uhk.hausy.subsystem.core.app.AppConfig;
import uhk.hausy.subsystem.core.model.*;
import uhk.hausy.subsystem.core.flatBuffer.objects.subsystem.*;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by tobou on 14.10.2016.
 */
public class TransformerSubsystem {

    public static long counter;

    public static void fbNodesToObjects(byte[] bytes, List<NodeType> nodeTypes, List<Node> nodes) {
        NodesFB nodesChannelsFB = NodesFB.getRootAsNodesFB(ByteBuffer.wrap(bytes));
        for(int i = 0; i < nodesChannelsFB.nodesLength(); i++) {
            nodes.add(createNodeFromFB(nodesChannelsFB.nodes(i), nodeTypes));
        }
    }

    public static void fbTypesToObjects(byte[] bytes, List<NodeType> nodeTypes) {
        TypesFB typesFB = TypesFB.getRootAsTypesFB(ByteBuffer.wrap(bytes));
        for(int i = 0; i < typesFB.typesLength(); i++) {
            nodeTypes.add(createTypeFromFB(typesFB.types(i)));
        }
    }

    public static void fbNodesAndTypesToObjects(byte[] bytes, List<NodeType> nodeTypes, List<Node> nodes) {
        NodesTypesFB nodesTypesFB = NodesTypesFB.getRootAsNodesTypesFB(ByteBuffer.wrap(bytes));
        for(int i = 0; i < nodesTypesFB.typesLength(); i++) {
            nodeTypes.add(createTypeFromFB(nodesTypesFB.types(i)));
        }
        for(int i = 0; i < nodesTypesFB.nodesLength(); i++) {
            nodes.add(createNodeFromFB(nodesTypesFB.nodes(i), nodeTypes));
        }
    }

    public static short fbDataToObjects(byte[] bytes, Map<Short, Node> nodes) {
        DataFB dataFB = DataFB.getRootAsDataFB(ByteBuffer.wrap(bytes));
        Node node = nodes.get(dataFB.address());
        if(dataFB.counter() - counter > 1) {
            //System.out.println("AAAAAAAAAAAAAAAAAAAA");

        }
        counter = dataFB.counter();
        for(int i = 0; i < dataFB.channelDataLength(); i++) {
            ChannelDataFB channelDataFB = dataFB.channelData(i);
            Channel channel = node.getChannels().get(i);
            for(int j = 0; j < channelDataFB.dataLength(); j++) {
                channel.getData()[j] = channelDataFB.data(j);
            }
        }
        return node.getId();
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
        int node = NodesFB.createNodesFB(flatBufferBuilder, nodesOff);
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
        int type = TypesFB.createTypesFB(flatBufferBuilder, typesOff);
        flatBufferBuilder.finish(type);
        return flatBufferBuilder.sizedByteArray();
    }

    public static byte[] objectsToNodesTypesFB(List<NodeType> nodeTypes, List<Node> nodes) {
        FlatBufferBuilder flatBufferBuilder = new FlatBufferBuilder();
        int[] nodesOffs = new int[nodes.size()];
        int[] typesOffs = new int[nodeTypes.size()];
        for(int i = 0; i < nodes.size(); i++) {
            Node n = nodes.get(i);
            n.setMacAddress(" ");
            nodesOffs[i] = createNodeFB(flatBufferBuilder,n);
        }
        for(int i = 0; i < nodeTypes.size(); i++) {
            NodeType t = nodeTypes.get(i);
            typesOffs[i] = createTypeFB(flatBufferBuilder, nodeTypes.get(i));
        }
        int nodesOff = NodesTypesFB.createNodesVector(flatBufferBuilder, nodesOffs);
        int typesOff = NodesTypesFB.createTypesVector(flatBufferBuilder, typesOffs);
        int totalOff = NodesTypesFB.createNodesTypesFB(flatBufferBuilder, nodesOff, typesOff);
        flatBufferBuilder.finish(totalOff);
        return flatBufferBuilder.sizedByteArray();
    }

    public static byte[] nodeToFbData(Node node) {
        FlatBufferBuilder flatBufferBuilder = new FlatBufferBuilder();
        int[] channelsOffs = new int[node.getChannels().size()];
        for(int i = 0; i < node.getChannels().size(); i++) {
            channelsOffs[i] = createChannelDataFB(flatBufferBuilder, node.getChannels().get(i));
        }
        int channelsOff = DataFB.createChannelDataVector(flatBufferBuilder, channelsOffs);
        int totalOff = DataFB.createDataFB(flatBufferBuilder, channelsOff, node.getId(),0);
        flatBufferBuilder.finish(totalOff);
        return flatBufferBuilder.sizedByteArray();
    }

    private static int createTypeFB(FlatBufferBuilder flatBufferBuilder, NodeType t) {
        int offset = TypeFB.createTypeFB(flatBufferBuilder, t.getId(), t.getAccessType(), t.getLatency(), t.getDataRange(), t.getDataCount(), t.getMinDelay());
        return offset;
    }

    private static int createNodeFB(FlatBufferBuilder flatBufferBuilder, Node n) {
        int offset = NodeFB.createNodeFB(flatBufferBuilder, n.getId(), n.getNodeType().getId(), (byte) 0, n.getChannelCount(), flatBufferBuilder.createString(n.getMacAddress()), n.getRandom());
        return offset;
    }

    private static int createChannelDataFB(FlatBufferBuilder flatBufferBuilder, Channel c) {
        int[] data = new int[c.getData().length];
        for(int i = 0; i < data.length; i++) {
            data[i] = (int) c.getData()[i];
        }
        int dataOffs = ChannelDataFB.createDataVector(flatBufferBuilder, data);
        int offset = ChannelDataFB.createChannelDataFB(flatBufferBuilder, dataOffs,c.getChannelNumber());
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
        node.setChannelCount(nodeFB.channelCount());
        node.setChannels(new ArrayList<>());
        for(int i = 0; i < node.getChannelCount(); i++) {
            Channel channel = new Channel();
            channel.setChannelNumber((byte) i);
            channel.setNode(node);
            node.getChannels().add(channel);
        }
        node.setMacAddress(nodeFB.macAddress());
        node.setRandom(nodeFB.random());
        return node;
    }

}
