
import java.math.BigDecimal;
import java.util.Date;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author salih
 */
public class Payment extends Transaction{
    private BigDecimal paymentAmount;
    
    public Payment(Date d, BigDecimal paymentAmount){
        super(d);
        this.paymentAmount = paymentAmount;
    }
    public BigDecimal getPaymentAmount(){
        return paymentAmount;
    }
    
}
