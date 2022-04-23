package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Employee {
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
	public String insertEmployee(String name, String email, String type, String contact) {
		Connection con = connect();
		String output = "";
		if (con == null) {
			return "Error while connecting to the database";
		}

		// create a prepared statement
		String query = " insert into employee (`EmployeeID`,`EmployeeName`,`EmployeeEmail`,`EmployeeType`,`EmployeeContact`)"
				+ " values (?, ?, ?, ?, ?)";
		PreparedStatement preparedStmt;
		try {
			preparedStmt = con.prepareStatement(query);

			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, email);
			preparedStmt.setString(4, type);
			preparedStmt.setString(5, contact);
			

			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (SQLException e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String readEmployee() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading Emplyees.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Employee Name</th><th>Employee Email</th>" + "<th>Employee Type</th>"
					+ "<th>Employee Contact</th>"  ;

			String query = "select * from employee";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String EmployeeID = Integer.toString(rs.getInt("EmployeeID"));
				String EmployeeName = rs.getString("EmployeeName");
				String EmployeeEmail = rs.getString("EmployeeEmail");
				String EmployeeType = rs.getString("EmployeeType");
				String EmployeeConatct = rs.getString("EmployeeContact");
				

				// Add into the html table
				output += "<tr><td>" + EmployeeName + "</td>";
				output += "<td>" + EmployeeEmail + "</td>";
				output += "<td>" + EmployeeType + "</td>";
				output += "<td>" + EmployeeConatct + "</td>";
			
			}
			con.close();

			output += "</table>";
		}

		catch (Exception e) {
			output = "Error while reading the Employees.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateEmployee(String ID, String name, String email, String type, String contact)

	{
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement

			String query = " update employee set EmployeeName= ? , EmployeeEmail = ? , EmployeeType = ? , EmployeeContact = ?   where EmployeeID = ? ";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, email);
			preparedStmt.setString(3, type);
			preparedStmt.setString(4, contact);
			

			preparedStmt.setInt(5, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the employee.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteEmployee(String EmployeeID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from employee where EmployeeID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(EmployeeID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the Employee.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
