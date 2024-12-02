package project1;

import javax.swing.*;

public class FireworksSkySimple extends JFrame {
    private DrawPanel drawPanel;

    public FireworksSkySimple() {
        setTitle("Fireworks");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        drawPanel = new DrawPanel();
        add(drawPanel);
        setVisible(true);
        for (int i = 0; i < 100; i++) {
            Thread fireworkThread = new Thread(new Firework(drawPanel));
            fireworkThread.start();
        }
        try{
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 50; i++) {
            Thread fireworkThread = new Thread(new Firework(drawPanel));
            fireworkThread.start();
        }
        try{
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 90; i++) {
            Thread fireworkThread = new Thread(new Firework(drawPanel));
            fireworkThread.start();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FireworksSkySimple::new);
    }
}

