package tikape.tikape.foorumi.database;

import java.util.List;

public interface Dao<T> {
    
    public T findOne(int k) throws Exception;
    
    public List<T> findAll() throws Exception;
    
    public List<T> findByCondition(List<String> cond, List<Object> val, int limit, int offset, String orderBy, boolean desc) throws Exception;
    
    public int getHighestId() throws Exception;
    
    public void add(T t) throws Exception;
    
    public void delete(int k) throws Exception;
    
}
