$(document).on("click", "#btnSave", function(event)
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validatePowerCutScheduleForm(); 

if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 

// If valid------------------------
var type = ($("#hidPowerCutScheduleIDSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "PowerCutSchedulesAPI", 
 type : type, 
 data : $("#formPowerCutSchedule").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onPowerCutSceduleSaveComplete(response.responseText, status); 
 } 
 }); 
});

function onPowerCutSceduleSaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divPowerCutSchedulesGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 }
$("#hidPowerCutScheduleIDSave").val(""); 
$("#formPowerCutSchedule")[0].reset(); 
}


// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
		{ 
		$("#hidPowerCutScheduleIDSave").val($(this).data("powercutscheduleid")); 
		 $("#area").val($(this).closest("tr").find('td:eq(0)').text()); 
		 $("#starttime").val($(this).closest("tr").find('td:eq(1)').text()); 
		 $("#endtime").val($(this).closest("tr").find('td:eq(2)').text()); 
		 $("#type").val($(this).closest("tr").find('td:eq(3)').text()); 
		});




$(document).on("click", ".btnRemove", function(event)
		{ 
		 $.ajax( 
		 { 
		 url : "PowerCutSchedulesAPI", 
		 type : "DELETE", 
		 data : "powercutscheduleID=" + $(this).data("powercutscheduleid"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onPowerCutScheduleDeleteComplete(response.responseText, status); 
		 } 
		 }); 
		});
		
function onPowerCutScheduleDeleteComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divPowerCutSchedulesGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}


// CLIENT-MODEL================================================================
function validatePowerCutScheduleForm()
{
	// AREA
	if ($("#area").val().trim() == "")
	{
	return "Insert Area.";
	}
	// START TIME
	if ($("#starttime").val().trim() == "")
	{
	return "Insert Power Cut Schedule Start Time.";
}

	// END TIME-------------------------------
	if ($("#endtime").val().trim() == "")
	return "Insert Power Cut Schedule End Time.";

			
	// TYPE------------------------
	if ($("#type").val().trim() == ""){
	return "Insert Power Cut Schedule Type.";
}
	return true;
}