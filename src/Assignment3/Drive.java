package Assignment3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import Assignment3.Q2.Combo;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class Drive extends Application{
	
	public static void main(String[] pArgs)
	{
		launch("");
	}
	public void start(Stage pPrimaryStage) throws Exception 
	{	
		
		pPrimaryStage.setScene(BloodDonationOption.healthInputScene(pPrimaryStage));
		
		pPrimaryStage.setTitle("Blood Donation Option");
		pPrimaryStage.setHeight(500);
		pPrimaryStage.setWidth(500);
		pPrimaryStage.show();
		
		
	}
	
	
	
}
