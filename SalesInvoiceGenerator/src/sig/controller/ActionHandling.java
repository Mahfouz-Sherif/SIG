/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sig.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
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
import sig.model.InvoiceLineTableModel;
import sig.view.InvoiceFrame;
import sig.view.InvoiceHeaderMessage;
import sig.view.InvoiceLineMessage;
/**
 *
 * @author Mahfouz Sherif
 */
public class ActionHandling implements ActionListener {
    private InvoiceFrame frame;
    private InvoiceHeaderMessage HeaderMessage;
    private InvoiceLineMessage LineMessage;
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
            case"New Line":
                System.out.println("New Line");
                NewLine();
                break;
            case"Delete Line":
                        System.out.println("Delete Line");
                    DeleteLine();
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
                break;
                
            case"NewLineCancel":
                NewLineMessageCancel();
                break;
            case"NewLineOK":
                NewLineMessageOK();
                
        }
        
    }

    private void CreateNewInvoice() {
        HeaderMessage= new InvoiceHeaderMessage(frame);
        HeaderMessage.setVisible(true);
         
    }
    
    private void NewInvoiceMessageCancel() {
       HeaderMessage.setVisible(false);
       HeaderMessage.dispose();
       HeaderMessage=null;
    }

    private void NewInvoiceMessageOK() {
       HeaderMessage.setVisible(false);
 
       String CustomerName = HeaderMessage.getCustomerNameField().getText();
       String InvoiceDate = HeaderMessage.getInvoiceDateField().getText();
       Date D = new Date();
       
        try {
            D = InvoiceFrame.DateFormat.parse(InvoiceDate);
        } catch (ParseException ex) {
           JOptionPane.showMessageDialog(frame, "Correct date format is (dd-mm-yyyy), resetting to today's date.","Invalid date format",JOptionPane.ERROR_MESSAGE);      
        }
        int InvNum =0;
        for(InvoiceHeader inv: frame.getInvoicesArray()){
             if(inv.getInvoiceNum()> InvNum )
                 InvNum=inv.getInvoiceNum();
        }  InvNum++;
        
        InvoiceHeader Inv= new InvoiceHeader(InvNum,CustomerName,D);
        frame.getInvoicesArray().add(Inv);
        frame.getHeaderTableModel().fireTableDataChanged();
       HeaderMessage.dispose();
       HeaderMessage=null;
    }

    
    private void DeleteInvoice() {
        int SelectedInvIndex= frame.getHeaderTable().getSelectedRow();
        if(SelectedInvIndex!=-1){
        frame.getInvoicesArray().remove(SelectedInvIndex);
        frame.getHeaderTableModel().fireTableDataChanged();
        frame.getLineTable().setModel(new InvoiceLineTableModel(null));
        
         frame.setLinesArray(null);
       frame.getCustomerNameLabel().setText("");
       frame.getInvNumLabel().setText("");
       frame.getInvTotalLabel().setText("");
       frame.getInvDateLabel().setText(null);
        }
    }


    private void NewLine() {
        LineMessage= new InvoiceLineMessage(frame);
        LineMessage.setVisible(true);
        
    }

    private void DeleteLine() {
        
        int SelectedInvoiceHeader= frame.getHeaderTable().getSelectedRow();             

        int SelectedLineIndex= frame.getLineTable().getSelectedRow();
        if(SelectedLineIndex != -1){   
        frame.getLinesArray().remove(SelectedLineIndex);
         InvoiceLineTableModel LineTableModel = (InvoiceLineTableModel) frame.getLineTable().getModel();
         LineTableModel.fireTableDataChanged();
         InvoiceHeader InvHeader= frame.getInvoicesArray().get(SelectedInvoiceHeader);                   //These statements show that the invoice total changed
         frame.getHeaderTableModel().fireTableDataChanged();                                             //without having to click on  the invoice header again
         frame.getInvTotalLabel().setText(""+ InvHeader.getInvoiceTotal());
        }
         frame.getHeaderTable().setRowSelectionInterval(SelectedInvoiceHeader,SelectedInvoiceHeader);    

                

        //System.exit(0);
    }

    private void LoadFile() {
       JFileChooser fc=new JFileChooser();
       try{
       int Result = fc.showOpenDialog(frame);
       if(Result==JFileChooser.APPROVE_OPTION){
       File HeaderFile=fc.getSelectedFile();
       Path HeaderPath= Paths.get(HeaderFile.getAbsolutePath())
               ;
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
       //InvoiceLines.add(line);
       }
       }
       InvoiceHeaderTableModel HeaderTableModel = new InvoiceHeaderTableModel(InvoiceHeaders);
       frame.setHeaderTableModel(HeaderTableModel);
       frame.getHeaderTable().setModel(HeaderTableModel);
       }
       }catch(IOException ex){
           JOptionPane.showMessageDialog(frame, "The file you used is either missing or invalid.", "Error", JOptionPane.ERROR_MESSAGE);
       } catch (ParseException ex) { 
           JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } 
    } 

    private void SaveFile() {
        ArrayList<InvoiceHeader> SaveInvoicesArray= frame.getInvoicesArray();
        JFileChooser fc= new JFileChooser();
        try {
        int res=fc.showSaveDialog(frame);
        if(res==JFileChooser.APPROVE_OPTION){
            File HeaderSaveFile=fc.getSelectedFile();    
            
                FileWriter HeaderFW = new FileWriter(HeaderSaveFile);
                String Headers="";
                String lines="";
                for(InvoiceHeader invoice : SaveInvoicesArray){
                     Headers+= invoice.toString();
                     Headers += '\n';
                     
                    for(InvoiceLine line : invoice.getLines() ){
                         lines+= line.toString();
                         lines+='\n';      
                    } 
              
                }
                res=fc.showSaveDialog(frame);
                File LineSaveFile =fc.getSelectedFile();
                FileWriter LineFW =new FileWriter(LineSaveFile);
                HeaderFW.write(Headers);
                LineFW.write(lines);
                HeaderFW.close();
                LineFW.close();
        }    
            } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame,"The file you used is either invalid or missing" ,"Error", JOptionPane.ERROR_MESSAGE);
            }
            
            
        }    

    private void NewLineMessageCancel() {
       LineMessage.setVisible(false);
       LineMessage.dispose();
       LineMessage=null;
    }

    private void NewLineMessageOK() {
       LineMessage.setVisible(false);
       
       String name= LineMessage.getItemNameField().getText();
       String CountText=LineMessage.getItemCountField().getText();
       String PriceText=LineMessage.getItemPriceField().getText();
       
       int count=1;     //initializtion
       double price=1;
       
       try{
            count=Integer.parseInt(CountText);
          } catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(frame, "Inconvertible Number","Invalid number format",JOptionPane.ERROR_MESSAGE);
          }
       try{
            price=Double.parseDouble(PriceText);
          } catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(frame, "Inconvertible Price","Invalid price format",JOptionPane.ERROR_MESSAGE);
          }
       
       if(price<0) price*=-1;   //negative number fix
       if(count<0) count*=-1;
       
       
       int SelectedInvoiceHeader= frame.getHeaderTable().getSelectedRow();
       if(SelectedInvoiceHeader!=-1){
          InvoiceHeader InvHeader= frame.getInvoicesArray().get(SelectedInvoiceHeader);
           InvoiceLine InvLine=new InvoiceLine(InvHeader,count,name,price);
           //InvHeader.getLines().add(InvLine); this statement will also give the same result          
           frame.getLinesArray().add(InvLine); 
           InvoiceLineTableModel LineTableModel = (InvoiceLineTableModel) frame.getLineTable().getModel();
           LineTableModel.fireTableDataChanged();
           frame.getHeaderTableModel().fireTableDataChanged();
           frame.getInvTotalLabel().setText(""+InvHeader.getInvoiceTotal());
        }
       frame.getHeaderTable().setRowSelectionInterval(SelectedInvoiceHeader,SelectedInvoiceHeader);    
       LineMessage.dispose();
       LineMessage=null;
    }
    

    
    
}
