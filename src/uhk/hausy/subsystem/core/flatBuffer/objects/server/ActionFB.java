// automatically generated, do not modify

package uhk.hausy.subsystem.core.flatBuffer.objects.server;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

public class ActionFB extends Table {
  public static ActionFB getRootAsActionFB(ByteBuffer _bb) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (new ActionFB()).__init(_bb.getInt(_bb.position()) + _bb.position(), _bb); }
  public ActionFB __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public int id() { int o = __offset(4); return o != 0 ? bb.getInt(o + bb_pos) : 0; }
  public int deviceId() { int o = __offset(6); return o != 0 ? bb.getInt(o + bb_pos) : 0; }
  public int ruleId() { int o = __offset(8); return o != 0 ? bb.getInt(o + bb_pos) : 0; }
  public String name() { int o = __offset(10); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer nameAsByteBuffer() { return __vector_as_bytebuffer(10, 1); }
  public int values(int j) { int o = __offset(12); return o != 0 ? bb.getInt(__vector(o) + j * 4) : 0; }
  public int valuesLength() { int o = __offset(12); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer valuesAsByteBuffer() { return __vector_as_bytebuffer(12, 4); }

  public static int createActionFB(FlatBufferBuilder builder,
      int id,
      int deviceId,
      int ruleId,
      int name,
      int values) {
    builder.startObject(5);
    ActionFB.addValues(builder, values);
    ActionFB.addName(builder, name);
    ActionFB.addRuleId(builder, ruleId);
    ActionFB.addDeviceId(builder, deviceId);
    ActionFB.addId(builder, id);
    return ActionFB.endActionFB(builder);
  }

  public static void startActionFB(FlatBufferBuilder builder) { builder.startObject(5); }
  public static void addId(FlatBufferBuilder builder, int id) { builder.addInt(0, id, 0); }
  public static void addDeviceId(FlatBufferBuilder builder, int deviceId) { builder.addInt(1, deviceId, 0); }
  public static void addRuleId(FlatBufferBuilder builder, int ruleId) { builder.addInt(2, ruleId, 0); }
  public static void addName(FlatBufferBuilder builder, int nameOffset) { builder.addOffset(3, nameOffset, 0); }
  public static void addValues(FlatBufferBuilder builder, int valuesOffset) { builder.addOffset(4, valuesOffset, 0); }
  public static int createValuesVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addInt(data[i]); return builder.endVector(); }
  public static void startValuesVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static int endActionFB(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
};

