package uhk.hausy.subsystem.core.database.dao.impl;

import uhk.hausy.subsystem.core.database.base.DbsManager;
import uhk.hausy.subsystem.core.database.dao.DaoDevice;
import uhk.hausy.subsystem.core.model.Channel;
import uhk.hausy.subsystem.core.model.Device;
import uhk.hausy.subsystem.core.model.Node;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by tobou on 07.11.2016.
 */
public class DaoDeviceImpl implements DaoDevice {

    private Connection connection;

    private DbsManager dbsManager = DbsManager.getInstance();

    @Override
    public void createOrUpdate(Device entity) throws Exception {

    }

    @Override
    public void persist(Device entity) throws Exception {

    }

    @Override
    public void remove(Device entity) throws Exception {

    }

    @Override
    public Device findById(Long id) throws Exception {
        return null;
    }

    @Override
    public List<Device> list() throws Exception {
        return null;
    }

    @Override
    public void persistMultiple(List<Device> devices) throws Exception {
        connection = dbsManager.getConnection();
        String sql = "REPLACE INTO device VALUES (?,?,?);";
        PreparedStatement prep = connection.prepareStatement(sql);
        for (Device d : devices) {
            prep.setInt(1, d.getId());
            prep.setString(2, d.getChannel().getId());
            prep.setString(3, d.getName());
            prep.addBatch();
        }
        int[] updateCounts = prep.executeBatch();
    }

    @Override
    public List<Device> list(Map<Short, Node> nodes) throws Exception {
        connection = dbsManager.getConnection();
        String sql = "SELECT * FROM Device d;";
        Statement stmt = connection.createStatement();
        stmt.execute(sql);
        ResultSet rs = stmt.getResultSet();
        List<Device> devices = new ArrayList<>();
        while(rs.next()) {
            Device device = new Device();
            device.setId(rs.getInt(1));
            device.setName(rs.getString(3));
            Iterator iterator = nodes.entrySet().iterator();
            while(iterator.hasNext()) {
                Map.Entry thisEntry = (Map.Entry) iterator.next();
                Node node = (Node) thisEntry.getValue();
                for(Channel channel : node.getChannels()) {
                    if(channel.getId().equals(rs.getString(2))) {
                        device.setChannel(channel);
                        channel.setDevice(device);
                    }
                }
            }
            devices.add(device);
        }

        return devices;
    }

}
