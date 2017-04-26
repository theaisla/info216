package CleanCode;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import project216.IScraper;

public class ScrapeSats implements IScraper {

	// Modellen som skal brukes i programmet
	Model model = ModelFactory.createDefaultModel();
	
	//liste med alle resources
	ArrayList<Resource> allResources = new ArrayList<Resource>();
	
	//liste med alle treningsformer
	ArrayList<Property> workoutTypes = new ArrayList<Property>();
	// URI
	String gymURI = "http://example/Sats";
	
	//Dagen som gjelder for de ulike resourcene - blir lest inn for hver dag.
	String theDay;
	
	// Størrelse på listen av resurser - for å vise til riktig resurs 
	private int numResources=0;
	
	private Document document;
	
	
	
	public ScrapeSats (String filename , boolean isFile) throws IOException{
		
		if (isFile){
			File input = new File(filename);
			document = Jsoup.parse(input, "UTF-8", "http://example.com/");
		}
		else{
			document = Jsoup.connect(filename).get();
		}
		extractDataFromPage();
		//sparql();
	} 


	public void extractDataFromPage() throws IOException{
		

		//dokument og 
		Property starttid = model.createProperty(gymURI + "Start");
		Property varighet = model.createProperty(gymURI + "Varighet");
		Property dag = model.createProperty(gymURI + "Dag");
		Property instruktoer = model.createProperty(gymURI + "Instruktoer");
		Property treningssenter = model.createProperty(gymURI + "Treningssenter");
		
		

		
		for (Element e: document.select("table.table.booking-table.booking-table-mobile.visible-sm.visible-xs.hidden-print.ng-scope tr")){ //".ng-scope"
			
			if (e.select(":root > .ng-binding").text().length() >4)	
				theDay = e.select(":root > .ng-binding").text();
			
			String title = e.select(".title.ng-binding").text();
			
			
			if (createResource(title)){   
				
				//instruktør
				String instructor = e.select(".subtitle.ng-binding").text();
				
				//time, duration, location, center
				String details = e.select(".ng-binding").select("span").text(); //.not(".queue-badge ng-binding").text();
				String [] timeAndLocationDetails = details.split(" ");
			
				
				//plukker ut bugs
				boolean rubbishdetector = false;
				for (int i = 0; i< timeAndLocationDetails.length-1; i++){
					
					if (timeAndLocationDetails[0].length()<3)
						rubbishdetector = true;
					if (rubbishdetector)
						timeAndLocationDetails[i]=timeAndLocationDetails[i+1];
					
				}

					String timeInStringFormat = timeAndLocationDetails[0];
					String duration = timeAndLocationDetails[1];
					String location = timeAndLocationDetails[3];
					String center = timeAndLocationDetails[4];
					System.out.printf("%50s %10s %5s %15s %15s %30s %20s \n", title, timeInStringFormat, duration, location, center, instructor, theDay  );
					
				
				
			}
		}
	}
	
	/**
	 * Method will be created if it is amoung the workout types we are looking for
	 * 
	 * @param title, title of a class held at the gym
	 * @return True if a resource is created
	 */
	
	public boolean createResource(String title){
		
		Property yoga = model.createProperty(gymURI + "/Yoga"); Property pilates = model.createProperty(gymURI + "/Pilates");
		Property cycling = model.createProperty(gymURI + "/Cycling"); Property mølle = model.createProperty(gymURI + "/Mølle");
		Property styrke = model.createProperty(gymURI + "/Styrke"); Property senior = model.createProperty(gymURI + "/Senior");
		Property tabata = model.createProperty(gymURI + "/Tabata"); Property run = model.createProperty(gymURI + "/Tabata");
		Property zumba = model.createProperty(gymURI + "/Zumba"); Property intervall = model.createProperty(gymURI + "/Intervall");
		Property dans = model.createProperty(gymURI + "/Dans"); Property sterk = model.createProperty(gymURI + "/Sterk");
		Property aqua = model.createProperty(gymURI + "/Aqua"); Property pump = model.createProperty(gymURI + "/Pump");
		Property stang = model.createProperty(gymURI + "/Stang"); Property step = model.createProperty(gymURI + "/Step");
		Property kettlebell = model.createProperty(gymURI + "/Kettlebell"); Property power = model.createProperty(gymURI + "/Power");
		Property leg = model.createProperty(gymURI + "/Leg"); Property trx = model.createProperty(gymURI + "/TRX");
		
		workoutTypes.add(yoga); workoutTypes.add(cycling); workoutTypes.add(styrke); workoutTypes.add(tabata); workoutTypes.add(zumba); 
		workoutTypes.add(dans); workoutTypes.add(aqua); workoutTypes.add(trx); workoutTypes.add(stang); workoutTypes.add(kettlebell); 
		workoutTypes.add(leg); workoutTypes.add(pilates); workoutTypes.add(mølle); workoutTypes.add(senior); workoutTypes.add(run); 
		workoutTypes.add(intervall); workoutTypes.add(sterk); workoutTypes.add(pump); workoutTypes.add(step); workoutTypes.add(power); 
		//workoutTypes.add(); workoutTypes.add(yoga); workoutTypes.add(yoga); workoutTypes.add(yoga); workoutTypes.add(yoga); 


		String titleUpperCase = title.toUpperCase();

		for (int i=0; i<workoutTypes.size();i++)

		if	(titleUpperCase.contains(workoutTypes.get(i).getLocalName().toUpperCase())){
			Resource myResource = (Resource) model.createResource(gymURI + numResources + title);//numresources for å få en unik URI
			allResources.add(myResource);
			numResources++;
			return true;
		}
		return false;
	}
	
	
	private Time convertFromStringToTime(String stringTime) {
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


	@Override
	public Time convertTime(String time) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void sparql(){
	// SPARQL query
    dumpQueryResult(model,String.format(
    		"prefix a: <http://example/Sats> SELECT ?x ?p WHERE {?x ?yoga ?p}"));

	}


    public void dumpQueryResult(Model model, String queryString) {
        Query query = QueryFactory.create(queryString);
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();
        ResultSetFormatter.out(System.out, results, query);
        qe.close();
    }


	@Override
	public String findOpeningHours() {
		// TODO Auto-generated method stub
		return null;		
	}

	@Override
	public String findAddress() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int getMonth() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}


