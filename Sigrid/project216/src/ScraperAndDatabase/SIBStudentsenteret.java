package CleanCode;

import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

/**
 * SIB City/Studentsenteret
 * 
 * I denne klassen har vi manuelt lagt til all data. 
 * Dette var en midlertidlig løsning siden SIB's timeplan ikke
 * var så enkel å skrape. I fremtiden håper vi at deres data blir 
 * mer tilgjengelig slik at vi kan oppdatere timeplanen hyppig.
 */

public class SIBStudentsenteret {
	
	
	Model model = ModelFactory.createDefaultModel();
	private String gymURI = "http://findmyfitness/";
	private String schemaURI = "http://schema.org/";
	
	public SIBStudentsenteret(){
		create();
	}
	
	public void create(){

		
		// Tid / dag / varighet / instruktoer
		Property starttid = model.createProperty(schemaURI + "startTime");
		Property varighet = model.createProperty(schemaURI + "duration");
		Property dag = model.createProperty(schemaURI + "dayOfWeek");
		Property instructor = model.createProperty(schemaURI + "instructor");
	
		Literal mandag = model.createLiteral("mandag");
		Literal tirsdag = model.createLiteral("tirsdag");
		Literal onsdag = model.createLiteral("onsdag");
		Literal torsdag = model.createLiteral("torsdag");
		Literal fredag = model.createLiteral("fredag");
		Literal loerdag = model.createLiteral("lørdag");
		Literal soendag = model.createLiteral("søndag");
		
		// Class type
		Resource flextrening = model.createResource(gymURI + "Flex");
		Resource kondisjonstrening = model.createResource(gymURI + "Kondisjon");
		Resource styrketrening = model.createResource(gymURI + "Styrke");
		Resource dansetrening = model.createResource(gymURI + "Dans");
		Resource bassengtrening = model.createResource(gymURI + "Basseng");


		//relations
		Property hasTitle = model.createProperty(schemaURI + "title");
		Property legalName = model.createProperty(schemaURI + "legalName");
		Property onLocation = model.createProperty(schemaURI + "location");
		Literal location = model.createLiteral("Studentsenteret");
		Literal gym = model.createLiteral("SIB");
		Property typeof = model.createProperty(schemaURI + "typeof");
		Property sameAs = model.createProperty(schemaURI + "sameAs");


		//saltimer
		Resource yoga1 = model.createResource(gymURI + "Yoga11"); 
			yoga1.addProperty(typeof, flextrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "Yoga").addLiteral(sameAs, "Yoga");
		Resource yoga2 = model.createResource(gymURI + "Yoga12"); 
			yoga2.addProperty(typeof, flextrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "Yoga").addLiteral(sameAs, "Yoga");
		Resource yoga3 = model.createResource(gymURI + "Yoga13"); 
			yoga3.addProperty(typeof, flextrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "Yoga").addLiteral(sameAs, "Yoga");
		Resource yoga4 = model.createResource(gymURI + "Yoga14"); 
			yoga4.addProperty(typeof, flextrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "Yoga").addLiteral(sameAs, "Yoga");
		Resource pilates = model.createResource(gymURI + "Pilates10"); 
			pilates.addProperty(typeof, flextrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "Pilates").addLiteral(sameAs, "Pilates");
		Resource stepMoves = model.createResource(gymURI + "StepMoves10"); 
			stepMoves.addProperty(typeof, kondisjonstrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "StepMoves").addLiteral(sameAs, "Step");
		Resource stepIntervall = model.createResource(gymURI + "StepIntervall10");
			stepIntervall.addProperty(typeof, kondisjonstrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "StepIntervall").addLiteral(sameAs, "Step");
		Resource tabata1 = model.createResource(gymURI + "Tabata11"); 
			tabata1.addProperty(typeof, styrketrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "Tabata").addLiteral(sameAs, "Tabata");
		Resource tabata2 = model.createResource(gymURI + "Tabata12"); 
			tabata2.addProperty(typeof, styrketrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "Tabata").addLiteral(sameAs, "Tabata");	
		Resource styrkeStang1 = model.createResource(gymURI + "StyrkeMedStang11"); 
			styrkeStang1.addProperty(typeof, styrketrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "StyrkeMedStang").addLiteral(sameAs, "StyrkeMedStang");
		Resource styrkeStang2 = model.createResource(gymURI + "StyrkeMedStang12"); 
			styrkeStang2.addProperty(typeof, styrketrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "StyrkeMedStang").addLiteral(sameAs, "StyrkeMedStang");
		Resource styrkeStang3 = model.createResource(gymURI + "StyrkeMedStang13"); 
			styrkeStang3.addProperty(typeof, styrketrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "StyrkeMedStang").addLiteral(sameAs, "StyrkeMedStang");
		Resource zumba = model.createResource(gymURI + "Zumba10"); 
			zumba.addProperty(typeof, dansetrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "Zumba").addLiteral(sameAs, "Zumba");
		Resource basisballRaw = model.createResource(gymURI + "BasisballRaw10"); 
			basisballRaw.addProperty(typeof, styrketrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "basisballRaw").addLiteral(sameAs, "Basisball");
		
		//spinning
		Resource pulsTopp551 = model.createResource(gymURI + "Pulstopp5511"); 
			pulsTopp551.addProperty(typeof, kondisjonstrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "PulsTopp55").addLiteral(sameAs, "Spinning");
		Resource pulsTopp552 = model.createResource(gymURI + "Pulstopp5512"); 
			pulsTopp552.addProperty(typeof, kondisjonstrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "PulsTopp55").addLiteral(sameAs, "Spinning");
		Resource pulsTopp553 = model.createResource(gymURI + "Pulstopp5513"); 
			pulsTopp553.addProperty(typeof, kondisjonstrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "PulsTopp55").addLiteral(sameAs, "Spinning");
		Resource pulsTopp554 = model.createResource(gymURI + "Pulstopp5514"); 
			pulsTopp554.addProperty(typeof, kondisjonstrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "PulsTopp55").addLiteral(sameAs, "Spinning");
		Resource pulsTopp555 = model.createResource(gymURI + "Pulstopp5515"); 
			pulsTopp555.addProperty(typeof, kondisjonstrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "PulsTopp55").addLiteral(sameAs, "Spinning");
		Resource pulsTopp556 = model.createResource(gymURI + "Pulstopp5516"); 
			pulsTopp556.addProperty(typeof, kondisjonstrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "PulsTopp55").addLiteral(sameAs, "Spinning");
		Resource intervall45 = model.createResource(gymURI + "Intervall4510"); 
			intervall45.addProperty(typeof, kondisjonstrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "Intervall45").addLiteral(sameAs, "Spinning");
		Resource intervall551 = model.createResource(gymURI + "Intervall55101"); 
			intervall551.addProperty(typeof, kondisjonstrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "Intervall55").addLiteral(sameAs, "Spinning");
		Resource intervall552 = model.createResource(gymURI + "intervall5512"); 
			intervall552.addProperty(typeof, kondisjonstrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "Intervall55").addLiteral(sameAs, "Spinning");
		Resource intervall553 = model.createResource(gymURI + "intervall5513"); 
			intervall553.addProperty(typeof, kondisjonstrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "Intervall55").addLiteral(sameAs, "Spinning");
		Resource raceday75 = model.createResource(gymURI + "Raceday7510"); 
			raceday75.addProperty(typeof, kondisjonstrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "RaceDay75").addLiteral(sameAs, "Spinning");
		Resource intervall90 = model.createResource(gymURI + "Intervall9010");
			intervall90.addProperty(typeof, kondisjonstrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "Intervall90").addLiteral(sameAs, "Spinning");
		
		//basseng
		Resource aquaIntervall1 = model.createResource(gymURI + "AqualIntervall11"); 
			aquaIntervall1.addProperty(typeof, bassengtrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "AquaIntervall").addLiteral(sameAs, "Basseng");
		Resource aquaIntervall2 = model.createResource(gymURI + "AqualIntervall12"); 
			aquaIntervall2.addProperty(typeof, bassengtrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "AquaIntervall").addLiteral(sameAs, "Basseng");
		
		//roing
		Resource intervallPyramide1 = model.createResource(gymURI + "IntervallPyramide11"); 
			intervallPyramide1.addProperty(typeof, kondisjonstrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "IntervallPyramide-Roing").addLiteral(sameAs, "Roing");
		Resource intervallPyramide2 = model.createResource(gymURI + "IntervallPyramide12"); 
			intervallPyramide2.addProperty(typeof, kondisjonstrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "IntervallPyramide-Roing").addLiteral(sameAs, "Roing");
		Resource intervallPyramide3 = model.createResource(gymURI + "IntervallPyramide13"); 
			intervallPyramide3.addProperty(typeof, kondisjonstrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "IntervallPyramide-Roing").addLiteral(sameAs, "Roing");
		Resource intervallPyramide4 = model.createResource(gymURI + "IntervallPyramide14"); 
			intervallPyramide4.addProperty(typeof, kondisjonstrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "IntervallPyramide-Roing").addLiteral(sameAs, "Roing");
		Resource intervallPyramide5 = model.createResource(gymURI + "IntervallPyramide15"); 
			intervallPyramide5.addProperty(typeof, kondisjonstrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "IntervallPyramide-Roing").addLiteral(sameAs, "Roing");

		
		// Mandag
		yoga1.addProperty(starttid, "07:30").addProperty(dag, mandag).addProperty(varighet, "55").addProperty(instructor, "Randi");
		intervallPyramide1.addProperty(starttid, "08:430").addProperty(dag, mandag).addProperty(varighet, "60").addProperty(instructor, "Eivind");
		pulsTopp551.addProperty(starttid, "16:15").addProperty(dag, mandag).addProperty(varighet, "55").addProperty(instructor, "Gina");
		stepMoves.addProperty(starttid, "16:45").addProperty(dag, mandag).addProperty(varighet, "55").addProperty(instructor, "Heidi");
		intervall45.addProperty(starttid, "18:00").addProperty(dag, mandag).addProperty(varighet, "45").addProperty(instructor, "Heidi");
		tabata1.addProperty(starttid, "18:00").addProperty(dag, mandag).addProperty(varighet, "45").addProperty(instructor, "Anna");
		intervall551.addProperty(starttid, "19:00").addProperty(dag, mandag).addProperty(varighet, "55").addProperty(instructor, "Anna");
		yoga2.addProperty(starttid, "19:00").addProperty(dag, mandag).addProperty(varighet, "55").addProperty(instructor, "");
		aquaIntervall1.addProperty(starttid, "20:00").addProperty(dag, mandag).addProperty(varighet, "45").addProperty(instructor, "Gina");
		styrkeStang1.addProperty(starttid, "20:00").addProperty(dag, mandag).addProperty(varighet, "55").addProperty(instructor, "Astrid Maria");
		
		// Tirsdag
		zumba.addProperty(starttid, "17:00").addProperty(dag, tirsdag).addProperty(varighet, "55").addProperty(instructor, "Jazmin");
		intervall90.addProperty(starttid, "17:30").addProperty(dag, tirsdag).addProperty(varighet, "90").addProperty(instructor, "Odin");
		intervallPyramide2.addProperty(starttid, "19:00").addProperty(dag, tirsdag).addProperty(varighet, "60").addProperty(instructor, "Jørgen");
		tabata2.addProperty(starttid, "19:10").addProperty(dag, tirsdag).addProperty(varighet, "45").addProperty(instructor, "Astrid Maria");
		pulsTopp552.addProperty(starttid, "19:15").addProperty(dag, tirsdag).addProperty(varighet, "55").addProperty(instructor, "Emilie");
		
		// Onsdag
		aquaIntervall2.addProperty(starttid, "15:00").addProperty(dag, onsdag).addProperty(varighet, "45").addProperty(instructor, "Gina");
		intervallPyramide3.addProperty(starttid, "17:00").addProperty(dag, onsdag).addProperty(varighet, "60").addProperty(instructor, "Eiving");
		yoga3.addProperty(starttid, "18:00").addProperty(dag, onsdag).addProperty(varighet, "55").addProperty(instructor, "Randi");
		pulsTopp553.addProperty(starttid, "19:00").addProperty(dag, onsdag).addProperty(varighet, "55").addProperty(instructor, "Brynhild");
		basisballRaw.addProperty(starttid, "19:10").addProperty(dag, onsdag).addProperty(varighet, "45").addProperty(instructor, "Veronika");
		pilates.addProperty(starttid, "20:00").addProperty(dag, onsdag).addProperty(varighet, "55").addProperty(instructor, "Catrine");
		
		// Torsdag
		pulsTopp554.addProperty(starttid, "08:15").addProperty(dag, torsdag).addProperty(varighet, "55").addProperty(instructor, "Gina");
		yoga4.addProperty(starttid, "12:15").addProperty(dag, torsdag).addProperty(varighet, "55").addProperty(instructor, "Olga");
		intervall552.addProperty(starttid, "16:15").addProperty(dag, torsdag).addProperty(varighet, "55").addProperty(instructor, "Espen");
		pulsTopp555.addProperty(starttid, "17:30").addProperty(dag, torsdag).addProperty(varighet, "55").addProperty(instructor, "Trudi");
		styrkeStang2.addProperty(starttid, "18:15").addProperty(dag, torsdag).addProperty(varighet, "55").addProperty(instructor, "Tonje N");
		intervallPyramide4.addProperty(starttid, "19:00").addProperty(dag, torsdag).addProperty(varighet, "60").addProperty(instructor, "Amandus");
		
		// Fredag
		stepIntervall.addProperty(starttid, "16:00").addProperty(dag, fredag).addProperty(varighet, "55").addProperty(instructor, "Linn E");
		raceday75.addProperty(starttid, "17:15").addProperty(dag, fredag).addProperty(varighet, "75").addProperty(instructor, "Linn E");
		intervallPyramide5.addProperty(starttid, "17:30").addProperty(dag, fredag).addProperty(varighet, "60").addProperty(instructor, "J�rgen");
		
		// Lørdag
		intervall553.addProperty(starttid, "13:00").addProperty(dag, loerdag).addProperty(varighet, "55").addProperty(instructor, "Ann Kristin M");
		
		// Søndag
		pulsTopp556.addProperty(starttid, "17:00").addProperty(dag, soendag).addProperty(varighet, "55").addProperty(instructor, "Linn E");
		styrkeStang3.addProperty(starttid, "18:15").addProperty(dag, soendag).addProperty(varighet, "55").addProperty(instructor, "Linn E");

		
	}
}