// automatically generated, do not modify

package uhk.hausy.subsystem.core.flatBuffer.objects.server;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

public class NodeFB extends Table {
  public static NodeFB getRootAsNodeFB(ByteBuffer _bb) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (new NodeFB()).__init(_bb.getInt(_bb.position()) + _bb.position(), _bb); }
  public NodeFB __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public short id() { int o = __offset(4); return o != 0 ? bb.getShort(o + bb_pos) : 0; }
  public byte typeId() { int o = __offset(6); return o != 0 ? bb.get(o + bb_pos) : 0; }
  public byte nodeType() { int o = __offset(8); return o != 0 ? bb.get(o + bb_pos) : 0; }
  public ChannelFB channels(int j) { return channels(new ChannelFB(), j); }
  public ChannelFB channels(ChannelFB obj, int j) { int o = __offset(10); return o != 0 ? obj.__init(__indirect(__vector(o) + j * 4), bb) : null; }
  public int channelsLength() { int o = __offset(10); return o != 0 ? __vector_len(o) : 0; }
  public String macAddress() { int o = __offset(12); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer macAddressAsByteBuffer() { return __vector_as_bytebuffer(12, 1); }

  public static int createNodeFB(FlatBufferBuilder builder,
      short id,
      byte typeId,
      byte nodeType,
      int channels,
      int macAddress) {
    builder.startObject(5);
    NodeFB.addMacAddress(builder, macAddress);
    NodeFB.addChannels(builder, channels);
    NodeFB.addId(builder, id);
    NodeFB.addNodeType(builder, nodeType);
    NodeFB.addTypeId(builder, typeId);
    return NodeFB.endNodeFB(builder);
  }

  public static void startNodeFB(FlatBufferBuilder builder) { builder.startObject(5); }
  public static void addId(FlatBufferBuilder builder, short id) { builder.addShort(0, id, 0); }
  public static void addTypeId(FlatBufferBuilder builder, byte typeId) { builder.addByte(1, typeId, 0); }
  public static void addNodeType(FlatBufferBuilder builder, byte nodeType) { builder.addByte(2, nodeType, 0); }
  public static void addChannels(FlatBufferBuilder builder, int channelsOffset) { builder.addOffset(3, channelsOffset, 0); }
  public static int createChannelsVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startChannelsVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addMacAddress(FlatBufferBuilder builder, int macAddressOffset) { builder.addOffset(4, macAddressOffset, 0); }
  public static int endNodeFB(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
};

