
package tikape.tikape.foorumi.database;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import tikape.tikape.foorumi.domain.Alue;


public class AlueDao extends AbstraktiDao<Alue, Integer>{

    public AlueDao(Database db, String taulu) {
        super(db, taulu);
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
