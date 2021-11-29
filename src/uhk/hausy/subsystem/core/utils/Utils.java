package uhk.hausy.subsystem.core.utils;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.SQLException;

public class Utils {

    public static Blob intToBlob(int[] values){
        Blob blob = null;
        try {
            blob = new SerialBlob(intToByte(values));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blob;
    }

    public static int[] blobToInt(Blob blob){
        int[] result = null;
        try {
            int blobLength = (int) blob.length();
            result = byteToInt(blob.getBytes(1, blobLength));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static byte[] intToByte(int[]src) {
        int srcLength = src.length;
        byte[] dst = new byte[srcLength*4];
        int offset = 0;
        for (int i = 0; i < srcLength; i++) {
            int x = src[i];
            dst[offset] = (byte) (x >>> 24);
            dst[offset+1] = (byte) (x >>> 16);
            dst[offset+2] = (byte) (x >>> 8);
            dst[offset+3] = (byte) x;
            offset+=4;
        }
        return dst;
    }

    public static int[] byteToInt(byte[] bytes) {
        int result[] = new int[bytes.length / 4];
        int offset = 0;
        for(int i = 0; i < result.length; i++) {
            result[i] = bytes[offset] << 24 | (bytes[offset+1] & 0xFF) << 16 | (bytes[offset+2] & 0xFF) << 8 | (bytes[offset+3] & 0xFF);
            offset += 4;
        }
        return result;
    }
}
