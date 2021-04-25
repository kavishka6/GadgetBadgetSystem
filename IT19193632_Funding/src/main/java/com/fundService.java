package com;


import model.fund;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/fund")
public class fundService
{
fund fundObj = new fund();

@POST
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)

public String insertfund(@FormParam("Fund_id") String Fund_id,
@FormParam("Funders_name") String Funders_name,
@FormParam("Project_name") String Project_name,
@FormParam("Amount") String Amount)
{
String output = fundObj.insertfund(Fund_id, Funders_name, Project_name, Amount);
return output;
}


}

