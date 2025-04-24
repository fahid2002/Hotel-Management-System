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

public class Employee extends JFrame {
    Connection conn = null; // Database connection object
    private JPanel contentPane; // Panel for content
    private JTable table; // Table to display employee data
    private JLabel lblNewLabel, lblJob, lblName, lblDepartment; // Labels for the column headers

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Employee frame = new Employee(); // Create Employee frame
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

    /**
     * Create the frame.
     * @throws SQLException 
     */
    public Employee() throws SQLException {
        //conn = Javaconnect.getDBConnection(); // Initialize the database connection (commented out)
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the application when the window is closed
        setBounds(430, 200, 1000, 600); // Set the window position and size
        contentPane = new JPanel(); // Create a new panel for content
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // Set the border of the content panel
        setContentPane(contentPane); // Set the content pane to the panel
        contentPane.setLayout(null); // Set the layout to null (absolute positioning)
        
        table = new JTable(); // Create a new JTable for displaying data
        table.setBounds(0, 34, 1000, 450); // Set position and size of the table
        contentPane.add(table); // Add the table to the content pane
        
        // Load Data button
        JButton btnLoadData = new JButton("Load Data");
        btnLoadData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    conn c = new conn(); // Create a new connection object
                    String displayCustomersql = "select * from Employee"; // SQL query to fetch employee data
                    ResultSet rs = c.s.executeQuery(displayCustomersql); // Execute the query
                    table.setModel(DbUtils.resultSetToTableModel(rs)); // Populate the table with data
                } catch(Exception e1) {
                    e1.printStackTrace(); // Print error if exception occurs
                }
            }
        });
        btnLoadData.setBounds(350, 500, 120, 30); // Set button size and position
        btnLoadData.setBackground(Color.BLACK); // Set background color of the button
        btnLoadData.setForeground(Color.WHITE); // Set text color of the button
        contentPane.add(btnLoadData); // Add the button to the content pane
        
        // Back button
        JButton btnExit = new JButton("Back");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Reception().setVisible(true); // Open the Reception window
                setVisible(false); // Close the current Employee window
            }
        });
        btnExit.setBounds(510, 500, 120, 30); // Set button size and position
        btnExit.setBackground(Color.BLACK); // Set background color of the button
        btnExit.setForeground(Color.WHITE); // Set text color of the button
        contentPane.add(btnExit); // Add the button to the content pane
        
        // Column headers
        lblNewLabel = new JLabel("Name");
        lblNewLabel.setBounds(41, 11, 46, 14); // Set position of the label
        contentPane.add(lblNewLabel); // Add label to the content pane
        
        lblJob = new JLabel("Age");
        lblJob.setBounds(159, 11, 46, 14); // Set position of the label
        contentPane.add(lblJob); // Add label to the content pane
        
        lblName = new JLabel("Gender");
        lblName.setBounds(273, 11, 46, 14); // Set position of the label
        contentPane.add(lblName); // Add label to the content pane
        
        lblDepartment = new JLabel("Job");
        lblDepartment.setBounds(416, 11, 86, 14); // Set position of the label
        contentPane.add(lblDepartment); // Add label to the content pane

        // Additional labels for more employee data
        JLabel l1 = new JLabel("Salary");
        l1.setBounds(536, 11, 86, 14); // Set position of the label
        contentPane.add(l1); // Add label to the content pane
        
        JLabel l2 = new JLabel("Phone");
        l2.setBounds(656, 11, 86, 14); // Set position of the label
        contentPane.add(l2); // Add label to the content pane
        
        JLabel l3 = new JLabel("Aadhar");
        l3.setBounds(786, 11, 86, 14); // Set position of the label
        contentPane.add(l3); // Add label to the content pane
        
        JLabel l4 = new JLabel("Gmail");
        l4.setBounds(896, 11, 86, 14); // Set position of the label
        contentPane.add(l4); // Add label to the content pane
        
        getContentPane().setBackground(Color.WHITE); // Set the background color of the window to white
    }
}
