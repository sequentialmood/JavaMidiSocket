package net.sequentialmood.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by IntelliJ IDEA.
 * User: sequentialmood
 * Date: 14 oct. 2010
 * Time: 23:19:51
 * To change this template use File | Settings | File Templates.
 */
public class Server extends Thread {
    final static int MINUTES = 1;
    public final static int PORT = 1024;
    protected PrintWriter _pout = null;
    protected Socket _s;
    ServerSocket _ss;

    public Server() throws IOException {
      _ss = new ServerSocket(PORT);
    }

    public void run() {
      while (true) {
         try {
         System.out.println("SocketServer waiting for connection");
         _s = _ss.accept();
         //BufferedReader is = new BufferedReader(new InputStreamReader(_s.getInputStream()));
        
         /*String passwd = is.readLine();
         String domain = is.readLine();       */

         _pout = new PrintWriter(_s.getOutputStream(), true);
         //pout.println("Welcome to " + domain + ", " + name);
         _pout.println("Welcome! you're connected ");
         } catch (IOException e) {
            System.err.println("Oh, dear me! " + e);
         }
      }
    }

    public void sendMessage(String message)
    {  try {
         _pout = new PrintWriter(_s.getOutputStream(), true);
         _pout.println(message+ (char)0x00);
         //_pout.flush();
        } catch (IOException e) {
            System.err.println("Oh, dear me! " + e);
        }
    }

    
}
