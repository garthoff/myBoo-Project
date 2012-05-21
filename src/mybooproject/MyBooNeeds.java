/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mybooproject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.*;
import javax.swing.JPanel;

/**
 *
 * @author renaudjenny
 */
public class MyBooNeeds {
    
    private static MyBooNeeds instance = null;
    
    private boolean needGame = false;
    private boolean needFood = false;
    private boolean needClean = false;
    
    public static synchronized MyBooNeeds getInstance()
    {
        if (instance == null)
        {
            instance = new MyBooNeeds();
        }
        return instance;
    }
    
    public synchronized boolean getNeedGame()
    {
        return this.needGame;
    }
    
    public synchronized void setNeedGame(boolean need)
    {
        this.needGame = need;
    }
    
    public synchronized boolean getNeedFood()
    {
        return this.needFood;
    }
    
    public synchronized void setNeedFood(boolean need)
    {
        this.needFood = need;
    }
    
    public synchronized boolean getNeedClean()
    {
        return this.needClean;
    }
    
    public synchronized void setNeedClean(boolean need)
    {
        this.needClean = need;
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
    
    public void game()
    {
        new Thread(new Game()).start();
    }
    
    public void waitingGame()
    {
        new Thread(new WaitingGame()).start();
    }
    
    public void imgAutoUpdater()
    {
        new Thread(new ImgAutoUpdater()).start();
    }
    
    public static class Food implements Runnable
    {
        public Food(){};

        public void run()
        {
            while (true)
            {
                double rondomMillis = (Math.random() * 8000.0) + 6000.0;
                try {
                    Thread.sleep((long)rondomMillis);
                    File usbKeyPath = new File("/Volumes/FOOD");
                    while (!usbKeyPath.isDirectory())
                    {
                        ArduinoActions.getInstance().setInstrution(1);
                        MyBooNeeds.getInstance().setNeedFood(true);
                        System.out.println("Feed me !");
                        Thread.sleep(2000);
                    }
                    ArduinoActions.getInstance().setInstrution(4);
                    MyBooNeeds.getInstance().setNeedFood(false);
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
        
        private static String[] poohLabels = {"Caca", "Prout", "Popo", "Pipi"};
        
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
                        MyBooNeeds.getInstance().setNeedClean(true);
                        System.out.println("Y'a du caca sur le bureau ! Je ne suis pas content !!!");
                        Thread.sleep(2000);
                    }
                    ArduinoActions.getInstance().setInstrution(5);
                    MyBooNeeds.getInstance().setNeedClean(false);
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
                    double rondomMillis = (Math.random() * 14000.0) + 6000.0;
                    try {
                        Thread.sleep((long)rondomMillis);
                        int poohRandomLabel = new Random().nextInt(poohLabels.length);
                        File pooh = new File(wc + poohLabels[poohRandomLabel]);
                        pooh.createNewFile();
                        System.out.println("J'ai fais " + poohLabels[poohRandomLabel] + " sur le bureau");
                    } catch (Exception ex)
                    {
                        Logger.getLogger(MyBooNeeds.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
    
    public static class Game implements Runnable
    {
        public Game() {};
        
        public void run()
        {
            while (true)
            {
                double rondomMillis = (Math.random() * 8000.0) + 6000.0;
                try {
                    Thread.sleep((long)rondomMillis);
                    MyBooNeeds.getInstance().setNeedGame(true);
                    ArduinoActions.getInstance().setInstrution(3);
                    System.out.println("J'ai envie de jouer !!! Joue avec moi !");

                    MyBooNeeds.getInstance().waitingGame();
                    
                    while (MyBooNeeds.getInstance().getNeedGame())
                    {
                        Thread.sleep(2000);
                    }
                    ArduinoActions.getInstance().setInstrution(6);
                }
                catch (InterruptedException ex) {
                    Logger.getLogger(MyBooNeeds.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public static class WaitingGame implements Runnable
    {
        public WaitingGame() {};
        
        public void run()
        {
            try {
                ServerSocket server = new ServerSocket(4321);
                Socket clientSocket = server.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out.println("play");
                if (in.readLine().equals("end"))
                {
                    out.close();
                    in.close();
                    clientSocket.close();
                    server.close();
                    MyBooNeeds.getInstance().setNeedGame(false);
                    System.out.println("Je n'ai plus envie de jouer");
                }
            } catch (Exception ex)
            {
                System.out.println("Interrupted connection with game");
            }
        }
    }
    
    public static class ImgAutoUpdater implements Runnable
    {
        public ImgAutoUpdater(){};

        public void run() {
            while (true)
            {
                try {
                    Thread.sleep(200);
                    if (MyBooProjectApp.getApplication().getMainAppView() != null)
                    {
                        if (MyBooNeeds.getInstance().getNeedFood())
                        {
                            MyBooProjectApp.getApplication().getMainAppView().setImg("food");
                        }
                        else if (MyBooNeeds.getInstance().getNeedClean())
                        {
                            MyBooProjectApp.getApplication().getMainAppView().setImg("pooh");
                        }
                        else if (MyBooNeeds.getInstance().getNeedGame())
                        {
                            MyBooProjectApp.getApplication().getMainAppView().setImg("play");
                        }
                        else
                        {
                            MyBooProjectApp.getApplication().getMainAppView().setImg("ok");
                        }
                    }
                    
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        
        
    }
    
}
