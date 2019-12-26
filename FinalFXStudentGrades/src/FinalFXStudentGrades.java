

import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class FinalFXStudentGrades extends Application {
	private Label lblTitle; 
	private final String Title = "Java Final - Student Grades";
	private TextArea studentListTextA;
	private TextField studentNameText;
	private TextField score1Text;
	private TextField score2Text;
	private TextField score3Text;
	private TextField score4Text;
	private TextField score5Text;
	private Button enterStudentInfoButton;
	Label lblAverage;
	String filename = "";
	final HBox hb = new HBox();
	
	public static void main(String[] args) {
        launch(args); // Run this Application.
	}

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setTop(createTitle());
        root.setBottom(createButtons());
        root.setRight(createRightPosition());
        root.setLeft(createLeftPosition());
        root.setStyle("-fx-background-color: #F0E0C0;");
        root.setPadding(new Insets(5, 10, 0, 10)); // spacing between nodes
        BorderPane.setMargin(studentListTextA, new Insets(5, 10, 0, 5));

        Scene scene = new Scene(root);    
        primaryStage.setTitle(Title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private HBox createTitle() {  // BorderPane TOP position
        HBox hbox  = new HBox();
        hbox.setAlignment(Pos.CENTER);
        Font font36B = Font.font("Ariel", FontWeight.BOLD, 36); // title
        lblTitle = new Label(Title);
        lblTitle.setFont(font36B); 
        hbox.getChildren().add(lblTitle);
        return hbox;
    }
    
    private HBox createButtons() {  // Border BOTTOM
        HBox hbox = new HBox();
        hbox.setSpacing(20.0);  // spacing between the buttons
        hbox.setPrefHeight(50); // spacing around the top/bottom of buttons
        hbox.setAlignment(Pos.CENTER); // center the buttons on the row

        // Button to print the TextArea node
        Button displayListTextButton = new Button("Display List");
        displayListTextButton.setPrefSize(110, 20);
        displayListTextButton.setOnAction (e -> displayList());

        Button btnClear = new Button("Clear");
        btnClear.setPrefSize(110, 20);
        btnClear.setOnAction (e -> clear());

        Button btnExit = new Button("Exit");
        btnExit.setPrefSize(110, 20);
        btnExit.setOnAction (e -> System.exit(0));

        hbox.getChildren().addAll(displayListTextButton, btnClear, btnExit);
        return hbox;      
    }    
    
    private TextArea createRightPosition() {
    	studentListTextA = new TextArea();  // reference created at top of file
        Font fontCourierNew = Font.font("Courier New", FontWeight.BOLD, 14);
        studentListTextA.setFont(fontCourierNew);
        studentListTextA.setPrefWidth(300.0);
        studentListTextA.setWrapText(true);
        return studentListTextA;
    }    
    
    private VBox createLeftPosition() { // BorderPane LEFT position
        VBox vbox = new VBox();
        
        Label lblName = new Label("Name");
        studentNameText = new TextField();    // reference created at top of file
        Label lblScores = new Label("Scores");
        score1Text = new TextField();
        score1Text.setMaxWidth(40);
        score2Text = new TextField();
        score2Text.setMaxWidth(40);
        score3Text = new TextField();
        score3Text.setMaxWidth(40);
        score4Text = new TextField();
        score4Text.setMaxWidth(40);
        score5Text = new TextField();
        score5Text.setMaxWidth(40);
        
        enterStudentInfoButton = new Button("Enter Student Information");
        enterStudentInfoButton.setOnAction (e -> EnterStudentInfo());
        
        hb.getChildren().addAll(score1Text, score2Text, score3Text, score4Text, score5Text);
        hb.setSpacing(3);
        Label lblBlankLine = new Label(""); // blank line before list of items
        
        lblAverage = new Label("Enter 5 scores in (0 - 100) above");
        
        Label lblBlankLine2 = new Label(""); // blank line before list of items
        
        vbox.getChildren().addAll(lblName, studentNameText, lblScores, hb, enterStudentInfoButton, lblBlankLine, lblAverage, lblBlankLine2);
        return vbox;
    }    
        
    public boolean isNumeric(String str) { 
    	try {  
    		Integer.parseInt(str);  
    	    return true;
    	} catch(NumberFormatException e) {
    	    return false;  
    	}
    }    
    
    private boolean isValidEntry(String score) {
    	boolean retValue = isNumeric(score);
    	if (retValue) {
    		Integer nScore = Integer.parseInt(score);
    		if (nScore >= 0 && nScore <= 100)
    			return true;
    		else
    			return false;
    	}
    	else
    		return retValue;
    }
    
    private void EnterStudentInfo() {
        String studentName = studentNameText.getText();
        
        String score1 = score1Text.getText();
        if (!isValidEntry(score1)) {
        	score1Text.setText("");
        	lblAverage.setText("Invalid entry in score #1, try again.");
        	return;
        }
     
        String score2 = score2Text.getText();
        if (!isValidEntry(score2)) {
        	score2Text.setText("");
        	lblAverage.setText("Invalid entry in score #2, try again.");
        	return;
        }
        
        String score3 = score3Text.getText();
        if (!isValidEntry(score3)) {
        	score3Text.setText("");
        	lblAverage.setText("Invalid entry in score #3, try again.");
        	return;
        }       
        
        String score4 = score4Text.getText();
        if (!isValidEntry(score4)) {
        	score4Text.setText("");
        	lblAverage.setText("Invalid entry in score #4, try again.");
        	return;
        }
        
        String score5 = score5Text.getText();        
        if (!isValidEntry(score5)) {
        	score5Text.setText("");
        	lblAverage.setText("Invalid entry in score #5, try again.");
        	return;
        }           
                
        Integer nTotal = Integer.parseInt(score1) + Integer.parseInt(score2) + Integer.parseInt(score3) + Integer.parseInt(score4) + Integer.parseInt(score5);
        System.out.println(nTotal);  
        Integer nAverage = nTotal / 5;
        System.out.println(nAverage);  
        String grade = ""; 
        
        if (nAverage < 60)
        	grade = "F";
        else if (nAverage >= 60 && nAverage < 70)
        	grade = "D";
        else if (nAverage >= 70 && nAverage < 80)
        	grade = "C";
        else if (nAverage >= 80 && nAverage < 90)
        	grade = "B";
        else if (nAverage >= 90 && nAverage <= 100)
        	grade = "A";
       
        String displayAverageString = "The average is " + Integer.toString(nAverage) + ". Grade: " + grade;
        lblAverage.setText(displayAverageString);
        
        String newAddedStudent = "";
        newAddedStudent = studentName + ", " + "Scores=" +  score1 + ", " + score2 + ", " + score3 + ", " + score4 + ", " + score5 + ", " + "Average=" + nAverage + ", " + "Grade=" + grade;

        try {       
        	String homePath = System.getenv("HOMEPATH"); // System environment variable
        	if (homePath == null) // maybe it is a Mac or Linux system
            	homePath = System.getenv("HOME");
        	String myProgramName = this.getClass().getName();
        	filename = homePath + "/Documents/" + myProgramName + "/StudentGrades.txt";        
        
        	WriteFile data = new WriteFile(filename, true);
        	data.writeToFile(newAddedStudent);
        } catch (IOException e) {
     	   System.out.println("Error: IOExceptions...");
     	   System.out.println(e);
        }
    }    
    
    private void displayList() {
    	String homePath = System.getenv("HOMEPATH"); // System environment variable
    	if (homePath == null) // maybe it is a Mac or Linux system
        	homePath = System.getenv("HOME");
    	String myProgramName = this.getClass().getName();
    	filename = homePath + "/Documents/" + myProgramName + "/StudentGrades.txt";   
    	
        ReadTextFile studentRecordsFile = new ReadTextFile(filename);
        String[] itemList = studentRecordsFile.getFileContents();
        int lineCount = studentRecordsFile.getLineCount();
 
        String displaystudentList = "";
        for (int i = 0; i < lineCount; i++){
        	displaystudentList = displaystudentList + itemList[i] + "\n";
        }
    	studentListTextA.setText(displaystudentList);
    }  
    
    private void clear() {
    	studentListTextA.setText("");
    }      
}
