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
}
