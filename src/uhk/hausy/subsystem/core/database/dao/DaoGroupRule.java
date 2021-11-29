package uhk.hausy.subsystem.core.database.dao;

import uhk.hausy.subsystem.core.model.GroupRule;
import uhk.hausy.subsystem.core.model.Node;

import java.util.List;
import java.util.Map;

/**
 * Created by tobou on 05.11.2016.
 */
public interface DaoGroupRule extends Dao<GroupRule, Long> {

    public List<GroupRule> list(Map<Short, Node> nodes) throws Exception;

}
