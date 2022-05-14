package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement; 

public class PowerCutSchedule {

	//A common method to connect to the DB
	private Connection connect()
	 {
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
	 
			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:4306/electrogrid", "root", "");
		}
		catch (Exception e)
			{e.printStackTrace();}
		return con;
	 }
	
	
	//insert PowerCut Schedule
			public String insertPowerCutSchedule(String area, String starttime, String endtime, String type)
			 {
				String output = "";
				try
				{
					Connection con = connect();
					if (con == null)
					{return "Error while connecting to the database for inserting."; }
					
					// create a prepared statement
					String query = " insert into power_cut_schedule(`powercutscheduleID`,`area`,`starttime`,`endtime`,`type`)"
							+ " values (?, ?, ?, ?, ?)";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					
					// binding values
					preparedStmt.setInt(1, 0);
					preparedStmt.setString(2, area);
					preparedStmt.setString(3, starttime);
					preparedStmt.setString(4, endtime);
					preparedStmt.setString(5, type);
					
					// execute the statement

					preparedStmt.execute();
					con.close();

					String newPowerCutSchedules = readPowerCutSchedules(); 
					output = "{\"status\":\"success\",\"data\":\""+newPowerCutSchedules+"\"}"; 
				}
				catch (Exception e)
				{
					output = "{\"status\":\"error\", \"data\":\"Error while inserting the powercut schedule.\"}"; 
					System.err.println(e.getMessage());
				}
				return output;
			 }
			
			
			//read PowerCut Schedule
			
			public String readPowerCutSchedules()
			 {
				String output = "";
				try
				{
					Connection con = connect();
					if (con == null)
					{
						return "Error while connecting to the database for reading."; 
					}
					
					// Prepare the html table to be displayed
					output = "<table border='1' class='table'><tr><th>Area</th> "   
							+ "<th>Powercut Start Time</th>" 
							+ "<th>Powercut End Time</th>" 
							+ "<th>Type</th>" 
							+ "<th>Update</th><th>Remove</th></tr>";
			 
					String query = "select * from power_cut_schedule";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					
					// iterate through the rows in the result set
					while (rs.next())
					{
						String powercutscheduleID = Integer.toString(rs.getInt("powercutscheduleID"));
						String area = rs.getString("area");
						String starttime = rs.getString("starttime");
						String endtime = rs.getString("endtime");
						String type = rs.getString("type");
						
						// Add into the html table
						output += "<tr align=Center><td><input id='hidPowerCutScheduleIDUpdate' name='hidPowerCutScheduleIDUpdate' type='hidden' value='"+powercutscheduleID+"'>" + area + "</td>";
						output += "<td>" + starttime + "</td>";
						output += "<td>" + endtime + "</td>";
						output += "<td>" + type + "</td>";
						
						// buttons
						output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn-secondary' data-powercutscheduleid='" + powercutscheduleID + "'></td>"
								+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-powercutscheduleid='" + powercutscheduleID + "'></td>"
								+"</tr>"; 
					}
					con.close();
					// Complete the html table
					output += "</table>";
				}
				catch (Exception e)
				{
					output = "Error while reading the powercut schedules.";
					System.err.println(e.getMessage());
				}
				return output;
			 }
			
			
			
			//update PowerCut Schedule
			
			public String updatePowerCutSchedule(String PowerCutScheduleID, String area, String starttime, String endtime, String type)
			{
			     String output = "";
			     try
			     {
			    	 Connection con = connect();
			    	 if (con == null)
			    	 {return "Error while connecting to the database for updating."; }
			    	 
			    	 // create a prepared statement
			    	 String query = "UPDATE power_cut_schedule SET area=?, starttime=?, endtime=?, type=? WHERE powercutscheduleID=?";
			    	 PreparedStatement preparedStmt = con.prepareStatement(query);
			    	 
			    	 // binding values
			    	 preparedStmt.setString(1, area);
			    	 preparedStmt.setString(2, starttime);
			    	 preparedStmt.setString(3, endtime);
			    	 preparedStmt.setString(4, type);
			    	 preparedStmt.setInt(5, Integer.parseInt(PowerCutScheduleID));
			    	 
			    	 // execute the statement
			    	 preparedStmt.execute();
			    	 con.close();

					 String newPowerCutSchedules = readPowerCutSchedules(); 
					 output = "{\"status\":\"success\",\"data\":\""+newPowerCutSchedules+"\"}"; 
			     }
			     catch (Exception e)
			     {
			    	 output = "{\"status\":\"error\",\"data\":\"Error while updating the powercut schedule.\"}"; 
			    	 System.err.println(e.getMessage());
			     }
			     return output;
			  }

			
			
			//delete PowerCut Schedule
		    public String deletePowerCutSchedule(String powercutscheduleID)
		     {
		    	String output = "";
		    	try
		    	{
		    		Connection con = connect();
		    		if (con == null)
		    		{return "Error while connecting to the database for deleting."; }
		    		
		    		// create a prepared statement
		    		String query = "delete from power_cut_schedule where powercutscheduleID=?";
		    		PreparedStatement preparedStmt = con.prepareStatement(query);
		    		
		    		// binding values
		    		preparedStmt.setInt(1, Integer.parseInt(powercutscheduleID));
		    		// execute the statement
		    		preparedStmt.execute();
		    		con.close();
		    		String newPowerCutSchedules = readPowerCutSchedules(); 
					output = "{\"status\":\"success\",\"data\":\""+newPowerCutSchedules+"\"}"; 
		    	}
		    	catch (Exception e)
		    	{
		    		output = "{\"status\":\"error\",\"data\":\"Error while deleting the powercut schedule...\"}";
		    		System.err.println(e.getMessage());
		    	}
		    	return output;
		     }
}
