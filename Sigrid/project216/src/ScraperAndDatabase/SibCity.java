package CleanCode;


/**
 * SIB City/Studentsenteret
 * 
 * I denne klassen har vi manuelt lagt til all data. 
 * Dette var en midlertidlig løsning siden SIB's timeplan ikke
 * var så enkel å skrape. I fremtiden håper vi at deres data blir 
 * mer tilgjengelig slik at vi kan oppdatere timeplanen hyppig.
 */

import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

public class SIBCity {
		

	public Model model = ModelFactory.createDefaultModel();
	private String gymURI = "http://findmyfitness/";
	private String schemaURI = "http://schema.org/";
	
	
	public SIBCity(){
		create();
	}
	
	private void create(){
			
		Property typeof = model.createProperty(schemaURI + "typeof");
		
		Resource flextrening = model.createResource(gymURI + "Flex");
		Resource kondisjonstrening = model.createResource(gymURI + "Kondisjon");
		Resource styrketrening = model.createResource(gymURI + "Styrke");
		Resource dansetrening = model.createResource(gymURI + "Dans");
	
		Property hasTitle = model.createProperty(schemaURI + "title");
		Property sameAs = model.createProperty(schemaURI + "sameAs");
		Property legalName = model.createProperty(schemaURI + "legalName");
		Property onLocation = model.createProperty(schemaURI + "location");
		
		Literal location = model.createLiteral("City");
		Literal gym = model.createLiteral("SIB");
		

		//Saltimer
		Resource yoga1 = model.createResource(gymURI + "Yoga1");
			yoga1.addProperty(typeof, flextrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "Yoga").addLiteral(sameAs, "Yoga");
		Resource yoga2 = model.createResource(gymURI + "Yoga2");
			yoga2.addProperty(typeof, flextrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "Yoga").addLiteral(sameAs, "Yoga");
		Resource yoga3 = model.createResource(gymURI + "Yoga3");
			yoga3.addProperty(typeof, flextrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "Yoga").addLiteral(sameAs, "Yoga");
		Resource yoga4 = model.createResource(gymURI + "Yoga4");
			yoga4.addProperty(typeof, flextrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "Yoga").addLiteral(sameAs, "Yoga"); 
		Resource yoga5 = model.createResource(gymURI + "Yoga5");
			yoga5.addProperty(typeof, flextrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "Yoga").addLiteral(sameAs, "Yoga"); 
		Resource yoga75 = model.createResource(gymURI + "Yoga75"); 
			yoga75.addProperty(typeof, flextrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "Yoga75").addLiteral(sameAs, "Yoga"); 
		Resource pilates1 = model.createResource(gymURI + "Pilates1"); 
			pilates1.addProperty(typeof, flextrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "Pilates").addLiteral(sameAs, "Pilates");
		Resource pilates2 = model.createResource(gymURI + "Pilates2"); 
			pilates2.addProperty(typeof, flextrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "Pilates").addLiteral(sameAs, "Pilates");
		Resource stepIntervall = model.createResource(gymURI + "StepIntervall"); 
			stepIntervall.addProperty(typeof, kondisjonstrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "StepIntervall").addLiteral(sameAs, "Step"); 
		Resource tabata1 = model.createResource(gymURI + "Tabata1"); 
			tabata1.addProperty(typeof, styrketrening.getLocalName()).addLiteral(onLocation, location)
			.addLiteral(legalName, gym).addLiteral(hasTitle, "Tabata").addLiteral(sameAs, "Tabata"); 
		Resource tabata2 = model.createResource(gymURI + "Tabata2"); 
			tabata2.addProperty(typeof, styrketrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "Tabata").addLiteral(sameAs, "Tabata"); 
		Resource tabata3 = model.createResource(gymURI + "Tabata3"); 
			tabata3.addProperty(typeof, styrketrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "Tabata").addLiteral(sameAs, "Tabata");	
		Resource tabata4 = model.createResource(gymURI + "Tabata4"); 
			tabata4.addProperty(typeof, styrketrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "Tabata").addLiteral(sameAs, "Tabata");
		Resource zumba1 = model.createResource(gymURI + "Zumba1"); 
			zumba1.addProperty(typeof, dansetrening.getLocalName()).addLiteral(onLocation, location).
				addLiteral(legalName, gym).addLiteral(hasTitle, "Zumba").addLiteral(sameAs, "Zumba"); 
		Resource zumba2 = model.createResource(gymURI + "Zumba2");
			zumba2.addProperty(typeof, dansetrening.getLocalName()).addLiteral(onLocation, location).
				addLiteral(legalName, gym).addLiteral(hasTitle, "Zumba").addLiteral(sameAs, "Zumba"); 
		Resource prama1 = model.createResource(gymURI + "PRAMA1"); 
			prama1.addProperty(typeof, styrketrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "PRAMA").addLiteral(sameAs, "Prama");
		Resource prama2 = model.createResource(gymURI + "PRAMA2"); 
			prama2.addProperty(typeof, styrketrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "PRAMA").addLiteral(sameAs, "Prama");	
		Resource prama3 = model.createResource(gymURI + "PRAMA3"); 
			prama3.addProperty(typeof, styrketrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "PRAMA").addLiteral(sameAs, "Prama");
		Resource prama4 = model.createResource(gymURI + "PRAMA4"); 
			prama4.addProperty(typeof, styrketrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "PRAMA").addLiteral(sameAs, "Prama");
		Resource prama5 = model.createResource(gymURI + "PRAMA5"); 
			prama5.addProperty(typeof, styrketrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "PRAMA").addLiteral(sameAs, "Prama");
		Resource raasterk1 = model.createResource(gymURI + "RåSTERK1"); 
			raasterk1.addProperty(typeof, styrketrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "Råsterk").addLiteral(sameAs, "RåSterk");
		Resource raasterk2 = model.createResource(gymURI + "RåSTERK2"); 
			raasterk2.addProperty(typeof, styrketrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "Råsterk").addLiteral(sameAs, "RåSterk");
		Resource raasterk3 = model.createResource(gymURI + "RåSTERK3"); 
			raasterk3.addProperty(typeof, styrketrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "Råsterk").addLiteral(sameAs, "RåSterk");
		Resource TRXslyngetrening1 = model.createResource(gymURI + "TRXSlyngetrening1"); 
			TRXslyngetrening1.addProperty(typeof, styrketrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "TRXSlyngeTrening").addLiteral(sameAs, "Slyngetrening");
		Resource TRXslyngetrening2 = model.createResource(gymURI + "TRXSlyngetrening2"); 
			TRXslyngetrening2.addProperty(typeof, styrketrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "TRXSlyngeTrening").addLiteral(sameAs, "Slyngetrening");
		Resource TRXslyngetrening3 = model.createResource(gymURI + "TRXSlyngetrening3"); 
			TRXslyngetrening3.addProperty(typeof, styrketrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "TRXSlyngeTrening").addLiteral(sameAs, "Slyngetrening");
		Resource basisballRIT1 = model.createResource(gymURI + "BasisballRIT1");
			basisballRIT1.addProperty(typeof, styrketrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "BasisballRIT").addLiteral(sameAs, "Basisball");
		Resource basisballRIT2 = model.createResource(gymURI + "BasisballRIT2");
			basisballRIT2.addProperty(typeof, styrketrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "BasisballRIT").addLiteral(sameAs, "Basisball");
		Resource movementFlow = model.createResource(gymURI + "MovementFlow");
			movementFlow.addProperty(typeof, flextrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "MovementFlow").addLiteral(sameAs, "MovementFlow"); 
		Resource mobilityStretch = model.createResource(gymURI + "Mobility");
			mobilityStretch.addProperty(typeof, flextrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "MobilityStretch").addLiteral(sameAs, "mobilityStretch"); 
		Resource stang1 = model.createResource(gymURI + "StyrkeMedStang1");
			stang1.addProperty(typeof, styrketrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "StyrkeMedStang").addLiteral(sameAs, "StyrkeMedStang"); 
		Resource stang2 = model.createResource(gymURI + "StyrkeMedStang2");
			stang2.addProperty(typeof, styrketrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "StyrkeMedStang").addLiteral(sameAs, "StyrkeMedStang"); 
		Resource HIITABS = model.createResource(gymURI + "HIITABS");
			HIITABS.addProperty(typeof, styrketrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "HIITABS").addLiteral(sameAs, "ABS");
		 Resource stepBasic = model.createResource(gymURI + "StepBasic");
		 	stepBasic.addProperty(typeof, kondisjonstrening.getLocalName()).addLiteral(onLocation, location)
		 		.addLiteral(legalName, gym).addLiteral(hasTitle, "StepBasic").addLiteral(sameAs, "Step"); 
	
		
		//Spinning
		Resource pulstopp551 = model.createResource(gymURI + "Pulstopp551"); 
			pulstopp551.addProperty(typeof, kondisjonstrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "PulsTopp55").addLiteral(sameAs, "Spinning"); 
		Resource pulstopp552 = model.createResource(gymURI + "Pulstopp552"); 
			pulstopp552.addProperty(typeof, kondisjonstrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "PulsTopp55").addLiteral(sameAs, "Spinning"); 
		Resource pulstopp553 = model.createResource(gymURI + "Pulstopp553"); 
			pulstopp553.addProperty(typeof, kondisjonstrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "PulsTopp55").addLiteral(sameAs, "Spinning"); 
		Resource intervall451 = model.createResource(gymURI + "Intervall451"); 
			intervall451.addProperty(typeof, kondisjonstrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "Intervall45").addLiteral(sameAs, "Spinning");
			Resource intervall452 = model.createResource(gymURI + "Intervall452"); 
			intervall452.addProperty(typeof, kondisjonstrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "Intervall45").addLiteral(sameAs, "Spinning");
		Resource intervall551 = model.createResource(gymURI + "Intervall551"); 
			intervall551.addProperty(typeof, kondisjonstrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "Intervall55").addLiteral(sameAs, "Spinning");
		Resource intervall552 = model.createResource(gymURI + "Intervall552"); 
			intervall552.addProperty(typeof, kondisjonstrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "Intervall55").addLiteral(sameAs, "Spinning");
		Resource intervall553 = model.createResource(gymURI + "Intervall553"); 
			intervall553.addProperty(typeof, kondisjonstrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "Intervall55").addLiteral(sameAs, "Spinning");
	
		//loepetime
		Resource moelle4x4 = model.createResource(gymURI + "SIBMølle4x4");
			moelle4x4.addProperty(typeof, kondisjonstrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "SibMølle4x4").addLiteral(sameAs, "Run");
		Resource moellePyramide1 = model.createResource(gymURI + "SIBMøllePyramide1");
			moellePyramide1.addProperty(typeof, kondisjonstrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "SIBMøllePyramide").addLiteral(sameAs, "Run");
		Resource moellePyramide2 = model.createResource(gymURI + "SIBMøllePyramide2");
			moellePyramide2.addProperty(typeof, kondisjonstrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "SIBMøllePyramide").addLiteral(sameAs, "Run");
		Resource moelleIntervall = model.createResource(gymURI + "SIBmølleintervall");
			moelleIntervall.addProperty(typeof, kondisjonstrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "SIBmølleintervall").addLiteral(sameAs, "Run");
		Resource moelle30 = model.createResource(gymURI + "SIBmølle30");
			moelle30.addProperty(typeof, kondisjonstrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "SIBmølle30").addLiteral(sameAs, "Run"); 
		Resource moelle45_15 = model.createResource(gymURI + "SIBmølle4515");
			moelle45_15.addProperty(typeof, kondisjonstrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "SIBmølle4515").addLiteral(sameAs, "Run");
		Resource moelleVario = model.createResource(gymURI + "SIBmøllevario");
			moelleVario.addProperty(typeof, kondisjonstrening.getLocalName()).addLiteral(onLocation, location)
				.addLiteral(legalName, gym).addLiteral(hasTitle, "SIBMølleVario").addLiteral(sameAs, "Run");
		
		
		//Property tid = model.createProperty(gymURI + "Tid");
	
		Literal mandag = model.createLiteral("mandag");
		Literal tirsdag = model.createLiteral("tirsdag");
		Literal onsdag = model.createLiteral("onsdag");
		Literal torsdag = model.createLiteral("torsdag");
		Literal fredag = model.createLiteral("fredag");
		Literal loerdag = model.createLiteral("lørdag");
		Literal soendag = model.createLiteral("søndag");
		
		
		
		// Tid / dag / varighet / instruktoer
		Property starttid = model.createProperty(schemaURI + "startTime");
		Property varighet = model.createProperty(schemaURI + "duration");
		Property dag = model.createProperty(schemaURI + "dayOfWeek");
		Property instruktoer = model.createProperty(schemaURI + "instructor");
	
		 
		//mandag
		intervall551.addProperty(starttid, "07:00").addProperty(dag, mandag).addProperty(varighet, "55").addProperty(instruktoer, "Linn E");
		raasterk1.addProperty(starttid, "15:00").addProperty(dag, mandag).addProperty(varighet, "60").addProperty(instruktoer, "Alexander");
		TRXslyngetrening1.addProperty(starttid, "16:00").addProperty(dag, mandag).addProperty(varighet, "55").addProperty(instruktoer, "Benjamin");
		pilates1.addProperty(starttid, "17:00").addProperty(dag, mandag).addProperty(varighet, "55").addProperty(instruktoer, "Olga");
		prama1.addProperty(starttid, "17:00").addProperty(dag, mandag).addProperty(varighet, "45").addProperty(instruktoer, "Kathrine H");
		moelle4x4.addProperty(starttid, "18:00").addProperty(dag, mandag).addProperty(varighet, "45").addProperty(instruktoer, "Sigrun");
		tabata1.addProperty(starttid, "18:00").addProperty(dag, mandag).addProperty(varighet, "45").addProperty(instruktoer, "Tonje N");
		basisballRIT1.addProperty(starttid, "19:00").addProperty(dag, mandag).addProperty(varighet, "45").addProperty(instruktoer, "Maria D");
		intervall451.addProperty(starttid, "19:00").addProperty(dag, mandag).addProperty(varighet, "45").addProperty(instruktoer, "Trudi");
		yoga1.addProperty(starttid, "20:00").addProperty(dag, mandag).addProperty(varighet, "55").addProperty(instruktoer, "Emilie T");
		
		//tirsdag
		intervall452.addProperty(starttid, "07:00").addProperty(dag, tirsdag).addProperty(varighet, "45").addProperty(instruktoer, "Ragnhild");
		tabata2.addProperty(starttid, "08:00").addProperty(dag, tirsdag).addProperty(varighet, "45").addProperty(instruktoer, "Ragnhild");
		TRXslyngetrening2.addProperty(starttid, "11:00").addProperty(dag, tirsdag).addProperty(varighet, "45").addProperty(instruktoer, "Ann-Kristin R");
		movementFlow.addProperty(starttid, "16:00").addProperty(dag, tirsdag).addProperty(varighet, "55").addProperty(instruktoer, "Tom");
		pulstopp552.addProperty(starttid, "17:00").addProperty(dag, tirsdag).addProperty(varighet, "55").addProperty(instruktoer, "Espen");
		mobilityStretch.addProperty(starttid, "17:00").addProperty(dag, tirsdag).addProperty(varighet, "55").addProperty(instruktoer, "Tom");
		stang1.addProperty(starttid, "18:00").addProperty(dag, tirsdag).addProperty(varighet, "55").addProperty(instruktoer, "Charlotte");
		prama2.addProperty(starttid, "16:00").addProperty(dag, tirsdag).addProperty(varighet, "45").addProperty(instruktoer, "Kathrine H");
		yoga75.addProperty(starttid, "19:00").addProperty(dag, tirsdag).addProperty(varighet, "75").addProperty(instruktoer, "Agnes");
		moellePyramide1.addProperty(starttid, "19:05").addProperty(dag, tirsdag).addProperty(varighet, "45").addProperty(instruktoer, "Charlotte");
		
		//onsdag
		HIITABS.addProperty(starttid, "07:10").addProperty(dag, onsdag).addProperty(varighet, "45").addProperty(instruktoer, "Ronja");
		yoga2.addProperty(starttid, "08:00").addProperty(dag, onsdag).addProperty(varighet, "55").addProperty(instruktoer, "Live");
		raasterk2.addProperty(starttid, "10:00").addProperty(dag, onsdag).addProperty(varighet, "60").addProperty(instruktoer, "Alexander");
		intervall552.addProperty(starttid, "16:15").addProperty(dag, onsdag).addProperty(varighet, "55").addProperty(instruktoer, "Jannik");
		pilates2.addProperty(starttid, "17:00").addProperty(dag, onsdag).addProperty(varighet, "55").addProperty(instruktoer, "Sigrun");
		moellePyramide2.addProperty(starttid, "18:00").addProperty(dag, onsdag).addProperty(varighet, "45").addProperty(instruktoer, "Patrik");
		prama3.addProperty(starttid, "18:00").addProperty(dag, onsdag).addProperty(varighet, "45").addProperty(instruktoer, "Omar");
		zumba1.addProperty(starttid, "18:00").addProperty(dag, onsdag).addProperty(varighet, "55").addProperty(instruktoer, "Carita");
		pulstopp551.addProperty(starttid, "19:00").addProperty(dag, onsdag).addProperty(varighet, "45").addProperty(instruktoer, "Patrik");
		stepBasic.addProperty(starttid, "19:00").addProperty(dag, onsdag).addProperty(varighet, "55").addProperty(instruktoer, "Vilde");
		tabata3.addProperty(starttid, "19:00").addProperty(dag, onsdag).addProperty(varighet, "45").addProperty(instruktoer, "Vilde");
		
		//torsdag
		moelleIntervall.addProperty(starttid, "06:45").addProperty(dag, torsdag).addProperty(varighet, "45").addProperty(instruktoer, "Thomas H");
		raasterk3.addProperty(starttid, "16:00").addProperty(dag, torsdag).addProperty(varighet, "60").addProperty(instruktoer, "Alexander");
		moelle30.addProperty(starttid, "16:15").addProperty(dag, torsdag).addProperty(varighet, "30").addProperty(instruktoer, "Kaia");
		tabata4.addProperty(starttid, "17:00").addProperty(dag, torsdag).addProperty(varighet, "45").addProperty(instruktoer, "Kaia");
		intervall553.addProperty(starttid, "17:15").addProperty(dag, torsdag).addProperty(varighet, "55").addProperty(instruktoer, "Omar");
		TRXslyngetrening3.addProperty(starttid, "18:00").addProperty(dag, torsdag).addProperty(varighet, "55").addProperty(instruktoer, "Anwar");
		basisballRIT2.addProperty(starttid, "19:00").addProperty(dag, torsdag).addProperty(varighet, "45").addProperty(instruktoer, "Karolina");
		prama4.addProperty(starttid, "19:00").addProperty(dag, torsdag).addProperty(varighet, "45").addProperty(instruktoer, "Anwar");
		yoga3.addProperty(starttid, "20:00").addProperty(dag, torsdag).addProperty(varighet, "55").addProperty(instruktoer, "Aurora");
		
		//fredag
		yoga4.addProperty(starttid, "07:00").addProperty(dag, fredag).addProperty(varighet, "55").addProperty(instruktoer, "Live");
		prama5.addProperty(starttid, "16:00").addProperty(dag, fredag).addProperty(varighet, "45").addProperty(instruktoer, "Maria D");
		zumba2.addProperty(starttid, "16:00").addProperty(dag, fredag).addProperty(varighet, "55").addProperty(instruktoer, "Candida");
		moelle45_15.addProperty(starttid, "17:00").addProperty(dag, fredag).addProperty(varighet, "45").addProperty(instruktoer, "Benjamin");
		stepIntervall.addProperty(starttid, "17:30").addProperty(dag, fredag).addProperty(varighet, "55").addProperty(instruktoer, "Louise");
	
		//loerdag 
		moelleVario.addProperty(starttid, "10:00").addProperty(dag, loerdag).addProperty(varighet, "45").addProperty(instruktoer, "-");
		stang2.addProperty(starttid, "11:00").addProperty(dag, loerdag).addProperty(varighet, "55").addProperty(instruktoer, "Helene");
		
	
		//soendag
		yoga5.addProperty(starttid, "10:15").addProperty(dag, soendag).addProperty(varighet, "55").addProperty(instruktoer, "Olga");;
		pulstopp553.addProperty(starttid, "18:00").addProperty(dag, soendag).addProperty(varighet, "55").addProperty(instruktoer, "Tarald");
	}
}
