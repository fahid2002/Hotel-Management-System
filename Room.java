package hotel.management.system;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import net.proteanit.sql.DbUtils;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Room extends JFrame {
    private JPanel contentPane;
    private JTable table;
    private JComboBox<String> cleanStatusComboBox;
    private JComboBox<String> availabilityComboBox;
    private JTextField priceTextField;  // JTextField for Price

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Room frame = new Room();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Room() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 200, 900, 600);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblRoomDetails = new JLabel("Room Details");
        lblRoomDetails.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblRoomDetails.setBounds(350, 10, 200, 30);
        contentPane.add(lblRoomDetails);

        // Table to display room details
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 50, 850, 300);
        contentPane.add(scrollPane);

        // Add ComboBox for Clean Status
        cleanStatusComboBox = new JComboBox<>(new String[] {"Clean", "Dirty"});
        cleanStatusComboBox.setBounds(200, 400, 150, 30);
        contentPane.add(cleanStatusComboBox);

        // Add ComboBox for Availability
        availabilityComboBox = new JComboBox<>(new String[] {"Available", "Occupied"});
        availabilityComboBox.setBounds(400, 400, 150, 30);
        contentPane.add(availabilityComboBox);

        // Add JTextField for Price
        priceTextField = new JTextField();
        priceTextField.setBounds(600, 400, 150, 30);
        contentPane.add(priceTextField);

        // Button to load data
        JButton btnLoadData = new JButton("Load Data");
        btnLoadData.addActionListener(e -> loadRoomData());
        btnLoadData.setBounds(100, 500, 150, 30);
        btnLoadData.setBackground(Color.BLACK);
        btnLoadData.setForeground(Color.WHITE);
        contentPane.add(btnLoadData);

        // Button to update room status
        JButton btnUpdate = new JButton("Update Room");
        btnUpdate.addActionListener(e -> updateRoomStatus());
        btnUpdate.setBounds(400, 500, 150, 30);
        btnUpdate.setBackground(Color.BLACK);
        btnUpdate.setForeground(Color.WHITE);
        contentPane.add(btnUpdate);

        // Button to go back
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(e -> {
            setVisible(false);
            new Reception().setVisible(true);
        });
        btnBack.setBounds(700, 500, 150, 30);
        btnBack.setBackground(Color.BLACK);
        btnBack.setForeground(Color.WHITE);
        contentPane.add(btnBack);

        // Custom cell renderer for cleaning status
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Add red/green circles for Cleaning Status column
                if (column == 2 && value != null) {
                    JPanel panel = new JPanel();
                    panel.setLayout(new FlowLayout(FlowLayout.LEFT));

                    String cleanStatus = value.toString().toLowerCase();
                    JLabel dotLabel = new JLabel();
                    dotLabel.setOpaque(true);
                    dotLabel.setPreferredSize(new Dimension(12, 12));

                    if (cleanStatus.equals("clean")) {
                        dotLabel.setBackground(Color.GREEN);
                    } else if (cleanStatus.equals("dirty")) {
                        dotLabel.setBackground(Color.RED);
                    }

                    panel.add(dotLabel);
                    JLabel textLabel = new JLabel(" " + value.toString());
                    panel.add(textLabel);
                    return panel;
                }
                return c;
            }
        });
    }

    // Method to load room data into the table
    private void loadRoomData() {
        try {
            conn c = new conn();
            String query = "SELECT * FROM room";
            ResultSet rs = c.s.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(rs));

            // Set column widths after table model is set
            table.getColumnModel().getColumn(0).setPreferredWidth(60); // Room number
            table.getColumnModel().getColumn(1).setPreferredWidth(100); // Availability
            table.getColumnModel().getColumn(2).setPreferredWidth(120); // Cleaning status
            table.getColumnModel().getColumn(3).setPreferredWidth(100); // Price (Assuming it’s the 4th column)
            table.setRowHeight(30);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to load room data.");
        }
    }

    // Method to update room status and price
    private void updateRoomStatus() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String roomNumber = table.getValueAt(selectedRow, 0).toString();
            String cleanStatus = cleanStatusComboBox.getSelectedItem().toString();
            String availability = availabilityComboBox.getSelectedItem().toString();
            String price = priceTextField.getText().trim();  // Get price from JTextField

            // SQL query setup
            try {
                conn c = new conn();
                String query = "UPDATE room SET cleaning_status = ?, availability = ?";
                
                // If price is provided, include it in the query
                if (!price.isEmpty()) {
                    query += ", price = ?";
                }
                query += " WHERE room_number = ?";
                
                PreparedStatement pst = c.c.prepareStatement(query);
                pst.setString(1, cleanStatus);
                pst.setString(2, availability);

                // If price is provided, set the price in the statement
                if (!price.isEmpty()) {
                    pst.setString(3, price);
                    pst.setString(4, roomNumber);
                } else {
                    pst.setString(3, roomNumber);
                }

                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Room status updated successfully!");
                    loadRoomData(); // Refresh table data
                } else {
                    JOptionPane.showMessageDialog(null, "Room number not found.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Failed to update room status and price.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a room from the table.");
        }
    }
}
