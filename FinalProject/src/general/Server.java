package general;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * A TCP server that runs on port 9090.  When a client connects, it
 * sends the client the current date and time, then closes the
 * connection with that client.  Arguably just about the simplest
 * server you can write.
 */
public class Server {
	static ServerSocket listener;
	static Socket socket;
    /**
     * Runs the server.
     */
    public static void main(String[] args) throws IOException {
        listener = new ServerSocket(9090);
        try {
            while (true) {
                socket = listener.accept();
                try {
                    PrintWriter out =
                        new PrintWriter(socket.getOutputStream(), true);
                    System.out.println("Connected to:"+socket.getInetAddress()+":"+socket.getPort());
                    out.println(new Date().toString());
                    File tFile=new File("res/test/dank-memes_o_6669985.jpg");
                    byte[] tByte=new byte[(int)tFile.length()];
                    
                    
                    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(tFile));
                    bis.read(tByte, 0, tByte.length);
                    OutputStream os = socket.getOutputStream();
                    os.write(tByte, 0, tByte.length);
                    os.flush();
                    
                    
                } finally{
                }
            }
        }
        finally {
            
        }
    }
    public void killServer() throws IOException{
            socket.close();
            listener.close();
    }
    
}