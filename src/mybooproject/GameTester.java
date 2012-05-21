/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mybooproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author renaudjenny
 */
public class GameTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 4321);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String fromServer;
            while ((fromServer = in.readLine()) != null) {
                System.out.println("Server: " + fromServer);
                if (fromServer.equals("Bye."))
                    break;

                InputStreamReader inp = new InputStreamReader(System.in);
                BufferedReader br = new BufferedReader(inp);

                System.out.println("Choose a command: ");
                String fromUser = br.readLine();
                if (fromUser != null) {
                    System.out.println("Client: " + fromUser);
                    out.println(fromUser);
                }
            }
            
            
        } catch (Exception ex) {
            Logger.getLogger(GameTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
