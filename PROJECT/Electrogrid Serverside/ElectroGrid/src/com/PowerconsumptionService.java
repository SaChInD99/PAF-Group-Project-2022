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

import model.Powerconsumption;

@Path("/Consumptions")

public class PowerconsumptionService {

	Powerconsumption consumptionObj = new Powerconsumption();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readConsumption() {
		return consumptionObj.readConsumption();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertConsumption(@FormParam("CustomerID") String CustomerID,
			@FormParam("Present_reading") String Present_reading, @FormParam("Previous_reading") String Previous_reading,
			@FormParam("Consumptionunit") String Consumptionunit, @FormParam("Tax") String Tax, @FormParam("Due_date") String Due_date) {
		String output = consumptionObj.insertConsumption(CustomerID, Present_reading, Previous_reading, Consumptionunit, Tax, Due_date );
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateConsumption(String consumptionData) {
		// Convert the input string to a JSON object
		JsonObject consumptionObject = new JsonParser().parse(consumptionData).getAsJsonObject();
		// Read the values from the JSON object
		String ConsumptionID = consumptionObject.get("ConsumptionID").getAsString();
		String CustomerID = consumptionObject.get("CustomerID").getAsString();
		String Present_reading = consumptionObject.get("Present_reading").getAsString();
		String Previous_reading = consumptionObject.get("Previous_reading").getAsString();
		String Consumptionunit = consumptionObject.get("Consumptionunit").getAsString();
		String Tax = consumptionObject.get("Tax").getAsString();
		String Due_date = consumptionObject.get("Due_date").getAsString();
		String output = consumptionObj.updateConsumption(ConsumptionID, CustomerID, Present_reading, Previous_reading,
				Consumptionunit, Tax, Due_date );
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteConsumption(String consumptionData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(consumptionData, "", Parser.xmlParser());

		// Read the value from the element <ConsumptionID>
		String ConsumptionID = doc.select("ConsumptionID").text();
		String output = consumptionObj.deleteConsumption(ConsumptionID);
		return output;
	}

}

