import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {
    static final int WIDTH = 1000;
    static final int HEIGHT = 600;
    
    private Thread gameThread;
    private Paddle player1, player2;
    private Ball ball;
    private Score score;

    public GamePanel() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        
        score = new Score(WIDTH, HEIGHT);
        
        resetPositions();
        
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void resetPositions() {
        player1 = new Paddle(10, (HEIGHT/2)-50, 20, 100, 1);
        player2 = new Paddle(WIDTH-30, (HEIGHT/2)-50, 20, 100, 2);
        ball = new Ball(WIDTH/2, (int)(Math.random() * HEIGHT), 20, 20);
    }

    private void checkCollision() {
        if(ball.y <= 0 || ball.y >= HEIGHT - 20) ball.setYDir(-ball.yVel);
        
        if(ball.intersects(player1) || ball.intersects(player2)) {
            ball.setXDir(-ball.xVel);
        }

        if(ball.x <= 0) { 
            score.p2++;
            resetPositions();
        }
        if(ball.x >= WIDTH) { 
            score.p1++;
            resetPositions();
        }
    }
    
    public void updateLogic() {
        player1.move();
        player2.move();
        ball.move();
        checkCollision();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        player1.draw(g);
        player2.draw(g);
        ball.draw(g);
        score.draw(g);
        Toolkit.getDefaultToolkit().sync();
    }

    public void run() {
        while(gameThread != null) {
            updateLogic();
            repaint();
            try { Thread.sleep(10); } catch (Exception e) {}
        }
    }

    private class AL extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            player1.keyPressed(e);
            player2.keyPressed(e);
        }
        public void keyReleased(KeyEvent e) {
            player1.keyReleased(e);
            player2.keyReleased(e);
        }
    }
}