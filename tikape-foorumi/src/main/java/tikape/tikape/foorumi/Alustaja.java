package tikape.tikape.foorumi;

import tikape.tikape.foorumi.database.Database;
import spark.Spark.*;

public class Alustaja {
    
    
    
    public void alustaSql(){
        String osoite = "jdbc:sqlite:foorumi.db";
        
        Database db = new Database(osoite);
        
        alustaDaot(db);
    }
    
    private void alustaDaot(Database db){
        
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
