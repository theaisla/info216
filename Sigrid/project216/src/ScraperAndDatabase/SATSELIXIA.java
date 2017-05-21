package CleanCode;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


/**
 * SATS ELIXIA
 * 
 * SATSELIXIA skal i fremtiden bruke GymDatabase, men dette lar seg ikke gjøre akkurat nå
 * 
 * Sats skraper data og legger den til modellen som resources, properties og literals.
 *
 * Må ha html fil nedlastet
 * 
 */


public class SATSELIXIA {

	// Modellen som skal brukes i programmet
	public Model model = ModelFactory.createDefaultModel();
	
	//liste med alle resources
	private ArrayList<Resource> allResources = new ArrayList<Resource>();
	
	//liste med alle treningsformer
	private ArrayList<Property> workoutTypes = new ArrayList<Property>();
	// URI
	private String gymURI = "http://findmyfitness/";
	
	private String schemaURI = "http://schema.org/";
	
	//Dagen som gjelder for de ulike resourcene - blir lest inn for hver dag.
	private String theDay;
	
	// Størrelse på listen av resurser - for å vise til riktig resurs 
	private int numResources=0;

	
	private Document document;
	
	
	
	public SATSELIXIA (String filename , boolean isFile) throws IOException{
		
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

				// Relasjonene
				Property hasDuration = model.createProperty(schemaURI + "duration");
				Property onDay = model.createProperty(schemaURI + "dayOfWeek");
				Property onTime = model.createProperty(schemaURI + "startTime");
				Property onLocation = model.createProperty(schemaURI + "location");
				Property hasInstructor = model.createProperty(schemaURI + "instructor");
				Property legalName = model.createProperty(schemaURI + "legalName");
				
				// Data
				Literal day = model.createLiteral(d.getDay());
				Literal time = model.createLiteral(timeAndLocationDetails[0]);
				Literal duration = model.createLiteral(timeAndLocationDetails[1]);
				Literal location = model.createLiteral(timeAndLocationDetails[3]);
				Literal gym = model.createLiteral(timeAndLocationDetails[4]);
				Literal instructor = model.createLiteral(instruktor);
				
				// Resource -- på dag -- day
				model.addLiteral(thisClass, onDay, day);
				model.addLiteral(thisClass, onTime, time);
				model.addLiteral(thisClass, hasDuration, duration);
				model.addLiteral(thisClass, onLocation, location);
				model.addLiteral(thisClass, hasInstructor, instructor);
				model.addLiteral(thisClass, legalName, gym);
				
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

		Property typeof = model.createProperty(schemaURI + "typeof");
		
		Property yoga = model.createProperty(gymURI + "Yoga"); Property pilates = model.createProperty(gymURI + "Pilates");
		Property cycling = model.createProperty(gymURI + "Cycling"); Property mølle = model.createProperty(gymURI + "Mølle");
		Property styrke = model.createProperty(gymURI + "Styrke"); Property senior = model.createProperty(gymURI + "Senior");
		Property tabata = model.createProperty(gymURI + "Tabata"); Property run = model.createProperty(gymURI + "Run");
		Property zumba = model.createProperty(gymURI + "Zumba"); Property intervall = model.createProperty(gymURI + "Intervall");
		Property dans = model.createProperty(gymURI + "Dans/Dance"); Property sterk = model.createProperty(gymURI + "Sterk");
		Property aqua = model.createProperty(gymURI + "Aqua"); Property pump = model.createProperty(gymURI + "Pump");
		Property dance = model.createProperty(gymURI + "Dance"); 
		Property stang = model.createProperty(gymURI + "Stang"); Property step = model.createProperty(gymURI + "Step");
		Property kettlebell = model.createProperty(gymURI + "Kettlebell"); Property power = model.createProperty(gymURI + "Power");
		Property leg = model.createProperty(gymURI + "Leg"); Property trx = model.createProperty(gymURI + "TRX");
		Property crosstraining = model.createProperty(gymURI + "Crosstraining"); Property build = model.createProperty(gymURI + "Build");
		Property shape = model.createProperty(gymURI + "Shape"); Property strength = model.createProperty(gymURI + "Strength");
		Property intensity = model.createProperty(gymURI + "Intensity"); Property abs = model.createProperty(gymURI + "ABS");
		Property pulse = model.createProperty(gymURI + "Pulse"); Property core = model.createProperty(gymURI + "Core"); 
		Property mobility = model.createProperty(gymURI + "Mobility"); Property tough = model.createProperty(gymURI + "Tough"); 
		Property balance = model.createProperty(gymURI + "Balance"); Property mama = model.createProperty(gymURI + "Mama");
		Property booty = model.createProperty(gymURI + "Booty"); Property energy = model.createProperty(gymURI + "Energy"); 
		Property body = model.createProperty(gymURI + "Body"); Property flx = model.createProperty(gymURI + "FLX"); 
		Property transformer = model.createProperty(gymURI + "Transformer"); Property box = model.createProperty(gymURI + "BOX");
		

		Resource flextrening = model.createResource(gymURI + "Flex");
		Resource kondisjonstrening = model.createResource(gymURI + "Kondisjon");
		Resource styrketrening = model.createResource(gymURI + "Styrke");
		Resource dansetrening = model.createResource(gymURI + "Dans");
		Resource bassengtrening = model.createResource(gymURI + "Basseng");
		Resource mammatrening = model.createResource(gymURI + "MammaTrening");
		Resource seniortrening = model.createResource(gymURI + "SeniorTrening");

		
		workoutTypes.add(yoga); workoutTypes.add(pilates); workoutTypes.add(dance); workoutTypes.add(intensity); 
		workoutTypes.add(cycling); workoutTypes.add(styrke); workoutTypes.add(tabata); workoutTypes.add(zumba); 
		workoutTypes.add(dans); workoutTypes.add(aqua); workoutTypes.add(trx); workoutTypes.add(stang); workoutTypes.add(kettlebell); 
		workoutTypes.add(leg);  workoutTypes.add(mølle); workoutTypes.add(senior); workoutTypes.add(run); 
		workoutTypes.add(intervall); workoutTypes.add(sterk); workoutTypes.add(pump); workoutTypes.add(step); workoutTypes.add(power); 
		workoutTypes.add(crosstraining); workoutTypes.add(build); workoutTypes.add(shape); workoutTypes.add(strength); 
		workoutTypes.add(abs); workoutTypes.add(pulse); workoutTypes.add(core); workoutTypes.add(mobility); workoutTypes.add(tough);
		workoutTypes.add(balance); workoutTypes.add(mama); workoutTypes.add(booty); workoutTypes.add(energy); workoutTypes.add(body);
		workoutTypes.add(flx); workoutTypes.add(transformer); workoutTypes.add(box);

		
		List<String> kondisjontab = Arrays.asList("Cycling", "Mølle", "Run", "Tabata", "Run", "Intervall", "Step", "Intensity", "Pulse", "3");
		List<String> styrketab = Arrays.asList("Styrke", "Tabata", "Sterk", "Pump", "Stang", "Kettlebell", "Leg", "Cross", "Strength", "BOX", 
												"Build", "Power", "TRX", "Power", "ABS", "Core", "Booty", "Body", "mobility", "transformer" );
		List<String> flextab = Arrays.asList("Pilates", "Yoga", "FLX", "Shape" );
		List<String> bassengtab = Arrays.asList("Aqua", "basseng");
		List<String> seniortab = Arrays.asList("Senior", "eldre", "pensjonist");
		List<String> mammatab = Arrays.asList( "Mama",  "Mamma", "gravid", "strongMama");
		List<String> dansetab = Arrays.asList("Zumba", "Dans", "Dance");
		
		

		Property hasTitle = model.createProperty(schemaURI + "title");
		Property sameAs = model.createProperty(schemaURI + "sameAs");
		
		
		for (int i=0; i<workoutTypes.size();i++)

			if	(titleArgument.toUpperCase().contains(workoutTypes.get(i).getLocalName().toUpperCase())){
				
				Resource workoutclass = (Resource) model.createResource(gymURI + numResources + workoutTypes.get(i).getLocalName());//numresources for å få en unik URI
				allResources.add(workoutclass);
				
				titleArgument = clearAllSpacesFromString(titleArgument);
				Literal title = model.createLiteral(titleArgument);
				model.addLiteral(workoutclass, hasTitle, title);
				model.add(workoutclass, sameAs, workoutTypes.get(i).getLocalName());
				   
				numResources++;
				
				
				// plukker automatisk ut hva timen trener - kjøretid O(n)
				for (String s : kondisjontab)
					if	(titleArgument.toUpperCase().contains(s.toUpperCase())){
						model.add(workoutclass, typeof, kondisjonstrening.getLocalName());
						continue;
					}
				for (String s : flextab)
					if	(titleArgument.toUpperCase().contains(s.toUpperCase())){
						model.add(workoutclass, typeof, flextrening.getLocalName());
						continue;
					}
				for (String s : styrketab)
					if (titleArgument.toUpperCase().contains(s.toUpperCase()))	{
						model.add(workoutclass, typeof, styrketrening.getLocalName());
						continue;
					}				
				for (String s : bassengtab)
					if (titleArgument.toUpperCase().contains(s.toUpperCase()))	{
						model.add(workoutclass, typeof, bassengtrening.getLocalName());
						continue;
					}
				for (String s : mammatab)
					if (titleArgument.toUpperCase().contains(s.toUpperCase()))	{
						model.add(workoutclass, typeof, mammatrening.getLocalName());
						continue;
					}
				for (String s : dansetab)
					if (titleArgument.toUpperCase().contains(s.toUpperCase()))	{
						model.add(workoutclass, typeof, dansetrening.getLocalName());
						continue;
					}
				for (String s : seniortab)
					if (titleArgument.toUpperCase().contains(s.toUpperCase()))	{
						model.add(workoutclass, typeof, seniortrening.getLocalName());
						continue;
					}
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


