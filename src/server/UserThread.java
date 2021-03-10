package server;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
 
/**
 * This thread handles connection for each connected client, so the server
 * can handle multiple clients at the same time.
 *
 * @author www.codejava.net
 */
public class UserThread extends Thread {
    private Socket socket;
    private ChatServer server;
    private PrintWriter writer;
    
//    public boolean isTeacher;
    public String userName;
    
    public UserThread(Socket socket, ChatServer server) {
        this.socket = socket;
        this.server = server;
    }
 
    public void run() {
        try {
//            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
 
//            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(socket.getOutputStream(), true);
 
            printUsers();
 
            userName = reader.readLine();
            
//            if(userName.equalsIgnoreCase("T")){
//                isTeacher = true;
//            }else{
//                isTeacher = false;
//            }
            server.addUser(userName, this);
 
            String serverMessage = "New user connected: " + userName;
            server.broadcast(serverMessage, this );
 
            String clientMessage;
 
            do { 
                clientMessage = reader.readLine().trim();
                serverMessage = "[" + userName + "]: " + clientMessage;
                if (clientMessage.startsWith("@")) {
                    int indexOfSpace = clientMessage.indexOf(' ');
                    System.out.println(clientMessage.substring(1, indexOfSpace));
                    server.sendTo(serverMessage, clientMessage.substring(1, indexOfSpace));
                } else {
                    server.broadcast(serverMessage, this);
                }
//                server.broadcast(serverMessage, this);
 
            } while (!clientMessage.equals("bye"));
 
            
 
        } catch (IOException ex) {
            System.out.println("Error in UserThread: " + ex.getMessage());
            ex.printStackTrace();
        }finally{
            
            try {
                server.removeUser(userName);
                socket.close();
                String serverMessage = userName + " has quitted.";
                server.broadcast(serverMessage, this);
            } catch (IOException ex) {
                Logger.getLogger(UserThread.class.getName()).log(Level.SEVERE, null, ex);
            }
 
           
        }
    }
 
    /**
     * Sends a list of online users to the newly connected user.
     */
    void printUsers() {
        if (server.hasUsers()) {
            writer.println("Connected users: " + server.getUserNames());
        } else {
            writer.println("No other users connected");
        }
    }
 
    /**
     * Sends a message to the client.
     */
    void sendMessage(String message) {
        writer.println(message);
    }
}