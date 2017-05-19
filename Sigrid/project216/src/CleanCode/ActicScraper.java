package CleanCode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;




public class ActicScraper {

	
	
	// Modellen som skal brukes i programmet
	private Model model = ModelFactory.createDefaultModel();
	

	private String gymURI = "http://findmyfitness/";
	
	private String schemaURI = "http://schema.org/";

	
	
	
	private ArrayList<String> titles = new ArrayList<String>();
	private ArrayList<String> instructors = new ArrayList<String>();
	private ArrayList<String> times = new ArrayList<String>();
	private ArrayList<String> durations = new ArrayList<String>();
	private String day = null;
	private String date = null;

	private Document document;
	
	
	
	public ActicScraper (String filename , boolean isFile) throws IOException{
		
		if (isFile){
			File input = new File(filename);
			document = Jsoup.parse(input, "UTF-8", "http://example.com/");
		}
		else{
			document = Jsoup.connect(filename).get();
		}
		extractDataFromPage();
	} 
	


	public void extractDataFromPage() throws IOException{
		
		String title = null;
		for (Element c: document.select(".day.u-size1of8")){ 
			
				   title = c.select(".title").text();
			String instructor = c.select(".instructor").text();
			String duration = c.select(".duration").text();
			String startTime = c.select(".time").text();
			
			date = c.select(".date").text();
			
			String [] splitted = date.split("\\. ");
			titles.add(title);
			times.add(startTime);
			durations.add(duration);
			instructors.add(instructor);
			Dato d = new Dato(splitted[1]);
			day = d.getDay();
			date = d.getDate();
			}
		
		GymDatabase gdb = new GymDatabase(model);
		Resource r = null;
		for (int i = 0; i < titles.size(); i++){
			r = getResourceFromDatabase(gdb, title);
			if (r != null);
			addLiterals(gdb, r, i);
		}
		
	}

	private void addLiterals(GymDatabase gdb, Resource r, int i) {
		if (r == null)
			throw new IllegalStateException("Ingen resurs er funnet");
			gdb.addAssetsToResource(model, r, day, date, times.get(i), instructors.get(i), durations.get(i), "Actic", "BergenSentrum");
		
	}



	private Resource getResourceFromDatabase(GymDatabase gdb, String title) {
		if (title == null)
			return null;
		
		
		Resource workoutclass = gdb.findResource(title, model);
	
		return workoutclass;
	}



	public static void main (String[]args) throws IOException{
	
		
		String filename = "http://www.actic.no/finn-sentre/gym-bergen/";
		
		ActicScraper scraper = new ActicScraper(filename, false);
	
	
	}
}