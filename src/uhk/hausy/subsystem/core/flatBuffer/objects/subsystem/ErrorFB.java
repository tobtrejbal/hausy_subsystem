// automatically generated, do not modify

package uhk.hausy.subsystem.core.flatBuffer.objects.subsystem;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

public class ErrorFB extends Table {
  public static ErrorFB getRootAsErrorFB(ByteBuffer _bb) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (new ErrorFB()).__init(_bb.getInt(_bb.position()) + _bb.position(), _bb); }
  public ErrorFB __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public byte errorCode() { int o = __offset(4); return o != 0 ? bb.get(o + bb_pos) : 0; }
  public short nodeId() { int o = __offset(6); return o != 0 ? bb.getShort(o + bb_pos) : 0; }
  public String text() { int o = __offset(8); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer textAsByteBuffer() { return __vector_as_bytebuffer(8, 1); }

  public static int createErrorFB(FlatBufferBuilder builder,
      byte errorCode,
      short nodeId,
      int text) {
    builder.startObject(3);
    ErrorFB.addText(builder, text);
    ErrorFB.addNodeId(builder, nodeId);
    ErrorFB.addErrorCode(builder, errorCode);
    return ErrorFB.endErrorFB(builder);
  }

  public static void startErrorFB(FlatBufferBuilder builder) { builder.startObject(3); }
  public static void addErrorCode(FlatBufferBuilder builder, byte errorCode) { builder.addByte(0, errorCode, 0); }
  public static void addNodeId(FlatBufferBuilder builder, short nodeId) { builder.addShort(1, nodeId, 0); }
  public static void addText(FlatBufferBuilder builder, int textOffset) { builder.addOffset(2, textOffset, 0); }
  public static int endErrorFB(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
  public static void finishErrorFBBuffer(FlatBufferBuilder builder, int offset) { builder.finish(offset); }
};

