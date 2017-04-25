package project216;

import java.util.ArrayList;

import java.io.FileOutputStream;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
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
	 * Treningssenter tilhoerighet:
	 * subOrganization for alle sib sentre?
	 * 
	 * For lignende treningsformer:
	 * Schema.org : Product -> isSimilarTo
	 * -sameAs
	 * -additionalType
	 * -Ellers kunne vi laget typer (av hva som trenes eks kjaernemuskulatur, styrke, kondisjon), 
	 * 		og returnere andre treningsforemer som trener det samme. 
	 * 
	 */
	
	
	/*
	 * Forslag til SPARQL
	 * 
	 * SELECT ?gym
	 * WHERE
	 * {
	 * ?gym harFasiltet Svoemmebasseng .
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
	 * ?instruktoer == "Aurora" .
	 * }
	 * 
	 * 
	 * SELECT ?typeOfClass
	 * WHERE {
	 * filter contains(?typeOfClass"Yoga") 
	 * }
	 * 
	 * 
	 */
		
	Model model = ModelFactory.createDefaultModel();
	Resource city = (Resource) model.createResource(gymURI);
	
	
	Property typeOfClass = model.createProperty(gymURI + "typeOfClass");
	Property harFasilitet = model.createProperty(gymURI + "harFasilitet");
	
	Resource vekter = model.createResource(gymURI + "vekter");
	Resource garderobe = model.createResource(gymURI + "garderobe");
	Resource spinningtime = model.createResource(gymURI + "spinning");
	Resource saltime = model.createResource(gymURI + "saltime");
	Resource loepetime = model.createResource(gymURI + "loepetime");
	
	city.addProperty(harFasilitet, vekter)
		.addLiteral(harFasilitet, garderobe)
			.addLiteral(harFasilitet, saltime);

	
	//Saltimer
	Resource yoga = model.createResource(gymURI + "Yoga"); 
	yoga.addProperty(typeOfClass, saltime);
	Resource yoga75 = model.createResource(gymURI + "Yoga75"); 
	yoga75.addProperty(typeOfClass, saltime);
	Resource pilates = model.createResource(gymURI + "Pilates"); 
	pilates.addProperty(typeOfClass, saltime);
	Resource stepMoves = model.createResource(gymURI + "Stepmoves"); 
	stepMoves.addProperty(typeOfClass, saltime);
	Resource stepIntervall = model.createResource(gymURI + "StepIntervall"); 
	stepIntervall.addProperty(typeOfClass, saltime);
	Resource tabata = model.createResource(gymURI + "Tabata"); 
	tabata.addProperty(typeOfClass, saltime);
	Resource styrkeStang = model.createResource(gymURI + "StyrkeMedStang"); 
	styrkeStang.addProperty(typeOfClass, saltime);
	Resource zumba = model.createResource(gymURI + "Zumba"); 
	zumba.addProperty(typeOfClass, saltime);
	Resource basisballRaw = model.createResource(gymURI + "BasisballRaw"); 
	basisballRaw.addProperty(typeOfClass, saltime);
	Resource prama = model.createResource(gymURI + "PRAMA"); 
	prama.addProperty(typeOfClass, saltime);
	Resource raasterk = model.createResource(gymURI + "RaaSTERK"); 
	raasterk.addProperty(typeOfClass, saltime);
	Resource TRXslyngetrening = model.createResource(gymURI + "TRXSlyngetrening"); 
	TRXslyngetrening.addProperty(typeOfClass, saltime);
	Resource basisballRIT = model.createResource(gymURI + "BasisballRIT");
	basisballRIT.addProperty(typeOfClass, saltime);
	Resource movementFlow = model.createResource(gymURI + "MovementFlow");
	movementFlow.addProperty(typeOfClass, saltime);
	Resource mobilityStretch = model.createResource(gymURI + "MobilityStretch");
	mobilityStretch.addProperty(typeOfClass, saltime);
	Resource stang = model.createResource(gymURI + "StyrkeMedStang");
	stang.addProperty(typeOfClass, saltime);
	Resource HIITABS = model.createResource(gymURI + "HIITABS");
	HIITABS.addProperty(typeOfClass, saltime);
	 Resource stepBasic = model.createResource(gymURI + "StepBasic");
	stepBasic.addProperty(typeOfClass, saltime);

	
	//Spinning
	Resource pulstopp55 = model.createResource(gymURI + "Pulstopp55"); 
	pulstopp55.addProperty(typeOfClass, spinningtime);
	Resource intervall45 = model.createResource(gymURI + "Intervall45"); 
	intervall45.addProperty(typeOfClass, spinningtime);
	Resource intervall55 = model.createResource(gymURI + "Intervall55"); 
	intervall55.addProperty(typeOfClass, spinningtime);
	Resource intervall90 = model.createResource(gymURI + "Intervall90"); 
	intervall90.addProperty(typeOfClass, spinningtime);
	Resource intervallPyramide = model.createResource(gymURI + "IntervallPyramide"); 
	intervallPyramide.addProperty(typeOfClass, spinningtime);

	
	//loepetime
	Resource moelle4x4 = model.createResource(gymURI + "SIBMoelle4x4");
	moelle4x4.addProperty(typeOfClass, loepetime);
	Resource moellePyramide = model.createResource(gymURI + "SIBMoellePyramide");
	moellePyramide.addProperty(typeOfClass, saltime);
	Resource moelleIntervall = model.createResource(gymURI + "SIBmoelleintervall");
	moelleIntervall.addProperty(typeOfClass, loepetime);
	Resource moelle30 = model.createResource(gymURI + "SIBmoelle30");
	moelle30.addProperty(typeOfClass, loepetime);
	Resource moelle45_15 = model.createResource(gymURI + "SIBmoelle4515");
	moelle45_15.addProperty(typeOfClass, saltime);
	Resource moelleVario = model.createResource(gymURI + "SIBmoellevario");
	moelleVario.addProperty(typeOfClass, saltime);
	
	
	//Property tid = model.createProperty(gymURI + "Tid");

	Property mandag = model.createProperty(gymURI + "Mandag");
	Property tirsdag = model.createProperty(gymURI + "Tirsdag");
	Property onsdag = model.createProperty(gymURI + "Onsdag");
	Property torsdag = model.createProperty(gymURI + "Torsdag");
	Property fredag = model.createProperty(gymURI + "Fredag");
	Property loerdag = model.createProperty(gymURI + "Loerdag");
	Property soendag = model.createProperty(gymURI + "Soendag");
	
	
	
	// Tid / dag / varighet / instruktoer
	Property starttid = model.createProperty(gymURI + "Start");
	Property varighet = model.createProperty(gymURI + "Varighet");
	Property dag = model.createProperty(gymURI + "Dag");
	Property instruktoer = model.createProperty(gymURI + "Instruktoer");

	 
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
	
	city.addProperty(mandag, "06:00-22:00" );
	city.addProperty(tirsdag, "06:00-22:00" );
	city.addProperty(onsdag, "06:00-22:00" );
	city.addProperty(torsdag, "06:00-22:00" );
	city.addProperty(fredag, "06:00-22:00" );
	city.addProperty(loerdag, "06:00-22:00" );
	city.addProperty(soendag, "06:00-22:00" );
	
