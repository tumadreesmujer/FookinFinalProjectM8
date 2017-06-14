package general;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	static int port;
    static ServerSocket serverSocket = null;
	static Socket socket = null;
	static InputStream in = null;
	static OutputStream out = null;
    public static void main(String[] args) throws IOException {
    	getFile(new File(getText(4444)),4445);
    	getFile(new File(getText(4444)),4445);
    }
    public static void getFile(File f, int p){	
    	port=p;
    	try {
            serverSocket = new ServerSocket(p);
        } catch (IOException e) {
            System.out.println("Can't setup server on this port number. ");
            e.printStackTrace();

            //System.out.println("Can't setup server on this port number. ");
            //e.printStackTrace();
        }
         try {
             socket = serverSocket.accept();
         } catch (IOException ex) {
             System.out.println("Can't accept client connection. ");
         }

         try {
             in = socket.getInputStream();
         } catch (IOException ex) { 
             System.out.println("Can't get socket input stream. ");
         }

         try {
             out = new FileOutputStream(f);
         } catch (FileNotFoundException ex) {
             System.out.println("File not found. ");
         }

         byte[] bytes = new byte[16*1024];

         int count;
         try {
			while ((count = in.read(bytes)) > 0) {
			     out.write(bytes, 0, count);
			 }

			 out.close();
			 in.close();
			 socket.close();
			 serverSocket.close();
			 return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
    }
    public static String getText(int p){
        try {
            serverSocket = new ServerSocket(4444);
            socket = serverSocket.accept();
			InputStreamReader inputstreamreader = new InputStreamReader(socket.getInputStream());
			BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
			String s = bufferedreader.readLine();
			bufferedreader.close();
			inputstreamreader.close();
			socket.close();
			serverSocket.close();
			return s;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
       }
    
}