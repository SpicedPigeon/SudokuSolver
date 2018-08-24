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
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Datei datei = new Datei();
        Sudoku sudoku = new Sudoku(datei);
        SudokuGUI testGUI = new SudokuGUI(sudoku);
        testGUI.setVisible(true);
    }

}
