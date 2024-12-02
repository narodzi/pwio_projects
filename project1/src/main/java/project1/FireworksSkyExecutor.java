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

        executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 100; i++) {
            executorService.execute(new Firework(drawPanel));
        }
        try{
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 50; i++) {
            executorService.execute(new Firework(drawPanel));
        }
        try{
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 90; i++) {
            executorService.execute(new Firework(drawPanel));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FireworksSkyExecutor::new);
    }
}
