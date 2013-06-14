import java.math.BigDecimal;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: salih
 * Date: 04.06.2013
 * Time: 14:43
 * To change this template use File | Settings | File Templates.
 */
public class Customer {
    
    private int id;
    protected String name;
    protected String lastName;
    protected String address;
    protected String pbx;
    protected String gsm;
    protected Date lastVisitDate;
    protected BigDecimal totalDept;
    
    
    protected Customer id(int id){this.id = id; return this;}
    protected Customer name(String name){this.name = name; return this;}
    protected Customer lastName(String lastName){this.lastName = lastName; return this;}
    protected Customer address(String address){this.address = address; return this;}
    protected Customer pbx(String pbx){this.pbx = pbx; return this;}

    
    
    

    protected void setCustomertoDatabase(
            String name,
            String lastName,
            String address,
            String pbx,
            String gsm,
            Date lastVisitDate,
            BigDecimal totalDept){
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.pbx = pbx;
        this.gsm = gsm;
        this.lastVisitDate = lastVisitDate;
        this.totalDept = totalDept;
    }
    
    protected void setCustomerFromDatabase(
            int id,
            String name,
            String lastName,
            String address,
            String pbx,
            String gsm,
            Date lastVisitDate,
            BigDecimal totalDept){
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.pbx = pbx;
        this.gsm = gsm;
        this.lastVisitDate = lastVisitDate;
        this.totalDept = totalDept;
    }    
 


}
