// automatically generated, do not modify

package uhk.hausy.subsystem.core.flatBuffer.objects.server;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

public class DataFB extends Table {
  public static DataFB getRootAsDataFB(ByteBuffer _bb) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (new DataFB()).__init(_bb.getInt(_bb.position()) + _bb.position(), _bb); }
  public DataFB __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public int subsystemId() { int o = __offset(4); return o != 0 ? bb.getInt(o + bb_pos) : 0; }
  public ChannelDataFB channelData(int j) { return channelData(new ChannelDataFB(), j); }
  public ChannelDataFB channelData(ChannelDataFB obj, int j) { int o = __offset(6); return o != 0 ? obj.__init(__indirect(__vector(o) + j * 4), bb) : null; }
  public int channelDataLength() { int o = __offset(6); return o != 0 ? __vector_len(o) : 0; }
  public short address() { int o = __offset(8); return o != 0 ? bb.getShort(o + bb_pos) : 0; }

  public static int createDataFB(FlatBufferBuilder builder,
      int subsystemId,
      int channelData,
      short address) {
    builder.startObject(3);
    DataFB.addChannelData(builder, channelData);
    DataFB.addSubsystemId(builder, subsystemId);
    DataFB.addAddress(builder, address);
    return DataFB.endDataFB(builder);
  }

  public static void startDataFB(FlatBufferBuilder builder) { builder.startObject(3); }
  public static void addSubsystemId(FlatBufferBuilder builder, int subsystemId) { builder.addInt(0, subsystemId, 0); }
  public static void addChannelData(FlatBufferBuilder builder, int channelDataOffset) { builder.addOffset(1, channelDataOffset, 0); }
  public static int createChannelDataVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startChannelDataVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addAddress(FlatBufferBuilder builder, short address) { builder.addShort(2, address, 0); }
  public static int endDataFB(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
  public static void finishDataFBBuffer(FlatBufferBuilder builder, int offset) { builder.finish(offset); }
};

