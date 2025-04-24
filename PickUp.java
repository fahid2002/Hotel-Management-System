package hotel.management.system;

import java.awt.BorderLayout;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PickUp extends JFrame {
    // Database connection objects
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    // UI components
    private JPanel contentPane;
    private JTable table;
    Choice c1;  // Choice component for selecting car type

    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Create and display the PickUp frame
                    PickUp frame = new PickUp();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Method to close the frame
    public void close() {
        this.dispose();
    }

    /**
     * Create the frame.
     * @throws SQLException 
     */
    public PickUp() throws SQLException {
        // Connection to the database (commented out here)
        // conn = Javaconnect.getDBConnection();
        
        // Set basic frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(530, 200, 800, 600);
        
        // Initialize and set up the content pane
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        // Label for the "Pick Up Service" title
        JLabel lblPickUpService = new JLabel("Pick Up Service");
        lblPickUpService.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblPickUpService.setBounds(90, 11, 158, 25);
        contentPane.add(lblPickUpService);
        
        // Label for selecting car type
        JLabel lblTypeOfCar = new JLabel("Type of Car");
        lblTypeOfCar.setBounds(32, 97, 89, 14);
        contentPane.add(lblTypeOfCar);

        // Choice component to display available car brands
        c1 = new Choice();
        try {
            // Database query to fetch car brands from the 'driver' table
            conn c = new conn();
            ResultSet rs = c.s.executeQuery("select * from driver");
            // Add each car brand to the Choice dropdown
            while(rs.next()){
                c1.add(rs.getString("brand"));    
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        // Set bounds for the choice dropdown
        c1.setBounds(123, 94, 150, 25);
        contentPane.add(c1);

        // Label for name column in the table
        JLabel lblInfo = new JLabel("Name");
        lblInfo.setBounds(24, 208, 46, 14);
        contentPane.add(lblInfo);
        
        // Button to display the drivers for the selected car brand
        JButton btnRegister = new JButton("Display");
        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // SQL query to fetch drivers based on the selected car brand
                String SQL = "select * from driver where brand = '"+c1.getSelectedItem()+"'";
                try {
                    // Execute the query and display results in the table
                    conn c = new conn();
                    rs = c.s.executeQuery(SQL);
                    table.setModel(DbUtils.resultSetToTableModel(rs));
                } catch (SQLException ss) {
                    ss.printStackTrace();
                }
            }
        });
        btnRegister.setBounds(200, 500, 120, 30);
        btnRegister.setBackground(Color.BLACK);
        btnRegister.setForeground(Color.WHITE);
        contentPane.add(btnRegister);
        
        // Button to go back to the previous screen (Reception)
        JButton btnExit = new JButton("Back");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // Open the Reception screen and close the current screen
                new Reception().setVisible(true);
                setVisible(false);
            }
        });
        btnExit.setBounds(420, 500, 120, 30);
        btnExit.setBackground(Color.BLACK);
        btnExit.setForeground(Color.WHITE);
        contentPane.add(btnExit);
        
        // Initialize the table to display the driver data
        table = new JTable();
        table.setBounds(0, 233, 800, 250);
        contentPane.add(table);
        
        // Column labels for the table (Age, Gender, Company, etc.)
        JLabel lblNewLabel = new JLabel("Age");
        lblNewLabel.setBounds(165, 208, 46, 14);
        contentPane.add(lblNewLabel);
        
        JLabel lblGender = new JLabel("Gender");
        lblGender.setBounds(264, 208, 46, 14);
        contentPane.add(lblGender);
        
        JLabel lblTypeOfDriver = new JLabel("Company");
        lblTypeOfDriver.setBounds(366, 208, 80, 14);
        contentPane.add(lblTypeOfDriver);
        
        JLabel lblDateOfThe = new JLabel("Brand");
        lblDateOfThe.setBounds(486, 208, 105, 14);
        contentPane.add(lblDateOfThe);
    
        JLabel lblAirport = new JLabel("Available");
        lblAirport.setBounds(600, 208, 86, 14);
        contentPane.add(lblAirport);
        
        JLabel lblAvailable = new JLabel("Location");
        lblAvailable.setBounds(700, 208, 73, 14);
        contentPane.add(lblAvailable);
        
        // Set the background color for the window
        getContentPane().setBackground(Color.WHITE);
    }
}
