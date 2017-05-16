package CleanCode;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.validation.Schema;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.apache.jena.vocabulary.DC_10;
import org.apache.jena.vocabulary.XSD;
import org.apache.jena.vocabulary.OWL2;
import org.apache.jena.vocabulary.VCARD;


import project216.IScraper;

public class ScrapeSats {

	// Modellen som skal brukes i programmet
	public Model model = ModelFactory.createDefaultModel();
	
	//liste med alle resources
	private ArrayList<Resource> allResources = new ArrayList<Resource>();
	
	//liste med alle treningsformer
	private ArrayList<Property> workoutTypes = new ArrayList<Property>();
	// URI
	private String gymURI = "http://example/Sats/";
	
	private String schemaURI = "http://schema.org/";
	
	//Dagen som gjelder for de ulike resourcene - blir lest inn for hver dag.
	private String theDay;
	
	
	
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
	} 
	


	public void extractDataFromPage() throws IOException{
		

	outerloop: // gir muligheten til å hoppe til neste element dersom søk er ferdig for e
		for (Element e: document.select("table.table.booking-table.booking-table-mobile.visible-sm.visible-xs.hidden-print.ng-scope tr")){ //".ng-scope"
			
			
			//leser inn ny dag
			if (e.select(":root > .ng-binding").text().length() >4){	
				theDay = e.select(":root > .ng-binding").text();
				continue outerloop;	
			}
			
				
			String title = e.select(".title.ng-binding").text();
			
			if (createResource(title)){ // sjekker at en tittel er som forventer og legger den til i underkategori av treningstype
					
				//time, duration, location, center
				String details = e.select(".ng-binding").select("span").text(); //.not(".queue-badge ng-binding").text();
				String [] timeAndLocationDetails = details.split(" ");
			
				
				timeAndLocationDetails = checkForAndRemovePotentialBugs(timeAndLocationDetails);

				
				String instruktor = clearAllSpacesFromString(e.select(".subtitle.ng-binding").text());
				
				Resource thisClass = allResources.get(numResources-1); //henter ut den gjeldende resursen 
				
				Dato d = new Dato(theDay);

				//relasjonene
				Property hasDuration = model.createProperty(schemaURI + "duration");
				Property onDay = model.createProperty(schemaURI + "dayOfWeek");
				Property onTime = model.createProperty(schemaURI + "startTime");
				Property onDate = model.createProperty(schemaURI+ "startDate");
				Property onLocation = model.createProperty(schemaURI + "location");
				Property hasInstructor = model.createProperty(schemaURI + "instructor");
				Property legalName = model.createProperty(schemaURI + "legalName");
				
				//
				Literal day = model.createLiteral(d.theDay);
				Literal time = model.createLiteral(timeAndLocationDetails[0]);
				Literal date = model.createLiteral(d.getDate());
				Literal duration = model.createLiteral(timeAndLocationDetails[1]);
				Literal location = model.createLiteral(timeAndLocationDetails[3]);
				Literal gym = model.createLiteral(timeAndLocationDetails[4]);
				
				Literal instructor = model.createLiteral(instruktor);
				
				              //resource -- på dag -- day
				model.addLiteral(thisClass, onDay, day);
				model.addLiteral(thisClass, onTime, time);
				model.addLiteral(thisClass, onDate, date);
				model.addLiteral(thisClass, hasDuration, duration);
				model.addLiteral(thisClass, onLocation, location);
				model.addLiteral(thisClass, hasInstructor, instructor);
				model.addLiteral(thisClass, legalName, gym);
				
//				resource.addLiteral(VCARD.Region, location);
//				resource.addLiteral(VCARD.Locality, gym);
				

				System.out.printf("%50s %10s %5s %15s %15s %30s %20s \n", title, timeAndLocationDetails[0], duration, location, gym, instructor, day  );
					
				
				
			}
		}
	}
	
	
	/**
	 * plukker ut bugs fra timeAndLocation (dette gjelder antall instruktører som oppgis i 1/10 
	 * av gruppetimene og er urelevant til programmet) 		
	 * 
	 * @param timeAndLocationDetails
	 * @return en oppryddet tabell, den samme dersom den ikke inneholdte ekstra informasjon
	 */
	private String[] checkForAndRemovePotentialBugs(String[] timeAndLocationDetails) {
		boolean rubbishdetector = false;
		for (int i = 0; i< timeAndLocationDetails.length-1; i++){
			
			if (timeAndLocationDetails[0].length()<3)
				rubbishdetector = true;
			if (rubbishdetector)
				timeAndLocationDetails[i]=timeAndLocationDetails[i+1];
		}
		return timeAndLocationDetails;
	}


	/**
	 * Lager resurser ettersom at de finnes i arkivet workoutTypes - finnes de ikke skal en default verdi 
	 * ta over. 
	 * 
	 * @param title, tittelen til gruppetimen
	 * @return True dersom en resurs ble laget
	 */
	
	public boolean createResource(String titleArgument){
		
		Resource flextrening = model.createResource(gymURI + "Flex");
		Resource kondisjonstrening = model.createResource(gymURI + "kondisjon");
		Resource styrketrening = model.createResource(gymURI + "styrke");
		
		
		Property yoga = model.createProperty(gymURI + "Yoga"); Property pilates = model.createProperty(gymURI + "Pilates");
		Property cycling = model.createProperty(gymURI + "Cycling"); Property mølle = model.createProperty(gymURI + "Mølle");
		Property styrke = model.createProperty(gymURI + "Styrke"); Property senior = model.createProperty(gymURI + "Senior");
		Property tabata = model.createProperty(gymURI + "Tabata"); Property run = model.createProperty(gymURI + "Run");
		Property zumba = model.createProperty(gymURI + "Zumba"); Property intervall = model.createProperty(gymURI + "Intervall");
		Property dans = model.createProperty(gymURI + "Dans/Dance"); Property sterk = model.createProperty(gymURI + "Sterk");
		Property aqua = model.createProperty(gymURI + "Aqua"); Property pump = model.createProperty(gymURI + "Pump");
		Property stang = model.createProperty(gymURI + "Stang"); Property step = model.createProperty(gymURI + "Step");
		Property kettlebell = model.createProperty(gymURI + "Kettlebell"); Property power = model.createProperty(gymURI + "Power");
		Property leg = model.createProperty(gymURI + "Leg"); Property trx = model.createProperty(gymURI + "TRX");
		Property crosstraining = model.createProperty(gymURI + "Crosstraining"); Property build = model.createProperty(gymURI + "Build");
		Property shape = model.createProperty(gymURI + "Shape"); Property strength = model.createProperty(gymURI + "Strength");
		Property intensity = model.createProperty(gymURI + "Intensity");
		
		
		Property typeof = model.createProperty(schemaURI + "typeof");
		//TODO legg til alle andre
		
		model.add(yoga, typeof, flextrening); model.add(pilates, typeof, flextrening); model.add(cycling, typeof, kondisjonstrening);
		model.add(mølle, typeof, kondisjonstrening); model.add(styrke, typeof, styrketrening); model.add(senior, typeof, flextrening);
		model.add(tabata, typeof, styrketrening); model.add(run, typeof, kondisjonstrening); model.add(zumba, typeof, kondisjonstrening);
		
		yoga.addProperty(OWL2.topDataProperty, flextrening); pilates.addProperty(OWL2.topDataProperty, flextrening);
		cycling.addProperty(OWL2.topDataProperty, kondisjonstrening); mølle.addProperty(OWL2.topDataProperty, kondisjonstrening);
		styrke.addProperty(OWL2.topDataProperty, styrketrening); senior.addProperty(OWL2.topDataProperty, flextrening);
		

		
		workoutTypes.add(yoga); workoutTypes.add(cycling); workoutTypes.add(styrke); workoutTypes.add(tabata); workoutTypes.add(zumba); 
		workoutTypes.add(dans); workoutTypes.add(aqua); workoutTypes.add(trx); workoutTypes.add(stang); workoutTypes.add(kettlebell); 
		workoutTypes.add(leg); workoutTypes.add(pilates); workoutTypes.add(mølle); workoutTypes.add(senior); workoutTypes.add(run); 
		workoutTypes.add(intervall); workoutTypes.add(sterk); workoutTypes.add(pump); workoutTypes.add(step); workoutTypes.add(power); 
		workoutTypes.add(crosstraining); workoutTypes.add(build); workoutTypes.add(shape); workoutTypes.add(strength); 
		workoutTypes.add(intensity);// workoutTypes.add(yoga); workoutTypes.add(yoga); workoutTypes.add(yoga); workoutTypes.add(yoga); 
		

		String titleUpperCase = titleArgument.toUpperCase();
		Property hasTitle = model.createProperty(schemaURI + "title");
		Property sameAs = model.createProperty(schemaURI + "sameAs");
		

		
		
		for (int i=0; i<workoutTypes.size();i++)

			if	(titleUpperCase.contains(workoutTypes.get(i).getLocalName().toUpperCase())){
				
				Resource workoutclass = (Resource) model.createResource(gymURI + numResources + workoutTypes.get(i));//numresources for å få en unik URI
				allResources.add(workoutclass);
				
				titleArgument = clearAllSpacesFromString(titleArgument);
				Literal title = model.createLiteral(titleArgument);
				model.addLiteral(workoutclass, hasTitle, title);
				model.add(workoutclass, sameAs, workoutTypes.get(i));
				//TODO tenkte å legge til yoga sin kategori her. altså workouttypes.get(i).getTYPEOF.....
			//	model.add(workoutclass, typeof, workoutTypes.get(i).get      
				numResources++;
				
				return true;
			}
		return false;
	}



	private String clearAllSpacesFromString(String titleArgument) {
		String newTitle = "";
		for (char c : titleArgument.toCharArray()){
			if (c == ' ')
				continue;
			newTitle += c;
		}
		return newTitle;
	}
	
}


