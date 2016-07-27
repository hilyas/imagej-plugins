import ij.*;
import ij.plugin.filter.PlugInFilter;
import ij.process.*;
import java.util.Arrays;
import ij.ImagePlus;
import ij.gui.NewImage;
import ij.plugin.PlugIn;

public class Arbitrary_Filter_Border_Handling implements PlugInFilter {

    public int setup(String arg, ImagePlus imp) {
        return DOES_8G; //accepts 8 bit grayscale images.
    }

    public void run(ImageProcessor orig) {
        int M = orig.getWidth();
        int N = orig.getHeight(); 

        //filter matrix of size (2K + 1)x(2L + 1)
        int[][] filter = {
        	{0,0,1,1,1,0,0},
        	{0,1,1,1,1,1,0},
        	{1,1,1,1,1,1,1},
        	{0,1,1,1,1,1,0},
        	{0,0,1,1,1,0,0}
        };
        double s = 1.0/23; //sun of filter coefficient is 23

        int K = filter[0].length/2; // K = 3
        int L = filter.length/2;    // L = 2

        ImageProcessor copy = orig.duplicate();

        //for (int v = L; v <= N-L; v++){
        for (int v = 0; v <= N; v++){
        //for (int v = 2; v <= N-2; v++){
            //for (int u = K; u <= M-K-1; u++){
            for (int u = 0; u <= M; u++){
            //for (int u = 3; u <= M-3-1; u++){
        		// computer filter result for position (u,v)
        		int sum = 0;
        		for (int j = -L; j <= L; j++){
                //for (int j = -2; j <= 2; j++){
        			for (int i = -K; i <= K; i++){
                    //for (int i = -3; i <= 3; i++){
                        int p = 0;
                        int c = 0;
        
                        if (u+i > M) {
                            p = copy.getPixel(u+i-K,v+j);
                        } 
                        else if (v+j > N) {
                            p = copy.getPixel(u+i,v+j-L);
                        } 
                        else {
                            p = copy.getPixel(u+i,v+j);
                        }

        				c = filter[j+2][i+3]; 
        				sum = sum + c * p;
        			}
        		}
                int q = 0;
        		q = (int) Math.round(s * sum);
        		if (q < 0) q = 0;
        		if (q > 255) q = 255;
        		orig.putPixel(u, v, q);
        	}
        }
    }
}