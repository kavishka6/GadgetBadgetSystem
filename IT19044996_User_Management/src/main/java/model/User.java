package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {
	
	//Database connection
	private Connection connect(){
		 Connection con = null;
		 try{
			 Class.forName("com.mysql.jdbc.Driver");
			 
			 
			 con = DriverManager.getConnection( "jdbc:mysql://localhost/paf?useTimezone=true&serverTimezone=UTC", "root", "");
		 }
		 catch (Exception e){
			 e.printStackTrace();
			 }
		 return con;
	 }
	
	//inserting users
	
	public String insertUsers( String uname, String uemail,String utype){
		String output = "";
		
		try{
			Connection con = connect();
			if (con == null){
				return "Connection Error to the database for inserting."; 
			}
			
			
			String query = " INSERT INTO usermanagement(`UserID`, `UserName`, `UserEmail`, `UserType`) VALUES (?, ?, ?, ?)";
			PreparedStatement preparedStmnt = con.prepareStatement(query);
			
			preparedStmnt.setInt(1, 0);
			preparedStmnt.setString(2,uname);
			preparedStmnt.setString(3,uemail);
			preparedStmnt.setString(4,utype);
			
			// executing the prepared statement
			preparedStmnt.execute();
			con.close();
			output = "Successfully Inserted";
		}
		catch (Exception e){
			output = " Cannot Insert! Error occured while inserting the user";
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return output;
	}
	

	//Reading users
	
	public String readUsers(){
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return " Cannot Read! Error occured whieconnecting to the database."; }
			
			//displaying table
			output = "<table border='1'>"
					+ "<tr><th>User ID</th>"
					+ "<th>User Name</th>" 
					+ "<th>User Email</th>"
					+ "<th>User Type</th>"+
					"<th>Update</th><th>Remove</th></tr>";

			String query = "select * from usermanagement";
			Statement stmnt = con.createStatement();
			ResultSet res = stmnt.executeQuery(query);
			
			
			while (res.next())
			{
				String id = res.getString("UserId");
				String name= res.getString("UserName");
				String email = res.getString("UserEmail");
				String type = res.getString("UserType");

				// Adding to the table
				output += "<tr><td>" + id+ "</td>";
				output += "<td>" + name + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + type + "</td>";

				
				// Buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='items.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
						+ "<input name='itemID' type='hidden' value='" + id
						+ "'>" + "</form></td></tr>";
			}
			con.close();
			// Completing the table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the user";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	//Updating User details
	
	public String update_Users(String UserId, String UserName, String UserType, String UserEmail ){
		String output = "";
		try{
			Connection con = connect();
			if (con == null)
			{return " Cannot Update! Error occured while connecting to the database for updating."; }
			
			// creating a prepared statement
			
			String query = "UPDATE usermanagement SET UserName=?,UserType=?,UserMail=? WHERE UserId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			
			preparedStmt.setString(4, UserId);
			preparedStmt.setString(1, UserName);
			preparedStmt.setString(3, UserType);
			preparedStmt.setString(2, UserEmail);
			
			
			System.out.println("no error1");
			
			// executing the prepared statement
			preparedStmt.execute();
			con.close();

			output = "Successfully Updated";
		}
		catch (Exception e)
		{
			output = "Cannot Update User! Error occured while updating the user";
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return output;
	}
	
		//Deleting the User Details
	
	public String deleteUsers(String UserId){
			String output = "";
			try{
				Connection con = connect();
				if (con == null)
				{return "Error while connecting to the database for deleting."; }
				// creating a prepared statement
				String query = "delete from usermanagement where UserID=?";
				PreparedStatement preparedStmnt = con.prepareStatement(query);
				// binding values
				preparedStmnt.setString(1,UserId);
				// executing the statement
				preparedStmnt.execute();
				con.close();
				output = "Successfully Deleted.";
			}
			catch (Exception e)
			{
				output = "Cannot Delete! Error occured while deleting the user";
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
			return output;
		} 
	
}
