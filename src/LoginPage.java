package src.src;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;

public class LoginPage {
    private static final String url = "jdbc:mysql://localhost:3306/food_management?useSSL=false";
    private static final String username = "root";
    private static final String password = "root";
    private static int uid = 0;
    private static String utype = null;

    public boolean validateRequest(JFrame frame,JTextField usernameField,JTextField passwordField, String uname, String upwd){
        String checkQuery = "SELECT c.uid, c.uname, c.upwd, u.utype from `creds` c JOIN `u-info` u on u.uid = c.uid";
        try{
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            ResultSet creds = stmt.executeQuery(checkQuery);
            String uname_="", upwd_="";

            while(creds.next()){
                uname_ = creds.getString("uname");
                upwd_ = creds.getString("upwd");
                if(uname.equals(uname_) && upwd.equals(upwd_)){
                    uid = creds.getInt("uid");
                    utype = creds.getString("utype");
                    return true;
                }
            }
            JOptionPane.showMessageDialog(frame, "Login Failed : ","Wrong UserName or Password !", JOptionPane.ERROR_MESSAGE);
            usernameField.setText("");
            passwordField.setText("");
            return false;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public LoginPage(){

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width * 0.75);
        int height = (int) (screenSize.height * 0.75);

        JFrame frame = new JFrame("EFMS LOGIN");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(255, 255, 240));
        ImageIcon frameIcon = new ImageIcon("E:\\OOAD PROJECT\\Project\\logofr.jpg");
        frame.setIconImage(frameIcon.getImage());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(255, 230, 180));
        //JLabel logo = new JLabel(new ImageIcon("E:\\OOAD PROJECT\\Project\\logofr.jpg"));
        JLabel companyName = new JLabel("FOOD RESCUERS", JLabel.CENTER);
        companyName.setFont(new Font("Arial", Font.BOLD, 28));
        companyName.setForeground(new Color(128, 64, 0));

        //topPanel.add(logo, BorderLayout.WEST);
        topPanel.add(companyName, BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(new CompoundBorder(
                new LineBorder(new Color(255, 204, 153), 2, true),
                new EmptyBorder(30, 50, 30, 50)
        ));
        formPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        usernameLabel.setForeground(new Color(102, 51, 0));
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(usernameLabel, gbc);

        JTextField usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(300, 40));
        usernameField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        usernameField.setBackground(Color.WHITE);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 16));
        usernameField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                usernameField.setBackground(new Color(255, 255, 200));
            }
            public void focusLost(FocusEvent evt) {
                usernameField.setBackground(Color.WHITE);
            }
        });
        gbc.gridy = 1;
        formPanel.add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));
        passwordLabel.setForeground(new Color(102, 51, 0));
        gbc.gridy = 2;
        formPanel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(300, 40));
        passwordField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        passwordField.setBackground(Color.WHITE);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                passwordField.setBackground(new Color(255, 255, 200));
            }
            public void focusLost(FocusEvent evt) {
                passwordField.setBackground(Color.WHITE);
            }
        });
        gbc.gridy = 3;
        formPanel.add(passwordField, gbc);


        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setPreferredSize(new Dimension(120, 40));
        loginButton.setBackground(new Color(255, 153, 51));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(255, 140, 0));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(255, 153, 51));
            }
        });
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(loginButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Username and password cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean flag = validateRequest(frame, usernameField, passwordField, username, password);
                if(flag){
                    JOptionPane.showMessageDialog(frame, "Login successful! Opening HomePage...");
                    src.src.SessionManager.getInstance().setUserData(uid, utype);
                    frame.dispose();
                    if(utype.equals("Individual") || utype.equals("Organization")) new src.src.HomePage();
                    else new src.src.HotelPage().setVisible(true);
                }
            }
        });

        passwordField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loginButton.doClick();
                }
            }
        });

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(formPanel, BorderLayout.CENTER);

        Toolkit it=Toolkit.getDefaultToolkit();
        Dimension d=it.getScreenSize();
        int w=frame.getWidth(), h=frame.getHeight();
        frame.setLocation(d.width/2-w/2, d.height/2-h/2);
        frame.setVisible(true);
        frame.setFocusable(true);
    }
    public static void main(String[] args) {
        new LoginPage();
    }
}
