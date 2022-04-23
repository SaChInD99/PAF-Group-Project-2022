package com;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Employee;

@Path("/Employee")

public class EmployeeService {

	Employee employeeObj = new Employee();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readEmployee() {
		return employeeObj.readEmployee();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertEmployee(@FormParam("EmployeeName") String EmployeeName,
			@FormParam("EmployeeEmail") String EmployeeEmail, @FormParam("EmployeeType") String EmployeeType,
			@FormParam("EmployeeContact") String EmployeeContact) {
		String output = employeeObj.insertEmployee(EmployeeName, EmployeeEmail, EmployeeType, EmployeeContact );
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateEmployee(String employeeData) {
		// Convert the input string to a JSON object
		JsonObject employeeObject = new JsonParser().parse(employeeData).getAsJsonObject();
		// Read the values from the JSON object
		String EmployeeID = employeeObject.get("EmployeeID").getAsString();
		String EmployeeName = employeeObject.get("EmployeeName").getAsString();
		String EmployeeEmail = employeeObject.get("EmployeeEmail").getAsString();
		String EmployeeType = employeeObject.get("EmployeeType").getAsString();
		String EmployeeContact = employeeObject.get("EmployeeContact").getAsString();
		
		String output = employeeObj.updateEmployee(EmployeeID, EmployeeName, EmployeeEmail, EmployeeType,
				EmployeeContact );
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteEmployee(String employeeData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(employeeData, "", Parser.xmlParser());

		// Read the value from the element <EmployeeID>
		String EmployeeID = doc.select("EmployeeID").text();
		String output = employeeObj.deleteEmployee(EmployeeID);
		return output;
	}

}

