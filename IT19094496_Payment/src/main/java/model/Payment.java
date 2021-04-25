package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

public class Payment {
	// A common method to connect to the DataBase
		private Connection connect() {
			Connection con = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");

				// Provide the correct details: DataBaseServer/DataBaseName, username, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gb_payment", "root", "");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return con;
		}
		
public String insertPayment(int orderid, float amount) {
			String out_put = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for inserting.";
				}
				// create a prepared statement
				String query = " insert into payment(`payment_ID`,`orderID`,`TotalAmount`)"+ "values (?, ?, ?)";
				PreparedStatement preparedStatement = con.prepareStatement(query);
				// binding values
				preparedStatement.setInt(1, 0);
				preparedStatement.setInt(2, orderid);
				preparedStatement.setFloat(3, amount);
				
				// execute the prepared statement
				preparedStatement.execute();
				con.close();
				out_put = "Inserted successfully";
			} catch (Exception e) {
				out_put = "Error while inserting payment.";
				System.err.println(e.getMessage());
			}
			return out_put;
		}

public String readPayments() {
	String out_put = "";
	try {
		Connection con = connect();
		if (con == null) {
			return "Error while connecting to the database for reading.";
		}
		// Prepare html table to display
		out_put = "<table border='1'><tr><th>PaymentID</th><th>OrderID</th>" 
				+ "<th>Payment Date and Time</th>" + "<th>Total Amount</th></tr>";

		String query = "select * from payment";
		Statement Statement = con.createStatement();
		ResultSet rs = Statement.executeQuery(query);

		// iterate rows in the result set
		while (rs.next()) {
			String payment_ID = Integer.toString(rs.getInt("payment_ID"));
			String orderID = Integer.toString(rs.getInt("orderID"));
			Timestamp datets = rs.getTimestamp("pay_DateTime");
			String pay_Date = datets.toString();
			String amount = Float.toString(rs.getFloat("TotalAmount"));
			


			// Add to html table
			out_put += "<tr><td>" + payment_ID + "</td>";
			out_put += "<td>" + orderID + "</td>";
			out_put += "<td>" + pay_Date + "</td>";
			out_put += "<td>" + amount + "</td>";

			// buttons

		}
		con.close();

		// Complete html table
		out_put += "</table>";
	} catch (Exception e) {
		out_put = "Error while reading payment.";
		System.err.println(e.getMessage());
	}
	return out_put;
}

public String updatePayment(String pID, String total, String oID) {
	String out_put = "";
	try {
		Connection con = connect();
		if (con == null) {
			return "Error while connecting to the database to update.";
		}
		// create prepared statement
		String query = "UPDATE payment SET orderID=?,TotalAmount=? WHERE payment_ID=?";
		PreparedStatement preparedStatement = con.prepareStatement(query);
		// binding values
		
		preparedStatement.setInt(1, Integer.parseInt(oID));
		preparedStatement.setFloat(2,Float.parseFloat(total));
		preparedStatement.setInt(3, Integer.parseInt(pID));
		// execute the prepared statement
		preparedStatement.execute();
		con.close();
		out_put = "Update successfull";
	} catch (Exception e) {
		out_put = "Error while updating payment.";
		System.err.println(e.getMessage());
		}
	return out_put;
	}

public String deletePayment(String payment_ID) {
	String out_put = "";
	try {
		Connection con = connect();
		if (con == null) {
			return "Error while connecting to the database to delete.";
		}
		// create a prepared statement
		String query = "delete from payment where payment_ID=?";
		PreparedStatement preparedStatement = con.prepareStatement(query);
		// binding values
		preparedStatement.setInt(1, Integer.parseInt(payment_ID));
		// execute the prepared statement
		preparedStatement.execute();
		con.close();
		out_put = "Delete successfull";
	} catch (Exception e) {
		out_put = "Error while deleting payment.";
		System.err.println(e.getMessage());
	}
	return out_put;
}
}
