package project1;

import javax.swing.*;
import java.awt.*;

class DrawPanel extends Canvas {
    private Color nightSkyColor = new Color(5, 10, 48);
    private Image offscreenImage;
    private Graphics2D offscreenGraphics;

    public DrawPanel() {
        this.nightSkyColor = new Color(5, 10, 48);
        this.setBackground(nightSkyColor);
    }


    public synchronized void clearFirework(int x, int y) {
        Graphics g = getGraphics();
        g.setColor(nightSkyColor);
        g.fillOval(x, y, 5, 5);
        repaint();
    }

    public synchronized void drawFirework(int x, int y, int speed, Color fireworkColor) {
        Graphics g = getGraphics();
        g.setColor(nightSkyColor);
        g.fillOval(x, y, 5, 5);
        g.setColor(fireworkColor);
        g.fillOval(x, y-speed, 5, 5);
        repaint();
    }

    public synchronized void drawExplosion(int x, int y, Color color, int explosionRadius, int explosionDimensions) {
        Graphics g = getGraphics();
        g.setColor(color);
        for (int i = 0; i < 8; i++) {
            int dx = (int) (Math.cos(i * Math.PI / 4) * explosionRadius);
            int dy = (int) (Math.sin(i * Math.PI / 4) * explosionRadius);
            g.fillOval(x + dx, y + dy, explosionDimensions, explosionDimensions);
        }
        repaint();
    }

    public synchronized void clearExplosion(int x, int y, int explosionRadius, int explosionDimensions) {
        Graphics g = getGraphics();
        g.setColor(this.nightSkyColor);
        for (int i = 0; i < 8; i++) {
            int dx = (int) (Math.cos(i * Math.PI / 4) * explosionRadius);
            int dy = (int) (Math.sin(i * Math.PI / 4) * explosionRadius);
            g.fillOval(x + dx, y + dy, explosionDimensions, explosionDimensions);
        }
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        update(g);
    }

    @Override
    public void update(Graphics g) {
        g.drawImage(offscreenImage, 0, 0, this);
    }
}
