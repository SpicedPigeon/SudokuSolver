/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

/**
 *
 * @author student
 */
public class Block {

    private Zelle[] zelle;

    public Block(Zelle[] zelle) {
        this.zelle = new Zelle[9];
        for (int i = 0; i < 9; i++) {
            this.zelle[i] = zelle[i];
        }
    }

    public void bereinigen() {
        for (int k = 0; k < 9; k++) {
            if (zelle[k].enthaeltNurEineZahl()) {
                for (int i = 0; i < 9; i++) {
                    if (k != i) {
                        zelle[i].zahlEntfernen(zelle[k].getEinzigeZahl());
                    }
                }
            }
        }
        entferneEinzigeZahl();
        entferneDoppler();
        entferneTrippler();
    }
    private void entferneEinzigeZahl(){
        int anzahl = 0;
        for(int zahl = 1 ; zahl <= 9 ; zahl++){
            for(int i = 0; i <9; i++){            
                if(zelle[i].enthaeltZahl(zahl)){
                    anzahl++;
                }
            }
            if(anzahl == 1){
                setzeZahlImBlock(zahl);
            }
            anzahl = 0;
        }
    }
    private void entferneDoppler() {
        for (int i = 0; i < 9; i++) {
            for (int k = 0; k < 9; k++) {
                if (zelle[i].istGleich(zelle[k]) && i != k) {
                    for (int t = 0; t < 9; t++) {
                        if (t != k && t != i) {
                            zelle[t].zahlEntfernen(zelle[i].getErsteZahl());
                            zelle[t].zahlEntfernen(zelle[i].getZweiteZahl());
                        }
                    }
                }
            }

        }
    }

    private void entferneTrippler() {
        for (int i = 0; i < 9; i++) {
            for (int k = 0; k < 9; k++) {
                for (int j = 0; j < 9; j++) {
                    if (zelle[i].istGleich(zelle[k], zelle[j]) && i != k && i != j && k != j) {
                        for (int t = 0; t < 9; t++) {
                            if (t != j && t != k && t != i) {
                                zelle[t].zahlEntfernen(zelle[i].getErsteZahl());
                                zelle[t].zahlEntfernen(zelle[i].getZweiteZahl());
                                zelle[t].zahlEntfernen(zelle[i].getDritteZahl());
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean hatFehler() {
        boolean b = false;
        for (int i = 0; i < 9; i++) {
            if(zelle[i].enthaeltKeineZahl()){
                b = true;
            }
        }
        return b;
    }

    public boolean enthaehltZahl(int aZahl) {
        boolean b = false;
        for (int i = 0; i < 9; i++) {
            if (zelle[i].enthaeltNurEineZahl()) {
                if (zelle[i].getEinzigeZahl() == aZahl) {
                    b = true;
                }
            }
        }
        return b;
    }
    public int getGesamtzahl(){
        int tmpGesamtzahl = 0;
        for(int i = 0; i < 9;i++){
            tmpGesamtzahl += zelle[i].getSummeZahlen();
        }
        return tmpGesamtzahl;
    }

    private void setzeZahlImBlock(int zahl) {
        for(int i = 0 ; i < 9; i++){
            if(zelle[i].enthaeltZahl(zahl)){
                zelle[i].setWert(zahl);
            }
        }
    }
}