//	
//	
//	
//	mandag.addProperty(aapningstider, "06:00-22:00");
//	Property aapningstiderF = model.createProperty(gymURI + "Fredag");
//	city.addProperty(aapningstiderF, "06:00-21:00");
//	
//	Property aapningstiderL = model.createProperty(gymURI + "Loerdag");
//	city.addProperty(aapningstiderL, "09:00-18:00");
//	
//	Property aapningstiderS = model.createProperty(gymURI + "Soendag");
//	city.addProperty(aapningstiderS, "10:00-20:00");
//	
//	
//	
	
	
	
	//skal vi legge til medlemsskap priser ogsaa??
	Property dropInnIkkeS = model.createProperty(gymURI + "DropInnIkkeStudentSlashAnsatt");
	city.addProperty(dropInnIkkeS, "100");
	
	Property dropInnS = model.createProperty(gymURI + "DropInnStudentSlashAnsatt");
	city.addProperty(dropInnS, "80");
	
	
	
	
	Property pCity = model.createProperty(gymURI + "City");
	city.addProperty(VCARD.Country, "Norge")
		.addProperty(VCARD.Pcode, "5008")
			.addProperty(VCARD.Street, "Vestre Stroemkaien 7")
				.addProperty(VCARD.Region, "Hordaland")
				.addProperty (pCity , "Bergen");
				

	city.addProperty(VCARD.TEL, "55 54 51 75");
	city.addLiteral(VCARD.EMAIL, "trening.sibcity@sib.no");
	
	
	System.out.println("TURTLE");
	model.write(System.out, "TURTLE");
	

	//Writing to file
	try {
		model.write(new FileOutputStream("City.ttl"), "TURTLE");
	} catch (Exception e) {
		// TODO: handle exception
	}
	
	// SPARQL query
    System.out.println("Find all paths from A to B in exactly two steps");
    dumpQueryResult(
            model,
            String.format(
                    "prefix a: <http://example/SibCity> SELECT ?x ?p WHERE { a:SIBMoelle4x4 ?x ?p}",
                    	("City.ttl")));

	}


    private static void dumpQueryResult(final Model model,
            final String queryString) {
        Query query = QueryFactory.create(queryString);
        QueryExecution qe = QueryExecutionFactory.create(query, model);
        ResultSet results = qe.execSelect();
        ResultSetFormatter.out(System.out, results, query);
        qe.close();
    }
	
}
