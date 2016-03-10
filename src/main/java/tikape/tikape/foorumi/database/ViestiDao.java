package tikape.tikape.foorumi.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;
import tikape.tikape.foorumi.domain.Viesti;

public class ViestiDao extends AbstraktiDao<Viesti, Integer> {

    public ViestiDao(Database db) {
        super(db, "Viesti");
    }

    @Override
    public Viesti createT(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String sisalto = rs.getString("sisalto");
        Timestamp aika = rs.getTimestamp("aika");
        String kayttaja = rs.getString("kayttaja");
        int avausId = rs.getInt("avaus");
        

        Viesti v = new Viesti(id, sisalto, aika, kayttaja, avausId);
        return v;
    }

    @Override
    public List<String> values(Viesti t) throws Exception {
        List<String> lista = new ArrayList();

        String id = "" + t.getId();
        String sisalto = t.getSisalto();
        String aika = t.getDate() + "";
        String kayttaja = t.getKayttaja();
        String avausId = t.getAvausId()+"";

        lista.add(id);
        lista.add(sisalto);
        lista.add(aika);
        lista.add(kayttaja);
        lista.add(avausId);

        return lista;

    }

    public List<Viesti> viestejaAvauksessa(int avausId, int sivu) throws Exception {
        List<String> ehdot = new ArrayList<>();
        List<Object> arvot = new ArrayList<>();

        ehdot.add("Viesti.avaus=?");
        arvot.add(avausId);
        
        int limit = 10;

        return super.findByCondition(ehdot, arvot, limit, limit*(sivu-1), "aika", false);
    }
    
    public int viestejaAvauksessa(int avausId) throws Exception{
        List<String> ehdot = new ArrayList<>();
        List<Object> arvot = new ArrayList<>();

        ehdot.add("Viesti.avaus=?");
        arvot.add(avausId);
        
        return super.findByCondition(ehdot, arvot, 999999, 0, "aika", true).size();
    }

    public void viestiSisalto(List<Viesti> lista) throws Exception {
        Connection c = db.getConnection();

        for (Viesti v : lista) {
            PreparedStatement s = c.prepareStatement("SELECT v.sisalto as sisalto, v.kayttaja as nimi FROM Viesti v WHERE v.id =?");
            s.setObject(1, v.getId());
            ResultSet rs = s.executeQuery();
            
            rs.next();

            v.setSisalto(rs.getString("sisalto"));
            v.setKayttaja(rs.getString("nimi"));

            s.close();
            rs.close();

        }
        c.close();
    }
}
