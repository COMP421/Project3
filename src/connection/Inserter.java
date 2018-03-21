package connection;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;


public class Inserter {
		
	public String insert(){
		String answer = null;
		for(int i=0; i<1; i++){
			String firstname = NameGenerator.generateName();
			String lastname = NameGenerator.generateName();
			String Health1 = lastname.substring(0, 4).toUpperCase();
			int Health2 = 1000 + (int)(Math.random() * 9999);
			int Health3 = 1000 + (int)(Math.random() * 9999);
			GregorianCalendar gc = new GregorianCalendar();

	        int year = randBetween(1900, 2010);

	        gc.set(gc.YEAR, year);

	        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));

	        gc.set(gc.DAY_OF_YEAR, dayOfYear);
	        
	        int num = 100 + (int)(Math.random() * 99999);
	        String address = NameGenerator.generateName();
	        
	        
	        String sex;
	        if(Math.random()<0.45){
	        	sex = "Male";
	        }
	        else if(Math.random()<0.90){
	        	sex = "Female";
	        }
	        else{
	        	sex = "Unknown";
	        }
	        
	        String[] types = {"A+","A-", "B+", "B-","O+","O-", "AB+","AB-"};
	        
	        String type = types[(int)(Math.random() * 7)];
	        int phone1 = 100 + (int)(Math.random() * 999);
	        int phone2 = 100 + (int)(Math.random() * 9999);
	        double weight = 100 + Math.random()*100;
	        double height = 140 + Math.random()*60;
	        DecimalFormat df2 = new DecimalFormat(".##");
			answer = "insert into donor values('" + Health1 + " "  +Health2 + " " + Health3 +"'," + "'" + firstname+ " " +lastname+ "',"+ "'" + gc.get(gc.YEAR) + "-" + (gc.get(gc.MONTH) + 1) + "-" + gc.get(gc.DAY_OF_MONTH)+  "',"+ "'" +num +" "+address + "',"+ "'" +sex+ "',"+ "'" +type + "',"+ "'514-"+ phone1+ "-"+phone2 + "',"+ "'" +df2.format(weight)+ "',"+ "'" +df2.format(height)+ "'" + ");";
		}
		
		return answer;
		
		
	}
	 public static int randBetween(int start, int end) {
	        return start + (int)Math.round(Math.random() * (end - start));
	    }

	

}
