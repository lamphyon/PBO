import java.awt.*;
import javax.swing.*;

public class ImagePanel extends JPanel {
    private OFImage currentImage;

    public ImagePanel() {
        setBackground(Color.DARK_GRAY);
    }

    public void setImage(OFImage image) {
        this.currentImage = image;
        repaint();
    }

    public void clearImage() {
        currentImage = null;
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (currentImage != null) {
            int pWidth = getWidth();
            int pHeight = getHeight();
            int iWidth = currentImage.getWidth();
            int iHeight = currentImage.getHeight();

            double ratio = Math.min((double) pWidth / iWidth, (double) pHeight / iHeight);
            int dWidth = (int) (iWidth * ratio);
            int dHeight = (int) (iHeight * ratio);
            int x = (pWidth - dWidth) / 2;
            int y = (pHeight - dHeight) / 2;

            g.drawImage(currentImage, x, y, dWidth, dHeight, null);
        } else {
            g.setColor(Color.WHITE);
            g.drawString("No Biomass Detected...", getWidth()/2 - 50, getHeight()/2);
        }
    }
}