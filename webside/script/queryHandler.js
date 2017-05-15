//___________________________________ Get elements _______________________________________________________________

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
    if(names[x] == "varighet"){
      res.push("?timer a:" + names[x] + " " + values[x] );
    }
    if(names[x] == "varighet"){
      res.push("?timer a:" + names[x] + " " + values[x] );
    }
  }
  console.log(res);
//return res;
return res.join(".<br>");

}




// when button is clicked, return values
document.getElementById('btn').onclick = function(){
   // gets the array returned by getElements()
//  var resultsVal = getValues(this.form);
//  var resultsName = getNames(this.form);
//  console.log(resultsVal);
//  console.log(resultsName);
  //document.getElementById("demo").innerHTML = (results);

document.getElementById("demo").innerHTML =
("prefix a: <http://example/SibCity> </br> SELECT ?treningssenter ?timenavn ?starttid ?sted"
  //+ goThroughNames()
  + "</br> WHERE { </br> "
  + createWHERE()
  + "}"

  );

}
