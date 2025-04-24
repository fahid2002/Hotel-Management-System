package hotel.management.system;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class UpdateCheck extends JFrame {
    Connection conn = null;
    PreparedStatement pst = null;
    private JPanel contentPane;
    private JTextField txt_ID;
    private JTextField txt_Status;
    private JTextField txt_Date;
    private JTextField txt_Time;
    private JTextField txt_Payment;

    Choice c1;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UpdateCheck frame = new UpdateCheck();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void close() {
        this.dispose();
    }

    public UpdateCheck() throws SQLException {
        // Setup database connection
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelmanagementsystem", "root", "jamesbond007");

        // Setup JFrame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 950, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Title label for Update Check-In Details
        JLabel lblUpdateCheckStatus = new JLabel("Check-In Details");
        lblUpdateCheckStatus.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblUpdateCheckStatus.setBounds(124, 11, 222, 25);
        contentPane.add(lblUpdateCheckStatus);

        // Add an image to the frame
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/inout.jpeg"));
        JLabel l1 = new JLabel(i1);
        l1.setBounds(420, 70, 500, 270);
        add(l1);

        // Guest ID label and Choice dropdown
        JLabel lblNewLabel = new JLabel("ID:");
        lblNewLabel.setBounds(25, 88, 46, 14);
        contentPane.add(lblNewLabel);

        // Dropdown to select the guest ID
        c1 = new Choice();
        try {
            ResultSet rs = conn.createStatement().executeQuery("select * from customer");
            while (rs.next()) {
                c1.add(rs.getString("number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        c1.setBounds(248, 85, 140, 20);
        contentPane.add(c1);

        // Labels and text fields for displaying guest and room details
        JLabel lblNewLabel_1 = new JLabel("Room Number :");
        lblNewLabel_1.setBounds(25, 129, 107, 14);
        contentPane.add(lblNewLabel_1);

        txt_ID = new JTextField();
        txt_ID.setBounds(248, 126, 140, 20);
        contentPane.add(txt_ID);

        JLabel lblNewLabel_2 = new JLabel("Name : ");
        lblNewLabel_2.setBounds(25, 174, 97, 14);
        contentPane.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Checked-in :");
        lblNewLabel_3.setBounds(25, 216, 107, 14);
        contentPane.add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("Amount Paid  : ");
        lblNewLabel_4.setBounds(25, 261, 107, 14);
        contentPane.add(lblNewLabel_4);

        JLabel lblNewLabel_5 = new JLabel("Pending Amount  : ");
        lblNewLabel_5.setBounds(25, 302, 150, 14);
        contentPane.add(lblNewLabel_5);

        // Text fields to display the details
        txt_Status = new JTextField();
        txt_Status.setBounds(248, 171, 140, 20);
        contentPane.add(txt_Status);
        txt_Status.setColumns(10);

        txt_Date = new JTextField();
        txt_Date.setBounds(248, 216, 140, 20);
        contentPane.add(txt_Date);
        txt_Date.setColumns(10);

        txt_Time = new JTextField();
        txt_Time.setBounds(248, 258, 140, 20);
        contentPane.add(txt_Time);
        txt_Time.setColumns(10);

        txt_Payment = new JTextField();
        txt_Payment.setBounds(248, 299, 140, 20);
        contentPane.add(txt_Payment);
        txt_Payment.setColumns(10);

        // Button to update the details
        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) { 
                try {
                    // Retrieve values from text fields
                    String s1 = c1.getSelectedItem();
                    String s2 = txt_ID.getText(); // room
                    String s3 = txt_Status.getText(); // name
                    String s4 = txt_Date.getText(); // Check_in_Status
                    String s5 = txt_Time.getText(); // deposit
                    
                    // Update the customer details in the database
                    String updateQuery = "UPDATE customer SET room = ?, name = ?, Check_in_Status = ?, deposit = ? WHERE number = ?";
                    pst = conn.prepareStatement(updateQuery);
                    pst.setString(1, s2);
                    pst.setString(2, s3);
                    pst.setString(3, s4);
                    pst.setString(4, s5);
                    pst.setString(5, s1);
                    pst.executeUpdate();
                    
                    JOptionPane.showMessageDialog(null, "Data Updated Successfully");
                    new Reception().setVisible(true);
                    setVisible(false);
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
            }
        });
        btnUpdate.setBounds(168, 378, 89, 23);
        btnUpdate.setBackground(Color.BLACK);
        btnUpdate.setForeground(Color.WHITE);
        contentPane.add(btnUpdate);

        // Button to go back to the previous screen
        JButton btnExit = new JButton("Back");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Reception().setVisible(true);
                setVisible(false);
            }
        });
        btnExit.setBounds(281, 378, 89, 23);
        btnExit.setBackground(Color.BLACK);
        btnExit.setForeground(Color.WHITE);
        contentPane.add(btnExit);

        // Button to check and display guest details
        JButton btnAdd = new JButton("Check");
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String s1 = c1.getSelectedItem();
                    // Query to get customer details based on selected guest ID
                    String query1 = "SELECT * FROM customer WHERE number = ?";
                    pst = conn.prepareStatement(query1);
                    pst.setString(1, s1);
                    ResultSet rs1 = pst.executeQuery();
                    
                    // Display customer details in text fields
                    if (rs1.next()) {
                        txt_ID.setText(rs1.getString("room"));
                        txt_Status.setText(rs1.getString("name"));
                        txt_Date.setText(rs1.getString("Check_in_Status"));
                        txt_Time.setText(rs1.getString("deposit"));
                    }
                    
                    // Query to get room price based on room number
                    String query2 = "SELECT price FROM room WHERE room_number = ?";
                    pst = conn.prepareStatement(query2);
                    pst.setString(1, txt_ID.getText());
                    ResultSet rs2 = pst.executeQuery();
                    
                    if (rs2.next()) {
                        String total = rs2.getString("price");
                        String paid = txt_Time.getText();
                        int pending = Integer.parseInt(total) - Integer.parseInt(paid);
                        
                        txt_Payment.setText(Integer.toString(pending));
                    }
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
            }
        });
        btnAdd.setBounds(56, 378, 89, 23);
        btnAdd.setBackground(Color.BLACK);
        btnAdd.setForeground(Color.WHITE);
        contentPane.add(btnAdd);

        // Set the background color
        getContentPane().setBackground(Color.WHITE);
    }
}
