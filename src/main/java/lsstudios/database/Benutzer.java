package lsstudios.database;

public class Benutzer {
    public String vorname;
    public String nachname;
    public String passwort;
    public int notensystem;

    public Benutzer(String vorname, String nachname, String passwort, int notensystem) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.passwort = passwort;
        this.notensystem = notensystem;
    }
}
