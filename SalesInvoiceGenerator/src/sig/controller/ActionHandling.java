/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sig.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import sig.model.InvoiceHeader;
import sig.model.InvoiceHeaderTableModel;
import sig.model.InvoiceLine;
import sig.view.InvoiceFrame;
/**
 *
 * @author Mahfouz Sherif
 */
public class ActionHandling implements ActionListener {
    private InvoiceFrame frame;
    
    public ActionHandling(InvoiceFrame frame){
           this.frame=frame;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Action Handling called!!");
        switch(e.getActionCommand()){
            case"Create New Invoice":
                System.out.println("Create New Invoice");
                CreateNewInvoice();
                break;
            case"Delete Invoice":
                System.out.println("Delete Invoice");
                DeleteInvoice();
                break;
            case"Save":
                System.out.println("Save");
                Save();
                break;
            case"Cancel":
                    System.out.println("Cancel");
                    
                    Cancel();
                break;
            case"Load File":
                System.out.println("Load File");  
                LoadFile();
                break;
            case"Save File":   
                System.out.println("Save File");
                SaveFile();
                break;
                
            case"NewInvoiceOK":
                NewInvoiceMessageOK();
                break;
            case"NewInvoiceCancel": 
                NewInvoiceMessageCancel();
        }
        
    }

    private void CreateNewInvoice() {
    }

    private void DeleteInvoice() {
    }


    private void Save() {
    }

    private void Cancel() {
        System.exit(0);
    }

    private void LoadFile() {
       JFileChooser fc=new JFileChooser();
       try{
       int Result = fc.showOpenDialog(frame);
       if(Result==JFileChooser.APPROVE_OPTION){
       File HeaderFile=fc.getSelectedFile();
       Path HeaderPath= Paths.get(HeaderFile.getAbsolutePath());
       List<String>HeaderLines=Files.readAllLines(HeaderPath);
       ArrayList<InvoiceHeader> InvoiceHeaders=new ArrayList<>();
       for(String HeaderLine: HeaderLines){
           String [] arr = HeaderLine.split(",");
           String str1=arr[0];
           String str2=arr[1];
           String str3=arr[2];
           int code=Integer.parseInt(str1);
           Date InvoiceDate = InvoiceFrame.DateFormat.parse(str2);
           InvoiceHeader header=new InvoiceHeader(code,str3,InvoiceDate);
           InvoiceHeaders.add(header);
       }
       frame.setInvoicesArray(InvoiceHeaders);
       Result = fc.showOpenDialog(frame);
       if(Result==JFileChooser.APPROVE_OPTION){
       File LineFile=fc.getSelectedFile();
       Path LinePath =Paths.get(LineFile.getAbsolutePath());
       List<String> LineLines=Files.readAllLines(LinePath);
       ArrayList<InvoiceLine> InvoiceLines=new ArrayList<>();
       for(String LineLine : LineLines) {
       String[] arr=LineLine.split(",");
       String str1=arr[0];     // invoice number (int)
       String str2=arr[1];     // item name (String)
       String str3=arr[2];     // item price (double)
       String str4=arr[3];     // count  (int)      
       int InvoiceCode = Integer.parseInt(str1);
       double price = Double.parseDouble(str3);
       int count = Integer.parseInt(str4);
       InvoiceHeader inv= frame.getInvoiceObject(InvoiceCode);
       InvoiceLine line=new InvoiceLine(inv,count,str2,price);
       inv.getLines().add(line);
       }
       }
       InvoiceHeaderTableModel HeaderTableModel = new InvoiceHeaderTableModel(InvoiceHeaders);
       frame.setHeaderTableModel(HeaderTableModel);
       frame.getHeaderTable().setModel(HeaderTableModel);
       }
       }catch(IOException ex){
           JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
       } catch (ParseException ex) { 
           JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } 
    } 

    private void SaveFile() {
    }

    private void NewInvoiceMessageCancel() {

    }

    private void NewInvoiceMessageOK() {

    }
    
}
