package model;

import java.util.ArrayList;
import java.util.List;

public class Biblioteka {
    private List<Knjiga> listaKnjiga;
    private List<Clan> listaClan;

    public Biblioteka() {
        this.listaKnjiga = new ArrayList<>();
        this.listaClan = new ArrayList<>();
    }

    public void dodajKnjigu(String naziv, String autor, String isbn) {
        naziv = naziv.toUpperCase();
        autor = autor.toUpperCase();
        isbn = isbn.toUpperCase();
        Knjiga novaKnjiga = new Knjiga(naziv,autor,isbn);
        listaKnjiga.add(novaKnjiga);
        System.out.println("Dodata knjiga: " + novaKnjiga);
    }

    public void prikaziKnjige(){
        for (Knjiga novaKnjiga : listaKnjiga) {
            System.out.println("ime: " + novaKnjiga.toString());
        }
    }

    public Knjiga pretraziKnjigu(int mesto){
        Knjiga pretrazenaKnjiga = listaKnjiga.get(mesto);
        return pretrazenaKnjiga;

    }

    public void zaduziKnjigu(String trazeniNaslov, int idClana){

        Clan trazeniClan = null;
        for (Clan clan : listaClan) {
            if (clan.getId() == idClana ){
                trazeniClan = clan;
                break;
            }
        }
        if (trazeniClan == null){
            System.out.println("Ne postoji clan sa tim ID");
            return ;
        }

        trazeniNaslov = trazeniNaslov.toUpperCase();
        for (Knjiga knjiga : listaKnjiga) {
            if (knjiga.getNaziv().equals(trazeniNaslov)) {
                if (knjiga.isJeZaduzena()) {
                    System.out.println("Knjiga " + knjiga + " je vec zaduzena.");
                    break;
                }else{
                    knjiga.setJeZaduzena(true);
                    trazeniClan.zaduziKnjigu(knjiga);
                    System.out.println("Knjiga koju zelis: " + knjiga.getNaziv());
                }
            }

        }

    }

    public void vratiKnjigu(String naslov, int idClana){
        Knjiga vratiKnjiga = null;
        naslov = naslov.toUpperCase();
        for (Knjiga knjiga : listaKnjiga) {
            if (knjiga.getNaziv().equals(naslov)) {
                if (knjiga.isJeZaduzena()) {
                    knjiga.setJeZaduzena(false);
                    vratiKnjiga = knjiga;
                    System.out.println("Knjiga: " + vratiKnjiga + " je vracena.");

                }
            }
            else {
                System.out.println(naslov + " nije zaduzena.");
                return;
            }
        }
        for (Clan c : listaClan){
            if(c.getId() == idClana){
                c.ukloniKnjigu(vratiKnjiga);
            }
            else {
                System.out.println(c.getId() + " nije vratio knjigu.");
            }
        }

    }

    public void registrujClana(String ime, String prezime){
        ime =  ime.toUpperCase();
        prezime =  prezime.toUpperCase();

        Clan clan = new Clan(ime,prezime);
        System.out.println("ID CLANA JE" + clan.getId());

        listaClan.add(clan);
    }
    public void prikaziClanove(){
        for (Clan clan : listaClan) {
            System.out.println(clan.getIme() + " " + clan.getPrezime() + " "+ clan.getId());
        }
    }

    public void prikaziZaduzeneKnjige(int id){
        for (Clan c :  listaClan) {
            if (c.getId() == id){
                System.out.println("Knjige koje poseduje: ");
                for (Knjiga knjiga : c.getZaduzeneKnjige()){
                    System.out.println(knjiga.getNaziv());
                }

            }
        }
    }

}
