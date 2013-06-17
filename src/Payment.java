
import java.math.BigDecimal;
import java.util.Calendar;
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
    
    public Payment(Date d, BigDecimal paymentAmount, Customer customer){
        super(d, customer);
        this.paymentAmount = paymentAmount;
    }
    public BigDecimal getPaymentAmount(){
        return paymentAmount;
    }

    @Override
    public String printRecipe(){
        Calendar c = Calendar.getInstance();
        c.setTime(this.getDate());
        
        String temp;
        temp = this.getCustomer().name + " " +this.getCustomer().lastName;
        temp += "\n"+"Ödeme Tutarı : "+this.getPaymentAmount() + " TL";
        temp += "\n"+ "Ödeme Tarihi :"+c.get(Calendar.DAY_OF_MONTH)+"-"+(c.get(Calendar.MONTH)+1) + "-"+c.get(Calendar.YEAR);
        
        System.out.println(temp);
        
        return temp;
    
    }
    
}
