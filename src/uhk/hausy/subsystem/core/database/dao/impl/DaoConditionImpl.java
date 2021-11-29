package uhk.hausy.subsystem.core.database.dao.impl;

import uhk.hausy.subsystem.core.database.base.DbsManager;
import uhk.hausy.subsystem.core.database.dao.DaoCondition;
import uhk.hausy.subsystem.core.model.Condition;

import java.sql.Connection;
import java.util.List;

/**
 * Created by tobou on 05.11.2016.
 */
public class DaoConditionImpl implements DaoCondition {

    private Connection connection;

    @Override
    public void createOrUpdate(Condition entity) throws Exception {

    }

    @Override
    public void persist(Condition entity) throws Exception {

    }

    @Override
    public void remove(Condition entity) throws Exception {

    }

    @Override
    public Condition findById(Long id) throws Exception {
        return null;
    }

    @Override
    public List<Condition> list() throws Exception {
        return null;
    }

    @Override
    public void persistMultiple(List<Condition> conditions) throws Exception {

    }
}
