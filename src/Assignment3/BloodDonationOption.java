package Assignment3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import Assignment3.Q2.Combo;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class BloodDonationOption {
	
	
	private final static String pattern = "yyyy-MM-dd";
	
	public static Scene healthInputScene(Stage pPrimaryStage, Scene prevScene){
		GridPane grid = new GridPane();
		
		final TextField id = new TextField();
		id.setPrefColumnCount(15);
		id.setPromptText("HealthInsuranceNumber");
		Label hin3L = new Label("Please enter the donor's health insurance number: ");
		GridPane.setConstraints(hin3L, 0, 0);
		grid.getChildren().add(hin3L);
		GridPane.setConstraints(id, 0, 1);
		grid.getChildren().add(id);
		
		final TextField address = new TextField();
		address.setPromptText("stationAddress");
		Label dsaddrL = new Label("Please enter the donation station's address: ");
		GridPane.setConstraints(dsaddrL, 0, 2);
		grid.getChildren().add(dsaddrL);
		GridPane.setConstraints(address, 0, 3);
		grid.getChildren().add(address);
		
		Button submit = new Button("submit");
		GridPane.setConstraints(submit, 1, 4);
		grid.getChildren().add(submit);
		
		submit.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				String HealthId = id.getText();
				String dsAddress = address.getText();
				String queryforDonor = "select count(*) from donor where healthinsurancenum = '" + HealthId + "' ;" ;
				String queryforStation = "select count(*) from donationstation where dsaddress ='" + dsAddress + "'; " ;
				try {
					Class.forName("org.postgresql.Driver");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String url = "jdbc:postgresql://comp421.cs.mcgill.ca:5432/cs421";
				String userName = "cs421g05";
				String password = "ASDFqwerty1234";
				Connection con;
				try {
					con = DriverManager.getConnection(url, userName, password);
					Statement stmt = con.createStatement();
					ResultSet rs= stmt.executeQuery(queryforDonor);
					int existforDonor = 0;
					while(rs.next()){
						existforDonor = rs.getInt(1);
					}
					rs.close();
					ResultSet rs2= stmt.executeQuery(queryforStation);
					int existforStation = 0;
					while(rs2.next()){
						existforStation = rs2.getInt(1);
					}
					rs2.close();
					if(existforDonor == 0){
						pPrimaryStage.hide();
						pPrimaryStage.setScene(Q2.errorSceneCreator("Donor health insurance number not found", pPrimaryStage, prevScene));
						pPrimaryStage.show();
					}
					else if(existforStation == 0){
						pPrimaryStage.hide();
						pPrimaryStage.setScene(Q2.errorSceneCreator("Donation address not found", pPrimaryStage, prevScene));
						pPrimaryStage.show();
					}
					else{
						pPrimaryStage.hide();
						pPrimaryStage.setScene(bloodDonationOptionScene(pPrimaryStage, HealthId, dsAddress , prevScene));
						pPrimaryStage.show();
						
					}
					stmt.close();
					con.close();
					
				} catch (SQLException e) {
					pPrimaryStage.hide();
					pPrimaryStage.setScene(Q2.errorSceneCreator(e.getMessage(), pPrimaryStage, prevScene));
					pPrimaryStage.show();
				}	
				
			}
			
		});
		
		Button rootButton = new Button("Back To Main Page");

		rootButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				pPrimaryStage.hide();
				pPrimaryStage.setScene(prevScene);
				pPrimaryStage.show();
				
			}
		});
		grid.add(rootButton, 1, 5);
		return new Scene(grid);
	}
	
	public static Scene bloodDonationOptionScene(Stage pPrimaryStage, String HealthID, String address, Scene prevScene){
		GridPane grid = new GridPane();
		
		final TextField did = new TextField();
		did.setPrefColumnCount(15);
		did.setPromptText("Did");
		Label didL = new Label("Please choose a donation ID number: ");
		GridPane.setConstraints(didL, 0, 0);
		grid.getChildren().add(didL);
		GridPane.setConstraints(did, 0, 1);
		grid.getChildren().add(did);
		
		
		StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = 
                DateTimeFormatter.ofPattern(pattern);
            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }
            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        }; 
		final DatePicker date = new DatePicker();
		date.setConverter(converter);
		date.setPromptText("YYYY-MM-DD");
		Label dL = new Label("Please enter the date of donation: ");
		GridPane.setConstraints(dL, 0, 2);
		grid.getChildren().add(dL);
		GridPane.setConstraints(date, 0, 3);
		grid.getChildren().add(date);
		
		final TextField qty = new TextField();
		qty.setPromptText("qty");
		Label qL = new Label("Please enter the quantity donated: ");
		GridPane.setConstraints(qL, 0, 4);
		grid.getChildren().add(qL);
		GridPane.setConstraints(qty, 0, 5);
		grid.getChildren().add(qty);
		
		
		
		//Defining the Submit button
		Button submit = new Button("submit");
		GridPane.setConstraints(submit, 1, 8);
		grid.getChildren().add(submit);
		
		submit.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				String aString  = "insert into donation values('" + did.getText() +"'," + "'" + date.getValue().toString()+"',"+ "'" +qty.getText() +"',"+ "'" + address + "',"+ "'" +HealthID + "'" + ");";
				System.out.println(aString);
				try {
					Class.forName("org.postgresql.Driver");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String url = "jdbc:postgresql://comp421.cs.mcgill.ca:5432/cs421";
				String userName = "cs421g05";
				String password = "ASDFqwerty1234";
				Connection con;
				
				pPrimaryStage.hide();
				pPrimaryStage.setScene(Q2.errorSceneCreator("Blood donation stored in database", pPrimaryStage, prevScene));
				pPrimaryStage.show();
				try {
					con = DriverManager.getConnection(url, userName, password);
					Statement stmt= con.createStatement();
					stmt.executeUpdate(aString);
					stmt.close();
					con.close();
				} catch (SQLException e) {
					pPrimaryStage.hide();
					pPrimaryStage.setScene(Q2.errorSceneCreator(e.getMessage(), pPrimaryStage, prevScene));
					pPrimaryStage.show();
				}
			}
			
		
		});		
		Scene scene= new Scene(grid);
		return scene;
	}
}
