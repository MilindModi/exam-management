package client;

import java.io.*;
import java.net.*;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JComboBox;

public class ClientReadHandler extends Thread {
    private BufferedReader reader;
    private final Socket socket;
    private final ChatClient client;
    private final DefaultListModel model;
    private final JList chatbox;
//    private final JComboBox<String> comboBox;

    public ClientReadHandler(Socket socket, ChatClient client, DefaultListModel model, JList chatbox) {
        this.socket = socket;
        this.client = client;
        this.model = model;
        this.chatbox = chatbox;
//        this.comboBox = comboBox;
        
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.out.println("Error getting input stream: " + e.getMessage());
        }
    }
 
    public void run() {
        while (true) {
            try {
                String response = reader.readLine();
                System.out.println("\n" + response);
//                if(response.startsWith("No other users connected")){ 
//                    comboBox.removeAllItems();
//                    continue;
//                }
//                if(response.startsWith("Connected users:")) {
//                    int indexOfOpeningBracket = response.indexOf("[");
//                    int indexOfClosingBracket = response.indexOf("]");
//                    String[] userList = response.substring(indexOfOpeningBracket, indexOfClosingBracket).split("[ ,]");
//                    comboBox.removeAllItems();
//                    for(String u : userList) {
//                        comboBox.addItem(u);
//                    }
//                }
                model.addElement(response);
                this.chatbox.setModel(model);
                // prints the username after displaying the server's message
                if (client.getUsername() != null) {
                    System.out.print("[" + client.getUsername() + "]: ");
                }
            } catch (IOException e) {
                System.out.println("Error reading from server: " + e.getMessage());
                break;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                break;
            }
        }
    }
}