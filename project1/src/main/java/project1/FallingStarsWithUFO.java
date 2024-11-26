package project1;

import javax.swing.*;

public class FallingStarsWithUFO extends JFrame {
    private DrawPanel drawPanel;

    public FallingStarsWithUFO() {
        setTitle("Spadające gwiazdy z UFO");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        drawPanel = new DrawPanel();
        add(drawPanel);
        setVisible(true);

        for (int i = 0; i < 10; i++) {
            Thread starThread = new Thread(new Star(drawPanel));
            starThread.start();
        }

        Thread ufoThread = new Thread(new UFO(drawPanel));
        ufoThread.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FallingStarsWithUFO::new);
    }
}

