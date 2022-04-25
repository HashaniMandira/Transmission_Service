package com;

import model.PowerCutSchedule;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


@Path("/PowerCutSchedule")

public class PowerCutScheduleService {

	
	PowerCutSchedule powercutscheduleObj = new PowerCutSchedule();
	 
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPowerCutSchedules()
	 {
	    return powercutscheduleObj.readPowerCutSchedules(); 
	 }
	 
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPowerCutSchedule(@FormParam("area") String area,
	 @FormParam("starttime") String starttime,
	 @FormParam("endtime") String endtime,
	 @FormParam("type") String type)
	{
	 String output = powercutscheduleObj.insertPowerCutSchedule(area, starttime, endtime, type);
	 return output;
	}
	 

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePowerCutSchedule(String powercutscheduleData)
	{
	//Convert the input string to a JSON object
	 JsonObject powercutscheduleObject = new JsonParser().parse(powercutscheduleData).getAsJsonObject();
	//Read the values from the JSON object
	 String powercutscheduleID = powercutscheduleObject.get("powercutscheduleID").getAsString();
	 String area = powercutscheduleObject.get("area").getAsString();
	 String starttime = powercutscheduleObject.get("starttime").getAsString();
	 String endtime = powercutscheduleObject.get("endtime").getAsString();
	 String type = powercutscheduleObject.get("type").getAsString();
	 String output = powercutscheduleObj.updatePowerCutSchedule(powercutscheduleID, area, starttime, endtime, type);
	return output;
	}
	 

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePowerCutSchedule(String powercutscheduleData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(powercutscheduleData, "", Parser.xmlParser());
	 
	//Read the value from the element <itemID>
	 String powercutscheduleID = doc.select("powercutscheduleID").text();
	 String output = powercutscheduleObj.deletePowerCutSchedule(powercutscheduleID);
	return output;
	}
	 
	
}
