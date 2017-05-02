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

	
	String theDate = null;
	String theDay = null;
	
	public Dato (String dateInfo){
		try {
			fixInput(dateInfo);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void fixInput(String dateInfo) throws ParseException {
		//System.out.println(dateInfo);
		String [] removeRubbish = dateInfo.split(" - ");
		String [] dateAndMonth = removeRubbish[1].split(" ");
		//System.out.println("Date : " + dateAndMonth[0] + "   month : " +  dateAndMonth[1]);
		//System.out.println(dateAndMonth.length);
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



	public int getMonth(String theDay){
		switch(theDay){
		case("januar"):
			return 1;
		case("februar"):
			return 2;
		case("mars"):
			return 3;
		case("april"):
			return 4;
		case("mai"):
			return 5;
		case("juni"):
			return 6;
		case("juli"):
			return 7;
		case("august"):
			return 8;
		case("september"):
			return 9;
		case("oktober"):
			return 10;
		case("november"):
			return 11;
		case("desember"):
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
