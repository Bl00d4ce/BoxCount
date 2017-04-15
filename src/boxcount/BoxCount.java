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
    static final String SOURCE_DIRECTORY = "edges/";
    static String fileName = "";
    static double[][] mapDimension = new double[IMG_SIZE_PX][IMG_SIZE_PX];
    static Color[][] aimBild = new Color[IMG_SIZE_PX][IMG_SIZE_PX];
    
    static final String STD_SIZES = "2,4,6,8,16,32,64";
    static String sizes = STD_SIZES;
    static final double[] STD_COLOR_LIMITS = {1.001, 1.125, 1.25, 1.375, 1.5, 1.625, 1.75, 1.875};
    static double[] colorLimits = STD_COLOR_LIMITS.clone();
    static final String ORIG_SOURCE_PATTERN = "9_{x}_{y}.png";
    static String sourcePattern = ORIG_SOURCE_PATTERN;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BoxCountMainFrame frmMain = new BoxCountMainFrame();
        frmMain.setup();
        frmMain.setVisible(true);
    }
    
}
