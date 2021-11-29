// automatically generated, do not modify

package uhk.hausy.subsystem.core.flatBuffer.objects.server;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

public class ConditionFB extends Table {
  public static ConditionFB getRootAsConditionFB(ByteBuffer _bb) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (new ConditionFB()).__init(_bb.getInt(_bb.position()) + _bb.position(), _bb); }
  public ConditionFB __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public int id() { int o = __offset(4); return o != 0 ? bb.getInt(o + bb_pos) : 0; }
  public int ruleId() { int o = __offset(6); return o != 0 ? bb.getInt(o + bb_pos) : 0; }
  public int deviceId() { int o = __offset(8); return o != 0 ? bb.getInt(o + bb_pos) : 0; }
  public String name() { int o = __offset(10); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer nameAsByteBuffer() { return __vector_as_bytebuffer(10, 1); }
  public byte operators(int j) { int o = __offset(12); return o != 0 ? bb.get(__vector(o) + j * 1) : 0; }
  public int operatorsLength() { int o = __offset(12); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer operatorsAsByteBuffer() { return __vector_as_bytebuffer(12, 1); }
  public int values(int j) { int o = __offset(14); return o != 0 ? bb.getInt(__vector(o) + j * 4) : 0; }
  public int valuesLength() { int o = __offset(14); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer valuesAsByteBuffer() { return __vector_as_bytebuffer(14, 4); }

  public static int createConditionFB(FlatBufferBuilder builder,
      int id,
      int ruleId,
      int deviceId,
      int name,
      int operators,
      int values) {
    builder.startObject(6);
    ConditionFB.addValues(builder, values);
    ConditionFB.addOperators(builder, operators);
    ConditionFB.addName(builder, name);
    ConditionFB.addDeviceId(builder, deviceId);
    ConditionFB.addRuleId(builder, ruleId);
    ConditionFB.addId(builder, id);
    return ConditionFB.endConditionFB(builder);
  }

  public static void startConditionFB(FlatBufferBuilder builder) { builder.startObject(6); }
  public static void addId(FlatBufferBuilder builder, int id) { builder.addInt(0, id, 0); }
  public static void addRuleId(FlatBufferBuilder builder, int ruleId) { builder.addInt(1, ruleId, 0); }
  public static void addDeviceId(FlatBufferBuilder builder, int deviceId) { builder.addInt(2, deviceId, 0); }
  public static void addName(FlatBufferBuilder builder, int nameOffset) { builder.addOffset(3, nameOffset, 0); }
  public static void addOperators(FlatBufferBuilder builder, int operatorsOffset) { builder.addOffset(4, operatorsOffset, 0); }
  public static int createOperatorsVector(FlatBufferBuilder builder, byte[] data) { builder.startVector(1, data.length, 1); for (int i = data.length - 1; i >= 0; i--) builder.addByte(data[i]); return builder.endVector(); }
  public static void startOperatorsVector(FlatBufferBuilder builder, int numElems) { builder.startVector(1, numElems, 1); }
  public static void addValues(FlatBufferBuilder builder, int valuesOffset) { builder.addOffset(5, valuesOffset, 0); }
  public static int createValuesVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addInt(data[i]); return builder.endVector(); }
  public static void startValuesVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static int endConditionFB(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
};

