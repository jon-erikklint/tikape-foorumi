package tikape.tikape.foorumi.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import tikape.tikape.foorumi.domain.Alue;

public class AlueDao extends AbstraktiDao<Alue> {

    public AlueDao(Database db) {
        super(db, "Alue");
    }

    public void viestejaYhteensa(List<Alue> a) throws Exception {
        Connection c = this.db.getConnection();
        
        for(Alue al : a){
            int tulos = 0;
            int id = al.getId();
            
            PreparedStatement ps = c.prepareStatement("SELECT COUNT(*) AS id FROM Viesti v, Avaus a WHERE v.avaus = a.id AND a.alue = ?");

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                tulos = rs.getInt("id");
            }
            al.setViesteja(tulos);
            
            ps.close();
            rs.close();
        }
          
        c.close();
    }
    
    public void uusimmatViestit(List<Alue> a) throws Exception {
        Connection c = db.getConnection();
        
        for(Alue al : a){
            
            PreparedStatement ps = c.prepareStatement("SELECT * FROM Avaus a, Viesti v WHERE a.id = v.avaus AND a.alue=? ORDER BY v.aika DESC");
            ps.setInt(1, al.getId());
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                al.setUusinviesti(rs.getTimestamp("aika"));
            }else{
                al.setUusinviesti(null);
            }
            
            rs.close();
            ps.close();
        }
        
        c.close();
    }

    @Override
    public Alue createT(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String nimi = rs.getString("nimi");

        Alue a = new Alue(id, nimi);
        return a;
    }

    @Override
    public List<Object> values(Alue t) throws Exception {
        List<Object> lista = new ArrayList();

        int id =  t.getId();
        String nimi = t.getNimi();

        lista.add(id);
        lista.add(nimi);

        return lista;
    }

}