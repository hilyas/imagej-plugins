import ij.*;
import ij.gui.*;
import ij.plugin.PlugIn;
import java.awt.*;

public class Find_Min implements PlugIn {

    public void run(String arg) {
            
        //double[] sample3 = {-2.152,5.123,5.011,6.624,1.856,7.445,4.307,0.029};
        //double min = sample3[0];

        // double[][] matrix = {{8.547, 0.372, 9.046, 5.217, 5.414},
        //                      {0.304, 8.398, 6.271, 7.338, 2.729},
        //                      {7.719, 0.867, 2.352, 3.876, 0.213}};

        matrix = new double[3][5];

        int w = matrix[0].length;
        int h = matrix.length;
        double min = matrix[0][0];
        double max = matrix[0][0];

        for (int i = 0 ; i < h; i++) {
                for (int j = 0; j < w ; j++ ) {
                        matrix[i][j] = Math.random(); 
                }
        }

        String wstr = Integer.toString(w);
        IJ.showMessage("width", wstr);
        String hstr = Integer.toString(h);
        IJ.showMessage("width", hstr);

        for (int i = 0 ; i < h; i++) {
                for (int j = 0; j < w ; j++ ) {
                        if (matrix[i][j] < min) { 
                                min = matrix[i][j]; 
                        }       
                        if (matrix[i][j] > max) { 
                                max = matrix[i][j]; 
                        }       
                }
        }

        double diff = max - min;

        String minstr = Double.toString(min);
        IJ.showMessage("Minimum value in array", minstr);
        String maxstr = Double.toString(max);
        IJ.showMessage("Maximum value in array", maxstr);
        String diffstr = Double.toString(diff);
        IJ.showMessage("Difference between max and min", diffstr);
        


    }

}