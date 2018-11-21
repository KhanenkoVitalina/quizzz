package application;

import java.sql.*;

public class DataBaseWork {
	  private Connection connect = null;
	  private Statement statement = null;
	  private PreparedStatement preparedStmt = null;
	  private ResultSet resultSet = null;
	  public DataBaseWork() {
		MySQLAccess acces = new MySQLAccess();
		connect  = acces.getConnection();
	  }
	  public void insert(User user) {
		  if(exists(user)) {
			  System.out.println("exists");
			  return;
		  }
		  String query = " insert into users (user_name, user_password, max_level)"
			        + " values (?, ?, ?)";
				try {
					preparedStmt = (PreparedStatement) connect.prepareStatement(query);
					preparedStmt.setString (1, user.getName());
					preparedStmt.setString (2, user.getPassword());
					preparedStmt.setInt    (3, user.getMaxLevel());
					preparedStmt.execute();
					System.out.println("Prep St executed!");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}    
	  }
	  public void update(User user) {
	      String query = "update users set max_level = " + user.getMaxLevel() + " where user_name = ? AND user_password = ?";
		try {
			preparedStmt = (PreparedStatement) connect.prepareStatement(query);
			 preparedStmt.setInt   (1, user.getMaxLevel());
		      preparedStmt.setString(2, "Fred");
		      preparedStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	     
	  }

	  public boolean exists(User user) {
		try {
			
			String query = "SELECT * FROM users WHERE user_name = '" + user.getName() + "' AND user_password = '" + user.getPassword() + "';";
			
			Statement statement = connect.createStatement();   
			resultSet = statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			resultSet.beforeFirst();
			if(resultSet.next()) {
				System.out.println("res set is null");
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
		 
	  }

	
}
