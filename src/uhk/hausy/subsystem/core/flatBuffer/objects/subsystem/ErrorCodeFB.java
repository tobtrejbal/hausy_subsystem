// automatically generated, do not modify

package uhk.hausy.subsystem.core.flatBuffer.objects.subsystem;

public class ErrorCodeFB {
  public static final byte ERROR_UART = 0;
  public static final byte ERROR_COMMUNICATION = 1;
  public static final byte ERROR_NODE_NOT_RESPONDING = 2;
  public static final byte ERROR_PACKET_NONSENSE = 3;
  public static final byte ERROR_NO_NEW_NODES = 4;
  public static final byte ERROR_FAILED_TO_ADDRESS_NODE = 5;
  public static final byte ERROR_JSON_NOT_VALID = 6;

  private static final String[] names = { "ERROR_UART", "ERROR_COMMUNICATION", "ERROR_NODE_NOT_RESPONDING", "ERROR_PACKET_NONSENSE", "ERROR_NO_NEW_NODES", "ERROR_FAILED_TO_ADDRESS_NODE", "ERROR_JSON_NOT_VALID", };

  public static String name(int e) { return names[e]; }
};

