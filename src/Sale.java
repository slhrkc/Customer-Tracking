
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
public class Sale extends Transaction{
    private BigDecimal saleAmount;
    private BigDecimal paymentAmount;
    public Sale(Date d, BigDecimal saleAmount, BigDecimal paymentAmount){
        super(d);
    }
    
}
