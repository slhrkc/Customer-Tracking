
import java.math.BigDecimal;
import java.sql.CallableStatement;
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
    
    private static final String DB_NAME = "makarna"; // the name of the database
            
    private static String framework = "embedded";
    private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private static String protocol = "jdbc:derby:";
    
    
    
    
    private static Connection conn;
    private static ResultSet rs;
    private static Statement statement ;
    
    private static PreparedStatement selectAllCustomers;
    private static PreparedStatement insertCustomer ;
    private static PreparedStatement insertPayment;
    private static PreparedStatement insertSale;
    private static PreparedStatement insertTransaction;
    private static PreparedStatement searchCustomers;
    private static PreparedStatement getCustomer;
    private static CallableStatement selectTransactions;
    private static CallableStatement selectTotalDebt;





    public static Customer getTotalDebt(Customer customer) throws SQLException{
        selectTotalDebt.setInt(1, customer.getID());
        ResultSet temp = selectTotalDebt.executeQuery();
        
        // if resultSet is empty, it means there is no transactions for this  customer
        // Which means totalDebt is ZERO
        
            while(temp.next()){ customer.totalDebt = temp.getBigDecimal(1);}
            
            if(customer.totalDebt == null) {customer.totalDebt = BigDecimal.ZERO;}
            
        
        return customer;
    }
    
    public static Customer getCustomer(int id) throws SQLException{
        System.out.println(id);
        Customer customer = new Customer();

        getCustomer.setInt(1, id);
        rs = getCustomer.executeQuery();
        while(rs.next()){
        customer.id(rs.getInt(1));
        customer.name(rs.getString(2));
        customer.lastName(rs.getString(3));
        customer.address(rs.getString(5));
        customer.pbx(rs.getString(6));
        customer.gsm(rs.getString(7));
        }
        
 
        return customer;
    }
    public static ResultSet searchCustomers(String s) throws SQLException{
        System.out.println(s);
        String searchText = "%%"+s+"%%";
        searchCustomers.setString(1, searchText);
        rs = searchCustomers.executeQuery();
        return rs;
    }
    
    
    /**
     * 
     * @param d The date from which customers didn't visit the store.
     * @return The resultSet that list the customers who didn't visit the store since d, and have a dept.
     */
    public static ResultSet searchCustomers(Date d) throws SQLException{
        
        return rs;
    }
    
    /**
     * 
     * @param customer the customer to be saved into the database
     */
    public static Customer saveCustomer(Customer customer) throws SQLException{
        if(insertCustomer==null){
            System.out.println("Haydaaaaaaaaa");
        }
        

        insertCustomer.setString(1, customer.name);
        insertCustomer.setString(2, customer.lastName);
        insertCustomer.setString(3, customer.name + " "+ customer.lastName);
        insertCustomer.setString(4, customer.address);
        insertCustomer.setString(5, customer.pbx);
        insertCustomer.setString(6, customer.gsm);
        
        insertCustomer.executeUpdate();
        rs = insertCustomer.getGeneratedKeys();
        if(rs==null){System.out.println("Haydaaaaaaaa");}
        while(rs.next()){
            customer.id(rs.getInt(1));
            
        }
        return customer;
 
        //selectAllCustomers();
    }
    
    public static void savePayment(Payment payment) throws SQLException{
        if(payment == null){System.out.println("payment is null");}
        if(payment.getCustomer() == null){System.out.println("customer is null");}
        

        insertTransaction.setInt(1, payment.getCustomer().getID());
        insertTransaction.setBigDecimal(2, payment.getPaymentAmount());
        insertTransaction.setBigDecimal(3, BigDecimal.ZERO);
        insertTransaction.setDate(4, new java.sql.Date(payment.getDate().getTime()));
        
        insertTransaction.executeUpdate();
    }
    
    public static void saveSale(Sale sale) throws SQLException{
        insertTransaction.setInt(1, sale.getCustomer().getID());
        insertTransaction.setBigDecimal(2, sale.getFirstPaymentAmount());
        insertTransaction.setBigDecimal(3, sale.getSaleAmount());
        insertTransaction.setDate(4, new java.sql.Date(sale.getDate().getTime()));
        
        insertTransaction.executeUpdate();
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
            
        }finally{try {
                initializePreparedStatements();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseOperations.class.getName()).log(Level.SEVERE, null, ex);
            }
}
        
        
        
        


            System.out.println("Connecting");      

        
        

    }
    
    /**
     *
     */
    public static void initializePreparedStatements() throws SQLException{
//        INTO tableName(col1, col2) VALUES (?,?)
        
        selectTotalDebt = conn.prepareCall("SELECT SUM (saleAmount - paymentAmount) as totalDebt FROM trans where c_id = ?");
        
        selectTransactions = conn.prepareCall("select t_id, paymentAmount, saleAmount, transDate from trans where c_id = ?");
        
        selectAllCustomers = conn.prepareStatement("select * from Customer");
        
        getCustomer = conn.prepareStatement("select* from Customer where c_id = ?");
        
        searchCustomers = conn.prepareStatement("select c_id, cName, cSurname, cPbx  from Customer where UPPER(cSearchName) LIKE UPPER(?)");
        
        insertCustomer  = conn.prepareStatement(
                        "insert into Customer(cName, cSurname, cSearchName, cAddress, cPbx, cGsm) values (?, ?, ?, ?, ?, ? )",Statement.RETURN_GENERATED_KEYS);
        insertPayment   = conn.prepareStatement(
                        "insert into Payment(c_id, paymentAmount, paymentDate) values (?, ?, ?)");
        insertSale      = conn.prepareStatement(
                        "insert into Sale(c_id, saleAmount, firstPayAmount, saleDate) values (?, ?, ?, ?)");
        insertTransaction = conn.prepareStatement(
                        "insert into TRANS(c_id, paymentAmount, saleAmount, transDate) values (?, ?, ?, ?)");
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
          createTransactionTable();
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
                    + "cSearchName varchar(48) not null,"
                    + "cAddress varchar(100), "
                    + "cPbx varchar(10),"
                    + "cGsm varchar(10),"                    
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
    
    private static void createTransactionTable() {
        try {
            statement = conn.createStatement();
            System.out.println("Creating trans table");
            statement.execute(""
                    + "create table TRANS ("
                    + "t_id INTEGER primary key generated always as identity(start with 1, increment by 1),"
                    + "c_id INTEGER not null, "
                    + "paymentAmount decimal(20,2) not null, "
                    + "saleAmount decimal(20,2) not null, "
                    + "transDate date not null,"
                    + "FOREIGN KEY (c_id)"
                    + "REFERENCES CUSTOMER (c_id)"
                    + ")");
            System.out.println("Created TRANSACTION table");

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private static void selectAllCustomers() throws SQLException{
        rs = selectAllCustomers.executeQuery();
        while(rs.next()){
            System.out.print(rs.getInt(1));
            System.out.print(rs.getString(2));
            System.out.println(rs.getString(3));

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

    public static ResultSet getTransactions(Customer customer) throws SQLException {
        selectTransactions.setInt(1, customer.getID());
        return selectTransactions.executeQuery();
    }
    
    
}
