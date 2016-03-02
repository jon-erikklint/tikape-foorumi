
package tikape.tikape.foorumi.database;

import java.sql.ResultSet;


public class AvausDao extends AbstraktiDao{

    public AvausDao(Database db, String taulu) {
        super(db, taulu);
    }

    @Override
    public Object createT(ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
