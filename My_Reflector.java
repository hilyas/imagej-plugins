import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

public class My_Reflector implements PlugInFilter {

	public int setup(String arg, ImagePlus im) {
		return DOES_8G; // this plugin accepts 8-bit grayscale images 
	}

	public void run(ImageProcessor ip) {
		int w = ip.getWidth();
		int h = ip.getHeight();

		// iterate over all image coordinates
		for (int u = 0; u < w/2; u++) {
			for (int v = 0; v < h; v++) {
				int p1 = ip.getPixel(u, v);
				int p2 = ip.getPixel(w - u, v);
				ip.putPixel(u, v, p2);
				ip.putPixel(w - u, v, p1);
			}
		}
	}

} // end of class
