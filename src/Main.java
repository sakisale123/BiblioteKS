
import model.Knjiga;
import repository.KnjigaRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner input = new Scanner(System.in);

        KnjigaRepository knjigaRepo = new KnjigaRepository();

        while(true){
            System.out.println("\n-----BIBLIOTEKA MENI------");
            System.out.println("1. Dodaj knjigu");
            System.out.println("2. Prikazi sve knjige");
            System.out.println("3. Zaduzi knjigu");
            System.out.println("4. Vrati knjigu");
            System.out.println("5. Registruj novog clana");
            System.out.println("6. Prikazu sve clanove");
            System.out.println("7. Prikazi zaduzene knjige clana");
            System.out.println("8. Izlaz");
            System.out.print("Izaberite opciju: ");

            String opcija = input.nextLine();

            switch (opcija){
                case "1":
                    System.out.println("Unesi naslov: ");
                    String naslov = input.nextLine();

                    System.out.println("Unesi autora: ");
                    String autor = input.nextLine();

                    System.out.println("Unesi IBSN: ");
                    String ibsn = input.nextLine();

                    Knjiga k = new Knjiga(naslov, autor, ibsn);

                    knjigaRepo.dodajKnjigu(k);
                    break;


                case "2":

                    List<Knjiga> sveKnjigeIzBaze = knjigaRepo.dobaviSveKnjige();

                    System.out.println("\n--- KNJIGE U BIBLIOTECI ---");
                    if (sveKnjigeIzBaze.isEmpty()) {
                        System.out.println("Magacin je prazan!");
                    } else {
                        for (Knjiga knjiga : sveKnjigeIzBaze) {
                            System.out.println("ID: " + knjiga.getId() + " | " + knjiga.getNaziv() + " - " + knjiga.getAutor());
                        }
                    }
                    break;


                case "3":
                    int idKnjige = 0;
                    int idClana = 0;
                    try{
                        System.out.println("\nUnesi clanskiBroj koji uzima: ");
                        idClana = Integer.parseInt(input.nextLine());
                        System.out.println("\nUnesi id knjige koju si uzeo: ");
                        idKnjige = Integer.parseInt(input.nextLine());
//                        biblioteka.zaduziKnjigu(nazivKnjige,idClana);

                    }
                    catch (Exception e){
                        System.out.println("Neposotji na : "+ idKnjige);
                    }
                    break;

                case "4":
//                    System.out.println("\nUnesi clanskiBroj koji uzima: ");
//                    idClana = Integer.parseInt(input.nextLine());
                    System.out.println("\nUnesi id knjige koju si uzeo: ");
                    idKnjige = Integer.parseInt(input.nextLine());
//                    biblioteka.vratiKnjigu(idKnjige,idClana);
                    BazaKonekcija.vratiKnjiguUBazu(idKnjige);
                    break;
                case "5":
                    System.out.println("Unesi ime: ");
                    String ime = input.nextLine();
                    System.out.println("Unesi prezime: ");
                    String prezime = input.nextLine();
                    System.out.println("Unesi trocifren broj karte: ");
                    String brojKarte = input.nextLine();
                    brojKarte = "KARTA-"+ brojKarte;

//                    biblioteka.registrujClana(ime, prezime);
                    BazaKonekcija.dodajClanaUBazu(ime,prezime,brojKarte);
                    System.out.println("Clana "+ ime + " je registrovan.");
                    break;
                case "6":
//                    biblioteka.prikaziClanove();
                    break;
                case "7":
                    try{
                        System.out.println("Unesi id clanske : ");
                        int id = Integer.parseInt(input.nextLine());
                        BazaKonekcija.prikaziKnjigeClana(id);
//                        biblioteka.prikaziZaduzeneKnjige(id);
                    }catch (Exception e){
                        System.out.println("Greska pri pretrazi");
                    }
                    break;



                case "8":
                    return;
            }

        }
    }
}