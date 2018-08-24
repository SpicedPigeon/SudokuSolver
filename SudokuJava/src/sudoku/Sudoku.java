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
public class Sudoku {

    //Zähler
    private int x = 0;
    private int y = 0;
    private int z = 0;
    //---------------
    private Zelle[][] zelle;
    private Block[] block;
    private Datei datei;

    public Sudoku(Datei aDatei) {
        block = new Block[27];
        zelle = new Zelle[9][9];
        this.datei = aDatei;
    }

    public Zelle[][] getZellen() {
        return zelle;
    }

    private void reinigen(Block[] aBlockArray) {
        for (int i = 0; i < 27; i++) {
            aBlockArray[i].bereinigen();
        }
    }

    public void reinigenOriginal() {
        this.reinigen(this.block);
    }

    public void initialisiereZellenAusDatei() {
        String kette = this.datei.getDatei();
        int tmpWert;
        //Zellen
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                int tmpX = x + y * 9;
                tmpWert = kette.charAt(tmpX) - '0';
                zelle[y][x] = new Zelle(tmpWert);
            }
        }
        block = initialisiereBloeckeAusZellen(zelle);
    }

    private Block[] initialisiereBloeckeAusZellen(Zelle[][] aZelleArray) {
        int counter = 0;
        Zelle[] tmpZelle1 = new Zelle[9];
        Zelle[] tmpZelle2 = new Zelle[9];
        Zelle[] tmpZelle3 = new Zelle[9];
        Block[] tmpBlock = new Block[27];
        //Reihenblöcke
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                tmpZelle1[x] = aZelleArray[y][x];
            }
            tmpBlock[counter] = new Block(tmpZelle1);
            counter++;
        }
        //Spaltenblöcke
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                tmpZelle1[y] = aZelleArray[y][x];
            }
            tmpBlock[counter] = new Block(tmpZelle1);
            counter++;
        }
        //Quadrantenblöcke
        int yH = 3;
        int y = 0;
        int c = 0;
        for (int i = 0; i < 3; i++) {
            while (y < yH) {
                for (int x = 0; x < 3; x++) {
                    tmpZelle1[c] = aZelleArray[y][x];
                    tmpZelle2[c] = aZelleArray[y][x + 3];
                    tmpZelle3[c] = aZelleArray[y][x + 6];
                    c++;
                }
                y++;
            }
            tmpBlock[counter] = new Block(tmpZelle1);
            counter++;
            tmpBlock[counter] = new Block(tmpZelle2);
            counter++;
            tmpBlock[counter] = new Block(tmpZelle3);
            counter++;
            yH += 3;
            c = 0;
        }
        return tmpBlock;
    }

    private int getGesamtzahl(Block[] aBlockArray) {
        int tmpZahl = 0;
        for (int i = 0; i < 27; i++) {
            tmpZahl += aBlockArray[i].getGesamtzahl();
        }
        return tmpZahl;
    }

    private Zelle[][] kopiereZellenArray(Zelle[][] aZelle) {
        Zelle[][] tmpKopieZelle = new Zelle[9][9];
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                tmpKopieZelle[y][x] = aZelle[y][x].cloneZelle();
            }
        }
        return tmpKopieZelle;
    }

    private boolean hatFehlerAufSudoku(Block[] aBlock) {
        boolean b = false;
        for (int i = 0; i < 27; i++) {
            if (aBlock[i].hatFehler()) {
                b = true;
            }
        }
        return b;
    }

    private void reinigenBisNichtsMehrPassiert(Block[] aBlockArray) {
        int gesamtZahlNachReinigen = 0, gesamtZahlVorReinigen = 1;

        while (gesamtZahlNachReinigen != gesamtZahlVorReinigen) {
            gesamtZahlVorReinigen = getGesamtzahl(aBlockArray);
            reinigen(aBlockArray);
            gesamtZahlNachReinigen = getGesamtzahl(aBlockArray);
        }
    }

    private boolean sudokuGeloest(Block[] aBlockArray) {
        boolean b = true;
        for (int i = 1; i <= 9; i++) {
            for (int t = 0; t < 27; t++) {
                if (!aBlockArray[t].enthaehltZahl(i)) {
                    b = false;
                }
            }
        }
        return b;
    }

    public void backTrackingSchritt() {
        Zelle[][] kopieZelle;
        Block[] kopieBlock;
        if (z < zelle[y][x].getSize()) {
            z++;
        } else if (x < 8) {
            z = 1;
            x++;
        } else if (y < 8) {
            z = 1;
            x = 0;
            y++;
        } else if (y == 8 && x == 8) {
            x = 0;
            y = 0;
            z = 1;
        }
        reinigenBisNichtsMehrPassiert(block);
        if (sudokuGeloest(block)) {
        } else {
            kopieZelle = kopiereZellenArray(zelle);
            kopieBlock = initialisiereBloeckeAusZellen(kopieZelle);
            if (!kopieZelle[y][x].enthaeltNurEineZahl()) {
                int tmpZahl = zelle[y][x].getZteZahl(z);
                kopieZelle[y][x].setWert(tmpZahl);
                reinigenBisNichtsMehrPassiert(kopieBlock);
                if (hatFehlerAufSudoku(kopieBlock)) {
                    zelle[y][x].zahlEntfernen(tmpZahl);
                    z--;
                }

            }
        }
    }

    public void backTracking() {
        while (!sudokuGeloest(block)) {

            backTrackingSchritt();

        }
        this.datei.dateiAktualisieren(zelle);
    }

    public void clear() {
        this.block = null;
        this.x = 0;
        this.y = 0;
        this.z = 0;
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                zelle[x][y].setWert(0);
            }
        }
    }

    //Datei Speicher

    public void ladeDatei() {
        this.datei.ladeDatei();
    }

    public void speicherDatei() {
        this.datei.speicherDatei();
    }

    public String getDateiString() {
        return this.datei.getDatei();
    }

    @Override
    public String toString() {
        String s = "";
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                
            }
        }
        return null;
    }
    
    
}
