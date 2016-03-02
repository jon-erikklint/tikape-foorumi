

package tikape.tikape.foorumi.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import tikape.tikape.foorumi.domain.Kayttaja;
import tikape.tikape.foorumi.domain.Viesti;


public class ViestiDao extends AbstraktiDao{
    private Map<String, Class<?>> Kayttaja;

    public ViestiDao(Database db) {
        super(db, "Viesti");
    }

    

    @Override
    public Object createT(ResultSet rs) {
        Viesti v = null;
        try {
            int id = rs.getInt("id");
            String sisalto = rs.getString("sisalto");
            v = new Viesti(id, sisalto);
            
        } catch (SQLException ex) {
            Logger.getLogger(ViestiDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return v;
    }
    
}
