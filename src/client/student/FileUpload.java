package client.student;

import client.User;
import java.io.*;
import java.net.Socket;

public class FileUpload {
    final String url ;
    final int port;
    final User user;
    
    public FileUpload(User user) {
        url = "127.0.0.1";
        port = 5000;
        this.user = user;
    }
    
    public static void main(String[] args) {
        var user = new User("10", "Pradip", "10_Pradip_xyz.txt", "abc");
        new FileUpload(user).uploadFile("", "C:\\Users\\Nirav Chavda\\Downloads\\Question Bank DAD - Digital Copy.docx");
    }
    
    public void uploadFile(String path,String fileName){ 
        try (Socket socket = new Socket(url, port)) {
            File file = new File(fileName);

            OutputStream os = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            
            BufferedOutputStream bos = new BufferedOutputStream(os);
            long length = file.length();
            byte[] bytes = new byte[4 * 1024];
            InputStream in = new FileInputStream(file);
            OutputStream out = socket.getOutputStream();

            dos.writeLong(length);
            dos.writeUTF(file.getName());
            dos.writeUTF(user.rollNum);
            dos.writeUTF(user.name);
            dos.writeUTF(user.examId);
            int count;
            
            while ((count = in.read(bytes)) != -1) {
                out.write(bytes, 0, count);
                out.flush();
            }
            
            System.out.println("File uploaded Successfully!");
            
            out.close();
            in.close();
            socket.close();
        }
        catch(Exception e) { 
            System.out.println("Error: " + e.getMessage());
        }
    }
}
