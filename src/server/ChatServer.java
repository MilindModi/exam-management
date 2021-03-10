package server;
  
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.DefaultListModel;
import javax.swing.JList;
 
public class ChatServer {
    private final int port;
    private final Map<String,ClientHandler> users = new HashMap<>();
    private DefaultListModel userModel;
     
    public ChatServer(int port) {
        this.port = port;
    }
 
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
 
            System.out.println("Chat Server is listening on port " + port);
 
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New user connected");
                ClientHandler newUser = new ClientHandler(socket, this);
                newUser.start();
            } 
        } catch (IOException ex) {
            System.out.println("Error in the server: " + ex.getMessage());
        }
    }
 
    public static void main(String[] args) {
        int port = 8989;
        ChatServer server = new ChatServer(port);
        server.start();
    }
 
    void sendToEveryone(String message, ClientHandler notToUser) {
        for (Map.Entry<String, ClientHandler> aUser : users.entrySet()) {
            if (aUser.getValue() != notToUser)
                aUser.getValue().sendMessage(message);
        }
    }
    
     void sendTo(String message, String username) {
        var user = getUser(username);
        if(user != null)
            user.sendMessage(message);
    }

    private ClientHandler getUser(String username) {
        for (Map.Entry<String, ClientHandler> aUser : users.entrySet()) {
            if (aUser.getKey().equals(username))
                return aUser.getValue();
        }
        return null;
    }

    void addUser(String userName, ClientHandler userThread) {
        users.put(userName, userThread);
    }
 
     void removeUser(String userName) {
        users.remove(userName);
    }
     
    Set<String> getUserNames() {
        return this.users.keySet();
    }

    boolean hasUsers() {
        return !this.users.isEmpty();
    }
}