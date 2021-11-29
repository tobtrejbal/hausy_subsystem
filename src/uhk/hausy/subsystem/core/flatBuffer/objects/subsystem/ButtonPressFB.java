// automatically generated, do not modify

package uhk.hausy.subsystem.core.flatBuffer.objects.subsystem;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

public class ButtonPressFB extends Table {
  public static ButtonPressFB getRootAsButtonPressFB(ByteBuffer _bb) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (new ButtonPressFB()).__init(_bb.getInt(_bb.position()) + _bb.position(), _bb); }
  public ButtonPressFB __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public byte button() { int o = __offset(4); return o != 0 ? bb.get(o + bb_pos) : 0; }

  public static int createButtonPressFB(FlatBufferBuilder builder,
      byte button) {
    builder.startObject(1);
    ButtonPressFB.addButton(builder, button);
    return ButtonPressFB.endButtonPressFB(builder);
  }

  public static void startButtonPressFB(FlatBufferBuilder builder) { builder.startObject(1); }
  public static void addButton(FlatBufferBuilder builder, byte button) { builder.addByte(0, button, 0); }
  public static int endButtonPressFB(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
  public static void finishButtonPressFBBuffer(FlatBufferBuilder builder, int offset) { builder.finish(offset); }
};

