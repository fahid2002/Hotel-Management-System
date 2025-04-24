package hotel.management.system;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Checkout extends JFrame {
    // Database connection objects
    Connection conn = null;
    PreparedStatement pst = null;

    // UI components
    private JPanel contentPane;
    private JTextField t1; // Unused, can be removed or used as needed
    Choice c1; // Dropdown to display customer numbers
    JLabel lblroomnumber; // Label to display the corresponding room number for a customer

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Create and display the Checkout frame
                    Checkout frame = new Checkout();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Constructor to initialize the Checkout frame
    public Checkout() throws SQLException {
        // Set basic frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(530, 200, 800, 294);

        // Initialize and set up the content pane
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Add a background image for aesthetics
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/checkout.jpg"));
        Image i3 = i1.getImage().getScaledInstance(400, 225, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i3);
        JLabel l1 = new JLabel(i2);
        l1.setBounds(300, 0, 500, 225);
        add(l1);

        // Label for the "Check Out" title
        JLabel lblCheckout = new JLabel("Check out ");
        lblCheckout.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblCheckout.setBounds(70, 11, 140, 35);
        contentPane.add(lblCheckout);

        // Label and dropdown for customer number
        JLabel lblName = new JLabel("Number:");
        lblName.setBounds(20, 85, 80, 14);
        contentPane.add(lblName);

        c1 = new Choice();
        c1.setBounds(130, 82, 150, 20);
        contentPane.add(c1);

        // Label to display the room number associated with the selected customer
        JLabel lblRoomNumberLabel = new JLabel("Room Number:");
        lblRoomNumberLabel.setBounds(20, 132, 100, 14);
        contentPane.add(lblRoomNumberLabel);

        lblroomnumber = new JLabel();
        lblroomnumber.setBounds(130, 132, 150, 20);
        contentPane.add(lblroomnumber);

        // Fetch customer numbers from the database and populate the dropdown
        try {
            conn c = new conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            while (rs.next()) {
                c1.add(rs.getString("number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Event listener to update the room number when a customer is selected
        c1.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                try {
                    conn c = new conn();
                    String number = c1.getSelectedItem();
                    ResultSet rs = c.s.executeQuery("select * from customer where number = '" + number + "'");
                    if (rs.next()) {
                        lblroomnumber.setText(rs.getString("room"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Optional "tick" button to refresh room number manually (redundant due to dropdown listener)
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/tick.png"));
        Image i5 = i4.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JButton l2 = new JButton(i6);
        l2.setBounds(290, 82, 20, 20);
        add(l2);

        // Action listener for the "tick" button to update room number
        l2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    conn c = new conn();
                    String number = c1.getSelectedItem();
                    ResultSet rs = c.s.executeQuery("select * from customer where number = '" + number + "'");
                    if (rs.next()) {
                        lblroomnumber.setText(rs.getString("room"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Button to perform the checkout operation
        JButton btnCheckout = new JButton("Check Out");
        btnCheckout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String number = c1.getSelectedItem(); // Get the selected customer number
                String roomNumber = lblroomnumber.getText(); // Get the room number
                
                // SQL queries to delete the customer record and update room availability
                String deleteSQL = "Delete from customer where number = '" + number + "'";
                String q2 = "update room set availability = 'Available' where room_number = '" + roomNumber + "'";

                conn c = new conn();
                try {
                    // Execute both SQL queries
                    c.s.executeUpdate(deleteSQL);
                    c.s.executeUpdate(q2);
                    
                    // Show success message and navigate to the Reception screen
                    JOptionPane.showMessageDialog(null, "Check Out Successful");
                    new Reception().setVisible(true);
                    setVisible(false);
                } catch (SQLException e1) {
                    System.out.println(e1.getMessage());
                }
            }
        });
        btnCheckout.setBounds(50, 200, 100, 25);
        btnCheckout.setBackground(Color.BLACK);
        btnCheckout.setForeground(Color.WHITE);
        contentPane.add(btnCheckout);

        // Button to go back to the Reception screen
        JButton btnExit = new JButton("Back");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Reception().setVisible(true);
                setVisible(false);
            }
        });
        btnExit.setBounds(160, 200, 100, 25);
        btnExit.setBackground(Color.BLACK);
        btnExit.setForeground(Color.WHITE);
        contentPane.add(btnExit);

        // Set the background color for the window
        getContentPane().setBackground(Color.WHITE);
    }
}
