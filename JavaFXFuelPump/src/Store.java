//package javafx_fuelpumpproject;

import java.io.File;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author dmcelroy
 */
public class Store extends JavaFX_FuelPumpProject {
    // default prices
    double price87octane = 3.499;     // the last 9 is for
    double price91octane = 3.699;     //    the 9/10 of a cent
    double priceDiesel = 4.199;
    // use Labels to display the prices on the store's sign
    Label lblPriceRegular = new Label();
    Label lblPriceSuper = new Label();
    Label lblPriceDiesel = new Label();   

    public Store (Pane storePane) {
        try {
            addStoreAndSign (storePane);
            addPriceLabels (storePane);
            findPricesInLogFile();
            updateSignPrices();
        }
        catch (Exception e) {
            System.out.println ("Can't find store image");
        } 
    }

    // getters and setters for 87 octane
    double getPrice87octane() {
        return price87octane;
    }
    double setPrice87octane(double price) {
        price87octane = price;
        updateSignPrices();
        return price87octane;
    }

    // getters and setters for 91 octane
    double getPrice91octane() {
        return price91octane;
    }
    double setPrice91octane(double price) {
        price91octane = price;
        updateSignPrices();
        return price91octane;
    }
    
    // getters and setters for Diesel
    double getPriceDiesel() {
        return priceDiesel;
    }
    double setPriceDiesel(double price) {
        priceDiesel = price;
        updateSignPrices();
        return priceDiesel;
    }
    
    // add image of the store and sign
    void addStoreAndSign (Pane storePane) {
        // identify the folder where the images and log file are located
        // The user's name is in the environment variable USER on MacOS and Linux
        //   but it is in the environment variable USERNAME on Windows
        String ID;
        ID = System.getenv("USER");
        if (ID == null) ID = System.getenv("USERNAME");
        loginID = ID;
        //Alan added
        fuelPumpFolder = "/Users/gwong/Sources22/alan3/JavaFXFuelPump/src/";               
        String storeFilename = fuelPumpFolder + "Store.png";          
        File fuelStore = new File(storeFilename);
        Image picStore = new Image(fuelStore.toURI().toString());
        ImageView ivStore = new ImageView(picStore);
        ivStore.setLayoutX(12);
        ivStore.setLayoutY(0);
        storePane.getChildren().add(ivStore);        
    }
    
    // add labels for the fuel prices
    void addPriceLabels (Pane storePane) {
        Font font14ArielBold = Font.font("Ariel", FontWeight.BOLD, 14); 

        // lblPriceRegular = price for regular gas on the store sign
        lblPriceRegular.setStyle("-fx-background-color: #FFFFFF;  -fx-text-alignment: center;");  // WHITE bkg
        lblPriceRegular.setLayoutX(150);
        lblPriceRegular.setLayoutY(23);
        lblPriceRegular.setMaxSize(40, 17);
        lblPriceRegular.setMinSize(40, 17);
        lblPriceRegular.setFont(font14ArielBold);        
        storePane.getChildren().add(lblPriceRegular);
        
        // lblPriceSuper = price for premium gas on the store sign
        lblPriceSuper.setStyle("-fx-background-color: #FFFFFF");  // WHITE bkg
        lblPriceSuper.setLayoutX(150);
        lblPriceSuper.setLayoutY(40);
        lblPriceSuper.setMaxSize(40, 17);
        lblPriceSuper.setMinSize(40, 17);
        lblPriceSuper.setFont(font14ArielBold);        
        storePane.getChildren().add(lblPriceSuper);
       
        // lblPriceDiesel = price for diesel on the store sign
        lblPriceDiesel.setStyle("-fx-background-color: #FFFFFF");  // WHITE bkg
        lblPriceDiesel.setLayoutX(150);
        lblPriceDiesel.setLayoutY(57);
        lblPriceDiesel.setMaxSize(40, 17);
        lblPriceDiesel.setMinSize(40, 17);
        lblPriceDiesel.setFont(font14ArielBold);        
        storePane.getChildren().add(lblPriceDiesel);       
    }
    
    // find price updates in the log file
    private void findPricesInLogFile() {
        // See if updated values for the fuel prices are in the log file
        LogFile logFile = new LogFile();        
        String priceUpdates = logFile.search("Price-update:");
        if (priceUpdates != null){
            String[] splitString = priceUpdates.split(" ", 20);
            // update the 87-octane price
            int i;
            for (i=0; i<splitString.length; i++)
                if (splitString[i].equals("87-octane")) break;
            if (i < splitString.length) // then "87-octane" was found
                setPrice87octane(Double.valueOf(splitString[i+1]));
            // update the 91-octane price
            for (i=0; i<splitString.length; i++)
                if (splitString[i].equals("91-octane")) break;
            if (i < splitString.length) // then "87-octane" was found
                setPrice91octane(Double.valueOf(splitString[i+1]));
            // update the Diesel price
            for (i=0; i<splitString.length; i++)
                if (splitString[i].equals("Diesel")) break;
            if (i < splitString.length) // then "87-octane" was found
                setPriceDiesel(Double.valueOf(splitString[i+1]));
        }        
    }
    
    // Update prices on the store's sign
    void updateSignPrices() {
    
        // strip off the 9/10 of a cent for the price displays
        lblPriceRegular.setText(String.format("%4.2f",(Math.floor(price87octane*100)/100)));
        lblPriceSuper.setText(String.format("%4.2f",(Math.floor(price91octane*100)/100)));
        lblPriceDiesel.setText(String.format("%4.2f",(Math.floor(priceDiesel*100)/100)));
    }
    
} // end of class Store()