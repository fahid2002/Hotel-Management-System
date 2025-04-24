
package hotel.management.system;

import javax.swing.*; // For GUI components
import java.awt.*;   // For layout and color customization
import java.awt.event.*; // For handling button actions
import java.sql.*;   // For database connectivity

public class Reception extends JFrame {

    private JPanel contentPane; // Panel to hold all UI components

    /*
      Main method to run the Reception screen.
     */
    public static void main(String[] args) {
        new Reception(); // Create an instance of the Reception class
    }

    /*
      Constructor to initialize and set up the Reception screen.
     */
    public Reception() {
        // Frame settings
        setBounds(380, 150, 850, 570); // Set size and position of the frame
        contentPane = new JPanel();   // Initialize the panel
        setContentPane(contentPane);  // Set the panel as the content pane
        contentPane.setLayout(null);  // Use absolute positioning for components

        // Add an image to the frame
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/concierge.gif")); // Load concierge image
        Image i3 = i1.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT); // Resize image
        ImageIcon i2 = new ImageIcon(i3); // Create an ImageIcon from resized image
        JLabel l1 = new JLabel(i2); // Create a JLabel to hold the image
        l1.setBounds(250, 30, 500, 470); // Position and size of the image
        add(l1); // Add the image to the frame

        // Add buttons for different functionalities
        // Each button navigates to a specific functionality
        createButton("New Customer Form", 10, 30, e -> {
            try {
                new NewCustomer().setVisible(true); // Open New Customer Form
                setVisible(false); // Hide current frame
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        createButton("Room", 10, 70, e -> {
            try {
                new Room().setVisible(true); // Open Room information
                setVisible(false);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        createButton("Department", 10, 110, e -> {
            try {
                new Department().setVisible(true); // Open Department information
                setVisible(false);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        createButton("Employee Info", 10, 150, e -> {
            try {
                new Employee().setVisible(true); // Open Employee information
                setVisible(false);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        createButton("Customer Info", 10, 190, e -> {
            try {
                new CustomerInfo().setVisible(true); // Open Customer Info
                setVisible(false);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        createButton("Manager Info", 10, 230, e -> {
            try {
                new ManagerInfo().setVisible(true); // Open Manager Info
                setVisible(false);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        createButton("Check out", 10, 270, e -> {
            try {
                new Checkout().setVisible(true); // Open Checkout functionality
                setVisible(false);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        createButton("Update Check Status", 10, 310, e -> {
            try {
                new UpdateCheck().setVisible(true); // Open Update Check Status
                setVisible(false);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

       

        createButton("Pick up Service", 10, 350, e -> {
            try {
                new PickUp().setVisible(true); // Open Pick-up Service
                setVisible(false);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        createButton("Search Room", 10, 390, e -> {
            try {
                new SearchRoom().setVisible(true); // Open Search Room functionality
                setVisible(false);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        createButton("Log Out", 10, 430, e -> {
            try {
                new Login().setVisible(true); // Log out and go to Login screen
                setVisible(false);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        // Set frame background color
        getContentPane().setBackground(Color.WHITE);

        // Make the frame visible
        setVisible(true);
    }

    private void createButton(String text, int x, int y, ActionListener listener) {
        JButton button = new JButton(text); // Create a new button
        button.setBounds(x, y, 200, 30); // Set button position and size
        button.setBackground(Color.BLACK); // Set button background color
        button.setForeground(Color.WHITE); // Set button text color
        button.addActionListener(listener); // Add the ActionListener
        contentPane.add(button); // Add the button to the content pane
    }
}
