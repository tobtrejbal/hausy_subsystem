// automatically generated, do not modify

package uhk.hausy.subsystem.core.flatBuffer.objects.server;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

public class RuleFB extends Table {
  public static RuleFB getRootAsRuleFB(ByteBuffer _bb) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (new RuleFB()).__init(_bb.getInt(_bb.position()) + _bb.position(), _bb); }
  public RuleFB __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public int id() { int o = __offset(4); return o != 0 ? bb.getInt(o + bb_pos) : 0; }
  public String name() { int o = __offset(6); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer nameAsByteBuffer() { return __vector_as_bytebuffer(6, 1); }
  public boolean active() { int o = __offset(8); return o != 0 ? 0!=bb.get(o + bb_pos) : false; }
  public String originalExpression() { int o = __offset(10); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer originalExpressionAsByteBuffer() { return __vector_as_bytebuffer(10, 1); }
  public ConditionFB conditions(int j) { return conditions(new ConditionFB(), j); }
  public ConditionFB conditions(ConditionFB obj, int j) { int o = __offset(12); return o != 0 ? obj.__init(__indirect(__vector(o) + j * 4), bb) : null; }
  public int conditionsLength() { int o = __offset(12); return o != 0 ? __vector_len(o) : 0; }
  public ActionFB actions(int j) { return actions(new ActionFB(), j); }
  public ActionFB actions(ActionFB obj, int j) { int o = __offset(14); return o != 0 ? obj.__init(__indirect(__vector(o) + j * 4), bb) : null; }
  public int actionsLength() { int o = __offset(14); return o != 0 ? __vector_len(o) : 0; }

  public static int createRuleFB(FlatBufferBuilder builder,
      int id,
      int name,
      boolean active,
      int originalExpression,
      int conditions,
      int actions) {
    builder.startObject(6);
    RuleFB.addActions(builder, actions);
    RuleFB.addConditions(builder, conditions);
    RuleFB.addOriginalExpression(builder, originalExpression);
    RuleFB.addName(builder, name);
    RuleFB.addId(builder, id);
    RuleFB.addActive(builder, active);
    return RuleFB.endRuleFB(builder);
  }

  public static void startRuleFB(FlatBufferBuilder builder) { builder.startObject(6); }
  public static void addId(FlatBufferBuilder builder, int id) { builder.addInt(0, id, 0); }
  public static void addName(FlatBufferBuilder builder, int nameOffset) { builder.addOffset(1, nameOffset, 0); }
  public static void addActive(FlatBufferBuilder builder, boolean active) { builder.addBoolean(2, active, false); }
  public static void addOriginalExpression(FlatBufferBuilder builder, int originalExpressionOffset) { builder.addOffset(3, originalExpressionOffset, 0); }
  public static void addConditions(FlatBufferBuilder builder, int conditionsOffset) { builder.addOffset(4, conditionsOffset, 0); }
  public static int createConditionsVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startConditionsVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addActions(FlatBufferBuilder builder, int actionsOffset) { builder.addOffset(5, actionsOffset, 0); }
  public static int createActionsVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startActionsVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static int endRuleFB(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
};

