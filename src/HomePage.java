package src.src;

import javax.swing.*;
import javax.xml.transform.Result;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.*;

import java.sql.DriverManager;

public class HomePage {

    private static String url = "jdbc:mysql://localhost:3306/food_management?useSSL=false";
    private static String username = "root";
    private static String password = "root";
    private static int uid = SessionManager.getInstance().getUserId();
    private static String utype = SessionManager.getInstance().getUserType();
    private static JPanel foodCardsPanel;
    private static JPanel mainFoodPanel;
    private static JPanel above1KmPanel;
    private static JPanel under1KmPanel;
    private static JPanel above1KmCardsPanel;
    private static JPanel under1KmCardsPanel;

    public HomePage(){

        JFrame frame = new JFrame("Food Rescuers");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(255, 255, 204));
        ImageIcon frameIcon = new ImageIcon("E:\\OOAD PROJECT\\Project\\logofr.jpg");  // Ensure logo.png is in the project folder
        frame.setIconImage(frameIcon.getImage());
        // Top Panel with title and navigation tabs
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        topPanel.setBackground(new Color(255, 204, 102));

        JLabel companyName = new JLabel(" FOOD RESCUERS");
        companyName.setFont(new Font("Arial", Font.BOLD, 20));
        companyName.setForeground(Color.BLACK);
        topPanel.add(companyName, BorderLayout.WEST);

        JPanel tabPanel = new JPanel(new GridLayout(1, 4));  // Updated to have 4 columns
        tabPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel individualOrgTab = new JLabel(utype, JLabel.CENTER);
        individualOrgTab.setFont(new Font("Arial", Font.BOLD, 14));
        individualOrgTab.setForeground(Color.BLACK);

        JButton profileTab = new JButton("PROFILE");
        profileTab.setIcon(new ImageIcon("path/to/profile_icon.png"));  // Profile icon
        profileTab.setHorizontalTextPosition(SwingConstants.RIGHT);
        profileTab.setBackground(new Color(144, 238, 144));  // Light green background for the button
        profileTab.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        addHoverEffect(profileTab);  // Adding hover effect to the profile button

        JButton signOutButton = new JButton("Logout");
        signOutButton.setIcon(new ImageIcon("path/to/logout_icon.png"));  // Logout icon
        signOutButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        signOutButton.setBackground(new Color(255, 182, 193));  // Light red background for the button
        signOutButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        addHoverEffect(signOutButton);  // Adding hover effect to the logout button

        JButton refreshButton = new JButton("Refresh");
        refreshButton.setIcon(new ImageIcon("path/to/refresh_icon.png"));
        refreshButton.setHorizontalTextPosition(SwingConstants.RIGHT);
        refreshButton.setBackground(new Color(173, 216, 230));
        refreshButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        addHoverEffect(refreshButton);

        tabPanel.add(individualOrgTab);
        tabPanel.add(profileTab);
        tabPanel.add(signOutButton);
        tabPanel.add(refreshButton);

        topPanel.add(tabPanel, BorderLayout.SOUTH);
        frame.add(topPanel, BorderLayout.NORTH);

