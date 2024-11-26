package project1;

import java.awt.*;
import java.util.Random;

class Star implements Runnable {
    private DrawPanel panel;
    private Random random;
    private Color color;

    public Star(DrawPanel panel) {
        random = new Random();

        this.panel = panel;
        this.random = new Random();
        this.color = new Color(this.random.nextInt(105) + 150, this.random.nextInt(105) + 150, this.random.nextInt(105) + 150);
    }

    @Override
    public void run() {
        while (true) {
            int x = random.nextInt(800);
            int y = 0;
            int speed = random.nextInt(10) + 10;

            try {
                while (y < 600) {
                    panel.drawStar(x, y, this.color);
                    Thread.sleep(90);
                    panel.clearStar(x, y);
                    y += speed;

                    if (panel.checkCollision(x, y)) {
                        panel.createExplosion(x, y);
                        break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
