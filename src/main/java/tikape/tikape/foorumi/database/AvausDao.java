package tikape.tikape.foorumi.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import tikape.tikape.foorumi.domain.Avaus;

public class AvausDao extends AbstraktiDao<Avaus> {

    public AvausDao(Database db) {
        super(db, "Avaus");
    }
    
    public List<Avaus> avauksetAlueella(int alueId, int sivu)throws Exception{
        List<String> ehdot = new ArrayList<>();
        List<Object> arvot = new ArrayList<>();
        
        ehdot.add("Avaus.alue=?");
        arvot.add(alueId);
        
        int limit = 10;
        
        return super.findByCondition(ehdot, arvot, limit, limit*(sivu-1), "otsikko", false);
    }
    
    public void viestejaAvauksessa(List<Avaus> avaukset) throws Exception{
        Connection c = db.getConnection();
        
        for(Avaus avaus : avaukset){
            PreparedStatement ps = c.prepareStatement("SELECT COUNT(*) viesteja FROM Avaus a, Viesti v WHERE a.id = v.avaus AND a.id=?;");
            
            
            ps.setInt(1, avaus.getId());

            ResultSet rs = ps.executeQuery();
            rs.next();

            avaus.setViesteja(rs.getInt("viesteja"));
        
            ps.close();
            rs.close();
        }
        
        c.close();
    }
    
    public void uusinViestiAvauksessa(List<Avaus> avaukset) throws Exception{
        Connection c = db.getConnection();
        
        for(Avaus avaus: avaukset){
            PreparedStatement ps = c.prepareStatement("SELECT * FROM Avaus a, Viesti v WHERE a.id = v.avaus AND a.id=? ORDER BY v.aika DESC LIMIT 1;");
        
            ps.setInt(1, avaus.getId());

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                avaus.setUusinviesti(rs.getTimestamp("aika"));
            }

            rs.close();
            ps.close();
        }
        
        c.close();
    }
    
    public int avauksiaAlueessa(int avausId) throws Exception{
        List<String> ehdot = new ArrayList<>();
        List<Object> arvot = new ArrayList<>();

        ehdot.add("Avaus.alue=?");
        arvot.add(avausId);
        
        return super.findByCondition(ehdot, arvot, 999999, 0, "otsikko", true).size();
    }

    @Override
    public Avaus createT(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String otsikko = rs.getString("otsikko");
        int avausId = rs.getInt("alue");
        
        Avaus a = new Avaus(id, otsikko, avausId);
        return a;
    }

    @Override
    public List<Object> values(Avaus t) throws Exception {
        List<Object> lista = new ArrayList();

        int id = t.getId();
        String otsikko = t.getOtsikko();
        int alue = t.getAlueId();

        lista.add(id);
        lista.add(otsikko);
        lista.add(alue);

        return lista;
    }
}
