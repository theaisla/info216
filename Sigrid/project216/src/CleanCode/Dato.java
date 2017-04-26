package CleanCode;

import java.text.ParseException;


public class Dato {

	
	String theDate = null;
	
	public Dato (String dateInfo){
	
		try {
			fixInput(dateInfo);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	private void fixInput(String dateInfo) throws ParseException {
		String [] removeRubbish = dateInfo.split("-");
		String [] dateAndMonth = removeRubbish[1].split(" ");
		
		int date = Integer.parseInt(dateAndMonth[0]);
		int month = getMonth(dateAndMonth[1]);
		
		convertToDateFormat(date, month);	
		
	}



	private void convertToDateFormat(int date, int month) throws ParseException {
		theDate =  "2017-" + month + "-" + date;
	}



	public int getMonth(String theDay){
		switch(theDay){
		case("Januar"):
			return 1;
		case("Februar"):
			return 2;
		case("Mars"):
			return 3;
		case("April"):
			return 4;
		case("Mai"):
			return 5;
		case("Juni"):
			return 6;
		case("Juli"):
			return 7;
		case("August"):
			return 8;
		case("September"):
			return 9;
		case("Oktober"):
			return 10;
		case("November"):
			return 11;
		case("Desember"):
			return 12;
		default: 
			return 0;
		}
	}

	public String getDate(){
		return theDate;
	}	
		
}
