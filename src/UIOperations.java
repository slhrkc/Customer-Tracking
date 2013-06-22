
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author salih
 */
public class UIOperations {
    
    
    
    
    /**
     * Fills the given JTable with the data from the resultset.
     * Table and the resultset must be compatible.
     * Column[0] from ResultSet will be assigned to Column[0] of the Table.
     * @param table to be filled with data
     * @param rs the data which will be pushed to the table
     * @return the table with updated data
     */
    public static JTable fillTable(JTable table, ResultSet rs) throws SQLException{
        
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            model.getDataVector().removeAllElements();
            table.addNotify();
            
            int columnCount = table.getColumnCount();

             
            
            while(rs.next()){
                Object[] rowData = new Object[4];
                rowData[0] = rs.getObject(1);
                rowData[1] = rs.getObject(2);
                rowData[2] = rs.getObject(3);
                rowData[3] = rs.getObject(4);
                model.addRow(rowData);

            }
            
            return table;

    }
    
}
