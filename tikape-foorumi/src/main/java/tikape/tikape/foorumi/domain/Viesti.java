/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.tikape.foorumi.domain;

import java.sql.Timestamp;

/**
 *
 * @author tokito
 */
public class Viesti {

    private int id;
    private String sisalto;
    private Timestamp date;
    private Kayttaja kayttaja;
    private Avaus avaus;
    

    public Viesti(int id, String sisalto, Timestamp date) {
        this.id = id;
        this.sisalto = sisalto;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setKayttaja(Kayttaja kayttaja) {
        this.kayttaja = kayttaja;
    }

    public void setAvaus(Avaus avaus) {
        this.avaus = avaus;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSisalto() {
        return sisalto;
    }

    public void setSisalto(String sisalto) {
        this.sisalto = sisalto;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Kayttaja getKayttaja() {
        return kayttaja;
    }

    public Avaus getAvaus() {
        return avaus;
    }
    
    

}
