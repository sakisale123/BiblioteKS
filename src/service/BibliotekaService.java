package service;

import model.Clan;
import model.Knjiga;

import java.sql.SQLException;
import java.util.List;

public interface BibliotekaService {
    String procesirajZaduzivanje(int clanId, int knjigaId);
    String procesirajVracanje(int knjigaId);
    List<Knjiga> dobaviSveKnjige();
    List<Clan> dobaviSveClanove();
    boolean dodajClanaUBazu (Clan c);
    List<Knjiga> prikaziKnjigeClana(Clan c);
    Clan pronadjiClanapoId (int id);
    boolean dodajKnjigu(Knjiga k);
    Knjiga pronadjiKnjiguPoId (int id);
}
