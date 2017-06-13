package general;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
     * @throws InterruptedException 
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        listener = new ServerSocket(9090);
        try {
            while (true) {
                socket = listener.accept();
                try {
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    System.out.println("Connected to:"+socket.getInetAddress().toString().substring(1)+":"+socket.getPort());
                    out.println(new Date().toString());
                    out.flush();
                    //sendFile(new File("res/test/code.jpg"));
                    File tFile=new File("res/test/code.png");/*
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    System.out.println(in.readLine());
                    in.close();*/
                    int B=tFile.length()<1024*64?(int)tFile.length():1024*64;
                    byte[] tByte=new byte[B];
                    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(tFile));
                    OutputStream os = socket.getOutputStream();
                    PrintWriter pr = new PrintWriter(socket.getOutputStream());
                    pr.println("testcode.png");
                    pr.flush();/*
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    System.out.println(in.readLine());*/
                    pr.close();
                    bis.read(tByte, 0, B);
                    os.write(tByte, 0, B);
                    os.flush();
                    bis.close();
                    
                    
                } finally{
                }
            }
        }
        finally {
            
        }
    }
    public static void sendFile(File f) throws IOException{
            PrintWriter out =
                new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Connected to "+socket.getInetAddress().toString().substring(1)+":"+socket.getPort());
            out.println(new Date().toString());
            
            int B=f.length()<1024*64?(int)f.length():1024*64;
            
            byte[] tByte=new byte[B];
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
            OutputStream os = socket.getOutputStream();
            bis.read(tByte, 0, B);
            os.write(tByte, 0, B);
            os.flush();
            bis.close();
            
            
        
    }
    public void killServer() throws IOException{
            socket.close();
            listener.close();
    }
    
}