package project1;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class DrawPanel extends JPanel {
    private Color nightSkyColor = new Color(5, 10, 48);
    private List<Rectangle> stars = new ArrayList<>();

    private Rectangle ufoTopPart = new Rectangle(0, -50, 25, 5);
    private Rectangle ufoMiddlePart = new Rectangle(0, -50, 50, 10);
    private Rectangle ufoBottomPart = new Rectangle(0, -50, 25, 5);

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(this.nightSkyColor);
    }

    public synchronized void drawStar(int x, int y, Color startColor) {
        Graphics g = getGraphics();
        g.setColor(startColor);
        g.fillOval(x, y, 5, 5);
        stars.add(new Rectangle(x, y, 5, 5));
    }

    public synchronized void clearStar(int x, int y) {
        Graphics g = getGraphics();
        g.setColor(this.nightSkyColor);
        g.fillOval(x, y, 5, 5);
        stars.removeIf(star -> star.x == x && star.y == y);
    }

    public synchronized void drawUFO(int x, int y) {
        this.ufoTopPart.y = y - 5;
        this.ufoMiddlePart.y = y;
        this.ufoBottomPart.y = y + 10;
        Graphics g = getGraphics();
        g.setColor(Color.GREEN);
        g.fillRect(x+12, this.ufoTopPart.y, ufoTopPart.width, ufoTopPart.height);
        g.setColor(new Color(0, 155, 0));
        g.fillRect(x, y, ufoMiddlePart.width, ufoMiddlePart.height);
        g.setColor(Color.GREEN);
        g.fillRect(x+12, this.ufoBottomPart.y, ufoBottomPart.width, ufoBottomPart.height);
        ufoTopPart.setLocation(x+12, ufoTopPart.y);
        ufoMiddlePart.setLocation(x, ufoMiddlePart.y);
        ufoBottomPart.setLocation(x+12, ufoBottomPart.y);
    }

    public synchronized void clearUFO(int x, int y) {
        Graphics g = getGraphics();
        g.setColor(this.nightSkyColor);
        g.fillRect(x+12, this.ufoTopPart.y, ufoTopPart.width, ufoTopPart.height);
        g.fillRect(x, y, ufoMiddlePart.width, ufoMiddlePart.height);
        g.fillRect(x+12, this.ufoBottomPart.y, ufoBottomPart.width, ufoBottomPart.height);
        this.ufoTopPart.y = -50;
        this.ufoMiddlePart.y = -50;
        this.ufoBottomPart.y = -50;
    }

    public synchronized boolean checkCollision(int x, int y) {
        Rectangle star = new Rectangle(x, y, 5, 5);
        return ufoMiddlePart.intersects(star) || ufoBottomPart.intersects(star) || ufoTopPart.intersects(star);
    }

    public synchronized void createExplosion(int x, int y) {
        Graphics g = getGraphics();
        g.setColor(Color.WHITE);

        for (int i = 0; i < 8; i++) {
            int dx = (int) (Math.cos(i * Math.PI / 4) * 15);
            int dy = (int) (Math.sin(i * Math.PI / 4) * 15);
            g.fillOval(x + dx, y + dy, 6, 6);
        }

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        g.setColor(this.nightSkyColor);
        for (int i = 0; i < 8; i++) {
            int dx = (int) (Math.cos(i * Math.PI / 4) * 15);
            int dy = (int) (Math.sin(i * Math.PI / 4) * 15);
            g.fillOval(x + dx, y + dy, 6, 6);
        }
    }
}
