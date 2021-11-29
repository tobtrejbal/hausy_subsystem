// automatically generated, do not modify

package uhk.hausy.subsystem.core.flatBuffer.objects.server;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

public class ConfigurationRequestFB extends Table {
  public static ConfigurationRequestFB getRootAsConfigurationRequestFB(ByteBuffer _bb) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (new ConfigurationRequestFB()).__init(_bb.getInt(_bb.position()) + _bb.position(), _bb); }
  public ConfigurationRequestFB __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public int subsystemId() { int o = __offset(4); return o != 0 ? bb.getInt(o + bb_pos) : 0; }

  public static int createConfigurationRequestFB(FlatBufferBuilder builder,
      int subsystemId) {
    builder.startObject(1);
    ConfigurationRequestFB.addSubsystemId(builder, subsystemId);
    return ConfigurationRequestFB.endConfigurationRequestFB(builder);
  }

  public static void startConfigurationRequestFB(FlatBufferBuilder builder) { builder.startObject(1); }
  public static void addSubsystemId(FlatBufferBuilder builder, int subsystemId) { builder.addInt(0, subsystemId, 0); }
  public static int endConfigurationRequestFB(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
  public static void finishConfigurationRequestFBBuffer(FlatBufferBuilder builder, int offset) { builder.finish(offset); }
};

