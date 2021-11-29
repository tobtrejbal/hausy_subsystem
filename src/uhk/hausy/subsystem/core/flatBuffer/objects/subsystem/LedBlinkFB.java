// automatically generated, do not modify

package uhk.hausy.subsystem.core.flatBuffer.objects.subsystem;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

public class LedBlinkFB extends Table {
  public static LedBlinkFB getRootAsLedBlinkFB(ByteBuffer _bb) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (new LedBlinkFB()).__init(_bb.getInt(_bb.position()) + _bb.position(), _bb); }
  public LedBlinkFB __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public byte led() { int o = __offset(4); return o != 0 ? bb.get(o + bb_pos) : 0; }
  public byte count() { int o = __offset(6); return o != 0 ? bb.get(o + bb_pos) : 0; }
  public short delay() { int o = __offset(8); return o != 0 ? bb.getShort(o + bb_pos) : 0; }

  public static int createLedBlinkFB(FlatBufferBuilder builder,
      byte led,
      byte count,
      short delay) {
    builder.startObject(3);
    LedBlinkFB.addDelay(builder, delay);
    LedBlinkFB.addCount(builder, count);
    LedBlinkFB.addLed(builder, led);
    return LedBlinkFB.endLedBlinkFB(builder);
  }

  public static void startLedBlinkFB(FlatBufferBuilder builder) { builder.startObject(3); }
  public static void addLed(FlatBufferBuilder builder, byte led) { builder.addByte(0, led, 0); }
  public static void addCount(FlatBufferBuilder builder, byte count) { builder.addByte(1, count, 0); }
  public static void addDelay(FlatBufferBuilder builder, short delay) { builder.addShort(2, delay, 0); }
  public static int endLedBlinkFB(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
  public static void finishLedBlinkFBBuffer(FlatBufferBuilder builder, int offset) { builder.finish(offset); }
};

