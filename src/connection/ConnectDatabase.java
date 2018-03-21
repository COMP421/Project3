package connection;
import java.sql.*;
public class ConnectDatabase {
	
	public static int randomBank(){
		int i = 1+(int)(Math.random()*5);
		if(i==6){
			i=5;
		}
		return i;
	}
	
	@SuppressWarnings("deprecation")
	public static String expireDate(String component, String donationDate){
		Date date = Date.valueOf(donationDate);
		if(component.equals("plasma")){
			date.setDate(date.getDate() + 365);
		}
		else if(component.equals("blood cell")){
			date.setDate(date.getDate() + 365*3);
		}
		else if(component.equals("AHF")){
			date.setDate(date.getDate() + 365);
		}
		else{
			date.setDate(date.getDate() + 5);
		}
		return date.toString();
		
	}
	
	public static void main(String[] args) throws SQLException{
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = "jdbc:postgresql://comp421.cs.mcgill.ca:5432/cs421";
		String userName = "cs421g05";
		String password = "ASDFqwerty1234";
		Connection con = DriverManager.getConnection(url, userName, password);
		
		Statement stmt1 = con.createStatement();
		Statement stmt2 = con.createStatement();
		ResultSet donationRS = stmt1.executeQuery("Select * from donation;");
		int[] bloodId = {1,1,1,1,1};
		while(donationRS.next()){
			double quantity = donationRS.getDouble("totalquantity");
			String donationDate = donationRS.getString("dateOfDonation");
			String insuranceNum = donationRS.getString("healthinsurancenum");
			String did = donationRS.getString("did");
			ResultSet donorBloodType = stmt2.executeQuery("select bloodType from donor where healthinsurancenum = '" + insuranceNum +"';");
			donorBloodType.next();
			String bloodType = donorBloodType.getString("bloodType");
			donorBloodType.close();
			int num = randomBank();
			stmt2.executeUpdate("insert into blood values ("+ bloodId[num-1] + ", 1394827, " + num +", '" + bloodType + "', 'Plasma', " + quantity/2 + ", '" + expireDate("plasma", donationDate) + "', -30, '" + did + "'); ");
			bloodId[num-1]++;
			stmt2.executeUpdate("insert into blood values ("+ bloodId[num-1] + ", 1394827, " + num +", '" + bloodType + "', 'Blood Cell', " + quantity/4 + ", '" + expireDate("blood cell", donationDate) + "', -70, '" + did + "'); ");
			bloodId[num-1]++;
			stmt2.executeUpdate("insert into blood values ("+ bloodId[num-1] + ", 1394827, " + num +", '" + bloodType + "', 'Cryoprecipitated AHF', " + quantity/8 + ", '" + expireDate("AHF", donationDate) + "', -20, '" + did + "'); ");
			bloodId[num-1]++;
			stmt2.executeUpdate("insert into blood values ("+ bloodId[num-1] + ", 1394827, " + num +", '" + bloodType + "', 'Platelet', " + quantity/8 + ", '" + expireDate("platelet", donationDate) + "', 22, '" + did + "'); ");
			bloodId[num-1]++;
		}
		donationRS.close();
		stmt1.close();
		stmt2.close();
		con.close();
	}
}
