
import java.sql.ResultSet;
import java.util.Date;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author salih
 */
public class DatabaseOperations {
    
    public static Customer getCustomer(int id){
        Customer customer = new Customer();
        return customer;
    }
    public static ResultSet searchCustomers(String s){
        ResultSet rs = null;
        return rs;
    }
    
    
    /**
     * 
     * @param d The date from which customers didn't visit the store.
     * @return The resultSet that list the customers who didn't visit the store since d, and have a dept.
     */
    public static ResultSet searchCustomers(Date d){
        ResultSet rs = null;
        return rs;
    }
    
    /**
     * 
     * @param customer the customer to be saved into the database
     */
    public static void saveCustomer(Customer customer){
        
    }
    
    
}
