import ij.*;
import ij.plugin.PlugIn;

public class Message_Test implements PlugIn {

	public void run(String arg) {
		IJ.showStatus("Plugin Message Test started.");
		IJ.showProgress(0.0);
		IJ.error("I need user input!");

		String name = IJ.getString("Please enter your name: ","I.J. User");
		IJ.showProgress(0.5);
		IJ.write("Starting sample plugin Red And Blue ... ");
		IJ.runPlugIn("Red_And_Blue","");
		IJ.showProgress(1.0);
		IJ.showMessage("Finished.",name+", thank you for running this plugin");
	}

}

