import java.sql.*;

public class BazaKonekcija {
    private static final String URL = "jdbc:mysql://localhost:3306/biblioteka";
    private static final String USER = "root";
    private static final String PASSWORD = "sakisale123";

    public static void testirajVezu() throws SQLException {
        try {
            Connection konekcija = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("BINGO USPESNA KONEKCIJA");

            konekcija.close();
        } catch (Exception e) {
            System.out.println("Greska prilikom povezovanja: " + e.getMessage());
        }

    }

    public static void prikaziKnjigeIzBaze() throws SQLException {
        String sqlUpit = "SELECT * FROM knjige";

        try (Connection konekcija = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement vozilo = konekcija.createStatement();
             ResultSet rezultat = vozilo.executeQuery(sqlUpit)) {

            System.out.println("\n--- SPISAK KNJIGA IZ BAZE ---");
            while (rezultat.next()) {
                int id = rezultat.getInt("id");
                String naslov = rezultat.getString("naziv");
                String autor = rezultat.getString("autor");
                String isbn = rezultat.getString("isbn");
                int zaduzioClanId = rezultat.getInt("zaduzena_clan_id");

                String status = null;

                if (zaduzioClanId == 0) {
                    status = "SLOBODNA";
                }else{
                    status = "ZADUZENA (id Clana: " + zaduzioClanId + ")";

                }



                System.out.println("ID: " + id + " | " + naslov + " - " + autor + " - " + isbn + " - " + status);
            }
        } catch (SQLException e) {
            System.out.println("Greska prilikom povezovanja: " + e.getMessage());
        }
    }


    public static void dodajKnjiguUBazu(String naslov, String autor, String isbn){
        String sqlUpit = "INSERT INTO knjige (naziv, autor, isbn) VALUES (?, ?, ?)";

        try(Connection konekcija = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement vozilo = konekcija.prepareStatement(sqlUpit);) {
            vozilo.setString(1, naslov);
            vozilo.setString(2, autor);
            vozilo.setString(3, isbn);

            int brojDodatihRedova = vozilo.executeUpdate();

            if (brojDodatihRedova > 0) {
                System.out.println("Dodatih redovata: " + brojDodatihRedova);
            }

            } catch (SQLException e) {
            System.out.println("Greska prilikom povezovanja: " + e.getMessage());
        }

    }



    public static void dodajClanaUBazu (String ime, String prezime, String brojKarte){
        String sqlUpit = "INSERT INTO clanovi (ime,prezime,broj_karte) VALUES (?, ?, ?)";

        try(Connection konekcija = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement vozilo = konekcija.prepareStatement(sqlUpit)){

            vozilo.setString(1, ime);
            vozilo.setString(2, prezime);
            vozilo.setString(3, brojKarte);

            int brojDodatihRedova = vozilo.executeUpdate();

            if (brojDodatihRedova > 0) {
                System.out.println("Dodatih redovata: " + brojDodatihRedova);
            }

        }catch (SQLException e){
            System.out.println("Greska prilikom povezovanja: " + e.getMessage());
        }
    }


    public static void zaduziKnjiguIzBaze(int clanId, int knjigaId){
        String sqlUpit = "update knjige\n" +
                "set zaduzena_clan_id = ?\n" +
                "where id = ? and zaduzena_clan_id is null";

        try(Connection konekcija = DriverManager.getConnection(URL,USER,PASSWORD);
        PreparedStatement vozilo = konekcija.prepareStatement(sqlUpit)){
            vozilo.setInt(1, clanId);
            vozilo.setInt(2, knjigaId);
            int brojDodatihRedova = vozilo.executeUpdate();
            if (brojDodatihRedova > 0) {
                System.out.println("Dodatih redovata: " + brojDodatihRedova);

            }
        } catch (SQLException e) {
            System.out.println("Greska prilikom povezovanja: " + e.getMessage());
        }
    }


    public static void vratiKnjiguUBazu(int idKnjige){
        String sqlUpit = "update knjige\n" +
                "set zaduzena_clan_id = null\n" +
                "where id = ?";


        try (Connection konekcija = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement vozilo = konekcija.prepareStatement(sqlUpit)){

            vozilo.setInt(1,idKnjige);

            int brojDodatihRedova = vozilo.executeUpdate();

            if (brojDodatihRedova > 0) {
                System.out.println("Dodatih redovata: " + brojDodatihRedova);
            }

        }catch (SQLException e){
            System.out.println("Greska prilikom povezovanja: " + e.getMessage());
        }

    }


}
