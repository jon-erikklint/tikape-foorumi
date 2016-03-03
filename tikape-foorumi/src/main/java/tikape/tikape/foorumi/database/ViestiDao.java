package tikape.tikape.foorumi.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import tikape.tikape.foorumi.domain.Kayttaja;
import tikape.tikape.foorumi.domain.Viesti;

public class ViestiDao extends AbstraktiDao<Viesti, Integer> {

    public ViestiDao(Database db) {
        super(db, "Viesti");
    }

    @Override
    public Viesti createT(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String sisalto = rs.getString("sisalto");

        Viesti v = new Viesti(id, sisalto, Timestamp.from(Instant.now()));
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
}