        profileTab.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new src.src.ProfilePage();
            }
        }
        );

        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SessionManager.getInstance().clearSession();
                new src.src.FoodRescuersApp();
                frame.dispose();
            }
        }
        );
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshPanel();
            }
        });

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        centerPanel.setBackground(new Color(255,255,204));

        JLabel availableFoodLabel = new JLabel("AVAILABLE FOOD", JLabel.CENTER);
        availableFoodLabel.setFont(new Font("Arial", Font.BOLD, 18));
        centerPanel.add(availableFoodLabel, BorderLayout.NORTH);

        mainFoodPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        mainFoodPanel.setOpaque(false);
        under1KmPanel = new JPanel();
        under1KmPanel.setLayout(new BorderLayout());
        under1KmPanel.setBackground(new Color(255,255,204));
        JLabel under1KmLabel = new JLabel("Under 1 Km", JLabel.CENTER);
        under1KmLabel.setFont(new Font("Arial", Font.BOLD, 18));
        under1KmLabel.setForeground(new Color(0, 0, 0));
        under1KmPanel.add(under1KmLabel, BorderLayout.NORTH);

        under1KmCardsPanel = new JPanel(new GridLayout(0, 1, 5, 2));
        under1KmCardsPanel.setBackground(new Color(255,255,204));

        JScrollPane under1KmScrollPane = new JScrollPane(under1KmCardsPanel);
        under1KmScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        under1KmScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        under1KmPanel.add(under1KmScrollPane, BorderLayout.CENTER);

        above1KmPanel = new JPanel();
        above1KmPanel.setLayout(new BorderLayout());
        above1KmPanel.setBackground(new Color(255,255,204));
        JLabel above1KmLabel = new JLabel("Above 1 Km", JLabel.CENTER);
        above1KmLabel.setFont(new Font("Arial", Font.BOLD, 18));
        above1KmLabel.setForeground(new Color(0, 0, 0));
        above1KmPanel.add(above1KmLabel, BorderLayout.NORTH);

        above1KmCardsPanel = new JPanel(new GridLayout(0, 1, 5, 2));  // Grid layout for 2 columns, 3 rows
        above1KmCardsPanel.setBackground(new Color(255,255,204));

        JScrollPane above1KmScrollPane = new JScrollPane(above1KmCardsPanel);
        above1KmScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        above1KmScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        above1KmPanel.add(above1KmScrollPane, BorderLayout.CENTER);

        mainFoodPanel.add(under1KmPanel);
        mainFoodPanel.add(above1KmPanel);

        centerPanel.add(mainFoodPanel);

        refreshPanel();
        frame.add(centerPanel);
        Toolkit it=Toolkit.getDefaultToolkit();
        Dimension d=it.getScreenSize();
        int w=frame.getWidth(), h=frame.getHeight();
        frame.setLocation(d.width/2-w/2, d.height/2-h/2);
        frame.setVisible(true);

        JOptionPane.showMessageDialog(null,"Update Address in Profile","Note !", JOptionPane.INFORMATION_MESSAGE);
    }
    public static void main(String[] args) {
        new HomePage();
    }
    private void addHoverEffect(JButton button) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(button.getBackground().darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(button.getBackground().brighter());
            }
        });
    }
    private static JPanel getHotelInfo(ResultSet rs) throws SQLException {
        String hname = rs.getString("hotelname");
        int fcost = rs.getInt("fcost");
        int fquan = rs.getInt("fquan");
        int order_id = rs.getInt("order_id");
        String fname = rs.getString("fname");
        JPanel foodCard = createFoodCard(hname,""+ fquan,""+ fcost, fname);
        foodCard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showAcceptBox("Hotel " + hname,""+ fquan,""+ fcost, fname, order_id);
            }
        });
        return foodCard;
    }
    private static void refreshPanel(){
        under1KmCardsPanel.removeAll();
        above1KmCardsPanel.removeAll();

        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(url, username, password);

            Statement stmt = connection.createStatement();
            Statement stmt2 = connection.createStatement();

            //add here the under and above cards for addr is not null
            //using try-catch
            //add if addr is null, just display it in above panel

            String userSql = "SELECT ulat, ulong FROM `geo-info` WHERE uid="+uid;

            try{
                ResultSet userPos = stmt2.executeQuery(userSql);
                double currlat = 0,currlong = 0;
                while(userPos.next()) {
                     currlat = userPos.getDouble("ulat");
                     currlong = userPos.getDouble("ulong");
                }
                try(Connection conn = DriverManager.getConnection(url, username, password);){
                    String hotelRange = "SELECT ulat, ulong, uid, " +
                            "(6371 * acos(cos(radians(?)) * cos(radians(ulat)) * " +
                            "cos(radians(? - ulong)) + sin(radians(?)) * sin(radians(ulat)))) AS distance " +
                            "FROM `geo-info` WHERE utype='Hotel' " +
                            "ORDER BY distance";

                    PreparedStatement stmt3 = conn.prepareStatement(hotelRange);

                    stmt3.setDouble(1, currlat);
                    stmt3.setDouble(2, currlong);
                    stmt3.setDouble(3, currlat);

                    ResultSet hotelPos = stmt3.executeQuery();

                    while(hotelPos.next()){
                        double hotellat = hotelPos.getDouble("ulat");
                        double hotelLong = hotelPos.getDouble("ulong");
                        Double dist = hotelPos.getDouble("distance");
                        int hid = hotelPos.getInt("uid");

                        String sql = "SELECT * FROM available_food WHERE uid="+hid;
                        ResultSet rs = stmt.executeQuery(sql);

                        while(rs.next()) {
                            JPanel foodCard = getHotelInfo(rs);

                            if(dist <= 1){
                                System.out.println("In try, Below 1km : "+dist);
                                under1KmCardsPanel.add(foodCard);
                            }
                            else {
                                System.out.println("In try, Above 1km : "+dist);
                                above1KmCardsPanel.add(foodCard);
                            }

                            above1KmCardsPanel.revalidate();
                            above1KmCardsPanel.repaint();
                            under1KmCardsPanel.revalidate();
                            under1KmCardsPanel.repaint();
                        }
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
            catch (Exception e){
                Connection conn2 = DriverManager.getConnection(url, username, password);
                String sq = "SELECT * FROM `available_food`";

                Statement stmt4 = conn2.createStatement();
                ResultSet rs = stmt4.executeQuery(sq);
                JPanel foodCard = null;
                try {
                    foodCard = getHotelInfo(rs);
                }
                catch (Exception ef) {
                    ef.printStackTrace();
                }
                above1KmCardsPanel.add(foodCard);
                e.printStackTrace();
            }

            System.out.println("Connected to the schema!");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }

        }
    }
    public static JPanel createFoodCard(String hotelName, String quantity, String COST, String foodName) {
        JPanel foodCard = new JPanel();
        foodCard.setLayout(new GridLayout(2, 1, 2, 2));
        foodCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        foodCard.setBackground(new Color(255, 255, 255));
        foodCard.setPreferredSize(new Dimension(300, 140));

        JPanel row1 = new JPanel(new GridLayout(1, 2, 10, 10));
        row1.setBackground(new Color(255, 255, 255));
        row1.add(createLabelFieldPanel("HOTEL", hotelName));
        row1.add(createLabelFieldPanel("QUANTITY", quantity));

        JPanel row2 = new JPanel(new GridLayout(1, 2, 10, 10));
        row2.setBackground(new Color(255, 255, 255));
        row2.add(createLabelFieldPanel("FOOD", foodName));
        row2.add(createLabelFieldPanel("COST", COST));

        foodCard.add(row1);
        foodCard.add(row2);

        return foodCard;
    }

    private static JPanel createLabelFieldPanel(String labelText, String fieldText) {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel label = new JLabel(labelText, JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(new Color(0, 0, 0));

        JTextField textField = new JTextField(fieldText);
        textField.setEditable(false);  // Make the text field non-editable
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setPreferredSize(new Dimension(100, 40));
        textField.setBackground(new Color(240, 240, 240));
        textField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        textField.setHorizontalAlignment(JTextField.CENTER);

        panel.add(label, BorderLayout.NORTH);
        panel.add(textField, BorderLayout.CENTER);

        return panel;
    }

    private static void showAcceptBox(String hotelName, String quantity, String COST, String foodName, int order_id) {
        JDialog acceptBox = new JDialog();
        acceptBox.setSize(300, 300);
        acceptBox.setTitle("Accept Food Donation");
        acceptBox.setLayout(new BorderLayout());

        JPanel infoPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        infoPanel.add(createLabelFieldPanel("Hotel Name", hotelName));
        infoPanel.add(createLabelFieldPanel("Quantity", quantity));
        infoPanel.add(createLabelFieldPanel("COST", COST));
        infoPanel.add(createLabelFieldPanel("Food", foodName));
        acceptBox.add(infoPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton acceptButton = new JButton("Accept");
        JButton cancelButton = new JButton("Cancel");

        acceptButton.setBackground(new Color(144, 238, 144));  // Light green for accept
        cancelButton.setBackground(new Color(255, 182, 193));  // Light red for cancel

        buttonPanel.add(acceptButton);
        buttonPanel.add(cancelButton);

        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(acceptBox, "Food accepted!");
                String acceptQuery = "INSERT INTO `history-food`(uid, utype, hotelname, fquan, fcost, fname, order_id) VALUES(?,?,?,?,?,?,?)";
                String deleteQuery = "DELETE FROM `available_food` WHERE order_id=?";

                try(Connection conn = DriverManager.getConnection(url, username, password);){
                    PreparedStatement pstmt = conn.prepareStatement(acceptQuery);
                    PreparedStatement pstmt2 = conn.prepareStatement(deleteQuery);

                    pstmt.setInt(1, uid);
                    pstmt.setString(2, utype);
                    pstmt.setString(3, hotelName);
                    pstmt.setInt(4, Integer.parseInt(quantity));
                    pstmt.setInt(5, Integer.parseInt(COST));
                    pstmt.setString(6, foodName);
                    pstmt.setInt(7, order_id);

                    pstmt2.setInt(1, order_id);

                    System.out.println("Row Affected " + pstmt.executeUpdate());
                    System.out.println("Row Deleted "+pstmt2.executeUpdate());
                }
                catch(Exception ef) {
                    ef.printStackTrace();
                }
                acceptBox.dispose();
                refreshPanel();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acceptBox.dispose();
            }
        });

        acceptBox.add(buttonPanel, BorderLayout.SOUTH);
        acceptBox.setLocationRelativeTo(null);
        acceptBox.setVisible(true);
    }
}

