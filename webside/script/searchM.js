//___________________________________ Get elements _______________________________________________________________

// Returns an array with values of the selected (checked) checkboxes
function getValues() {
  // arrays that store selected checkboxes values and names
  var values = [];
  // gets all the input tags in frm, and their number
  var input = document.getElementsByTagName('input');

  for(var i = 0; i < input.length; i++) {
    if(input[i].type == 'checkbox' && input[i].checked == true) values.push(input[i].value);
  }
  return values;
}

function getNames() {
  // arrays that store selected checkboxes values and names
  var names = [];
  // gets all the input tags in frm, and their number
  var input = document.getElementsByTagName('input');

  for(var i = 0; i < input.length; i++) {
    if(input[i].type == 'checkbox' && input[i].checked == true) names.push(input[i].name);
  }
  return names;
}

function goThroughNames() {
  var names = getNames(this.form);
  var temp = {};
  // check if two names are the same
  for(x in names){
    temp[names[x]] = true;
    var r = [];
  }
  // Push names to array with a ?
    for (var k in temp){
    r.push("?" + k);

    //(testarray.join("%"))
  }
  return r.join(" ");
}

function goThroughValues() {
	//må fjerne kommaer
	
  var names = getNames(this.form);
  var values = getValues(this.form);
 
for(i in names){
if(names[i] == "Dag"){
  var res = [];

  for(i in names){
//gjelder a:mandag/tirsdag.. a:saltime/spinning/loepetime
    res.push("?" + names[i] + " ?x a:"  + values[i] + "</br>");
  }
  return res.join(".");
}
}
/*
				for(i in names){
				if(names[i] == "Start" || names[i] == "Varighet"){
				  var res2 = [];

				  for(i in names){
				//gjelder tider "18:00"/ "60 min"
					res2.push("?" + names[i] + " a:"  + names[i] + ' "' + values[i]+ '"' + "</br>");
				  }
				  return res2.join(".");
				}
				}*/
/*
	for(i in names){
	if(names[i] == "type_trening"){
	  var res3 = [];

	  for(i in names){
	//gjelder tider a:typetrening  a:Pilates ?x ?p 
		res3.push("a:" + values[i] + " ?" + names[i] +  " ?y </br>");
	  }
	  return res3.join(".");
	}
	}*/
}


// when button is clicked, return values
document.getElementById('btn').onclick = function(){
   // gets the array returned by getElements()
  var resultsVal = getValues(this.form);
  var resultsName = getNames(this.form);
  console.log(resultsVal);
  console.log(resultsName);
  /*document.getElementById("demo").innerHTML = (results);
var ukedag = goThroughNames() + " ?x a:" + resultsVal  + " .";
var startpunkt = goThroughNames() + " a:" + resultsName + " '" + resultsVal + "'" + " .";

document.getElementById("demo").innerHTML =
("prefix a: <http://example/SibCity> </br> SELECT "
  //+ for (x in resultsNames){
  //x;
  //}
 // + goThroughNames()
 + "*"
  + "</br> WHERE { </br>"
  + ukedag 
  + startpunkt 
  + "</br> }"

  );*/
  document.getElementById("demo").innerHTML =
("prefix a: <http://example/SibCity> </br> SELECT "
  + goThroughNames()
  + "</br> WHERE { </br> "
  + goThroughValues()
  + "}"

  );



}
/*

SELECT ?Dag ?Start
WHERE { 
?Dag a:Onsdag
,?Start a:07:00

SELECT ?Dag
WHERE { 
?Dag ?x a:Onsdag.
?Dag a:Start "07:00"

  ?timer ?p a:Mandag .
  ?timer  a:Varighet "55 min" .
  ?timer  a:Start ?starttid
  
  
?Dag ?x a:Torsdag.
?Dag a:Start "07:00"


//distanse
? 

//dager
if (alleDager = ?Dag)
	
if (mandag = ?Mandag)
	 ?timer ?p a:Mandag 
//tidspunkt
if (tidspunkt = ?Start)
	  ?timer  a:Start ?starttid
  ?timer  a:Start "16:00"
  
//type trening
if (pilates = ?pilates)
 a:Pilates ?x ?p 	

//vartighet
if(30 min eller mer = ?Varighet)
  ?timer  a:Varighet "55 min" .
    ?timer  a:Varighet ?min 

	//fasilitet
if(?harFasilitet)
	  ?x a:harFasilitet ?p 


*/


//___________________________________ Run query in fuseki ________________________________________________________



//___________________________________ Get results ________________________________________________________________



//___________________________________ Print results ______________________________________________________________



//___________________________________ Run code ___________________________________________________________________
