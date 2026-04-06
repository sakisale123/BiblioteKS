package service.impl;

import model.Clan;
import model.Knjiga;
import repository.ClanRepository;
import repository.KnjigaRepository;
import service.BibliotekaService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BibliotekaServiceImpl implements BibliotekaService {

    private ClanRepository clanRepo;
    private KnjigaRepository knjigaRepo;

    public BibliotekaServiceImpl() {
        clanRepo = new ClanRepository();
        knjigaRepo = new KnjigaRepository();
    }

    @Override
    public String procesirajZaduzivanje(int clanId, int knjigaId){
        Clan c = clanRepo.pronadjiClanapoId(clanId);
        if(c == null){
            return "Greak: Clan sa id:" + clanId + " ne postoji.";
        }

        Knjiga k  = knjigaRepo.pronadjiKnjiguPoId(knjigaId);
        if (k == null){
            return "Greska: Knjiga id:" + knjigaId + " ne postoji.";
        }

        List<Knjiga> knjigeClana = clanRepo.prikaziKnjigeClana(c);
        if (knjigeClana.size() >= 3){
            return "Greska: Clan" + c.getIme() + " vec ima zaduzene tri knjige.";
        }
        boolean uspeh = knjigaRepo.zaduziKnjiguIzBaze(c,k);

        if (uspeh){
            return "Clan "+ c.getIme()+"je zaduzio "+k.getNaziv();
        }
        else {
            return "Greska pri zaduzivanju knjige";
        }
    }

    @Override
    public String procesirajVracanje(int knjigaId){
        Knjiga k = knjigaRepo.pronadjiKnjiguPoId(knjigaId);
        if (k == null){
            return "Greska: knjiga id:" + knjigaId + " ne postoji.";
        }
        boolean uspeh = knjigaRepo.vratiKnjiguUBazu(k);
        if (uspeh){
            return "Knjiga "+k.getNaziv()+"je uspesno vracena";
        }
        else {
            return "Greska pri vracanju knjige";
        }
    }

    @Override
    public List<Knjiga> dobaviSveKnjige(){
        return knjigaRepo.dobaviSveKnjige();
    }

    @Override
    public List<Clan> dobaviSveClanove(){
        return clanRepo.prikaziClanove();
    }

    @Override
    public boolean dodajClanaUBazu (Clan c){
        if (c == null){
            return false;
        }
        return clanRepo.dodajClanaUBazu(c);
    }

    @Override
    public List<Knjiga> prikaziKnjigeClana(Clan c){
        if (c == null){
            return new ArrayList<>();
        }
        return clanRepo.prikaziKnjigeClana(c);
    }

    @Override
    public Clan pronadjiClanapoId (int id){
        if (id <= 0){
            return null;
        }
        return clanRepo.pronadjiClanapoId(id);
    }

    @Override
    public Knjiga pronadjiKnjiguPoId (int id){
        if (id <= 0){
            return null;
        }
        return knjigaRepo.pronadjiKnjiguPoId(id);
    }

    @Override
    public boolean dodajKnjigu(Knjiga k){
        if (k == null){
            return false;
        }
        return knjigaRepo.dodajKnjigu(k);
    }

}
