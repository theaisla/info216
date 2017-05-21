package CleanCode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


/**
 * ACTIC
 * 
 * Actic's data er ikke lett å få tak i på en strukturert form.
 * Vi har derfor gjort det beste vi kunne i denne situsjonen. 
 * 
 * Actic sender all data lest inn videre til GymDatabase hvor modellen
 * blir laget og sendt tilbake. 
 * 
 * Data leses inn ved bruk av JSOUP og leses direkte fra nettsiden.
 * 
 */

public class ACTIC {

	
	// Modellen som skal brukes i programmet
	public Model model = ModelFactory.createDefaultModel();
	private Document document;
	
	private ArrayList<String> titles;
	private ArrayList<String> instructors;
	private ArrayList<String> times;
	private ArrayList<String> durations;
	private ArrayList<String> days = new ArrayList<String>();
	private String day = null;
	private boolean endOfWeek = false;
	
	
	
	public ACTIC (String filename , boolean isFile) throws IOException{
		
		if (isFile){
			File input = new File(filename);
			document = Jsoup.parse(input, "UTF-8", "http://example.com/");
		}
		else{
			document = Jsoup.connect(filename).get();
		}
		extractDataFromPage();
	} 
	

	/**
	 * Metoden som scraper siden og plukker ut ønsket data
	 * Bruker JSOUP
	 * 
	 * @throws IOException
	 */
	public void extractDataFromPage() throws IOException{
	
		times = new ArrayList<String>();
		String title = null;
		
		outerloop:
		for (Element c: document.select(".day.u-size1of8")){ 

			day = getDay(c.select(".date").text());
			
			if (endOfWeek)
				break outerloop;
			
			title = c.select(".title").text();
			
			if (title.isEmpty())
				continue outerloop;
			
			String instructor = c.select(".instructor").text();
			String duration = c.select(".duration").text();
			
			String temp =c.select("div.session").text();
			String [] timesplit = temp.split(" ");

			for (String s : timesplit)
				if (s.contains(":")) 
					times.add(s);

			titles = (ArrayList<String>) cleanup(title, false);
			instructors = (ArrayList<String>) cleanup(instructor, true);
			durations = (ArrayList<String>) cleanup(duration, false);

		
			GymDatabase gdb = new GymDatabase(model);
			Resource r = null;
			
	
			int counter = 0;
			for (String s : titles){
					r = getResourceFromDatabase(gdb, s);
					addLiterals(gdb, r, counter);
					counter++;
					
			}
		}
	}

	/**
	 * Denne metoden henter ut hvilken dag det er basert på dato.
	 * Actic gir tilgjengelighet for 3 uker (ca) med timer, så for å begrense 
	 * den til en uke har vi lagt inn en sjekk som stopper outerloop i 
	 * extractDataFromPage() metoden.
	 * @param dato, dato til dagen.
	 * @return dagen
	 */
	private String getDay(String dato) {
		String [] splitted = dato.split("\\. ");
		Dato d = new Dato(splitted[1]);
		day = d.getDay();
		if (days.size()>0)
			if (days.contains(day))
				endOfWeek = true;
		
		days.add(day);
		return day;
	}

	
	/**
	 * Rydder ut bugs som ligger i titlene - Ved scraping av Actic kommer alle titler
	 *  i en string og skilles bare med et mellomrom. Løsningen er midlertidlig og fungerer bra
	 *  på oppsettet deres nå.
	 *  
	 * @param keys, ord som skal ryddes opp i
	 * @param isInstructors, sant dersom det er instruktører - Actic har en standar med to navn
	 * 		  per person. Dette utnytter vi
	 * 
	 * @return liste med 'rene' ord
	 */
	private List<String> cleanup(String keys, boolean isInstructors) {

		List<String> list = new ArrayList<String>();
		String [] splitted = keys.split(" ");
		
		String previous ="";
		
		if (!isInstructors){
			for (String s : splitted){
				
				String temp = s.toUpperCase();
				if (temp.contains("BY") || temp.contains("ACTIC") || temp.contains("INDOOR") || temp.contains("MIN."))
					continue;
				if (previous.toUpperCase().equals("SPINNING") && temp.toUpperCase().equals("INTERVALL")
						|| previous.toUpperCase().equals("SENIOR") && temp.toUpperCase().equals("ATC")){	
					list.remove(previous);
					list.add(previous + s);
					continue;
				}
				list.add(s);
				previous = s;
				
			}
			return list;
		}

		for (int i = 0; i < splitted.length ; i++)
			if (i < splitted.length-1)
				list.add(splitted[i] + splitted[++i]);		
		
		return list;
	}


	/**
	 * Henter ut resource dersom tittel finnes i databasen
	 * @param gdb, objekt til databasen
	 * @param title, tittel på treningstimen
	 * @return resuren som ble laget
	 */
	private Resource getResourceFromDatabase(GymDatabase gdb, String title) {
		if (title == null)
			System.out.println(title +"  krise");;
	
		Resource workoutclass = gdb.setUpWorkoutclass(title);
		return workoutclass;
	}

	/**
	 * Kobler en resurs til tid, varighet++
	 * @param gdb, objekt til databasen
	 * @param r, resursen som skal kobles
	 * @param i, teller på hvilken listeplass som skal hentes ut
	 */
	private void addLiterals(GymDatabase gdb, Resource r, int i) {
		if (r == null)
			return;
		model = gdb.addAssetsToResource(model, r, day, times.get(i), 
						instructors.get(i), durations.get(i), "Actic", "BergenSentrum");
	}


	
	
	
	public static void main (String[]args) throws IOException{
	
		
		String filename = "http://www.actic.no/finn-sentre/gym-bergen/";
		filename = "Gym Bergen - Actic Norge.html";
		ACTIC scraper = new ACTIC(filename, true);
	
		
	
		scraper.model.write(System.out, "TURTLE");
	}
}