import java.util.Arrays;
import ij.ImagePlus;
import ij.*;
import ij.plugin.filter.PlugInFilter;
import ij.process.*;

public class Filter_Sobel implements PlugInFilter {

	public int setup(String arg, ImagePlus imp) {
        return DOES_8G; //accepts 8 bit grayscale images.
    }

	public void run(ImageProcessor orig) {
        int w = orig.getWidth();
        int h = orig.getHeight(); 
        // 3x3 filter matrix for Sobel
        double [][] x_filter = {
            {-1,0,1},
            {-2,0,2},
            {-1,0,1},
        };

        double [][] y_filter = {
            {-1,-2,-1},
            {0,0,0},
            {1,2,1},
        };
        double s = 1.0/8.0; //sun of filter coefficient is 8

        ImageProcessor copy = orig.duplicate();

        for (int v=1; v<=h-2; v++) {
            for (int u=1; u<=w-2; u++) {
                //compute filter result for position (u,v)
                //double sum = 0;
                double Dx = 0;
                double Dy = 0;
                double euvcalc = 0;

                for (int j=-1; j<=1; j++) {
                    for (int i=-1; i<=1; i++) {
                        int p = copy.getPixel(u+i,v+j);
						// get the corresponding filter coefficient:
                        //double c = filter[j+1][i+1];
                        double x = x_filter[j+1][i+1];
                        double y = y_filter[j+1][i+1];
                        //sum = sum + c * p;
                        Dx = Dx + x * p;
                        Dy = Dy + y * p;
                        euvcalc = Math.sqrt(Dx*Dx + Dy*Dy);
                    }
                }
                // int q = (int) (sum / 9.0);
                //int q = (int) Math.round(sum);
                int q = (int) Math.round(s*euvcalc);
                orig.putPixel(u,v,q);  
            }
        }

        //invert the image
        for (int u = 0; u < w; u++) {
			for (int v = 0; v < h; v++) {
				int p = orig.getPixel(u, v);
				orig.putPixel(u, v, 255 - p); // invert
			}
		}
    }

    
}