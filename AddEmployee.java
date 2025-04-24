package hotel.management.system;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class AddEmployee extends JFrame {

    // Declare GUI components
    JTextField textField, textField_1, textField_3, textField_4, textField_6;
    JComboBox<String> c1;

    public AddEmployee() {
        // Set window properties
        getContentPane().setForeground(Color.BLUE);
        getContentPane().setBackground(Color.WHITE);
        setTitle("ADD EMPLOYEE DETAILS");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(900, 600);
        getContentPane().setLayout(null);

        // Create and add label and text field for Name
        JLabel nameLabel = new JLabel("NAME");
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        nameLabel.setBounds(60, 30, 150, 27);
        add(nameLabel);

        textField = new JTextField();
        textField.setBounds(200, 30, 150, 27);
        add(textField);

        // Create and add Save button
        JButton saveButton = new JButton("SAVE");
        saveButton.setBounds(200, 370, 150, 30);
        saveButton.setBackground(Color.BLACK);
        saveButton.setForeground(Color.WHITE);
        add(saveButton);

        // Create and add label and text field for Age
        JLabel ageLabel = new JLabel("AGE");
        ageLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        ageLabel.setBounds(60, 80, 150, 27);
        add(ageLabel);

        textField_1 = new JTextField();
        textField_1.setBounds(200, 80, 150, 27);
        add(textField_1);

        // Create and add Gender radio buttons
        JLabel genderLabel = new JLabel("GENDER");
        genderLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        genderLabel.setBounds(60, 120, 150, 27);
        add(genderLabel);

        JRadioButton maleRadioButton = new JRadioButton("MALE");
        maleRadioButton.setBackground(Color.WHITE);
        maleRadioButton.setBounds(200, 120, 70, 27);
        add(maleRadioButton);

        JRadioButton femaleRadioButton = new JRadioButton("FEMALE");
        femaleRadioButton.setBackground(Color.WHITE);
        femaleRadioButton.setBounds(280, 120, 70, 27);
        add(femaleRadioButton);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);

        // Create and add label and JComboBox for Job role selection
        JLabel jobLabel = new JLabel("JOB");
        jobLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        jobLabel.setBounds(60, 170, 150, 27);
        add(jobLabel);

        String[] jobRoles = { "Front Desk Clerks", "Porters", "Housekeeping", "Kitchen Staff", "Room Service", "Waiter/Waitress", "Manager", "Accountant", "Chef" };
        c1 = new JComboBox<>(jobRoles);
        c1.setBackground(Color.WHITE);
        c1.setBounds(200, 170, 150, 30);
        add(c1);

        // Create and add label and text field for Salary
        JLabel salaryLabel = new JLabel("SALARY");
        salaryLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        salaryLabel.setBounds(60, 220, 150, 27);
        add(salaryLabel);

        textField_3 = new JTextField();
        textField_3.setBounds(200, 220, 150, 27);
        add(textField_3);

        // Create and add label and text field for Phone number
        JLabel phoneLabel = new JLabel("PHONE");
        phoneLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        phoneLabel.setBounds(60, 270, 150, 27);
        add(phoneLabel);

        textField_4 = new JTextField();
        textField_4.setBounds(200, 270, 150, 27);
        add(textField_4);

        // Create and add label and text field for Email
        JLabel emailLabel = new JLabel("EMAIL");
        emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        emailLabel.setBounds(60, 320, 150, 27);
        add(emailLabel);

        textField_6 = new JTextField();
        textField_6.setBounds(200, 320, 150, 27);
        add(textField_6);

        // Add a title label
        JLabel title = new JLabel("ADD EMPLOYEE DETAILS");
        title.setForeground(Color.BLUE);
        title.setFont(new Font("Tahoma", Font.PLAIN, 31));
        title.setBounds(450, 24, 442, 35);
        add(title);

        // Set an image on the GUI
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icons/hi.jpeg"));
        Image scaledImage = icon.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setBounds(410, 80, 480, 410);
        add(imageLabel);

        // Define action for the Save button
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String name = textField.getText();
                String age = textField_1.getText();
                String salary = textField_3.getText();
                String phone = textField_4.getText();
                String email = textField_6.getText();

                String gender = null;
                if (maleRadioButton.isSelected()) {
                    gender = "Male";
                } else if (femaleRadioButton.isSelected()) {
                    gender = "Female";
                }

                String job = (String) c1.getSelectedItem();

                try {
                    // Database connection and insertion
                    conn c = new conn();
                    String query = "INSERT INTO employee VALUES ('" + name + "', '" + age + "', '" + gender + "', '" + job + "', '" + salary + "', '" + phone + "', '" + email + "')";
                    c.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Employee Added Successfully");
                    setVisible(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Set window properties
        setLocation(530, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new AddEmployee();
    }
}
