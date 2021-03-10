/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 *
 * @author MODI
 */
public class FileUpload {
    String url ;
    int port;
    FileUpload(){
        url = "127.0.0.1";
        port = 5000;
    }
    public static void main(String[] args) {
        new FileUpload().uploadFile("","C:\\Users\\MODI\\Desktop\\19_MilindModi_OOAD_Sessional_1.pdf");
    }
    public void uploadFile( String path,String fileName){ 
        try (Socket socket = new Socket(url, port)) {
            File file = new File( fileName);

            OutputStream os = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            
            BufferedOutputStream bos = new BufferedOutputStream(os);
//            BufferedInputStream bis = new BufferedInputStream(new InputStream(socket.getInputStream()));
            long length = file.length();
            byte[] bytes = new byte[16 * 1024];
            InputStream in = new FileInputStream(file);
            OutputStream out = socket.getOutputStream();

            dos.writeUTF(file.getName());
            dos.writeUTF("19");
            int count;
 
            while ((count = in.read(bytes)) > 0) {
                out.write(bytes, 0, count);
            }
            
            out.close();
            in.close();
            socket.close();
        }
        catch(Exception e) { 
            e.printStackTrace();
        }
    }
}
