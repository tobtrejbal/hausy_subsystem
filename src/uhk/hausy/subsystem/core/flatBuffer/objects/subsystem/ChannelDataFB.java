// automatically generated, do not modify

package uhk.hausy.subsystem.core.flatBuffer.objects.subsystem;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

public class ChannelDataFB extends Table {
  public static ChannelDataFB getRootAsChannelDataFB(ByteBuffer _bb) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (new ChannelDataFB()).__init(_bb.getInt(_bb.position()) + _bb.position(), _bb); }
  public ChannelDataFB __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public int data(int j) { int o = __offset(4); return o != 0 ? bb.getInt(__vector(o) + j * 4) : 0; }
  public int dataLength() { int o = __offset(4); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer dataAsByteBuffer() { return __vector_as_bytebuffer(4, 4); }
  public byte channelId() { int o = __offset(6); return o != 0 ? bb.get(o + bb_pos) : 0; }

  public static int createChannelDataFB(FlatBufferBuilder builder,
      int data,
      byte channelId) {
    builder.startObject(2);
    ChannelDataFB.addData(builder, data);
    ChannelDataFB.addChannelId(builder, channelId);
    return ChannelDataFB.endChannelDataFB(builder);
  }

  public static void startChannelDataFB(FlatBufferBuilder builder) { builder.startObject(2); }
  public static void addData(FlatBufferBuilder builder, int dataOffset) { builder.addOffset(0, dataOffset, 0); }
  public static int createDataVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addInt(data[i]); return builder.endVector(); }
  public static void startDataVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addChannelId(FlatBufferBuilder builder, byte channelId) { builder.addByte(1, channelId, 0); }
  public static int endChannelDataFB(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
};

