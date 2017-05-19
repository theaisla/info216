//___________________________________ Get elements _____________________________
var uri = "http://schema.org/";
//var prefix = encodeURI(uri);


var uri_enc = encodeURIComponent(uri);
var uri_dec = decodeURIComponent(uri_enc);
var prefix = uri_dec;


function createWHERE() {
  // arrays that store selected checkboxes values and names
  var values = [];
  var names = [];
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
      res.push("?timer a:" + names[x] + " &quot" + values[x] + "&quot . <br>");
    }


    if(names[x] == "starttid"){
      res.push("?timer a:" + names[x] + " " + values[x] );
    }
    if(names[x] == "type_trening"){
      res.push("?timer a:" + names[x] + " " + values[x] );
    }
    if(names[x] == "varighet"){
      res.push("?timer a:" + names[x] + " " + values[x] );
    }
    if(names[x] == "fasiliteter"){
      res.push("?treningssenter a:" + names[x] + " " + values[x] );
    }
  }
  console.log(res);
//return res;
return res.join(" . <br>");

}


//___________________________________ Create query _____________________________


// when button is clicked, return values
document.getElementById('btn').onclick = function createQuery(){

  var myQuery = ("prefix a: &lt" + prefix +"&gt <br> SELECT ?timeNavn ?starter ?sted ?dag ?varighet <br>"
    + " WHERE { <br> "
    + createWHERE() +
    "?timer a:dayOfWeek ?dag . <br> ?timer a:duration ?varighet . <br> ?timer a:legalName ?sted . <br> ?timer a:title ?timeNavn . <br> ?timer a:startTime ?starter . <br>}" );

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
