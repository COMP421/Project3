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
		GridPane.setConstraints(id, 0, 0);
		grid.getChildren().add(id);
		
		Button submit = new Button("submit");
		GridPane.setConstraints(submit, 0, 1);
		grid.getChildren().add(submit);
		
		Button quit = new Button("Quit");
		grid.add(quit,10,10);
		quit.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				Platform.exit();
			}
		});
		
		submit.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				String HealthId = id.getText();
				String query = "select count(*) from donor where healthinsurancenum = '" + HealthId + "' ;" ;
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
					ResultSet rs= stmt.executeQuery(query);
					
					int exist = 0;
					
					while(rs.next()){
						exist = rs.getInt(1);
					}
					
					if(exist == 1){
						pPrimaryStage.hide();
						pPrimaryStage.setScene(bloodDonationOptionScene(pPrimaryStage, HealthId));
						pPrimaryStage.show();
					}
					else{
						pPrimaryStage.hide();
						pPrimaryStage.setScene(Q2.errorSceneCreator("Not found"));
						pPrimaryStage.show();
					}
					
				} catch (SQLException e) {
					pPrimaryStage.hide();
					pPrimaryStage.setScene(Q2.errorSceneCreator(e.getMessage()));
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
		grid.add(rootButton, 8, 8);
		return new Scene(grid);
	}
	
	public static Scene bloodDonationOptionScene(Stage pPrimaryStage, String HealthID){
		GridPane grid = new GridPane();
		
		final TextField did = new TextField();
		did.setPrefColumnCount(15);
		did.setPromptText("Did");
		GridPane.setConstraints(did, 0, 0);
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
		date.setPromptText("Date");
		GridPane.setConstraints(date, 0, 1);
		grid.getChildren().add(date);
		
		final TextField qty = new TextField();
		qty.setPromptText("qty");
		GridPane.setConstraints(qty, 0, 2);
		grid.getChildren().add(qty);
		
		final TextField address = new TextField();
		address.setPromptText("stationAddress");
		GridPane.setConstraints(address, 0, 3);
		grid.getChildren().add(address);
		
		//Defining the Submit button
		Button submit = new Button("stationAddress");
		GridPane.setConstraints(submit, 0, 4);
		grid.getChildren().add(submit);
		
		submit.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				String aString  = "insert into donation values('" + did.getText() +"'," + "'" + date.getValue().toString()+"',"+ "'" +qty.getText() +"',"+ "'" + address.getText() + "',"+ "'" +HealthID + "'" + ");";
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
				pPrimaryStage.setScene(Q2.errorSceneCreator("Complete"));
				pPrimaryStage.show();
				try {
					con = DriverManager.getConnection(url, userName, password);
					Statement stmt= con.createStatement();
					stmt.executeUpdate(aString);
				} catch (SQLException e) {
					pPrimaryStage.hide();
					pPrimaryStage.setScene(Q2.errorSceneCreator(e.getMessage()));
					pPrimaryStage.show();
				}
			}
		
		});
		
		
		
		
		
		
		
		Scene scene= new Scene(grid);
		return scene;
	}
}
