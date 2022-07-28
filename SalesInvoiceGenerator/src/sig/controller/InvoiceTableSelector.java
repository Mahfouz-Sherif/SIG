/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sig.controller;

import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import sig.model.InvoiceHeader;
import sig.model.InvoiceLine;
import sig.model.InvoiceLineTableModel;
import sig.view.InvoiceFrame;

/**
 *
 * @author Mahfouz Sherif
 */
public class InvoiceTableSelector implements ListSelectionListener {
    
    private InvoiceFrame frame;

    public InvoiceTableSelector(InvoiceFrame frame) {
        this.frame = frame;
    }
    
    
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        int SelectedInvoiceIndex = frame.getHeaderTable().getSelectedRow();
       // System.out.println("Invoice Selected: " + SelectedInvoiceIndex );
        if(SelectedInvoiceIndex!=-1){
       InvoiceHeader SelectedInv= frame.getInvoicesArray().get(SelectedInvoiceIndex);
       ArrayList<InvoiceLine> lines = SelectedInv.getLines();
       InvoiceLineTableModel LineTableModel = new InvoiceLineTableModel(lines);
       frame.setLinesArray(lines);
       frame.getLineTable().setModel(LineTableModel);
       frame.getCustomerNameLabel().setText(SelectedInv.getCustomerName());
       frame.getInvNumLabel().setText(""+SelectedInv.getInvoiceNum());
       frame.getInvTotalLabel().setText(""+SelectedInv.getInvoiceTotal());
       frame.getInvDateLabel().setText(InvoiceFrame.DateFormat.format(SelectedInv.getInvoiceDate()));
        System.out.println(SelectedInv + " " + SelectedInv.getLines());
    }
        
    }
    
}
