import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;

public class ImageViewer extends JFrame {
    private static final String VERSION = "Version 2.0 - Overmind Edition";
    private JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));
    
    private ImagePanel imagePanel;
    private JLabel filenameLabel;
    private JLabel statusLabel;
    private OFImage currentImage;

    public ImageViewer() {
        super("Image Viewer Pro");
        initUI();
    }

    private void initUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setupMenuBar();

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout(6, 6));

        filenameLabel = new JLabel("No file displayed.");
        contentPane.add(filenameLabel, BorderLayout.NORTH);

        imagePanel = new ImagePanel();
        contentPane.add(new JScrollPane(imagePanel), BorderLayout.CENTER);

        statusLabel = new JLabel(VERSION);
        contentPane.add(statusLabel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setupMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        int mask = Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx();

        // File Menu
        JMenu fileMenu = new JMenu("File");
        addMenuItem(fileMenu, "Open", KeyEvent.VK_O, mask, e -> openFile());
        addMenuItem(fileMenu, "Close", KeyEvent.VK_W, mask, e -> closeImage());
        fileMenu.addSeparator();
        addMenuItem(fileMenu, "Quit", KeyEvent.VK_Q, mask, e -> System.exit(0));
        menuBar.add(fileMenu);

        // Filter Menu
        JMenu filterMenu = new JMenu("Filter");
        addMenuItem(filterMenu, "Darker", 0, 0, e -> applyFilter("dark"));
        addMenuItem(filterMenu, "Lighter", 0, 0, e -> applyFilter("light"));
        addMenuItem(filterMenu, "Threshold", 0, 0, e -> applyFilter("threshold"));
        menuBar.add(filterMenu);

        // Help Menu
        JMenu helpMenu = new JMenu("Help");
        addMenuItem(helpMenu, "About", 0, 0, e -> JOptionPane.showMessageDialog(this, "Image Viewer\n" + VERSION));
        menuBar.add(helpMenu);
    }

    private void addMenuItem(JMenu menu, String title, int key, int mask, ActionListener action) {
        JMenuItem item = new JMenuItem(title);
        if (key > 0) item.setAccelerator(KeyStroke.getKeyStroke(key, mask));
        item.addActionListener(action);
        menu.add(item);
    }

    private void openFile() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            currentImage = ImageFileManager.loadImage(file);
            if (currentImage != null) {
                imagePanel.setImage(currentImage);
                filenameLabel.setText("File: " + file.getName());
                statusLabel.setText("Biomass assimilated successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Format not supported.");
            }
        }
    }

    private void closeImage() {
        currentImage = null;
        imagePanel.clearImage();
        filenameLabel.setText("No file displayed.");
        statusLabel.setText("Image cleared.");
    }

    private void applyFilter(String type) {
        if (currentImage == null) {
            statusLabel.setText("No image to filter.");
            return;
        }
        switch (type) {
            case "dark": currentImage.darker(); break;
            case "light": currentImage.lighter(); break;
            case "threshold": currentImage.threshold(); break;
        }
        repaint();
        statusLabel.setText("Filter " + type + " applied.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ImageViewer::new);
    }
}