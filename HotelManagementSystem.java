package hotel.management.system;

import java.awt.*; // Import for color, layout, and other GUI elements
import javax.swing.*;  // Import for JFrame, JLabel, JButton, etc.
import java.awt.event.*; // Import for ActionListener and event handling

public class HotelManagementSystem extends JFrame implements ActionListener {

    JLabel l1; // Label to hold the background image and text
    JButton b1; // Button to navigate to the login screen

    public HotelManagementSystem() {
        
        // Set up the window size, layout, and location
        setSize(1366, 430);          // Frame size set to 1366x430 pixels
        setLayout(null);             // Use absolute layout
        setLocation(100, 100);      // Set the window location on the screen

        l1 = new JLabel("");        // Initialize the label (for background image)
        b1 = new JButton("Next");   // Initialize the button with label "Next"

        b1.setBackground(Color.WHITE); // Set the button background color to white
        b1.setForeground(Color.BLACK); // Set the button text color to black
        
        // Add background image to the label
        ImageIcon i1  = new ImageIcon(ClassLoader.getSystemResource("icons/1.jpeg")); // Load the image
        Image i3 = i1.getImage().getScaledInstance(2000, 1335, Image.SCALE_DEFAULT); // Scale the image
        ImageIcon i2 = new ImageIcon(i3); // Convert the image into an icon
        l1 = new JLabel(i2); // Set the image icon as the label content

        // Add the title text to the screen
        JLabel lid = new JLabel("HOTEL MANAGEMENT SYSTEM"); // Create the title label
        lid.setBounds(30, 300, 1500, 100); // Set the position and size of the title
        lid.setFont(new Font("serif", Font.PLAIN, 40)); // Set the font size and style
        lid.setForeground(Color.white); // Set the text color to white
        l1.add(lid); // Add the title to the background label

        // Set button position and size
        b1.setBounds(1170, 325, 150, 50); // Set the position of the "Next" button
        l1.setBounds(0, 0, 1366, 390); // Set the background label size

        // Add the button to the label
        l1.add(b1); 
        add(l1); // Add the label (with image and button) to the frame

        // Add action listener for the "Next" button
        b1.addActionListener(this);

        setVisible(true); // Make the frame visible

        // Infinite loop for flashing effect on the title label
        while(true){
            lid.setVisible(false); // Hide the title label
            try{
                Thread.sleep(500); // Sleep for 500 milliseconds (half a second)
            } catch(Exception e){} 
            lid.setVisible(true); // Show the title label
            try{
                Thread.sleep(500); // Sleep for another 500 milliseconds
            } catch(Exception e){}
        }
    }

    // Action performed when "Next" button is clicked
    public void actionPerformed(ActionEvent ae) {
        new Login().setVisible(true); // Open the Login window
        this.setVisible(false); // Close the current (Welcome) window
    }

    public static void main(String[] args) {
        HotelManagementSystem window = new HotelManagementSystem(); // Create an instance of the HotelManagementSystem
        window.setVisible(true); // Show the window
    }
}
