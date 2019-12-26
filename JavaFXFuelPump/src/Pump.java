//package javafx_fuelpumpproject;

import java.io.File;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

/**
 *
 * @author dmcelroy
 */
public class Pump extends JavaFX_FuelPumpProject {
    // these class level variables are created for each individual pump
    private int pumpNumber;
    private double deposit = 0.00;
    private double sale = 0.00;                 // default initial values
    private double change = 0.00;
    private double pricePerGallon = 2.499;      // $2.49 9/10
    private double previousSale = 0.00;
    private String fuelGrade;
    
    // These controls are placed in a grid for each pump below the store
    private TextField txtDeposit;       // for the customer's deposit before pumping
    private Label  lblChange;           // used to display the change after pumping done
    private Button btnClear;            // the [Clear] button is located below the store
    
    // These controls are placed on the pump after it is created
    private Label  lblSale;             // displays current dollars being delivered
    private Label  lblGallons;          // displays current gallons being delivered
    private Label  lblPricePerGallon;   //
    private Button btn87;               // button to select 87 octane
    private Button btn91;               // button to select 91 octane
    private Button btnDiesel;           // button to select diesel
    private Button btnStartStop;        // Start/Stop button on the fuel pump
    
    // These variables are used by the timers when pumping fuel
    private boolean timerActive = false;
    private Timeline saleTimeline;
    private Timeline volumeTimeline;
    private DoubleProperty saleTimeSeconds = new SimpleDoubleProperty(0);
    private DoubleProperty volumeTimeSeconds = new SimpleDoubleProperty(0);
    private final double SECONDS_PER_GALLON = 4;    // fuel delivery speed
    
    // This is a static variable. There is only one copy of depositGrid
    //   that is shared by all pumps
    private static GridPane depositGrid;
    private static double totalSales = 0.00;
    public  static int pumpCount = 0;
    public  static final int PUMP_WIDTH = 300;    
    
    Pump (Pane pump) {  // constructor
        try {
            // check for the maximum number of pumps
            if (pumpCount >= MAX_PUMPS) {
                System.out.println ("Unable to create another pump " + MAX_PUMPS);
                return;
            }
            // add the fuel pump image from the Pump.png file
            // The user's name is in the environment variable USER on MacOS and Linux
            //   but it is in the environment variable USERNAME on Windows
            String loginID = System.getenv("USER");
            if (loginID == null) loginID = System.getenv("USERNAME");
            //Alan added
            //fuelPumpFolder = "/Users/gwong/Sources22/alan3/JavaFXFuelPump/src/";         
            String fuelPumpFilename = "//Users/gwong/Sources22/alan3/JavaFXFuelPump/src/Pump.png";  
            File fuelPumpFile = new File(fuelPumpFilename);          
            Image picFuelPump = new Image(fuelPumpFile.toURI().toString());
            ImageView ivFuelPump = new ImageView(picFuelPump);
            ivFuelPump.toBack();
            pump.getChildren().add(ivFuelPump);          
            putControlsOnPump(pump);
            addDepositAndChangeControls(storePane);
        }
        catch (Exception e) {
            System.out.println ("Can't find images");
        }     
    }
        
    public int getPumpNumber() { return pumpNumber; }
    public double getPreviousSale() { return previousSale; }
    public double getTotalSales() { return totalSales; }
    public static int getPumpCount() { return pumpCount; }
    
    private void clearPump(KeyCode keycode) {
        if (keycode == KeyCode.ENTER) {                    
            previousSale = sale;             // save the Current sale amount
            sale = 0.0;        
            pricePerGallon = 0.0;       // updated when customer selects fuel type
            btnStartStop.setText("Deposit $\nSelect fuel");   // button indeterminate state     

            // enable the fuel selection buttons
            btn87.setDisable(false);
            btn91.setDisable(false);
            btnDiesel.setDisable(false);
            txtDeposit.setDisable(false);
            deposit = 0.0;
            txtDeposit.setEditable(true);   // OK to update the deposit amount
            txtDeposit.clear();
            change = 0.00;
            lblChange.setText("");
            txtDeposit.requestFocus();
            updatePump();
        }
    }
    
    private void setPricePerGallon(double price) {
        if (lblChange.getText().equals("")) {
            pricePerGallon = price;
            updatePump();
            if (! txtDeposit.getText().equals(""))
                btnStartStop.setText("Start");
        }
    }
    
    private void updatePump() {
        // release lblSale and lblGallons from the timer and update their values
        timerActive = false;
        if (lblSale.textProperty().isBound()) 
            lblSale.textProperty().unbind();       
        lblSale.setText(String.format("%8.2f", sale)); 
        
        if (lblGallons.textProperty().isBound())
            lblGallons.textProperty().unbind();      
        
        if (pricePerGallon == 0) {
            lblPricePerGallon.setText("");  // clear the price
            lblPricePerGallon.setTextAlignment(TextAlignment.RIGHT);
        }
        else {
            // strip off the 9/10 cents for the display
            double priceX = Math.floor(pricePerGallon*100)/100;
            lblPricePerGallon.setText(String.format("%6.2f", priceX));
        }
    }

