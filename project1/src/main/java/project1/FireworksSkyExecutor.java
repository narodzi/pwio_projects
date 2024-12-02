package project1;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class FireworksSkyExecutor extends JFrame implements KeyListener {

    private final DrawPanel drawPanel;
    private final ScheduledExecutorService executorService;

    public FireworksSkyExecutor() {
        executorService = Executors.newScheduledThreadPool(1000);
        setTitle("Fireworks with Executor");
        setSize(800, 600);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        drawPanel = new DrawPanel();
        drawPanel.setFocusable(false);
        add(drawPanel);
        setVisible(true);
        addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_Q -> createFireworks(10);
            case KeyEvent.VK_W -> createFireworks(50);
            case KeyEvent.VK_E -> createFireworks(100);
            case KeyEvent.VK_R -> createFireworks(500);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private void createFireworks(int n){
        for (int i = 0; i < n; i++) {
            executorService.execute(new Firework(drawPanel));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FireworksSkyExecutor::new);
    }
}
