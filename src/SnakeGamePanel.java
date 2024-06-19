import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class SnakeGamePanel extends JPanel implements Runnable {

    private static final int WIDTH = 900;
    private static final int HEIGHT = 650;
    private static final int UNIT_SIZE = 20;
    private static final int GAME_UNITS = (WIDTH * HEIGHT) / UNIT_SIZE;
    private static final int DELAY =120;

    private ArrayList<Point> snake;
    private Point food;
    private char direction = 'R';
    private boolean running = false;
    private Thread gameThread;
    private boolean gameStartedByButton = false;



    public SnakeGamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.green);
        setFocusable(true);

        snake = new ArrayList<>();
        snake.add(new Point(0, 0));
        food = generateFoodLocation();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        if (direction != 'L') direction = 'R';
                        break;
                    case KeyEvent.VK_LEFT:
                        if (direction != 'R') direction = 'L';
                        break;
                    case KeyEvent.VK_UP:
                        if (direction != 'D') direction = 'U';
                        break;
                    case KeyEvent.VK_DOWN:
                        if (direction != 'U') direction = 'D';
                        break;
                    case KeyEvent.VK_SPACE:
                        if (!gameStartedByButton) {
                            startGame();
                            gameStartedByButton = true;
                        }
                        break;
                }
            }
        });

    }


    void startGame() {
        running = true;
        snake.clear();
        snake.add(new Point(0, 0));
        direction = 'R';
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (running) {
            move();
            checkCollision();
            checkFood();
            repaint();
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void move() {
        Point head = snake.get(0);
        Point newHead = new Point(head);
        switch (direction) {
            case 'R':
                newHead.x += UNIT_SIZE;
                break;
            case 'L':
                newHead.x -= UNIT_SIZE;
                break;
            case 'U':
                newHead.y -= UNIT_SIZE;
                break;
            case 'D':
                newHead.y += UNIT_SIZE;
                break;
        }
        snake.add(0, newHead);
        if (!isFoodConsumed(newHead)) {
            snake.remove(snake.size() - 1);
        }
    }

    private boolean isFoodConsumed(Point head) {
        return head.equals(food);
    }

    private void checkCollision() {
        Point head = snake.get(0);
        if (head.x < 0 || head.x >= WIDTH || head.y < 0 || head.y >= HEIGHT || snake.subList(1, snake.size()).contains(head)) {
            running = false;

        }

    }

    private void checkFood() {
        Point head = snake.get(0);
        if (head.equals(food)) {
            food = generateFoodLocation();

            Point tail = snake.get(snake.size() - 1);
            snake.add(new Point(tail));
        }

    }

    private Point generateFoodLocation() {
        Random random = new Random();
        int x = random.nextInt(WIDTH / UNIT_SIZE) * UNIT_SIZE;
        int y = random.nextInt(HEIGHT / UNIT_SIZE) * UNIT_SIZE;
        return new Point(x, y);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (running) {

            g.setColor(Color.blue);
            for (Point point : snake) {
                g.fillRect(point.x, point.y, UNIT_SIZE, UNIT_SIZE);
            }
            g.setColor(Color.RED);
            g.fillRect(food.x, food.y, UNIT_SIZE, UNIT_SIZE);
        } else {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            FontMetrics metrics = g.getFontMetrics();
            String gameOverMessage = "Game Over";
             g.drawString(gameOverMessage, (WIDTH - metrics.stringWidth(gameOverMessage)) / 2, HEIGHT / 2);
        }
    }

    public boolean isRunning() {
        return running;
    }
}
