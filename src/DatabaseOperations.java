
import java.sql.ResultSet;
import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Properties;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author salih
 */
public class DatabaseOperations {
    
    private static final String DB_NAME = "ertas"; // the name of the database
            
    private static String framework = "embedded";
    private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private static String protocol = "jdbc:derby:";
    
    
    private static Connection conn;
    private static ResultSet rs;
    private static PreparedStatement insertCustomer ;
    private static PreparedStatement insertPayment;
    private static PreparedStatement insertSale;




    
    public static Customer getCustomer(int id){
        Customer customer = new Customer();
        return customer;
    }
    public static ResultSet searchCustomers(String s){
        rs = null;
        return rs;
    }
    
    
    /**
     * 
     * @param d The date from which customers didn't visit the store.
     * @return The resultSet that list the customers who didn't visit the store since d, and have a dept.
     */
    public static ResultSet searchCustomers(Date d){
        rs = null;
        return rs;
    }
    
    /**
     * 
     * @param customer the customer to be saved into the database
     */
    public static void saveCustomer(Customer customer){
        
    }
    
    public static void initializeDB() throws SQLException{
        loadDriver();
        
        conn  = DriverManager.getConnection(protocol + DB_NAME
                    + ";create=true");
        
        conn.setAutoCommit(false);    
        
        
        
        
        

    }
    
    /**
     *
     */
    public static void initializePreparedStatements() throws SQLException{
        insertCustomer  = conn.prepareStatement(
                        "insert into Customer values (?, ?)");
        insertPayment   = conn.prepareStatement(
                        "insert into Payment values (?, ?)");
        insertSale      = conn.prepareStatement(
                        "insert into Sale values (?, ?)");
    }
    
    
        private static void loadDriver() {
        /*
         *  The JDBC driver is loaded by loading its class.
         *  If you are using JDBC 4.0 (Java SE 6) or newer, JDBC drivers may
         *  be automatically loaded, making this code optional.
         *
         *  In an embedded environment, this will also start up the Derby
         *  engine (though not any databases), since it is not already
         *  running. In a client environment, the Derby engine is being run
         *  by the network server framework.
         *
         *  In an embedded environment, any static Derby system properties
         *  must be set before loading the driver to take effect.
         */
        try {
            Class.forName(driver).newInstance();
            System.out.println("Loaded the appropriate driver");
        } catch (ClassNotFoundException cnfe) {
            System.err.println("\nUnable to load the JDBC driver " + driver);
            System.err.println("Please check your CLASSPATH.");
            cnfe.printStackTrace(System.err);
        } catch (InstantiationException ie) {
            System.err.println(
                        "\nUnable to instantiate the JDBC driver " + driver);
            ie.printStackTrace(System.err);
        } catch (IllegalAccessException iae) {
            System.err.println(
                        "\nNot allowed to access the JDBC driver " + driver);
            iae.printStackTrace(System.err);
        }
    }
    
    
}
