function createWHERE() {
  // arrays that store selected checkboxes values and names
  var values = []; var names = [];
  var listTime = [];   var listDay = [];  var listDuration = []; var listTitle = [];
  var res = [];
  var result = Array.from(new Set(res));

  // gets all the input tags in frm, and their number
  var input = document.getElementsByTagName('input');

  for(var i = 0; i < input.length; i++) {
    if(input[i].type == 'checkbox' && input[i].checked == true) values.push(input[i].value);
  }

  for(var i = 0; i < input.length; i++) {
    if(input[i].type == 'checkbox' && input[i].checked == true) names.push(input[i].name);
  }

  //______ Find values _____
  for(x in values){
    if(names[x] == "dayOfWeek"){
      listDay.push("&quot" + values[x] + "&quot");
    }

    if(names[x] == "startTime"){
      listTime.push("&quot" + values[x] + "&quot");
    }
  }

//______ Print Query Text _____

for (x in names){
  if(names[x] == "dayOfWeek"){
    res.push("?timer a:dayOfWeek ?value .  <br>FILTER(?value IN("
    + listDay + "))");
  }

  if(names[x] == "startTime"){
    res.push("?timer a:startTime ?value .  <br>FILTER(?value IN("
    + listTime + "))");
  }
}



  return result.join(" . <br>");

}


//___________________________________ Create query _____________________________

// when button is clicked, return query
document.getElementById('btn').onclick = function createQuery(){

  var myQuery = ("prefix a: &lthttp://schema.org/&gt <br> SELECT ?timeNavn ?starter ?sted ?dag ?varighet <br>"
    + " WHERE { <br> ?timer a:dayOfWeek ?dag . <br> ?timer a:duration ?varighet . <br> ?timer a:legalName ?sted . <br> ?timer a:title ?timeNavn . <br> ?timer a:startTime ?starter . <br><br>"
    + createWHERE() + "<br>}");

document.getElementById("demo").innerHTML = myQuery;

}

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
