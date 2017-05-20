package CleanCode;

import java.util.ArrayList;

import java.io.FileOutputStream;

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
import org.apache.jena.vocabulary.VCARD;

public class SibCity {
		

	public Model model = ModelFactory.createDefaultModel();
//	private Resource city = (Resource) model.createResource(gymURI);
	// URI
	private String gymURI = "http://findmyfitness/";
	private String schemaURI = "http://schema.org/";
	
	
	public SibCity(){
		create();
	}
	
	private void create(){
		
	Property typeof = model.createProperty(schemaURI + "typeof");
	Property harFasilitet = model.createProperty(gymURI + "harFasilitet");
	
//	Resource vekter = model.createResource(gymURI + "vekter");
//	Resource garderobe = model.createResource(gymURI + "garderobe");
//	Resource spinningtime = model.createResource(gymURI + "spinning");
//	Resource saltime = model.createResource(gymURI + "saltime");
//	Resource loepetime = model.createResource(gymURI + "loepetime");
	
//	city.addProperty(harFasilitet, vekter)
//		.addLiteral(harFasilitet, garderobe)
//			.addLiteral(harFasilitet, saltime);

	Resource flextrening = model.createResource(gymURI + "Flex");
	Resource kondisjonstrening = model.createResource(gymURI + "Kondisjon");
	Resource styrketrening = model.createResource(gymURI + "Styrke");
	Resource dansetrening = model.createResource(gymURI + "Styrke");
	Resource bassengtrening = model.createResource(gymURI + "Basseng");
	Resource mammatrening = model.createResource(gymURI + "MammaTrening");
	Resource seniortrening = model.createResource(gymURI + "SeniorTrening");

	Property hasTitle = model.createProperty(schemaURI + "title");
	Property sameAs = model.createProperty(schemaURI + "");
	
	
	Property hasDuration = model.createProperty(schemaURI + "duration");
	Property onDay = model.createProperty(schemaURI + "dayOfWeek");
	Property onTime = model.createProperty(schemaURI + "startTime");
	Property onDate = model.createProperty(schemaURI+ "startDate");
	Property onLocation = model.createProperty(schemaURI + "location");
	Property hasInstructor = model.createProperty(schemaURI + "instructor");
	Property legalName = model.createProperty(schemaURI + "legalName");
	
	
	
	
	//Saltimer
	Resource yoga = model.createResource(gymURI + "Yoga"); 
	yoga.addProperty(typeof, flextrening);
	Resource yoga75 = model.createResource(gymURI + "Yoga75"); 
	yoga75.addProperty(typeof, flextrening);
	Resource pilates = model.createResource(gymURI + "Pilates"); 
	pilates.addProperty(typeof, flextrening);
	Resource stepMoves = model.createResource(gymURI + "Stepmoves"); 
	stepMoves.addProperty(typeof, kondisjonstrening);
	Resource stepIntervall = model.createResource(gymURI + "StepIntervall"); 
	stepIntervall.addProperty(typeof, kondisjonstrening);
	Resource tabata = model.createResource(gymURI + "Tabata"); 
	tabata.addProperty(typeof, styrketrening);
	Resource styrkeStang = model.createResource(gymURI + "StyrkeMedStang"); 
	styrkeStang.addProperty(typeof, styrketrening);
	Resource zumba = model.createResource(gymURI + "Zumba"); 
	zumba.addProperty(typeof, dansetrening);
	Resource basisballRaw = model.createResource(gymURI + "BasisballRaw"); 
	basisballRaw.addProperty(typeof, styrketrening);
	Resource prama = model.createResource(gymURI + "PRAMA"); 
	prama.addProperty(typeof, styrketrening).addProperty(typeof, kondisjonstrening);
	Resource raasterk = model.createResource(gymURI + "RaaSTERK"); 
	raasterk.addProperty(typeof, styrketrening);
	Resource TRXslyngetrening = model.createResource(gymURI + "TRXSlyngetrening"); 
	TRXslyngetrening.addProperty(typeof, styrketrening);
	Resource basisballRIT = model.createResource(gymURI + "BasisballRIT");
	basisballRIT.addProperty(typeof, styrketrening);
	Resource movementFlow = model.createResource(gymURI + "MovementFlow");
	movementFlow.addProperty(typeof, flextrening);
	Resource mobilityStretch = model.createResource(gymURI + "MobilityStretch");
	mobilityStretch.addProperty(typeof, flextrening);
	Resource stang = model.createResource(gymURI + "StyrkeMedStang");
	stang.addProperty(typeof, styrketrening);
	Resource HIITABS = model.createResource(gymURI + "HIITABS");
	HIITABS.addProperty(typeof, styrketrening);
	 Resource stepBasic = model.createResource(gymURI + "StepBasic");
	stepBasic.addProperty(typeof, kondisjonstrening);

	
	//Spinning
	Resource pulstopp55 = model.createResource(gymURI + "Pulstopp55"); 
	pulstopp55.addProperty(typeof, kondisjonstrening);
	Resource intervall45 = model.createResource(gymURI + "Intervall45"); 
	intervall45.addProperty(typeof, kondisjonstrening);
	Resource intervall55 = model.createResource(gymURI + "Intervall55"); 
	intervall55.addProperty(typeof, kondisjonstrening);
	Resource intervall90 = model.createResource(gymURI + "Intervall90"); 
	intervall90.addProperty(typeof, kondisjonstrening);
//TODO
	//	Resource intervallPyramide = model.createResource(gymURI + "IntervallPyramide"); 
//	intervallPyramide.addProperty(typeof, kondisjonstrening);

	
	//loepetime
	Resource moelle4x4 = model.createResource(gymURI + "SIBMoelle4x4");
	moelle4x4.addProperty(typeof, kondisjonstrening);
	Resource moellePyramide = model.createResource(gymURI + "SIBMoellePyramide");
	moellePyramide.addProperty(typeof, kondisjonstrening);
	Resource moelleIntervall = model.createResource(gymURI + "SIBmoelleintervall");
	moelleIntervall.addProperty(typeof, kondisjonstrening);
	Resource moelle30 = model.createResource(gymURI + "SIBmoelle30");
	moelle30.addProperty(typeof, kondisjonstrening);
	Resource moelle45_15 = model.createResource(gymURI + "SIBmoelle4515");
	moelle45_15.addProperty(typeof, kondisjonstrening);
	Resource moelleVario = model.createResource(gymURI + "SIBmoellevario");
	moelleVario.addProperty(typeof, kondisjonstrening);
	
	
	//Property tid = model.createProperty(gymURI + "Tid");

	Literal mandag = model.createLiteral("Mandag");
	Literal tirsdag = model.createLiteral("Tirsdag");
	Literal onsdag = model.createLiteral("Onsdag");
	Literal torsdag = model.createLiteral("Torsdag");
	Literal fredag = model.createLiteral("Fredag");
	Literal loerdag = model.createLiteral("Loerdag");
	Literal soendag = model.createLiteral("Soendag");
	
	
	
	// Tid / dag / varighet / instruktoer
	Property starttid = model.createProperty(schemaURI + "StartTime");
	Property varighet = model.createProperty(schemaURI + "Duration");
	Property dag = model.createProperty(schemaURI + "DayOfWeek");
	Property instruktoer = model.createProperty(schemaURI + "Instructor");

	 
	//mandag
	intervall55.addProperty(starttid, "07:00").addProperty(dag, mandag).addProperty(varighet, "55 min").addProperty(instruktoer, "Linn E");
	raasterk.addProperty(starttid, "15:00").addProperty(dag, mandag).addProperty(varighet, "60 min").addProperty(instruktoer, "Alexander");
	TRXslyngetrening.addProperty(starttid, "16:00").addProperty(dag, mandag).addProperty(varighet, "55 min").addProperty(instruktoer, "Benjamin");
	pilates.addProperty(starttid, "17:00").addProperty(dag, mandag).addProperty(varighet, "55 min").addProperty(instruktoer, "Olga");
	prama.addProperty(starttid, "17:00").addProperty(dag, mandag).addProperty(varighet, "45 min").addProperty(instruktoer, "Kathrine H");
	moelle4x4.addProperty(starttid, "18:00").addProperty(dag, mandag).addProperty(varighet, "45 min").addProperty(instruktoer, "Sigrun");
	tabata.addProperty(starttid, "18:00").addProperty(dag, mandag).addProperty(varighet, "45 min").addProperty(instruktoer, "Tonje N");
	basisballRIT.addProperty(starttid, "19:00").addProperty(dag, mandag).addProperty(varighet, "45 min").addProperty(instruktoer, "Maria D");
	intervall45.addProperty(starttid, "19:00").addProperty(dag, mandag).addProperty(varighet, "45 min").addProperty(instruktoer, "Trudi");
	yoga.addProperty(starttid, "20:00").addProperty(dag, mandag).addProperty(varighet, "55 min").addProperty(instruktoer, "Emilie T");
	
	//tirsdag
	intervall45.addProperty(starttid, "07:00").addProperty(dag, tirsdag).addProperty(varighet, "45 min").addProperty(instruktoer, "Ragnhild");
	tabata.addProperty(starttid, "08:00").addProperty(dag, tirsdag).addProperty(varighet, "45 min").addProperty(instruktoer, "Ragnhild");
	TRXslyngetrening.addProperty(starttid, "11:00").addProperty(dag, tirsdag).addProperty(varighet, "45 min").addProperty(instruktoer, "Ann-Kristin R");
	movementFlow.addProperty(starttid, "16:00").addProperty(dag, tirsdag).addProperty(varighet, "55 min").addProperty(instruktoer, "Tom");
	pulstopp55.addProperty(starttid, "17:00").addProperty(dag, tirsdag).addProperty(varighet, "55 min").addProperty(instruktoer, "Espen");
	mobilityStretch.addProperty(starttid, "17:00").addProperty(dag, tirsdag).addProperty(varighet, "55 min").addProperty(instruktoer, "Tom");
	stang.addProperty(starttid, "18:00").addProperty(dag, tirsdag).addProperty(varighet, "55 min").addProperty(instruktoer, "Charlotte");
	prama.addProperty(starttid, "16:00").addProperty(dag, tirsdag).addProperty(varighet, "45 min").addProperty(instruktoer, "Kathrine H");
	yoga75.addProperty(starttid, "19:00").addProperty(dag, tirsdag).addProperty(varighet, "75 min").addProperty(instruktoer, "Agnes");
	moellePyramide.addProperty(starttid, "19:05").addProperty(dag, tirsdag).addProperty(varighet, "45 min").addProperty(instruktoer, "Charlotte");
	
	//onsdag
	HIITABS.addProperty(starttid, "07:10").addProperty(dag, onsdag).addProperty(varighet, "45 min").addProperty(instruktoer, "Ronja");
	yoga.addProperty(starttid, "08:00").addProperty(dag, onsdag).addProperty(varighet, "55 min").addProperty(instruktoer, "Live");
	raasterk.addProperty(starttid, "10:00").addProperty(dag, onsdag).addProperty(varighet, "60 min").addProperty(instruktoer, "Alexander");
	intervall55.addProperty(starttid, "16:15").addProperty(dag, onsdag).addProperty(varighet, "55 min").addProperty(instruktoer, "Jannik");
	pilates.addProperty(starttid, "17:00").addProperty(dag, onsdag).addProperty(varighet, "55 min").addProperty(instruktoer, "Sigrun");
	moellePyramide.addProperty(starttid, "18:00").addProperty(dag, onsdag).addProperty(varighet, "45 min").addProperty(instruktoer, "Patrik");
	prama.addProperty(starttid, "18:00").addProperty(dag, onsdag).addProperty(varighet, "45 min").addProperty(instruktoer, "Omar");
	zumba.addProperty(starttid, "18:00").addProperty(dag, onsdag).addProperty(varighet, "55 min").addProperty(instruktoer, "Carita");
	pulstopp55.addProperty(starttid, "19:00").addProperty(dag, onsdag).addProperty(varighet, "45 min").addProperty(instruktoer, "Patrik");
	stepBasic.addProperty(starttid, "19:00").addProperty(dag, onsdag).addProperty(varighet, "55 min").addProperty(instruktoer, "Vilde");
	tabata.addProperty(starttid, "19:00").addProperty(dag, onsdag).addProperty(varighet, "45 min").addProperty(instruktoer, "Vilde");
	
	//torsdag
	moelleIntervall.addProperty(starttid, "06:45").addProperty(dag, torsdag).addProperty(varighet, "45 min").addProperty(instruktoer, "Thomas H");
	raasterk.addProperty(starttid, "16:00").addProperty(dag, torsdag).addProperty(varighet, "60 min").addProperty(instruktoer, "Alexander");
	moelle30.addProperty(starttid, "16:15").addProperty(dag, torsdag).addProperty(varighet, "30 min").addProperty(instruktoer, "Kaia");
	tabata.addProperty(starttid, "17:00").addProperty(dag, torsdag).addProperty(varighet, "45 min").addProperty(instruktoer, "Kaia");
	intervall55.addProperty(starttid, "17:15").addProperty(dag, torsdag).addProperty(varighet, "55 min").addProperty(instruktoer, "Omar");
	TRXslyngetrening.addProperty(starttid, "18:00").addProperty(dag, torsdag).addProperty(varighet, "55 min").addProperty(instruktoer, "Anwar");
	basisballRIT.addProperty(starttid, "19:00").addProperty(dag, torsdag).addProperty(varighet, "45 min").addProperty(instruktoer, "Karolina");
	prama.addProperty(starttid, "19:00").addProperty(dag, torsdag).addProperty(varighet, "45 min").addProperty(instruktoer, "Anwar");
	yoga.addProperty(starttid, "20:00").addProperty(dag, torsdag).addProperty(varighet, "55 min").addProperty(instruktoer, "Aurora");
	
	//fredag
	yoga.addProperty(starttid, "07:00").addProperty(dag, fredag).addProperty(varighet, "55 min").addProperty(instruktoer, "Live");
	prama.addProperty(starttid, "16:00").addProperty(dag, fredag).addProperty(varighet, "45 min").addProperty(instruktoer, "Maria D");
	zumba.addProperty(starttid, "16:00").addProperty(dag, fredag).addProperty(varighet, "55 min").addProperty(instruktoer, "Candida");
	moelle45_15.addProperty(starttid, "17:00").addProperty(dag, fredag).addProperty(varighet, "45 min").addProperty(instruktoer, "Benjamin");
	stepIntervall.addProperty(starttid, "17:30").addProperty(dag, fredag).addProperty(varighet, "55 min").addProperty(instruktoer, "Louise");

	//loerdag 
	moelleVario.addProperty(starttid, "10:00").addProperty(dag, loerdag).addProperty(varighet, "45 min").addProperty(instruktoer, "-");
	stang.addProperty(starttid, "11:00").addProperty(dag, loerdag).addProperty(varighet, "55 min").addProperty(instruktoer, "Helene");
	

	//soendag
	yoga.addProperty(starttid, "10:15").addProperty(dag, soendag).addProperty(varighet, "55 min").addProperty(instruktoer, "Olga");;
	pulstopp55.addProperty(starttid, "18:00").addProperty(dag, soendag).addProperty(varighet, "55 min").addProperty(instruktoer, "Tarald");
	
		
	
	Property aapningstider = model.createProperty(gymURI + "aapningstider");
	
//	city.addProperty(mandag, "06:00-22:00" );
//	city.addProperty(tirsdag, "06:00-22:00" );
//	city.addProperty(onsdag, "06:00-22:00" );
//	city.addProperty(torsdag, "06:00-22:00" );
//	city.addProperty(fredag, "06:00-22:00" );
//	city.addProperty(loerdag, "06:00-22:00" );
//	city.addProperty(soendag, "06:00-22:00" );
//	

//	
//	//skal vi legge til medlemsskap priser ogsaa??
//	Property dropInnIkkeS = model.createProperty(gymURI + "DropInnIkkeStudentSlashAnsatt");
//	city.addProperty(dropInnIkkeS, "100");
//	
//	Property dropInnS = model.createProperty(gymURI + "DropInnStudentSlashAnsatt");
//	city.addProperty(dropInnS, "80");
//	
//	
//	
//	
//	Property pCity = model.createProperty(gymURI + "City");
//	city.addProperty(VCARD.Country, "Norge")
//		.addProperty(VCARD.Pcode, "5008")
//			.addProperty(VCARD.Street, "Vestre Stroemkaien 7")
//				.addProperty(VCARD.Region, "Hordaland")
//				.addProperty (pCity , "Bergen");
//				
//
//	city.addProperty(VCARD.TEL, "55 54 51 75");
//	city.addLiteral(VCARD.EMAIL, "trening.sibcity@sib.no");
//	


	}	

	
}
