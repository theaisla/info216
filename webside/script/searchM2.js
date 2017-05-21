function loadXMLDoc() {
  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      myFunction(this);
    }
  };
  xmlhttp.open("GET", "FindMyFitness.xml", true);
  xmlhttp.send();
}
function myFunction(xml) {
  var i;
  var xmlDoc = xml.responseXML;
  var table="<tr><th>Timenavn</th><th>Starter</th><th>Dag</th><th>Varighet</th><th>Senter</th><th>Sted</th></tr>";
  var x = xmlDoc.getElementsByTagName("rdf:Description");
  for (i = 0; i <x.length; i++) {
	  table += "<tr>" + 
	  "<td>"+ x[i].getElementsByTagName("a:title")[0].childNodes[0].nodeValue + "</td>" + 
	  "<td>"+ x[i].getElementsByTagName("a:startTime")[0].childNodes[0].nodeValue + "</td>" +
	  "<td>"+ x[i].getElementsByTagName("a:dayOfWeek")[0].childNodes[0].nodeValue + "</td>" +
	  "<td>"+ x[i].getElementsByTagName("a:duration")[0].childNodes[0].nodeValue + "</td>" +
	  "<td>"+ x[i].getElementsByTagName("a:legalName")[0].childNodes[0].nodeValue + "</td>" +
	  "<td>"+ x[i].getElementsByTagName("a:location")[0].childNodes[0].nodeValue + "</td>" +"</tr>"
	  ;}
  document.getElementById("demo2").innerHTML = table;
}

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
document.getElementById('btn').onclick = function createQuery(){

  var myQuery = ("prefix a: &lthttp://schema.org/&gt <br> SELECT ?timeNavn ?starter ?dag ?varighet ?sted ?location <br>"
    + " WHERE { <br> ?timer a:dayOfWeek ?dag . <br> ?timer a:duration ?varighet . <br> ?timer a:legalName ?sted . <br> ?timer a:title ?timeNavn . <br> ?timer a:startTime ?starter . <br>?timer a:location ?location .  <br><br>"
    + createWHERE() + "<br>} <br><br>  <h3> Hvis ikke du fant det du ser etter, kanskje noen av disse resultatene faller med i smak:</h3>");

    var myAltQuery = ("prefix a: &lthttp://schema.org/&gt <br> SELECT ?timeNavn ?starter ?dag ?varighet ?sted ?location <br>"
      + " WHERE { <br> ?timer a:dayOfWeek ?dag . <br> ?timer a:duration ?varighet . <br> ?timer a:legalName ?sted . <br> ?timer a:title ?timeNavn . <br> ?timer a:startTime ?starter . <br>?timer a:location ?location .  <br><br>"
      + createAltWHERE() + "<br>}");

document.getElementById("demo").innerHTML = myQuery;


document.getElementById("alt").innerHTML = myAltQuery;

}

//___________________________________ Run Query ________________________________

//___________________________________ Prtin Results ____________________________
