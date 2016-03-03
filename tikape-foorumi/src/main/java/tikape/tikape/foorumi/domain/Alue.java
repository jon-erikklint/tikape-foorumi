
package tikape.tikape.foorumi.domain;

import java.sql.Timestamp;


public class Alue {
    private int id;
    private String nimi;
    private Timestamp uusinviesti;
    private int viesteja;
    

    public Alue(int id, String nimi) {
        this.id = id;
        this.nimi = nimi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
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
}
