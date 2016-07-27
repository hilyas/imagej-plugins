import ij.ImagePlus;
import ij.*;
import ij.plugin.filter.PlugInFilter;
import ij.process.*;

public class Filter_Average3x3_2 implements PlugInFilter {

    public int setup(String arg, ImagePlus imp) {
        return DOES_8G; //accepts 8 bit grayscale images.
    }

    public void run(ImageProcessor orig) {
        int w = orig.getWidth();
        int h = orig.getHeight(); 
        // 3x3 filter matrix
        double [][] filter = {
            {0.075, 0.125, 0.075},
            {0.125, 0.200, 0.125},
            {0.075, 0.125, 0.075},
        };
        ImageProcessor copy = orig.duplicate();

        // for (int v=1; v<=h-2; v++) {
        //     for (int u=1; u<=w-2; u++) {
        for (int v=0; v<=h-1; v++) {
            for (int u=0; u<=w-1; u++) {
                //compute filter result for position (u,v)
                double sum = 0;
                for (int j=-1; j<=1; j++) {
                    for (int i=-1; i<=1; i++) {
                        int p = copy.getPixel(u+i,v+j);
                        if (p < 0) p = 0;  //take care of border
			// get the corresponding filter coefficient:
                        double c = filter[j+1][i+1];
                        sum = sum +c * p;
                    }
                }
                // int q = (int) (sum / 9.0);
                int q = (int) Math.round(sum);
                orig.putPixel(u,v,q);  
            }
        }
    }

    
}