package general;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;


public class testClient {
	static Socket socket = null;
    static String host;
    static int port;
    public static void main(String[] args) throws IOException {
        sendFile(new File("res/test/elon-musk-recovered.jpg"),"192.168.1.3",4444);
    	sendFile(new File("res/test/picture1.png"),"192.168.1.3",4444);
    	/*Socket socket = null;
        String host = "127.0.0.1";

        socket = new Socket(host, 4444);

        File file = new File("M:\\test.xml");
        // Get the size of the file
        long length = file.length();
        byte[] bytes = new byte[16 * 1024];
        InputStream in = new FileInputStream(file);
        OutputStream out = (OutputStream) socket.getOutputStream();

        int count;
        while ((count = in.read(bytes)) > 0) {
            out.write(bytes, 0, count);
        }

        out.close();
        in.close();
        socket.close();*/
    }
    public testClient(String ip, int p){
    	
    	
    }
    public static void sendFile(File f,String ip, int p){
    host = ip;
	port = p;
	try {
		socket = new Socket(host,port);
	} catch (UnknownHostException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
    	 byte[] bytes = new byte[16 * 1024];
    	 InputStream in = null;
         OutputStream out = null;
         try {
        	 in = new FileInputStream(f);
         } catch (FileNotFoundException e) {
			e.printStackTrace();
         }
         try {
        	 out = (OutputStream) socket.getOutputStream();
        	 int count;
	         while ((count = in.read(bytes)) > 0) {
	             out.write(bytes, 0, count);
	         }

	         in.close();
	         out.close();
	         socket.close();
         } catch(SocketException e){
        	 return;
         } catch (IOException e) {
        	 e.printStackTrace();
         }
         
    }
    
}