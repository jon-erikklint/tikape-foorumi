package tikape.tikape.foorumi.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import tikape.tikape.foorumi.domain.Kayttaja;

public class Kayttajadao extends AbstraktiDao<Kayttaja, Integer>{

    public Kayttajadao(Database db) {
        super(db, "Kayttaja");
    }

    @Override
    public Kayttaja createT(ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
