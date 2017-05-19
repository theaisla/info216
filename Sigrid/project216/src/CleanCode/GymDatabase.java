package CleanCode;

import java.util.ArrayList;

import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

public class GymDatabase {

	//liste med alle treningsformer
	private ArrayList<Property> workoutTypes = new ArrayList<Property>();
	
	private String gymURI = "http://findmyfitness/";
	private String schemaURI = "http://schema.org/";
	
	private int numResources = 0;
	
	public GymDatabase(Model model) {
		setup(model);
		
	}
	
	
	
	private void setup(Model model) {
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
		
		
		workoutTypes.add(yoga); workoutTypes.add(cycling); workoutTypes.add(styrke); workoutTypes.add(tabata); workoutTypes.add(zumba); 
		workoutTypes.add(dans); workoutTypes.add(aqua); workoutTypes.add(trx); workoutTypes.add(stang); workoutTypes.add(kettlebell); 
		workoutTypes.add(leg); workoutTypes.add(pilates); workoutTypes.add(mølle); workoutTypes.add(senior); workoutTypes.add(run); 
		workoutTypes.add(intervall); workoutTypes.add(sterk); workoutTypes.add(pump); workoutTypes.add(step); workoutTypes.add(power); 
		workoutTypes.add(crosstraining); workoutTypes.add(build); workoutTypes.add(shape); workoutTypes.add(strength); 
		workoutTypes.add(intensity);// workoutTypes.add(yoga); workoutTypes.add(yoga); workoutTypes.add(yoga); workoutTypes.add(yoga); 
		
	}



	public Resource findResource(String keyWord, Model model){

		for (int i=0; i<workoutTypes.size();i++)

			if	(keyWord.toUpperCase().contains(workoutTypes.get(i).getLocalName().toUpperCase())){
				
				Resource workoutclass = (Resource) model.createResource(gymURI + ++numResources + workoutTypes.get(i));//numresources for å få en unik URI
				//allResources.add(workoutclass);
				
				keyWord = clearAllSpacesFromString(keyWord);
				Literal title = model.createLiteral(keyWord);
				
				
				Property hasTitle = model.createProperty(schemaURI + "title");
				Property sameAs = model.createProperty(schemaURI + "sameAs");
				
				model.addLiteral(workoutclass, hasTitle, title);
				model.add(workoutclass, sameAs, workoutTypes.get(i));
				
				//TODO tenkte å legge til yoga sin kategori her. altså workouttypes.get(i).getTYPEOF.....
			//	model.add(workoutclass, typeof, workoutTypes.get(i).get      

				return workoutclass;
				//if	(kondisjonstrening.contains(workoutTypes.get(i).getLocalName().toUpperCase())){
			}
		return null;
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
	
	public void addAssetsToResource
		(Model model, Resource resource, String dayArgument,String dateArgument, String timeArgument,String 
		     	instructorArgument,String durationArgument, String gymArgument, String locationArgument ){
		//relasjonene
		Property hasDuration = model.createProperty(schemaURI + "duration");
		Property onDay = model.createProperty(schemaURI + "dayOfWeek");
		Property onTime = model.createProperty(schemaURI + "startTime");
		Property onDate = model.createProperty(schemaURI+ "startDate");
		Property onLocation = model.createProperty(schemaURI + "location");
		Property hasInstructor = model.createProperty(schemaURI + "instructor");
		Property legalName = model.createProperty(schemaURI + "legalName");
		
		//
		Literal day = model.createLiteral(dayArgument);
		Literal time = model.createLiteral(timeArgument);
		Literal date = model.createLiteral(dateArgument);
		Literal duration = model.createLiteral(durationArgument);
		Literal location = model.createLiteral(locationArgument);
		Literal gym = model.createLiteral(gymArgument);
		Literal instructor = model.createLiteral(instructorArgument);
		
		              //resource -- på dag -- day
		model.addLiteral(resource, onDay, day);
		model.addLiteral(resource, onTime, time);
		model.addLiteral(resource, onDate, date);
		model.addLiteral(resource, hasDuration, duration);
		model.addLiteral(resource, onLocation, location);
		model.addLiteral(resource, hasInstructor, instructor);
		model.addLiteral(resource, legalName, gym);
	}
}
