package project216;

import java.util.ArrayList;

import java.io.FileOutputStream;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.VCARD;

public class SibCity {

	
	
	public static void main(String[] args) {
		
		
	String gymURI = "http://example/SibCity";	
	String schema = "http://schema.org/";
	
	
	/*
	 * Treningssenter tilhørighet:
	 * subOrganization for alle sib sentre?
	 * 
	 * For lignende treningsformer:
	 * Schema.org : Product -> isSimilarTo
	 * -sameAs
	 * -additionalType
	 * -Ellers kunne vi laget typer (av hva som trenes eks kjærnemuskulatur, styrke, kondisjon), 
	 * 		og returnere andre treningsforemer som trener det samme. 
	 * 
	 */
	
	
	/*
	 * Forslag til SPARQL
	 * 
	 * SELECT ?gym
	 * WHERE
	 * {
	 * ?gym harFasiltet Svømmebasseng .
	 * }
	 * 
	 * 
	 * SELECT ?gym
	 * WHERE
	 * {
	 * ?gym typeOfClass pilates .
	 * ?dag equals mandag .
	 * ?starttid>"15:00" .
	 * }
	 * 
	 * SELECT ?time
	 * WHERE
	 * {
	 * ?instruktør == "Aurora" .
	 * }
	 * 
	 * 
	 */
		
	Model model = ModelFactory.createDefaultModel();
	Resource city = (Resource) model.createResource(gymURI);
	
	
	Property typeOfClass = model.createProperty(gymURI + "typeOfClass");
	Property harFasilitet = model.createProperty(gymURI + "har fasilitet");
	
	Resource vekter = model.createResource(gymURI + "vekter");
	Resource garderobe = model.createResource(gymURI + "garderobe");
	Resource spinningtime = model.createResource(gymURI + "spinning");
	Resource saltime = model.createResource(gymURI + "saltime");
	Resource løpetime = model.createResource(gymURI + "løpetime");
	
	city.addProperty(harFasilitet, vekter)
		.addLiteral(harFasilitet, garderobe)
			.addLiteral(harFasilitet, saltime);

	
	//Saltimer
	Resource yoga = model.createResource(gymURI + "Yoga"); 
	yoga.addProperty(typeOfClass, saltime);
	Resource yoga75 = model.createResource(gymURI + "Yoga 75"); 
	yoga75.addProperty(typeOfClass, saltime);
	Resource pilates = model.createResource(gymURI + "Pilates"); 
	pilates.addProperty(typeOfClass, saltime);
	Resource stepMoves = model.createResource(gymURI + "Step moves"); 
	stepMoves.addProperty(typeOfClass, saltime);
	Resource stepIntervall = model.createResource(gymURI + "Step Intervall"); 
	stepIntervall.addProperty(typeOfClass, saltime);
	Resource tabata = model.createResource(gymURI + "Tabata"); 
	tabata.addProperty(typeOfClass, saltime);
	Resource styrkeStang = model.createResource(gymURI + "Styrke med stang"); 
	styrkeStang.addProperty(typeOfClass, saltime);
	Resource zumba = model.createResource(gymURI + "Zumba"); 
	zumba.addProperty(typeOfClass, saltime);
	Resource basisballRaw = model.createResource(gymURI + "Basisball raw"); 
	basisballRaw.addProperty(typeOfClass, saltime);
	Resource prama = model.createResource(gymURI + "PRAMA"); 
	prama.addProperty(typeOfClass, saltime);
	Resource raasterk = model.createResource(gymURI + "RåSTERK"); 
	raasterk.addProperty(typeOfClass, saltime);
	Resource TRXslyngetrening = model.createResource(gymURI + " TRX Slyngetrening"); 
	TRXslyngetrening.addProperty(typeOfClass, saltime);
	Resource basisballRIT = model.createResource(gymURI + "Basisball RIT");
	basisballRIT.addProperty(typeOfClass, saltime);
	Resource movementFlow = model.createResource(gymURI + "	Movement Flow");
	movementFlow.addProperty(typeOfClass, saltime);
	Resource mobilityStretch = model.createResource(gymURI + "Mobility & Stretch");
	mobilityStretch.addProperty(typeOfClass, saltime);
	Resource stang = model.createResource(gymURI + "Styrke med stang");
	stang.addProperty(typeOfClass, saltime);
	Resource HIITABS = model.createResource(gymURI + "HIIT/ABS");
	HIITABS.addProperty(typeOfClass, saltime);
	 Resource stepBasic = model.createResource(gymURI + "StepBasic");
	stepBasic.addProperty(typeOfClass, saltime);

	
	//Spinning
	Resource pulstopp55 = model.createResource(gymURI + "Pulstopp 55"); 
	pulstopp55.addProperty(typeOfClass, spinningtime);
	Resource intervall45 = model.createResource(gymURI + "Intervall 45"); 
	intervall45.addProperty(typeOfClass, spinningtime);
	Resource intervall55 = model.createResource(gymURI + "Intervall 55"); 
	intervall55.addProperty(typeOfClass, spinningtime);
	Resource intervall90 = model.createResource(gymURI + "Intervall 90"); 
	intervall90.addProperty(typeOfClass, spinningtime);
	Resource intervallPyramide = model.createResource(gymURI + "Intervall pyramide"); 
	intervallPyramide.addProperty(typeOfClass, spinningtime);

	
	//løpetime
	Resource mølle4x4 = model.createResource(gymURI + "SIB mølle 4x4");
	mølle4x4.addProperty(typeOfClass, løpetime);
	Resource møllePyramide = model.createResource(gymURI + "SIB Mølle Pyramide");
	møllePyramide.addProperty(typeOfClass, saltime);
	Resource mølleIntervall = model.createResource(gymURI + "SIB mølle intervall");
	mølleIntervall.addProperty(typeOfClass, løpetime);
	Resource mølle30 = model.createResource(gymURI + "SIB mølle 30");
	mølle30.addProperty(typeOfClass, løpetime);
	Resource mølle45_15 = model.createResource(gymURI + "SIB mølle 45/15");
	mølle45_15.addProperty(typeOfClass, saltime);
	Resource mølleVario = model.createResource(gymURI + "SIB mølle vario");
	mølleVario.addProperty(typeOfClass, saltime);
	
	
	//Property tid = model.createProperty(gymURI + "Tid");

	Property mandag = model.createProperty(gymURI + "Mandag");
	Property tirsdag = model.createProperty(gymURI + "Tirsdag");
	Property onsdag = model.createProperty(gymURI + "Onsdag");
	Property torsdag = model.createProperty(gymURI + "Torsdag");
	Property fredag = model.createProperty(gymURI + "Fredag");
	Property lørdag = model.createProperty(gymURI + "Lørdag");
	Property søndag = model.createProperty(gymURI + "Søndag");
	
	
	
	// Tid / dag / varighet / instruktør
	Property starttid = model.createProperty(gymURI + "Start");
	Property varighet = model.createProperty(gymURI + "Varighet");
	Property dag = model.createProperty(gymURI + "Dag");
	Property instruktør = model.createProperty(gymURI + "Instruktør");

	 
	//mandag
	intervall55.addProperty(starttid, "07:00").addProperty(dag, mandag).addProperty(varighet, "55 min").addProperty(instruktør, "Linn E");
	raasterk.addProperty(starttid, "15:00").addProperty(dag, mandag).addProperty(varighet, "60 min").addProperty(instruktør, "Alexander");
	TRXslyngetrening.addProperty(starttid, "16:00").addProperty(dag, mandag).addProperty(varighet, "55 min").addProperty(instruktør, "Benjamin");
	pilates.addProperty(starttid, "17:00").addProperty(dag, mandag).addProperty(varighet, "55 min").addProperty(instruktør, "Olga");
	prama.addProperty(starttid, "17:00").addProperty(dag, mandag).addProperty(varighet, "45 min").addProperty(instruktør, "Kathrine H");
	mølle4x4.addProperty(starttid, "18:00").addProperty(dag, mandag).addProperty(varighet, "45 min").addProperty(instruktør, "Sigrun");
	tabata.addProperty(starttid, "18:00").addProperty(dag, mandag).addProperty(varighet, "45 min").addProperty(instruktør, "Tonje N");
	basisballRIT.addProperty(starttid, "19:00").addProperty(dag, mandag).addProperty(varighet, "45 min").addProperty(instruktør, "Maria D");
	intervall45.addProperty(starttid, "19:00").addProperty(dag, mandag).addProperty(varighet, "45 min").addProperty(instruktør, "Trudi");
	yoga.addProperty(starttid, "20:00").addProperty(dag, mandag).addProperty(varighet, "55 min").addProperty(instruktør, "Emilie T");
	
	//tirsdag
	intervall45.addProperty(starttid, "07:00").addProperty(dag, tirsdag).addProperty(varighet, "45 min").addProperty(instruktør, "Ragnhild");
	tabata.addProperty(starttid, "08:00").addProperty(dag, tirsdag).addProperty(varighet, "45 min").addProperty(instruktør, "Ragnhild");
	TRXslyngetrening.addProperty(starttid, "11:00").addProperty(dag, tirsdag).addProperty(varighet, "45 min").addProperty(instruktør, "Ann-Kristin R");
	movementFlow.addProperty(starttid, "16:00").addProperty(dag, tirsdag).addProperty(varighet, "55 min").addProperty(instruktør, "Tom");
	pulstopp55.addProperty(starttid, "17:00").addProperty(dag, tirsdag).addProperty(varighet, "55 min").addProperty(instruktør, "Espen");
	mobilityStretch.addProperty(starttid, "17:00").addProperty(dag, tirsdag).addProperty(varighet, "55 min").addProperty(instruktør, "Tom");
	stang.addProperty(starttid, "18:00").addProperty(dag, tirsdag).addProperty(varighet, "55 min").addProperty(instruktør, "Charlotte");
	prama.addProperty(starttid, "16:00").addProperty(dag, tirsdag).addProperty(varighet, "45 min").addProperty(instruktør, "Kathrine H");
	yoga75.addProperty(starttid, "19:00").addProperty(dag, tirsdag).addProperty(varighet, "75 min").addProperty(instruktør, "Agnes");
	møllePyramide.addProperty(starttid, "19:05").addProperty(dag, tirsdag).addProperty(varighet, "45 min").addProperty(instruktør, "Charlotte");
	
	//onsdag
	HIITABS.addProperty(starttid, "07:10").addProperty(dag, onsdag).addProperty(varighet, "45 min").addProperty(instruktør, "Ronja");
	yoga.addProperty(starttid, "08:00").addProperty(dag, onsdag).addProperty(varighet, "55 min").addProperty(instruktør, "Live");
	raasterk.addProperty(starttid, "10:00").addProperty(dag, onsdag).addProperty(varighet, "60 min").addProperty(instruktør, "Alexander");
	intervall55.addProperty(starttid, "16:15").addProperty(dag, onsdag).addProperty(varighet, "55 min").addProperty(instruktør, "Jannik");
	pilates.addProperty(starttid, "17:00").addProperty(dag, onsdag).addProperty(varighet, "55 min").addProperty(instruktør, "Sigrun");
	møllePyramide.addProperty(starttid, "18:00").addProperty(dag, onsdag).addProperty(varighet, "45 min").addProperty(instruktør, "Patrik");
	prama.addProperty(starttid, "18:00").addProperty(dag, onsdag).addProperty(varighet, "45 min").addProperty(instruktør, "Omar");
	zumba.addProperty(starttid, "18:00").addProperty(dag, onsdag).addProperty(varighet, "55 min").addProperty(instruktør, "Carita");
	pulstopp55.addProperty(starttid, "19:00").addProperty(dag, onsdag).addProperty(varighet, "45 min").addProperty(instruktør, "Patrik");
	stepBasic.addProperty(starttid, "19:00").addProperty(dag, onsdag).addProperty(varighet, "55 min").addProperty(instruktør, "Vilde");
	tabata.addProperty(starttid, "19:00").addProperty(dag, onsdag).addProperty(varighet, "45 min").addProperty(instruktør, "Vilde");
	
	//torsdag
	mølleIntervall.addProperty(starttid, "06:45").addProperty(dag, torsdag).addProperty(varighet, "45 min").addProperty(instruktør, "Thomas H");
	raasterk.addProperty(starttid, "16:00").addProperty(dag, torsdag).addProperty(varighet, "60 min").addProperty(instruktør, "Alexander");
	mølle30.addProperty(starttid, "16:15").addProperty(dag, torsdag).addProperty(varighet, "30 min").addProperty(instruktør, "Kaia");
	tabata.addProperty(starttid, "17:00").addProperty(dag, torsdag).addProperty(varighet, "45 min").addProperty(instruktør, "Kaia");
	intervall55.addProperty(starttid, "17:15").addProperty(dag, torsdag).addProperty(varighet, "55 min").addProperty(instruktør, "Omar");
	TRXslyngetrening.addProperty(starttid, "18:00").addProperty(dag, torsdag).addProperty(varighet, "55 min").addProperty(instruktør, "Anwar");
	basisballRIT.addProperty(starttid, "19:00").addProperty(dag, torsdag).addProperty(varighet, "45 min").addProperty(instruktør, "Karolina");
	prama.addProperty(starttid, "19:00").addProperty(dag, torsdag).addProperty(varighet, "45 min").addProperty(instruktør, "Anwar");
	yoga.addProperty(starttid, "20:00").addProperty(dag, torsdag).addProperty(varighet, "55 min").addProperty(instruktør, "Aurora");
	
	//fredag
	yoga.addProperty(starttid, "07:00").addProperty(dag, fredag).addProperty(varighet, "55 min").addProperty(instruktør, "Live");
	prama.addProperty(starttid, "16:00").addProperty(dag, fredag).addProperty(varighet, "45 min").addProperty(instruktør, "Maria D");
	zumba.addProperty(starttid, "16:00").addProperty(dag, fredag).addProperty(varighet, "55 min").addProperty(instruktør, "Candida");
	mølle45_15.addProperty(starttid, "17:00").addProperty(dag, fredag).addProperty(varighet, "45 min").addProperty(instruktør, "Benjamin");
	stepIntervall.addProperty(starttid, "17:30").addProperty(dag, fredag).addProperty(varighet, "55 min").addProperty(instruktør, "Louise");

	//lørdag 
	mølleVario.addProperty(starttid, "10:00").addProperty(dag, lørdag).addProperty(varighet, "45 min").addProperty(instruktør, "-");
	stang.addProperty(starttid, "11:00").addProperty(dag, lørdag).addProperty(varighet, "55 min").addProperty(instruktør, "Helene");
	

	//søndag
	yoga.addProperty(starttid, "10:15").addProperty(dag, søndag).addProperty(varighet, "55 min").addProperty(instruktør, "Olga");;
	pulstopp55.addProperty(starttid, "18:00").addProperty(dag, søndag).addProperty(varighet, "55 min").addProperty(instruktør, "Tarald");
	
		
	
	Property åpningstider = model.createProperty(gymURI + "Åpningstider");
	
	city.addProperty(mandag, "06:00-22:00" );
	city.addProperty(tirsdag, "06:00-22:00" );
	city.addProperty(onsdag, "06:00-22:00" );
	city.addProperty(torsdag, "06:00-22:00" );
	city.addProperty(fredag, "06:00-22:00" );
	city.addProperty(lørdag, "06:00-22:00" );
	city.addProperty(søndag, "06:00-22:00" );
	
//	
//	
//	
//	mandag.addProperty(åpningstider, "06:00-22:00");
//	Property åpningstiderF = model.createProperty(gymURI + "Fredag");
//	city.addProperty(åpningstiderF, "06:00-21:00");
//	
//	Property åpningstiderL = model.createProperty(gymURI + "Lørdag");
//	city.addProperty(åpningstiderL, "09:00-18:00");
//	
//	Property åpningstiderS = model.createProperty(gymURI + "Søndag");
//	city.addProperty(åpningstiderS, "10:00-20:00");
//	
//	
//	
	
	
	
	//skal vi legge til medlemsskap priser også??
	Property dropInnIkkeS = model.createProperty(gymURI + "Drop inn ikke student/ansatt");
	city.addProperty(dropInnIkkeS, "100");
	
	Property dropInnS = model.createProperty(gymURI + "Drop inn student/ansatt");
	city.addProperty(dropInnS, "80");
	
	
	
	
	Property pCity = model.createProperty(gymURI + "City");
	city.addProperty(VCARD.Country, "Norge")
		.addProperty(VCARD.Pcode, "5008")
			.addProperty(VCARD.Street, "Vestre Strømkaien 7")
				.addProperty(VCARD.Region, "Hordaland")
				.addProperty (pCity , "Bergen");
				

	city.addProperty(VCARD.TEL, "55 54 51 75");
	city.addLiteral(VCARD.EMAIL, "trening.sibcity@sib.no");
	
	
	System.out.println("TURTLE");
	model.write(System.out, "TURTLE");

	}
	
}
