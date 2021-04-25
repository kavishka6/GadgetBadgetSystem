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

import model.Payment;

@Path("/payment")

public class PaymentService {

	Payment payobj = new Payment();	
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPayments()
	 {
		 return payobj.readPayments();
	 }
	 
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("orderid") int orderid,
	 @FormParam("amount") float amount)
	 
	{
	 String out_put = payobj.insertPayment(orderid, amount);
	return out_put;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePayment(String payData)
	{
	//Convert input string to JSON object
	 JsonObject itemObject = new JsonParser().parse(payData).getAsJsonObject();
	//Read values from JSON object
	 String payment_ID = itemObject.get("payment_ID").getAsString();
	 String orderID = itemObject.get("orderID").getAsString();
	 String total = itemObject.get("TotalAmount").getAsString();
	 String out_put = payobj.updatePayment(payment_ID, total, orderID);
	return out_put;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePayment(String payData)
	{
	//Convert input string to XML document
	 Document doc = Jsoup.parse(payData, "", Parser.xmlParser());

	//Read value from element <itemID>
	 String payment_ID = doc.select("payment_ID").text();
	 String out_put = payobj.deletePayment(payment_ID);
	return out_put;
	}
	
	
}
