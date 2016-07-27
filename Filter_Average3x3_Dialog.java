
import ij.ImagePlus;
import ij.gui.GenericDialog;
import ij.gui.NewImage;
import ij.plugin.PlugIn;
import ij.*;
import ij.plugin.filter.PlugInFilter;
import ij.process.*;

public class Filter_Average3x3_Dialog implements PlugInFilter {

    public int setup(String arg, ImagePlus imp) {
        return DOES_8G; //accepts 8 bit grayscale images.
    }

    public void run(ImageProcessor orig) {
        int w = orig.getWidth();
        int h = orig.getHeight();  
        
        //String title = "New Filter";
        int center = 8;
        int middle = 5;
        int corner = 3;
        double scaling = 1;


        GenericDialog gd = new GenericDialog("New Filter");
        //gd.addStringField("Welcome! ", title);
        gd.addNumericField("center: ", center, 0);
        gd.addNumericField("middle: ", middle, 0);
        gd.addNumericField("corner: ", corner, 0);
        gd.addNumericField("scaling: ", scaling, 0);
        gd.showDialog();
        if (gd.wasCanceled())
            return;
        //title = gd.getNextString();
        center = (int) gd.getNextNumber();
        middle = (int) gd.getNextNumber();
        corner = (int) gd.getNextNumber();
        scaling = (int) gd.getNextNumber();

        // 3x3 filter matrix
        double [][] filter = {
            {corner, middle, corner},
            {middle, center, middle},
            {corner, middle, corner},
        };

        ImageProcessor copy = orig.duplicate();

        for (int v=1; v<=h-2; v++) {
            for (int u=1; u<=w-2; u++) {
                //compute filter result for position (u,v)
                double sum = 0;
                for (int j=-1; j<=1; j++) {
                    for (int i=-1; i<=1; i++) {
                        int p = copy.getPixel(u+i,v+j);
                        // get the corresponding filter coefficient:
                        double c = filter[j+1][i+1];
                        double scaled_c = c/scaling;
                        sum = sum + scaled_c * p;
                    }
                }
                int q = (int) Math.round(sum);
                orig.putPixel(u,v,q);  
            }
        }
    }

    
}