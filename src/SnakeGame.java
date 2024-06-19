import javax.swing.*;
import java.awt.*;

public class SnakeGame extends JFrame {

    public SnakeGame() {
        setTitle("Snake Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        SnakeGamePanel gamePanel = new SnakeGamePanel();
        SnakeMenuPanel menuPanel = new SnakeMenuPanel(gamePanel);

        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.add(gamePanel, BorderLayout.CENTER);
        containerPanel.add(menuPanel, BorderLayout.NORTH);

        add(containerPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        gamePanel.requestFocusInWindow();
    }



}
