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

import static boxcount.BoxCount.IMG_SIZE_PX;
import ij.IJ;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;


public class BCUtil {      
    private static final String FILENAME_ALPHABET = "abcdefghijklmnopqrstuvwxyzäöüß _-ABCDEFGHIJKLMNOPQRSTUVWXYZÄÖÜ0123456789";
    
    static void zeigeFehler(Component _parent, String _text, String _title, boolean _beep, Component _cmpFocus){
        zeigeDialog(_parent, _text, _title, JOptionPane.ERROR_MESSAGE, _beep, _cmpFocus); 
    }
    
    static void zeigeNachricht(Component _parent, String _text, String _title, boolean _beep, Component _cmpFocus){
        zeigeDialog(_parent, _text, _title, JOptionPane.PLAIN_MESSAGE, _beep, _cmpFocus); 
    }
    
    private static void zeigeDialog(Component _parent, String _text, String _title, int _messageType, boolean _beep, Component _cmpFocus){
        if (_beep) Toolkit.getDefaultToolkit().beep();
        JOptionPane.showMessageDialog(_parent, _text, _title, JOptionPane.PLAIN_MESSAGE); 
        if (_cmpFocus != null){
            _cmpFocus.requestFocus();
        }
    }
    
    static Color DimensionToColor(double _d){
        if (_d < 1.001) return Color.WHITE;
        if (_d < 1.125) return Color.BLUE;
        if (_d < 1.25) return Color.CYAN;
        if (_d < 1.375) return Color.GREEN;
        if (_d < 1.5) return Color.YELLOW;
        if (_d < 1.625) return Color.ORANGE;
        if (_d < 1.75) return Color.RED;
        if (_d < 1.875) return Color.MAGENTA;
        else return Color.PINK;
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
    
    static boolean checkFileName(Component _parent, JTextField _edt){
        String name = _edt.getText().trim();
        for (int i = 0; i < name.length(); i++){
            String c = Character.toString(name.charAt(i));
            if (!FILENAME_ALPHABET.contains(c)){
                zeigeFehler(_parent, "Dateiname enthält ungültige Zeichen!", "Ungültiger Dateiname", true, _edt);
                return false;
            }
        }
        return true;        
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
            case bsmSave: {
                _bar.setValue(1);
                _bar.setString("Erzeugt PNG...");
                break;
            }
            case bsmFinished: {
                _bar.setValue(2);
                _bar.setString("Fertig!");
                break;
            }
        }
    }
    
    static void updateGridBar(JProgressBar _bar, int _value){
        _bar.setValue(_value);
        _bar.setString(_value + "/" + IMG_SIZE_PX);
    }
}

