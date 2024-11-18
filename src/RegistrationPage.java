package src.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.regex.Pattern;

public class RegistrationPage {
    public static JPanel radioButtonPanel;
    public static JRadioButton radioO;
    public static JRadioButton radioH;
    public static JRadioButton radioI;
    public static JButton registerButton;
    public static JTextField emailField;
    public static JPasswordField passwordField;
    public static JPasswordField confirmPasswordField;
    private static final String url = "jdbc:mysql://localhost:3306/food_management?useSSL=false";
    private static final String username = "root";
    private static final String password = "root";

    public RegistrationPage(){

        Toolkit it=Toolkit.getDefaultToolkit();
        Dimension d=it.getScreenSize();


        JFrame frame = new JFrame("EFMS REGISTRATION");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(255, 255, 204));
        ImageIcon frameIcon = new ImageIcon("E:\\OOAD PROJECT\\Project\\logofr.jpg");
        frame.setIconImage(frameIcon.getImage());
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBackground(new Color(255, 204, 102)); // Darker yellow
        JLabel companyName = new JLabel(" FOOD RESCUERS");
        companyName.setFont(new Font("Arial", Font.BOLD, 20));
        companyName.setForeground(Color.BLACK);

        topPanel.add(companyName, BorderLayout.CENTER);

        // Create a panel for the form components
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50)); // Increased padding for better spacing
        formPanel.setOpaque(false); // Transparent to inherit background

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Create labels and input fields (Input fields now below labels)
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(usernameLabel, gbc);
        Dimension preferredSize = new Dimension(300, 40);
        JTextField usernameField = new JTextField();
        usernameField.setPreferredSize(preferredSize);
        usernameField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2)); // Custom border
        usernameField.setBackground(Color.WHITE);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 16)); // Increase font size
        usernameField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                usernameField.setBackground(new Color(255, 255, 153)); // Change background color on focus
            }

            public void focusLost(FocusEvent evt) {
                usernameField.setBackground(Color.WHITE); // Reset background color
            }
        });


        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        formPanel.add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField();
        passwordField.setPreferredSize(preferredSize);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2)); // Custom border
        passwordField.setBackground(Color.WHITE);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16)); // Increase font size
        passwordField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                passwordField.setBackground(new Color(255, 255, 153)); // Change background color on focus
            }

            public void focusLost(FocusEvent evt) {
                passwordField.setBackground(Color.WHITE); // Reset background color
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(passwordField, gbc);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");

        confirmPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(confirmPasswordLabel, gbc);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setPreferredSize(preferredSize);
        confirmPasswordField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2)); // Custom border
        confirmPasswordField.setBackground(Color.WHITE);
        confirmPasswordField.setFont(new Font("Arial", Font.PLAIN, 16)); // Increase font size
        confirmPasswordField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                confirmPasswordField.setBackground(new Color(255, 255, 153)); // Change background color on focus
            }

            public void focusLost(FocusEvent evt) {
                confirmPasswordField.setBackground(Color.WHITE); // Reset background color
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(confirmPasswordField, gbc);

        JLabel emailLabel = new JLabel("Email ID:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 6;
        formPanel.add(emailLabel, gbc);

        emailField = new JTextField();
        emailField.setPreferredSize(preferredSize);
        emailField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2)); // Custom border
        emailField.setBackground(Color.WHITE);
        emailField.setFont(new Font("Arial", Font.PLAIN, 16)); // Increase font size
        emailField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                emailField.setBackground(new Color(255, 255, 153)); // Change background color on focus
            }

            public void focusLost(FocusEvent evt) {
                emailField.setBackground(Color.WHITE); // Reset background color
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 7;
        formPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 1;
        JLabel typefield = new JLabel("Type:");
        typefield.setFont(new Font("Arial", Font.PLAIN, 16));
        formPanel.add(typefield, gbc);

        radioButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        radioButtonPanel.setOpaque(false);
        radioH = new JRadioButton("Hotel");
        radioH.setFont(new Font("Arial", Font.PLAIN, 16));
        radioH.setOpaque(false);
        radioI = new JRadioButton("Individual");
        radioI.setOpaque(false);
        radioI.setFont(new Font("Arial", Font.PLAIN, 16));
        radioO = new JRadioButton("Organization");
        radioO.setFont(new Font("Arial", Font.PLAIN, 16));
        radioO.setOpaque(false);

        ButtonGroup group = new ButtonGroup();
        group.add(radioH);
        group.add(radioI);
        group.add(radioO);

        radioButtonPanel.add(radioH);
        radioButtonPanel.add(radioI);
        radioButtonPanel.add(radioO);

        gbc.gridx = 1;
        gbc.gridy = 8;
        formPanel.add(radioButtonPanel, gbc);

        // Create the register button and move it to the right side
        registerButton = new JButton("Register");
        registerButton.setBackground(new Color(255, 204, 102)); // Darker yellow button
        registerButton.setForeground(Color.BLACK);
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        formPanel.add(registerButton, gbc);

        // Add listeners for validation and button click
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());
                String email = emailField.getText();

                if (!validatePassword(password)) {
                    JOptionPane.showMessageDialog(frame, "Password must be at least 10 characters, contain 1 uppercase letter, 1 digit, and 1 special character.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(frame, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!emailValid(email)) {
                    JOptionPane.showMessageDialog(frame, "Invalid Email.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    if(!username.isEmpty() && !password.isEmpty())
                        insertData(username, password, email);
                    else throw new SQLException();
                }
                catch (SQLException ef){
                    ef.printStackTrace();
                }
                JOptionPane.showMessageDialog(frame, "Registration successful!");
                sendEmail(email, username);
                frame.dispose();
                new src.src.LoginPage();
            }
        });

        // Add panels to the frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(formPanel, BorderLayout.CENTER);
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    registerButton.doClick();
                }
            }
        });

        int w=frame.getWidth(), h=frame.getHeight();
        frame.setLocation(d.width/2-w/2, d.height/2-h/2);
        frame.setFocusable(true);
        frame.setVisible(true);
    }
    private static void sendEmail(String email, String user){
        try {
            String content = "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <style>\n" +
                    "        body {\n" +
                    "            font-family: Arial, sans-serif;\n" +
                    "            color: #333;\n" +
                    "            background-color: #f9f9f9;\n" +
                    "            margin: 0;\n" +
                    "            padding: 0;\n" +
                    "        }\n" +
                    "        .email-container {\n" +
                    "            max-width: 600px;\n" +
                    "            margin: 0 auto;\n" +
                    "            background: white;\n" +
                    "            border-radius: 10px;\n" +
                    "            overflow: hidden;\n" +
                    "            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);\n" +
                    "        }\n" +
                    "        .header {\n" +
                    "            background-color: #e63946;  /* Soft red color */\n" +
                    "            color: #fff;\n" +
                    "            padding: 20px;\n" +
                    "            text-align: center;\n" +
                    "            font-size: 1.5em;\n" +
                    "        }\n" +
                    "        .header img {\n" +
                    "            max-width: 100px;\n" +
                    "            margin-bottom: 10px;\n" +
                    "        }\n" +
                    "        .content {\n" +
                    "            padding: 20px;\n" +
                    "            text-align: center;\n" +
                    "        }\n" +
                    "        .content h2 {\n" +
                    "            color: #e63946;  /* Soft red color */\n" +
                    "            font-size: 1.8em;\n" +
                    "        }\n" +
                    "        .content p {\n" +
                    "            font-size: 1.1em;\n" +
                    "            line-height: 1.6;\n" +
                    "        }\n" +
                    "        .cta-button {\n" +
                    "            display: inline-block;\n" +
                    "            background-color: #e63946;  /* Soft red color */\n" +
                    "            color: #fff;\n" +
                    "            padding: 12px 25px;\n" +
                    "            border-radius: 30px;\n" +
                    "            font-size: 1em;\n" +
                    "            text-decoration: none;\n" +
                    "            margin-top: 20px;\n" +
                    "        }\n" +
                    "        .footer {\n" +
                    "            background-color: #f1f1f1;\n" +
                    "            color: #666;\n" +
                    "            text-align: center;\n" +
                    "            padding: 20px;\n" +
                    "            font-size: 0.9em;\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "    <div class=\"email-container\">\n" +
                    "        <div class=\"header\">\n" +
                    "            <img src=\"https://res.cloudinary.com/di4ujiaem/image/upload/w_1000,ar_1:1,c_fill,g_auto,e_art:hokusai/v1731508403/logofr_lwbj09.jpg\" alt=\"Logo\">\n" +
                    "            <h1>Thank You for Registering with Us!</h1>\n" +
                    "        </div>\n" +
                    "\n" +
                    "        <div class=\"content\">\n" +
                    "            <h2>Welcome @<mark style=\"color:white\">" + user + "</mark> to Our Community!</h2>\n" +
                    "            <p>We’re thrilled to have you on board. Get ready for exciting updates, and much more!</p>\n" +
                    "            <p>Explore our offerings, and let us make your journey with us delightful.</p>\n" +
                    "            \n" +
                    "        </div>\n" +
                    "\n" +
                    "        <div class=\"footer\">\n" +
                    "            <p>If you have any questions, feel free to <a href=\"mailto:tonly2814@gmail.com\" style=\"color:#e63946;\">Contact us</a>.</p>\n" +
                    "            <p>&copy; 2024 Food Rescuers. All rights reserved.</p>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "</body>\n" +
                    "</html>\n";

            GmailSenderApp.sendEmail(email,"Thankyou For Registering !", "", content);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void insertData(String uname, String pwd, String umail) throws SQLException{
        String insertQuery = "INSERT INTO `creds`(uname, upwd) VALUES (?, ?)";
        String insertQuery2 = "INSERT INTO `u-info`(uid, umail, utype) VALUES (?, ?, ?)";

        String type = null;
        // Establish a connection and execute the query
        try (Connection connection = DriverManager.getConnection(url, username, password);){
            int generatedUID = 0;
            try {
                if (radioH.isSelected()) {
                    type = "Hotel";
                } else if (radioI.isSelected()) {
                    type = "Individual";
                } else if (radioO.isSelected())
                    type = "Organization";
            }
            catch(NullPointerException e){
                e.printStackTrace();
            }
            PreparedStatement pstmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);

            connection.setAutoCommit(false);

            pstmt.setString(1, uname);
            pstmt.setString(2, pwd);

            pstmt.executeUpdate();

            ResultSet generated = pstmt.getGeneratedKeys();
            while(generated.next()){
                generatedUID = generated.getInt(1);
            }

            PreparedStatement pstmt2 = connection.prepareStatement(insertQuery2);

            pstmt2.setInt(1, generatedUID);
            pstmt2.setString(2, umail);
            pstmt2.setString(3, type);
            pstmt2.executeUpdate();
            connection.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new RegistrationPage();
    }

    // Password validation method
    public static boolean validatePassword(String password) {
        String passwordPattern = "^(?=.*[A-Z])(?=.*[!@#$%^&*])(?=.*\\d).{10,}$";
        return Pattern.matches(passwordPattern, password);
    }

    public static boolean emailValid(String email) {
        if(email.isEmpty() || email.length() > 25) return false;
        try {
            String[] user = email.split("@",2);
            for(int i=0;i<user[1].length();i++){
                if(user[1].charAt(i) == '@') return false;
            }
            for(int i=0;i<user[0].length();i++){
                if(Character.isDigit(user[0].charAt(i)) || Character.isAlphabetic(user[0].charAt(i)) || user[0].charAt(i) == '_' || user[0].charAt(i) == '-' || user[0].charAt(i) == '+')
                    continue;
                else return false;
            }
            String dot = user[1].split("[.]")[1];
            if(!(dot.equals("com") || dot.equals("in") || dot.equals("edu"))) return false;
        }
        catch(Exception e) {return false;}
        return true;
    }

}
