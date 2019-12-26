//package javafx_fuelpumpproject;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.print.PrinterJob;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
//guo import static javafx_fuelpumpproject.LogFile.logFilename;

  

/**
 *
 * @author dmcelroy
 */
public class Print extends JavaFX_FuelPumpProject {
    DialogBox printDialog;
    String logFilename;
    
    Print() {
    	// 
    	//fuelPumpFolder = "/Users/" + loginID + "/Documents/FuelPump/";               
    	logFilename = "/Users/gwong/Sources22/alan3/JavaFXFuelPump/src/" + "LogFile.txt"; 
    	
    }  // constructor
    
    public void receipt() {
        BorderPane printReceiptPane = new BorderPane();
        VBox vbox = new VBox();

        // request pump number
        Label lblPump = new Label("Select pump");
        // build a canvas to hold the receipt with a rectangle around it
        Canvas receiptCanvas = new Canvas(210, 250);
        GraphicsContext gc = receiptCanvas.getGraphicsContext2D();

        // build a drop-down combo box with entries for each pump ("Pump-1", "Pump-2", etc)
        //   pump identifiers like "Pump-1" are stored in the log file that will be 
        //   searched to find the last customer for the selected pump
        final ComboBox<String> pumpComboBox = new ComboBox<>();
        for (int i=1; i<=Pump.getPumpCount(); i++)
            pumpComboBox.getItems().add(String.format("Pump-%d ", i));
        // if there is only one pump, set the comboBox selection to "Pump-1"
        if (Pump.getPumpCount() == 1) {
            pumpComboBox.setValue("Pump-1");
            printReceipt ("Pump-1", gc);
        }
        else {
            // add a listner that calls the printRecipt() method with the selected pump
            pumpComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                @Override public void changed(ObservableValue ov, String t, String t1) {
                printReceipt(t1, gc); // call the printReceipt routine with the selected pump
                }    
            });
        }
        
        vbox.getChildren().addAll(lblPump, pumpComboBox, new Label(""), receiptCanvas);
        printReceiptPane.setCenter(vbox);
        printDialog = new DialogBox(printReceiptPane, "Print Receipt", "Print", "Close", 250, 150);
        if (printDialog.getClickedButton().equals("Print"))
            printCanvas (receiptCanvas);    
    } // end of receipt() method
    
    private void printReceipt(String pumpID, GraphicsContext gc) {       
        double deposit=0, sale=0, change=0, gallons=0, price=0;
        String fuelType = "unknown"; 
        
        gc.setFill(Color.WHITE);
        gc.setStroke(Color.BLACK);
        gc.fillRect(0, 0, 210, 250);
        gc.setLineWidth(2);
        gc.setFill(Color.BLACK);
        gc.strokeRect(5, 5, 200, 240);
        Font fontCourierNew = Font.font("Courier New", FontWeight.NORMAL, 14);
        gc.setFont(fontCourierNew);
        
        // See if selected pump is in the log file 
        LogFile logFile = new LogFile();        
        String logFileRecord = logFile.search(pumpID);
        
        if (logFileRecord.equals("Not found")){
            BorderPane pumpNotFoundPane = new BorderPane();
            Label lblPumpNotFound = new Label("No receipt found ");
            pumpNotFoundPane.setLeft(lblPumpNotFound);
            new DialogBox(pumpNotFoundPane, "Searching for "+pumpID+" receipt", "Ok", 500, 270);
        }
        else {
            logFileRecord = logFileRecord.replaceAll("\\s+", " "); // remove duplicate spaces
            String[] splitString = logFileRecord.split(" ", 20);
            // update the 87-octane price
            int i;
            for (i=0; i<splitString.length; i++)
                if (splitString[i].equals("deposit")) break;
            if (i < splitString.length) // then "deposit" was found
            {
                deposit = Double.valueOf(splitString[i+1]);
                sale = Double.valueOf(splitString[i+3]);
                change = Double.valueOf(splitString[i+5]);
                fuelType = splitString[i+6];
                gallons = Double.valueOf(splitString[i+7]);
                price = Double.valueOf(splitString[i+10]);
            }
        
            String strReceipt = "Java Fuel Store\n";
            String date = splitString[1] + " " + splitString[2];
            strReceipt += date + "\n\n";

            strReceipt += String.format("%-8.8s\n", pumpID);
            strReceipt += String.format("%.1f gallons\n", gallons);
            strReceipt += String.format("of %s\n", fuelType);
            strReceipt += String.format("at %.3f per gal\n\n", price);

            strReceipt += String.format("%-8.8s %6.2f\n", "Deposit", deposit);
            strReceipt += String.format("%-8.8s %6.2f\n", "Sale", sale);
            strReceipt += String.format("%-8.8s %6.2f\n\n", "Change", change);
            strReceipt += "Thank you very much\n";
            strReceipt += "Come again soon.";
            
            gc.setLineWidth(1);
            gc.strokeText(strReceipt, 20, 30);
        }
    }
    
    
    /**
     * void printSelections()
     *  
     * The header is placed at the top of each page with the event title, date
     *  and page #. A new page is printed for each block of 40 items
     */
    public void printLogFile() {   
        String title = "Fuel Pump Log File";
        Label linesToPrint = new Label();
        Font fontCourierNew = Font.font("Courier New", FontWeight.NORMAL, 7);
        linesToPrint.setFont(fontCourierNew);
        final int  LINES_ON_PAGE = 60;
        int page = 1;       

        try {
            // open file for reading
            File diskLogFile = new File(logFilename);
            Scanner scannerLogFile = new Scanner(new File(logFilename) );
            if (!diskLogFile.exists()){
                new DialogBox("Unable to open " + logFilename);
            }
            else {      
                // print lines on a page with the title and date header at the top of the page
                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyy");
                Date date = new Date();
                int line = 0;  // index into listOfItems[]
                do {
                    // create the header at top of each page
                    linesToPrint.setText(                       // header a top of page
                            title + "   " +        // event title
                            dateFormat.format(date) +           // date
                            "   Page " +                        // "Page"
                            Integer.toString(page++) + "\n\n"); //   page #  
                    // create the printable list of items
                    do {
                        linesToPrint.setText(linesToPrint.getText() + scannerLogFile.nextLine() + "\n");
                        line++;                                 // next line
                    } while (line%LINES_ON_PAGE!=0 && scannerLogFile.hasNextLine() );
                    printLabel(linesToPrint);                        // send page to printer
                } while (scannerLogFile.hasNextLine()); // keep going until the entire TextArray is printed
            }
        }
        catch (IOException ioe) {
            new DialogBox("Unable to open " + logFilename);
            ioe.printStackTrace();
        }
    }
    
    /**
     * void print(Label text)
     * 
     * @param Label text - contains text of a full page to be printed
     * 
     * the printSelections() method places a full page into the text parameter
     */
    private void printLabel(Label text)  {            
        PrinterJob job = PrinterJob.createPrinterJob();
         
        if (job != null) { // then the PrinterJob was created successfully
            boolean printed = job.printPage(text);
            if (printed) {                
                job.endJob(); // End the printer job
            }
            else {
                System.out.println("Printing failed.");
            }
        }
        else {
            System.out.println("Could not create a printer job.");
        }
    } 
    
    /**
     * void print(Label text)
     * 
     * @param Label text - contains text of a full page to be printed
     * 
     * the printSelections() method places a full page into the text parameter
     */
    private void printCanvas(Canvas canvas)  {            
        PrinterJob job = PrinterJob.createPrinterJob();
         
        if (job != null) { // then the PrinterJob was created successfully
            boolean printed = job.printPage(canvas);
            if (printed) {                
                job.endJob(); // End the printer job
            }
            else {
                System.out.println("Printing failed.");
            }
        }
        else {
            System.out.println("Could not create a printer job.");
        }
    }      
}