package uhk.hausy.subsystem.core.database.dao.impl;

import uhk.hausy.subsystem.core.database.base.DbsManager;
import uhk.hausy.subsystem.core.database.dao.DaoNode;
import uhk.hausy.subsystem.core.model.Channel;
import uhk.hausy.subsystem.core.model.Node;
import uhk.hausy.subsystem.core.model.NodeType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by tobou on 12.10.2016.
 */
public class DaoNodeImpl implements DaoNode {

    private Connection connection;

    private DbsManager dbsManager = DbsManager.getInstance();

    @Override
    public void createOrUpdate(Node entity) throws Exception {

    }

    @Override
    public void persist(Node entity) throws Exception {

    }

    @Override
    public void remove(Node entity) throws Exception {

    }

    @Override
    public Node findById(Long id) throws Exception {
        connection = dbsManager.getConnection();
        String sql = "SELECT n.* FROM NODE n WHERE n.id = "+id+";";
        Statement stmt = connection.createStatement();
        stmt.execute(sql);
        ResultSet rs = stmt.getResultSet();
        if(!rs.wasNull()) {
            NodeType nodeType = new NodeType();
            Node node = new Node();
            node.setId(rs.getShort(1));
            node.setChannelCount(rs.getByte(3));
            node.setMacAddress(rs.getString(4));
            nodeType.setId(rs.getByte(5));
            nodeType.setDataRange(rs.getByte(6));
            nodeType.setDataCount(rs.getByte(7));
            nodeType.setLatency(rs.getByte(8));
            nodeType.setAccessType(rs.getByte(9));
            nodeType.setMinDelay(rs.getByte(10));
            node.setNodeType(nodeType);

            String sqlChannel = "SELECT * FROM Channel c WHERE  c.node_id = "+node.getId()+";";
            Statement stmtChannel = connection.createStatement();
            stmtChannel.execute(sqlChannel);
            ResultSet rsNode = stmtChannel.getResultSet();
            List<Channel> channels = new ArrayList<>();
            while(rsNode.next()) {
                Channel channel = new Channel();
                channel.setId(rsNode.getString(1));
                channel.setChannelNumber(rsNode.getByte(2));
                channel.setNode(node);
                channels.add(channel);
            }
            node.setChannels(channels);
            return node;
        } else {
            return null;
        }
    }

    @Override
    public List<Node> list() throws Exception {
        connection = dbsManager.getConnection();
        String sql = "SELECT n.*, t.* FROM NODE n INNER JOIN NODETYPE t ON  n.type_id = t.id;";
        Statement stmt = connection.createStatement();
        stmt.execute(sql);
        ResultSet rs = stmt.getResultSet();
        List<Node> nodes = new ArrayList<>();
        while(rs.next()) {
            NodeType nodeType = new NodeType();
            Node node = new Node();
            node.setId(rs.getShort(1));
            node.setChannelCount(rs.getByte(3));
            node.setMacAddress(rs.getString(4));
            nodeType.setId(rs.getByte(5));
            nodeType.setDataRange(rs.getByte(6));
            nodeType.setDataCount(rs.getByte(7));
            nodeType.setLatency(rs.getByte(8));
            nodeType.setAccessType(rs.getByte(9));
            nodeType.setMinDelay(rs.getByte(10));
            node.setNodeType(nodeType);

            String sqlChannel = "SELECT * FROM Channel c WHERE  c.node_id = "+node.getId()+";";
            Statement stmtChannel = connection.createStatement();
            stmtChannel.execute(sqlChannel);
            ResultSet rsNode = stmtChannel.getResultSet();
            List<Channel> channels = new ArrayList<>();
            while(rsNode.next()) {
                Channel channel = new Channel();
                channel.setId(rsNode.getString(1));
                channel.setChannelNumber(rsNode.getByte(2));
                channel.setNode(node);
                channels.add(channel);
            }
            node.setChannels(channels);
            nodes.add(node);
        }

        //System.out.println("SIZE"+nodes.size());

        return nodes;
    }

    @Override
    public void persistMultiple(List<Node> nodes) throws Exception {
        String sql = "REPLACE INTO node VALUES (?,?,?,?);";
        connection = dbsManager.getConnection();
        PreparedStatement prep = connection.prepareStatement(sql);
        for (Node n : nodes) {
            prep.setShort(1, n.getId());
            prep.setByte(2, n.getNodeType().getId());
            prep.setByte(3, n.getChannelCount());
            prep.setString(4, n.getMacAddress());
            prep.addBatch();
            String sqlChannel = "REPLACE INTO channel VALUES (?,?,?);";
            PreparedStatement prepStateChannel = connection.prepareStatement(sqlChannel);
            for (Channel c : n.getChannels()) {
                prepStateChannel.setString(1, c.getNode().getId()+":"+c.getChannelNumber());
                prepStateChannel.setByte(2, c.getChannelNumber());
                prepStateChannel.setShort(3, c.getNode().getId());
                prepStateChannel.addBatch();
            }
            prepStateChannel.executeBatch();
        }
        int[] updateCounts = prep.executeBatch();
    }

    @Override
    public short generateId() throws Exception {
        Random random = new Random();
        short generatedValue = 1;
        for(int i = 0; i < Short.MAX_VALUE; i++) {
            if(findById((long) generatedValue) != null) {
                return generatedValue;
            }
        }
        return -1;
    }
}
