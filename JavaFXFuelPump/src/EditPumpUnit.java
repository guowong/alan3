//package javafx_fuelpumpproject;

import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author dmcelroy
 * 
 *  1) read the current prices from the store
 *  2) setup controls on the pane, display the current prices in the TextFields
 *  3) if the [Accept] button is clicked, update the store
 *  4) update the log file
 */
public class EditPumpUnit extends JavaFX_FuelPumpProject {
    private double new87octanePrice;
    private double new91octanePrice;
    private double newDieselPrice;
    
    EditPumpUnit() {  // constructor
        // 1) read the current prices from the store
        new87octanePrice = store.getPrice87octane();
        new91octanePrice = store.getPrice91octane();
        newDieselPrice = store.getPriceDiesel();
        
        // 2) setup controls on the pane
        
        BorderPane editPumpUnitPane = new BorderPane();
        VBox vbox = new VBox();

        final ToggleGroup group = new ToggleGroup();

        RadioButton rb1 = new RadioButton("Pump Unit Gallons");
        rb1.setToggleGroup(group);
        rb1.setSelected(true);

        RadioButton rb2 = new RadioButton("Pump Unit Liters");
        rb2.setToggleGroup(group);        
        
        rb1.setSelected(true);
        rb1.requestFocus();
        
        // message at the bottom
        Label msg = new Label("Pump Units are updated at the pump\nwith the next transaction\n\n\n");
        vbox.getChildren().addAll(rb1, rb2, msg);
        
        editPumpUnitPane.setCenter(vbox);
        DialogBox editPumpUnitDialog = new DialogBox(editPumpUnitPane, "Edit Pump Unit", "Accept", "Cancel", 250, 240);
        
        //  if the [Accept] button is clicked, update the Pump Unit in Pumps
        if (editPumpUnitDialog.getClickedButton().equals("Accept")) {
            try {
            	
            	RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
            	String toogleGroupValue = selectedRadioButton.getText();
            	
                // if User selected:  "Pump Unit Liters"
            	// Get all 3 prices from Store
            	// Calculate all 3 prices for Liter Pump Unit
            	// Set it back to Store object for all 3 prices
            	// Re-load the Pump.png
            	
            	if (toogleGroupValue == "Pump Unit Liters") {
            		new87octanePrice = Double.valueOf(store.getPrice87octane());
            		// calculate the price of liter unit
            		new87octanePrice += 10;
            		store.setPrice87octane(new87octanePrice);
            		
                    new91octanePrice = Double.valueOf(store.getPrice91octane());
            		// calculate the price of liter unit
                    new91octanePrice += 20;
                    
            		store.setPrice91octane(new91octanePrice);
                    
                    newDieselPrice = Double.valueOf(store.getPriceDiesel());
                    // calculate the price of liter unit
                    newDieselPrice += 30;
                    store.setPriceDiesel(newDieselPrice);
                    
            	}
            	
            	System.out.print ("\nAlan Wong's Coin counting program: "); 

            } 
            catch (NumberFormatException e) {
                editPricesError();
            }  
            catch (IllegalArgumentException e) {
                editPricesError(); 
            }
        }
    }
    
    private void editPricesError() {
            BorderPane errorPane = new BorderPane();
            errorPane.setCenter(new Label("Fuel values must be\npositive numeric values"));
            new DialogBox(errorPane, "Error", "Ok", 240, 240);    
    }
} // end of class EditPrices