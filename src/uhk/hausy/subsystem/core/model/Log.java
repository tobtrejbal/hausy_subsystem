package uhk.hausy.subsystem.core.model;

/**
 * Created by admin on 31.01.2017.
 */
public class Log {

    int id;
    byte type;
    long firstTimestamp;
    long lastTimestamp;
    short occurrenceCount;
    String nodeId;

    public static String getTableSql() {
        String sql = "CREATE TABLE IF NOT EXISTS ChannelData " +
                "(ID INTEGER PRIMARY KEY                NOT NULL," +
                " NODEID                      CHAR(20)  NOT NULL," +
                " TYPE                        BYTE      NOT NULL," +
                " FIRSTTIMESTAMP              BIGINT    NOT NULL," +
                " LASTTIMESTAMP               BIGINT    NOT NULL," +
                " OCCURRENCECOUNT             SHORT     NOT NULL);";
        return sql;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public long getFirstTimestamp() {
        return firstTimestamp;
    }

    public void setFirstTimestamp(long firstTimestamp) {
        this.firstTimestamp = firstTimestamp;
    }

    public long getLastTimestamp() {
        return lastTimestamp;
    }

    public void setLastTimestamp(long lastTimestamp) {
        this.lastTimestamp = lastTimestamp;
    }

    public short getOccurrenceCount() {
        return occurrenceCount;
    }

    public void setOccurrenceCount(short occurrenceCount) {
        this.occurrenceCount = occurrenceCount;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }
}
