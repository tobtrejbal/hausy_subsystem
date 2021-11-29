package uhk.hausy.subsystem.core.database.dao.impl;

import uhk.hausy.subsystem.core.database.base.DbsManager;
import uhk.hausy.subsystem.core.database.dao.DaoNodeType;
import uhk.hausy.subsystem.core.model.NodeType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tobou on 14.10.2016.
 */
public class DaoNodeTypeImpl implements DaoNodeType {

    private Connection connection;

    private DbsManager dbsManager = DbsManager.getInstance();

    @Override
    public void createOrUpdate(NodeType entity) throws Exception {

    }

    @Override
    public void persist(NodeType entity) throws Exception {

    }

    @Override
    public void remove(NodeType entity) throws Exception {

    }

    @Override
    public NodeType findById(Long id) throws Exception {
        return null;
    }

    @Override
    public List<NodeType> list() throws Exception {
        connection = dbsManager.getConnection();
        String sql = "Select * from NODETYPE t;";
        Statement stmt = connection.createStatement();
        stmt.execute(sql);
        ResultSet rs = stmt.getResultSet();
        List<NodeType> nodeTypes = new ArrayList<>();
        while(rs.next()) {
            NodeType nodeType = new NodeType();
            nodeType.setId(rs.getByte(1));
            nodeType.setDataRange(rs.getByte(2));
            nodeType.setDataCount(rs.getByte(3));
            nodeType.setLatency(rs.getByte(4));
            nodeType.setAccessType(rs.getByte(5));
            nodeType.setMinDelay(rs.getByte(6));
            nodeTypes.add(nodeType);
        }
        return nodeTypes;
    }

    @Override
    public void persistMultiple(List<NodeType> nodeTypes) throws Exception {
        String sql = "REPLACE INTO NODETYPE VALUES (?,?,?,?,?,?);";
        connection = dbsManager.getConnection();
        PreparedStatement prep = connection.prepareStatement(sql);
        for (NodeType t : nodeTypes) {
            prep.setByte(1, t.getId());
            prep.setByte(2, t.getDataRange());
            System.out.println("aaaa"+t.getDataRange());
            prep.setByte(3, t.getDataCount());
            prep.setByte(4, t.getLatency());
            prep.setByte(5, t.getAccessType());
            prep.setByte(6, t.getMinDelay());
            prep.addBatch();
        }
        int[] updateCounts = prep.executeBatch();
    }
}
