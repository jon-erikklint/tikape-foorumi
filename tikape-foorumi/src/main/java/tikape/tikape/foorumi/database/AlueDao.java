package tikape.tikape.foorumi.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import tikape.tikape.foorumi.domain.Alue;

public class AlueDao extends AbstraktiDao<Alue, Integer> {

    public AlueDao(Database db) {
        super(db, "Alue");
    }

    public int viestejaYhteensa(Alue a) throws Exception {
        
        int tulos = 0;
        String id = a.getId() + "";

        Connection c = this.db.getConnection();
        PreparedStatement ps = c.prepareStatement("SELECT COUNT(viesti.id) AS id FROM viesti v, avaus a WHERE v.avaus = a.id AND a.alue = ?");

        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            tulos = rs.getInt("id");
        }
        return tulos;
    }

    @Override
    public Alue createT(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String nimi = rs.getNString("nimi");

        Alue a = new Alue(id, nimi);
        return a;
    }

    @Override
    public List<String> values(Alue t) throws Exception {
        List<String> lista = new ArrayList();

        String id = "" + t.getId();
        String nimi = t.getNimi();

        lista.add(id);
        lista.add(nimi);

        return lista;
    }

}
