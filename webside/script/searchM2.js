//___________________________________ Get elements _____________________________
var prefix = "http://schema.org/";



function createWHERE() {
  // arrays that store selected checkboxes values and names
  var values = [];
  var names = [];
  var list = [];
  var res = [];
  // gets all the input tags in frm, and their number
  var input = document.getElementsByTagName('input');

  for(var i = 0; i < input.length; i++) {
    if(input[i].type == 'checkbox' && input[i].checked == true) values.push(input[i].value);
  }

  for(var i = 0; i < input.length; i++) {
    if(input[i].type == 'checkbox' && input[i].checked == true) names.push(input[i].name);
  }


  for(x in values){

 if(names[x] == "dayOfWeek"){
	  var i =2;
      list.push("&quot" + values[x] + "&quot");
	 	 if (i++ == list.length-1){
	  res.push("?timer a:dayOfWeek ?value .  <br>FILTER(?value IN("
    + list.toString() + ")) . <br>");
	  }
      console.log(list);
    }


    if(names[x] == "starttid"){
      res.push("?timer a:" + names[x] + " " + values[x] );
    }
    if(names[x] == "type_trening"){
      res.push("?timer a:" + names[x] + " " + values[x] );
    }
    if(names[x] == "duration"){
		if (values[x] == "min30"){
		res.push("FILTER (?varighet <= " + '"30"'+ ") . <br>");}
		if (values[x] == "plus60"){
		res.push("FILTER (?varighet < " + '"60"'+ ") . <br>");}
		if (values[x] == "30til60"){
		res.push("FILTER (?varighet <= " + '"60"'+ " && ?varighet > " + '"30"' + ") . <br>");}
    }
  }
  //console.log(res);
//return res;
return res.join(" . <br>");

}


//___________________________________ Create query _____________________________


// when button is clicked, return values
document.getElementById('btn').onclick = function createQuery(){

  var myQuery = ("prefix a: &lt" + prefix +"&gt <br> SELECT ?dato ?timeNavn ?starter ?sted ?dag ?varighet ?instructor ?location ?liktSom<br>"
    + " WHERE { <br> "
    + createWHERE() +
    " ?timer a:dayOfWeek ?dag . <br> ?timer a:duration ?varighet . <br> ?timer a:legalName ?sted . <br> ?timer a:title ?timeNavn . <br> ?timer a:startTime ?starter . <br> ?timer a:location ?location . <br> ?timer a:instructor ?instructor . <br> ?timer a:typeOf ?liktSom . <br> ?timer a:startDate ?dato }" );

document.getElementById("demo").innerHTML = myQuery;

}

/*
SELECT ?timeNavn ?starter ?sted ?dag ?varighet
WHERE {
?timer <http://schema.org/sameAs> <http://example/Sats/Yoga> .
?timer <http://schema.org/dayOfWeek>   "tirsdag" .
?timer <http://schema.org/dayOfWeek> ?dag .
?timer <http://schema.org/duration> ?varighet .
?timer <http://schema.org/legalName> ?sted .
?timer <http://schema.org/title> ?timeNavn .
?timer <http://schema.org/startTime> ?starter .
}
*/



//___________________________________ Run Query ________________________________

/*var rdfstore = require('rdfstore'), fs = require('fs');

rdfstore.create(function(store){
  var rdf = fs.readFileSync('ttl/StudentSenter.ttl').toString();
  store.load('text/turtle', rdf, function(s,d){
    console.log(s,d);
    store.execute("WHERE {" + createWHERE() + "}", function(success, results){
      console.log(success, results);
    });
  });
});*/


//http://*host*/dataset/query -- the SPARQL query endpoint.
//http://*host*/dataset/update -- the SPARQL Update language endpoint.
//http://*host*/dataset/data -- the SPARQL Graph Store Protocol endpoint.
//http://*host*/dataset/upload -- the file upload endpoint.


//var ttl = ('ttl/StudentSenter.ttl');
//ttl.start();
//var state = JSON.parse(localStorage.getItem('ttlInfo'));
//ttl.reset(state); // drop existing state and replace it with loaded state




//___________________________________ Prtin Results ____________________________
