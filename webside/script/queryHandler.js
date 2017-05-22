// arrays that store selected checkboxes values and names
var values = []; var names = [];
var listTime = [];   var listDay = [];  var listDuration = []; var listTitle5 = []; var listTitle4 = [];
var res = []; var res2 = [];

// gets all the input tags in frm, and their number
var input = document.getElementsByTagName('input');

function findDay1() {
  for(x in values){
    if(names[x] == "dayOfWeek"){
      listDay.push("'" + values[x] + "'");
    }
  }
}

function findTime1() {
  for(x in values){
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
  }
}

function findDuration1() {
  for(x in values){
    if(names[x] == "duration"){
      if (values[x] == "min30"){
        listDuration.push("?varighet <= '30' && ?varighet !='120'");}
      if (values[x] == "31til60"){
        listDuration.push("?varighet <= '60' && ?varighet > '31'");}
      if (values[x] == "plus60"){
        listDuration.push("?varighet < '61' || ?varighet = '120'");}
    }
  }
}

function findsameAs1() {
  for(x in values){
    if(names[x] == "sameAs"){
      listTitle5.push("'" + values[x] + "'");
    }
  }
}

function findtypeOf1() {
  for(x in ids){
      if (ids[x] == "basseng"){listTitle4.push("'Basseng'");}
      if (ids[x] == "mama"){listTitle4.push("'MammaTrening'");}
      if (ids[x] == "senior"){listTitle4.push("'SeniorTrening'");}
      if (ids[x] == "step"){listTitle4.push("'Dans'");}
      if (ids[x] == "dans"){listTitle4.push("'Dans'");}
      if (ids[x] == "yoga"){listTitle4.push("'Flex'");}
      if (ids[x] == "mob"){listTitle4.push("'Flex'");}
      if (ids[x] == "pilates"){listTitle4.push("'Flex'");}
      if (ids[x] == "intens"){listTitle4.push("'Kondisjon'");}
      if (ids[x] == "run"){listTitle4.push("'Kondisjon'");}
      if (ids[x] == "ro"){listTitle4.push("'Kondisjon'");}
      if (ids[x] == "sykle"){listTitle4.push("'Kondisjon'");}
      if (ids[x] == "tabata"){listTitle4.push("'Kondisjon'");}
      if (ids[x] == "energy"){listTitle4.push("'Kondisjon'");}
      if (ids[x] == "slyng"){listTitle4.push("'Styrke'");}
      if (ids[x] == "styrkeB"){listTitle4.push("'Styrke'");}
      if (ids[x] == "styrkeH"){listTitle4.push("'Styrke'");}
      if (ids[x] == "styrkeK"){listTitle4.push("'Styrke'");}
  }
}

function createWHERE1() {
  for(var i = 0; i < input.length; i++) {
    if(input[i].type == 'checkbox' && input[i].checked == true) values.push(input[i].value);
  }
  for(var i = 0; i < input.length; i++) {
    if(input[i].type == 'checkbox' && input[i].checked == true) names.push(input[i].name);
  }

  findDay1(); findTime1(); findDuration1(); findsameAs1();

for (x in names){
  if(names[x] == "dayOfWeek"){
    res.push("?timer a:dayOfWeek ?value .  <br>FILTER(?value IN("
    + listDay + "))");
  }
  if(names[x] == "startTime"){
    res.push("FILTER(" + listTime.join(" || ") + ")");
  }
  if(names[x] == "sameAs"){
    var listTitle2 = Array.from(new Set(listTitle5));
    res.push("?timer a:sameAs ?value2 .  <br>FILTER(?value2 IN(" + listTitle2 + "))");
  }
  if(names[x] == "duration"){
    res.push("FILTER(" + listDuration.join("||") + ")");
  }
}
// remove duplicates
  var result = Array.from(new Set(res));
  return result.join(" . <br>");
}

function createAWHERE() {
  for(var i = 0; i < input.length; i++) {
    if(input[i].type == 'checkbox' && input[i].checked == true) ids.push(input[i].id);
  }
  for(var i = 0; i < input.length; i++) {
    if(input[i].type == 'checkbox' && input[i].checked == true) names.push(input[i].name);
  }
  for(var i = 0; i < input.length; i++) {
    if(input[i].type == 'checkbox' && input[i].checked == true) values.push(input[i].value);
  }

  findDay1(); findTime1(); findDuration1(); findtypeOf1();

for (x in names){
  if(names[x] == "dayOfWeek"){
    var listDay1 = Array.from(new Set(listDay));
    res2.push("?timer a:dayOfWeek ?value3 .  <br>FILTER(?value3 IN("
    + listDay1 + "))");
  }
  if(names[x] == "startTime"){
    res2.push("FILTER(" + listTime.join(" || ") + ")");
  }
  if(names[x] == "sameAs"){
    var listTitle3 = Array.from(new Set(listTitle4));
    res2.push("?timer a:typeof ?value4 .  <br>FILTER(?value4 IN(" + listTitle3 + "))");
  }
  if(names[x] == "duration"){
    res2.push("FILTER(" + listDuration.join("||") + ")");
  }
}
// remove duplicates
  var result1 = Array.from(new Set(res2));
  return result1.join(" . <br>");
}

//___________________________________ Create query _____________________________

// when button is clicked, return query
document.getElementById('btn2').onclick = function createQuery(){

  var myQuery1 = ("prefix a: &lthttp://schema.org/&gt <br> SELECT ?timeNavn ?starter ?dag ?varighet ?sted ?location <br>"
    + " WHERE { <br> ?timer a:dayOfWeek ?dag . <br> ?timer a:duration ?varighet . <br> ?timer a:legalName ?sted . <br> ?timer a:title ?timeNavn . <br> ?timer a:startTime ?starter . <br>?timer a:location ?location .  <br><br>"
    + createWHERE1()
    + "<br>} <br><br>  <h3> Hvis ikke du fant det du ser etter, kanskje noen av disse resultatene faller med i smak:</h3><br><br>");

    var myQuery2 = ("prefix a: &lthttp://schema.org/&gt <br> SELECT ?timeNavn ?starter ?dag ?varighet ?sted ?location <br>"
    + " WHERE { <br> ?timer a:dayOfWeek ?dag . <br> ?timer a:duration ?varighet . <br> ?timer a:legalName ?sted . <br> ?timer a:title ?timeNavn . <br> ?timer a:startTime ?starter . <br>?timer a:location ?location .  <br><br>"
    + createAWHERE() + "<br>}");

document.getElementById("demo").innerHTML = myQuery1;
document.getElementById("alt").innerHTML = myQuery2;
}
