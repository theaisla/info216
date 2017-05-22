package ScraperAndDatabase;

/**
 * GymDatabase 
 * 
 * En database til programmet som holder styr på treningstyper 
 * og annet som er generelt for alle treningssentre. 
 * 
 * En bug har forhindret oss i å bruke GymDatabasen for alle treningssentre.
 * Ved å se på actic kan en se at flere spinningtimer kommer i samme resurs 
 * sett og dette er ikke ønskelig. Selv om navnet er unikt på alle resurser 
 * legger de seg under samme istedet for å lage sin egen.
 * 
 * 
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

public class GymDatabase {

	//liste med alle treningsformer
	private ArrayList<Property> workoutTypes = new ArrayList<Property>();
	
	private String gymURI = "http://findmyfitness/";
	private String schemaURI = "http://schema.org/";
	
	Model model;
	
	private int numResources = 0;
	
	public GymDatabase(Model model) {
		
		this.model = model;
		setup();
	}
	
	
	/**
	 * Legger til type treninger i workoutTypes for videre søk
	 */
	private void setup() {
		
		
		Property yoga = model.createProperty(gymURI + "Yoga"); Property pilates = model.createProperty(gymURI + "Pilates");
		Property cycling = model.createProperty(gymURI + "Cycling"); Property mølle = model.createProperty(gymURI + "Mølle");
		Property styrke = model.createProperty(gymURI + "Styrke"); Property senior = model.createProperty(gymURI + "Senior");
		Property tabata = model.createProperty(gymURI + "Tabata"); Property run = model.createProperty(gymURI + "Run");
		Property zumba = model.createProperty(gymURI + "Zumba"); Property intervall = model.createProperty(gymURI + "Intervall");
		Property dans = model.createProperty(gymURI + "Dans/Dance"); Property sterk = model.createProperty(gymURI + "Sterk");
		Property aqua = model.createProperty(gymURI + "Aqua"); Property pump = model.createProperty(gymURI + "Pump");
		Property dance = model.createProperty(gymURI + "Dans/Dance"); 
		Property stang = model.createProperty(gymURI + "Stang"); Property step = model.createProperty(gymURI + "Step");
		Property kettlebell = model.createProperty(gymURI + "Kettlebell"); Property power = model.createProperty(gymURI + "Power");
		Property leg = model.createProperty(gymURI + "Leg"); Property trx = model.createProperty(gymURI + "TRX");
		Property crosstraining = model.createProperty(gymURI + "Crosstraining"); Property build = model.createProperty(gymURI + "Build");
		Property shape = model.createProperty(gymURI + "Shape"); Property strength = model.createProperty(gymURI + "Strength");
		Property intensity = model.createProperty(gymURI + "Intensity"); Property spinning = model.createProperty(gymURI + "Spinning");
		Property core = model.createProperty(gymURI + "Core"); Property boot = model.createProperty(gymURI + "Boot");
		
		workoutTypes.add(yoga); workoutTypes.add(pilates); workoutTypes.add(dance); 
		workoutTypes.add(cycling); workoutTypes.add(styrke); workoutTypes.add(tabata); workoutTypes.add(zumba); 
		workoutTypes.add(dans); workoutTypes.add(aqua); workoutTypes.add(trx); workoutTypes.add(stang); workoutTypes.add(kettlebell); 
		workoutTypes.add(leg);  workoutTypes.add(mølle); workoutTypes.add(senior); workoutTypes.add(run); 
		workoutTypes.add(intervall); workoutTypes.add(sterk); workoutTypes.add(pump); workoutTypes.add(step); workoutTypes.add(power); 
		workoutTypes.add(crosstraining); workoutTypes.add(build); workoutTypes.add(shape); workoutTypes.add(strength); 
		workoutTypes.add(intensity);// workoutTypes.add(yoga); workoutTypes.add(yoga); workoutTypes.add(yoga); workoutTypes.add(yoga); 
		workoutTypes.add(spinning); workoutTypes.add(core); workoutTypes.add(boot); //workoutTypes.add(e);

	}

	/**
	 * Fjerner mellomrom i tiltleArgument
	 * 
	 * @param titleArgument
	 * @return ny titleArgument uten mellomrom
	 */
	private String clearAllSpacesFromString(String titleArgument) {
		String newTitle = "";
		for (char c : titleArgument.toCharArray()){
			if (c == ' ')
				continue;
			newTitle += c;
		}
		return newTitle;
	}
	
	/**
	 *  Legger inn all data til en spesifik resurs
	 *  
	 * @param model
	 * @param resource
	 * @param dayArgument
	 * @param timeArgument
	 * @param instructorArgument
	 * @param durationArgument
	 * @param gymArgument
	 * @param locationArgument
	 * @return model
	 */
	public Model addAssetsToResource (Model model, Resource resource, 
			String dayArgument, String timeArgument,String instructorArgument,
				String durationArgument, String gymArgument, String locationArgument ){

		//relasjonene
		Property hasDuration = model.createProperty(schemaURI + "duration");
		Property onDay = model.createProperty(schemaURI + "dayOfWeek");
		Property onTime = model.createProperty(schemaURI + "startTime");
		Property onLocation = model.createProperty(schemaURI + "location");
		Property hasInstructor = model.createProperty(schemaURI + "instructor");
		Property legalName = model.createProperty(schemaURI + "legalName");
		
		//
		Literal day = model.createLiteral(dayArgument);
		Literal time = model.createLiteral(timeArgument);
		Literal duration = model.createLiteral(durationArgument);
		Literal location = model.createLiteral(locationArgument);
		Literal gym = model.createLiteral(gymArgument);
		Literal instructor = model.createLiteral(instructorArgument);
		
		              //resource -- på dag -- day
		model.addLiteral(resource, onDay, day);
		model.addLiteral(resource, onTime, time);
		model.addLiteral(resource, hasDuration, duration);
		model.addLiteral(resource, onLocation, location);
		model.addLiteral(resource, hasInstructor, instructor);
		model.addLiteral(resource, legalName, gym);
		
		return model;
	}
	
	/**
	 * Lager resursen og finner hvilken type trening den er.
	 * 
	 * @param titleArgument
	 * @return resursen
	 */
	public Resource setUpWorkoutclass(String titleArgument){  

		Property typeof = model.createProperty(schemaURI + "typeof");


		Resource flextrening = model.createResource(gymURI + "Flex");
		Resource kondisjonstrening = model.createResource(gymURI + "Kondisjon");
		Resource styrketrening = model.createResource(gymURI + "Styrke");
		Resource dansetrening = model.createResource(gymURI + "Dans");
		Resource bassengtrening = model.createResource(gymURI + "Basseng");
		Resource mammatrening = model.createResource(gymURI + "MammaTrening");
		Resource seniortrening = model.createResource(gymURI + "SeniorTrening");

		
		List<String> kondisjontab = Arrays.asList("Cycling", "Mølle", "Run", "Tabata", "Run", "Intervall", "Step", "Intensity", "Shape",
												  "Pulse", "3", "Spinning");
		List<String> styrketab = Arrays.asList("Styrke", "Tabata", "Sterk", "Pump", "Stang", "Kettlebell", "Leg", "Cross", "Strength", "BOX", 
												"Build", "Power", "TRX", "Power", "ABS", "Core", "Booty", "Body", "mobility", "transformer" );
		List<String> flextab = Arrays.asList("Pilates", "Yoga", "FLX" );
		List<String> bassengtab = Arrays.asList("Aqua", "basseng");
		List<String> seniortab = Arrays.asList("Senior", "eldre", "pensjonist");
		List<String> mammatab = Arrays.asList( "Mama",  "Mamma", "gravid", "strongMama");
		List<String> dansetab = Arrays.asList("Zumba", "Dans", "Dance", "Step");
		
		

		Property hasTitle = model.createProperty(schemaURI + "title");
		Property sameAs = model.createProperty(schemaURI + "sameAs");
		
		
		for (int i=0; i<workoutTypes.size();i++)

			if	(titleArgument.toUpperCase().contains(workoutTypes.get(i).getLocalName().toUpperCase())){
				
				Resource workoutclass = (Resource) model.createResource(gymURI + numResources + workoutTypes.get(i).getLocalName());//numresources for å få en unik URI
				//allResources.add(workoutclass);
				
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
				
				return workoutclass;
				}
		return null;
	}
}
