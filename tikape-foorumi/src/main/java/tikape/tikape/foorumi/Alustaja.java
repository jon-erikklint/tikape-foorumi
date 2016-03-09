package tikape.tikape.foorumi;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.*;
import spark.ModelAndView;
import tikape.tikape.foorumi.database.*;
import spark.Spark.*;
import static spark.Spark.get;
import static spark.Spark.post;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.tikape.foorumi.domain.*;

public class Alustaja {

    private AlueDao alueDao;
    private AvausDao avausDao;
    private ViestiDao viestiDao;

    public void alustaSql() {
        Database db = luoDatabase();

        alueDao = new AlueDao(db);
        avausDao = new AvausDao(db);
        viestiDao = new ViestiDao(db);
    }

    private Database luoDatabase() {
        String osoite = "jdbc:sqlite:foorumi.db";

        return new Database(osoite);
    }

    public void alustaKuuntelijat() throws Exception {
        avaussivu();
        aluesivu();
        viestisivu();
    }

    private void avaussivu() {
        get("/:alue", (req, res) -> {
            String a = req.params(":alue");
            int alue;
            try {
                alue = Integer.parseInt(a);
            } catch (Exception e) {
                return new ModelAndView(null, "avaukset");
            }

            Map map = new HashMap<>();

            List<Avaus> avaukset = avausDao.avauksetAlueella(alue);
            avausDao.uusinViestiAvauksessa(avaukset);
            avausDao.viestejaAvauksessa(avaukset);

            map.put("avaukset", avaukset);

            Alue al = alueDao.findOne(alue);

            map.put("aihealue", al.getNimi());
            map.put("aiheid", alue);

            return new ModelAndView(map, "avaukset");
        },
                new ThymeleafTemplateEngine());
        
        post("/:alue", (req, res) -> {
            String a = req.params(":alue");
            int alue;
            try {
                alue = Integer.parseInt(a);
            } catch (Exception e) {
                res.redirect("/"+a);
                return null;
            }
            
            String avauksenOtsikko = req.queryParams("otsikko");
            String viestinSisalto = req.queryParams("viestinSisalto");
            String kayttaja = req.queryParams("kayttaja");
            int avausId = avausDao.getHighestId()+1;
            int viestiId = viestiDao.getHighestId()+1;
            
            Avaus avaus = new Avaus(avausId, avauksenOtsikko, alue);
            Viesti alkuviesti = new Viesti(viestiId, viestinSisalto, Timestamp.from(Instant.now()), kayttaja, avausId);
            
            System.out.println("£££££££££££££££££££££££££££££££££££££££££££££££");
            
            avausDao.add(avaus);
            viestiDao.add(alkuviesti);
            
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            
            res.redirect("/"+alue+"/"+avausId);
            
            return null;});
    }
    

    private void aluesivu() throws SQLException, ClassNotFoundException {

        get("/", (req, res) -> {
            Map map = new HashMap<>();

            List<Alue> lista = alueDao.findAll();
            alueDao.viestejaYhteensa(lista);
            alueDao.uusimmatViestit(lista);

            map.put("alueet", lista);

            return new ModelAndView(map, "alueet");
        },
                new ThymeleafTemplateEngine());
        
        post("/", (req, res) ->{
            
            String uudenAlueenNimi = req.queryParams("nimi");
            int id = alueDao.getHighestId()+1;
            
            Alue uusiAlue = new Alue(id, uudenAlueenNimi);
            
            alueDao.add(uusiAlue);
            
            res.redirect("/"+id);
            
            return null;
        });
    }

    private void viestisivu() {
        get("/:avaus/:viesti", (req, res) -> {
            String a = req.params(":avaus");
            String b = req.params(":viesti");

            int avaus;
            int viesti;

            try {
            avaus = Integer.parseInt(a);
            viesti = Integer.parseInt(b);
            } catch (Exception e) {
                return new ModelAndView(null, "avaus");
            }
            
            Map map = new HashMap();

            List<Viesti> lista = viestiDao.viestejaAvauksessa(viesti);
            viestiDao.viestiSisalto(lista);

            map.put("viestit", lista);

            Avaus av = avausDao.findOne(viesti);

            map.put("otsikko", av.getOtsikko());

            return new ModelAndView(map, "avaus");

        },
                new ThymeleafTemplateEngine());

    }

}
