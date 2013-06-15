
import java.util.Date;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author salih
 */
public class Transaction {
    private Date date;
    
    public Transaction(Date date){
        this.date = date;
    }
    
    public Date getDate(){
        return date;
    }
    
}
