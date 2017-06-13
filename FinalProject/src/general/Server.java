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
import java.net.SocketException;
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
    }
    public static void sendFile(File f) throws IOException{
    	listener = new ServerSocket(9090);
    		boolean temp = true;
            while (temp) {
                socket = listener.accept();
                try {
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    System.out.println("Connected to:"+socket.getInetAddress().toString().substring(1)+":"+socket.getPort());
                    //sendFile(new File("res/test/code.jpg"));
                   /*
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    System.out.println(in.readLine());
                    in.close();*/
                    int B=f.length()<1024*25?(int)f.length():1024*25;
                    byte[] tByte=new byte[B];
                    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
                    PrintWriter pr = new PrintWriter(socket.getOutputStream());
                    System.out.println(f.getAbsolutePath().replaceAll("\\\\", "/"));
                    pr.println(f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("\\res\\")));
                    pr.flush();
                    OutputStream os = socket.getOutputStream();/*
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    System.out.println(in.readLine());*/
                    //pr.close();
                    bis.read(tByte, 0, B);
                    
                    os.write(tByte, 0, B);
                    os.flush();
                    bis.close();
                    in.close();
                    String tempStr= new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine().toString();
                    if(tempStr.equals("done"))
                    	listener.close();
                    	return;
                    
                } catch(SocketException e){
                	listener.close();
                	return;
                }
            
            }
           
            
        
    }
    public void killServer() throws IOException{
            socket.close();
           listener.close();
    }
   
}