package com;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
import model.User;
//For XML
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

@Path("/Users") 
public class UserManagementService {
	 User userObj = new User();

	//Get....................................................
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems(){
		return userObj.readUsers();
	}
	
	//Post...................................................
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem( @FormParam("name") String name,@FormParam("mail") String mail,@FormParam("type") String type ){
		String output = userObj.insertUsers(name, mail, type);
		return output;
	}
	
	//Delete.......................................................
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String userData){
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(userData, "", Parser.xmlParser());

		//Read the value from the element <itemID>
		String userID = doc.select("userID").text();
		String output = userObj.deleteUsers(userID);
		return output;
	}
	
	//Put..........................................................
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String userData){
		//Convert the input string to a JSON object
		 JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject();
		//Read the values from the JSON object
		 String id = userObject.get("id").getAsString();
		 String name = userObject.get("name").getAsString();
		 String mail = userObject.get("mail").getAsString();
		 String type = userObject.get("type").getAsString();
	
		 String output = userObj.updateUsers(id, name, mail, type);
		 
		 return output;
	}

}
