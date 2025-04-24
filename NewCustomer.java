package hotel.management.system;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.util.Date;

public class NewCustomer extends JFrame {
    private JPanel contentPane;
    private JTextField t1, t2, t3, t6;
    private JComboBox<String> comboBox;
    private JRadioButton r1, r2;
    private Choice c1;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                NewCustomer frame = new NewCustomer();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public NewCustomer() {
        setBounds(530, 200, 850, 550);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/OIP1.jpeg"));
        Image i3 = i1.getImage().getScaledInstance(300, 400, Image.SCALE_DEFAULT);
        JLabel l1 = new JLabel(new ImageIcon(i3));
        l1.setBounds(480, 10, 300, 500);
        add(l1);

        JLabel lblTitle = new JLabel("NEW CUSTOMER FORM");
        lblTitle.setFont(new Font("Yu Mincho", Font.PLAIN, 20));
        lblTitle.setBounds(118, 11, 260, 53);
        contentPane.add(lblTitle);

        // ID
        JLabel lblId = new JLabel("ID :");
        lblId.setBounds(35, 76, 200, 14);
        contentPane.add(lblId);

        comboBox = new JComboBox<>(new String[]{"Phone Number", "Passport", "Voter ID", "Driving License"});
        comboBox.setBounds(271, 73, 150, 20);
        contentPane.add(comboBox);

        JLabel lblNumber = new JLabel("Number :");
        lblNumber.setBounds(35, 111, 200, 14);
        contentPane.add(lblNumber);

        t1 = new JTextField();
        t1.setBounds(271, 111, 150, 20);
        contentPane.add(t1);

        JLabel lblName = new JLabel("Name :");
        lblName.setBounds(35, 151, 200, 14);
        contentPane.add(lblName);

        t2 = new JTextField();
        t2.setBounds(271, 151, 150, 20);
        contentPane.add(t2);

        JLabel lblGender = new JLabel("Gender :");
        lblGender.setBounds(35, 191, 200, 14);
        contentPane.add(lblGender);

        r1 = new JRadioButton("Male");
        r1.setBackground(Color.WHITE);
        r1.setBounds(271, 191, 80, 12);
        contentPane.add(r1);

        r2 = new JRadioButton("Female");
        r2.setBackground(Color.WHITE);
        r2.setBounds(350, 191, 100, 12);
        contentPane.add(r2);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(r1);
        genderGroup.add(r2);

        JLabel lblCountry = new JLabel("Country :");
        lblCountry.setBounds(35, 231, 200, 14);
        contentPane.add(lblCountry);

        t3 = new JTextField();
        t3.setBounds(271, 231, 150, 20);
        contentPane.add(t3);

        JLabel lblRoom = new JLabel("Allocated Room Number :");
        lblRoom.setBounds(35, 274, 200, 14);
        contentPane.add(lblRoom);

        c1 = new Choice();
        try {
            conn c = new conn();
            ResultSet rs = c.s.executeQuery("SELECT room_number FROM room WHERE availability = 'Available'");
            while (rs.next()) {
                c1.add(rs.getString("room_number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        c1.setBounds(271, 274, 150, 20);
        contentPane.add(c1);

        JLabel lblCheckIn = new JLabel("Check-In Status :");
        lblCheckIn.setBounds(35, 316, 200, 14);
        contentPane.add(lblCheckIn);

        Date date = new Date();
        JLabel lblCheckInValue = new JLabel(date.toString());
        lblCheckInValue.setBounds(271, 316, 200, 14);
        contentPane.add(lblCheckInValue);

        JLabel lblDeposit = new JLabel("Deposit :");
        lblDeposit.setBounds(35, 359, 200, 14);
        contentPane.add(lblDeposit);

        t6 = new JTextField();
        t6.setBounds(271, 359, 150, 20);
        contentPane.add(t6);

        JButton btnAdd = new JButton("Add");
        btnAdd.addActionListener(e -> {
            String document = (String) comboBox.getSelectedItem();
            String number = t1.getText();
            String name = t2.getText();
            String gender = r1.isSelected() ? "Male" : r2.isSelected() ? "Female" : null;
            String country = t3.getText();
            String roomNumber = c1.getSelectedItem();
            String checkInStatus = lblCheckInValue.getText();
            String deposit = t6.getText();

            try {
                conn c = new conn();
                String insertCustomer = "INSERT INTO customer (document, number, name, gender, country, room, Check_in_Status, deposit) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pst = c.c.prepareStatement(insertCustomer);
                pst.setString(1, document);
                pst.setString(2, number);
                pst.setString(3, name);
                pst.setString(4, gender);
                pst.setString(5, country);
                pst.setString(6, roomNumber);
                pst.setString(7, checkInStatus);
                pst.setString(8, deposit);

                String updateRoom = "UPDATE room SET availability = 'Occupied' WHERE room_number = ?";
                PreparedStatement pst2 = c.c.prepareStatement(updateRoom);
                pst2.setString(1, roomNumber);

                pst.executeUpdate();
                pst2.executeUpdate();

                JOptionPane.showMessageDialog(null, "Customer Added Successfully!");
                new Reception().setVisible(true);
                setVisible(false);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
        btnAdd.setBounds(100, 430, 120, 30);
        btnAdd.setBackground(Color.BLACK);
        btnAdd.setForeground(Color.WHITE);
        contentPane.add(btnAdd);

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(e -> {
            new Reception().setVisible(true);
            setVisible(false);
        });
        btnBack.setBounds(260, 430, 120, 30);
        btnBack.setBackground(Color.BLACK);
        btnBack.setForeground(Color.WHITE);
        contentPane.add(btnBack);

        contentPane.setBackground(Color.WHITE);
    }
}
