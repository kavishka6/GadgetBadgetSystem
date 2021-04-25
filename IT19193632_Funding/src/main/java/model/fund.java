package model;



import java.sql.*;

import org.jsoup.nodes.Document;

public class fund{
	
	//A common method to connect to the DB
		private Connection connect()
		 {
		 Connection con = null;
		 try
		 {
		 Class.forName("com.mysql.jdbc.Driver");

		 //Provide the correct details: DBServer/DBName, username, password
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Localhost", "root", "");
		 }
		 catch (Exception e)
		 {e.printStackTrace();}
		 return con;
		 }
		public String insertfund(String Fund_id, String Funders_name, String Project_name, String Amount)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "GadgetBadget Alert: Error while connecting to the database for inserting."; }
		 // create a prepared statement
		 String query = "insert into fundmanagement(`Fund_id`,`Funders_name`,`Project_name`,`Amount`)"+ " values (?, ?, ?, ?)";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, 0);
		 preparedStmt.setString(2, Funders_name);
		 preparedStmt.setString(3, Project_name);
		 preparedStmt.setString(4, Amount);
		
		// execute the statement3
		 preparedStmt.execute();
		 con.close();
		 output = "GadgetBadget Alert: Inserted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "GadgetBadget Alert: Error while inserting the fund management details.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
		
		//read
		public String readfund()
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "GadgetBadget Alert: Error while connecting to the database for reading."; }
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>Item Code</th><th>Item Name</th>" + "<th>Item Price</th>" +"<th>Item Description</th>" +"<th>Update</th><th>Remove</th></tr>";

		 String query = "select * from fundmanagement";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
		 String Fund_id = Integer.toString(rs.getInt("Fund_id"));
		 String Funders_name = rs.getString("Funders_name");
		 String Project_name = rs.getString("Project_name");
		 String Amount = Double.toString(rs.getDouble("Amount"));
		 
		 // Add into the html table
		 output += "<tr><td>" + Fund_id + "</td>";
		 output += "<td>" + Funders_name + "</td>";
		 output += "<td>" + Project_name + "</td>";
		 output += "<td>" + Amount + "</td>";
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
		 + "<td><form method='post' action='items.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>" + "<input name='ID' type='hidden' value='" + Fund_id + "'>" + "</form></td></tr>";
		 }
		 con.close();
		 // Complete the html table
		 output += "</table>";
		 }
		 catch (Exception e)
		 {
		 output = "GadgetBadget Alert: Error while reading the funds.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 } 
		
		public String updatefund(String Fund_id, String Funders_name, String Project_name, String Amount)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "GadgetBadget Alert: Error while connecting to the database for updating."; }
		 // create a prepared statement
		 String query = "UPDATE items SET Funders_name=?,Project_name=?,Amount=? WHERE Fund_id=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setString(1, Funders_name);
		 preparedStmt.setString(2, Project_name);
		 preparedStmt.setDouble(3, Double.parseDouble(Amount));
		 preparedStmt.setInt(4, Integer.parseInt(Fund_id));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "GadgetBadget Alert: Updated successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while updating the item.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 } 
		
		public String deletefund(String Fund_id)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "GadgetBadget Alert: Error while connecting to the database for deleting."; }
		 // create a prepared statement
		 String query = "delete from items where Fund_id=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(Fund_id));
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "GadgetBadget Alert: Deleted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while deleting the item.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 } 
	 }
