
package sig.model;


public class InvoiceLine {
    private InvoiceHeader invoice;
    private int count;
    private String itemName;
    private double itemPrice;

    public InvoiceLine(InvoiceHeader invoice, int count, String itemName, double itemPrice) {
        this.invoice = invoice;
        this.count = count;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
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

    @Override
    public String toString() {
        return "InvoiceLine{" + "invoice=" + invoice + ", count=" + count + ", itemName=" + itemName + ", itemPrice=" + itemPrice + '}';
    }

    
    
    
    
}
