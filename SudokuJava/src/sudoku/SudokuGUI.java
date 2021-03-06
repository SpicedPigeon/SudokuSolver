/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

/**
 *
 * @author student
 */
public class SudokuGUI extends javax.swing.JFrame {

    private Sudoku aSudoku;
    private JTable[] quadranten = new JTable[9];

    /**
     * Creates new form GUI
     */
    public SudokuGUI(Sudoku aSudoku) {
        this.aSudoku = aSudoku;
        initStyle();
        initComponents();
        //Zellentext mittig setzen

        sudokuFeld = new CustomPanel();
        panelField.add(sudokuFeld);
        sudokuFeld.setBounds(20, 20, 600, 600);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        panelField = new javax.swing.JPanel();
        btnBackTracking = new javax.swing.JButton();
        btnStep = new javax.swing.JButton();
        menu = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuFileOpen = new javax.swing.JMenuItem();
        menuFileSave = new javax.swing.JMenuItem();

        jMenu2.setText("jMenu2");

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sudoku");

        btnBackTracking.setText("BackTracking bis Lösung");
        btnBackTracking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackTrackingActionPerformed(evt);
            }
        });

        btnStep.setText("Ohne Backtracking");
        btnStep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStepActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelFieldLayout = new javax.swing.GroupLayout(panelField);
        panelField.setLayout(panelFieldLayout);
        panelFieldLayout.setHorizontalGroup(
            panelFieldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFieldLayout.createSequentialGroup()
                .addGap(640, 640, 640)
                .addGroup(panelFieldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBackTracking, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnStep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelFieldLayout.setVerticalGroup(
            panelFieldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFieldLayout.createSequentialGroup()
                .addGap(322, 322, 322)
                .addComponent(btnBackTracking)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnStep)
                .addContainerGap(249, Short.MAX_VALUE))
        );

        jMenu1.setText("File");

        menuFileOpen.setText("Open Sudoku File...");
        menuFileOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFileOpenActionPerformed(evt);
            }
        });
        jMenu1.add(menuFileOpen);

        menuFileSave.setText("Save File...");
        menuFileSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFileSaveActionPerformed(evt);
            }
        });
        jMenu1.add(menuFileSave);

        menu.add(jMenu1);

        setJMenuBar(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuFileOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFileOpenActionPerformed
        this.aSudoku.ladeDatei();
        try {
            aSudoku.initialisiereZellenAusDatei();
            this.update();
        } catch (NullPointerException ne) {

        } catch (StringIndexOutOfBoundsException se) {
            JOptionPane.showMessageDialog(null, "Fehlerhafte Datei!");
        }
    }//GEN-LAST:event_menuFileOpenActionPerformed

    private void menuFileSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFileSaveActionPerformed
        this.aSudoku.speicherDatei();
    }//GEN-LAST:event_menuFileSaveActionPerformed

    private void btnStepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStepActionPerformed
        try {
            aSudoku.reinigenOriginal();
            this.update();
        } catch (NullPointerException npe) {
            JOptionPane.showMessageDialog(null, "Kein Sudoku eingelesen!");
        } catch (IndexOutOfBoundsException ie) {
            JOptionPane.showMessageDialog(null, "Fehler auf Sudoku!");
            aSudoku.clear();
            this.update();
        }
    }//GEN-LAST:event_btnStepActionPerformed

    private void btnBackTrackingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackTrackingActionPerformed
        try {
            aSudoku.backTracking();
            this.update();
            JOptionPane.showMessageDialog(null, "Sudoku gelöst!");
        } catch (NullPointerException npe) {
            JOptionPane.showMessageDialog(null, "Kein Sudoku eingelesen!");
        } catch (IndexOutOfBoundsException ie) {
            JOptionPane.showMessageDialog(null, "Fehler auf Sudoku!");
            aSudoku.clear();
            this.update();
        }
    }//GEN-LAST:event_btnBackTrackingActionPerformed

    public void update() {
        sudokuFeld.repaint();
    }

    private void initStyle() {
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {

        }
    }

    private CustomPanel sudokuFeld;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBackTracking;
    private javax.swing.JButton btnStep;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuBar menu;
    private javax.swing.JMenuItem menuFileOpen;
    private javax.swing.JMenuItem menuFileSave;
    private javax.swing.JPanel panelField;
    // End of variables declaration//GEN-END:variables

    private class CustomPanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.GRAY);

            //schmale  Linien
            int klLinAb = sudokuFeld.getWidth() / 9;
            for (int i = 1; i < 9; i++) {
                g.drawLine(i * klLinAb, 0, i * klLinAb, sudokuFeld.getHeight()-3);
                g.drawLine(0, i * klLinAb, sudokuFeld.getWidth()-3, i * klLinAb);
            }

            //Ränder
            g.setColor(Color.black);
            int abstand = klLinAb * 3;
            for (int i = 0; i < 4; i++) {
                g.fillRect(0, i * abstand, sudokuFeld.getWidth()-2, 5);
                g.fillRect(i * abstand, 0, 5, sudokuFeld.getHeight()-2);
            }

            //Zahlen
            g.setFont(new Font("Arial", Font.PLAIN, 30));
            try {
                for (int x = 0; x < 9; x++) {
                    for (int y = 1; y < 10; y++) {
                        g.drawString(aSudoku.getZellen()[x][y - 1].getZahlString(), klLinAb * x + 25, klLinAb * y - 20);
                    }
                }
            } catch (NullPointerException np) {

            }
        }
    }
}
