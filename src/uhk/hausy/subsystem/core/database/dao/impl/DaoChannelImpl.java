package uhk.hausy.subsystem.core.database.dao.impl;

import uhk.hausy.subsystem.core.database.base.DbsManager;
import uhk.hausy.subsystem.core.database.dao.DaoChannel;
import uhk.hausy.subsystem.core.model.Channel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tobou on 03.11.2016.
 */
public class DaoChannelImpl implements DaoChannel {

    private Connection connection;
    private DbsManager dbsManager = DbsManager.getInstance();

    @Override
    public void createOrUpdate(Channel entity) throws Exception {

    }

    @Override
    public void persist(Channel entity) throws Exception {

    }

    @Override
    public void remove(Channel entity) throws Exception {

    }

    @Override
    public Channel findById(Long id) throws Exception {
        return null;
    }

    @Override
    public List<Channel> list() throws Exception {
        return null;
    }

    @Override
    public void persistMultiple(List<Channel> channels) throws Exception {

    }
}
