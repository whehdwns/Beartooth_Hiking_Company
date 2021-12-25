function checkPartySize(){
	let value = parseInt(document.getElementById("party").value)
	if (value > 10){
		//document.getElementById("party_quantity").value = 10
		alert("Party size shouldn't be greater than 10")
	}
	if (value < 1){
		//document.getElementById("party_quantity").value = 1
		alert("Party size shouldn't be less than 1")
	}
}

function validDate(){
	let date = document.getElementById("date").value
	let date_split = date.split("-")
	var today = new Date();
	input_year = parseInt(date_split[0])
	input_month = parseInt(date_split[1])
	input_date = parseInt(date_split[2])

	if(input_year < today.getFullYear() ||
	   input_year === today.getFullYear() &&
	   (input_month < today.getMonth()+1 || input_month === today.getMonth()+1 && 
		input_date  < today.getDate())){
		document.getElementById("date").value = new Date()
		alert("Unable to select a start date before today")
	}
	if(	date_split[0] > 2025){
		document.getElementById("date").value = new Date()
		alert("You can't reserve for the date so far in advance.")
	}
}

function hike_check(){
	let tour_options = document.getElementById("tour").value
	console.log(tour_options)
	if(tour_options==""){
		alert("Please choose Your Hikes")
	}
}

window.onload = function(){
	var hike_list = {
		"Gardiner Lake" : ['3', '5'],
		"Hellroaring Plateau" :  ['2', '3', '4'],
		"The Beaten Path" :  ['5', '7']
	};
	tours = document.getElementById("tour");
	durations = document.getElementById("duration");

	for (key in hike_list) {
		tours.innerHTML = tours.innerHTML + '<option>'+ key +'</option>';
	}

	tours.addEventListener('change', function check_Hike_Duration(e){
		durations.innerHTML = '';
		itm = e.target.value;
		if(itm in hike_list){
			for (i = 0; i < hike_list[itm].length; i++) {
				durations.innerHTML = durations.innerHTML + '<option>'+ hike_list[itm][i] +'</option>';
			}
		}
	});
}
