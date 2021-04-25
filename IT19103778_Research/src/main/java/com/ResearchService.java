package com;


//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document; 

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


import org.jsoup.Jsoup;


import model.Research;
@Path("/research") 
public class ResearchService {

	Research ItemObj = new Research();

	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	 {
	 return ItemObj.readItems();
	 }
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("id") String id,
	 @FormParam("title") String title,
	 @FormParam("description") String description,
	 @FormParam("leader") String leader, 
	 @FormParam("university") String university,
	 @FormParam("published_date") String published_date)
	{
	 String output = ItemObj.insertItem(id, title, description, leader,university,published_date);
	return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String itemData)
	{
	//Converting input string to an XML document
	 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

	//Reading the value from the element <itemID>
	 String itemID = doc.select("itemID").text();
	 String output = ItemObj.deleteItem(itemID);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String itemData)
	{
	//Converting input string to a JSON obj
		
	 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
	//Reading values from the JSON obj
	 
	 String id = itemObject.get("id").getAsString();
	 String title = itemObject.get("title").getAsString();
	 String description = itemObject.get("description").getAsString();
	 String leader = itemObject.get("leader").getAsString();
	 String university = itemObject.get("university").getAsString();
	 String published_date = itemObject.get("published_date").getAsString();
	 String output = ItemObj.updateItem(id, title, description, leader, university,published_date);
	return output;
	}
	
	
	
	}
	

