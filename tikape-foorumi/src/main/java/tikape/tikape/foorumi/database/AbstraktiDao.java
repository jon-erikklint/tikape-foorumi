package tikape.tikape.foorumi.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstraktiDao<T,K> implements Dao<T,K>{

    Database db;
    private String taulu;

    public AbstraktiDao(Database db, String taulu) {
        this.db = db;
        this.taulu = taulu;
    }
    
    public abstract T createT(ResultSet rs) throws Exception;
    
    public abstract List<String> values(T t) throws Exception;

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
    public List<T> findByCondition(List<String> conds, List<Object> values) throws Exception{
        Connection c = db.getConnection();
        
        String query = "SELECT * FROM "+taulu+" WHERE ";
        for(int i = 0 ; i < conds.size() ; i++){
            query += conds.get(i);
            if(i < conds.size()-1){
                query+=" AND ";
            }
        }
        
        PreparedStatement ps = c.prepareStatement(query);
        for(int i = 0 ; i < values.size() ; i++){
            ps.setObject(i+1, values.get(i));
        }
        
        ResultSet rs = ps.executeQuery();
        
        List<T> list = new ArrayList<T>();
        while(rs.next()){
            list.add(createT(rs));
        }
        
        return list;
        
    }

    @Override
    public void add(T t) throws Exception {
        Connection c = db.getConnection();
        
        List<String> parametres = values(t);
        String sarakkeet = "";
        
        for(int i = 0 ; i < parametres.size() ; i++){
            
            sarakkeet+=parametres.get(i);
            if(i<parametres.size()-1){
                sarakkeet+=", ";
            }
            
        }
        
        PreparedStatement ps = c.prepareStatement("INSERT INTO "+taulu+" VALUES ("+sarakkeet+")");
        
        ps.executeUpdate();
        
        ps.close();
        c.close();
    }

    @Override
    public void delete(K k) throws Exception {
        Connection c = db.getConnection();
        PreparedStatement ps = c.prepareStatement("DELETE FROM "+taulu+" WHERE id=?");
        ps.setObject(1, k);
        
        ps.executeUpdate();
        
        ps.close();
        c.close();
    }
    
    
    
}
