package project1;

import javax.swing.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FireworksSkyExecutor extends JFrame {
    private DrawPanel drawPanel;
    private ExecutorService executorService;

    public FireworksSkyExecutor() {
        setTitle("Fireworks with Executor");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        drawPanel = new DrawPanel();
        add(drawPanel);
        setVisible(true);

        executorService = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 3; i++) {
            executorService.submit(new Firework(drawPanel));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FireworksSkyExecutor::new);
    }
}
