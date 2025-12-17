import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame {
    private final GamePanel gameArea;

    public GameFrame() {
        gameArea = new GamePanel();
        
        this.getContentPane().add(gameArea);
        this.setTitle("Neon Pong Engine");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
        gameArea.requestFocusInWindow();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameFrame());
    }
}