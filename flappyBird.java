import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {

    private int birdY = 250, birdVelocity = 0;
    private int gravity = 1, jump = -12;
    private ArrayList<Rectangle> pipes;
    private int pipeWidth = 60, pipeGap = 150;
    private Timer timer;
    private int score = 0;
    private boolean gameOver = false;

    public FlappyBird() {
        JFrame frame = new JFrame("Flappy Bird - BlueJ");
        frame.setSize(400, 600);
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.addKeyListener(this);

        pipes = new ArrayList<Rectangle>();
        addPipe(true);
        addPipe(true);
        addPipe(true);

        timer = new Timer(20, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Background
        g.setColor(Color.cyan);
        g.fillRect(0, 0, 400, 600);

        // Ground
        g.setColor(Color.orange);
        g.fillRect(0, 550, 400, 50);

        // Bird
        g.setColor(Color.red);
        g.fillOval(100, birdY, 20, 20);

        // Pipes
        g.setColor(Color.green);
        for (Rectangle pipe : pipes) {
            g.fillRect(pipe.x, pipe.y, pipe.width, pipe.height);
        }

        // Score
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Score: " + score, 20, 40);

        // Game Over
        if (gameOver) {
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("Game Over", 100, 300);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            birdVelocity += gravity;
            birdY += birdVelocity;

            ArrayList<Rectangle> toRemove = new ArrayList<Rectangle>();
            for (Rectangle pipe : pipes) {
                pipe.x -= 5;

                if (pipe.x + pipe.width < 0) {
                    toRemove.add(pipe);
                }

                // Score
                if (pipe.x + pipe.width == 100) {
                    score++;
                }

                // Collision
                if (pipe.intersects(new Rectangle(100, birdY, 20, 20))) {
                    gameOver = true;
                }
            }

            pipes.removeAll(toRemove);

            if (pipes.size() < 6) {
                addPipe(false);
            }

            if (birdY > 530 || birdY < 0) {
                gameOver = true;
            }

            repaint();
        }
    }

    public void addPipe(boolean start) {
        int height = 50 + new Random().nextInt(200);
        if (start) {
            pipes.add(new Rectangle(400 + pipes.size() * 200, 0, pipeWidth, height));
            pipes.add(new Rectangle(400 + pipes.size() * 200, height + pipeGap, pipeWidth, 600 - height - pipeGap));
        } else {
            int lastX = pipes.get(pipes.size() - 1).x;
            pipes.add(new Rectangle(lastX + 200, 0, pipeWidth, height));
            pipes.add(new Rectangle(lastX + 200, height + pipeGap, pipeWidth, 600 - height - pipeGap));
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            birdVelocity = jump;
            if (gameOver) {
                // Reset
                birdY = 250;
                birdVelocity = 0;
                pipes.clear();
                score = 0;
                gameOver = false;
                addPipe(true);
                addPipe(true);
                addPipe(true);
            }
        }
    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        new FlappyBird();
    }
}

