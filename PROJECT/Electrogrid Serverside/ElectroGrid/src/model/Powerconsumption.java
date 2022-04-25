package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Powerconsumption {
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
	public String insertConsumption(String cus, String presread, String preread, String unit, String tax, String date) {
		Connection con = connect();
		String output = "";
		if (con == null) {
			return "Error while connecting to the database";
		}

		// create a prepared statement
		String query = " insert into consumption (`ConsumptionID`,`CustomerID`,`Present_reading`,`Previous_reading`,`Consumptionunit`,`Tax`,`Due_date`)"
				+ " values (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement preparedStmt;
		try {
			preparedStmt = con.prepareStatement(query);

			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, cus);
			preparedStmt.setString(3, presread);
			preparedStmt.setString(4, preread);
			preparedStmt.setString(5, unit);
			preparedStmt.setString(6, tax);
			preparedStmt.setString(7, date);

			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (SQLException e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String readConsumption() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading Consumption.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Consumption ID</th><th>Customer ID</th>" + "<th>Present Reading</th>"
					+ "<th>Previous reading</th>" + "<th>Consumption unit</th>" + "<th>Tax</th>" + "<th>Due Date</th>" ;

			String query = "select * from consumption";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String ConsumptionID = Integer.toString(rs.getInt("ConsumptionID"));
				String CustomerID = rs.getString("CustomerID");
				String Present_reading = rs.getString("Present_reading");
				String previous_reading = rs.getString("previous_reading");
				String Consumtionunit = rs.getString("Consumptionunit");
				String Tax = rs.getString("Tax");
				String Due_date = rs.getString("Due_date");

				// Add into the html table
				output += "<tr><td>" + ConsumptionID + "</td>";
				output += "<td>" + CustomerID + "</td>";
				output += "<td>" + Present_reading + "</td>";
				output += "<td>" + previous_reading+ "</td>";
				output += "<td>" + Consumtionunit + "</td>";
				output += "<td>" + Tax + "</td>";
				output += "<td>" + Due_date + "</td>";
			}
			con.close();

			output += "</table>";
		}

		catch (Exception e) {
			output = "Error while reading the Consumption.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateConsumption(String ID, String cus, String presread, String preread , String unit, String tax, String date)

	{
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement

			String query = " update consumption set CustomerID= ? , Present_reading = ? , Previous_reading = ? , Consumptionunit = ? , Tax = ? , Due_date = ?  where ConsumptionID = ? ";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, cus);
			preparedStmt.setString(3, presread);
			preparedStmt.setString(4, preread);
			preparedStmt.setString(5, unit);
			preparedStmt.setString(6, tax);
			preparedStmt.setString(7, date);

			preparedStmt.setInt(7, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the consumption.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteConsumption(String ConsumptionID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from consumption where ConsumptionID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(ConsumptionID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the Consumption.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
