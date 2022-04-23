package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Powerstation {
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
	public String insertPowerstation(String name, String province, String location, String powergenerated) {
		Connection con = connect();
		String output = "";
		if (con == null) {
			return "Error while connecting to the database";
		}

		// create a prepared statement
		String query = " insert into powerstation (`PowerstationID`,`Stationname`,`Province`,`Location`,`Powergenerated`)"
				+ " values (?, ?, ?, ?, ?)";
		PreparedStatement preparedStmt;
		try {
			preparedStmt = con.prepareStatement(query);

			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, province);
			preparedStmt.setString(4, location);
			preparedStmt.setString(5, powergenerated);
			

			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (SQLException e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String readPowerstation() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading Powerstation.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Powerstation ID</th><th>Powerstation Name</th><th>Province</th>" + "<th>Location</th>"
					+ "<th>Powergenerated</th>"  ;

			String query = "select * from powerstation";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String PowerstationID = Integer.toString(rs.getInt("PowerstationID"));
				String Stationname = rs.getString("Stationname");
				String Province = rs.getString("Province");
				String Location = rs.getString("Location");
				String Powergenerated = rs.getString("Powergenerated");
	

				// Add into the html table
				output += "<tr><td>" + PowerstationID + "</td>";
				output += "<td>" + Stationname + "</td>";
				output += "<td>" + Province + "</td>";
				output += "<td>" + Location + "</td>";
				output += "<td>" + Powergenerated + "</td>";
			}
			con.close();

			output += "</table>";
		}

		catch (Exception e) {
			output = "Error while reading the Powerstation.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updatePowerstation(String ID, String name, String province, String location, String powergenerated)

	{
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement

			String query = " update powerstation set Stationname= ? , Province = ? , Location = ? , Powergenerated = ?  where PowerstationID = ? ";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, province);
			preparedStmt.setString(3, location);
			preparedStmt.setString(4, powergenerated);
		

			preparedStmt.setInt(5, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the powerstation.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deletePowerstation(String PowerstationID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from powerstation where PowerstationID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(PowerstationID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the Powerstation.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}