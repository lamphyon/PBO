import java.awt.*;

public class Score {
    int width;
    int height;
    int p1;
    int p2;

    public Score(int w, int h) {
        this.width = w;
        this.height = h;
    }

    public void draw(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        for(int i = 0; i < height; i += 40) {
            g.drawLine(width/2, i, width/2, i+20);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Monospaced", Font.BOLD, 55));

        g.drawString(String.format("%02d", p1), (width/2)-100, 60);
        g.drawString(String.format("%02d", p2), (width/2)+40, 60);
    }
}