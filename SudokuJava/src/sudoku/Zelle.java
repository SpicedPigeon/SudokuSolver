/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.util.ArrayList;

/**
 *
 * @author student
 */
public class Zelle {

    private ArrayList<Integer> zahlen;

    public Zelle(int wert) {
        zahlen = new ArrayList<>();
        if (wert == 0) {
            for (int i = 0; i < 9; i++) {
                zahlen.add((i + 1));
            }
        } else {
            zahlen.add(wert);
        }
    }

    public Zelle(ArrayList<Integer> aZahlen) {
        zahlen = (ArrayList<Integer>) aZahlen.clone();
    }

    public void zahlEntfernen(Integer zahl) {
        zahlen.remove(zahl);
    }

    public String getZahlString() {
//        String s = "";
//        for (int i = 0; i < zahlen.size(); i++) {
//            s += Integer.toString(zahlen.get(i));
//        }
        //mögliche zahlen nicht anzeigen
        String s = "";
        if (this.zahlen.size() == 1) {
                s += Integer.toString(zahlen.get(0));
            
        }
        return s;
    }

    public int getEinzigeZahl() {
        int zahl;
        if (zahlen.size() == 1) {
            zahl = zahlen.get(0);
        } else {
            //Exeption nicht zahl = 0
            zahl = 0;
        }
        return zahl;
    }

    public void setWert(int wert) {
        zahlen.clear();
        if (wert != 0) {
            zahlen.add(wert);
        }
    }

    public boolean istGleich(Zelle aZelle) {
        boolean b = false;
        if (this.getZahlString().equals(aZelle.getZahlString()) && this.getZahlString().length() == 2) {
            b = true;
        }
        return b;
    }

    public boolean istGleich(Zelle aZelle1, Zelle aZelle2) {
        boolean b = false;
        if (this.getZahlString().equals(aZelle1.getZahlString()) && this.getZahlString().length() == 3 && this.getZahlString().equals(aZelle2.getZahlString())) {
            b = true;
        }
        return b;
    }

    public boolean enthaeltNurEineZahl() {
        return (zahlen.size() == 1);
    }

    public int getErsteZahl() {
        return zahlen.get(0);
    }

    public int getZweiteZahl() {
        return zahlen.get(1);
    }

    public int getDritteZahl() {
        return zahlen.get(2);
    }

    public int getSummeZahlen() {
        int tmpZahl = 0;
        for (int i = 0; i < zahlen.size(); i++) {
            tmpZahl += zahlen.get(i);
        }
        return tmpZahl;
    }

    public Zelle cloneZelle() {
        Zelle tmpZelle = new Zelle(this.zahlen);

        return tmpZelle;
    }

    public int getZteZahl(int z) {
        return zahlen.get(z - 1);
    }

    public int getSize() {
        return zahlen.size();
    }

    public boolean enthaeltKeineZahl() {
        boolean b = false;
        if (zahlen.isEmpty()) {
            b = true;
        }
        return b;
    }

    public boolean enthaeltZahl(int zahl) {
        return zahlen.contains(zahl);
    }
}
