//___________________________________ Get elements _______________________________________________________________

var valg = document.forms['search'].elements['valg[]'];
var results = [];

var button = document.getElementById(theButton);
button.onclick = console.log("pushed");

// using reference to sports obtained above
for (var i=0; i < valg.length; i++) {
	var test = 	valg[i];
	test.onclick = runStuff;
}

// access properties of checkbox clicked using 'this' keyword
function runStuff() {
  if ( this.checked ) { // if checked ...
	   var navn =this.value;
	   results.push(navn);
	   console.log(results);
     document.getElementById("demo").innerHTML = (results );
  } else {
  // if not checked ...
  }
}



//___________________________________ Create query _______________________________________________________________



//___________________________________ Run query in fuseki ________________________________________________________



//___________________________________ Get results ________________________________________________________________



//___________________________________ Print results ______________________________________________________________



//___________________________________ Run code ___________________________________________________________________
