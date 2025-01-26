# Sales Invoice Generator (SIG)  

## Overview  
The **Sales Invoice Generator (SIG)** is a Java-based desktop application designed to help businesses automate the process of generating, managing, and retaining sales invoices. This project integrates core Java principles with a user-friendly GUI and efficient file storage techniques to ensure accurate bookkeeping and simplified transaction management.  

## Features  
1. **Invoice Management**:  
   - View all invoices in a preview table displaying:  
     - Invoice Number  
     - Invoice Date  
     - Customer Name  
     - Total Amount  
   - View detailed information for each selected invoice, including all individual items.  

2. **Invoice Actions**:  
   - Create new invoices and add them to the records.  
   - Delete selected invoices with a confirmation dialog for safety.  
   - Edit invoice details (e.g., date, customer name, and items).  
   - Save edits to ensure all changes are updated in the records.  

3. **File Operations**:  
   - Load previously saved invoice data from two CSV files:  
     - **InvoiceHeader.csv**: Contains invoice-level data such as invoice number, date, and customer name.  
     - **InvoiceLines.csv**: Contains detailed item-level data, including item name, price, and quantity.  
   - Save the latest invoice data back into the CSV files.  

4. **Graphical User Interface (GUI)**:  
   - Developed using the **JFrame** class and complex components like **JTable**.  
   - Split-screen design with:  
     - A left panel showing invoice headers in a table.  
     - A right panel for displaying and editing the details of the selected invoice.  
   - Editable fields for invoice date and customer name, with uneditable fields for invoice number and total.  

5. **Error Handling**:  
   - Comprehensive exception handling for file operations:  
     - Descriptive messages for wrong file format, incorrect date format, or missing files.  
   - Ensures smooth user experience even in cases of errors.  

## Project Structure  
The project is organized into three main packages to ensure modularity and maintainability:  

### 1. **Model**  
   - Contains the core data structures for managing invoices and items:  
     - **InvoiceHeader**: Represents the main invoice with fields like invoice number, date, customer name, and a list of associated items.  
     - **InvoiceLine**: Represents individual items on an invoice with fields for item name, price, and quantity.  
   - **FileOperations**: A utility class for reading and writing invoice data to and from CSV files.  

### 2. **View**  
   - Implements the GUI using **JFrame** and **JTable** components.  
   - Features a split-screen layout with:  
     - A table for viewing invoice headers.  
     - A detailed form for displaying and editing invoice details.  

### 3. **Controller**  
   - Handles action events such as button clicks and table selections.  
   - Manages the application's interaction between the view and the model, ensuring smooth functionality.  

## Exception Handling  
The application ensures robustness through comprehensive exception handling:  

1. **Reading Exceptions**:  
   - Displays descriptive messages for:  
     - Incorrect file format.  
     - Invalid date format.  
     - Missing files.  

2. **Writing Exceptions**:  
   - Handles errors such as:  
     - Wrong file format.  
     - File or folder not found.

## Technologies Used  
- **Programming Language**: Java  
- **GUI Framework**: Swing  
- **Data Storage**: CSV files  
- **Development Tools**: IntelliJ IDEA
