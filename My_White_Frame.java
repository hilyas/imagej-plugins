import ij.*;
import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

public class My_White_Frame implements PlugInFilter {

	public int setup(String arg, ImagePlus im) {
		return DOES_8G; // this plugin accepts 8-bit grayscale images 
	}

	public void run(ImageProcessor ip) {
		int w = ip.getWidth();
		int h = ip.getHeight();

		int frame_width = 10;
		int pixel_intensity = 255;

		for (int u = 0; u < frame_width; u++) {
			for (int v = 0; v < h; v++) {
				int p = ip.getPixel(u, v);
				ip.putPixel(u, v, pixel_intensity); 
			}
		}

		for (int u = w-frame_width; u < w; u++) {
			for (int v = 0; v < h; v++) {
				int p = ip.getPixel(u, v);
				ip.putPixel(u, v, pixel_intensity); 
			}
		}

		for (int u = 0; u < w; u++) {
			for (int v = 0; v < frame_width; v++) {
				int p = ip.getPixel(u, v);
				ip.putPixel(u, v, pixel_intensity); 
			}
		}

		for (int u = 0; u < w; u++) {
			for (int v = h-frame_width; v < h; v++) {
				int p = ip.getPixel(u, v);
				ip.putPixel(u, v, pixel_intensity);
			}
		}

	}

} // end of class
