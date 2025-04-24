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

public class ManagerInfo extends JFrame {
    Connection conn = null; // Connection to the database
    private JPanel contentPane; // Panel to hold UI components
    private JTable table; // Table to display data
    private JLabel lblNewLabel, lblJob, lblName, lblDepartment; // Labels for column headers

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ManagerInfo frame = new ManagerInfo(); // Create ManagerInfo frame
                    frame.setVisible(true); // Make the frame visible
                } catch (Exception e) {
                    e.printStackTrace(); // Print any exceptions
                }
            }
        });
    }

    // Method to close the frame
    public void close() {
        this.dispose(); // Dispose of the frame (close it)
    }

    /**
     * Create the frame.
     * @throws SQLException 
     */
    public ManagerInfo() throws SQLException {
        //conn = Javaconnect.getDBConnection(); // Assuming this line connects to the database (commented out here)
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set close operation to exit on close
        setBounds(430, 200, 1000, 600); // Set window size and location
        contentPane = new JPanel(); // Create panel for content
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // Set padding for panel
        setContentPane(contentPane); // Set the content pane of the frame
        contentPane.setLayout(null); // Set layout manager to null for manual positioning

        // Create the JTable to display data
        table = new JTable();
        table.setBounds(0, 34, 1000, 450); // Set position and size of table
        contentPane.add(table); // Add the table to the panel

        // Create button to load data into the table
        JButton btnLoadData = new JButton("Load Data");
        btnLoadData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    conn c = new conn(); // Establish connection to the database
                    String displayCustomersql = "select * from Employee where job = 'Manager'"; // SQL query to fetch managers
                    ResultSet rs = c.s.executeQuery(displayCustomersql); // Execute the query and get results
                    table.setModel(DbUtils.resultSetToTableModel(rs)); // Set the table model with the result set
                } catch (Exception e1) {
                    e1.printStackTrace(); // Print any exceptions
                }
            }
        });
        btnLoadData.setBounds(350, 500, 120, 30); // Set button position and size
        btnLoadData.setBackground(Color.BLACK); // Set button background color
        btnLoadData.setForeground(Color.WHITE); // Set button text color
        contentPane.add(btnLoadData); // Add the button to the panel

        // Create button to go back to the previous screen
        JButton btnExit = new JButton("Back");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Reception().setVisible(true); // Open the Reception screen
                setVisible(false); // Close the current screen
            }
        });
        btnExit.setBounds(510, 500, 120, 30); // Set button position and size
        btnExit.setBackground(Color.BLACK); // Set button background color
        btnExit.setForeground(Color.WHITE); // Set button text color
        contentPane.add(btnExit); // Add the button to the panel

        // Create labels for column headers
        lblNewLabel = new JLabel("Name");
        lblNewLabel.setBounds(41, 11, 46, 14); // Set position for label
        contentPane.add(lblNewLabel);

        lblJob = new JLabel("Age");
        lblJob.setBounds(159, 11, 46, 14); // Set position for label
        contentPane.add(lblJob);

        lblName = new JLabel("Gender");
        lblName.setBounds(273, 11, 46, 14); // Set position for label
        contentPane.add(lblName);

        lblDepartment = new JLabel("Job");
        lblDepartment.setBounds(416, 11, 86, 14); // Set position for label
        contentPane.add(lblDepartment);

        // Additional labels for salary, phone, and gmail
        JLabel l1 = new JLabel("Salary");
        l1.setBounds(536, 11, 86, 14);
        contentPane.add(l1);

        JLabel l2 = new JLabel("Phone");
        l2.setBounds(656, 11, 86, 14);
        contentPane.add(l2);


        JLabel l4 = new JLabel("Gmail");
        l4.setBounds(896, 11, 86, 14);
        contentPane.add(l4);

        // Set background color for the content pane
        getContentPane().setBackground(Color.WHITE);
    }
}
