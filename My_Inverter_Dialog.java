import ij.*;
import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;
import ij.gui.GenericDialog;
import ij.gui.NewImage;
import ij.plugin.PlugIn;

public class My_Inverter_Dialog implements PlugInFilter {

	public int setup(String arg, ImagePlus im) {
		return DOES_8G; // this plugin accepts 8-bit grayscale images 
	}

	public void run(ImageProcessor ip) {
		int w = ip.getWidth();
		int h = ip.getHeight();

		String wstring = Integer.toString(w);
		String hstring = Integer.toString(h);

		int x1 = 0;
        int x2 = w;
        int y1 = 0;
        int y2 = h;
        
        GenericDialog gd = new GenericDialog("My Inverter Dialog");
        gd.addStringField("Image Width (delta x): ", wstring);
        gd.addStringField("Image Height (delta y): ", hstring);
        gd.addNumericField("Enter x1: ", x1, 0);
        gd.addNumericField("Enter x2: ", x2, 0);
        gd.addNumericField("Enter y1: ", y1, 0);
        gd.addNumericField("Enter y2: ", y2, 0);
        gd.showDialog();
        if (gd.wasCanceled())
            return;
        x1 = (int) gd.getNextNumber();
        x2 = (int) gd.getNextNumber();
        y1 = (int) gd.getNextNumber();
        y2 = (int) gd.getNextNumber();

		// iterate over given ROI coordinates
		for (int u = x1; u < x2; u++) {
			for (int v = y1; v < y2; v++) {
				int p = ip.getPixel(u, v);
				ip.putPixel(u, v, 255 - p); // invert ROI
			}
		}
	}

} // end of class
