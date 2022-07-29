
package sig.model;


public class InvoiceLine {
    private InvoiceHeader invoice;
    private int count, InvNum;
    private String itemName;
    private double itemPrice;

    public InvoiceLine(InvoiceHeader invoice, int count, String itemName, double itemPrice) {
        this.invoice = invoice;
        this.count = count;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.InvNum = invoice.getInvoiceNum();
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public InvoiceHeader getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceHeader invoice) {
        this.invoice = invoice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    
    public double getLineTotal(){
    return itemPrice * count;
    }

    public int getInvNum() {
        return InvNum;
    }
    
    

    @Override
    public String toString() {
        return "" + InvNum + "," + itemName + "," + itemPrice + "," + count + "";
    }

    
    
    
    
}
