
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
public class Sale extends Transaction{
    private BigDecimal saleAmount;
    private BigDecimal paymentAmount;
    public Sale(Date d, BigDecimal saleAmount, BigDecimal paymentAmount, Customer customer){
        super(d, customer);
        this.saleAmount = saleAmount;
        this.paymentAmount = paymentAmount;
    }

    @Override
    public String printRecipe() {
       Calendar c = Calendar.getInstance();
        c.setTime(this.getDate());
        
        String temp;
        temp = this.getCustomer().name + " " +this.getCustomer().lastName;
        temp += "\n"+"Satış Tutarı : "+this.saleAmount + " TL";
        temp += "\n"+"Peşinat Tutarı : "+this.getFirstPaymentAmount() + " TL";

        temp += "\n"+ "Satış Tarihi :"+c.get(Calendar.DAY_OF_MONTH)+"-"+(c.get(Calendar.MONTH)+1) + "-"+c.get(Calendar.YEAR);
        
        System.out.println(temp);
        
        return temp;    }
    
    public BigDecimal getSaleAmount(){
        return saleAmount;
    }
    
    public BigDecimal getFirstPaymentAmount(){
        return paymentAmount;
    }
    
}
