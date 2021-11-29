package uhk.hausy.subsystem.core.database.dao;

import java.util.List;

/**
 * Created by tobou on 05.11.2016.
 */
public interface Dao<E,K> {

    void createOrUpdate(E entity) throws Exception;
    void persist(E entity) throws Exception;
    void remove(E entity) throws Exception;
    E findById(K id) throws Exception;
    List<E> list() throws Exception;
    void persistMultiple(List<E> eList) throws Exception;

}
