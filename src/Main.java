
import model.Clan;
import model.Knjiga;
import repository.ClanRepository;
import repository.KnjigaRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner input = new Scanner(System.in);

        KnjigaRepository knjigaRepo = new KnjigaRepository();
        ClanRepository clanRepo = new ClanRepository();

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
                    System.out.println("Unesi id clana: ");
                    int idC = Integer.parseInt(input.nextLine());
                    System.out.println("Unesi id knjige: ");
                    int idK = Integer.parseInt(input.nextLine());

                    Clan clanZaZaduzivanje = clanRepo.pronadjiClanapoId(idC);
                    Knjiga knjigaZaZaduczivanje = knjigaRepo.pronadjiClanapoId(idK);

                    knjigaRepo.zaduziKnjiguIzBaze(clanZaZaduzivanje, knjigaZaZaduczivanje);
                    System.out.println("Usposno ste zaduzili knjigu "+ knjigaZaZaduczivanje.getNaziv());
                    break;

                case "4":
                    System.out.println("Unesi id knjige: ");
                    int idKn = Integer.parseInt(input.nextLine());

                    Knjiga knjigaZaVracanje = knjigaRepo.pronadjiClanapoId(idKn);

                    knjigaRepo.vratiKnjiguUBazu(knjigaZaVracanje);
                    System.out.println("Usposno ste vratili knjigu "+ knjigaZaVracanje.getNaziv());
                    break;
                case "5":
                    System.out.println("Unesi ime: ");
                    String ime = input.nextLine();
                    System.out.println("Unesi prezime: ");
                    String prezime = input.nextLine();
                    System.out.println("Unesi trocifren broj karte: ");
                    String brojKarte = input.nextLine();
                    brojKarte = "KARTA-"+ brojKarte;

                    Clan clanZaDodavanje = new Clan(ime, prezime, brojKarte);

                    clanRepo.dodajClanaUBazu(clanZaDodavanje);
                    System.out.println("Clana "+ ime + " je registrovan.");
                    break;

                case "6":
                    List<Clan> listaClanov = clanRepo.prikaziClanove();
                    for(Clan c : listaClanov){
                        System.out.println(c.getId() +" | "+ c.getIme()+" "+c.getPrezime()+" | "+c.getBrojKarte());
                    }
                    break;

                case "7":
                    try{
                        System.out.println("Unesi id clana: ");
                        int id4 = Integer.parseInt(input.nextLine());
                        Clan trazeniClan = clanRepo.pronadjiClanapoId(id4);

                        List<Knjiga> njegoveKnjige = clanRepo.prikaziKnjigeClana(trazeniClan);

                        System.out.println("--- KNJIGE KOD CLANA: " + trazeniClan.getIme() + " " + trazeniClan.getPrezime() + " ---");

                        if (njegoveKnjige.isEmpty()) {
                            System.out.println("Ovaj clan nema zaduzenih knjiga.");
                        } else {
                            for (Knjiga k3 : njegoveKnjige) {
                                System.out.println("- " + k3.getNaziv() + " (" + k3.getAutor() + ")");
                            }
                        }
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