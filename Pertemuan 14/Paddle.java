import java.awt.*;
import java.awt.event.*;

public class Paddle extends Rectangle {
    int id;
    int yVel;
    int speed = 10; 

    public Paddle(int x, int y, int w, int h, int id) {
        super(x, y, w, h);
        this.id = id;
    }

    public void keyPressed(KeyEvent e) {
        switch(id) {
            case 1 -> {
                if(e.getKeyCode() == KeyEvent.VK_W) setYDir(-speed);
                if(e.getKeyCode() == KeyEvent.VK_S) setYDir(speed);
            }
            case 2 -> {
                if(e.getKeyCode() == KeyEvent.VK_UP) setYDir(-speed);
                if(e.getKeyCode() == KeyEvent.VK_DOWN) setYDir(speed);
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        switch(id) {
            case 1 -> {
                if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S) setYDir(0);
            }
            case 2 -> {
                if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) setYDir(0);
            }
        }
    }

    public void setYDir(int yDir) { yVel = yDir; }
    public void move() { y += yVel; }

    public void draw(Graphics g) {
        if(id == 1) g.setColor(new Color(57, 255, 20));
        else g.setColor(new Color(255, 0, 127));
        
        g.fillRoundRect(x, y, width, height, 15, 15);
    }
}