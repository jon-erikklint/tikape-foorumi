package tikape.tikape.foorumi.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import tikape.tikape.foorumi.domain.Viesti;

public class ViestiDao extends AbstraktiDao<Viesti, Integer> {

    public ViestiDao(Database db) {
        super(db, "Viesti");
    }

    @Override
    public Viesti createT(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String sisalto = rs.getString("sisalto");
        String kayttaja = rs.getString("kayttaja");

        Viesti v = new Viesti(id, sisalto, Timestamp.from(Instant.now()), kayttaja);
        return v;
    }

    @Override
    public List<String> values(Viesti t) throws Exception {
        List<String> lista = new ArrayList();

        String id = "" + t.getId();
        String sisalto = t.getSisalto();
        String aika = t.getDate() + "";
        String avaus = t.getAvaus() + "";
        String kayttaja = t.getKayttaja() + "";

        lista.add(id);
        lista.add(sisalto);
        lista.add(aika);
        lista.add(avaus);
        lista.add(kayttaja);

        return lista;

    }

    public List<Viesti> viestejaAvauksessa(int avausId) throws Exception {
        List<String> ehdot = new ArrayList<>();
        List<Object> arvot = new ArrayList<>();

        ehdot.add("Viesti.avaus=?");
        arvot.add(avausId);

        return super.findByCondition(ehdot, arvot);
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
