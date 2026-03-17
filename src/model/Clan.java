package model;

import java.util.ArrayList;
import java.util.List;

public class Clan {

    private int id;
    private String ime;
    private String prezime;
    private String brojKarte;

    private List<Knjiga> zaduzeneKnjige;


    public Clan(String ime, String prezime, String brojKarte) {

        this.ime = ime;
        this.prezime = prezime;
        this.brojKarte = brojKarte;

        this.zaduzeneKnjige = new ArrayList<>();

    }

    public Clan() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getBrojKarte() {
        return brojKarte;
    }

    public void setBrojKarte(String brojKarte) {
        this.brojKarte = brojKarte;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void zaduziKnjigu(Knjiga k){
        zaduzeneKnjige.add(k);
    }

    public void ukloniKnjigu(Knjiga k){
        zaduzeneKnjige.remove(k);
    }

    public List<Knjiga> getZaduzeneKnjige() {
        return zaduzeneKnjige;
    }

    public void setZaduzeneKnjige(List<Knjiga> zaduzeneKnjige) {
        this.zaduzeneKnjige = zaduzeneKnjige;
    }
}
