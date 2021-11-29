// automatically generated, do not modify

package uhk.hausy.subsystem.core.flatBuffer.objects.server;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

public class GroupListFB extends Table {
  public static GroupListFB getRootAsGroupListFB(ByteBuffer _bb) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (new GroupListFB()).__init(_bb.getInt(_bb.position()) + _bb.position(), _bb); }
  public GroupListFB __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public int subsystemId() { int o = __offset(4); return o != 0 ? bb.getInt(o + bb_pos) : 0; }
  public GroupRuleFB groups(int j) { return groups(new GroupRuleFB(), j); }
  public GroupRuleFB groups(GroupRuleFB obj, int j) { int o = __offset(6); return o != 0 ? obj.__init(__indirect(__vector(o) + j * 4), bb) : null; }
  public int groupsLength() { int o = __offset(6); return o != 0 ? __vector_len(o) : 0; }

  public static int createGroupListFB(FlatBufferBuilder builder,
      int subsystemId,
      int groups) {
    builder.startObject(2);
    GroupListFB.addGroups(builder, groups);
    GroupListFB.addSubsystemId(builder, subsystemId);
    return GroupListFB.endGroupListFB(builder);
  }

  public static void startGroupListFB(FlatBufferBuilder builder) { builder.startObject(2); }
  public static void addSubsystemId(FlatBufferBuilder builder, int subsystemId) { builder.addInt(0, subsystemId, 0); }
  public static void addGroups(FlatBufferBuilder builder, int groupsOffset) { builder.addOffset(1, groupsOffset, 0); }
  public static int createGroupsVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startGroupsVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static int endGroupListFB(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
  public static void finishGroupListFBBuffer(FlatBufferBuilder builder, int offset) { builder.finish(offset); }
};

