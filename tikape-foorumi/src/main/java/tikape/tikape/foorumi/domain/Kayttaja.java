package tikape.tikape.foorumi.domain;

public class Kayttaja {
 
    private int id;
    private int nimi;

    public Kayttaja(int id, int nimi) {
        this.id = id;
        this.nimi = nimi;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNimi() {
        return nimi;
    }

    public void setNimi(int nimi) {
        this.nimi = nimi;
    }
    
    
}
