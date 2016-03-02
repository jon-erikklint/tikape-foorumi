package tikape.tikape.foorumi;

import static spark.Spark.*;
import tikape.tikape.foorumi.database.Database;

public class Main {
    
    public static void main(String[] args) throws Exception{
        String osoite = "jdbc:sqlite:foorumi.db";
        
        Database db = new Database(osoite);
        
        
    }
    
}
