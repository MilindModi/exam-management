package logger;

import client.User;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class KeyLoggerFile {

    FileWriter myWriter;
    final User user;

    public KeyLoggerFile(User user) {
        this.user = user;
        File theDir = new File("local/");
        if (!theDir.exists()) {
            theDir.mkdirs();
        }
    }

    public void keyLoggerWriter(CharSequence log) throws IOException {
        try {
            myWriter = new FileWriter("local/"+user.keyLogFile, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        myWriter.append(log);
        myWriter.close();
    }
}
