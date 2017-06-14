package general;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;


public class Client {
	static Socket socket = null;
    static String host;
    static int port;
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
    public static void sendText(String s,String ip, int p){
    	try {
    		socket = new Socket(ip,p);
			OutputStream outstream = socket .getOutputStream(); 
			PrintWriter out = new PrintWriter(outstream);
			out.print(s);
			out.close();
			outstream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

public class Client {
	static Socket socket = null;
    static String host;
    static int port;
    public static void main(String[] args) throws IOException {
    	sendText("test.jpg","192.168.1.3",4444);
        sendFile(new File("test.jpg"),"192.168.1.3",4445);
    	sendText("picture1.png","192.168.1.3",4444);
    	sendFile(new File("picture1.png"),"192.168.1.3",4445);
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
    public static void sendText(String s,String ip, int p){
    	try {
    		socket = new Socket(ip,p);
			OutputStream outstream = socket .getOutputStream(); 
			PrintWriter out = new PrintWriter(outstream);
			out.print(s);
			out.close();
			outstream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
    
}