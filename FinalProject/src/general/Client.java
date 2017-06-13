package general;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * A simple Swing-based client for the capitalization server.
 * It has a main frame window with a text field for entering
 * strings and a textarea to see the results of capitalizing
 * them.
 */
public class Client {

    private BufferedReader in;
    private PrintWriter out;
    private String serverAddress;
    private int serverPort;
    static Socket socket;
    /**
     * Constructs the client by laying out the GUI and registering a
     * listener with the textfield so that pressing Enter in the
     * listener sends the textfield contents to the server.
     */
    public Client(String sA, int sP) {
    	serverAddress=sA;
    	serverPort=sP;

        // Add Listeners
        /*dataField.addActionListener(new ActionListener() {
            *//**
             * Responds to pressing the enter key in the textfield
             * by sending the contents of the text field to the
             * server and displaying the response from the server
             * in the text area.  If the response is "." we exit
             * the whole application, which closes all sockets,
             * streams and windows.
             *//*
            public void actionPerformed(ActionEvent e) {
                out.println(dataField.getText());
                   String response;
                try {
                    response = in.readLine();
                    if (response == null || response.equals("")) {
                          System.exit(0);
                      }
                } catch (IOException ex) {
                       response = "Error: " + ex;
                }
                messageArea.append(response + "\n");
                dataField.selectAll();
            }
        });*/
    }

    /**
     * Implements the connection logic by prompting the end user for
     * the server's IP address, connecting, setting up streams, and
     * consuming the welcome messages from the server.  The Capitalizer
     * protocol says that the server sends three lines of text to the
     * client immediately after establishing a connection.
     */
    public void connectToServer() throws IOException {
		socket = new Socket(serverAddress, serverPort);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        // Consume the initial welcoming messages from the server

        System.out.println(in.readLine());
    }
    public void sendReady(String s) throws IOException{
    	PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println("ready: "+s);
    }

    /**
     * Runs the client application.
     */
    public static void main(String[] args) throws Exception {
        Client client = new Client("192.168.1.13",9090);
        client.connectToServer();
        int kB = 64;
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        byte[] tByte = new byte[1024*kB]; 
        String tempName = new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine().toString();
        out = new PrintWriter(socket.getOutputStream(), true);
        out.println("ready");
		TimeUnit.SECONDS.sleep(1);
        InputStream is = socket.getInputStream();
        FileOutputStream fos = new FileOutputStream(tempName);
        //FileOutputStream fos = new FileOutputStream("test2.png");
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        int bytesRead = is.read(tByte, 0, 1024*kB);
        bos.write(tByte, 0, bytesRead);
        bos.close();
    }
}