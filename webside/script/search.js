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
  }
  // return array of unique names, and remove comma
  return r.join(" ");
}


function goThroughValues() {
  var names = getNames(this.form);
  var values = getValues(this.form);

  var res = [];

  for(i in names){
    res.push("?" + names[i] + " a:"  + values[i] + "</br>");
  }
  return res;
}

// when button is clicked, return values
document.getElementById('btn').onclick = function(){
   // gets the array returned by getElements()
  var resultsVal = getValues(this.form);
  var resultsName = getNames(this.form);
  console.log(resultsVal);
  console.log(resultsName);
  //document.getElementById("demo").innerHTML = (results);

document.getElementById("demo").innerHTML =
("prefix a: <http://example/SibCity> </br> SELECT "
  + goThroughNames()
  + "</br> WHERE { </br> "
  + goThroughValues()
  + "}"

  );

}

//___________________________________ Create query _______________________________________________________________


//SELECT ?timer ?starttid
//WHERE {
  //?timer a:typeOfClass ?saltime .
  //?timer  a:Varighet "55 min" .
  //?timer  a:Start ?starttid
//}






//___________________________________ Run query in fuseki ________________________________________________________



//___________________________________ Get results ________________________________________________________________



//___________________________________ Print results ______________________________________________________________



//___________________________________ Run code ___________________________________________________________________
