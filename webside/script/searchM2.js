const xhr = new XMLHttpRequest();
xhr.open('GET', 'https://example.com/data.json'); // by default async 
xhr.responseType = 'json'; // in which format you expect the response to be


xhr.onload = function() {
  if(this.status == 200) {// onload called even on 404 etc so check the status
   console.log(this.response); // No need for JSON.parse()
  }
};

xhr.onerror = function() {
  // error 
};


xhr.send();
function createWHERE() {
	// arrays that store selected checkboxes values and names
	var values = [];
	var names = [];
	var list = [];
	var res = [];
	// gets all the input tags in frm, and their number
	var input = document.getElementsByTagName('input');

	for(var i = 0; i < input.length; i++) {
		if(input[i].type == 'checkbox' && input[i].checked == true) 
			values.push(input[i].value);
	}

	for(var i = 0; i < input.length; i++) {
		if(input[i].type == 'checkbox' && input[i].checked == true)
			names.push(input[i].name);
	}


	for(x in values){
		if(names[x] == "duration"){
			if (values[x] == "min30"){
			res.push("FILTER (?varighet <= " + '"30"'+ ") . <br>");}
			if (values[x] == "plus60"){
			res.push("FILTER (?varighet < " + '"60"'+ ") . <br>");}
			if (values[x] == "30til60"){
			res.push("FILTER (?varighet <= " + '"60"'+ " && ?varighet > " + '"30"' + ") . <br>");}
		}		
		if(names[x] == "startTime"){
			if (values[x] == "f10"){
			res.push("FILTER (?startTime <= " + '"30"'+ ") . <br>");}
			if (values[x] == "10til15"){
			res.push("FILTER (?varighet < " + '"60"'+ ") . <br>");}
			if (values[x] == "15til17"){
			res.push("FILTER (?varighet <= " + '"60"'+ " && ?varighet > " + '"30"' + ") . <br>");}
			if (values[x] == "18til20"){
			res.push("FILTER (?varighet <= " + '"30"'+ ") . <br>");}
			if (values[x] == "etter20"){
			res.push("FILTER (?varighet < " + '"60"'+ ") . <br>");}
		}
	}

	return res.join(" . <br>");
}

document.getElementById('btn').onclick = function createQuery(){

  var myQuery = ("prefix a: &lthttp://schema.org/&gt <br> SELECT ?dato ?timeNavn ?starter ?sted ?dag ?varighet ?instructor ?location ?liktSom<br>"
    + " WHERE { <br> "
    + createWHERE() +
    " ?timer a:dayOfWeek ?dag . <br> ?timer a:duration ?varighet . <br> ?timer a:legalName ?sted . <br> ?timer a:title ?timeNavn . <br> ?timer a:startTime ?starter . <br> ?timer a:location ?location . <br> ?timer a:instructor ?instructor . <br> ?timer a:typeof ?liktSom . <br> ?timer a:startDate ?dato }" );

document.getElementById("demo2").innerHTML = myQuery;
}