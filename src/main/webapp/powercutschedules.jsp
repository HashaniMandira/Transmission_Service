<%@page import="com.PowerCutSchedule"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Power Cut Schedules Management</title>
		<link rel="stylesheet" href="Views/bootstrap.min.css">
		<script src="Components/jquery-3.6.0.min.js"></script>
		<script src="Components/powercutschedules.js"></script>
	</head>
	
	<body> 
		<div class="container"><div class="row"><div class="col-6"> 
			<h1>Power Cut Schedules Management</h1>
			
			<form id="formPowerCutSchedule" name="formPowerCutSchedule" >
 				Area: 
 					<input id="area" name="area" type="text" 
 						class="form-control form-control-sm">
 					<br> 
 				Power cut schedule start time: 
 					<input id="starttime" name="starttime" type="text" 
 						class="form-control form-control-sm">
 					<br> 
 				Power cut schedule end time: 
 					<input id="endtime" name="endtime" type="text" 
 						class="form-control form-control-sm">
 					<br> 
 				Power cut schedule type: 
 					<input id="type" name="type" type="text" 
 						class="form-control form-control-sm">
 					<br>
 				<input id="btnSave" name="btnSave" type="button" value="Save" 
 					class="btn btn-primary">
 				<input type="hidden" id="hidPowerCutScheduleIDSave" name="hidPowerCutScheduleIDSave" value="">
		</form>

		<div id="alertSuccess" class="alert alert-success"></div>
		<div id="alertError" class="alert alert-danger"></div>
		<br>
		<div id="divPowerCutSchedulesGrid">
 			<%
 				PowerCutSchedule powercutscheduleObj = new PowerCutSchedule(); 
 				out.print(powercutscheduleObj.readPowerCutSchedules()); 
 			%>
		</div>
		</div> </div> </div> 
	</body>
</html>