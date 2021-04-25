package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Research {

	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 //Giving the correct details: DbName or DBServer, user name, user pw
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/research_db", "root", "");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 }
	
	
	public String insertItem(String code, String title, String desc,String leader,String university,String published_date)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 
	 // creating a prepared statement
	 
	 String query = " INSERT INTO research (`id`, `title`, `description`, `leader`, `university`, `published_date`) VALUES (?, ?, ?, ?, ?, ?);";
	 PreparedStatement preparedStmnt = con.prepareStatement(query);
	 
	 // binding values
	 preparedStmnt.setString(1, code);
	 preparedStmnt.setString(2, title);
	 preparedStmnt.setString(3, desc);
	 preparedStmnt.setString(4, leader);
	 preparedStmnt.setString(5, university);
	 preparedStmnt.setString(6, published_date);
	 
	// executing the statement
	
	 preparedStmnt.execute();
	 con.close();
	 output = "Successfully Inserted";
	 }
	 catch (Exception e)
	 {
	 output = "Cannot Insert! Error occured while inserting the item.";
	 System.err.println(e.getMessage());
	 e.printStackTrace();
	 }
	 return output;
	 }
	
	
	public String readItems()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 
	 // Preparing the html table to be displayed
	 
	 output = "<table border='1'><tr><th>Item Code</th><th>Research Title</th>" +
	 "<th>Research Description</th>" +
	 "<th>Member Name</th>" +
	 "<th>University</th>" +
	 "<th>Published Date</th>" +
	 "<th>Update</th><th>Remove</th></tr>";

	 String query = "select * from research";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 
	 
	 while (rs.next())
	 {
	 String id = Integer.toString(rs.getInt("code"));
	 String name= rs.getString("name");
	 String des = rs.getString("description");
	 String leader = rs.getString("leader");
	 String university = rs.getString("university");
	 String published_date = rs.getString("published_date");
	 
	 // Adding to the table
	 
	 output += "<tr><td>" + id+ "</td>";
	 output += "<td>" + name + "</td>";
	 output += "<td>" + des + "</td>";
	 output += "<td>" + leader + "</td>";
	 output += "<td>" + university + "</td>";
	 output += "<td>" + published_date + "</td>";
	 
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
	 + "<td><form method='post' action='items.jsp'>"
	 + "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
	 + "<input name='itemID' type='hidden' value='" + id
	 + "'>" + "</form></td></tr>";
	 }
	 con.close();
	 
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the items.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	
	
	public String updateItem(String id, String name, String des, String leader, String university, String published_date)
	
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 
	 // creating a prepared statement
	 
	 String query = "UPDATE research SET id=?,title=?,description=?,leader=?,university=?,published_date=? WHERE id=?";
	 PreparedStatement preparedStmnt = con.prepareStatement(query);
	 
	
	 
	 preparedStmnt.setString(1, id);
	 preparedStmnt.setString(2, des);
	 preparedStmnt.setString(3, leader);
	 preparedStmnt.setString(4, university);
	 preparedStmnt.setString(5, published_date);
	 preparedStmnt.setInt(6,Integer.parseInt(id));
	 System.out.println("no error1");
	 // execute the statement
	 preparedStmnt.execute();
	 con.close();
	
	 output = "Successfully Updated";
	 }
	 catch (Exception e)
	 {
	 output = "Cannot Update! Error occured while updating the item.";
	 System.err.println(e.getMessage());
	 e.printStackTrace();
	 }
	 return output;
	 }
	
	
	public String deleteItem(String itemID)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // creating a prepared statement
	 
	 String query = "delete from research where id=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setString(1,itemID);
	 
	 // executing the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Successfully Deleted";
	 }
	 catch (Exception e)
	 {
	 output = "Cannot Delete! Error occured while deleting the item.";
	 System.err.println(e.getMessage());
	 e.printStackTrace();
	 }
	 return output;
	 } 
}
