
package tikape.tikape.foorumi.domain;


public class Avaus {
    
    private int id;
    private String otsikko;
    private Alue alue;

    public Avaus(int id, String otsikko, Alue alue) {
        this.id = id;
        this.otsikko = otsikko;
        this.alue = alue;
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
    
    
}
