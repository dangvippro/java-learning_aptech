package socketdemo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server extends Thread {

    private final ServerSocket serverSocket;
    
    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }
    
    @Override 
    public void run() {
        while (true) {
            try {
                System.out.println("Listening for client message on port " + serverSocket.getLocalPort());
                Socket socket = serverSocket.accept();
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    serverSocket.close();
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        try {
            Thread thread = new Server(6060);
            thread.start();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
