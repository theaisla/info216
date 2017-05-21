// arrays that store selected checkboxes values and names
var values = []; var names = [];
var listTime = [];   var listDay = [];  var listDuration = []; var listTitle = [];
var res = [];

// gets all the input tags in frm, and their number
var input = document.getElementsByTagName('input');

function findValues() {
  // Find values
  for(x in values){
    if(names[x] == "dayOfWeek"){
      listDay.push("'" + values[x] + "'");
    }

    if(names[x] == "startTime"){
      if (values[x] == "f10"){
        listTime.push("regex(?starter,'^06:') || regex(?starter,'07:') || regex(?starter,'^08:') || regex(?starter,'09:')");}
      if (values[x] == "10til12"){
        listTime.push("regex(?starter,'^10:') || regex(?starter,'11:') || regex(?starter,'^12:')");}
      if (values[x] == "13til15"){
        listTime.push("regex(?starter,'^13:') || regex(?starter,'14:') || regex(?starter,'15:')");}
      if (values[x] == "16til18"){
        listTime.push("regex(?starter,'^16:') || regex(?starter,'17:') || regex(?starter,'^18:')");}
      if (values[x] == "etter19"){
        listTime.push("regex(?starter,'^19:') || regex(?starter,'20:') || regex(?starter,'^21:') || regex(?starter,'22')");}
    }

    if(names[x] == "sameAs"){
      listTitle.push("'" + values[x] + "'");
    }

    if(names[x] == "duration"){
      if (values[x] == "min29"){
        listDuration.push("?varighet <= '29' ");}
      if (values[x] == "30til59"){
        listDuration.push("?varighet <= '59' && ?varighet > '30'");}
      if (values[x] == "plus60"){
        listDuration.push("?varighet > '60' || ?varighet = '120'");}
    }
  }
}


function createWHERE() {
  for(var i = 0; i < input.length; i++) {
    if(input[i].type == 'checkbox' && input[i].checked == true) values.push(input[i].value);
  }
  for(var i = 0; i < input.length; i++) {
    if(input[i].type == 'checkbox' && input[i].checked == true) names.push(input[i].name);
  }

 findValues();

for (x in names){
  if(names[x] == "dayOfWeek"){
    res.push("?timer a:dayOfWeek ?value .  FILTER(?value IN("
    + listDay + "))");
  }

  if(names[x] == "startTime"){
    res.push("FILTER(" + listTime.join(" || ") + ")");
  }

  if(names[x] == "sameAs"){
    var listTitle2 = Array.from(new Set(listTitle));
    res.push("?timer a:sameAs ?value2 .  FILTER(?value2 IN("
    + listTitle2 + "))");
  }

  if(names[x] == "duration"){
    res.push("FILTER(" + listDuration.join("||") + ")");
  }
}
// remove duplicates
  var result = Array.from(new Set(res));

  return result.join(" . ");
}


//___________________________________ Run query _____________________________

    /**
     * The code used to send the query is inspired by:
     * Author: Mark Wallace
     * Refs:
     * [1] http://www.w3.org/TR/sparql11-results-json/
     */

    function sparqlQueryJson(queryStr, endpoint, callback, isDebug) {
      var querypart = "query=" + escape(queryStr);

      // Get our HTTP request object.
      var xmlhttp = null;
      if(window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
     } else if(window.ActiveXObject) {
       // Code for older versions of IE, like IE6 and before.
       xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
     } else {
       alert('Perhaps your browser does not support XMLHttpRequests?');
     }

     // Set up a POST with JSON result format.
     xmlhttp.open('POST', endpoint, true); // GET can have caching probs, so POST
     xmlhttp.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
     xmlhttp.setRequestHeader("Accept", "application/sparql-results+json");

     // Set up callback to get the response asynchronously.
     xmlhttp.onreadystatechange = function() {
       if(xmlhttp.readyState == 4) {
         if(xmlhttp.status == 200) {
           // Do something with the results
          // if(isDebug) alert(xmlhttp.responseText);
           callback(xmlhttp.responseText);
         } else {
           // Some kind of error occurred.
           alert("Sparql query error: " + xmlhttp.status + " "
               + xmlhttp.responseText);
         }
       }
     };
     // Send the query to the endpoint.
     xmlhttp.send(querypart);
    };


    // when button is clicked, return query
    document.getElementById('btn').onclick = function createQuery(){

    // Define endpoint. Fuseki, make sure
    var endpoint = "http://localhost:3030/ds/query";

      // Create the entire query
      var query = ("PREFIX a: <http://schema.org/> SELECT ?timeNavn ?starter ?dag ?varighet ?sted ?location "
      + "WHERE {?timer a:dayOfWeek ?dag . ?timer a:duration ?varighet . ?timer a:legalName ?sted . ?timer a:title ?timeNavn . ?timer a:startTime ?starter . ?timer a:location ?location ."
      + createWHERE() + "}");

      // Define a callback function to receive the SPARQL JSON result.
      function myCallback(str) {
        // Convert result to JSON
        var jsonObj = eval('(' + str + ')');

        // Build up a table of results.
        var result = " <table border='1' cellpadding='9'>" ;
        for(var i = 0; i<  jsonObj.results.bindings.length; i++) {
          result += " <tr> </tr>"
          result += " <tr> <td>" + jsonObj.results.bindings[i].timeNavn.value;
          result += " </td><td>" + jsonObj.results.bindings[i].starter.value;
          result += " </td><td>" + jsonObj.results.bindings[i].dag.value;
          result += " </td><td>" + jsonObj.results.bindings[i].varighet.value;
          result += " </td><td>" + jsonObj.results.bindings[i].sted.value;
          result += " </td><td>" + jsonObj.results.bindings[i].location.value;
          result += " </td></tr>";
        }
        result += "</table>" ;

        document.getElementById("demo").innerHTML = result;
     }

     // Make the query.
     sparqlQueryJson(query, endpoint, myCallback, true);
}
