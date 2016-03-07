package tikape.tikape.foorumi.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import tikape.tikape.foorumi.domain.Avaus;

public class AvausDao extends AbstraktiDao<Avaus,Integer> {

    public AvausDao(Database db) {
        super(db, "Avaus");
    }
    
    public List<Avaus> avauksetAlueella(int alueId)throws Exception{
        List<String> ehdot = new ArrayList<>();
        List<Object> arvot = new ArrayList<>();
        
        ehdot.add("Avaus.alue=?");
        arvot.add(alueId);
        
        return super.findByCondition(ehdot, arvot);
    }
    
    public int viestejaAvauksessa(Avaus avaus) throws Exception{
        Connection c = db.getConnection();
        PreparedStatement ps = c.prepareStatement("SELECT COUNT(*) viesteja FROM Avaus a, Viesti v WHERE a.id = viesti.avaus AND a.id=?;");
        ps.setObject(1, avaus.getId());
        
        ResultSet rs = ps.executeQuery();
        rs.next();
        
        return rs.getInt("viesteja");
    }
    
    public Timestamp uusinViestiAvauksessa(Avaus avaus) throws Exception{
        Connection c = db.getConnection();
        PreparedStatement ps = c.prepareStatement("SELECT * FROM Avaus a, Viesti v WHERE a.id = viesti.avaus AND a.id=? ORDER BY viesti.date DESC LIMIT 1;");
        ps.setObject(1, avaus.getId());
        
        ResultSet rs = ps.executeQuery();
        
        rs.next();
        
        return rs.getTimestamp("date");
    }

    @Override
    public Avaus createT(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String otsikko = rs.getString("otsikko");
        Avaus a = new Avaus(id, otsikko);
        return a;
    }

    @Override
    public List<String> values(Avaus t) throws Exception {
        List<String> lista = new ArrayList();

        String id = "" + t.getId();
        String otsikko = t.getOtsikko();
        String alue = t.getAlue() + "";
        String viesteja = t.getViesteja() + "";
        String uusinViesti = t.getUusinviesti() + "";

        lista.add(id);
        lista.add(otsikko);
        lista.add(alue);
        lista.add(viesteja);
        lista.add(uusinViesti);

        return lista;
    }
}
