package hotel.management.system;

import java.awt.BorderLayout;
import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import java.sql.*;	
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Department extends JFrame {
    Connection conn = null; // Database connection object
    private JPanel contentPane; // Panel for content
    private JTable table; // Table to display department data
    private JLabel lblNewLabel, lblNewLabel_1; // Labels for the column headers

   
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Department frame = new Department(); // Create Department frame
                    frame.setVisible(true); // Make the frame visible
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Method to close the current window
    public void close() {
        this.dispose(); // Dispose of the frame
    }

   
    public Department() throws SQLException {
        //conn = Javaconnect.getDBConnection(); // Initialize the database connection (commented out)
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the application when the window is closed
        setBounds(600, 200, 700, 500); // Set the window position and size
        contentPane = new JPanel(); // Create a new panel for content
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // Set the border of the content panel
        setContentPane(contentPane); // Set the content pane to the panel
        contentPane.setLayout(null); // Set the layout to null (absolute positioning)
        
        table = new JTable(); // Create a new JTable for displaying data
        table.setBounds(0, 40, 700, 350); // Set position and size of the table
        contentPane.add(table); // Add the table to the content pane
        
        // Load Data button
        JButton btnNewButton = new JButton("Load Data");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    conn c = new conn(); // Create a new connection object
                    String displayCustomersql = "select * from Department"; // SQL query to fetch department data
                    ResultSet rs = c.s.executeQuery(displayCustomersql); // Execute the query
                    table.setModel(DbUtils.resultSetToTableModel(rs)); // Populate the table with data
                } catch (Exception e1) {
                    e1.printStackTrace(); // Print error if exception occurs
                }
            }
        });
        btnNewButton.setBounds(170, 410, 120, 30); // Set button size and position
        btnNewButton.setBackground(Color.BLACK); // Set background color of the button
        btnNewButton.setForeground(Color.WHITE); // Set text color of the button
        contentPane.add(btnNewButton); // Add the button to the content pane
        
        // Back button
        JButton btnNewButton_1 = new JButton("Back");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Reception().setVisible(true); // Open the Reception window
                setVisible(false); // Close the current Department window
            }
        });
        btnNewButton_1.setBounds(400, 410, 120, 30); // Set button size and position
        btnNewButton_1.setBackground(Color.BLACK); // Set background color of the button
        btnNewButton_1.setForeground(Color.WHITE); // Set text color of the button
        contentPane.add(btnNewButton_1); // Add the button to the content pane
        
        // Column headers
        lblNewLabel = new JLabel("Department");
        lblNewLabel.setBounds(145, 11, 105, 14); // Set position of the label
        contentPane.add(lblNewLabel); // Add label to the content pane
        
        lblNewLabel_1 = new JLabel("Budget");
        lblNewLabel_1.setBounds(431, 11, 75, 14); // Set position of the label
        contentPane.add(lblNewLabel_1); // Add label to the content pane

        getContentPane().setBackground(Color.WHITE); // Set the background color of the window to white
    }
}
