/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author MODI
 */
public class FileRecieve {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(5000);
        } catch (IOException ex) {
            System.out.println("Can't setup server on this port number. ");
        }

        serverSocket.setReuseAddress(true);
       
        while(true){
            Socket socket = null;
            InputStream in = null;
            OutputStream out = null;
            DataInputStream dis =null;
               try {
                    System.out.println("Server is waiting");
                    socket = serverSocket.accept(); 
                } catch (IOException ex) {
                    ex.printStackTrace();
                    System.out.println("Can't accept client connection. ");
                }

                try {
                    in = socket.getInputStream();
                    dis = new DataInputStream(in);
                } catch (IOException ex) {
                    System.out.println("Can't get socket input stream. ");
                }


                try { 
                    String fName = dis.readUTF(); 
                    String rNum = dis.readUTF();
                    out = new FileOutputStream("upload/" + rNum+ "_" +fName );

                } catch (FileNotFoundException ex) {
                    System.out.println("File not found. ");
                }
                int count;
                 byte[] bytes = new byte[16*1024];
               count = 0;
                while ((count = in.read(bytes)) > 0) {
                    out.write(bytes, 0, count);
                }

                out.close();
                in.close();
                socket.close();
//                serverSocket.close();
        }
     
    }
}
