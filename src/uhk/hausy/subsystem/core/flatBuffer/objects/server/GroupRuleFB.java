// automatically generated, do not modify

package uhk.hausy.subsystem.core.flatBuffer.objects.server;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

public class GroupRuleFB extends Table {
  public static GroupRuleFB getRootAsGroupRuleFB(ByteBuffer _bb) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (new GroupRuleFB()).__init(_bb.getInt(_bb.position()) + _bb.position(), _bb); }
  public GroupRuleFB __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public String name() { int o = __offset(4); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer nameAsByteBuffer() { return __vector_as_bytebuffer(4, 1); }
  public RuleFB rules(int j) { return rules(new RuleFB(), j); }
  public RuleFB rules(RuleFB obj, int j) { int o = __offset(6); return o != 0 ? obj.__init(__indirect(__vector(o) + j * 4), bb) : null; }
  public int rulesLength() { int o = __offset(6); return o != 0 ? __vector_len(o) : 0; }
  public boolean active() { int o = __offset(8); return o != 0 ? 0!=bb.get(o + bb_pos) : false; }

  public static int createGroupRuleFB(FlatBufferBuilder builder,
      int name,
      int rules,
      boolean active) {
    builder.startObject(3);
    GroupRuleFB.addRules(builder, rules);
    GroupRuleFB.addName(builder, name);
    GroupRuleFB.addActive(builder, active);
    return GroupRuleFB.endGroupRuleFB(builder);
  }

  public static void startGroupRuleFB(FlatBufferBuilder builder) { builder.startObject(3); }
  public static void addName(FlatBufferBuilder builder, int nameOffset) { builder.addOffset(0, nameOffset, 0); }
  public static void addRules(FlatBufferBuilder builder, int rulesOffset) { builder.addOffset(1, rulesOffset, 0); }
  public static int createRulesVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startRulesVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addActive(FlatBufferBuilder builder, boolean active) { builder.addBoolean(2, active, false); }
  public static int endGroupRuleFB(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
};

