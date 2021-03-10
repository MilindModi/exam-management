package client;

import client.ChatClient;
import java.io.*;
import java.net.*;
import javax.swing.DefaultListModel;
import javax.swing.JList;
/**
 * This thread is responsible for reading server's input and printing it
 * to the console.
 * It runs in an infinite loop until the client disconnects from the server.
 *
 * @author www.codejava.net
 */
public class ReadThread extends Thread {
    private BufferedReader reader;
    private Socket socket;
    private ChatClient client;
    private DefaultListModel model;
    private JList jList1;
    public ReadThread(Socket socket, ChatClient client, DefaultListModel model, JList jList1) {
        this.socket = socket;
        this.client = client;
        this.model = model;
        this.jList1 = jList1;
        try {
            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
        } catch (IOException ex) {
            System.out.println("Error getting input stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
 
    public void run() {
        while (true) {
            try {
                String response = reader.readLine();
                System.out.println("\n" + response);
                model.addElement(response);
                this.jList1.setModel(model);
                // prints the username after displaying the server's message
                if (client.getUserName() != null) {
                    System.out.print("[" + client.getUserName() + "]: ");
                }
            } catch (IOException ex) {
                System.out.println("Error reading from server: " + ex.getMessage());
                ex.printStackTrace();
                break;
            }
        }
    }
}