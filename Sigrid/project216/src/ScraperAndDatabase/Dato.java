package CleanCode;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  For å finne riktig søkedag på de ulike treningstimene. 
 *  
 *  Noen av dagene oppgis som "I dag" - og siden noen av bookingsidene må lastes ned er det ikke 
 *  med sikkerhet at "i dag" er i dag.
 * 
 *  Denne klassen bruker datoen og konverterer den til riktig format. 
 *  Med bruk av java sin innebygde kalender kan en hente ut dagen timen gjelder på.
 * 
 * 
 * @author Sigrid Maria Brækken
 */
public class Dato {

	
	private String theDate = null;
	private String theDay = null;
	
	public Dato (String dateInfo){
		try {
			fixInput(dateInfo);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void fixInput(String dateInfo) throws ParseException {
		
		String [] removeRubbish = null;
		String [] dateAndMonth = null;
		if (dateInfo.contains("-")){
			removeRubbish = dateInfo.split(" - ");
			dateAndMonth = removeRubbish[1].split(" ");
		}
		else 
			dateAndMonth = dateInfo.split(" ");  

		
		int date = Integer.parseInt(dateAndMonth[0]);
		int month = getMonth(dateAndMonth[1]);
		
		convertToDateFormat(date, month);	
		
	}



	private void convertToDateFormat(int date, int month) throws ParseException {
		
		String sDate = "" + date; 
		String sMonth = "" + month;
		
		if (date <= 9)
			sDate = 0 + "" + sDate;
		if (month <= 9)
			sMonth = 0 + "" + sMonth;
		
		theDate =  sDate + "/" + sMonth + "/" + "2017" ;
		SimpleDateFormat format1=new SimpleDateFormat("dd/MM/yyyy");
		Date dt1=format1.parse(theDate);
		DateFormat format2=new SimpleDateFormat("EEEE"); 
		
		theDay=format2.format(dt1);
	}



	public int getMonth(String theMonth){
		if (theMonth.length()>3)
			theDay = theDay.substring(0, 2);

		switch(theMonth.toUpperCase()){
		
		case(("JAN")):
			return 1;
		case("FEB"):
			return 2;
		case("MAR"):
			return 3;
		case("APR"):
			return 4;
		case("MAI"):
			return 5;
		case ("MAY"):
			return 5;
		case("JUN"):
			return 6;
		case("JUL"):
			return 7;
		case("AUG"):
			return 8;
		case("SEP"):
			return 9;
		case("OKT"):
			return 10;
		case("OCT"):
			return 10;
		case("NOV"):
			return 11;
		case("DES"):
			return 12;
		case("DEC"):
			return 12;
		default: 
			return 0;
		}
	}

	public String getDate(){
		return theDate;
	}	
	public String getDay(){
		return theDay;
	}
		
}
