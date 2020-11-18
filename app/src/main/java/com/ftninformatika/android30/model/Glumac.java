package com.ftninformatika.android30.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
@DatabaseTable(tableName = Glumac.TABLE_NAME_USER)
public class Glumac {

    public static final String TABLE_NAME_USER = "glumci";

    public static final String FIELD_NAME_ID = "id";
    public static final String FIELD_NAME_IME = "ime";
    public static final String FIELD_NAME_PREZIME = "prezime";
    public static final String FIELD_NAME_BIO = "biografija";
    //public static final String FIELD_NAME_DATUMRODJENJA = "datumrodjenja";
    public static final String FIELD_NAME_OCENA = "ocena";
    public static final String FIELD_NAME_FILMOVI = "filmovi";

    @DatabaseField(columnName = FIELD_NAME_ID, generatedId = true)
    private int id;

    @DatabaseField(columnName = FIELD_NAME_IME)
    private String ime;
    @DatabaseField(columnName = FIELD_NAME_PREZIME)
    private String prezime;
    @DatabaseField(columnName = FIELD_NAME_BIO)
    private String biografija;
//    @DatabaseField(columnName = FIELD_NAME_DATUMRODJENJA)
//    private String datumRodjenja;
    @DatabaseField(columnName = FIELD_NAME_OCENA)
    private float ocena;
    @DatabaseField(columnName = FIELD_NAME_FILMOVI)
    private String filmovi;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getBiografija() {
        return biografija;
    }

    public void setBiografija(String biografija) {
        this.biografija = biografija;
    }

    public float getOcena() {
        return ocena;
    }

    public void setOcena(float ocena) {
        this.ocena = ocena;
    }

    public String getFilmovi() {
        return filmovi;
    }

    public void setFilmovi(String filmovi) {
        this.filmovi = filmovi;
    }

//    public String getDatumRodjenja() {
//        return datumRodjenja;
//    }
//
//    public void setDatumRodjenja(String datumRodjenja) {
//        this.datumRodjenja = datumRodjenja;
//    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

}
