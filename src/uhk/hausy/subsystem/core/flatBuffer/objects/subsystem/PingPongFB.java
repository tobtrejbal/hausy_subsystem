// automatically generated, do not modify

package uhk.hausy.subsystem.core.flatBuffer.objects.subsystem;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

public class PingPongFB extends Table {
  public static PingPongFB getRootAsPingPongFB(ByteBuffer _bb) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (new PingPongFB()).__init(_bb.getInt(_bb.position()) + _bb.position(), _bb); }
  public PingPongFB __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public long ping() { int o = __offset(4); return o != 0 ? bb.getLong(o + bb_pos) : 0; }
  public long pong() { int o = __offset(6); return o != 0 ? bb.getLong(o + bb_pos) : 0; }

  public static int createPingPongFB(FlatBufferBuilder builder,
      long ping,
      long pong) {
    builder.startObject(2);
    PingPongFB.addPong(builder, pong);
    PingPongFB.addPing(builder, ping);
    return PingPongFB.endPingPongFB(builder);
  }

  public static void startPingPongFB(FlatBufferBuilder builder) { builder.startObject(2); }
  public static void addPing(FlatBufferBuilder builder, long ping) { builder.addLong(0, ping, 0); }
  public static void addPong(FlatBufferBuilder builder, long pong) { builder.addLong(1, pong, 0); }
  public static int endPingPongFB(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
  public static void finishPingPongFBBuffer(FlatBufferBuilder builder, int offset) { builder.finish(offset); }
};

