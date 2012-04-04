/*
 * MyBooProjectApp.java
 */

package mybooproject;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;
import gnu.io.*;
import java.io.IOException;

/**
 * The main class of the application.
 */
public class MyBooProjectApp extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        show(new MyBooProjectView(this));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of MyBooProjectApp
     */
    public static MyBooProjectApp getApplication() {
        return Application.getInstance(MyBooProjectApp.class);
    }

    public static void main(String[] args) throws IOException, Exception {
        // TODO code application logic here
        SerialComm main = new SerialComm();
	main.connect("/dev/tty.usbmodem1d11");
        
	System.out.println("Started");
        launch(MyBooProjectApp.class, args);
    }
}
