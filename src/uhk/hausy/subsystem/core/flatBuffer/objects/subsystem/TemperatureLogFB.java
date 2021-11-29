// automatically generated, do not modify

package uhk.hausy.subsystem.core.flatBuffer.objects.subsystem;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

public class TemperatureLogFB extends Table {
  public static TemperatureLogFB getRootAsTemperatureLogFB(ByteBuffer _bb) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (new TemperatureLogFB()).__init(_bb.getInt(_bb.position()) + _bb.position(), _bb); }
  public TemperatureLogFB __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public float temperature() { int o = __offset(4); return o != 0 ? bb.getFloat(o + bb_pos) : 0; }

  public static int createTemperatureLogFB(FlatBufferBuilder builder,
      float temperature) {
    builder.startObject(1);
    TemperatureLogFB.addTemperature(builder, temperature);
    return TemperatureLogFB.endTemperatureLogFB(builder);
  }

  public static void startTemperatureLogFB(FlatBufferBuilder builder) { builder.startObject(1); }
  public static void addTemperature(FlatBufferBuilder builder, float temperature) { builder.addFloat(0, temperature, 0); }
  public static int endTemperatureLogFB(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
  public static void finishTemperatureLogFBBuffer(FlatBufferBuilder builder, int offset) { builder.finish(offset); }
};

