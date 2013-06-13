
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
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Salih ERİKCİ
 */
public class DatabaseOperations {
    
    private static final String DB_NAME = "qazxcswedvfr"; // the name of the database
            
    private static String framework = "embedded";
    private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private static String protocol = "jdbc:derby:";
    
    
    
    
    private static Connection conn;
    private static ResultSet rs;
    private static Statement statement ;
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
    
    public static void initializeDB()  {
        
        loadDriver();
        
        try{
            connectToDatabase();
        }catch(Exception ex){
            try {
                createDatabase();
            } catch (SQLException ex1) {
                Logger.getLogger(DatabaseOperations.class.getName()).log(Level.SEVERE, null, ex1);
            }
            
        }
        
        
        
        


            System.out.println("Connecting");
            
  
           
        
        
        
        
        
        
        

    }
    
    /**
     *
     */
    public static void initializePreparedStatements() throws SQLException{
        
        insertCustomer  = conn.prepareStatement(
                        "insert into Customer values (?, ?, ?, ?, ?)");
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

    private static void createDatabase() throws SQLException {
          conn = DriverManager.getConnection(protocol + DB_NAME
                    + ";create=true"); //To change body of generated methods, choose Tools | Templates.
          System.out.println("Database created!!!");

          createCustomerTable();
          createPaymentTable();
          createSaleTable();
    }

    private static void createCustomerTable() throws SQLException{
        try {
            statement = conn.createStatement();
            System.out.println("Creating customer table");
            statement.execute(""
                    + "create table CUSTOMER ("
                    + "c_id INTEGER primary key generated always as identity(start with 1, increment by 1),"
                    + "cName varchar(24) not null, "
                    + "cSurname varchar(24) not null,"
                    + "cAddress varchar(100), "
                    + "cLastVisitDate date ,"
                    + "totalDept decimal(20,2) "
                    + ")");
            System.out.println("Created customer table");

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void createPaymentTable() {
        try {
            statement = conn.createStatement();
            System.out.println("Creating Payment table");
            statement.execute(""
                    + "create table PAYMENT ("
                    + "p_id INTEGER primary key generated always as identity(start with 1, increment by 1),"
                    + "c_id INTEGER not null, "
                    + "paymentAmount decimal(20,2) not null, "
                    + "paymentDate date not null,"
                    + "FOREIGN KEY (c_id)"
                    + "REFERENCES CUSTOMER (c_id)"
                    + ")");
            System.out.println("Created Payment table");

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private static void createSaleTable() {
        try {
            statement = conn.createStatement();
            System.out.println("Creating Sale table");
            statement.execute(""
                    + "create table SALE ("
                    + "s_id INTEGER primary key generated always as identity(start with 1, increment by 1),"
                    + "c_id INTEGER not null, "
                    + "saleAmount decimal(20,2) not null, "
                    + "firstPayAmount decimal(20,2) not null,"
                    + "saleDate date not null,"
                    + "FOREIGN KEY (c_id)"
                    + "REFERENCES Customer (c_id)"
                    + ")");
            System.out.println("Created Sale table");

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseOperations.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }

    private static void connectToDatabase() throws SQLException {
            conn = DriverManager.getConnection(protocol + DB_NAME
                      + ";create=false");
        }
    
    
}
