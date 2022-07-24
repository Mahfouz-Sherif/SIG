/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sig.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Mahfouz Sherif
 */
public class InvoiceLineTableModel extends AbstractTableModel {

    private ArrayList<InvoiceLine> LinesArray;
    private String[] columns={"No.","Item Name","Item Price","Count","Total"};   

    public InvoiceLineTableModel(ArrayList<InvoiceLine> LinesArray) {
        this.LinesArray = LinesArray;
    }
          
    @Override
    public int getRowCount() {
        return LinesArray.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceLine line= LinesArray.get(rowIndex);
        switch(columnIndex){
            case 0: return line.getItemName();
            case 1: return line.getItemPrice();
            case 2: return line.getCount();
            case 3: return line.getLineTotal();
            default: return "";
            
        }
    }
     
    @Override
    public String getColumnName(int column){
           return columns[column];
    }
    
}
