package DonorInserter;

import java.sql.*;
public class DonorInserter {
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
		Statement stmt = con.createStatement();
		for(int i = 0 ; i<300; i++) {
			String aString = "";
			stmt.executeUpdate(aString);
		}
		stmt.close();
		con.close();
	}
}