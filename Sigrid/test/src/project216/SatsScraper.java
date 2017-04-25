package project216;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

//import org.apache.jena.rdf.model.Resource;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class SatsScraper {

	public static void main(String[] args) throws IOException {


		String filename = "Booke gruppetrening på SATS ELIXIA - SATS ELIXIA.html";
		extractDataFromPage(filename, true);
	}


	
	
	private static void extractDataFromPage(String URL, boolean isFile) throws IOException{
		
		
		String theDay;
		
		Document document;
		if (isFile){
			File input = new File(URL);
			document = Jsoup.parse(input, "UTF-8", "http://example.com/");
		}
		else{
			document = Jsoup.connect(URL).get();
		}
		
		
		for (Element e: document.select("table.table.booking-table.booking-table-mobile.visible-sm.visible-xs.hidden-print.ng-scope tr")){ //".ng-scope"
			//System.out.println(e.text());

			String title = e.select(".title.ng-binding").text();
			System.out.println(findResource(title));
		//	findResource(title);
			
			
			//instruktør
			String instructor = e.select(".subtitle.ng-binding").text();
			
			//time, duration, location, center
			String details = e.select(".ng-binding").select("span").text(); //.not(".queue-badge ng-binding").text();
			String [] splitted = details.split(" ");
		
			
			//plukker ut bugs
			boolean rubbishdetector = false;
			for (int i = 0; i< splitted.length-1; i++){
				
				if (splitted[0].length()<3)
					rubbishdetector = true;
				if (rubbishdetector)
					splitted[i]=splitted[i+1];
				
			}
			// date length < 4
			if (splitted.length > 4){
				
			String timeInStringFormat = splitted[0];
			String duration = splitted[1];
			String location = splitted[3];
			String center = splitted[4];
			
			
			//Time time = convertFromStringToTime(stringTime);
			System.out.printf("%50s %10s %5s %15s %15s %15s \n", title, timeInStringFormat, duration, location, center, instructor  );
			}
			else {
				theDay = e.text();
				System.out.println(e.text());
			}
		}
	}
	/**
	 * Metoden skal returnere den rette attributten
	 * slik at alle verdiene kan lagres til akkurat denne.
	 * @param title
	 * @return 
	 * @return attribute
	 */
	
	private static String findResource(String title){

		List<String> liste = new ArrayList<String>();
		liste.add("Yoga"); liste.add("Pilates"); liste.add("Cycling");
		liste.add("Styrke"); liste.add("Senior"); liste.add("Løpe");
		liste.add("Tabata"); liste.add("Zumba"); liste.add("Run");
		liste.add("Mølle");
		
		String titleUpperCase = title.toUpperCase();

		for (int i=0; i<liste.size();i++)
		if	(titleUpperCase.contains(liste.get(i).toUpperCase()))
			return title + "|&|" + liste.get(i);

		return null;
	}
	
	private static Time convertFromStringToTime(String stringTime) {
		DateFormat sdf = new SimpleDateFormat("hh:mm");
		try {
		    // To get the date object from the string just called the 
		    // parse method and pass the time string to it. This method 
		    // throws ParseException if the time string is invalid. 
		    // But remember as we don't pass the date information this 
		    // date object will represent the 1st of january 1970.
			System.out.println((Time) sdf.parse(stringTime));
		    return (Time) sdf.parse(stringTime);            
		} catch (Exception f) {
		    f.printStackTrace();
		}
		return null;
	}

}
