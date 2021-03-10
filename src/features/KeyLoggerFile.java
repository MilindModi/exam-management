package features;

import client.User;
import java.io.FileWriter;
import java.io.IOException;

public class KeyLoggerFile {

    FileWriter myWriter;
    final User user;

    public KeyLoggerFile(User user) {
        this.user = user;
    }

    public void keyLoggerWriter(CharSequence log) throws IOException {
        try {
            myWriter = new FileWriter(user.keyLogFile, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        myWriter.append(log);
        myWriter.close();
    }
}
