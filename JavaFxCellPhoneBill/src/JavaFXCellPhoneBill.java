//alan import java.awt.Label;
import javafx.scene.control.Label;

import java.text.NumberFormat;
import java.text.ParsePosition;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class JavaFXCellPhoneBill extends Application {
	Label lblTitle;
	Label lblPlansAndPrices;
	Label lblCustomerData;
	Label lblName;
	TextField txtName;
	TextField txtPlan;
	Label lblPlan;
	TextField txtGBused;
	Label lblGBused;
	Label lblCustomerBill;
	Label lblCustomerName;
	Label lblPleasePay;
	Button btnCompute;
	Button btnClear;
	Button btnExit;
	
	final double PRICE_PER_GB = 15.00;
	
	@Override
	public void start(Stage primaryStage) {
		BorderPane root = new BorderPane();
		root.setTop(createTop());
		root.setLeft(createLeft());
		root.setCenter(createCenter());
		root.setRight(createRight());
		root.setBottom(createBottom());
		
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("JavaFX Cell Phone Bill");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private HBox createTop() {
		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER);
		Font font36B = Font.font("Ariel", FontWeight.BOLD, 36);
		lblTitle = new Label("Cell Phone Billing");
		lblTitle.setFont(font36B);
		hbox.getChildren().add(lblTitle);
		return hbox;
	}
	
	private VBox createLeft() {
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(0,20,0, 20));
		lblPlansAndPrices = new Label(
					  "PLANS & PRICES\n"
					+ "--------------------\n"
					+ "A =  0 GB $50.00\n"
					+ "B =  2 GB $60.00\n"
					+ "C =  4 GB $70.00\n"
					+ "D =  10 GB $90.00\n"
					+ "+$15.00/GB over plan limit"
					);
		vbox.getChildren().add(lblPlansAndPrices);
		return vbox;
	}
	
	private VBox createCenter() {
		VBox vbox = new VBox();
		vbox.setSpacing(10.0);
		
		lblCustomerData = new Label("Customer Data");
				
		VBox vbox1 = new VBox();
		lblName = new Label(" Name");
		txtName = new TextField();
		txtName.setPrefSize(200,20);
		txtName.setMaxWidth(200);
	    vbox1.getChildren().addAll(lblName, txtName);
		
	    txtPlan = new TextField();
	    txtPlan.setMaxWidth(30);
	    lblPlan = new Label("   Plan (A-D): ");
	    HBox hbox2 = new HBox(txtPlan, lblPlan);
	    
		txtGBused = new TextField();
		txtGBused.setMaxWidth(50);
	    lblGBused = new Label("   GB Used");
	    HBox hbox3 = new HBox(txtGBused, lblGBused);
	    
		vbox.getChildren().addAll(lblCustomerData, vbox1, hbox2, hbox3);
		return vbox;
	}
	
	private HBox createBottom() {
		HBox hbox = new HBox();
		hbox.setSpacing(20.0);
		hbox.setPrefHeight(50);
		hbox.setAlignment(Pos.CENTER);
		
		btnCompute = new Button("Compute");
		btnCompute.setPrefSize(110,20);
		btnCompute.setOnAction((e -> compute()));
		
		btnClear = new Button("Clear");
		btnClear.setPrefSize(110, 20);
		btnClear.setOnAction((e -> clear()));	
		
		btnExit = new Button("Exit");
		btnExit.setPrefSize(110, 20);
		btnExit.setOnAction((e -> System.exit(0)));
		
		hbox.getChildren().addAll(btnCompute, btnClear, btnExit);
		return hbox;
	}
	
	private VBox createRight() {
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(0,20,0, 20));
		vbox.setSpacing(20.0);
		lblCustomerBill = new Label("Customer Bill \n");
		lblCustomerName = new Label("Name: \n");
		lblPleasePay = new Label("Please Pay: \n");
		
		vbox.getChildren().addAll(lblCustomerBill, lblCustomerName, lblPleasePay);
		return vbox;
	}
	
	private void clear() {	
		txtName.clear();
		txtPlan.clear();
		txtGBused.clear();	
	}
	
	private boolean isNumeric(String str) { 
	  try {  
	    Double.parseDouble(str);    
	    return true;
	  } catch(NumberFormatException e){  
	    return false;  
	  }  
	}
	
	private void compute() {
		char plan=' ';
		double GBused;
		double GBextra = 0;
		double baseRate = 0;
		double baseGB = 0;
		double bill;
		boolean validPlan = true;
		String displayPlanMsg;

		lblCustomerName.setText("Customer Name: " + txtName.getText());
		
        String typedInGBUsed = txtGBused.getText();
        if (isNumeric(typedInGBUsed))
        	lblGBused.setText("  GB used: " + txtGBused.getText());
        else {
        	lblGBused.setText("  Value must be numeric. ");	
        	return;
        }
		
		try {
			if (txtGBused.getText().trim().equals(""))
				txtGBused.setText("0.0");
			GBused = Double.parseDouble(txtGBused.getText());
			
	        System.out.println();
	        System.out.println(GBused);
	        
	        switch(txtPlan.getText().toUpperCase()) {
	        case "A":
	        	baseRate = 50.00;
	        	GBextra = GBused;
	        	displayPlanMsg = "  Plan (A-D): A "; 
	        	System.out.println(txtPlan.getText());
	        	break;
	        	
	        case "B":
	        	baseRate = 60.00;
	        	GBextra = GBused - 2;
	        	displayPlanMsg = "  Plan (A-D): B ";
	        	System.out.println(txtPlan.getText());
	        	break;
	        	
	        case "C":
	        	baseRate = 70.00;
	        	GBextra = GBused - 4;
	        	displayPlanMsg = "  Plan (A-D): C ";
	        	System.out.println(txtPlan.getText());
	        	break;
	        	
	        case "D":
	        	baseRate = 90.00;
	        	GBextra = GBused - 10;
	        	displayPlanMsg = "  Plan (A-D): D ";
	        	System.out.println(txtPlan.getText());
	        	break;
	        	
	        default:
	        	displayPlanMsg = "  ***Illegal Plan selected ";
	        	lblPlan.setText(displayPlanMsg);
	        	return;
	        }
	        
	        lblPlan.setText(displayPlanMsg);
			
	      
	        if (GBextra > 0) {
	        	bill = GBextra * PRICE_PER_GB + baseRate;
	        } else {
	        	bill = baseRate;
	        }
	        	
			lblPleasePay.setText("Please Pay: $" + String.format("%.2f", bill));
			
		} catch(Exception e ) {
			
		}
	
	}
}