// automatically generated, do not modify

package uhk.hausy.subsystem.core.flatBuffer.objects.server;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

public class ChannelFB extends Table {
  public static ChannelFB getRootAsChannelFB(ByteBuffer _bb) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (new ChannelFB()).__init(_bb.getInt(_bb.position()) + _bb.position(), _bb); }
  public ChannelFB __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public String id() { int o = __offset(4); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer idAsByteBuffer() { return __vector_as_bytebuffer(4, 1); }
  public short nodeId() { int o = __offset(6); return o != 0 ? bb.getShort(o + bb_pos) : 0; }
  public byte channelNumber() { int o = __offset(8); return o != 0 ? bb.get(o + bb_pos) : 0; }

  public static int createChannelFB(FlatBufferBuilder builder,
      int id,
      short nodeId,
      byte channelNumber) {
    builder.startObject(3);
    ChannelFB.addId(builder, id);
    ChannelFB.addNodeId(builder, nodeId);
    ChannelFB.addChannelNumber(builder, channelNumber);
    return ChannelFB.endChannelFB(builder);
  }

  public static void startChannelFB(FlatBufferBuilder builder) { builder.startObject(3); }
  public static void addId(FlatBufferBuilder builder, int idOffset) { builder.addOffset(0, idOffset, 0); }
  public static void addNodeId(FlatBufferBuilder builder, short nodeId) { builder.addShort(1, nodeId, 0); }
  public static void addChannelNumber(FlatBufferBuilder builder, byte channelNumber) { builder.addByte(2, channelNumber, 0); }
  public static int endChannelFB(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
};

