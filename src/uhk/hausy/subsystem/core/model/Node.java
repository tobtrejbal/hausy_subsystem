package uhk.hausy.subsystem.core.model;

import java.util.List;

/**
 * Created by tobou on 14.10.2016.
 */
public class Node {

    short id;
    byte channelCount;
    String macAddress;
    NodeType nodeType;
    List<Channel> channels;

    boolean rulesApplied;
    short random;

    public Node(NodeType nodeType) {
        this.nodeType = nodeType;
    }

    public Node() {
    }

    public static String getTableSql() {
        String sql = "CREATE TABLE IF NOT EXISTS Node " +
                "(ID          TINYINT    PRIMARY KEY    NOT NULL," +
                " TYPE_ID                   BYTE        NOT NULL," +
                " CHANNEL_COUNT             BYTE        NOT NULL," +
                " MAC_ADDRESS               CHAR(20)    );";
        return sql;
    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public byte getChannelCount() {
        return channelCount;
    }

    public void setChannelCount(byte channelCount) {
        this.channelCount = channelCount;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public short getRandom() {
        return random;
    }

    public void setRandom(short random) {
        this.random = random;
    }

    public NodeType getNodeType() {
        return nodeType;
    }

    public void setNodeType(NodeType nodeType) {
        this.nodeType = nodeType;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", channelCount=" + channelCount +
                ", macAddress=" + macAddress +
                ", random='" + random + '\'' +
                ", nodeType=" + nodeType.getId() +
                ", nodeTypeAccess=" + nodeType.getAccessType() +
                ", nodeTypeLatency=" + nodeType.getLatency() +
                ", nodeTypeDataRange=" + nodeType.getDataRange() +
                ", nodeTypeDataCount=" + nodeType.getDataCount() +
                ", nodeTypeMinDelay=" + nodeType.getMinDelay() +
                ", Channels["+ channels.size() +"]=" + channels.toString() +
                "}\n";
    }

    public boolean isRulesApplied() {
        return rulesApplied;
    }

    public void setRulesApplied(boolean rulesApplied) {
        this.rulesApplied = rulesApplied;
    }
}
