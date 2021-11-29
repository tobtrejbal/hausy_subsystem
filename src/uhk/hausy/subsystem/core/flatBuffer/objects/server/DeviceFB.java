// automatically generated, do not modify

package uhk.hausy.subsystem.core.flatBuffer.objects.server;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

public class DeviceFB extends Table {
  public static DeviceFB getRootAsDeviceFB(ByteBuffer _bb) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (new DeviceFB()).__init(_bb.getInt(_bb.position()) + _bb.position(), _bb); }
  public DeviceFB __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public int id() { int o = __offset(4); return o != 0 ? bb.getInt(o + bb_pos) : 0; }
  public String name() { int o = __offset(6); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer nameAsByteBuffer() { return __vector_as_bytebuffer(6, 1); }
  public String channelId() { int o = __offset(8); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer channelIdAsByteBuffer() { return __vector_as_bytebuffer(8, 1); }

  public static int createDeviceFB(FlatBufferBuilder builder,
      int id,
      int name,
      int channelId) {
    builder.startObject(3);
    DeviceFB.addChannelId(builder, channelId);
    DeviceFB.addName(builder, name);
    DeviceFB.addId(builder, id);
    return DeviceFB.endDeviceFB(builder);
  }

  public static void startDeviceFB(FlatBufferBuilder builder) { builder.startObject(3); }
  public static void addId(FlatBufferBuilder builder, int id) { builder.addInt(0, id, 0); }
  public static void addName(FlatBufferBuilder builder, int nameOffset) { builder.addOffset(1, nameOffset, 0); }
  public static void addChannelId(FlatBufferBuilder builder, int channelIdOffset) { builder.addOffset(2, channelIdOffset, 0); }
  public static int endDeviceFB(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
};

