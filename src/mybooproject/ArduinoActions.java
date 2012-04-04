package mybooproject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.jdesktop.application.Action;

/**
 *
 * @author renaudjenny
 */
public class ArduinoActions implements ActionListener {

    private static ArduinoActions instance = null;
    private int  instruction = -9;

    public static synchronized ArduinoActions getInstance() {
        if (instance == null) {
            instance = new ArduinoActions();
        }
        return instance;
    }

    public void actionPerformed(ActionEvent ae) {
        System.out.println("test");
    }
    
    public synchronized int getInstruction()
    {
        return this.instruction;
    }
    
    public synchronized void setInstrution(int newInstruction)
    {
        this.instruction = newInstruction;
    }
}
