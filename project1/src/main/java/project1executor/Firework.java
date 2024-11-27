package project1executor;

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
        this.color = new Color(random.nextInt(105) + 150, random.nextInt(105) + 150, random.nextInt(105) + 150);
        this.explosionHeight = random.nextInt(200) + 100;

        this.explosionRadius = random.nextInt(30) + 5;
        this.explosionDimensions = random.nextInt(7) + 3;
    }

    @Override
    public void run() {
        while (true) {
            int x = random.nextInt(800);
            int y = 600;
            int speed = random.nextInt(10) + 10;

            try {
                while (y > 0) {
                    panel.drawFirework(x, y, color);
                    Thread.sleep(110);
                    panel.clearFirework(x, y);
                    y -= speed;

                    if (y <= explosionHeight) {
                        panel.createExplosion(x, y, color, explosionRadius, explosionDimensions);
                        Thread.sleep(200);
                        panel.clearExplosion(x, y, explosionRadius, explosionDimensions);
                        break;
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }
}
