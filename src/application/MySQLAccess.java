package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySQLAccess {
	private  Connection con;
	public  Connection getConnection() {
		connect();
		return con;
	}
	private  void connect() {
		try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        System.out.println("Obtained");
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
		 try {
			 con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/quizdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","Vitalina1!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
