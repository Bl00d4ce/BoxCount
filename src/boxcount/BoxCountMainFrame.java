/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boxcount;

import static boxcount.BCUtil.*;
import static boxcount.BoxCount.*;
import static boxcount.barStepMode.*;
import java.awt.Cursor;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Simon
 */
public class BoxCountMainFrame extends javax.swing.JFrame {

    /**
     * Creates new form BoxCountMainFrame
     */
    public BoxCountMainFrame() {
        initComponents();
    }
    
    void setup(){
        btnResetSizes.setToolTipText(STD_SIZES);
        btnResetSizesActionPerformed(null);
        initProgressBar(barStep, 0, 2);
        initProgressBar(barRow, 0, IMG_SIZE_PX);
        initProgressBar(barCol, 0, IMG_SIZE_PX);
        updateStepBar(barStep, bsmInactive);
        updateGridBar(barRow, 0);
        updateGridBar(barCol, 0);
        edtSourcePattern.setText(ORIG_SOURCE_PATTERN);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblSizes = new javax.swing.JLabel();
        edtSizes = new javax.swing.JTextField();
        btnResetSizes = new javax.swing.JButton();
        barRow = new javax.swing.JProgressBar();
        btnStart = new javax.swing.JButton();
        btnRandom = new javax.swing.JButton();
        barCol = new javax.swing.JProgressBar();
        barStep = new javax.swing.JProgressBar();
        lblStep = new javax.swing.JLabel();
        lblRow = new javax.swing.JLabel();
        lblCol = new javax.swing.JLabel();
        lblFileName = new javax.swing.JLabel();
        edtFileName = new javax.swing.JTextField();
        btnColorConfig = new javax.swing.JButton();
        edtSourcePattern = new javax.swing.JTextField();
        lblSourcePattern = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Boxcounting-Dimension berechnen");
        setName("frmMain"); // NOI18N

        lblSizes.setText("Boxgrößen:");
        lblSizes.setToolTipText("Als kommaseparierte Zahlen eingeben (Müssen zwingend steigen!)");
        lblSizes.setName("lblSizes"); // NOI18N

        btnResetSizes.setText("Zurücksetzen");
        btnResetSizes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetSizesActionPerformed(evt);
            }
        });

        barRow.setPreferredSize(new java.awt.Dimension(256, 14));

        btnStart.setText("Starten");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        btnRandom.setText("Zufallsbild erzeugen");
        btnRandom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRandomActionPerformed(evt);
            }
        });

        barCol.setPreferredSize(new java.awt.Dimension(256, 14));

        barStep.setPreferredSize(new java.awt.Dimension(256, 14));

        lblStep.setText("Aktueller Arbeitsschritt:");

        lblRow.setText("Aktuelle Zeile:");

        lblCol.setText("Aktuelle Spalte:");

        lblFileName.setText("Dateiname:");
        lblFileName.setToolTipText("Ohne Dateiendung! Bleibt der Dateiname leer, heißen die Dateien 'heatmap' oder 'random'.");

        btnColorConfig.setText("Farbgebung konfigurieren");
        btnColorConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColorConfigActionPerformed(evt);
            }
        });

        lblSourcePattern.setText("Namensmuster Quelldateien:");
        lblSourcePattern.setToolTipText("Mit Dateiendung! {x} & {y} als Platzhalter für aktuelle Zeile/Spalte");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblRow)
                            .addComponent(lblStep)
                            .addComponent(lblCol)
                            .addComponent(btnRandom))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnStart, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(barCol, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(barStep, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(barRow, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnColorConfig)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblSourcePattern)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(edtSourcePattern, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(lblSizes)
                                                .addGap(18, 18, 18))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(lblFileName)
                                                .addGap(17, 17, 17)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(edtFileName, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                                            .addComponent(edtSizes))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnResetSizes)))
                        .addGap(0, 1, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSizes)
                    .addComponent(edtSizes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnResetSizes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFileName)
                    .addComponent(edtFileName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edtSourcePattern, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSourcePattern))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnColorConfig)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnRandom)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblStep)
                                .addGap(21, 21, 21))
                            .addComponent(lblRow))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCol))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnStart)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(barStep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(barRow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(barCol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnResetSizesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetSizesActionPerformed
        edtSizes.setText(STD_SIZES);
    }//GEN-LAST:event_btnResetSizesActionPerformed

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        if (!checkSizes(this, edtSizes)){
            return;
        }
        if (!checkFileName(this, edtFileName, false)){
            return;
        }
        if (!checkFileName(this, edtSourcePattern, true)){
            return;
        }
        if (!checkFileNameForVariables(this, edtSourcePattern)){
            return;
        }
        sizes = edtSizes.getText();
        fileName = edtFileName.getText();
        if (fileName.equals("")){
            fileName = "heatmap";
        }
        sourcePattern = edtSourcePattern.getText();
        
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        updateStepBar(barStep, bsmCalc);
        processImages();
        updateStepBar(barStep, bsmSave);
        savePNG(fileName);
        saveXLS(fileName);
        updateStepBar(barStep, bsmFinished);
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnStartActionPerformed

    private void btnRandomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRandomActionPerformed
        if (!checkFileName(this, edtFileName, false)){
            return;
        }  
        fileName = edtFileName.getText();
        if (fileName.equals("")){
            fileName = "random";
        }
        
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        updateStepBar(barStep, bsmCalc);
        processRandom();
        updateStepBar(barStep, bsmSave);
        savePNG(fileName);
        saveXLS(fileName);
        updateStepBar(barStep, bsmFinished);
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnRandomActionPerformed

    private void btnColorConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColorConfigActionPerformed
        ColorConfig dlgCC = new ColorConfig(this, true);
        dlgCC.setup();
        dlgCC.setVisible(true);
    }//GEN-LAST:event_btnColorConfigActionPerformed
    
    private void processImages(){
        PNGProcess pp = new PNGProcess();
        double D;
        for (int x = 0; x < IMG_SIZE_PX; x++){
            for (int y = 0; y < IMG_SIZE_PX; y++){
                updateGridBar(barCol, y); //Performanceintensiv, hier aber nicht so wichtig
                pp.reset();
                pp.setup(x, y);
                D = pp.getDimension();
                mapDimension[x][y] = D;
                aimBild[x][y] = DimensionToColor(D);
            }
            updateGridBar(barRow, x);
        }  
        updateGridBar(barCol, IMG_SIZE_PX);
        updateGridBar(barRow, IMG_SIZE_PX); 
    }
    
    /*
        Excel ist in der Lage, eine so formatierten Datei
        (Tabulator zur Spaltentrennung; Zeilenumbruch zur Reihentrennung)
        zu importieren.
    */
    private void saveXLS(String _fileName){
        String fileName = _fileName + ".xls";
        Path pXLS = Paths.get(fileName);  
        List<String> saveLines = new ArrayList<>(); 
        for (int x = 0; x < mapDimension.length; x++){
            StringBuilder sb = new StringBuilder();
            for (int y = 0; y < mapDimension[x].length; y++){
                if (y!=0){
                    sb.append("\t");
                }
                sb.append(formatDimensionString(mapDimension[x][y]));
            }
            saveLines.add(sb.toString());
        }
        try{
            Files.write(pXLS, saveLines, Charset.forName("UTF-8"));
        }
        catch (IOException e){
            Logger logger = Logger.getAnonymousLogger();
            logger.log(Level.SEVERE, "Datei " + fileName + " konnte nicht gespeichert werden!", e);
        }
    }
    
    private void savePNG(String _fileName){
        String fileName = _fileName + ".png";
        File fPNG = new File(fileName);
        BufferedImage im = new BufferedImage(IMG_SIZE_PX, IMG_SIZE_PX, TYPE_INT_RGB);
        for (int x = 0; x < IMG_SIZE_PX; x++){
            for (int y = 0; y < IMG_SIZE_PX; y++){
                //updateGridBar(barCol, y); frisst zuviel Performance
                int rgb = aimBild[x][y].getRGB();
                im.setRGB(x, y, rgb);
            }
            updateGridBar(barRow, x);
        }
        updateGridBar(barCol, IMG_SIZE_PX);
        updateGridBar(barRow, IMG_SIZE_PX); 

        try {
            ImageIO.write(im, "PNG", fPNG);        
        }
        catch (IOException e) {
            Logger logger = Logger.getAnonymousLogger();
            logger.log(Level.SEVERE, "Datei " + fPNG.getPath() + " konnte nicht gespeichert werden!", e);
        }
        zeigeNachricht(this, "Datei " + fileName + " wurde erzeugt!", "Fertig!", false, null);
    }
    
    private void processRandom(){
        Random r = new Random();
        double rnd;
        for (int x = 0; x < IMG_SIZE_PX; x++){
            for (int y = 0; y < IMG_SIZE_PX; y++){
                if (r.nextInt(1000) == 1){ //frisst zuviel Performance, aber da eh nur zu Testzwecken und zufallsbasiert gönn ich mir den Spaß (:
                    updateGridBar(barCol, y);
                }
                rnd = r.nextDouble() + 1.0;
                mapDimension[x][y] = rnd;
                aimBild[x][y] = DimensionToColor(rnd);
            }
            updateGridBar(barRow, x);
        } 
        updateGridBar(barCol, IMG_SIZE_PX);
        updateGridBar(barRow, IMG_SIZE_PX);    
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BoxCountMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BoxCountMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BoxCountMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BoxCountMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BoxCountMainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar barCol;
    private javax.swing.JProgressBar barRow;
    private javax.swing.JProgressBar barStep;
    private javax.swing.JButton btnColorConfig;
    private javax.swing.JButton btnRandom;
    private javax.swing.JButton btnResetSizes;
    private javax.swing.JButton btnStart;
    private javax.swing.JTextField edtFileName;
    private javax.swing.JTextField edtSizes;
    private javax.swing.JTextField edtSourcePattern;
    private javax.swing.JLabel lblCol;
    private javax.swing.JLabel lblFileName;
    private javax.swing.JLabel lblRow;
    private javax.swing.JLabel lblSizes;
    private javax.swing.JLabel lblSourcePattern;
    private javax.swing.JLabel lblStep;
    // End of variables declaration//GEN-END:variables
}
