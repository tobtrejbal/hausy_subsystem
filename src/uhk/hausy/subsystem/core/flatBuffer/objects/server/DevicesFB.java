// automatically generated, do not modify

package uhk.hausy.subsystem.core.flatBuffer.objects.server;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

public class DevicesFB extends Table {
  public static DevicesFB getRootAsDevicesFB(ByteBuffer _bb) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (new DevicesFB()).__init(_bb.getInt(_bb.position()) + _bb.position(), _bb); }
  public DevicesFB __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public int subsystemId() { int o = __offset(4); return o != 0 ? bb.getInt(o + bb_pos) : 0; }
  public DeviceFB devices(int j) { return devices(new DeviceFB(), j); }
  public DeviceFB devices(DeviceFB obj, int j) { int o = __offset(6); return o != 0 ? obj.__init(__indirect(__vector(o) + j * 4), bb) : null; }
  public int devicesLength() { int o = __offset(6); return o != 0 ? __vector_len(o) : 0; }

  public static int createDevicesFB(FlatBufferBuilder builder,
      int subsystemId,
      int devices) {
    builder.startObject(2);
    DevicesFB.addDevices(builder, devices);
    DevicesFB.addSubsystemId(builder, subsystemId);
    return DevicesFB.endDevicesFB(builder);
  }

  public static void startDevicesFB(FlatBufferBuilder builder) { builder.startObject(2); }
  public static void addSubsystemId(FlatBufferBuilder builder, int subsystemId) { builder.addInt(0, subsystemId, 0); }
  public static void addDevices(FlatBufferBuilder builder, int devicesOffset) { builder.addOffset(1, devicesOffset, 0); }
  public static int createDevicesVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startDevicesVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static int endDevicesFB(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
  public static void finishDevicesFBBuffer(FlatBufferBuilder builder, int offset) { builder.finish(offset); }
};

