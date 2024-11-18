package src.src;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.nimbus.State;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

import static java.awt.BorderLayout.WEST;

public class HotelPage extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JPanel hotelMainPage;
    private JPanel addFoodPage;
    private JPanel removeFoodPage;
    private JPanel addedFoodListPanel;
    private JPanel historyFoodPage;
    private JTextField foodNameField, quantityField, costField;
    private ArrayList<String> addedFoods;

    public HotelPage() {
        setTitle("FOOD RESCUERS");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon frameIcon = new ImageIcon("E:\\OOAD PROJECT\\Project\\logofr.jpg");
        setIconImage(frameIcon.getImage());

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        addedFoods = new ArrayList<>();

        initHotelMainPage();
        initAddFoodPage();
        initRemoveFoodPage();
        initHistoryFoodPage();

        mainPanel.add(hotelMainPage, "HotelMainPage");
        mainPanel.add(addFoodPage, "AddFoodPage");
        mainPanel.add(removeFoodPage, "RemoveFoodPage");
        mainPanel.add(historyFoodPage, "HistoryFoodPage");

        add(mainPanel);
        cardLayout.show(mainPanel, "HotelMainPage");

        loadFoodsFromDatabase();

        int w=getWidth(), h=getHeight();
        Toolkit it=Toolkit.getDefaultToolkit();
        Dimension d=it.getScreenSize();
        setLocation(d.width/2-w/2, d.height/2-h/2);
    }

    private void initHistoryFoodPage() {
        historyFoodPage = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Orders Received", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(28, 74, 227));
        titleLabel.setBorder(new EmptyBorder(20, 0, 20, 0));
        historyFoodPage.add(titleLabel, BorderLayout.NORTH);

        JPanel ordersPanel = new JPanel();
        ordersPanel.setLayout(new BoxLayout(ordersPanel, BoxLayout.Y_AXIS));
        ordersPanel.setBackground(new Color(240, 248, 255));

        JScrollPane scrollPane = new JScrollPane(ordersPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        historyFoodPage.add(scrollPane, BorderLayout.CENTER);

        loadOrderHistory(ordersPanel);
    }

    private void loadOrderHistory(JPanel ordersPanel) {
        String hotelname = null;

        String sql = "SELECT name from `u-info` where uid="+SessionManager.getInstance().getUserId();
        try(Connection conn2 = connectToDatabase();
            Statement stmt2 = conn2.createStatement();
            ResultSet rs2 = stmt2.executeQuery(sql);){
                while(rs2.next()){
                    hotelname = rs2.getString("name");
                }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        String query = "SELECT h.fquan, h.fcost, h.fname, u.name, h.utype FROM `history-food` h " +
                "JOIN `u-info` u on u.uid=h.uid WHERE h.hotelname="+"'Hotel "+hotelname+"'";

        try (Connection connection = connectToDatabase();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            ordersPanel.removeAll();

            while (rs.next()) {
                String orderInfo = "<html><strong>Quantity:</strong> " + rs.getInt("fquan")
                        + "<br><strong>Cost:</strong> Rs." + rs.getDouble("fcost")
                        + "<br><strong>Ordered by:</strong> " + rs.getString("name")
                        + "<br><strong>Type:</strong> " + rs.getString("utype")
                        + "</html>";

                JPanel orderCard = new JPanel(new BorderLayout());
                orderCard.setBorder(new CompoundBorder(new LineBorder(Color.GRAY, 1, true), new EmptyBorder(10, 10, 10, 10)));
                orderCard.setBackground(Color.WHITE);
                orderCard.setMaximumSize(new Dimension(700, 100));

                JLabel orderLabel = new JLabel(orderInfo);
                orderLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                orderCard.add(orderLabel, BorderLayout.CENTER);

                ordersPanel.add(orderCard);
                ordersPanel.add(Box.createVerticalStrut(10));
            }
            ordersPanel.revalidate();
            ordersPanel.repaint();

        } catch (Exception e) {
            e.printStackTrace();
        }

        JPanel buttonPanel = new JPanel();
        JButton backButton = createStyledButton("< Back");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "HotelMainPage"));
        buttonPanel.add(backButton);
        historyFoodPage.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadFoodsFromDatabase() {
        String query = "SELECT * FROM available_food where uid=" + SessionManager.getInstance().getUserId();
        try (Connection connection = connectToDatabase();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            addedFoods.clear();
            while (rs.next()) {
                String foodName = rs.getString("fname");
                int quantity = rs.getInt("fquan");
                double cost = rs.getDouble("fcost");
                String foodItem = foodName + " - Quantity: " + quantity + " , Cost: Rs." + cost;
                addedFoods.add(foodItem);
            }
            updateAddedFoodList();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearAddFoodFields() {
        foodNameField.setText("");
        quantityField.setText("");
        costField.setText("");
    }


    private void initHotelMainPage() {
        hotelMainPage = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(255, 204, 102));
        JLabel companyName = new JLabel("FOOD RESCUERS", JLabel.CENTER);
        companyName.setFont(new Font("Arial", Font.BOLD, 24));
        companyName.setForeground(Color.BLACK);
        topPanel.add(companyName, BorderLayout.CENTER);
        hotelMainPage.add(topPanel, BorderLayout.NORTH);

        addedFoodListPanel = new JPanel();
        addedFoodListPanel.setLayout(new BoxLayout(addedFoodListPanel, BoxLayout.Y_AXIS));

        Font titleFont = new Font("Arial", Font.BOLD, 24);
        TitledBorder border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 2), "Added Foods");
        border.setTitleFont(titleFont);
        border.setTitleColor(new Color(50, 50, 150));

        addedFoodListPanel.setBorder(border);
        updateAddedFoodList();

        JScrollPane scrollPane = new JScrollPane(addedFoodListPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        hotelMainPage.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        // Create buttons with custom colors, sizes, and fonts
        JButton addFoodButton = new JButton("Add Food");
        JButton removeFoodButton = new JButton("Remove Food");
        JButton viewHistoryButton = new JButton("View Order History");
        JButton viewProfileButton = new JButton("View Profile");
        JButton signoutButton = new JButton("Sign Out");

        addFoodButton.setBackground(new Color(60, 179, 113));      // Medium Sea Green
        addFoodButton.setForeground(Color.WHITE);
        removeFoodButton.setBackground(new Color(255, 69, 0));     // Orange Red
        removeFoodButton.setForeground(Color.WHITE);
        viewHistoryButton.setBackground(new Color(213, 183, 74));
        viewHistoryButton.setForeground(Color.WHITE);
        viewProfileButton.setBackground(new Color(100, 149, 237)); // Cornflower Blue
        viewProfileButton.setForeground(Color.WHITE);
        signoutButton.setBackground(new Color(220, 20, 60));       // Crimson Red
        signoutButton.setForeground(Color.WHITE);

        Font buttonFont = new Font("Arial", Font.BOLD, 16);
        Dimension buttonSize = new Dimension(140, 45); // Larger button size

        addFoodButton.setFont(buttonFont);
        removeFoodButton.setFont(buttonFont);
        viewProfileButton.setFont(buttonFont);
        viewHistoryButton.setFont(buttonFont);
        signoutButton.setFont(buttonFont);

        addFoodButton.setPreferredSize(buttonSize);
        removeFoodButton.setPreferredSize(buttonSize);
        viewProfileButton.setPreferredSize(buttonSize);
        viewHistoryButton.setPreferredSize(buttonSize);
        signoutButton.setPreferredSize(buttonSize);


        addFoodButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addFoodButton.setBackground(new Color(46, 139, 87)); // Darker green on hover
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addFoodButton.setBackground(new Color(60, 179, 113)); // Original color
            }
        });

        removeFoodButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                removeFoodButton.setBackground(new Color(178, 34, 34)); // Darker red on hover
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                removeFoodButton.setBackground(new Color(255, 69, 0)); // Original color
            }
        });

        viewProfileButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                viewProfileButton.setBackground(new Color(72, 118, 255)); // Darker blue on hover
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                viewProfileButton.setBackground(new Color(100, 149, 237)); // Original color
            }
        });

        signoutButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                signoutButton.setBackground(new Color(178, 34, 34)); // Darker red on hover
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                signoutButton.setBackground(new Color(220, 20, 60)); // Original color
            }
        });

        buttonPanel.add(addFoodButton);
        buttonPanel.add(removeFoodButton);
        buttonPanel.add(viewHistoryButton);
        buttonPanel.add(viewProfileButton);
        buttonPanel.add(signoutButton);

        hotelMainPage.add(buttonPanel, BorderLayout.SOUTH);

        addFoodButton.addActionListener(e -> {
            clearAddFoodFields();
            cardLayout.show(mainPanel, "AddFoodPage");
        });

        removeFoodButton.addActionListener(e -> {
            loadFoodsForRemoval();
            cardLayout.show(mainPanel, "RemoveFoodPage");
        });

        viewHistoryButton.addActionListener(e -> cardLayout.show(mainPanel, "HistoryFoodPage"));

        viewProfileButton.addActionListener(e -> {
            new src.src.ProfilePage();
            cardLayout.show(mainPanel, "ProfilePage");
        });

        signoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SessionManager.getInstance().clearSession();
                dispose();
            }
        });
    }

    private void updateAddedFoodList() {
        addedFoodListPanel.removeAll();
        for (String food : addedFoods) {
            JPanel foodCard = new JPanel(new BorderLayout());
            foodCard.setBorder(new LineBorder(Color.GRAY, 1, true));
            foodCard.setBackground(Color.WHITE);
            foodCard.setMaximumSize(new Dimension(700, 50));
            JLabel foodLabel = new JLabel(food);
            foodLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            foodLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
            foodCard.add(foodLabel, BorderLayout.CENTER);
            addedFoodListPanel.add(foodCard);
        }
        addedFoodListPanel.revalidate();
        addedFoodListPanel.repaint();
    }

    private void initAddFoodPage() {
        addFoodPage = new JPanel(new BorderLayout());
        JLabel addFoodLabel = new JLabel("+ Add Food", JLabel.CENTER);
        addFoodLabel.setFont(new Font("Arial", Font.BOLD, 24));
        addFoodLabel.setForeground(new Color(28, 74, 227));
        addFoodLabel.setBorder(new EmptyBorder(20, 0, 20, 0));
        addFoodPage.add(addFoodLabel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Padding around the panel
        inputPanel.setBackground(new Color(240, 248, 255));    // Light background color (AliceBlue)

        Font labelFont = new Font("Arial", Font.BOLD, 16);

        JLabel foodNameLabel = new JLabel("Food Name:", JLabel.RIGHT);
        foodNameLabel.setFont(labelFont);    // Set font for label
        inputPanel.add(foodNameLabel);

        foodNameField = new JTextField();
        foodNameField.setFont(new Font("Arial", Font.PLAIN, 14));  // Font for text field
        foodNameField.setBorder(new LineBorder(new Color(173, 216, 230), 1, true));  // Light blue rounded border
        inputPanel.add(foodNameField);

        JLabel quantityLabel = new JLabel("Quantity:", JLabel.RIGHT);
        quantityLabel.setFont(labelFont);
        inputPanel.add(quantityLabel);

        quantityField = new JTextField();
        quantityField.setFont(new Font("Arial", Font.PLAIN, 14));
        quantityField.setBorder(new LineBorder(new Color(173, 216, 230), 1, true));  // Same border style
        inputPanel.add(quantityField);

        JLabel costLabel = new JLabel("Cost:", JLabel.RIGHT);
        costLabel.setFont(labelFont);
        inputPanel.add(costLabel);

        costField = new JTextField();
        costField.setFont(new Font("Arial", Font.PLAIN, 14));
        costField.setBorder(new LineBorder(new Color(173, 216, 230), 1, true));  // Same border style
        inputPanel.add(costField);

        addFoodPage.add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        // Create Confirm and Cancel buttons with custom colors and size
        JButton confirmButton = new JButton("Confirm");
        JButton cancelButton = new JButton("Cancel");

        confirmButton.setBackground(new Color(34, 139, 34)); // Forest Green
        confirmButton.setForeground(Color.WHITE);
        cancelButton.setBackground(new Color(220, 20, 60));  // Crimson Red
        cancelButton.setForeground(Color.WHITE);

        Font buttonFont = new Font("Arial", Font.BOLD, 16);
        confirmButton.setFont(buttonFont);
        cancelButton.setFont(buttonFont);
        confirmButton.setPreferredSize(new Dimension(120, 40));
        cancelButton.setPreferredSize(new Dimension(120, 40));

        confirmButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                confirmButton.setBackground(new Color(0, 128, 0));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                confirmButton.setBackground(new Color(34, 139, 34));
            }
        });

        cancelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cancelButton.setBackground(new Color(178, 34, 34));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancelButton.setBackground(new Color(220, 20, 60));
            }
        });

        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        addFoodPage.add(buttonPanel, BorderLayout.SOUTH);

        confirmButton.addActionListener(e -> {
            String foodName = foodNameField.getText();
            String quantityText = quantityField.getText();
            String costText = costField.getText();

            if (!foodName.isEmpty() && !quantityText.isEmpty() && !costText.isEmpty()) {
                insertFoodToDatabase(foodName, Integer.parseInt(quantityText), Double.parseDouble(costText));
                loadFoodsFromDatabase();
                cardLayout.show(mainPanel, "HotelMainPage");
            }
        });

        cancelButton.addActionListener(e -> cardLayout.show(mainPanel, "HotelMainPage"));
    }

    private void initRemoveFoodPage() {
        removeFoodPage = new JPanel(new BorderLayout());
        JLabel removeFoodLabel = new JLabel("- Remove Food", JLabel.CENTER);
        removeFoodLabel.setFont(new Font("Arial", Font.BOLD, 24));
        removeFoodLabel.setBorder(new EmptyBorder(20, 0, 20, 0));
        removeFoodLabel.setForeground(new Color(255, 93, 93));
        removeFoodPage.add(removeFoodLabel, BorderLayout.NORTH);
    }

    private void loadFoodsForRemoval() {
        //removeFoodPage.removeAll();

        JPanel foodListPanel = new JPanel();
        foodListPanel.setLayout(new BoxLayout(foodListPanel, BoxLayout.Y_AXIS));
        foodListPanel.setBorder(new CompoundBorder(
                new LineBorder(new Color(100, 149, 237), 2, true),
                new EmptyBorder(20, 20, 20, 20)
        ));
        foodListPanel.setBackground(new Color(240, 248, 255));

        String query = "SELECT * FROM available_food where uid=" + SessionManager.getInstance().getUserId();
        try (Connection connection = connectToDatabase();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            ArrayList<JCheckBox> checkBoxes = new ArrayList<>();
            while (rs.next()) {
                String foodName = rs.getString("fname");
                int quantity = rs.getInt("fquan");
                double cost = rs.getDouble("fcost");
                JCheckBox checkBox = new JCheckBox(foodName + " - Quantity: " + quantity + ", Cost: Rs." + cost);
                checkBox.setFont(new Font("Arial", Font.PLAIN, 14));
                checkBoxes.add(checkBox);
                foodListPanel.add(checkBox);
            }

            JButton confirmButton = createStyledButton("Confirm");
            confirmButton.addActionListener(e -> {
                for (JCheckBox checkBox : checkBoxes) {
                    if (checkBox.isSelected()) {
                        removeFoodFromDatabase(checkBox.getText().split(" - ")[0]);
                    }
                }
                loadFoodsFromDatabase();
                cardLayout.show(mainPanel, "HotelMainPage");
            });

            JButton cancelButton = createStyledButton("Cancel");
            cancelButton.addActionListener(e -> cardLayout.show(mainPanel, "HotelMainPage"));

            JPanel buttonPanel = new JPanel();
            buttonPanel.setBackground(new Color(255, 229, 180));
            buttonPanel.add(confirmButton);
            buttonPanel.add(cancelButton);

            removeFoodPage.add(new JScrollPane(foodListPanel), BorderLayout.CENTER);
            removeFoodPage.add(buttonPanel, BorderLayout.SOUTH);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        removeFoodPage.revalidate();
        removeFoodPage.repaint();
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(255, 140, 0));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(120, 40));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(255, 69, 0));
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(255, 140, 0));
            }
        });

        return button;
    }

    private void insertFoodToDatabase(String foodName, int quantity, double cost) {
        String query = "INSERT INTO `available_food` (fname, fquan, fcost, uid, hotelname) VALUES (?, ?, ?, ?, ?)";
        String query2 = "SELECT name from `u-info` WHERE uid=" + SessionManager.getInstance().getUserId();

        try (Connection connection = connectToDatabase()) {
            PreparedStatement pstmt = connection.prepareStatement(query);
            Statement stmt = connection.createStatement();

            String hname = null;
            ResultSet rs = stmt.executeQuery(query2);
            while (rs.next()) {
                hname = rs.getString("name");
            }
            pstmt.setString(1, foodName);
            pstmt.setInt(2, quantity);
            pstmt.setDouble(3, cost);
            pstmt.setInt(4, SessionManager.getInstance().getUserId());
            pstmt.setString(5, hname);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Add HotelName in Profile!", "Details", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void removeFoodFromDatabase(String foodName) {
        String query = "DELETE FROM available_food WHERE fname = ?";
        try (Connection connection = connectToDatabase();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, foodName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection connectToDatabase() {
        String url = "jdbc:mysql://localhost:3306/food_management?useSSL=false";
        String username = "root";
        String password = "root";
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        new HotelPage().setVisible(true);
    }
}
