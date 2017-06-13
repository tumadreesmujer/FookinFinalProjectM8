package general;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
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

    public void connectToServer() throws IOException {
		socket = new Socket(serverAddress, serverPort);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public static void main(String[] args) throws Exception {
        getFile("192.168.1.3",9090);
        getFile("192.168.1.3",9090);
    }
    public static void getFile(String ip, int Socket) throws IOException{
        Client client = new Client(ip,Socket);
        client.connectToServer();
        int kB = 24;
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        byte[] tByte = new byte[1024*kB]; 
        String file = new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine().toString().substring(1);
        File tempName = new File(file.substring(file.lastIndexOf("\\")));
        System.out.println(file);
        //out = new PrintWriter(socket.getOutputStream(), true);
        InputStream is = socket.getInputStream();
        FileOutputStream fos = new FileOutputStream(file);
        //FileOutputStream fos = new FileOutputStream("test2.png");
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        int bytesRead = is.read(tByte, 0, 1024*kB);
        bos.write(tByte, 0, bytesRead);
        out.write("done");
        bos.close();
        out.close();
        is.close();
        fos.close();
        System.out.println("done");
        
        }
}