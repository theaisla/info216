// read input
var input = get_query_string_parameters();

// create query parametres

// run query
function query(e) {

}

// return result
//___________________________________ Run code ________________________________________________________________

window.onload = function() {
	query_params = get_query_string_parameters();
	search_results = movies_object;

	if (query_params.film_title) {
		res.innerHTML = ("<h2> Filmtitler som inneholder "  + query_params.film_title +  ": <h2>");
		query(input);
		}

	// hvis resultarray er tomt, skriv det på resultatsiden
	if(results.length == 0){
		nores.innerHTML = ("<p>Beklager, fant ikke noen resultater</p>");
	}
}
