package src.src;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.imageio.ImageIO;

public class ProfilePage {

    private static int uid = 0;
    private static String utype = null;
    private static boolean isEditable = false; // Flag for edit mode
    private static JTextField nameField;
    private static JTextField phoneField;
    private static JTextField addressField;
    private static JTextField emailField;
    private static JLabel profileImageLabel;
    private static JLabel profileImageDisplay;
    private static JButton editButton;
    private static JButton saveButton;
    private static JButton discardButton;
    private static JButton uploadPhotoButton;
    private static JButton removePhotoButton;
    private static JPanel radioButtonPanel;
    private static JRadioButton radioO;
    private static JRadioButton radioH;
    private static JRadioButton radioI;
    private static String path;
    private static String outpath = "E:\\OOAD PROJECT\\Project\\pfimage\\";
    private static final String url = "jdbc:mysql://localhost:3306/food_management?useSSL=false";
    private static final String username = "root";
    private static final String password = "root";

    public ProfilePage(){
        JFrame frame = new JFrame("Profile Page");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600); // Set frame size to three-fourths of the screen
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(255, 255, 204)); // Light yellow background

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBackground(new Color(255, 204, 102)); // Darker yellow
        JLabel companyName = new JLabel(" FOOD RESCUERS");
        companyName.setFont(new Font("Arial", Font.BOLD, 20));
        companyName.setForeground(Color.BLACK);
        topPanel.add(companyName);
        frame.add(topPanel, BorderLayout.NORTH);
        ImageIcon frameIcon = new ImageIcon("E:\\OOAD PROJECT\\Project\\logofr.jpg");  // Ensure logo.png is in the project folder
        frame.setIconImage(frameIcon.getImage());

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50)); // Padding for better spacing
        formPanel.setBackground(new Color(255, 255, 204)); // Match background color

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        profileImageDisplay = new JLabel();
        profileImageDisplay.setPreferredSize(new Dimension(150, 150)); // Square size for profile image
        profileImageDisplay.setOpaque(false);
        profileImageDisplay.setBackground(Color.LIGHT_GRAY); // Placeholder color
        profileImageDisplay.setHorizontalAlignment(JLabel.CENTER);
        profileImageDisplay.setVerticalAlignment(JLabel.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across both columns
        formPanel.add(profileImageDisplay, gbc);

        profileImageLabel = new JLabel("Profile Photo: ");
        gbc.gridy = 1;
        formPanel.add(profileImageLabel, gbc);

        uploadPhotoButton = new JButton("Upload Photo");
        uploadPhotoButton.addActionListener(e -> uploadImage());
        uploadPhotoButton.setEnabled(isEditable);
        gbc.gridwidth = 1; // Reset gridwidth
        gbc.gridx = 1;
        formPanel.add(uploadPhotoButton, gbc);

        // Remove Photo Button (Initially hidden)
        removePhotoButton = new JButton("Remove Photo");
        removePhotoButton.addActionListener(e -> removeImage());
        removePhotoButton.setEnabled(isEditable);
        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(removePhotoButton, gbc);

        // Other form fields
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1; // Reset to default width
        formPanel.add(new JLabel("Type:"), gbc); // Adding Type label

        radioButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        radioH = new JRadioButton("Hotel");
        radioI = new JRadioButton("Individual");
        radioO = new JRadioButton("Organization");

        ButtonGroup group = new ButtonGroup();
        group.add(radioH);
        group.add(radioI);
        group.add(radioO);

        radioButtonPanel.add(radioH);
        radioButtonPanel.add(radioI);
        radioButtonPanel.add(radioO);

        gbc.gridx = 1;
        radioButtonPanel.setEnabled(false);
        radioH.setEnabled(false);
        radioI.setEnabled(false);
        radioO.setEnabled(false);
        formPanel.add(radioButtonPanel, gbc);

        // Name Field
        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(300, 30));
        nameField.setEditable(isEditable);
        nameField.setText("");
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        formPanel.add(nameField, gbc);

        // Phone Number
        phoneField = new JTextField();
        phoneField.setPreferredSize(new Dimension(300, 30));
        phoneField.setEditable(isEditable);
        phoneField.setText("");
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(new JLabel("Phone Number:"), gbc);
        gbc.gridx = 1;
        formPanel.add(phoneField, gbc);

        // Address
        addressField = new JTextField();
        addressField.setPreferredSize(new Dimension(300, 30));
        addressField.setEditable(isEditable);
        addressField.setText("");
        gbc.gridx = 0;
        gbc.gridy = 6;
        formPanel.add(new JLabel("Address:"), gbc);
        gbc.gridx = 1;
        formPanel.add(addressField, gbc);

        // Email ID
        emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(300, 30));
        emailField.setEditable(false);
        gbc.gridx = 0;
        gbc.gridy = 7;
        formPanel.add(new JLabel("Email ID:"), gbc);
        gbc.gridx = 1;
        formPanel.add(emailField, gbc);

        checkProfileStatus();
        // Buttons for Edit, Save Changes, Discard Changes
        editButton = new JButton("Edit");
        editButton.setBackground(new Color(255, 204, 102)); // Yellow
        editButton.addActionListener(e -> toggleEditMode());
        gbc.gridx = 0;
        gbc.gridy = 8;
        formPanel.add(editButton, gbc);

        saveButton = new JButton("Save Changes");
        saveButton.setBackground(new Color(102, 255, 102)); // Green
        saveButton.addActionListener(e -> saveChanges());
        saveButton.setVisible(false); // Initially hidden
        gbc.gridx = 1; // Right align
        formPanel.add(saveButton, gbc);

        discardButton = new JButton("Discard Changes");
        discardButton.setBackground(new Color(255, 102, 102)); // Red
        discardButton.addActionListener(e -> discardChanges());
        discardButton.setVisible(false); // Initially hidden
        gbc.gridx = 1; // Right align
        gbc.gridy = 9;
        formPanel.add(discardButton, gbc);

        frame.add(formPanel, BorderLayout.CENTER);
        Toolkit it=Toolkit.getDefaultToolkit();
        Dimension d=it.getScreenSize();
        int w=frame.getWidth(), h=frame.getHeight();
        frame.setLocation(d.width/2-w/2, d.height/2-h/2);

        frame.setVisible(true);
    }
    public static void main(String[] args) {
        // Create a new JFrame
        new ProfilePage();
    }

    private static void uploadImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose an image file");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Images", "jpg", "jpeg", "png", "gif"));

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fileChooser.getSelectedFile();
                if(file != null){
                    path = file.getAbsolutePath();
                    System.out.println(path);
                }
                BufferedImage img = ImageIO.read(file);

                // Resize to a square format (150x150)
                Image scaledImage = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                profileImageDisplay.setIcon(new ImageIcon(scaledImage));
                profileImageLabel.setText("Profile Photo: " + file.getName());

                uploadPhotoButton.setVisible(false);
                removePhotoButton.setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error loading image.");
            }
        }
    }
    private static void displayProfile(String outimg){
        File file = new File(outimg);
        try{
            BufferedImage img = ImageIO.read(file);
            // Resize to a square format (150x150)
            Image scaledImage = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            profileImageDisplay.setIcon(new ImageIcon(scaledImage));
            profileImageLabel.setText("Profile Photo: " + file.getName());
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error loading image.");
        }
    }
    private static void checkProfileStatus(){
        try{
            InputStream profimg = null;

            Connection conn = DriverManager.getConnection(url, username, password);
            uid = SessionManager.getInstance().getUserId();
            utype = SessionManager.getInstance().getUserType();

            String checkProf = "SELECT uprofimg, umail, uaddr, uphone, name from `u-info` WHERE uid = ?";
            PreparedStatement pstmt = conn.prepareStatement(checkProf);
            pstmt.setInt(1, uid);

            ResultSet rs = pstmt.executeQuery();
            String uphone = null;
            boolean fg = false;
            while(rs.next()){
                profimg = rs.getBinaryStream("uprofimg");
                if(!rs.wasNull())
                    fg = true;

                uphone = rs.getString("uphone");
                System.out.println(profimg);
                emailField.setText(rs.getString("umail"));

                if(uphone != null && (isEditable || !isEditable)) {
                    nameField.setText(rs.getString("name"));
                    phoneField.setText(""+uphone);
                    addressField.setText(rs.getString("uaddr"));

                    if(utype.equals("Hotel")){
                        radioH.setSelected(true);
                    }
                    else if(utype.equals("Individual")){
                        radioI.setSelected(true);
                    }
                    else if(utype.equals("Organization")){
                        radioO.setSelected(true);
                    }

                    String outimg = outpath + uid + "-profile" + ".jpeg";
                    FileOutputStream fout = new FileOutputStream(outimg);

                    byte[] by = new byte[1024];
                    int c = -1;

                    if(fg) {
                        while((c = profimg.read(by)) != -1) {
                            fout.write(by);
                        }
                        uploadPhotoButton.setVisible(false);
                        removePhotoButton.setVisible(true);
                        displayProfile(outimg);
                    }
                    break;
                }
                else if(!fg && isEditable == true) {
                    uploadPhotoButton.setVisible(true);
                    removePhotoButton.setVisible(false);
                    break;
                }

            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private static void removeImage() {
        profileImageDisplay.setIcon(null); // Remove image icon
        profileImageLabel.setText("Profile Photo:"); // Reset label text
        path = null;
        // Show "Upload Photo" button and hide "Remove Photo" button
        uploadPhotoButton.setVisible(true);
        removePhotoButton.setVisible(false);
    }

    // Toggle edit mode
    private static void toggleEditMode() {
        isEditable = !isEditable;
        nameField.setEditable(isEditable);
        phoneField.setEditable(isEditable);
        addressField.setEditable(isEditable);
        uploadPhotoButton.setEnabled(isEditable);
        removePhotoButton.setEnabled(isEditable);
        editButton.setVisible(!isEditable);
        saveButton.setVisible(isEditable);
        discardButton.setVisible(isEditable);
    }

    // Save changes
    private static void saveChanges() {
        String utype=null;
        if(radioH.isSelected()){
            utype = "Hotel";
        }
        else if(radioI.isSelected()){
            utype = "Individual";
        }
        else utype = "Organization";
        updateTable(path, addressField.getText(), phoneField.getText(), nameField.getText(), utype);
        JOptionPane.showMessageDialog(null, "Changes saved successfully!");
        toggleEditMode();
    }

    // Discard changes
    private static void discardChanges() {
        toggleEditMode();
        JOptionPane.showMessageDialog(null, "Changes discarded!");
    }

    private static void updateTable(String prof, String addr, String phone, String name, String utype){
        try{
            Connection conn = DriverManager.getConnection(url, username, password);
            String updateQuery = "UPDATE `u-info` SET uaddr=?, uphone=?, name=?, utype=?, uprofimg=? WHERE uid=?";

            PreparedStatement pstmt = conn.prepareStatement(updateQuery);

            if(SessionManager.getInstance().getUserType().equals("Hotel")) addr = name+" , "+addr;
            pstmt.setString(1, addr);
            pstmt.setString(2, phone);
            pstmt.setString(3, name);
            pstmt.setString(4, utype);

            FileInputStream fi;
            if(prof != null)
                fi = new FileInputStream(prof);
            else fi = null;
            pstmt.setBinaryStream(5, fi);
            pstmt.setInt(6, uid);

            pstmt.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        try(Connection conn = DriverManager.getConnection(url, username, password);){
            String geoUpdate = "INSERT INTO `geo-info`(uid, ulat, ulong, utype) VALUES(?,?,?,?)";
            PreparedStatement pstmt2 = conn.prepareStatement(geoUpdate);
            if(addr != null) {
                FindLocation find = new src.src.FindLocation(addr);
                pstmt2.setInt(1, uid);
                System.out.println(find.getlat());
                System.out.println(find.getlong());
                pstmt2.setDouble(2, Double.parseDouble(find.getlat()));
                pstmt2.setDouble(3, Double.parseDouble(find.getlong()));
                pstmt2.setString(4, utype);

                pstmt2.executeUpdate();
            }
        }
        catch(Exception e){
            System.out.println("Entry Present");
            try(Connection conn = DriverManager.getConnection(url, username, password);){
                String geoUpdate = "UPDATE `geo-info` SET ulat=?, ulong=? WHERE uid=?";

                PreparedStatement pstmt2 = conn.prepareStatement(geoUpdate);
                FindLocation find = new src.src.FindLocation(addr);

                System.out.println(find.getlat());
                System.out.println(find.getlong());
                pstmt2.setDouble(1, Double.parseDouble(find.getlat()));
                pstmt2.setDouble(2, Double.parseDouble(find.getlong()));
                pstmt2.setInt(3, uid);

                pstmt2.executeUpdate();
            }
            catch (Exception ef){
                ef.printStackTrace();
            }
        }
    }

}
