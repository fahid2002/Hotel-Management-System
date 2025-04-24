package hotel.management.system;

import java.awt.*; // For GUI components and layout managers
import java.sql.*; // For database connectivity and SQL operations
import javax.swing.*; // For Swing components
import javax.swing.border.EmptyBorder; // To add padding to the content pane
import net.proteanit.sql.DbUtils; // To convert ResultSet into TableModel for JTable
import java.awt.event.*; // For handling button events

public class SearchRoom extends JFrame {
    Connection conn = null; // Database connection object
    PreparedStatement pst = null; // PreparedStatement for parameterized SQL queries
    ResultSet rs = null; // ResultSet to store query results
    private JPanel contentPane; // The main content pane of the frame
    private JTable table; // Table to display room information
    Choice c1; // Dropdown to select bed type

    /**
     * Main method to launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                SearchRoom frame = new SearchRoom(); // Create the frame
                frame.setVisible(true); // Make the frame visible
            } catch (Exception e) {
                e.printStackTrace(); // Print stack trace for debugging
            }
        });
    }

    /**
     * Constructor for SearchRoom. Sets up the GUI components and event handling.
     */
    public SearchRoom() throws SQLException {
        // Set up the frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit application on close
        setBounds(530, 200, 800, 600); // Set position and size of the frame

        // Create the main content pane
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // Add padding
        setContentPane(contentPane); // Set the content pane
        contentPane.setLayout(null); // Use absolute layout for custom positioning

        // Add a title label
        JLabel lblSearchForRoom = new JLabel("Search For Room");
        lblSearchForRoom.setFont(new Font("Tahoma", Font.PLAIN, 20)); // Set font
        lblSearchForRoom.setBounds(300, 11, 186, 31); // Set position and size
        contentPane.add(lblSearchForRoom); // Add to content pane

        // Add a label for room bed type
        JLabel lblRoomAvailable = new JLabel("Room Bed Type:");
        lblRoomAvailable.setBounds(50, 73, 96, 14); // Set position and size
        contentPane.add(lblRoomAvailable); // Add to content pane

        // Add a dropdown (Choice) for bed type selection
        c1 = new Choice();
        c1.add("Single Bed"); // Add option "Single Bed"
        c1.add("Double Bed"); // Add option "Double Bed"
        c1.setBounds(153, 70, 120, 20); // Set position and size
        contentPane.add(c1); // Add to content pane

        // Add a checkbox to filter available rooms
        JCheckBox checkRoom = new JCheckBox("Only display Available");
        checkRoom.setBounds(400, 69, 205, 23); // Set position and size
        checkRoom.setBackground(Color.WHITE); // Set background color
        contentPane.add(checkRoom); // Add to content pane

        // Add a JTable to display room data within a scroll pane
        table = new JTable(); // Initialize the table
        JScrollPane scrollPane = new JScrollPane(table); // Add table to a scroll pane
        scrollPane.setBounds(20, 150, 740, 300); // Set position and size of the scroll pane
        contentPane.add(scrollPane); // Add the scroll pane to the content pane

        // Add column headers for the table
        JLabel lblRoomType = new JLabel("Room Number");
        lblRoomType.setBounds(23, 125, 96, 14); // Set position and size
        contentPane.add(lblRoomType); // Add to content pane

        JLabel lblRoomAvailable_1 = new JLabel("Availability");
        lblRoomAvailable_1.setBounds(175, 125, 120, 14); // Set position and size
        contentPane.add(lblRoomAvailable_1); // Add to content pane

        JLabel lblPrice_1 = new JLabel("Price");
        lblPrice_1.setBounds(458, 125, 46, 14); // Set position and size
        contentPane.add(lblPrice_1); // Add to content pane

        JLabel l1 = new JLabel("Bed Type");
        l1.setBounds(580, 125, 96, 14); // Set position and size
        contentPane.add(l1); // Add to content pane

        JLabel lblCleanStatus = new JLabel("Clean Status");
        lblCleanStatus.setBounds(306, 125, 96, 14); // Set position and size
        contentPane.add(lblCleanStatus); // Add to content pane

        // Add a search button to fetch and display room data
        JButton btnSearch = new JButton("Search");
        btnSearch.addActionListener(e -> {
            // SQL query to fetch rooms by bed type
            String SQL = "select * from Room where bed_type = '" + c1.getSelectedItem() + "'";
            // SQL query to fetch only available rooms by bed type
            String SQL2 = "select * from Room where availability = 'Available' AND bed_type = '" + c1.getSelectedItem() + "'";
            try {
                conn c = new conn(); // Create a new database connection
                rs = c.s.executeQuery(SQL); // Execute the query
                table.setModel(DbUtils.resultSetToTableModel(rs)); // Populate table with results

                // If checkbox is selected, fetch only available rooms
                if (checkRoom.isSelected()) {
                    rs = c.s.executeQuery(SQL2);
                    table.setModel(DbUtils.resultSetToTableModel(rs));
                }
            } catch (SQLException ss) {
                ss.printStackTrace(); // Print any SQL exceptions
            }
        });
        btnSearch.setBounds(250, 500, 120, 30); // Set position and size
        btnSearch.setBackground(Color.BLACK); // Set button background color
        btnSearch.setForeground(Color.WHITE); // Set button text color
        contentPane.add(btnSearch); // Add button to content pane

        // Add a back button to return to the previous screen
        JButton btnExit = new JButton("Back");
        btnExit.addActionListener(e -> {
            new Reception().setVisible(true); // Open the Reception screen
            setVisible(false); // Hide the current screen
        });
        btnExit.setBounds(400, 500, 120, 30); // Set position and size
        btnExit.setBackground(Color.BLACK); // Set button background color
        btnExit.setForeground(Color.WHITE); // Set button text color
        contentPane.add(btnExit); // Add button to content pane

        // Set the background color for the frame
        getContentPane().setBackground(Color.WHITE);
    }
}
