// arrays that store selected checkboxes values and names
var values = []; var names = [];
var listTime = [];   var listDay = [];  var listDuration = []; var listTitle = [];
var res = [];


// gets all the input tags in frm, and their number
var input = document.getElementsByTagName('input');


function findValues() {
  //______ Find values _____
  for(x in values){
    if(names[x] == "dayOfWeek"){
      listDay.push("&quot" + values[x] + "&quot");
    }

    if(names[x] == "startTime"){
      if (values[x] == "f10"){
        listTime.push("regex(?starter,'^06') || regex(?starter,'07') || regex(?starter,'^08') || regex(?starter,'09')");}
      if (values[x] == "10til12"){
        listTime.push("regex(?starter,'^10') || regex(?starter,'11') || regex(?starter,'^12')");}
      if (values[x] == "13til15"){
        listTime.push("regex(?starter,'^13') || regex(?starter,'14') || regex(?starter,'15')");}
      if (values[x] == "16til18"){
        listTime.push("regex(?starter,'^16') || regex(?starter,'17') || regex(?starter,'^18')");}
      if (values[x] == "etter19"){
        listTime.push("regex(?starter,'^19') || regex(?starter,'20') || regex(?starter,'^21') || regex(?starter,'22')");}
    }

    if(names[x] == "sameAs"){
      listTitle.push("&quot" + values[x] + "&quot");
    }

    if(names[x] == "duration"){
      if (values[x] == "min30"){
        listDuration.push("?varighet <= &quot30&quot");}
      if (values[x] == "plus60"){
        listDuration.push("?varighet < &quot60&quot");}
      if (values[x] == "30til60"){
        listDuration.push("?varighet <= &quot60&quot && ?varighet > &quot30&quot");}
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
    res.push("?timer a:dayOfWeek ?value .  <br>FILTER(?value IN("
    + listDay + "))");
  }

  if(names[x] == "startTime"){
    res.push("FILTER(" + listTime.join(" || ") + ")");
  }

  if(names[x] == "sameAs"){
    var listTitle2 = Array.from(new Set(listTitle));
    res.push("?timer a:sameAs ?value2 .  <br>FILTER(?value2 IN("
    + listTitle2 + "))");
  }

  if(names[x] == "duration"){
    res.push("FILTER(" + listDuration.join("||") + ")");
  }
}
// remove duplicates
  var result = Array.from(new Set(res));

  return result.join(" . <br>");
}


function createAltWHERE() {

}


//___________________________________ Create query _____________________________

// when button is clicked, return query
/*document.getElementById('btn').onclick = function createQuery(){

  var myQuery = ("prefix a: &lthttp://schema.org/&gt <br> SELECT ?timeNavn ?starter ?dag ?varighet ?sted ?location <br>"
    + " WHERE { <br> ?timer a:dayOfWeek ?dag . <br> ?timer a:duration ?varighet . <br> ?timer a:legalName ?sted . <br> ?timer a:title ?timeNavn . <br> ?timer a:startTime ?starter . <br>?timer a:location ?location .  <br><br>"
    + createWHERE() + "<br>} <br><br>  <h3> Hvis ikke du fant det du ser etter, kanskje noen av disse resultatene faller med i smak:</h3>");*/

  //  var myAltQuery = ("prefix a: &lthttp://schema.org/&gt <br> SELECT ?timeNavn ?starter ?dag ?varighet ?sted ?location <br>"
    //  + " WHERE { <br> ?timer a:dayOfWeek ?dag . <br> ?timer a:duration ?varighet . <br> ?timer a:legalName ?sted . <br> ?timer a:title ?timeNavn . <br> ?timer a:startTime ?starter . <br>?timer a:location ?location .  <br><br>"
      //+ createAltWHERE() + "<br>}");

//document.getElementById("demo").innerHTML = myQuery;


//document.getElementById("alt").innerHTML = myAltQuery;

//var queryFuseki = ("prefix a: &lthttp://schema.org/&gt SELECT ?timeNavn ?starter ?dag ?varighet ?sted ?location "
  //+ " WHERE { ?timer a:dayOfWeek ?dag . ?timer a:duration ?varighet . ?timer a:legalName ?sted . ?timer a:title ?timeNavn . ?timer a:startTime ?starter . ?timer a:location ?location . "
  //+ createWHERE() + "}");
//document.getElementById("demo").innerHTML = queryFuseki;




/*var http = new XMLHttpRequest();
var url = "http://localhost:3030/ds/update";
var params = "lorem=ipsum&name=binny";
http.open("POST", url, true);

//Send the proper header information along with the request
http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

http.onreadystatechange = function() {//Call a function when the state changes.
    if(http.readyState == 4 && http.status == 200) {
        alert(http.responseText);
    }
}
http.send(params);*/


    /**
     * Author: Mark Wallace
     *
     * This function asynchronously issues a SPARQL query to a
     * SPARQL endpoint, and invokes the callback function with the JSON
     * Format [1] results.
     *
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
           if(isDebug) alert(xmlhttp.responseText);
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

     // Done; now just wait for the callback to be called.
    };

    document.getElementById('btn').onclick = function createQuery(){

    var endpoint = "http://localhost:3030/ds/query";
      //var query = "prefix a: <http://schema.org/> select * {?s ?p ?o} limit 5" ;

      var query = ("PREFIX a: <http://schema.org/> SELECT ?timeNavn ?starter ?dag ?varighet ?sted ?location WHERE {?timer a:dayOfWeek ?dag . ?timer a:duration ?varighet . ?timer a:legalName ?sted . ?timer a:title ?timeNavn . ?timer a:startTime ?starter . ?timer a:location ?location . }");



      // {?s ?p ?o} limit 5" ;

      //var queryFuseki = ("prefix a: &lthttp://schema.org/&gt SELECT ?timeNavn ?starter ?dag ?varighet ?sted ?location "
        //+ " WHERE { ?timer a:dayOfWeek ?dag . ?timer a:duration ?varighet . ?timer a:legalName ?sted . ?timer a:title ?timeNavn . ?timer a:startTime ?starter . ?timer a:location ?location . "
        //+ createWHERE() + "}");


      //var query = ("prefix a: 'http://schema.org/> SELECT * WHERE { ?timer a:dayOfWeek ?dag ."+ createWHERE() + "}");


      // Define a callback function to receive the SPARQL JSON result.
      function myCallback(str) {
        // Convert result to JSON
        var jsonObj = eval('(' + str + ')');

        // Build up a table of results.
        var result = " <table border='2' cellpadding='9'>" ;
        for(var i = 0; i<  jsonObj.results.bindings.length; i++) {
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

//___________________________________ Run Query ________________________________




//___________________________________ Prtin Results ____________________________
