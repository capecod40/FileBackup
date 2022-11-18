package misc;

import java.awt.*;

public class Splash {
    public Splash() {
        init();
    }

    public void init() {
        SplashScreen splashScreen = SplashScreen.getSplashScreen();
        if (splashScreen == null) {
            System.out.println("Splash screen is null!");
            return;
        }
        Graphics2D graphics = splashScreen.createGraphics();
        if (graphics == null) {
            System.out.println("Splash screen graphics is null!");
            return;
        }
    }
}
