import model.Biblioteka;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner input = new Scanner(System.in);
        Biblioteka biblioteka = new Biblioteka();

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

//                    Korisceno array list kao baza i sada je prevaceno na MySQL
//                    biblioteka.dodajKnjigu(naslov, autor, ibsn);
                    BazaKonekcija.dodajKnjiguUBazu(naslov, autor, ibsn);
                    break;


                case "2":
//                    biblioteka.prikaziKnjige();
                    BazaKonekcija.prikaziKnjigeIzBaze();
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
                        BazaKonekcija.zaduziKnjiguIzBaze(idClana,idKnjige);
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
                    biblioteka.prikaziClanove();
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