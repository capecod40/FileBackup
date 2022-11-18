package misc;

import java.awt.*;

public class Splash {
    public Splash() {
        init();
    }

    public void init() {
        final SplashScreen splashScreen = SplashScreen.getSplashScreen();
        if (splashScreen == null) {
            System.out.println("Splash screen is null!");
            return;
        }
        Graphics2D graphics = splashScreen.createGraphics();
        if (graphics == null) {
            System.out.println("Splash screen graphics is null!");
            return;
        }
        for (int i = 0; i < 100; i++) {
            final String[] comps = {"foo", "bar", "baz"};
            graphics.setComposite(AlphaComposite.Clear);
            graphics.fillRect(120,140,200,40);
            graphics.setPaintMode();
            graphics.setColor(Color.BLACK);
/*            graphics.drawString("Loading "+ comps[(frame/5)%3]+"...", 120, 150);*/
            splashScreen.update();
            try {
                Thread.sleep(90);
            } catch (InterruptedException e) {
                System.out.println("Interrupted exception!");
            }
        }
    }
}
