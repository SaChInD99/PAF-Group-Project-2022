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

import model.Feedback;

@Path("/Feedbacks")

public class FeedbackService {

	Feedback feedbackObj = new Feedback();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readfeedback() {
		return feedbackObj.readfeedback();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertfeedback(@FormParam("CustomerName") String CustomerName,
			@FormParam("CustomerEmail") String CustomerEmail, @FormParam("Feedback") String feedback) {
		String output = feedbackObj.insertfeedback(CustomerName, CustomerEmail, feedback );
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatefeedback(String feedbackData) {
		// Convert the input string to a JSON object
		JsonObject feedbackObject = new JsonParser().parse(feedbackData).getAsJsonObject();
		// Read the values from the JSON object
		String FeedbackID = feedbackObject.get("FeedbackID").getAsString();
		String CustomerName = feedbackObject.get("CustomerName").getAsString();
		String CustomerEmail = feedbackObject.get("CustomerEmail").getAsString();
		String feedback = feedbackObject.get("Feedback").getAsString();
		String output = feedbackObj.updateFeedback(FeedbackID, CustomerName, CustomerEmail, feedback);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteFeedback(String feedbackData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(feedbackData, "", Parser.xmlParser());

		// Read the value from the element <CustomerID>
		String FeedbackID = doc.select("FeedbackID").text();
		String output = feedbackObj.deleteFeedback(FeedbackID);
		return output;
	}

}

