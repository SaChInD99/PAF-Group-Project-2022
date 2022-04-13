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

import model.Powerstation;

@Path("/Powerstations")

public class PowerstationService {

	Powerstation powerObj = new Powerstation();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPowerstation() {
		return powerObj.readPowerstation();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPowerstation(@FormParam("Stationname") String Stationname,
			@FormParam("Province") String Province, @FormParam("Location") String Location,
			@FormParam("Powergenerated") String Powergenerated) {
		String output = powerObj.insertPowerstation(Stationname, Province, Location, Powergenerated );
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePowerstation(String powerData) {
		// Convert the input string to a JSON object
		JsonObject powerObject = new JsonParser().parse(powerData).getAsJsonObject();
		// Read the values from the JSON object
		String PowerstationID = powerObject.get("PowerstationID").getAsString();
		String Stationname = powerObject.get("Stationname").getAsString();
		String Province = powerObject.get("Province").getAsString();
		String Location = powerObject.get("Location").getAsString();
		String Powergenerated = powerObject.get("Powergenerated").getAsString();
		
		String output = powerObj.updatePowerstation(PowerstationID, Stationname, Province, Location, Powergenerated);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePowerstation(String powerData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(powerData, "", Parser.xmlParser());

		// Read the value from the element <CustomerID>
		String PowerstationID = doc.select("PowerstationID").text();
		String output = powerObj.deletePowerstation(PowerstationID);
		return output;
	}

}

