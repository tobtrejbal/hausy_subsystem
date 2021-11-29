// automatically generated, do not modify

package uhk.hausy.subsystem.core.flatBuffer.objects.server;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

public class TypesFB extends Table {
  public static TypesFB getRootAsTypesFB(ByteBuffer _bb) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (new TypesFB()).__init(_bb.getInt(_bb.position()) + _bb.position(), _bb); }
  public TypesFB __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public int subsystemId() { int o = __offset(4); return o != 0 ? bb.getInt(o + bb_pos) : 0; }
  public TypeFB types(int j) { return types(new TypeFB(), j); }
  public TypeFB types(TypeFB obj, int j) { int o = __offset(6); return o != 0 ? obj.__init(__indirect(__vector(o) + j * 4), bb) : null; }
  public int typesLength() { int o = __offset(6); return o != 0 ? __vector_len(o) : 0; }

  public static int createTypesFB(FlatBufferBuilder builder,
      int subsystemId,
      int types) {
    builder.startObject(2);
    TypesFB.addTypes(builder, types);
    TypesFB.addSubsystemId(builder, subsystemId);
    return TypesFB.endTypesFB(builder);
  }

  public static void startTypesFB(FlatBufferBuilder builder) { builder.startObject(2); }
  public static void addSubsystemId(FlatBufferBuilder builder, int subsystemId) { builder.addInt(0, subsystemId, 0); }
  public static void addTypes(FlatBufferBuilder builder, int typesOffset) { builder.addOffset(1, typesOffset, 0); }
  public static int createTypesVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startTypesVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static int endTypesFB(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
  public static void finishTypesFBBuffer(FlatBufferBuilder builder, int offset) { builder.finish(offset); }
};

