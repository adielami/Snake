import javax.swing.*;
import java.awt.*;

public class SnakeMenuPanel extends JPanel {

    private SnakeGamePanel gamePanel;
    private JLabel scoreLabel;
    private JLabel highScoreLabel;
    private SnakeMenuPanel menuPanel;

    public SnakeMenuPanel(SnakeGamePanel gamePanel) {
        this.gamePanel = gamePanel;
        setPreferredSize(new Dimension(600, 70));
        setBackground(Color.pink);

        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(e -> {
            gamePanel.requestFocusInWindow();
            if (!gamePanel.isRunning()) {
                gamePanel.startGame();
            }
        });

        add(startButton);
    }

    public void setMenuPanel(SnakeMenuPanel menuPanel) {
        this.menuPanel = menuPanel;
    }


}

