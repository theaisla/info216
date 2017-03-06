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
	
	//sentre
	Resource vektertorget = model.createResource("http://somewhere/Vektertorget");
	Resource sats = model.createResource("http://somewhere/Sats");
	
	//trening
	Property propYoga = model.createProperty(iriBase + "Yoga");
	Property propYoga2 = model.createProperty(iriBase + "Yoga2");
	Property propYogaFlow = model.createProperty(iriBase + "YogaFlow");
	Property propPilates = model.createProperty(iriBase + "Pilates");
	Property propMeditasjon = model.createProperty(iriBase + "propMeditasjon");
	Property propBodyBalance = model.createProperty(iriBase + "propBodyBalance");
	
	//timer
	Property propMin= model.createProperty(iriBase+ "AntallMinutter");
	Property propStartTid= model.createProperty(iriBase+ "Starter");
	
	Resource res90 = model.createResource(iriBase + "90min");
	Resource res75 = model.createResource(iriBase + "75min");
	Resource res60 = model.createResource(iriBase + "60min");
	Resource res55 = model.createResource(iriBase + "55min");
	Resource res30 = model.createResource(iriBase + "30min");
	
//åpningstider
	Property aapningstider= model.createProperty(iriBase+ "AApningstider");
	Property propMan= model.createProperty(iriBase+ "Mandag");
	Property propTir= model.createProperty(iriBase+ "Tirsdag");
	Property propOns= model.createProperty(iriBase+ "Onsdag");
	Property propTors= model.createProperty(iriBase+ "Torsdag");
	Property propFre= model.createProperty(iriBase+ "Fredag");
	Property propLor= model.createProperty(iriBase+ "Lordag");
	Property propSon= model.createProperty(iriBase+ "Sondag");
	
	vektertorget.addProperty(propMan, "0700-2200").addProperty(propTir, "0700-2200").addProperty(propOns, "0700-2200").addProperty(propTors, "0700-2200");
	vektertorget.addProperty(propFre, "0700-2100");
	vektertorget.addProperty(propLor, "1000-1700");
	vektertorget.addProperty(propSon, "1400-2000");
	sats.addProperty(propMan, "0600-2200").addProperty(propTir, "0600-2200").addProperty(propOns, "0600-2200").addProperty(propTors, "0600-2200");
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
	
	// må ha med 
		/*
		 * hvilket senter (vektertorget/sats)
		 * Treningstype (yoga/pilates)
		 * ukedag (propMan/propTir/propOns/propTors/propFre/propLor/propSon)
		 * klokkeslett (??  propStartTid )
		 * antallminutter (res90/res75/res60/res55/res30)
		 */
	
	//vektertorget
	vektertorget.addProperty(propTir, propPilates).addProperty(propStartTid, "17:45").addProperty(propMin, res55);
	vektertorget.addProperty(propOns, propYoga).addProperty(propStartTid, "09:00").addProperty(propMin, res55);
	vektertorget.addProperty(propOns, propYoga).addProperty(propStartTid, "15:30").addProperty(propMin, res55);
	vektertorget.addProperty(propOns, propMeditasjon).addProperty(propStartTid, "16:30").addProperty(propMin, res30);
	vektertorget.addProperty(propTors, propYoga2).addProperty(propStartTid, "18:30").addProperty(propMin, res90);
	vektertorget.addProperty(propLor, propYoga).addProperty(propStartTid, "11:15").addProperty(propMin, res75);
	vektertorget.addProperty(propSon, propYoga).addProperty(propStartTid, "18:15").addProperty(propMin, res90);
	
	//sats
	sats.addProperty(propMan, propPilates).addProperty(propStartTid, "10:30").addProperty(propMin, res60);
	sats.addProperty(propMan, propBodyBalance).addProperty(propStartTid, "20:15").addProperty(propMin, res60);
	sats.addProperty(propMan, propYoga).addProperty(propStartTid, "20:00").addProperty(propMin, res75);
	sats.addProperty(propTir, propPilates).addProperty(propStartTid, "19:30").addProperty(propMin, res75);
	sats.addProperty(propOns, propPilates).addProperty(propStartTid, "18:45").addProperty(propMin, res60);
	sats.addProperty(propOns, propYoga).addProperty(propStartTid, "20:00").addProperty(propMin, res75);
	sats.addProperty(propTors, propYogaFlow).addProperty(propStartTid, "11:30").addProperty(propMin, res60);
	sats.addProperty(propTors, propYogaFlow).addProperty(propStartTid, "20:00").addProperty(propMin, res75);
	sats.addProperty(propFre, propYoga).addProperty(propStartTid, "15:00").addProperty(propMin, res75);
	sats.addProperty(propFre, propBodyBalance).addProperty(propStartTid, "16:15").addProperty(propMin, res60);
	sats.addProperty(propLor, propPilates).addProperty(propStartTid, "09:15").addProperty(propMin, res75);
	sats.addProperty(propFre, propYoga).addProperty(propStartTid, "10:15").addProperty(propMin, res90);
	
		
	//
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
