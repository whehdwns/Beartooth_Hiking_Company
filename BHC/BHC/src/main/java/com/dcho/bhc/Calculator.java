package com.dcho.bhc;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dcho.bhc.BookingDay;
import com.dcho.bhc.Rates;
import com.dcho.bhc.Rates.HIKE;


import javax.ws.rs.Produces;

@Path("bhc")
public class Calculator {
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("toJson")
	public String getObject() {
		return "WORKING";
	}
	
    @POST
    @Path("quoterate")
    @Consumes(MediaType.APPLICATION_JSON) 
	public Response setObject(Rate_Request rate_request) {
    	String quoterateresult= rate_result(rate_request.getDate(), rate_request.getParty(), rate_request.getTour(), rate_request.getDuration());
    	Request_Json request_json = new Request_Json();
    	request_json.setText(quoterateresult);
    	return Response.ok(request_json, MediaType.APPLICATION_JSON).build();
    }
    
    public String rate_result(String date, String party, String tour, String duration) {
        int hike_duration =0;	
        String quoterateresult = null;
        String[] hikeOptions = {"Gardiner Lake", "Hellroaring Plateau", "The Beaten Path"};
    	if (date == null) {
    		quoterateresult= "Error : Please choose start Date";
    	}
    	if (party == null) {
    		quoterateresult = "Error : Please choose party size ";
    	}
    	if (tour == null) {
    		quoterateresult = " Error : Please choose your tour option ";
    	}
    	if (duration == null) {
    		quoterateresult = "Error : Please choose duration";
    	}else {
            try {
				String[] start_Date_split = date.split("-");
				int year = Integer.parseInt(start_Date_split[0]);
				int month = Integer.parseInt(start_Date_split[1]);
				int day = Integer.parseInt(start_Date_split[2]);
				try {
					int party_quantity = Integer.parseInt(party);
					try {
						Rates hikes_options = null;
						if (tour.equals(hikeOptions[0])) {
							hikes_options = new Rates(HIKE.GARDINER);     
						}
						if (tour.equals(hikeOptions[1])) {
							hikes_options = new Rates(HIKE.HELLROARING);
						}
						if (tour.equals(hikeOptions[2])) {
							hikes_options = new Rates(HIKE.BEATEN);   
						}
						hike_duration = Integer.parseInt(duration);
						try {
							BookingDay startDay = new BookingDay(year, month, day);
							BookingDay endDay = new BookingDay(year, month, day+hike_duration);
							//Check if the start date is valid
							if (startDay.isValidDate()) {
								hikes_options.setBeginDate(startDay);
								//Check if it is valid duration of the reservation
								if(hikes_options.setDuration(hike_duration)) {
									//Determine if the entered dates are valid
									if(hikes_options.isValidDates()){
										if((0<party_quantity) && (party_quantity<11) ) {
											double total_cost = hikes_options.getCost() * party_quantity;
											quoterateresult=  "Total Cost of " +
															  tour +" hike for " +
															  hike_duration + " days" +
															  " starting from "+ startDay + 
															  " with " + party +" people" +
															  " will cost $"+ total_cost +
															  " ( $"+hikes_options.getCost()+" Per person)";
//												"Hike Option is " + tourOptions + 
//												", Start Date is " + startDay + 
//												" , End Date is " + endDay +
//												", Weekdays is " + hikes_options.getNormalDays() +
//												", Weekends is " + hikes_options.getPremiumDays() +
//												", Duration is " +hike_duration +
//												", Party Size is " +party_size +
//												", Cost is "+ hikes_options.getCost() +
//												", Total Cost is "+ total_cost;	
										}else {
											quoterateresult= "Error : The number of people in the part must be between 1 and 10.";
										}						
									}else {
										quoterateresult= "Error : The dates chosen are invalid! (Valid Season:6/1~9/30)";
									}
								}else {
									quoterateresult = "Error :: Your duration is invalid! (Please check the table above)";
								}
							}else {
								quoterateresult = "Error : The dates chosen are invalid! (Valid Season:6/1~9/30)";
							}
						}catch(Exception e1) {
							quoterateresult = "Error : Incorrect Date! Date Format:YYYY-MM-DD ";
						}
					}catch(Exception e) {
						quoterateresult = "Error : Please choose Hike Options!";
					}
				}catch(Exception e) {
					quoterateresult = "Error : Please choose Party Size!";
	            }
            }catch(Exception e) {
            	quoterateresult = "Error : Please choose Date!";
            }
    	}
    	return quoterateresult;
    	
    }
}
