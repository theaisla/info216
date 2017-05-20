const BASE_URL = 'http://localhost:3030/jue/?query=';

const PREFIXES = [	'prefix a: <http://schema.org/>',
					'prefix b: <http://findmyfitness/>'].join("");


const USER_QUERY = [
	PREFIXES,
	'	SELECT DISTINCT * ',
	'WHERE {',
		'?timer a:dayOfWeek ?dag .',
	  '  ?timer a:duration ?varighet .',
	 '    	?timer a:title ?timeNavn .?timer a:title "Yoga" .',
	'}'
].join("");

const USER_VITALS_QUERY = (yoga) => {
return `${PREFIXES}
		SELECT DISTINCT * 
		WHERE { 
		?timer a:dayOfWeek ?dag . 
		?timer a:duration ?varighet . 
		?timer a:legalName ?sted . 
		?timer a:title ?timeNavn .
		?timer a:startTime ?starter . 
		}`;
		
}


var sparql = {

	getUsers: () => {
		return BASE_URL + encodeURIComponent(USER_QUERY);
	},

	getUserVitals: (yoga) => {
		return BASE_URL + encodeURIComponent(USER_VITALS_QUERY(yoga));
	},

}

  console.log("result");