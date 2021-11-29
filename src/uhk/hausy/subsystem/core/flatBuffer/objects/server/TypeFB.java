// automatically generated, do not modify

package uhk.hausy.subsystem.core.flatBuffer.objects.server;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

public class TypeFB extends Table {
  public static TypeFB getRootAsTypeFB(ByteBuffer _bb) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (new TypeFB()).__init(_bb.getInt(_bb.position()) + _bb.position(), _bb); }
  public TypeFB __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public byte id() { int o = __offset(4); return o != 0 ? bb.get(o + bb_pos) : 0; }
  public byte accessType() { int o = __offset(6); return o != 0 ? bb.get(o + bb_pos) : 0; }
  public byte latency() { int o = __offset(8); return o != 0 ? bb.get(o + bb_pos) : 0; }
  public byte dataRange() { int o = __offset(10); return o != 0 ? bb.get(o + bb_pos) : 0; }
  public byte dataCount() { int o = __offset(12); return o != 0 ? bb.get(o + bb_pos) : 0; }
  public byte minDelay() { int o = __offset(14); return o != 0 ? bb.get(o + bb_pos) : 0; }

  public static int createTypeFB(FlatBufferBuilder builder,
      byte id,
      byte accessType,
      byte latency,
      byte dataRange,
      byte dataCount,
      byte minDelay) {
    builder.startObject(6);
    TypeFB.addMinDelay(builder, minDelay);
    TypeFB.addDataCount(builder, dataCount);
    TypeFB.addDataRange(builder, dataRange);
    TypeFB.addLatency(builder, latency);
    TypeFB.addAccessType(builder, accessType);
    TypeFB.addId(builder, id);
    return TypeFB.endTypeFB(builder);
  }

  public static void startTypeFB(FlatBufferBuilder builder) { builder.startObject(6); }
  public static void addId(FlatBufferBuilder builder, byte id) { builder.addByte(0, id, 0); }
  public static void addAccessType(FlatBufferBuilder builder, byte accessType) { builder.addByte(1, accessType, 0); }
  public static void addLatency(FlatBufferBuilder builder, byte latency) { builder.addByte(2, latency, 0); }
  public static void addDataRange(FlatBufferBuilder builder, byte dataRange) { builder.addByte(3, dataRange, 0); }
  public static void addDataCount(FlatBufferBuilder builder, byte dataCount) { builder.addByte(4, dataCount, 0); }
  public static void addMinDelay(FlatBufferBuilder builder, byte minDelay) { builder.addByte(5, minDelay, 0); }
  public static int endTypeFB(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
};

