package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class FileRecieve {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(5000);
        } catch (IOException ex) {
            System.out.println("Can't setup server on this port number. ");
        }

        while (true) {
            Socket socket = null;
            InputStream in = null;
            OutputStream out = null;
            DataInputStream dis = null;

            try {
                System.out.println("Server is waiting");
                socket = serverSocket.accept();
            } catch (IOException ex) {
                System.out.println("Can't accept client connection. ");
            }

            try {
                in = socket.getInputStream();
                dis = new DataInputStream(in);
            } catch (IOException ex) {
                System.out.println("Can't get socket input stream. ");
            }

            long size = 0;
            try {
                size = dis.readLong();
                final String fName = dis.readUTF();
                final String rNum = dis.readUTF();
                final String uName = dis.readUTF();
                final String examId = dis.readUTF();
                File theDir = new File("uploads/" + examId + "/" + rNum +"/");
                if (!theDir.exists()) {
                    theDir.mkdirs();
                }
                out = new FileOutputStream("uploads/" + examId + "/" + rNum + "/"+ fName);

            } catch (FileNotFoundException ex) {
                System.out.println("File not found. ");
            }

            int count;
            byte[] bytes = new byte[4 * 1024];
            count = 0;

            while (size > 0 && (count = in.read(bytes, 0, (int) Math.min(bytes.length, size))) != -1) {
                out.write(bytes, 0, count);
                size -= count;
            }

            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
            if (socket != null) {
                socket.close();
            }
        }

    }
}
