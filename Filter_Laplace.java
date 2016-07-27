import ij.*;
import ij.plugin.filter.PlugInFilter;
import ij.process.*;
import java.util.Arrays;

public class Filter_Laplace implements PlugInFilter {

    public int setup(String arg, ImagePlus imp) {
        return DOES_8G; //accepts 8 bit grayscale images.
    }

    public void run(ImageProcessor orig) {
        int M = orig.getWidth();
        int N = orig.getHeight(); 

        //filter matrix of size (2K + 1)x(2L + 1)
        int[][] filter = {
            {0,1,0},
            {1,-4,1},
            {0,1,0},
        };
        double s = 1.0/8; //sun of filter coefficient is 8

        // int K = filter[0].length/2;
        // int L = filter.length/2;

        ImageProcessor copy = orig.duplicate();

        // for (int v = L; v <= N-L; v++){
        //     for (int u = K; u <= M-K-1; u++){
        for (int v=1; v<=N-2; v++) {
            for (int u=1; u<=M-2; u++) {
                // computer filter result for position (u,v)
                int sum = 0;
                // for (int j = -L; j <= L; j++){
                //     for (int i = -K; i <= K; i++){
                for (int j=-1; j<=1; j++) {
                    for (int i=-1; i<=1; i++) {
                        int p = orig.getPixel(u+i, v+j);
                        // int c = filter[j+L][i+K];
                        int c = filter[j+1][i+1];
                        sum = sum + c * p;
                    }
                }
                
                int q1 = (int) Math.round(s * sum);
                copy.putPixel(u, v, q1);

                double p1 = orig.getPixel(u, v);
                double p2 = copy.getPixel(u, v);
                double diff = p1 - 5 * p2;
                int q2 = (int) Math.round(diff);
                orig.putPixel(u, v, q2);
            }
        }

    }
}