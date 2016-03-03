package tikape.tikape.foorumi.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tikape.tikape.foorumi.domain.Kayttaja;

public class Kayttajadao extends AbstraktiDao<Kayttaja, Integer>{

    public Kayttajadao(Database db) {
        super(db, "Kayttaja");
    }

    @Override
    public Kayttaja createT(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nimi = rs.getString("nimi");
        Kayttaja k = new Kayttaja(id, nimi);
        return k;
        
    }

    @Override
    public List<String> values(Kayttaja t) {
        
        List<String> lista = new ArrayList();
        
        String id = "" + t.getId();
        String nimi = t.getNimi();
        
        lista.add(id);
        lista.add(nimi);
        
        return lista;
    }
    
    
}
