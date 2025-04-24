
// Package declaration to group related classes together
package hotel.management.system;

// Importing required classes for SQL operations
import java.sql.*;

// Class to handle database connection
public class conn {
    Connection c;  // Variable to hold the database connection
    Statement s;   // Variable to execute SQL queries

    // Constructor to initialize the connection and statement
    public conn() {  
        try {  
            // Load and register the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");  

            // Establish a connection to the MySQL database
            // "jdbc:mysql:///hotelmanagementsystem" specifies the database URL
            // "root" is the username, and "jamesbond007" is the password
            c = DriverManager.getConnection("jdbc:mysql:///hotelmanagementsystem", "root", "jamesbond007"); 
            
            // Create a Statement object to execute SQL queries
            s = c.createStatement();  
        } catch (Exception e) { 
            // Print the stack trace for debugging
            e.printStackTrace();  

            // Print a custom message with the exception
            System.out.println(e);  
        }  
    }  
}
 