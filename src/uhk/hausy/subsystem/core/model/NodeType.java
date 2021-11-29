package uhk.hausy.subsystem.core.model;

/**
 * Created by tobou on 14.10.2016.
 */
public class NodeType {

    byte id;
    byte dataRange;
    byte dataCount;
    byte latency;
    byte accessType;
    byte minDelay;

    public static String getTableSql() {
        String sql = "CREATE TABLE IF NOT EXISTS NodeType " +
                "(ID INTEGER PRIMARY KEY                NOT NULL," +
                " DATA_RANGE                 BYTE       NOT NULL," +
                " DATA_COUNT                 BYTE       NOT NULL," +
                " LATENCY                    BYTE       NOT NULL," +
                " ACCESS_TYPE                BYTE       NOT NULL," +
                " MIN_DELAY                  BYTE       NOT NULL);";
        return sql;
    }

    public byte getId() {
        return id;
    }

    public void setId(byte id) {
        this.id = id;
    }

    public byte getDataRange() {
        return dataRange;
    }

    public void setDataRange(byte dataRange) {
        this.dataRange = dataRange;
    }

    public byte getDataCount() {
        return dataCount;
    }

    public void setDataCount(byte dataCount) {
        this.dataCount = dataCount;
    }

    public byte getLatency() {
        return latency;
    }

    public void setLatency(byte latency) {
        this.latency = latency;
    }

    public byte getAccessType() {
        return accessType;
    }

    public void setAccessType(byte accessType) {
        this.accessType = accessType;
    }

    public byte getMinDelay() {
        return minDelay;
    }

    public void setMinDelay(byte minDelay) {
        this.minDelay = minDelay;
    }
}
