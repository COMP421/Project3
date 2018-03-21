package Assignment3;


import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import JavaFX.AddTable.Combo;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
//import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
//import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class Q2 extends Application
{
	private static ObservableList<Combo> data = FXCollections.observableArrayList();
	private static TableView<Combo> table = new TableView<Combo>();
	private final String pattern = "yyyy-MM-dd";

	/**
	 * 
	 * @param pArgs 
	 */
	public static void main(String[] pArgs)
	{
		launch("");
	}
	/**
	 * 
	 */
	@Override
	public void start(Stage pPrimaryStage) throws Exception 
	{
		GridPane root =  new GridPane();

		Button button1 = new Button("InnerButton");
		
		

		root.add(button1,0,0);
		
		button1.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				System.out.println("InnerButton clicked.");
			}
			
		});
		//Creating a GridPane container
		GridPane donorRegisGrid = new GridPane();
		donorRegisGrid.setPadding(new Insets(10, 10, 10, 10));
		donorRegisGrid.setVgap(5);
		donorRegisGrid.setHgap(5);
		//Defining the Name text field
		
		
		
		//Defining the Last Name text field
		final TextField healthID = new TextField();
		healthID.setPromptText("healthID.");
		GridPane.setConstraints(healthID, 0, 0);
		donorRegisGrid.getChildren().add(healthID);
		
		final TextField dname = new TextField();
		dname.setPromptText("Enter your full name.");
		GridPane.setConstraints(dname, 0, 1);
		donorRegisGrid.getChildren().add(dname);
		
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
		GridPane.setConstraints(date, 0, 2);
		donorRegisGrid.getChildren().add(date);
		
		final TextField address = new TextField();
		address.setPromptText("Address.");
		GridPane.setConstraints(address, 0, 3);
		donorRegisGrid.getChildren().add(address);
		
		final ObservableList<String> genderType = FXCollections.observableArrayList("Male", "Female", "Other");
		final ComboBox gender = new ComboBox(genderType);
		gender.setPromptText("Gender");
		GridPane.setConstraints(gender, 0, 4);
		donorRegisGrid.getChildren().add(gender);
		
		final ObservableList<String> bloodtype = FXCollections.observableArrayList("A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-");
		final ComboBox btype = new ComboBox(bloodtype);
		btype.setPromptText("Blood type");
		GridPane.setConstraints(btype, 0, 5);
		donorRegisGrid.getChildren().add(btype);
		
		//Defining the Comment text field
		final TextField phone = new TextField();
		phone.setPrefColumnCount(15);
		phone.setPromptText("phone number");
		GridPane.setConstraints(phone, 0, 6);
		donorRegisGrid.getChildren().add(phone);
		
		final TextField weight = new TextField();
		weight.setPromptText("weight");
		GridPane.setConstraints(weight, 0, 7);
		donorRegisGrid.getChildren().add(weight);
		
		final TextField height = new TextField();
		height.setPromptText("height");
		GridPane.setConstraints(height, 0, 8);
		donorRegisGrid.getChildren().add(height);
		
		
		//Defining the Submit button
		Button submit = new Button("Submit");
		GridPane.setConstraints(submit, 1, 9);
		donorRegisGrid.getChildren().add(submit);
		
		
		
		submit.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				String aString  = "insert into donor values('" + healthID.getText() +"'," + "'" + dname.getText()+"',"+ "'" +date.getValue().toString() +"',"+ "'" + address.getText() + "',"+ "'" +gender.getValue().toString() + "',"+ "'" +btype.getValue().toString()  + "','"+ phone.getText() +"',"+ "'" +weight.getText() + "',"+ "'" +height.getText() + "'" + ");";
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
				try {
					con = DriverManager.getConnection(url, userName, password);
					Statement stmt= con.createStatement();
					stmt.executeUpdate(aString);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		});
		Button switchS = new Button("Donor Registration");
		root.add(switchS,0,0);
		
		switchS.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				pPrimaryStage.hide();
				pPrimaryStage.setScene(new Scene(donorRegisGrid));
				pPrimaryStage.show();
				
			}
		});
		
		Button quit = new Button("Quit");
		donorRegisGrid.add(quit,10,10);
		quit.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				Platform.exit();
			}
		});
		
		Button bloodManagement = new Button("Expired Blood Management");
		GridPane bloodMgt = new GridPane();
		root.add(bloodManagement, 1, 0);
		final TextField bbankid = new TextField();
		bbankid.setPromptText("Date");
		bloodMgt.getChildren().add(bbankid);
		Button submitInMGT = new Button("Submit");
		submitInMGT.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
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
					ResultSet rs= stmt.executeQuery("select bloodtype, component, sum(quantity) as qt from blood group by bloodtype, component;");
					while(rs.next()){
						data.add(new Combo(rs.getString("bloodtype"), rs.getString("component"), rs.getDouble("qt")));
					}
					
					pPrimaryStage.hide();
					pPrimaryStage.setScene(sceneCreator());
					pPrimaryStage.show();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		});
		bloodMgt.add(submitInMGT, 1, 0);
		
		bloodManagement.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				pPrimaryStage.hide();
				pPrimaryStage.setScene(new Scene(bloodMgt));
				pPrimaryStage.show();
				
				
				
			}
		});
		
		pPrimaryStage.setScene(new Scene(root));
		pPrimaryStage.setTitle("Blood Donation Database");
		pPrimaryStage.setHeight(500);
		pPrimaryStage.setWidth(500);
		pPrimaryStage.show();
		
		
	}
	
	
	
	
	public static Scene sceneCreator(){
		Scene scene = new Scene(new Group());
 
        final Label label = new Label("Blood Book");
        label.setFont(new Font("Arial", 20));
 
        table.setEditable(true);
 
        TableColumn firstNameCol = new TableColumn("Blood type");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<Combo, String>("firstName"));
 
        TableColumn lastNameCol = new TableColumn("Blood Component");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<Combo, String>("lastName"));
 
        TableColumn emailCol = new TableColumn("Quantity");
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(
                new PropertyValueFactory<Combo, String>("email"));
 
        table.setItems(data);
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
 
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);
 
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
		return scene;
	}
	public static class Combo {
		 
        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleDoubleProperty email;
 
        private Combo(String BloodType, String BloodComponent, double quantity) {
            this.firstName = new SimpleStringProperty(BloodType);
            this.lastName = new SimpleStringProperty(BloodComponent);
            this.email = new SimpleDoubleProperty(quantity);
        }
 
        public String getFirstName() {
            return firstName.get();
        }
 
        public void setFirstName(String fName) {
            firstName.set(fName);
        }
 
        public String getLastName() {
            return lastName.get();
        }
 
        public void setLastName(String fName) {
            lastName.set(fName);
        }
 
        public double getEmail() {
            return email.get();
        }
 
        public void setEmail(double fName) {
            email.set(fName);
        }
    }
}