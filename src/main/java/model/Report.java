package model; 
import java.sql.*; 

public class Report 
{ //A common method to connect to the DB
		private Connection connect(){ 
			
						Connection con = null; 
						
						try{ 
								Class.forName("com.mysql.jdbc.Driver"); 
 
								//Provide the correct details: DBServer/DBName, username, password 
								con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid", "root", ""); 
						} 
						catch (Exception e) {
							e.printStackTrace();
							} 
						
						return con; 
			} 
		
		
		public String insertReport(String reportNo, String category, String units, String reportdescription){ 
			
					String output = ""; 
					
					try
					{ 
						Connection con = connect(); 
						
						if (con == null) 
						{
							return "Error while connecting to the database for inserting."; 
							
						} 
						// create a prepared statement
						
						String query = " insert into consumption_report (`reportID`,`reportNo`,`category`,`units`,`reportDescription`)"+" values (?, ?, ?, ?, ?)"; 
						PreparedStatement preparedStmt = con.prepareStatement(query); 
						// binding values
						preparedStmt.setInt(1, 0); 
						preparedStmt.setString(2, reportNo); 
						preparedStmt.setString(3, category); 
						preparedStmt.setDouble(4, Double.parseDouble(units)); 
						preparedStmt.setString(5, reportdescription); 
						// execute the statement
 
						preparedStmt.execute(); 
						con.close(); 
						output = "Inserted successfully"; 
					} 
					
					catch (Exception e) 
					{ 
						output = "Error while inserting the item."; 
						System.err.println(e.getMessage()); 
					} 
					return output; 
			} 
		
		
		
			public String readReports(){ 
				
					String output = ""; 
					
					try{ 
							Connection con = connect(); 
							if (con == null){
								
								return "Error while connecting to the database for reading."; 
								
								} 
							// Prepare the html table to be displayed
							output = "<table border='1'><tr><th>Report Number</th><th>Customer Category</th>" +
									"<th>Units Consumed</th>" + 
									"<th>Description</th>" +
									"<th>Update</th><th>Remove</th></tr>"; 
 
							String query = "select * from consumption_report"; 
							Statement stmt = con.createStatement(); 
							ResultSet rs = stmt.executeQuery(query); 
							// iterate through the rows in the result set
							while (rs.next()) 
							{ 
									String reportID = Integer.toString(rs.getInt("reportID")); 
									String reportNo = rs.getString("reportNo"); 
									String category = rs.getString("category"); 
									String units = Double.toString(rs.getDouble("units")); 
									String reportDescription = rs.getString("reportDescription"); 
									// Add into the html table
									output += "<tr><td>" + reportNo + "</td>"; 
									output += "<td>" + category + "</td>"; 
									output += "<td>" + units + "</td>"; 
									output += "<td>" + reportDescription + "</td>"; 
									// buttons
									output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
											+ "<td><form method='post' action='consumption_report.jsp'>"
											+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
											+ "<input name='reportID' type='hidden' value='" + reportID 
											+ "'>" + "</form></td></tr>"; 
							} 
								con.close(); 
								// Complete the html table
								output += "</table>"; 
						} 
						catch (Exception e){ 
									output = "Error while reading the items."; 
									System.err.println(e.getMessage()); 
						} 
						return output; 
						} 
			
			
			public String updateReport(String ID, String No, String category, String units, String description){ 
				
					String output = ""; 
					
					try{ 
							Connection con = connect(); 
							if (con == null){
								return "Error while connecting to the database for updating.";
								} 
							// create a prepared statement
							String query = "UPDATE consumption_report SET reportNo=?,category=?,units=?,reportDescription=? WHERE reportID=?"; 
							PreparedStatement preparedStmt = con.prepareStatement(query); 
							// binding values
							preparedStmt.setString(1, No); 
							preparedStmt.setString(2, category); 
							preparedStmt.setDouble(3, Double.parseDouble(units)); 
							preparedStmt.setString(4, description); 
							preparedStmt.setInt(5, Integer.parseInt(ID)); 
							// execute the statement
							preparedStmt.execute(); 
							con.close(); 
							output = "Updated successfully"; 
					} 
					
					catch (Exception e){ 
						
						output = "Error while updating the item."; 
						System.err.println(e.getMessage()); 
						
					} 
					
					return output; 
			} 
			
			
			public String deleteReport(String reportID){ 
				
					String output = ""; 
					
					try{ 
						Connection con = connect(); 
						
						if (con == null){
							return "Error while connecting to the database for deleting."; 
							} 
						// create a prepared statement
						String query = "delete from consumption_report where reportID=?"; 
						PreparedStatement preparedStmt = con.prepareStatement(query); 
						// binding values
						preparedStmt.setInt(1, Integer.parseInt(reportID)); 
						// execute the statement
						preparedStmt.execute(); 
						con.close(); 
						output = "Deleted successfully"; 
					} 
					
					catch (Exception e){ 
						output = "Error while deleting the item."; 
						System.err.println(e.getMessage()); 
					} 
					return output; 
			} 
			
			
			
			
} 