    /** process the Start/Stop button click  
     * 
     * The 'start/stop' button on the pump has four states
     *   [Deposit] - waiting for deposit $$$ and fuel selection
     *   [Start] - starts the pump when clicked
     *   [Stop]  - stops the pump when it is pumping or reached the deposit amount
     *   [Clear] - clears the pump, deposit amount and change
     */ 
    private void StartOrStopStopPump() {  
        /////// START THE PUMP //////
        if (btnStartStop.getText().equals("Start")
                && !timerActive && lblChange.getText().equals("")) {
            txtDeposit.setEditable(false);  // can't change deposit while pumping
            btnStartStop.setText("Stop");
            btn87.setDisable(true);     // don't let price or amount change
            btn91.setDisable(true);
            btnDiesel.setDisable(true);
            try { deposit = Double.parseDouble(txtDeposit.getText()); }
            catch (NumberFormatException e) { deposit = 0; }
            txtDeposit.setText(String.format("%.2f", deposit));
            txtDeposit.setDisable(true);  
          
            timerActive = true;

            if (saleTimeline != null) {
                saleTimeline.stop();
            }
            if (volumeTimeline != null) {
                volumeTimeline.stop();
            }
            // bind the Sale and Gallons labels to the timers so that the
            //   timers can update the values within the labels
            lblSale.textProperty().bind(saleTimeSeconds.asString("%8.2f"));  
            lblGallons.textProperty().bind(volumeTimeSeconds.asString("%5.1f"));

            // compute gallons to deliver and time to deliver
            double pricePerGal;
            double galToDeliver = 0.0;
            try { pricePerGal = Double.parseDouble(lblPricePerGallon.getText()); }
            catch (NumberFormatException e) { pricePerGal = 0; }
            if (pricePerGal != 0.0) 
                galToDeliver = deposit / pricePerGal;
            int pumpTime = (int)(galToDeliver * SECONDS_PER_GALLON);

            if (galToDeliver > 0) {
                saleTimeSeconds.set(0);                 // start at 0
                saleTimeline = new Timeline();
                saleTimeline.getKeyFrames().add(
                    new KeyFrame(Duration.seconds(pumpTime),
                    new KeyValue(saleTimeSeconds,deposit)));
                saleTimeline.playFromStart();

                volumeTimeSeconds.set(0);
                volumeTimeline = new Timeline();
                volumeTimeline.getKeyFrames().add(
                    new KeyFrame(Duration.seconds(pumpTime),
                    new KeyValue(volumeTimeSeconds,galToDeliver)));
                volumeTimeline.setOnFinished(e -> saleComplete() );
                volumeTimeline.playFromStart();
            }
        }         
        else if (btnStartStop.getText().equals("Stop")) {
            saleComplete();
        }
    }
    
    private void stopPump() {
        // stop the timers that are used to show delivery of fuel
        if (saleTimeline != null)   saleTimeline.stop();
        if (volumeTimeline != null) volumeTimeline.stop();
        timerActive = false;
        
        btn87.setDisable(false);     // don't let price or amount change
        btn91.setDisable(false);
        btnDiesel.setDisable(false);
        txtDeposit.setDisable(true);
    } // end of stopPump() method
    
    private void saleComplete() {
        btnStartStop.setText("   Sale\nComplete");
        lblSale.textProperty().unbind();        // release from timer control
        lblGallons.textProperty().unbind();     // release from timer control
        sale = Double.parseDouble(lblSale.getText());
        change = deposit - sale;
        lblChange.setText(String.format("%7.2f",change));
        previousSale = sale;     // keep track of the last sale made
        totalSales += sale;                     
        stopPump();

        LogFile logFile = new LogFile();        
        logFile.write("Pump-" + pumpNumber 
                + " deposit " + String.format("%.2f", deposit)
                + " sale " + String.format("%.2f", sale)
                + " change " + String.format("%.2f", change)
                + " " + fuelGrade 
                + " " + lblGallons.getText() + " gallons " 
                + " price " + String.format("%.3f", pricePerGallon)
        );
    }

