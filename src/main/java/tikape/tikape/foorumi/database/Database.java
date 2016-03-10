package tikape.tikape.foorumi.database;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class Database {
    
    private String osoite;

    public Database(String osoite) {
        this.osoite = osoite;
        
        init();
    }
    
    private void init(){
        List<String> alustus = new ArrayList<>();
        alustus.add("CREATE TABLE Alue (id integer PRIMARY KEY NOT NULL,nimi varchar(40) NOT NULL);");
        alustus.add("CREATE TABLE Avaus (id integer PRIMARY KEY NOT NULL,otsikko varchar(60) NOT NULL,alue integer NOT NULL,FOREIGN KEY (alue) REFERENCES Alue(id));");
        alustus.add("CREATE TABLE Viesti (id integer PRIMARY KEY NOT NULL,sisalto varchar(1000) NOT NULL,aika timestamp NOT NULL,kayttaja varchar(40) NOT NULL,avaus integer NOT NULL,FOREIGN KEY (avaus) REFERENCES Avaus(id));");
        
        Connection c;
        try{
            c = getConnection();
        }catch(Exception e){
            System.out.println("en toimi!");
            return;
        }
        
        try{
            PreparedStatement psa = c.prepareStatement("SELECT COUNT(*) a FROM Viesti");
            ResultSet rs = psa.executeQuery();
            rs.next();
            if(rs.getInt("a")>0){
               return; 
            }
            
        }catch(Exception e){}  
        
        try{
            for(String kasky : alustus){
                PreparedStatement ps = c.prepareStatement(kasky);
                ps.executeUpdate();
                ps.close();
            }
        }catch(Exception e){
            System.out.println("en toimi nytkään!");
        }
        
        
    }
    
    public Connection getConnection() throws Exception{
        if (this.osoite.contains("postgres")) {
            try {
                URI dbUri = new URI(osoite);

                String username = dbUri.getUserInfo().split(":")[0];
                String password = dbUri.getUserInfo().split(":")[1];
                String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

                return DriverManager.getConnection(dbUrl, username, password);
            } catch (Throwable t) {
                System.out.println("Error: " + t.getMessage());
                t.printStackTrace();
            }
        }

        return DriverManager.getConnection(osoite);
    }
}
