import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GatekeeperFrame {
    private final String VALID_USER = "Asep123";
    private final String VALID_PASS = "halo";

    private JTextField fieldUser;
    private JPasswordField fieldPass;
    private JFrame mainFrame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GatekeeperFrame().init());
    }

    public void init() {
        mainFrame = new JFrame("Gatekeeper Access");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel content = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        content.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        fieldUser = new JTextField(15);
        content.add(fieldUser, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        content.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        fieldPass = new JPasswordField(15);
        content.add(fieldPass, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel actionPanel = new JPanel();
        JButton loginBtn = new JButton("Login");
        JButton clearBtn = new JButton("Cancel");

        loginBtn.addActionListener(new AccessHandler());
        clearBtn.addActionListener(new ResetHandler());

        actionPanel.add(loginBtn);
        actionPanel.add(clearBtn);
        content.add(actionPanel, gbc);

        mainFrame.getContentPane().add(content);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    private class AccessHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String inputUser = fieldUser.getText();
            String inputPass = new String(fieldPass.getPassword());

            if (VALID_USER.equals(inputUser) && VALID_PASS.equals(inputPass)) {
                JOptionPane.showMessageDialog(mainFrame, "Login Granted! The Swarm welcomes you.");
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Login Denied. Unidentified biomass.");
            }
        }
    }

    private class ResetHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            fieldUser.setText("");
            fieldPass.setText("");
            fieldUser.requestFocusInWindow();
        }
    }
}