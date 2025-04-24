package hotel.management.system;

// Import necessary libraries
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class AddRoom extends JFrame implements ActionListener {

    // Declare GUI components
    private JPanel contentPane;
    private JTextField t1, t2, t3, t4;  // Text fields for user input
    private JComboBox comboBox, comboBox_1, comboBox_2, comboBox_3;  // Combo boxes for selections
    JButton b1, b2;  // Buttons for actions
    Choice c1;  // Choice component (though unused here)

    public static void main(String[] args) {
        // Create and display the AddRoom GUI
        new AddRoom().setVisible(true);
    }

    public AddRoom() {
        // Set window size and position
        setBounds(450, 200, 1000, 450);
        
        // Create the main panel and set layout
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Set an image on the GUI (background or decoration)
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/twelve.jpg"));
        Image i3 = i1.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);  // Scale the image
        ImageIcon i2 = new ImageIcon(i3);
        JLabel l15 = new JLabel(i2);
        l15.setBounds(400, 30, 500, 370);
        add(l15);

        // Add a label for the title of the window
        JLabel l10 = new JLabel("Add Rooms");
        l10.setFont(new Font("Tahoma", Font.BOLD, 18));
        l10.setBounds(194, 10, 120, 22);
        contentPane.add(l10);

        // Room Number field
        JLabel l1 = new JLabel("Room Number");
        l1.setForeground(new Color(25, 25, 112));  // Set text color
        l1.setFont(new Font("Tahoma", Font.BOLD, 14));
        l1.setBounds(64, 70, 102, 22);
        contentPane.add(l1);

        t4 = new JTextField();  // TextField for room number input
        t4.setBounds(174, 70, 156, 20);
        contentPane.add(t4);

        // Availability dropdown
        JLabel l2 = new JLabel("Availability");
        l2.setForeground(new Color(25, 25, 112));
        l2.setFont(new Font("Tahoma", Font.BOLD, 14));
        l2.setBounds(64, 110, 102, 22);
        contentPane.add(l2);

        comboBox = new JComboBox(new String[] { "Available", "Occupied" });  // ComboBox for room availability
        comboBox.setBounds(176, 110, 154, 20);
        contentPane.add(comboBox);

        // Cleaning Status dropdown
        JLabel l3 = new JLabel("Cleaning Status");
        l3.setForeground(new Color(25, 25, 112));
        l3.setFont(new Font("Tahoma", Font.BOLD, 14));
        l3.setBounds(64, 150, 102, 22);
        contentPane.add(l3);

        comboBox_2 = new JComboBox(new String[] { "Clean", "Dirty" });  // ComboBox for cleaning status
        comboBox_2.setBounds(176, 150, 154, 20);
        contentPane.add(comboBox_2);

        // Price field
        JLabel l4 = new JLabel("Price");
        l4.setForeground(new Color(25, 25, 112));
        l4.setFont(new Font("Tahoma", Font.BOLD, 14));
        l4.setBounds(64, 190, 102, 22);
        contentPane.add(l4);

        t2 = new JTextField();  // TextField for price input
        t2.setBounds(174, 190, 156, 20);
        contentPane.add(t2);

        // Bed Type dropdown
        JLabel l5 = new JLabel("Bed Type");
        l5.setForeground(new Color(25, 25, 112));
        l5.setFont(new Font("Tahoma", Font.BOLD, 14));
        l5.setBounds(64, 230, 102, 22);
        contentPane.add(l5);

        comboBox_3 = new JComboBox(new String[] { "Single Bed", "Double Bed" });  // ComboBox for bed type selection
        comboBox_3.setBounds(176, 230, 154, 20);
        contentPane.add(comboBox_3);

        // Add Button (to save the room data)
        b1 = new JButton("Add");
        b1.addActionListener(this);  // Add ActionListener
        b1.setBounds(64, 321, 111, 33);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        contentPane.add(b1);

        // Back Button (to close the window)
        b2 = new JButton("Back");
        b2.addActionListener(this);
        b2.setBounds(198, 321, 111, 33);
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        contentPane.add(b2);

        // Set background color of the content pane
        contentPane.setBackground(Color.WHITE);
    }

    // ActionListener for handling button clicks
    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == b1) {  // If Add button is clicked
                try {
                    conn c = new conn();  // Establish connection to the database
                    String room = t4.getText();  // Get room number from input field
                    String available = (String) comboBox.getSelectedItem();  // Get availability selection
                    String status = (String) comboBox_2.getSelectedItem();  // Get cleaning status
                    String price = t2.getText();  // Get price from input field
                    String type = (String) comboBox_3.getSelectedItem();  // Get bed type selection

                    // SQL query to insert room details into the database
                    String str = "INSERT INTO room values( '" + room + "', '" + available + "', '" + status + "', '" + price + "', '" + type + "')";

                    c.s.executeUpdate(str);  // Execute the update query
                    JOptionPane.showMessageDialog(null, "Room Successfully Added");  // Display success message
                    this.setVisible(false);  // Close the AddRoom window
                } catch (Exception ee) {
                    System.out.println(ee);  // Print any exceptions
                }
            } else if (ae.getSource() == b2) {  // If Back button is clicked
                this.setVisible(false);  // Close the window
            }
        } catch (Exception eee) {
            // Handle any other exceptions
        }
    }
}
