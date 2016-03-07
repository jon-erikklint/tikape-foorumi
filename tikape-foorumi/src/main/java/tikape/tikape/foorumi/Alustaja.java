package tikape.tikape.foorumi;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.*;
import spark.ModelAndView;
import tikape.tikape.foorumi.database.*;
import spark.Spark.*;
import static spark.Spark.get;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.tikape.foorumi.domain.*;

public class Alustaja {

    private AlueDao alueDao;
    private AvausDao avausDao;
    private Kayttajadao kayttajaDao;
    private ViestiDao viestiDao;

    public void alustaSql() {
        Database db = luoDatabase();

        alueDao = new AlueDao(db);
        avausDao = new AvausDao(db);
        kayttajaDao = new Kayttajadao(db);
        viestiDao = new ViestiDao(db);
    }

    private Database luoDatabase() {
        String osoite = "jdbc:sqlite:foorumi.db";

        return new Database(osoite);
    }

    public void alustaKuuntelijat() throws Exception{
        avaussivu();
        aluesivu();
        viestisivu();
    }

    private void avaussivu() {
        get("/:alue", (req, res) ->{
            String a = req.params(":alue");
            int alue;
            try{
                alue = Integer.parseInt(a);
            }catch(Exception e){
                return new ModelAndView(null, "avaukset");
            }
            
            Map map = new HashMap<>();
            
            List<Avaus> avaukset = avausDao.avauksetAlueella(alue);
            avausDao.uusinViestiAvauksessa(avaukset);
            avausDao.viestejaAvauksessa(avaukset);
            
            map.put("avaukset", avaukset);
            
            Alue al = alueDao.findOne(alue);
            
            map.put("aihealue", al.getNimi());
            
            return new ModelAndView(map, "avaukset");}, 
                new ThymeleafTemplateEngine());
    }

    private void aluesivu() throws SQLException, ClassNotFoundException {
        
        get("/", (req, res) -> {
            Map map = new HashMap<>();
        
            List<Alue> lista = alueDao.findAll();
            alueDao.viestejaYhteensa(lista);
            alueDao.uusimmatViestit(lista);
            
            System.out.println(lista.get(0).getNimi());
            
            map.put("alueet", lista);

            return new ModelAndView(map, "alueet");
        },
                new ThymeleafTemplateEngine());
    }

    private void viestisivu() {
        

    }

}
