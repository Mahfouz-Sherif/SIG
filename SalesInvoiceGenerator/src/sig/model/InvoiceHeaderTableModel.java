/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sig.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import sig.view.InvoiceFrame;

/**
 *
 * @author Mahfouz Sherif
 */
public class InvoiceHeaderTableModel extends AbstractTableModel {
    
    private ArrayList<InvoiceHeader> InvoicesArray;
    private String[] columns={"No.","Date","Customer","Total"};

    public InvoiceHeaderTableModel(ArrayList<InvoiceHeader> InvoicesArray) {
        this.InvoicesArray = InvoicesArray;
    }
    

    @Override
    public int getRowCount() {
        return InvoicesArray.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         InvoiceHeader inv= InvoicesArray.get(rowIndex);
         switch (columnIndex){
             case 0: return inv.getInvoiceNum();
             case 1: return InvoiceFrame.DateFormat.format(inv.getInvoiceDate());
             case 2: return inv.getCustomerName();
             case 3: return inv.getInvoiceTotal();
         }
        return"";         

    }
    
    @Override
    public String getColumnName(int column){
       return columns[column];
    }
    
}
