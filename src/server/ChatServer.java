package server;
  
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.DefaultListModel;
import javax.swing.JList;
 
/**
 * This is the chat server program.
 * Press Ctrl + C to terminate the program.
 *
 * @author www.codejava.net
 */
public class ChatServer {
    private int port;
    private Map<String,UserThread> users = new HashMap<>();
//    private Set<String> userNames = new HashSet<>();
//    private Set<UserThread> userThreads = new HashSet<>();
    private DefaultListModel userModel;
     
    public ChatServer(int port) {
        this.port = port;
     
    }
 
    public void execute() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
 
            System.out.println("Chat Server is listening on port " + port);
 
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New user connected");
 
                UserThread newUser = new UserThread(socket, this);
//                userThreads.add(newUser);
                
                newUser.start();
 
            }
 
        } catch (IOException ex) {
            System.out.println("Error in the server: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
 
    public static void main(String[] args) {
//        if (args.length < 1) {
//            System.out.println("Syntax: java ChatServer <port-number>");
//            System.exit(0);
//        }
 
        int port = Integer.parseInt("8989");
 
        ChatServer server = new ChatServer(port);
        server.execute();
    }
 
    /**
     * Delivers a message from one user to others (broadcasting)
     */
//    void broadcast(String message, UserThread excludeUser) {
//        if(excludeUser.userName.equalsIgnoreCase("T")){
//            for (UserThread aUser : userThreads) {
//                if ( aUser != excludeUser) {
//                    aUser.sendMessage(message);
//                } 
//            }
//        }else{
//            for (UserThread aUser : userThreads) {
//                if ( aUser != excludeUser &&  (aUser.userName.equals("T")   )) {
//                    aUser.sendMessage(message);
//                }
//            }
//        }
//    }
    void broadcast(String message, UserThread excludeUser) {
        for (Map.Entry<String, UserThread> aUser : users.entrySet()) {
            if (aUser.getValue() != excludeUser) {
                aUser.getValue().sendMessage(message);
            }
        }
    }
     void sendTo(String message, String username) {
        var user = getUser(username);
        user.sendMessage(message);
    }

    private UserThread getUser(String username) {
        for (Map.Entry<String, UserThread> aUser : users.entrySet()) {
            if (aUser.getKey().equalsIgnoreCase(username)) {
                return aUser.getValue();
            }
        }
        return null;
    }
    /**
     * Stores username of the newly connected client.
     */
//    void addUserName(String userName) {
//        userNames.add(userName);
//        userModel.addElement(userName);
//        userList.setModel(userModel);
//    }
     void addUser(String userName, UserThread userThread) {
        // userNames.add(userName);
        users.put(userName, userThread);
    }
 
    /**
     * When a client is disconneted, removes the associated username and UserThread
     */
//    void removeUser(String userName, UserThread aUser) {
//        boolean removed = userNames.remove(userName);
//        if (removed) {
//            userThreads.remove(aUser);
//            System.out.println("The user " + userName + " quitted");
//        }
//    }
 
     void removeUser(String userName) {
        // boolean removed = userNames.remove(userName);
        // if (removed) {
        // userThreads.remove(aUser);
        // System.out.println("The user " + userName + " quitted");
        // }
        users.remove(userName);
    }
      Set<String> getUserNames() {
        return this.users.keySet();
    }

    boolean hasUsers() {
        return !this.users.isEmpty();
    }
//    Set<String> getUserNames() {
//        return this.userNames;
//    }
// 
    /**
     * Returns true if there are other users connected (not count the currently connected user)
     */
//    boolean hasUsers() {
//        return !this.userNames.isEmpty();
//    }
}