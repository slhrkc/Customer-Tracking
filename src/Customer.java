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
    private String firstName;
    private String lastName;
    private String address;
    private String pbx;
    private String gsm;
    private Date lastVisitDate;
    private BigDecimal totalDept;
    
    

    protected void setCustomer(
            int id,
            String firstName,
            String lastName,
            String address,
            String pbx,
            String gsm,
            Date lastVisitDate,
            BigDecimal totalDept){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.pbx = pbx;
        this.gsm = gsm;
        this.lastVisitDate = lastVisitDate;
        this.totalDept = totalDept;
    }


}
