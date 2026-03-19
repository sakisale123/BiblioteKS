package repository;

import model.Clan;
import model.Knjiga;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClanRepository {

    private static final String URL = "jdbc:mysql://localhost:3306/biblioteka";
    private static final String USER = "root";
    private static final String PASSWORD = "sakisale123";


    public boolean dodajClanaUBazu (Clan c){
        String sqlUpit = "INSERT INTO clanovi (ime,prezime,broj_karte) VALUES (?, ?, ?)";

        try(Connection konekcija = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement vozilo = konekcija.prepareStatement(sqlUpit)){

            vozilo.setString(1, c.getIme());
            vozilo.setString(2, c.getPrezime());
            vozilo.setString(3, c.getBrojKarte());

            int brojDodatihRedova = vozilo.executeUpdate();

            return brojDodatihRedova > 0;

        }catch (SQLException e){
            return false;
        }
    }

    public List<Knjiga> prikaziKnjigeClana(Clan c) {
        String sqlUpit = "SELECT * FROM knjige WHERE zaduzena_clan_id = ?";

        List<Knjiga> k = new ArrayList<>();

        try (Connection konekcija = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement oklopnoVozilo = konekcija.prepareStatement(sqlUpit)) {

            oklopnoVozilo.setInt(1, c.getId());

            ResultSet rezultat = oklopnoVozilo.executeQuery();


            while (rezultat.next()) {
                int id = rezultat.getInt("id");
                String naslov = rezultat.getString("naziv");
                String autor = rezultat.getString("autor");
                String isbn = rezultat.getString("isbn");

                Knjiga k1 = new Knjiga(naslov, autor, isbn);
                k1.setId(id);

                k.add(k1);

            }

            return k;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return k;
        }
    }

    public List<Clan> prikaziClanove(){
        String sqlUpit = "SELECT * FROM clanovi";
        List<Clan> k = new ArrayList<>();

        try (Connection konekcija = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement oklopnoVozilo = konekcija.prepareStatement(sqlUpit)) {

            ResultSet rezultat = oklopnoVozilo.executeQuery();

            while (rezultat.next()) {
                int id = rezultat.getInt("id");
                String ime = rezultat.getString("ime");
                String prezime = rezultat.getString("prezime");
                String brojKarte = rezultat.getString("broj_karte");

                Clan k1 = new Clan(ime, prezime, brojKarte);
                k1.setId(id);
                k.add(k1);
            }
            return k;

        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }



    public Clan pronadjiClanapoId (int id){
        String sqlUpit = "SELECT * FROM clanovi WHERE id = ?";
        Clan k = null;

        try(Connection konecija = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement vozilo = konecija.prepareStatement(sqlUpit)){

            vozilo.setInt(1, id);
            ResultSet rezultat = vozilo.executeQuery();

            if (rezultat.next()) {
                int id1 = rezultat.getInt("id");
                String ime = rezultat.getString("ime");
                String prezime = rezultat.getString("prezime");
                String brojKarte = rezultat.getString("broj_karte");

                Clan k1 = new Clan(ime, prezime, brojKarte);
                k1.setId(id1);
                k = k1;

            }
            return k;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return k;
        }
    }
}
