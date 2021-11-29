// automatically generated, do not modify

package uhk.hausy.subsystem.core.flatBuffer.objects.server;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

public class ConfigurationFB extends Table {
  public static ConfigurationFB getRootAsConfigurationFB(ByteBuffer _bb) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (new ConfigurationFB()).__init(_bb.getInt(_bb.position()) + _bb.position(), _bb); }
  public ConfigurationFB __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public int subsystemId() { int o = __offset(4); return o != 0 ? bb.getInt(o + bb_pos) : 0; }
  public TypeFB types(int j) { return types(new TypeFB(), j); }
  public TypeFB types(TypeFB obj, int j) { int o = __offset(6); return o != 0 ? obj.__init(__indirect(__vector(o) + j * 4), bb) : null; }
  public int typesLength() { int o = __offset(6); return o != 0 ? __vector_len(o) : 0; }
  public NodeFB nodes(int j) { return nodes(new NodeFB(), j); }
  public NodeFB nodes(NodeFB obj, int j) { int o = __offset(8); return o != 0 ? obj.__init(__indirect(__vector(o) + j * 4), bb) : null; }
  public int nodesLength() { int o = __offset(8); return o != 0 ? __vector_len(o) : 0; }
  public DeviceFB devices(int j) { return devices(new DeviceFB(), j); }
  public DeviceFB devices(DeviceFB obj, int j) { int o = __offset(10); return o != 0 ? obj.__init(__indirect(__vector(o) + j * 4), bb) : null; }
  public int devicesLength() { int o = __offset(10); return o != 0 ? __vector_len(o) : 0; }
  public GroupRuleFB groupRules(int j) { return groupRules(new GroupRuleFB(), j); }
  public GroupRuleFB groupRules(GroupRuleFB obj, int j) { int o = __offset(12); return o != 0 ? obj.__init(__indirect(__vector(o) + j * 4), bb) : null; }
  public int groupRulesLength() { int o = __offset(12); return o != 0 ? __vector_len(o) : 0; }

  public static int createConfigurationFB(FlatBufferBuilder builder,
      int subsystemId,
      int types,
      int nodes,
      int devices,
      int groupRules) {
    builder.startObject(5);
    ConfigurationFB.addGroupRules(builder, groupRules);
    ConfigurationFB.addDevices(builder, devices);
    ConfigurationFB.addNodes(builder, nodes);
    ConfigurationFB.addTypes(builder, types);
    ConfigurationFB.addSubsystemId(builder, subsystemId);
    return ConfigurationFB.endConfigurationFB(builder);
  }

  public static void startConfigurationFB(FlatBufferBuilder builder) { builder.startObject(5); }
  public static void addSubsystemId(FlatBufferBuilder builder, int subsystemId) { builder.addInt(0, subsystemId, 0); }
  public static void addTypes(FlatBufferBuilder builder, int typesOffset) { builder.addOffset(1, typesOffset, 0); }
  public static int createTypesVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startTypesVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addNodes(FlatBufferBuilder builder, int nodesOffset) { builder.addOffset(2, nodesOffset, 0); }
  public static int createNodesVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startNodesVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addDevices(FlatBufferBuilder builder, int devicesOffset) { builder.addOffset(3, devicesOffset, 0); }
  public static int createDevicesVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startDevicesVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addGroupRules(FlatBufferBuilder builder, int groupRulesOffset) { builder.addOffset(4, groupRulesOffset, 0); }
  public static int createGroupRulesVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startGroupRulesVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static int endConfigurationFB(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
  public static void finishConfigurationFBBuffer(FlatBufferBuilder builder, int offset) { builder.finish(offset); }
};

