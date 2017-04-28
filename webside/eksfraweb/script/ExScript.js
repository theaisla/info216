
// Returns an array with values of the selected (checked) checkboxes in "frm"
function getSelectedChbox() {
 // JavaScript & jQuery Course - http://coursesweb.net/javascript/
  var selchbox = [];        // array that will store the value of selected checkboxes

  // gets all the input tags in frm, and their number
  var inpfields = document.getElementsByTagName('input');
  var nr_inpfields = inpfields.length;

  // traverse the inpfields elements, and adds the value of selected (checked) checkbox in selchbox
  for(var i=0; i<nr_inpfields; i++) {
    if(inpfields[i].type == 'checkbox' && inpfields[i].checked == true) selchbox.push(inpfields[i].value);
  }

  return selchbox;
}

  /* Test this function */
// When click on #btntest, alert the selected values
document.getElementById('btntest').onclick = function(){
  var selchb = getSelectedChbox(this.form);     // gets the array returned by getSelectedChbox()
  alert(selchb);
}
