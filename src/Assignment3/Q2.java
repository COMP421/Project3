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
	
	private static ObservableList<Combo2> Donationdata = FXCollections.observableArrayList();
	private static TableView<Combo2> Donationtable = new TableView<Combo2>();
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
				String errorMessage = "";
				boolean thereIsAnError = false;
//				if(2018 - date.getValue().getYear() < 16 ||2018 - date.getValue().getYear() > 122){
//					errorMessage = "age not eligible";
//					thereIsAnError = true;
//				}
//				else if(Double.parseDouble(weight.getText()) < 50 || Double.parseDouble(weight.getText()) > 500 ){
//					errorMessage = "weight not eligible";
//					thereIsAnError = true;
//				}
//				else if(Double.parseDouble(height.getText()) > 240){
//					errorMessage = "Height not realistic";
//					thereIsAnError = true;
//				}
//				if(thereIsAnError){
//					pPrimaryStage.hide();
//					pPrimaryStage.setScene(errorSceneCreator(errorMessage));
//					pPrimaryStage.show();
//					return;
//				}
				
				
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
					pPrimaryStage.hide();
					pPrimaryStage.setScene(errorSceneCreator(e.getMessage()));
					pPrimaryStage.show();
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
		
		
		
		
		Button BloodDonation = new Button("Donate Blood");
		root.add(BloodDonation,0,3);
		
		BloodDonation.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				pPrimaryStage.hide();
				pPrimaryStage.setScene(BloodDonationOption.healthInputScene(pPrimaryStage));
				pPrimaryStage.setTitle("Blood Donation Option");
				pPrimaryStage.show();
				
			}
		});
		
		//Creating a GridPane container
		GridPane recordGrid = new GridPane();
		recordGrid.setPadding(new Insets(10, 10, 10, 10));
		recordGrid.setVgap(5);
		recordGrid.setHgap(5);
		//Defining the Name text field
		
		
		
		//Defining the Last Name text field
		final TextField healthInsuranceNum = new TextField();
		healthID.setPromptText("healthID.");
		GridPane.setConstraints(healthInsuranceNum, 0, 0);
		recordGrid.getChildren().add(healthInsuranceNum);
		
		
		Button recordSubmit = new Button("Submit");
		
		recordSubmit.setOnAction(new EventHandler<ActionEvent>(){
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
					ResultSet rs= stmt.executeQuery("select * from donation where healthinsurancenum =  '"+ healthInsuranceNum.getText() + "'");
					while(rs.next()){
						Donationdata.add(new Combo2(rs.getString("dateofdonation"), rs.getString("totalquantity"), rs.getString("dsaddress")));
					}
					
					pPrimaryStage.hide();
					pPrimaryStage.setScene(donationsceneCreator());
					pPrimaryStage.show();
				} catch (SQLException e) {
					pPrimaryStage.hide();
					pPrimaryStage.setScene(errorSceneCreator(e.getMessage()));
					pPrimaryStage.show();
				}	
			}
		});
		
		GridPane.setConstraints(recordSubmit, 1, 1);
		recordGrid.getChildren().add(recordSubmit);
		Button quit = new Button("Quit");
		recordGrid.add(quit,10,10);
		root.add(quit,0,5);
		quit.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				Platform.exit();
			}
		});
		
		Button record = new Button("Donation record");
		root.add(record,0,2);
		record.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				pPrimaryStage.hide();
				pPrimaryStage.setScene(new Scene(recordGrid));
				pPrimaryStage.show();
			}
			
		});
		
		/*
		GridPane.setConstraints(submitInMGT, 1, 1);
		bloodMgt.getChildren().add(submitInMGT);
		
		Button mgtquit = new Button("Quit");
		bloodMgt.add(mgtquit,1,20);
		mgtquit.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				Platform.exit();
			};
		});
*/
		Button bloodManagement = new Button("Expired Blood Management");
		GridPane bloodMgt = new GridPane();
		root.add(bloodManagement, 0, 4);
		final TextField bbankid = new TextField();
		Label bankId = new Label("Please enter the blood bank id you want to search for: ");
		
		GridPane.setConstraints(bankId, 0, 0);
		bloodMgt.getChildren().add(bankId);
		bbankid.setPromptText("bank id");
		GridPane.setConstraints(bbankid, 0, 1);
		bloodMgt.getChildren().add(bbankid);
		
		Button submitInMGT = new Button("Submit");
		GridPane.setConstraints(submitInMGT, 1, 2);
		bloodMgt.getChildren().add(submitInMGT);
		
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
					ResultSet rs= stmt.executeQuery("select bloodtype, component, sum(quantity) as qt from blood where bankid = "+ bbankid.getText() + " group by bloodtype, component order by bloodtype;");
					while(rs.next()){
						data.add(new Combo(rs.getString("bloodtype"), rs.getString("component"), rs.getDouble("qt")));
					}
					
					pPrimaryStage.hide();
					pPrimaryStage.setScene(sceneCreator());
					pPrimaryStage.show();
				} catch (SQLException e) {
					pPrimaryStage.hide();
					pPrimaryStage.setScene(errorSceneCreator(e.getMessage()));
					pPrimaryStage.show();
				}	
			}
		});
		//bloodMgt.add(submitInMGT, 1, 0);
		
		bloodManagement.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				pPrimaryStage.hide();
				pPrimaryStage.setScene(new Scene(bloodMgt));
				pPrimaryStage.show();
			}
		});
		Scene rootScene = new Scene(root);
		pPrimaryStage.setScene(rootScene);
		Button rootButton = new Button("Back To root");

		donorRegisGrid.add(rootButton, 3,3);
		
		rootButton.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				pPrimaryStage.hide();
				pPrimaryStage.setScene(rootScene);
				pPrimaryStage.show();
				
			}
		});
		pPrimaryStage.setTitle("Blood Donation Database");
		pPrimaryStage.setHeight(500);
		pPrimaryStage.setWidth(500);
		pPrimaryStage.show();


	};
	
	
	
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
	
	public static Scene donationsceneCreator(){
		Scene scene = new Scene(new Group());
 
        final Label label = new Label("Donor info");
        label.setFont(new Font("Arial", 20));
 
        Donationtable.setEditable(true);
 
        TableColumn firstNameCol = new TableColumn("Donation date");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<Combo, String>("firstName"));
 
        TableColumn lastNameCol = new TableColumn("Donation quantity");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<Combo, String>("lastName"));
 
        TableColumn emailCol = new TableColumn("donation Address");
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(
                new PropertyValueFactory<Combo, String>("email"));
 
        Donationtable.setItems(Donationdata);
        Donationtable.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
 
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, Donationtable);
 
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
		return scene;
	}
	
	public static Scene errorSceneCreator(String message){
        final Label label = new Label(message);
        label.setFont(new Font("Arial", 20));
 
        GridPane grid = new GridPane();
        grid.getChildren().add(label);
        
		return new Scene(grid);
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
	
public static class Combo2 {
		
        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty email;
 
        private Combo2(String BloodType, String BloodComponent, String quantity) {
            this.firstName = new SimpleStringProperty(BloodType);
            this.lastName = new SimpleStringProperty(BloodComponent);
            this.email = new SimpleStringProperty(quantity);
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
 
        public String getEmail() {
            return email.get();
        }
 
        public void setEmail(String fName) {
            email.set(fName);
        }
    }
}
