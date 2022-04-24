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

import model.Electricityboard;

@Path("/Electricityboards")

public class ElectricityboardService {

	Electricityboard elecObj = new Electricityboard();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readElec() {
		return elecObj.readElec();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertElec(@FormParam("Electricityboard") String Electricityboard,
			@FormParam("Location") String Location, @FormParam("Contactnumber") String Contactnumber) {
		String output = elecObj.insertElec(Electricityboard, Location, Contactnumber );
		return output;
	}
	
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateElec(String elecData) {
			// Convert the input string to a JSON object
			JsonObject elecObject = new JsonParser().parse(elecData).getAsJsonObject();
			// Read the values from the JSON object
			String ElectricityboardID = elecObject.get("ElecricityboardID").getAsString();
			String Electricityboard = elecObject.get("Electricityboard").getAsString();
			String Location = elecObject.get("Location").getAsString();
			String Contactnumber = elecObject.get("Contactnumber").getAsString();
			String output = elecObj.updateElec(ElectricityboardID, Electricityboard, Location, Contactnumber);
			return output;
		}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteBill(String elecData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(elecData, "", Parser.xmlParser());

		// Read the value from the element <CustomerID>
		String ElectricityboardID = doc.select("ElectricityboardID").text();
		String output = elecObj.deleteElec(ElectricityboardID);
		return output;
	}

}

