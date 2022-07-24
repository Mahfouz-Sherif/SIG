
package sig.model;

import java.util.ArrayList;
import java.util.Date;

public class InvoiceHeader {
    private int invoiceNum;
    private String customerName;
    private Date invoiceDate;
    private ArrayList<InvoiceLine> lines;

    public InvoiceHeader(int invoiceNum, String customerName, Date invoiceDate) {
        this.invoiceNum = invoiceNum;
        this.customerName = customerName;
        this.invoiceDate = invoiceDate;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public int getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(int invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public ArrayList<InvoiceLine> getLines() {
        if(lines==null){
        lines=new ArrayList<>();
        }
        return lines;
    }

    public void setLines(ArrayList<InvoiceLine> lines) {
        this.lines = lines;
    }
    
    public double getInvoiceTotal(){
        double total=0.0;
        for(int i=0;i<lines.size();i++){
        total+=lines.get(i).getLineTotal();
        }
        return total;
    }

    @Override
    public String toString() {
        return "InvoiceHeader{" + "invoiceNum=" + invoiceNum + ", customerName=" + customerName + ", invoiceDate=" + invoiceDate + '}';
    }
    
    }
    

