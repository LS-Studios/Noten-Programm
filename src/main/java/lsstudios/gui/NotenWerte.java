package lsstudios.gui;

import javafx.scene.control.Button;

public class NotenWerte {
    int id;
    int note;
    String aufgabe;
    String notenwert;

    public NotenWerte(int note, String aufgabe, String notenwert) {
        this.note = note;
        this.aufgabe = aufgabe;
        this.notenwert = notenwert;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getAufgabe() {
        return aufgabe;
    }

    public void setAufgabe(String aufgabe) {
        this.aufgabe = aufgabe;
    }

    public String getNotenwert() {
        return notenwert;
    }

    public void setNotenwert(String notenwert) {
        this.notenwert = notenwert;
    }
}
