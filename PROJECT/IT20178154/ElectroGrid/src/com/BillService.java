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

import model.Bill;

@Path("/Bills")

public class BillService {

	Bill billObj = new Bill();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readBill() {
		return billObj.readBill();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertBill(@FormParam("CustomerID") String CustomerID,
			@FormParam("Billamount") String Billamount, @FormParam("Billdate") String Billdate,
			@FormParam("Paidamount") String Paidamount, @FormParam("Status") String Status) {
		String output = billObj.insertBill(CustomerID, Billamount, Billdate, Paidamount, Status );
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateBill(String billData) {
		// Convert the input string to a JSON object
		JsonObject billObject = new JsonParser().parse(billData).getAsJsonObject();
		// Read the values from the JSON object
		String Billno = billObject.get("Billno").getAsString();
		String CustomerID = billObject.get("CustomerID").getAsString();
		String Billamount = billObject.get("Billamount").getAsString();
		String Billdate = billObject.get("Billdate").getAsString();
		String Paidamount = billObject.get("Paidamount").getAsString();
		String Status = billObject.get("Status").getAsString();
		String output = billObj.updateBill(Billno, CustomerID, Billamount, Billdate, Paidamount,
				Status);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteBill(String billData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(billData, "", Parser.xmlParser());

		// Read the value from the element <CustomerID>
		String Billno = doc.select("Billno").text();
		String output = billObj.deleteBill(Billno);
		return output;
	}

}

