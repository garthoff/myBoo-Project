/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mybooproject;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author renaudjenny
 */
public class MyBooNeeds {
    
    private static MyBooNeeds instance = null;
    
    public static synchronized MyBooNeeds getInstance()
    {
        if (instance == null)
        {
            instance = new MyBooNeeds();
        }
        return instance;
    }
    
    public void food()
    {
        new Thread(new Food()).start();
    }
    
    public void pooh()
    {
        new Thread(new Pooh()).start();
    }
    
    public void poohMaker()
    {
        new Thread(new Pooh.PoohMaker()).start();
    }
    
    public static class Food implements Runnable
    {
        public Food(){};

        public void run()
        {
            while (true)
            {
                double rondomMillis = (Math.random() * 8000.0) + 3000.0;
                try {
                    Thread.sleep((long)rondomMillis);
                    File usbKeyPath = new File("/Volumes/FOOD");
                    while (!usbKeyPath.isDirectory())
                    {
                        ArduinoActions.getInstance().setInstrution(1);
                        System.out.println("Feed me !");
                        Thread.sleep(2000);
                    }
                    ArduinoActions.getInstance().setInstrution(4);
                }
                catch (InterruptedException ex) {
                    Logger.getLogger(MyBooNeeds.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public static class Pooh implements Runnable
    {
        public Pooh(){};
        
        private static String[] poohLabels = {"Caca", "Prout", "Popo", "Fumier"};
        
        public class PoohFilter implements FilenameFilter
        {
            public boolean accept(File file, String string) 
            {
                for (int i = 0; i < poohLabels.length; i++)
                {
                    if (string.startsWith(poohLabels[i]))
                    {
                        return true;
                    }
                }
                return false;
            }
            
        }
        
        public void run()
        {
            File wc = new File("/Users/renaudjenny/Desktop/");
            while (true)
            {
                try {
                    Thread.sleep(2000);
                    while (wc.listFiles(new PoohFilter()).length > 0)
                    {
                        ArduinoActions.getInstance().setInstrution(2);
                        System.out.println("Y'a du caca sur le bureau ! Je ne suis pas content !!!");
                        Thread.sleep(2000);
                    }
                    ArduinoActions.getInstance().setInstrution(5);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MyBooNeeds.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            
        }
    
        
        public static class PoohMaker implements Runnable
        {
            public PoohMaker(){};
            
            public void run() {
                String wc = "/Users/renaudjenny/Desktop/";
                while (true)
                {
                    double rondomMillis = (Math.random() * 12000.0) + 4000.0;
                    try {
                        Thread.sleep((long)rondomMillis);
                        int poohRandomLabel = new Random().nextInt(poohLabels.length);
                        File pooh = new File(wc + poohLabels[poohRandomLabel]);
                        pooh.createNewFile();
                        System.out.println("J'ai fais un besoin sur le bureau");
                    } catch (Exception ex)
                    {
                        Logger.getLogger(MyBooNeeds.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
    
}
