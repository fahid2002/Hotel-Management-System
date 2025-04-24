package hotel.management.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    JLabel l1, l2; // l1 = Username label, l2 = Password label
    JTextField username; // Text field for entering username
    JPasswordField password; // Password field for entering password
    JButton b1, b2; // b1 = Login button, b2 = Cancel button

    Login() {
        super("Login");

        setLayout(null); // Setting layout to null for manual positioning of components

        // Label for username
        l1 = new JLabel("Username");
        l1.setBounds(40, 20, 100, 30);
        add(l1);

        // Label for password
        l2 = new JLabel("Password");
        l2.setBounds(40, 70, 100, 30);
        add(l2);

        // Text field for username input
        username = new JTextField();
        username.setBounds(150, 20, 150, 30);
        add(username);

        // Password field for password input
        password = new JPasswordField();
        password.setBounds(150, 70, 150, 30);
        add(password);

        // Image icon for the login window (a GIF)
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/10login.gif"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT); // Scaling the image
        ImageIcon i3 = new ImageIcon(i2); // Creating a new ImageIcon with the scaled image
        JLabel l3 = new JLabel(i3); // Label to display the icon
        l3.setBounds(350, 18, 180, 200);
        add(l3); // Add the image to the frame

        // Login button
        b1 = new JButton("Login");
        b1.setBounds(40, 140, 120, 30);
        b1.setFont(new Font("serif", Font.BOLD, 15)); // Setting the font for the button
        b1.addActionListener(this); // Add action listener for the button
        b1.setBackground(Color.BLACK); // Set background color of the button
        b1.setForeground(Color.WHITE); // Set foreground color of the button
        add(b1); // Add the login button to the frame

        // Cancel button
        b2 = new JButton("Cancel");
        b2.setBounds(180, 140, 120, 30);
        b2.setFont(new Font("serif", Font.BOLD, 15)); // Set font for the button
        b2.setBackground(Color.BLACK); // Set background color of the button
        b2.setForeground(Color.WHITE); // Set foreground color of the button
        add(b2); // Add cancel button to the frame

        // Add action listener to cancel button
        b2.addActionListener(this);

        // Set the background color of the content pane
        getContentPane().setBackground(Color.WHITE);

        // Set the frame visible and configure its size and position
        setVisible(true);
        setSize(600, 300);
        setLocation(600, 350); // Position the window on screen
    }

    // ActionListener method to handle button clicks
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) { // If the login button is clicked
            try {
                conn c = new conn(); // Create connection object
                String u = username.getText(); // Get the username input
                String v = password.getText(); // Get the password input

                // SQL query to check if the username and password match the record in the database
                String q = "select * from login where username='" + u + "' and password='" + v + "'";

                ResultSet rs = c.s.executeQuery(q); // Execute query to fetch matching record
                if (rs.next()) { // If a record is found, user is authenticated
                    new Dashboard().setVisible(true); // Show the dashboard if login is successful
                    setVisible(false); // Hide the login window
                } else { // If no matching record is found, show error
                    JOptionPane.showMessageDialog(null, "Invalid login");
                    setVisible(false); // Hide the login window
                }
            } catch (Exception e) {
                e.printStackTrace(); // Print any errors that occur during the login process
            }
        } else if (ae.getSource() == b2) { // If the cancel button is clicked
            System.exit(0); // Exit the application
        }
    }

    // Main method to run the login window
    public static void main(String[] arg) {
        new Login(); // Instantiate and display the Login window
    }
}
