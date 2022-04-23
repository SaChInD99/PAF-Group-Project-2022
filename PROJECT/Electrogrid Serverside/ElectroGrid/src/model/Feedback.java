package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Feedback {
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
	public String insertfeedback(String name, String email, String feedback) {
		Connection con = connect();
		String output = "";
		if (con == null) {
			return "Error while connecting to the database";
		}

		// create a prepared statement
		String query = " insert into feedback (`FeedbackID`,`CustomerName`,`CustomerEmail`,`Feedback`)"
				+ " values (?, ?, ?, ?)";
		PreparedStatement preparedStmt;
		try {
			preparedStmt = con.prepareStatement(query);

			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, email);
			preparedStmt.setString(4, feedback);
			

			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (SQLException e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String readfeedback() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading Feedbacks.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Customer Name</th><th>customer Email</th>" + "<th>Customer Feedback</th>"
					 ;

			String query = "select * from feedback";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String FeedbackID = Integer.toString(rs.getInt("FeedbackID"));
				String CustomerName = rs.getString("CustomerName");
				String CustomerEmail = rs.getString("CustomerEmail");
				String CustomerFeedback = rs.getString("Feedback");
				

				// Add into the html table
				output += "<tr><td>" + CustomerName + "</td>";
				output += "<td>" + CustomerEmail + "</td>";
				output += "<td>" + CustomerFeedback + "</td>";
				
			}
			con.close();

			output += "</table>";
		}

		catch (Exception e) {
			output = "Error while reading the Feedbacks.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateFeedback(String ID, String name, String email, String feedback)

	{
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement

			String query = " update feedback set CustomerName= ? , CustomerEmail = ? , Feedback = ? where FeedbackID = ?";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, email);
			preparedStmt.setString(3, feedback);
			

			preparedStmt.setInt(4, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the Feedback.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteFeedback(String FeedbackID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from feedback where FeedbackID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(FeedbackID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the Feedback.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
}