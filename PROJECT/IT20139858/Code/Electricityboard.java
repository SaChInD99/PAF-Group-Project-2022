package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Electricityboard {
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
	public String insertElec(String electricityboard, String location, String contactnumber) {
		Connection con = connect();
		String output = "";
		if (con == null) {
			return "Error while connecting to the database";
		}

		// create a prepared statement
		String query = " insert into electricity (`ElectricityboardID`,`Electricityboard`,`Location`,`Contactnumber`)"
				+ " values (?, ?, ?, ?)";
		PreparedStatement preparedStmt;
		try {
			preparedStmt = con.prepareStatement(query);

			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, electricityboard);
			preparedStmt.setString(3, location);
			preparedStmt.setString(4, contactnumber);
			

			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (SQLException e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String readElec() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading Electricityboard details.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Electicity Board ID</th><th>Electicity Board</th><th>Location</th>" + "<th>Contact Number</th>" ;

			String query = "select * from electricity";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String ElectricityboardID = Integer.toString(rs.getInt("ElectricityboardID"));
				String Electricityboard = rs.getString("Electricityboard");
				String Location = rs.getString("Location");
				String Contactnumber = rs.getString("Contactnumber");
			

				// Add into the html table
				output += "<tr><td>" + ElectricityboardID + "</td>";
				output += "<td>" + Electricityboard + "</td>";
				output += "<td>" + Location + "</td>";
				output += "<td>" + Contactnumber + "</td>";
				
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

	public String updateElec(String ID, String electricityboard, String location, String contactnumber)

	{
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement

			String query = " update electricity set Electricityboard= ? , Location = ? , Contactnumber = ?   where  ElectricityboardID = ? ";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, electricityboard);
			preparedStmt.setString(2, location);
			preparedStmt.setString(3, contactnumber);
		

			preparedStmt.setInt(4, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the Electricityboard Details.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteElec(String ElectricityboardID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from electricity where ElectricityboardID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(ElectricityboardID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the Electricity Details.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}