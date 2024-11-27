package project1;

import java.awt.*;
import java.util.Random;

class Firework implements Runnable {
    private DrawPanel panel;
    private Random random;
    private Color color;
    private int explosionHeight;
    private int explosionRadius;
    private int explosionDimensions;

    public Firework(DrawPanel panel) {
        random = new Random();

        this.panel = panel;
        this.random = new Random();
        this.color = new Color(this.random.nextInt(105) + 150, this.random.nextInt(105) + 150, this.random.nextInt(105) + 150);
        this.explosionHeight = this.random.nextInt(200) + 100;

        this.explosionRadius = new Random().nextInt(30) + 5;
        this.explosionDimensions = new Random().nextInt(7) + 3;
    }

    @Override
    public void run() {
        while (true) {
            int x = random.nextInt(800);
            int y = 600;
            int speed = random.nextInt(10) + 10;

            try {
                while (y > 0) {
                    panel.drawFirework(x, y, this.color);
                    Thread.sleep(110);
                    panel.clearFirework(x, y);
                    y -= speed;

                    if (y <= this.explosionHeight) {
                        panel.createExplosion(x, y, this.color, this.explosionRadius, this.explosionDimensions);
                        Thread.sleep(200);
                        panel.clearExplosion(x, y, this.explosionRadius, this.explosionDimensions);
                        break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
