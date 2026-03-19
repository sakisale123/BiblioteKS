package repository;

import model.Clan;
import model.Knjiga;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KnjigaRepository {

    private static final String URL = "jdbc:mysql://localhost:3306/biblioteka";
    private static final String USER = "root";
    private static final String PASSWORD = "sakisale123";

    public List<Knjiga> dobaviSveKnjige() throws SQLException {
        List<Knjiga> lista = new ArrayList<>();
        String sqlUpit = "SELECT * FROM knjige";

        try (Connection konekcija = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement vozilo = konekcija.createStatement();
             ResultSet rezultat = vozilo.executeQuery(sqlUpit)) {

            while (rezultat.next()) {
                int id = rezultat.getInt("id");
                String naslov = rezultat.getString("naziv");
                String autor = rezultat.getString("autor");
                String isbn = rezultat.getString("isbn");

                Knjiga k =  new Knjiga(naslov, autor, isbn);

                k.setId(id);

                lista.add(k);

            }
        } catch (SQLException e) {
            System.out.println("Greska prilikom povezovanja: " + e.getMessage());
        }
        return lista;
    }

    public boolean dodajKnjigu(Knjiga k){
        String sqlUpit = "INSERT INTO knjige (naziv, autor, isbn) VALUES (?, ?, ?)";

        try(Connection konekcija = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement vozilo = konekcija.prepareStatement(sqlUpit);) {
            vozilo.setString(1, k.getNaziv());
            vozilo.setString(2, k.getAutor());
            vozilo.setString(3, k.getIsbn());

            int brojDodatihRedova = vozilo.executeUpdate();

            return brojDodatihRedova > 0;

        } catch (SQLException e) {
            return false;
        }


    }

    public boolean zaduziKnjiguIzBaze(Clan c, Knjiga k){
        String sqlUpit = "update knjige\n" +
                "set zaduzena_clan_id = ?\n" +
                "where id = ? and zaduzena_clan_id is null";

        try(Connection konekcija = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement vozilo = konekcija.prepareStatement(sqlUpit)){
            vozilo.setInt(1, c.getId());
            vozilo.setInt(2, k.getId());
            int brojDodatihRedova = vozilo.executeUpdate();
            return brojDodatihRedova > 0;
        } catch (SQLException e) {
            return false;
        }

    }

    public boolean vratiKnjiguUBazu(Knjiga k){
        String sqlUpit = "update knjige\n" +
                "set zaduzena_clan_id = null\n" +
                "where id = ?";

        try (Connection konekcija = DriverManager.getConnection(URL,USER,PASSWORD);
             PreparedStatement vozilo = konekcija.prepareStatement(sqlUpit)){

            vozilo.setInt(1,k.getId());

            int brojDodatihRedova = vozilo.executeUpdate();

            return brojDodatihRedova > 0;

        }catch (SQLException e){
            return false;

        }

    }


    public Knjiga pronadjiClanapoId (int id){
        String sqlUpit = "SELECT * FROM knjige WHERE id = ?";
        Knjiga k = null;

        try(Connection konecija = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement vozilo = konecija.prepareStatement(sqlUpit)){

            vozilo.setInt(1, id);
            ResultSet rezultat = vozilo.executeQuery();

            if (rezultat.next()) {
                int id1 = rezultat.getInt("id");
                String ime = rezultat.getString("naziv");
                String prezime = rezultat.getString("autor");
                String brojKarte = rezultat.getString("isbn");

                Knjiga k1 = new Knjiga(ime, prezime, brojKarte);
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
