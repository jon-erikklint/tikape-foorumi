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
import tikape.tikape.foorumi.domain.Alue;

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
        aihesivu();
        aluesivu();
        viestisivu();
    }

    private void aihesivu() {

    }

    private void aluesivu() throws SQLException, ClassNotFoundException {
        
        get("/", (req, res) -> {
            Map map = new HashMap<>();
        
            List<Alue> lista = alueDao.findAll();
            
            map.put("alueet", lista);

            return new ModelAndView(map, "alueet");
        },
                new ThymeleafTemplateEngine());
    }

    private void viestisivu() {
        

    }

}
