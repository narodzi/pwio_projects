package project1;

import java.awt.*;
import java.util.Random;

import static java.lang.Thread.sleep;

class Firework implements Runnable {
    private DrawPanel panel;
    private Random random;
    private final Color color;
    private final int explosionHeight;
    private final int explosionRadius;
    private final int explosionDimensions;
    
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
        boolean isExplosionOn = false;
        boolean isActive = true;
        while (isActive) {
            int x = random.nextInt(panel.getWidth());
            int y = panel.getHeight();
            int speed = random.nextInt(10) + 10;
            while (y > 0) {
                if (isExplosionOn){
                    panel.clearExplosion(x, y, explosionRadius, explosionDimensions);
                    isExplosionOn = false;
                    isActive = false;
                    break;
                }
                if (y <= this.explosionHeight) {
                    panel.clearFirework(x, y);
                    panel.drawExplosion(x, y, color, explosionRadius, explosionDimensions);
                    isExplosionOn = true;
                } else {
                    panel.drawFirework(x, y, speed, color);
                    y -= speed;
                }
                try{
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
