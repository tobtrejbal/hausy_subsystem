package uhk.hausy.subsystem.core.database.dao;


import uhk.hausy.subsystem.core.model.Node;

/**
 * Created by tobou on 13.10.2016.
 */
public interface DaoNode extends Dao<Node, Long> {

    short generateId() throws Exception;

}
