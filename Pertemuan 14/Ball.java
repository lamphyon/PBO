import java.awt.*;
import java.util.*;

public class Ball extends Rectangle {
    Random rand;
    int xVel;
    int yVel;
    int initialSpeed = 3; 

    public Ball(int x, int y, int w, int h) {
        super(x, y, w, h);
        rand = new Random();
        
        int rX = rand.nextBoolean() ? 1 : -1;
        setXDir(rX * initialSpeed);

        int rY = rand.nextBoolean() ? 1 : -1;
        setYDir(rY * initialSpeed);
    }

    public void setXDir(int xDir) { xVel = xDir; }
    public void setYDir(int yDir) { yVel = yDir; }
    public void move() {
        x += xVel;
        y += yVel;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, width, height);
    }
}