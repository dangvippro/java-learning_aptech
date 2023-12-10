package socketdemo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EchoServer {
    public final static int defaultPort = 2000;
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(defaultPort);
            while (true) {
                Socket s = ss.accept();
                InputStream in = s.getInputStream();
                OutputStream out = s.getOutputStream();
                
                int ch = 0;
                while (true) {
                    ch = in.read();
                    if (ch == -1) {
                        break;
                    }
                    out.write(ch);
                }
                s.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(EchoServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
