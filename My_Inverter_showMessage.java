import ij.*;
import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

public class My_Inverter_showMessage implements PlugInFilter {

	public int setup(String arg, ImagePlus im) {
		return DOES_8G; // this plugin accepts 8-bit grayscale images 
	}

	public void run(ImageProcessor ip) {
		int w = ip.getWidth();
		int h = ip.getHeight();

		String wstring = Integer.toString(w);
		String hstring = Integer.toString(h);

		IJ.showMessage("Width: ", wstring);	
		IJ.showMessage("Height: ", hstring);	
		
		// iterate over all image coordinates
		for (int u = 0; u < w; u++) {
			for (int v = 0; v < h; v++) {
				int p = ip.getPixel(u, v);
				ip.putPixel(u, v, 255 - p); // invert
			}
		}
	}

} // end of class
