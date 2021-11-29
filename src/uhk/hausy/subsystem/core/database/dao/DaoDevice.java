package uhk.hausy.subsystem.core.database.dao;

import uhk.hausy.subsystem.core.model.Device;
import uhk.hausy.subsystem.core.model.Node;

import java.util.List;
import java.util.Map;

/**
 * Created by tobou on 07.11.2016.
 */
public interface DaoDevice extends Dao<Device, Long> {

    public List<Device> list(Map<Short, Node> nodes) throws Exception;

}
