package tikape.tikape.foorumi.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import tikape.tikape.foorumi.domain.Kayttaja;

public abstract class AbstraktiDao<T,K> implements Dao<T,K>{

    private Database db;
    private String taulu;

    public AbstraktiDao(Database db, String taulu) {
        this.db = db;
        this.taulu = taulu;
    }
    
    public abstract T createT(ResultSet rs);

    @Override
    public T findOne(K k) throws Exception {
        Connection connection = db.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM "+taulu+" WHERE id=?");
        ps.setObject(1, k);
        
        ResultSet rs = ps.executeQuery();
        
        if(!rs.next()){
            return null;
        }
        
        T t = createT(rs);
        
        rs.close();
        ps.close();
        connection.close();
        
        return t;
    }

    @Override
    public List<T> findAll() throws Exception {
        Connection c = db.getConnection();
        PreparedStatement ps = c.prepareStatement("SELECT * FROM "+taulu);
        
        ResultSet rs = ps.executeQuery();
        
        List<T> ts = new ArrayList<>();
        
        while(rs.next()){
            T t = createT(rs);
            ts.add(t);
        }
        
        rs.close();
        ps.close();
        c.close();
        
        return ts;
    }

    @Override
    public void add(T t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(K k) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
