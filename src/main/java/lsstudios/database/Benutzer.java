package lsstudios.database;

public class Benutzer {
    public String vorname;
    public String nachname;
    public String passwort;
    public String notensystem;
    public String colorTheme;

    public Benutzer(String vorname, String nachname, String passwort, String notensystem, String colorTheme) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.passwort = passwort;
        this.notensystem = notensystem;
        this.colorTheme = colorTheme;
    }
}
