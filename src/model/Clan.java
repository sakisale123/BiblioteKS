package model;

import java.util.ArrayList;
import java.util.List;

public class Clan {
    private static int generatorId = 1;

    private int id;
    private String ime;
    private String prezime;

    private List<Knjiga> zaduzeneKnjige;


    public Clan(String ime, String prezime) {
        this.id = generatorId;
        this.ime = ime;
        this.prezime = prezime;

        this.zaduzeneKnjige = new ArrayList<>();

        generatorId ++;
    }

    public Clan() {}

    public int getId() {
        return id;
    }

    public String getPrezime() {
        return prezime;
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
