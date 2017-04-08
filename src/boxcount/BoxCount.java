/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boxcount;

import java.awt.Color;

/**
 *
 * @author Simon
 */
public class BoxCount {
    
    static final int IMG_SIZE_PX = 512;
    static String directory = "edges/";
    static String fileName = "";
    static double[][] mapDimension = new double[IMG_SIZE_PX][IMG_SIZE_PX];
    static Color[][] aimBild = new Color[IMG_SIZE_PX][IMG_SIZE_PX];
    
    static final String STD_SIZES = "2,4,6,8,16,32,64";
    static String sizes = STD_SIZES;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BoxCountMainFrame frmMain = new BoxCountMainFrame();
        frmMain.setup();
        frmMain.setVisible(true);
    }
    
}
