package tikape.tikape.foorumi.database;

import java.util.List;

public interface Dao<T, K> {
    
    public T findOne(K k) throws Exception;
    
    public List<T> findAll() throws Exception;
    
    public void add(T t) throws Exception;
    
    public void delete(K k) throws Exception;
    
}