    /*
     *  addDepositAndChangeControls(Pane storePane)
     *
     * Usea GridPanel to hold the customer information for each pump
     *   Label      Pump #
     *   TextField  Deposit
     *   Label      Change
     *
     * THe TextField, Label and Buttons for each pump are placed
     *   visually below the store even though logically they belong to pumps
     */
    private void addDepositAndChangeControls(Pane storePane) {
        if (storePane != null)  { // make sure the left pane has been created
            if (depositGrid == null) {
                depositGrid = new GridPane();
                depositGrid.setLayoutX(0);
                depositGrid.setLayoutY(210);
                depositGrid.setPadding(new Insets(10));
                depositGrid.setHgap(2);
                depositGrid.setVgap(2);
                storePane.getChildren().add(depositGrid);
            }
            // compute base grid positions
            int col = (pumpNumber-1)%2*2;
            int row = (pumpNumber-1)/2*5;
            Font font14Ariel  = Font.font("Ariel", FontWeight.NORMAL, 14); 
            
            // add labels for "Deposit" and "Change" to the left of pumps 1, 3, 5, etc.
            if (pumpNumber%2==1) {
                Label lblBlank = new Label("");
                depositGrid.add(lblBlank, 0, 5);
                Label lblDepositID = new Label("Deposit");
                depositGrid.add(lblDepositID, 0, row+1);
                Label lblChangeID = new Label("Change");
                depositGrid.add(lblChangeID, 0, row+2);
            }
            // Identify the pump number
            Label lblPumpID = new Label("Pump " + pumpNumber);
            lblPumpID.setFont(font14Ariel);
            lblPumpID.setMaxSize(60, 27);
            lblPumpID.setMinSize(60, 27);            
            depositGrid.add(lblPumpID, col+1, row+0);
            
            // provide a TextField for the customer to enter a deposit
            txtDeposit = new TextField(); // to input the deposit
            txtDeposit.setMaxSize(56, 27);
            txtDeposit.setMinSize(56, 27);    
            txtDeposit.setFont(font14Ariel);
            txtDeposit.setOnAction(e -> { 
                if (! lblPricePerGallon.getText().equals(""))
                    btnStartStop.setText("Start");
                if (! txtDeposit.getText().equals("")) {
                    try { deposit = Double.parseDouble(txtDeposit.getText()); }
                    catch (NumberFormatException exp) { deposit = 0; }
                    txtDeposit.setText(String.format("%.2f", deposit));
                    txtDeposit.setDisable(true);  
                }                    
            }); // end of setOnAction()
            depositGrid.add(txtDeposit, col+1, row+1);
            
            // provide a Label to display the computed change after delivery
            lblChange = new Label();
            lblChange.setStyle("-fx-background-color: #FFFFFF");  // LightCoral bkg
            lblChange.setMaxSize(54, 27);
            lblChange.setMinSize(54, 27);
            lblChange.setFont(font14Ariel);
            depositGrid.add(lblChange, col+1, row+2);
   
            // The [Clear] button clears the pump, deposit and change
            btnClear = new Button("Clear");
            btnClear.setMaxSize(56, 27);
            btnClear.setMinSize(56, 27);
            btnClear.setFont(font14Ariel);
            // clear() if the button is clicked by the mouse. Fake an ENTER key
            btnClear.setOnAction(e -> clearPump(KeyCode.ENTER) );
            // clear() if the button has focus and the ENTER key is clicked        
            btnClear.setOnKeyPressed (e -> clearPump(((KeyEvent)e).getCode()));
            depositGrid.add(btnClear, col+1, row+3);
            
            // space between rows for Deposit, Change and Clear button
            Label lblSpacer = new Label(" ");
            depositGrid.add(lblSpacer, col+1, row+4);  
                    }     
    } // end of addDepositAndChangeControls() method

