/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boxcount;

import static boxcount.BoxCount.sizes;
import ij.ImagePlus;
import ij.plugin.filter.FractalBoxCounter;
import ij.process.ImageProcessor;
import ij.io.Opener;
import static boxcount.BoxCount.SOURCE_DIRECTORY;
import static boxcount.BoxCount.sourcePattern;

/**
 *
 * @author Simon
 */
public class PNGProcess {
    private static final Opener FILE_OPENER = new Opener();
    
    private ImagePlus imp;
    private ImageProcessor ip;
    private final FractalBoxCounter fbc;
    
    PNGProcess(){
        imp = null;
        ip = null;
        fbc = new FractalBoxCounter();
    }
    
    void reset(){
        imp = null;
        ip = null;
    }
    
    void setup(int x, int y){
        String filename = sourcePattern.replaceAll("\\{x\\}", Integer.toString(x)).replaceAll("\\{y\\}", Integer.toString(y));
        imp = FILE_OPENER.openImage(SOURCE_DIRECTORY, filename);
        fbc.setup("", imp);
        ip = imp.getProcessor();
    }
    
    double getDimension(){
        return fbc.DimensionFromBoxCounts(ip, sizes);
    }
}
