/*
 * MyBooProjectApp.java
 */

package mybooproject;

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;
import gnu.io.*;
import java.io.IOException;

/**
 * The main class of the application.
 */
public class MyBooProjectApp extends SingleFrameApplication {

    private MyBooProjectView mainView;
    
    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        show(mainView = new MyBooProjectView(this));
    }

    public MyBooProjectView getMainAppView()
    {
        return this.mainView;
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
	main.connect("/dev/tty.usbmodem1a21");
        
	System.out.println("Started");
        launch(MyBooProjectApp.class, args);
        MyBooNeeds.getInstance().food();
        MyBooNeeds.getInstance().pooh();
        MyBooNeeds.getInstance().poohMaker();
        MyBooNeeds.getInstance().game();
        MyBooNeeds.getInstance().imgAutoUpdater();
    }

    @Action
    public void lightLEDGreen() {
        ArduinoActions.getInstance().setInstrution(3);
        System.out.println("J'allume la LED Verte");
        
    }
    
    @Action
    public void lightLEDRed() {
        ArduinoActions.getInstance().setInstrution(1);
        System.out.println("J'allume la LED Rouge");
        
    }
    
    @Action
    public void lightLEDYellow() {
        ArduinoActions.getInstance().setInstrution(2);
        System.out.println("J'allume la LED Jaune");
        
    }
    
    @Action
    public void turnOffLEDGreen() {
        ArduinoActions.getInstance().setInstrution(4);
        System.out.println("J'éteind la LED Verte");
        
    }
    
    @Action
    public void turnOffLEDRed() {
        ArduinoActions.getInstance().setInstrution(5);
        System.out.println("J'éteind la LED Rouge");
        
    }
    
    @Action
    public void turnOffLEDYellow() {
        ArduinoActions.getInstance().setInstrution(6);
        System.out.println("J'éteind la LED Jaune");
        
    }
}
