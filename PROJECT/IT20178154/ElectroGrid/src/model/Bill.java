package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Bill {
	public Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/electrogrid", "root", "");
			// For testing
			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	// insert method
	public String insertBill(String customerid, String billamount, String billdate, String paidamount, String status) {
		Connection con = connect();
		String output = "";
		if (con == null) {
			return "Error while connecting to the database";
		}

		// create a prepared statement
		String query = " insert into bill (`Billno`,`CustomerID`,`Billamount`,`Billdate`,`Paidamount`,`Status`)"
				+ " values (?, ?, ?, ?, ?, ?)";
		PreparedStatement preparedStmt;
		try {
			preparedStmt = con.prepareStatement(query);

			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, customerid);
			preparedStmt.setString(3, billamount);
			preparedStmt.setString(4, billdate);
			preparedStmt.setString(5, paidamount);
			preparedStmt.setString(6, status);

			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (SQLException e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String readBill() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading Billing details.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Customer ID</th><th>Bill Amount</th>" + "<th>Bill Date</th>"
					+ "<th>Paid Amount</th>" + "<th>Status</th></tr>" ;

			String query = "select * from Bill";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String Billno = Integer.toString(rs.getInt("Billno"));
				String CustomerID = rs.getString("CustomerID");
				String Billamount = rs.getString("Billamount");
				String Billdate = rs.getString("Billdate");
				String Paidamount = rs.getString("Paidamount");
				String Status = rs.getString("Status");

				// Add into the html table
				output += "<tr><td>" + CustomerID + "</td>";
				output += "<td>" + Billamount + "</td>";
				output += "<td>" + Billdate + "</td>";
				output += "<td>" + Paidamount + "</td>";
				output += "<td>" + Status + "</td>";
			}
			con.close();

			output += "</table>";
		}

		catch (Exception e) {
			output = "Error while reading the Customers.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateBill(String ID, String customerid, String billamount, String billdate, String paidamount, String status)

	{
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement

			String query = " update bill set CustomerID= ? , Billamount = ? , Billdate = ? , Paidamount = ? , Status = ?  where Billno = ? ";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, customerid);
			preparedStmt.setString(2, billamount);
			preparedStmt.setString(3, billdate);
			preparedStmt.setString(4, paidamount);
			preparedStmt.setString(5, status);

			preparedStmt.setInt(6, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the Billing Details.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteBill(String Billno) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from bill where Billno=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(Billno));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the Bill Details.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}