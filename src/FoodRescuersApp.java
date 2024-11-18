package src.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FoodRescuersApp extends JFrame {

    private JLabel logoLabel;
    private ImageIcon logoIcon;

    public FoodRescuersApp() {
        setTitle("Food Rescuers");
        setSize(500, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        ImageIcon frameIcon = new ImageIcon("E:\\OOAD PROJECT\\Project\\logofr.jpg");  // Ensure logo.png is in the project folder
        setIconImage(frameIcon.getImage());

        JPanel mainPanel = new JPanel(new BorderLayout());

        logoIcon = new ImageIcon("E:\\OOAD PROJECT\\Project\\logofr.jpg"); // Ensure logo.png is in the project folder
        logoLabel = new JLabel();
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(logoLabel, BorderLayout.CENTER);

        scaleImage();

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                scaleImage();
            }
        });

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(245, 77, 38));
        loginButton.setForeground(new Color(255, 255, 255));
        loginButton.setFont(new Font("Verdana", Font.PLAIN, 20));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new src.src.LoginPage();
                dispose();
            }
        });
        buttonPanel.add(loginButton, BorderLayout.WEST);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBackground(new Color(245, 77, 38));
        signUpButton.setForeground(new Color(255, 255, 255));
        signUpButton.setFont(new Font("Verdana", Font.PLAIN, 20));
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new src.src.RegistrationPage();
                dispose();
            }
        });
        buttonPanel.add(signUpButton, BorderLayout.EAST);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == 'l') {
                    loginButton.doClick();
                }
                else if(e.getKeyChar() == 'r') {
                    signUpButton.doClick();
                }
            }
        });
        setFocusable(true);
    }


    private void scaleImage() {
        int width = getWidth();
        int height = getHeight() - 100;

        Image image = logoIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        logoLabel.setIcon(new ImageIcon(image));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FoodRescuersApp().setVisible(true);
            }
        });
    }
}
