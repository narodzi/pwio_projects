package project1;

import java.util.Random;

class UFO implements Runnable {
    private int ufoY;

    private DrawPanel panel;

    public UFO(DrawPanel panel) {
        this.panel = panel;
    }

    @Override
    public void run() {
        while (true) {
            this.ufoY = new Random().nextInt(300) + 200;
            try {
                Thread.sleep(new Random().nextInt(5000));
                for (int x = 0; x < 800; x += 10) {
                    panel.drawUFO(x, this.ufoY);
                    Thread.sleep(90);
                    panel.clearUFO(x, this.ufoY);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
