
package tikape.tikape.foorumi.domain;

import java.sql.Timestamp;


public class Avaus {
    
    private int id;
    private String otsikko;
    private int alueId;
    private Alue alue;
    private Timestamp uusinviesti;
    private int viesteja;

    public Avaus(int id, String otsikko, int alueId) {
        this.id = id;
        this.otsikko = otsikko;
        this.alueId = alueId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOtsikko() {
        return otsikko;
    }

    public void setOtsikko(String otsikko) {
        this.otsikko = otsikko;
    }

    public Alue getAlue() {
        return alue;
    }

    public void setAlue(Alue alue) {
        this.alue = alue;
    }

    public Timestamp getUusinviesti() {
        return uusinviesti;
    }

    public void setUusinviesti(Timestamp uusinviesti) {
        this.uusinviesti = uusinviesti;
    }

    public int getViesteja() {
        return viesteja;
    }

    public void setViesteja(int viesteja) {
        this.viesteja = viesteja;
    }

    public int getAlueId() {
        return alueId;
    }

    public void setAlueId(int alueId) {
        this.alueId = alueId;
    }
    
    
}
