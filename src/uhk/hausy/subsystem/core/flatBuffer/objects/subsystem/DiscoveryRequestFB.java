// automatically generated, do not modify

package uhk.hausy.subsystem.core.flatBuffer.objects.subsystem;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

public class DiscoveryRequestFB extends Table {
  public static DiscoveryRequestFB getRootAsDiscoveryRequestFB(ByteBuffer _bb) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (new DiscoveryRequestFB()).__init(_bb.getInt(_bb.position()) + _bb.position(), _bb); }
  public DiscoveryRequestFB __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public byte nodeType() { int o = __offset(4); return o != 0 ? bb.get(o + bb_pos) : 0; }

  public static int createDiscoveryRequestFB(FlatBufferBuilder builder,
      byte nodeType) {
    builder.startObject(1);
    DiscoveryRequestFB.addNodeType(builder, nodeType);
    return DiscoveryRequestFB.endDiscoveryRequestFB(builder);
  }

  public static void startDiscoveryRequestFB(FlatBufferBuilder builder) { builder.startObject(1); }
  public static void addNodeType(FlatBufferBuilder builder, byte nodeType) { builder.addByte(0, nodeType, 0); }
  public static int endDiscoveryRequestFB(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
  public static void finishDiscoveryRequestFBBuffer(FlatBufferBuilder builder, int offset) { builder.finish(offset); }
};

