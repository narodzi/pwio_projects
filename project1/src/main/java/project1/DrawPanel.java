package project1;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class DrawPanel extends JPanel {
    private Color nightSkyColor = new Color(5, 10, 48);
    private List<Rectangle> stars = new ArrayList<>();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(this.nightSkyColor);
    }

    public synchronized void drawFirework(int x, int y, Color startColor) {
        Graphics g = getGraphics();
        g.setColor(startColor);
        g.fillOval(x, y, 5, 5);
        stars.add(new Rectangle(x, y, 5, 5));
    }

    public synchronized void clearFirework(int x, int y) {
        Graphics g = getGraphics();
        g.setColor(this.nightSkyColor);
        g.fillOval(x, y, 5, 5);
        stars.removeIf(star -> star.x == x && star.y == y);
    }


    public synchronized void createExplosion(int x, int y, Color color, int explosionRadius, int explosionDimensions) {
        Graphics g = getGraphics();
        g.setColor(color);

        for (int i = 0; i < 8; i++) {
            int dx = (int) (Math.cos(i * Math.PI / 4) * explosionRadius);
            int dy = (int) (Math.sin(i * Math.PI / 4) * explosionRadius);
            g.fillOval(x + dx, y + dy, explosionDimensions, explosionDimensions);
        }
    }

    public synchronized void clearExplosion(int x, int y, int explosionRadius, int explosionDimensions) {
        Graphics g = getGraphics();
        g.setColor(this.nightSkyColor);
        for (int i = 0; i < 8; i++) {
            int dx = (int) (Math.cos(i * Math.PI / 4) * explosionRadius);
            int dy = (int) (Math.sin(i * Math.PI / 4) * explosionRadius);
            g.fillOval(x + dx, y + dy, explosionDimensions, explosionDimensions);
        }
    }
}
