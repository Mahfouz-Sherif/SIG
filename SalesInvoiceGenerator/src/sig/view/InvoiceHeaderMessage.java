/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sig.view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
public class InvoiceHeaderMessage extends JDialog{
private JTextField CustomerNameField;
private JTextField InvDateField;
private JLabel CustomerNameLabel;
private JLabel InvDateLabel;
private JButton OKButton;
private JButton CancelButton;

public InvoiceHeaderMessage(InvoiceFrame frame){
    CustomerNameLabel= new JLabel("Customer Name: ");
    CustomerNameField=new JTextField(20);
    InvDateLabel=new JLabel("Invoice Date: ");
    InvDateField=new JTextField(20);
    OKButton= new JButton("OK");
    CancelButton= new JButton("Cancel");
    
   OKButton.setActionCommand("NewInvoiceOK");
    CancelButton.setActionCommand("NewInvoiceCancel");
    
    OKButton.addActionListener(frame.getHandle());
   CancelButton.addActionListener(frame.getHandle());
    setLayout(new GridLayout(3,2));
    
    add(InvDateLabel);
    add(InvDateField);
    add(CustomerNameLabel);
    add(CustomerNameField);
    add(OKButton);
    add(CancelButton);
    
    pack();
       

}

    public JTextField getCustomerNameField() {
     return CustomerNameField;
    }

    public JTextField getInvoiceDateField() {
     return InvDateField;
    }

}