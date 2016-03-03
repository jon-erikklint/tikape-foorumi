package tikape.tikape.foorumi.database;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import tikape.tikape.foorumi.domain.Avaus;

public class AvausDao extends AbstraktiDao<Avaus,Integer> {

    public AvausDao(Database db, String taulu) {
        super(db, taulu);
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
