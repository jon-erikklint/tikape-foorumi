package tikape.tikape.foorumi;

import tikape.tikape.foorumi.database.*;
import spark.Spark.*;

public class Alustaja {
    
    private AlueDao alueDao;
    private AvausDao avausDao;
    private Kayttajadao kayttajaDao;
    private ViestiDao viestiDao;
    
    public void alustaSql(){
        Database db = luoDatabase();
        
        alueDao = new AlueDao(db);
        avausDao = new AvausDao(db);
        kayttajaDao = new Kayttajadao(db);
        viestiDao = new ViestiDao(db);
    }
    
    private Database luoDatabase(){
        String osoite = "jdbc:sqlite:foorumi.db";
        
        return new Database(osoite);
    }
    
    public void alustaKuuntelijat(){
        aihesivu();
        aluesivu();
        viestisivu();
    }
    
    private void aihesivu(){
        
    }
    
    private void aluesivu(){
        
    }
    
    private void viestisivu(){
        
    }
    
}
