package uhk.hausy.subsystem.core.model;

/**
 * Created by tobou on 03.11.2016.
 */
public class Channel {

    private String id;

    private byte channelNumber;

    private Node node;

    private Device device;

    private int[] data;

    public static String getTableSql() {
        String sql = "CREATE TABLE IF NOT EXISTS Channel " +
                "(ID                        CHAR(20)    NOT NULL," +
                " CHANNEL_NUMBER            TINYINT     NOT NULL," +
                " NODE_ID                   TINYINT     NOT NULL);";
        return sql;
    }

    public byte getChannelNumber() {
        return channelNumber;
    }

    public void setChannelNumber(byte channelNumber) {
        this.channelNumber = channelNumber;
    }

    public int[] getData() {
        return data;
    }

    public void setData(int[] data) {
        for(int i = 0; i < this.data.length; i++) {
            this.data[i] = data[i];
        }
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        data = new int[node.getNodeType().getDataCount()];
        this.node = node;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    @Override
    public String toString() {
        return "Channel{" + "ID=" + id + '}';
    }

    public String getAddress() {
        return node.getId()+":"+id;
    }
}