    // Place Labels on top of the pump image for $$$, gallons, price/gal
    void putControlsOnPump(Pane pump) {
        Font font14Ariel  = Font.font("Ariel", FontWeight.NORMAL, 14); 
        Font font16Ariel  = Font.font("Ariel", FontWeight.NORMAL, 16); 
       
        // display the pump # at the top of the pump
        pumpNumber = pumpCount+1;               // pumps started at 0
        Label lblPumpNumber = new Label("Pump #" + pumpNumber);
        lblPumpNumber.setLayoutX(120);          // relative to the pump
        lblPumpNumber.setLayoutY(10);           
        Font bold16Font = Font.font("Ariel", FontWeight.BOLD, 16); 
        lblPumpNumber.setFont(bold16Font);      // font size
        lblPumpNumber.setTextFill(Color.WHITE); // white letters     
        
        // lblSale = on the pump for the amount of the sale
        lblSale = new Label(String.format("%8.2f", 0.0));
        lblSale.setStyle("-fx-background-color: #DDDDDD");  // Light Gray bkg
        lblSale.setLayoutX(111);
        lblSale.setLayoutY(85);
        lblSale.setMaxSize(73, 23);
        lblSale.setMinSize(73, 23);
        lblSale.setFont(font16Ariel);   
 
        // lblGallons = on the pump for the gallons delivered
        lblGallons = new Label(String.format("%5.1f", 0.0));
        lblGallons.setStyle("-fx-background-color: #DDDDDD");  // Light Gray bkg
        lblGallons.setLayoutX(123);
        lblGallons.setLayoutY(126);
        lblGallons.setMaxSize(51, 23);
        lblGallons.setMinSize(51, 23);
        lblGallons.setFont(font16Ariel);  
 
        // lblPricePerGallon = on the pump for price per gallon
        lblPricePerGallon = new Label();
        lblPricePerGallon.setStyle("-fx-background-color: #DDDDDD");  // Light Gray bkg
        lblPricePerGallon.setLayoutX(113);
        lblPricePerGallon.setLayoutY(167);
        lblPricePerGallon.setMaxSize(50, 23);   // leave room for the 9/10
        lblPricePerGallon.setMinSize(50, 23);
        lblPricePerGallon.setFont(font16Ariel);  
        
        // 87 octane selection
        btn87 = new Button("87");
        btn87.setLayoutX(66);
        btn87.setLayoutY(208);
        btn87.setStyle(" -fx-base: rgb(255,255,0);");  // YELLOW
        btn87.setMaxSize(41, 31);           // make buttons same size
        btn87.setMinSize(41, 31);
        btn87.setFont(font14Ariel);
        // set the price to 87 octane if the [87] button is clicked by the mouse
        btn87.setOnAction(e -> { 
            setPricePerGallon(store.getPrice87octane()); 
            fuelGrade = "87-octane";
        });
        // set the price to 87 octane if the [87] button is clicked with the [Enter] key
        btn87.setOnKeyPressed(e -> { 
            if(((KeyEvent)e).getCode()==KeyCode.ENTER)
                setPricePerGallon(store.getPrice87octane());
            fuelGrade = "87-octane";
        });
        
        // 91 octane selection
        btn91 = new Button("91");
        btn91.setLayoutX(118);
        btn91.setLayoutY(208);
        btn91.setStyle(" -fx-base: rgb(255,255,0);");  // YELLOW
        btn91.setMaxSize(41, 31);           // make buttons same size
        btn91.setMinSize(41, 31);
        btn91.setFont(font14Ariel);
        // set the price to 91 octane if the [91] button is clicked by the mouse
        btn91.setOnAction(e -> { 
            setPricePerGallon(store.getPrice91octane()); 
            fuelGrade = "91-octane";
        });
        // set the price to 91 octane if the [91] button is clicked with the [Enter] key
        btn91.setOnKeyPressed(e -> { 
            if(((KeyEvent)e).getCode()==KeyCode.ENTER)
                setPricePerGallon(store.getPrice91octane());
            fuelGrade = "91-octane";
        });
        
        // diesel selection
        btnDiesel = new Button("Diesel");
        btnDiesel.setLayoutX(170);
        btnDiesel.setLayoutY(208);
        btnDiesel.setStyle(" -fx-base: rgb(0,127,0);  -fx-text-fill: rgb(255,255,255);");  // YELLOW
        btnDiesel.setMaxSize(64, 31);           // make buttons same size
        btnDiesel.setMinSize(64, 31);
        btnDiesel.setFont(font14Ariel);
        // set the price to Diesel if the [Diesel] button is clicked by the mouse
        btnDiesel.setOnAction(e -> { 
            setPricePerGallon(store.getPriceDiesel()); 
            fuelGrade = "Diesel   ";
        });
        // set the price to Diesel if the [Diesel] button is clicked with the [Enter] key
        btnDiesel.setOnKeyPressed(e -> { 
            if(((KeyEvent)e).getCode()==KeyCode.ENTER)
                setPricePerGallon(store.getPriceDiesel());
            fuelGrade = "Diesel";
        }); 
        
         // Start / Stop button
        btnStartStop = new Button("Deposit $\nSelect fuel");
        btnStartStop.setLayoutX(94);
        btnStartStop.setLayoutY(257);
        btnStartStop.setMaxSize(108, 46);           // make buttons same size
        btnStartStop.setMinSize(108, 46);
        btnStartStop.setFont(font14Ariel);
        // Start/Stop when the big button on the pump is clicked with the mouse
        btnStartStop.setOnAction(e -> StartOrStopStopPump() );
        // Start/Stop when the big button on the pump is clicked with the [Enter] key
        btnStartStop.setOnKeyPressed(e -> { 
            if(((KeyEvent)e).getCode()==KeyCode.ENTER)
                StartOrStopStopPump();
        }); 
        
        pump.getChildren().addAll(lblPumpNumber,lblSale,lblGallons,lblPricePerGallon,
                                  btn87,btn91,btnDiesel,btnStartStop);      
    } // end of putControlsOnPump(Pane pump)
        
} // end of class JavaFX_FuelPumpProject