package com; 

import model.Report; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 




@Path("/Reports") 
public class ReportService{ 
	
	Report reportObj = new Report(); 
			
			
			@GET
			@Path("/") 
			@Produces(MediaType.TEXT_HTML) 
			public String readReports() 
			 { 
				return reportObj.readReports(); 
			}
			
			@POST
			@Path("/") 
			@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
			@Produces(MediaType.TEXT_PLAIN) 
			public String insertReport(@FormParam("reportNo") String reportNo, 
									 @FormParam("category") String category, 
									 @FormParam("units") String units, 
									 @FormParam("reportDescription") String reportDescription){ 
				
				String output = reportObj.insertReport(reportNo, category, units, reportDescription); 
				return output; 
				
			}
			
			
			@PUT
			@Path("/") 
			@Consumes(MediaType.APPLICATION_JSON) 
			@Produces(MediaType.TEXT_PLAIN) 
			public String updateReport(String reportData) 
			{ 
				//Convert the input string to a JSON object 
				JsonObject reportObject = new JsonParser().parse(reportData).getAsJsonObject(); 
				
				//Read the values from the JSON object
				String reportID = reportObject.get("reportID").getAsString(); 
				String reportNo = reportObject.get("reportNo").getAsString(); 
				String category = reportObject.get("category").getAsString(); 
				String units = reportObject.get("units").getAsString(); 
				String reportDescription = reportObject.get("reportDescription").getAsString(); 
				String output = reportObj.updateReport(reportID, reportNo, category, units, reportDescription); 
				return output;
				
			}
			
			
			@DELETE
			@Path("/") 
			@Consumes(MediaType.APPLICATION_XML) 
			@Produces(MediaType.TEXT_PLAIN)
			public String deleteReport(String reportData) 
			{ 
				//Convert the input string to an XML document
				Document doc = Jsoup.parse(reportData, "", Parser.xmlParser()); 
			 
				//Read the value from the element <itemID>
				String reportID = doc.select("reportID").text(); 
				String output = reportObj.deleteReport(reportID); 
				return output; 
			}

			
					
}