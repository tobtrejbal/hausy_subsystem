// automatically generated, do not modify

package uhk.hausy.subsystem.core.flatBuffer.objects.server;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

public class NodesFB extends Table {
  public static NodesFB getRootAsNodesFB(ByteBuffer _bb) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (new NodesFB()).__init(_bb.getInt(_bb.position()) + _bb.position(), _bb); }
  public NodesFB __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public int subsystemId() { int o = __offset(4); return o != 0 ? bb.getInt(o + bb_pos) : 0; }
  public NodeFB nodes(int j) { return nodes(new NodeFB(), j); }
  public NodeFB nodes(NodeFB obj, int j) { int o = __offset(6); return o != 0 ? obj.__init(__indirect(__vector(o) + j * 4), bb) : null; }
  public int nodesLength() { int o = __offset(6); return o != 0 ? __vector_len(o) : 0; }

  public static int createNodesFB(FlatBufferBuilder builder,
      int subsystemId,
      int nodes) {
    builder.startObject(2);
    NodesFB.addNodes(builder, nodes);
    NodesFB.addSubsystemId(builder, subsystemId);
    return NodesFB.endNodesFB(builder);
  }

  public static void startNodesFB(FlatBufferBuilder builder) { builder.startObject(2); }
  public static void addSubsystemId(FlatBufferBuilder builder, int subsystemId) { builder.addInt(0, subsystemId, 0); }
  public static void addNodes(FlatBufferBuilder builder, int nodesOffset) { builder.addOffset(1, nodesOffset, 0); }
  public static int createNodesVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startNodesVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static int endNodesFB(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
  public static void finishNodesFBBuffer(FlatBufferBuilder builder, int offset) { builder.finish(offset); }
};

