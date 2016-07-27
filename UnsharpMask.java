import gauss.GaussKernel1d;
import ij.IJ;
import ij.ImagePlus;
import ij.gui.GenericDialog;
import ij.plugin.filter.Convolver;
import ij.plugin.filter.PlugInFilter;
import ij.process.Blitter;
import ij.process.ImageProcessor;


public class Filter_UnsharpMask implements PlugInFilter {

    public int setup(String arg, ImagePlus imp) {
        return DOES_8G; //accepts 8 bit grayscale images.
    }

    public void unsharpMask(ImageProcessor ip, double sigma, double a) {
    	ImageProcessor I = ip.convertToFloat(); //I

    	//create a blurred version of the image
    	ImageProcessor J = I.duplicate();  // I~
    	float[] H = GaussKernelid.create(sigma);
    	Convolver cv = new Convolver();
    	cv.setNormalize(true);
    	cv.convolve(J,H,1,H.length);
    	c.convolve(J,H,H.length,1);
    	
    	I.multiply(1+a);   //I <- (1+a).I
    	J.multiply(a);     // I~ <- a.I~
    	I.copyBits(J,0,0,Blitter.SUBSTRACT);
    	
    	//copy result back into the original byte image
    	ip.insert(I.convertToByte(false),0,0);
    }
}