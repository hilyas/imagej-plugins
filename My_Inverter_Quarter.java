
import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

public class My_Inverter_Quarter implements PlugInFilter {

	public int setup(String arg, ImagePlus im) {
		return DOES_8G; // this plugin accepts 8-bit grayscale images 
	}

	public void run(ImageProcessor ip) {
		int w = ip.getWidth();
		int h = ip.getHeight();

		// iterate over all image coordinates
		for (int u = 0; u < w/2; u++) {
			for (int v = 0; v < h/2; v++) {
				int p = ip.getPixel(u, v);
				ip.putPixel(u, v, 255 - p); // invert
			}
		}
	}

} // end of class
