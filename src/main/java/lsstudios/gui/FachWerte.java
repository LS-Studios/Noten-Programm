package lsstudios.gui;

import java.util.ArrayList;

public class FachWerte {
    public String fach;
    public String thema;
    public ArrayList<Integer> kleineNoten;
    public int klausurNoten;
    public int schnitt;

    public FachWerte(String fach, String thema, ArrayList<Integer> kleineNoten, int klausurNoten, int schnitt) {
        this.fach = fach;
        this.thema = thema;
        this.kleineNoten = kleineNoten;
        this.klausurNoten = klausurNoten;
        this.schnitt = schnitt;
    }

    public String getFach() {
        return fach;
    }

    public void setFach(String fach) {
        this.fach = fach;
    }

    public String getThema() {
        return thema;
    }

    public void setThema(String thema) {
        this.thema = thema;
    }

    public ArrayList<Integer> getKleineNoten() {
        return kleineNoten;
    }

    public void setKleineNoten(ArrayList<Integer> kleineNoten) {
        this.kleineNoten = kleineNoten;
    }

    public int getKlausurNoten() {
        return klausurNoten;
    }

    public void setKlausurNoten(int klausurNoten) {
        this.klausurNoten = klausurNoten;
    }

    public int getSchnitt() {
        return schnitt;
    }

    public void setSchnitt(int schnitt) {
        this.schnitt = schnitt;
    }
}
