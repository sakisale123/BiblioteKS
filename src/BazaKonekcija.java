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
}
