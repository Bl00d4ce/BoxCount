/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boxcount;

/**
 *
 * @author Simon
 */

import static boxcount.BoxCount.colorLimits;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import java.util.Locale;
import static boxcount.BoxCount.IMG_SIZE_PX_X;
import static boxcount.BoxCount.IMG_SIZE_PX_Y;
import static java.lang.Thread.sleep;


public class BCUtil {      
    private static final String FILENAME_ALPHABET = "abcdefghijklmnopqrstuvwxyzäöüß _-()[]{}ABCDEFGHIJKLMNOPQRSTUVWXYZÄÖÜ0123456789";
    
    static void zeigeFehler(Component _parent, String _text, String _title, boolean _beep, Component _cmpFocus){
        zeigeDialog(_parent, _text, _title, JOptionPane.ERROR_MESSAGE, _beep, _cmpFocus); 
    }
    
    static void zeigeNachricht(Component _parent, String _text, String _title, boolean _beep, Component _cmpFocus){
        zeigeDialog(_parent, _text, _title, JOptionPane.PLAIN_MESSAGE, _beep, _cmpFocus); 
    }
    
    static boolean zeigeJNDialog(Component _parent, String _text, String _title, boolean _beep, Component _cmpFocus){
        if (_beep) Toolkit.getDefaultToolkit().beep();
        int choice = JOptionPane.showConfirmDialog(_parent, _text, _title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
        if (choice == 2){
            _cmpFocus.requestFocus();
            return false;
        }
        return true;
    }
    
    private static void zeigeDialog(Component _parent, String _text, String _title, int _messageType, boolean _beep, Component _cmpFocus){
        if (_beep) Toolkit.getDefaultToolkit().beep();
        JOptionPane.showMessageDialog(_parent, _text, _title, _messageType); 
        if (_cmpFocus != null){
            _cmpFocus.requestFocus();
        }
    }
    
    static Color DimensionToColor(double _d){
        if (_d < colorLimits[0]) return Color.WHITE;
        if (_d < colorLimits[1]) return Color.BLUE;
        if (_d < colorLimits[2]) return Color.CYAN;
        if (_d < colorLimits[3]) return Color.GREEN;
        if (_d < colorLimits[4]) return Color.YELLOW;
        if (_d < colorLimits[5]) return Color.ORANGE;
        if (_d < colorLimits[6]) return Color.RED;
        if (_d < colorLimits[7]) return Color.MAGENTA;
        else return Color.PINK;
    }
    
    static String formatDimensionString(double _d){
        return String.format(Locale.US, "%.4f", _d);
    }
    
    static boolean checkDims(Component _parent, JTextField _edtX, JTextField _edtY){
        return checkDim(_parent, _edtX) && checkDim(_parent, _edtY);
    }
    
    private static boolean checkDim(Component _parent, JTextField _edt){
        try{
            Integer.parseInt(_edt.getText());
        }
        catch (NumberFormatException e){
            zeigeFehler(_parent, "Eingabe für Bildgröße kann nicht eingelesen werden!", "Ungültige Bildgröße", true, _edt);
            return false;
        }
        return true;
    } 
    
    static boolean checkSizes(Component _parent, JTextField _edt){
        int[] arrSizes;
        try{
            arrSizes = StringToIntArray(_edt.getText());
        }
        catch(NumberFormatException e){
            zeigeFehler(_parent, "Eingabe für Boxgrößen kann nicht eingelesen werden!", "Ungültige Boxgrößen", true, _edt);
            return false;
        }
        for (int i = 0; i < arrSizes.length - 1; i++){
            if (arrSizes[i] >= arrSizes[i + 1]){
                zeigeFehler(_parent, "Boxgrößen sind nicht steigend!", "Ungültige Boxgrößen", true, _edt);
                return false;
            }
        }
        return true;
    }
    
    static boolean checkFileName(Component _parent, JTextField _edt, boolean _bExtension){
        String name = _edt.getText().trim();
        String alphabet = FILENAME_ALPHABET + (_bExtension ? "." : "");
        for (int i = 0; i < name.length(); i++){
            String c = Character.toString(name.charAt(i));
            if (!alphabet.contains(c)){
                zeigeFehler(_parent, "Dateiname enthält ungültige Zeichen!", "Ungültiger Dateiname", true, _edt);
                return false;
            }
        }
        if (_bExtension && (name.indexOf(".") != name.lastIndexOf("."))){
            zeigeFehler(_parent, "Dateiname muss genau eine Endung enthalten", "Ungültiger Dateiname", true, _edt);
            return false;
        }
        return true;        
    }
    
    static boolean checkColorLimits(Component _parent, JTextField[] _edits){
        double[] arrD = new double[_edits.length];
        for (int i = 0; i < _edits.length; i++){
            try{
                arrD[i] = Double.parseDouble(_edits[i].getText());
            }
            catch (NumberFormatException e){
                zeigeFehler(_parent, "Eingabe kann nicht in einen Gleitkommawert übertragen werden!", "Ungültige Eingabe", true, _edits[i]);
                return false;
            }
        }
        
        for (int i = 0; i < _edits.length; i++){
            if ((arrD[i] < 1.0) || (arrD[i] > 2.0)){
                zeigeFehler(_parent, "Eingabe liegt nicht zwischen 1 & 2!", "Nicht erlaubter Wert", true, _edits[i]);
                return false;
            }
        }
        
        for (int i = 1; i < _edits.length; i++){
            if (arrD[i] < arrD[i - 1]){
                zeigeFehler(_parent, "Eingabe ist kleiner als vorheriges Feld", "Nicht erlaubter Wert", true, _edits[i]);
                return false;
            }
        }
        return true;
    }
    
    static boolean checkFileNameForVariables(Component _parent, JTextField _edt){
        String name = _edt.getText();
        boolean bX = name.contains("{x}");
        boolean bY = name.contains("{y}");
        if (bX && bY){
            return true;
        }
        String errMissingVar = "Folgende Variablen fehlen: " + 
                (!bX ? "{x}" : "") +
                ((!bX && !bY) ? ", " : "") +
                (!bY ? "{y}" : "") + "\n" +
                "Soll trotzdem fortgesetzt werden?";
        return zeigeJNDialog(_parent, errMissingVar, "Fehlende Variablen", false, _edt);
    }
    
    private static int[] StringToIntArray(String _s) throws NumberFormatException{
        StringTokenizer st = new StringTokenizer(_s, ", \t");
        int nInts = st.countTokens();
        int[] ints = new int[nInts];
        for(int i=0; i<nInts; i++) {
            ints[i] = Integer.parseInt(st.nextToken());    
        }
        return ints;
    }
    
    static void initProgressBar(JProgressBar _bar, int _min, int _max){
        _bar.setMinimum(_min);
        _bar.setMaximum(_max);
        _bar.setValue(_min);
        _bar.setStringPainted(true);
    }    
    
    static void updateStepBar(JProgressBar _bar, barStepMode _bsm){
        switch (_bsm){
            case bsmInactive: {
                _bar.setValue(0);
                _bar.setString("Inaktiv");
                break;
            }
            case bsmCalc: {
                _bar.setValue(0);
                _bar.setString("Berechnet Dimensionen...");
                break;
            }
            case bsmSavePNG: {
                _bar.setValue(1);
                _bar.setString("Erzeugt PNG...");
                break;
            }
            case bsmSaveXLS: {
                _bar.setValue(2);
                _bar.setString("Erzeugt XLS...");
                break;
            }
            case bsmFinished: {
                _bar.setValue(3);
                _bar.setString("Fertig!");
                break;
            }
        }
        _bar.update(_bar.getGraphics());
    }
    
    static void updateGridBar(JProgressBar _bar, int _value){
        _bar.setValue(_value);
        _bar.setString(_value + "/" + _bar.getMaximum());
        _bar.update(_bar.getGraphics());
    }
    
    static void silentSleep(long millis){
        try {
            sleep(1000);
        } 
        catch (InterruptedException ex) {
            Logger.getLogger(BCUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

