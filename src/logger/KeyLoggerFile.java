package logger;

import client.Student;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class KeyLoggerFile {

    FileWriter myWriter;
    final Student user;

    public KeyLoggerFile(Student user) {
        this.user = user;
        File theDir = new File("local/");
        if (!theDir.exists()) {
            theDir.mkdirs();
        }
    }

    public void keyLoggerWriter(CharSequence log) throws IOException {
        try {
            myWriter = new FileWriter("local/" + user.keyLogFile, true);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        myWriter.append(log);
        myWriter.close();
    }
}
