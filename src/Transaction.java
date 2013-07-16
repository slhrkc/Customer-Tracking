
import java.util.Date;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author salih
 */
public abstract class Transaction {

    private int id;
    private Date date;
    private Customer customer;

    public Transaction(Date date, Customer customer) {
        this.date = date;
        this.customer = customer;
    }

    public Date getDate() {
        return date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setId(int i) {
        this.id = i;
    }

    public int getId() {
        return id;
    }

    /**
     *
     * @return String representation of the Transaction
     */
    public abstract String printRecipe();
}
