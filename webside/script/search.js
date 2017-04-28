//___________________________________ Get elements _______________________________________________________________

// Returns an array with values of the selected (checked) checkboxes
function getElements() {
  // array that stores selected checkboxes values
  var selection = [];
  // gets all the input tags in frm, and their number
  var input = document.getElementsByTagName('input');

  for(var i = 0; i < input.length; i++) {
    if(input[i].type == 'checkbox' && input[i].checked == true) selection.push(input[i].value);
  }
  return selection;
}

// when button is clicked, return values
document.getElementById('btn').onclick = function(){
   // gets the array returned by getElements()
  var results = getElements(this.form);
  console.log(results);
  document.getElementById("demo").innerHTML = (results);
}

//___________________________________ Create query _______________________________________________________________



//___________________________________ Run query in fuseki ________________________________________________________



//___________________________________ Get results ________________________________________________________________



//___________________________________ Print results ______________________________________________________________



//___________________________________ Run code ___________________________________________________________________
