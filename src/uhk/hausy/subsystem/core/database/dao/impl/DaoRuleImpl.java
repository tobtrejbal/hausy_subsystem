package uhk.hausy.subsystem.core.database.dao.impl;

import uhk.hausy.subsystem.core.database.base.DbsManager;
import uhk.hausy.subsystem.core.database.dao.DaoNode;
import uhk.hausy.subsystem.core.database.dao.DaoRule;
import uhk.hausy.subsystem.core.model.Rule;

import java.sql.Connection;
import java.util.List;

/**
 * Created by tobou on 05.11.2016.
 */
public class DaoRuleImpl implements DaoRule {

    private Connection connection;

    @Override
    public void createOrUpdate(Rule entity) throws Exception {

    }

    @Override
    public void persist(Rule entity) throws Exception {

    }

    @Override
    public void remove(Rule entity) throws Exception {

    }

    @Override
    public Rule findById(Long id) throws Exception {
        return null;
    }

    @Override
    public List<Rule> list() throws Exception {
        return null;
    }

    @Override
    public void persistMultiple(List<Rule> daoNodes) throws Exception {

    }
}
