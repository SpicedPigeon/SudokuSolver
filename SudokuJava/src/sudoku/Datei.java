/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;

public class Datei {

    private String dateiString;
    private JFileChooser fc;

    public Datei() {
        initStyle();
        fc = new JFileChooser();
    }

    public void dateiAktualisieren(Zelle[][] aZelle) {
        String neuerString = "";
        for (int i = 0; i < 9; i++) {
            for (int k = 0; k < 9; k++) {
                neuerString += Integer.toString(aZelle[i][k].getEinzigeZahl());
            }
        }
        this.dateiString = neuerString;
    }

    public void ladeDatei() {
        int returnVal = fc.showOpenDialog(fc);
        String s = "";
        try {
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                while (br.ready()) {
                    s += br.readLine();
                }

                fr.close();
                this.dateiString = s;
            }
        } catch (IOException ioe) {

        }
    }

    public void speicherDatei() {
        int returnVal = fc.showSaveDialog(fc);
        String loesung = this.dateiString;
        try {
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File f = fc.getSelectedFile();
                f.createNewFile();
                FileWriter fw = new FileWriter(f);
                BufferedWriter bw = new BufferedWriter(fw);
                int t = 8;
                for (int i = 0; i < 81; i++) {
                    bw.append(loesung.charAt(i));
                    if (i == t) {
                        t += 9;
                        bw.append("\n");
                    }
                }
                bw.close();
            }
        } catch (IOException ioe) {

        }
    }
    public String getDatei() {
        return this.dateiString;
    }
    private void initStyle(){
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            
        }
    }
}
