//package javafx_fuelpumpproject;

import static java.lang.Double.max;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
//alan import static JavaFX_FuelPumpProject.MAX_PUMPS;

/**
 *
 * @author Dan
 */
public class Menu extends JavaFX_FuelPumpProject {
    
    Menu () {} // default constructor
    
    MenuBar addMenuBar (Stage stage, GridPane storeAndPumps) {        
        
        MenuBar menuBar = new MenuBar();
        
        //----------------------
        //----- File Menu ------
        //----------------------
        javafx.scene.control.Menu fileMenu = new javafx.scene.control.Menu("File");
        // File/Exit
        MenuItem exitMenu = new MenuItem("Exit");
        exitMenu.setOnAction(e -> System.exit(0));
        exitMenu.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.ALT_DOWN));        
        fileMenu.getItems().add(exitMenu);
        
        //------------------------
        // ------ View Menu ------
        //------------------------
        javafx.scene.control.Menu viewMenu = new javafx.scene.control.Menu("View");
        
        // View - View Sales Data
        MenuItem viewSalesMenu = new MenuItem("View Sales...");
            viewSalesMenu.setOnAction(e -> {
            Label salesDisplay = new Label("The previous sale for each pump\n");
            for (int i=0; i<Pump.pumpCount; i++)
                salesDisplay.setText(salesDisplay.getText() + 
                            String.format("Pump %d  $%.2f\n",
                   pump[i].getPumpNumber(), pump[i].getPreviousSale()));
            salesDisplay.setText(salesDisplay.getText() +
                    String.format("\nSales this session  $%.2f\n\n",
                        pump[0].getTotalSales()));
            BorderPane viewSales = new BorderPane();
            viewSales.setCenter(salesDisplay);
            DialogBox viewSalesDialog = new DialogBox(viewSales, "Fuel sales", "Ok", 260, 240);
            });          
        viewSalesMenu.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.ALT_DOWN));    
        
         // View - View/Print receipt
        Print viewPrint = new Print();  // instantiate the print class
        MenuItem viewPrintReceiptMenu = new MenuItem("View/Print receipt...");
        viewPrintReceiptMenu.setOnAction(e -> viewPrint.receipt());
        viewPrintReceiptMenu.setAccelerator(new KeyCodeCombination(KeyCode.P, KeyCombination.ALT_DOWN));
               
        // View - View/Print Log File
        MenuItem viewPrintLogFileMenu = new MenuItem("View/Print Log File...");
        viewPrintLogFileMenu.setOnAction(e -> {
            LogFile logFile = new LogFile();        
            logFile.displayLogFile();
        });            
        viewPrintLogFileMenu.setAccelerator(new KeyCodeCombination(KeyCode.V, KeyCombination.ALT_DOWN));        
        // add the menu items to the VIEW MENU
        viewMenu.getItems().addAll(viewSalesMenu, viewPrintReceiptMenu, viewPrintLogFileMenu);
               
        //------------------------
        // ------ Edit Menu ------
        //------------------------
        javafx.scene.control.Menu editMenu = new javafx.scene.control.Menu("Edit");
        // Edit / Edit Prices
        MenuItem editPricesMenu = new MenuItem("Edit prices...");
        editPricesMenu.setOnAction(e -> {new EditPrices();} );            
        editPricesMenu.setAccelerator(new KeyCodeCombination(KeyCode.E, KeyCombination.ALT_DOWN));        
        editMenu.getItems().add(editPricesMenu);
        
        //Alan added
        // Edit Pump Unit
        MenuItem editPumpUnitMenu = new MenuItem("Edit Pump Unit...");
        editPumpUnitMenu.setOnAction(e -> {new EditPumpUnit();} );
        editPumpUnitMenu.setAccelerator(new KeyCodeCombination(KeyCode.E, KeyCombination.ALT_DOWN));   
        editMenu.getItems().add(editPumpUnitMenu);
        
        // Edit / Add a Pump
        MenuItem addPumpMenu = new MenuItem("Add a pump");
        addPumpMenu.setAccelerator(new KeyCodeCombination(KeyCode.U, KeyCombination.ALT_DOWN));        
        addPumpMenu.setOnAction(e -> { 
            if (Pump.pumpCount >= MAX_PUMPS) {
                new DialogBox("Maximum number of pumps = " + MAX_PUMPS);
            }
            else {
                double newWidth = stage.getWidth() + Pump.PUMP_WIDTH;
                stage.setWidth (newWidth);
                stage.setX(max(5, stage.getX()-Pump.PUMP_WIDTH/2));
                storeAndPumps.add(createFuelPump(), Pump.pumpCount+1, 0); 
                if (Pump.pumpCount>=3 && Pump.pumpCount%2==1) { // more room for the Deposit, change and button
                    stage.setHeight(stage.getHeight() + 27*5);
                    stage.setY(max(5, stage.getY()-50));
                }
            }
        });       
        editMenu.getItems().add(addPumpMenu);       

        //------------------------
        // ------ Help Menu ------        
        //------------------------
        javafx.scene.control.Menu helpMenu = new javafx.scene.control.Menu("Help"); 
        MenuItem aboutMenu = new MenuItem("About...");
        aboutMenu.setOnAction(e -> new About());
        aboutMenu.setAccelerator(new KeyCodeCombination(KeyCode.H, KeyCombination.ALT_DOWN));        
        helpMenu.getItems().add(aboutMenu);
           
        // the last thing to do is place the menus on the menuBar
        menuBar.getMenus().addAll(fileMenu, viewMenu, editMenu, helpMenu);
        
        return menuBar;
    }
        
}