package model;

public class Knjiga {
    private String naziv;
    private String autor;
    private String isbn;
    private boolean jeZaduzena;

    public Knjiga(String naziv, String autor, String isbn) {
        this.naziv = naziv;
        this.autor = autor;
        this.isbn = isbn;
        this.jeZaduzena = false;
    }

    public Knjiga() {
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isJeZaduzena() {
        return jeZaduzena;
    }

    public void setJeZaduzena(boolean jeZaduzena) {
        this.jeZaduzena = jeZaduzena;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Knjiga{" +
                "naziv='" + naziv + '\'' +
                ", autor='" + autor + '\'' +
                ", isbn='" + isbn + '\'' +
                ", jeZaduzena=" + jeZaduzena +
                '}';
    }
}