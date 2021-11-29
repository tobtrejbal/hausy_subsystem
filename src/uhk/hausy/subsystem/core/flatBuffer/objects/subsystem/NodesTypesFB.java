// automatically generated, do not modify

package uhk.hausy.subsystem.core.flatBuffer.objects.subsystem;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

public class NodesTypesFB extends Table {
  public static NodesTypesFB getRootAsNodesTypesFB(ByteBuffer _bb) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (new NodesTypesFB()).__init(_bb.getInt(_bb.position()) + _bb.position(), _bb); }
  public NodesTypesFB __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public NodeFB nodes(int j) { return nodes(new NodeFB(), j); }
  public NodeFB nodes(NodeFB obj, int j) { int o = __offset(4); return o != 0 ? obj.__init(__indirect(__vector(o) + j * 4), bb) : null; }
  public int nodesLength() { int o = __offset(4); return o != 0 ? __vector_len(o) : 0; }
  public TypeFB types(int j) { return types(new TypeFB(), j); }
  public TypeFB types(TypeFB obj, int j) { int o = __offset(6); return o != 0 ? obj.__init(__indirect(__vector(o) + j * 4), bb) : null; }
  public int typesLength() { int o = __offset(6); return o != 0 ? __vector_len(o) : 0; }

  public static int createNodesTypesFB(FlatBufferBuilder builder,
      int nodes,
      int types) {
    builder.startObject(2);
    NodesTypesFB.addTypes(builder, types);
    NodesTypesFB.addNodes(builder, nodes);
    return NodesTypesFB.endNodesTypesFB(builder);
  }

  public static void startNodesTypesFB(FlatBufferBuilder builder) { builder.startObject(2); }
  public static void addNodes(FlatBufferBuilder builder, int nodesOffset) { builder.addOffset(0, nodesOffset, 0); }
  public static int createNodesVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startNodesVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addTypes(FlatBufferBuilder builder, int typesOffset) { builder.addOffset(1, typesOffset, 0); }
  public static int createTypesVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startTypesVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static int endNodesTypesFB(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
  public static void finishNodesTypesFBBuffer(FlatBufferBuilder builder, int offset) { builder.finish(offset); }
};

