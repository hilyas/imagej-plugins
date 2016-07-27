import ij.IJ;
import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

public class Display_Demo implements PlugInFilter{
	ImagePlus im = null;

	public int setup(String arg, ImagePlus im){
		if (im == null){
			IJ.noImage();
			return DONE;
		}
		this.im = im; //keep reference to associated ImagePlus
		return DOES_ALL;
	}

	public void run(ImageProcessor ip){
		int w = ip.getWidth();
		int h = ip.getHeight();

		// iterate over all image coordinates
		for (int u = 0; u < w; u++) {
			for (int v = 0; v < h; v++) {
				int p = ip.getPixel(u, v);
				ip.putPixel(u, v, 255 - p); // invert
			}
		}

		for (int i = 0; i<8; i++){
			//modify this image
			//ip.smooth();
			ip.rotate(90);
			//redisplay this image
			im.updateAndDraw();
			//sleep 100 ms so user can watch
			IJ.wait(300);
		}
	}
}//end of class Display_Demo