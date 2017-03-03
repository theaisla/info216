package info216;

import java.io.FileOutputStream;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.VCARD;

public class dataSet {
	public static void main(String[] args) {
		String iriBase = "http://no.uib.infomedia.info216/";
		
	// create an empty Model
	Model model = ModelFactory.createDefaultModel();
	
	Resource vektertorget = model.createResource("http://somewhere/Vektertorget");
	Resource sats = model.createResource("http://somewhere/Sats");
	
	Property propYoga = model.createProperty(iriBase + "Yoga");
	Property propPilates = model.createProperty(iriBase + "Pilates");
	
	vektertorget.addProperty(propYoga, "kl 6").addProperty(propYoga, "kl 4");
	
	
//timer
	Property propMin= model.createProperty(iriBase+ "AntallMinutter");
	//Property propTime= model.createProperty(iriBase+ "Time");
	Property propStartTid= model.createProperty(iriBase+ "Starter");
	
	//sats.addProperty(propTime, propYoga);
	sats.addProperty(propYoga, "Kr 100,-");
	
	
	
	Property propTime = model.createProperty(iriBase + "Time");	
	Resource res75 = model.createResource(iriBase + "75min");
	Resource res60min = model.createResource(iriBase + "60min");
	Resource resTime1 = sats.addProperty(propStartTid, "1500").addProperty(propMin, res75);
	
	Resource resTravelling = model.createResource(iriBase + "Travelling");
	Resource resBirds = model.createResource(iriBase + "Birds");
	Resource resEcology = model.createResource(iriBase + "Ecology");
	Resource resEnviroment = model.createResource(iriBase + "Enviroment");
	Resource resPhotography = model.createResource(iriBase + "Photography");
	
	Resource[]listSats = {resTime1};
	///Resource[]listOfInterestsCade = {resBirds, resEcology, resTravelling, resEnviroment, resPhotography};
	
	Resource satsTime = model.createList(listSats);
//	Resource cadeIntersts = model.createList(listOfInterestsCade);

	sats.addProperty(propTime, satsTime);
	//sats.addProperty(propIntrests, cadeIntersts);
	
	
//åpningstider
	Property propManTors= model.createProperty(iriBase+ "Mandag-Torsdag");
	Property propFre= model.createProperty(iriBase+ "Fredag");
	Property propLor= model.createProperty(iriBase+ "Lordag");
	Property propSon= model.createProperty(iriBase+ "Sondag");
	
	vektertorget.addProperty(propManTors, "0700-2200");
	vektertorget.addProperty(propFre, "0700-2100");
	vektertorget.addProperty(propLor, "1000-1700");
	vektertorget.addProperty(propSon, "1400-2000");

	sats.addProperty(propManTors, "0600-2200");
	sats.addProperty(propFre, "0600-2100");
	sats.addProperty(propLor, "0900-1800");
	sats.addProperty(propSon, "1000-2000");
	
//pris
	Property propPrice= model.createProperty(iriBase+ "PrisDrop-in");
	sats.addProperty(propPrice, model.createResource(iriBase + "ForAlle:").addProperty(propPrice, "Kr 200,-"));
	vektertorget.addProperty(propPrice,
			model.createResource(iriBase + "ForStudenter:").addProperty(propPrice, "Kr 70,-"));
	vektertorget.addProperty(propPrice,
			model.createResource(iriBase + "ForIkkeStudenter:").addProperty(propPrice, "Kr 100,-"));
	
//adresse
	Property propCity = model.createProperty(iriBase + "City");
	vektertorget.addProperty(VCARD.Country, "Norge").addProperty(VCARD.Pcode, "5008")
					.addProperty(VCARD.Street, "Lars Hillesgate 29").addProperty(propCity, "Bergen");
	sats.addProperty(VCARD.Country, "Norge").addProperty(VCARD.Pcode, "5016")
					.addProperty(VCARD.Street, "Chrsitiesgt. 13").addProperty(propCity, "Bergen");
	
//Kontakt
	vektertorget.addProperty(VCARD.EMAIL, "vektertorget.treningssenter@sib.no");
	vektertorget.addProperty(VCARD.TEL, "55 54 51 80");
	sats.addProperty(VCARD.EMAIL, "bergen@satselixia.no");
	sats.addProperty(VCARD.TEL, "55 54 13 70");
	

	
	//Writing to file
	try {
		model.write(new FileOutputStream("trening.ttl"), "TURTLE");
	} catch (Exception e) {
		// TODO: handle exception
	}
	
System.out.println("--------------TURTLE------------------");
	model.write(System.out, "TURTLE");
	System.out.println("--------------N-TRIPLE------------------");
	model.write(System.out, "N-TRIPLE");
	System.out.println("--------------N3------------------");
	model.write(System.out, "N3");
	System.out.println("--------------JSON-LD------------------");
	model.write(System.out, "JSON-LD");
	System.out.println("--------------RDF/XML------------------");
	model.write(System.out, "RDF/XML");
	}

}
