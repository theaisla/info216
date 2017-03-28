package prosjekt;

import java.io.FileOutputStream;
import java.util.GregorianCalendar;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.vocabulary.VCARD;
import org.apache.jena.vocabulary.XSD;
import org.apache.jena.datatypes.xsd.*;


public class SSSIB {
	
	public static void main(String[] args) {
		Model basemodel = ModelFactory.createDefaultModel();
		InfModel model = ModelFactory.createRDFSModel(basemodel);
		
		// Forkortelser
		// String dbpedia = "http://dbpedia.org/resource/";
		String schema = "http://schema.org/";
		String owl = "http://owl.org/";
		
		// SIB
		Resource SIB = model.createResource(owl + "SIB");

		// Studentsenteret
		Resource studentsenteret = model.createResource(owl + "studentsenteret");
		
		// Relationship
		SIB.addProperty(FOAF.knows, studentsenteret); 
		studentsenteret.addProperty(FOAF.knows, SIB);
		
		// Contact info
		studentsenteret.addProperty(VCARD.EMAIL, "studentsenteret@sib.no");
		studentsenteret.addProperty(VCARD.TEL, "55545124"); 
		studentsenteret.addProperty(VCARD.ADR, "Parkveien1,5007Bergen,Norge"); 
		
		//Åpningstider
		Property openingHours = model.createProperty(schema + "openingHours");
		Property mo = model.createProperty(schema + "Mandag");
		Property tu = model.createProperty(schema + "Tirsdag");
		Property we = model.createProperty(schema + "Onsdag");
		Property th = model.createProperty(schema + "Torsdag");
		Property fr = model.createProperty(schema + "Fredag");
		Property sa = model.createProperty(schema + "Lordag");
		Property su = model.createProperty(schema + "Sondag");
		
		studentsenteret.addProperty(mo, "07:00-22:30");
		studentsenteret.addProperty(tu, "07:00-22:30");
		studentsenteret.addProperty(we, "07:00-22:30");
		studentsenteret.addProperty(th, "07:00-22:30");
		studentsenteret.addProperty(fr, "07:00-21:30");
		studentsenteret.addProperty(sa, "09:00-18:00");
		studentsenteret.addProperty(su, "10:00-20:00");
		
		Resource[]ssListOfOpeningHours = {mo, tu, we, th, fr, sa, su};
		Resource ssOpeningHours = model.createList(ssListOfOpeningHours);
		studentsenteret.addProperty(openingHours, ssOpeningHours);
		
		// Class type
		Resource spinningtime = model.createResource(owl + "spinning");
		Resource saltime = model.createResource(owl + "saltime");
		Resource bassengtime = model.createResource(owl + "bassengtimer");
		Resource rotime = model.createResource(owl + "rotime");
		
		// Fasiliteter
		Property harFasilitet = model.createProperty(owl + "harFasilitet");
		Resource basseng = model.createResource(owl + "basseng");
		Resource vekter = model.createResource(owl + "vekter");
		Resource garderobe = model.createResource(owl + "garderobe");
		Resource personligTrening = model.createResource(owl + "personligTrening");
				
		studentsenteret.addProperty(harFasilitet, basseng)
						.addLiteral(harFasilitet, vekter)
						.addLiteral(harFasilitet, garderobe)
						.addLiteral(harFasilitet, saltime)
						.addLiteral(harFasilitet, bassengtime)
						.addLiteral(harFasilitet, rotime)
						.addLiteral(harFasilitet, personligTrening)
						.addProperty(harFasilitet, spinningtime);
		
		// Classes 	
		Property workoutClass = model.createProperty(owl + "workoutClass");
		
		//saltimer
		Resource yoga = model.createResource(owl + "yoga");
		Resource pilates = model.createResource(owl + "pilates");
		Resource step = model.createResource(owl + "step");
		Resource tabata = model.createResource(owl + "tabata");
		Resource styrke = model.createResource(owl + "styrke");
		Resource zumba = model.createResource(owl + "zumba");
		Resource basisball = model.createResource(owl + "basisball");
		
		//spinning
		Resource spinning = model.createResource(owl + "spinning");
		
		//basseng
		Resource bassengTime = model.createResource(owl + "bassengTrening");
		
		//rotime
		Resource roing = model.createResource(owl + "roIntervall");

		//liste
		Resource[]listOfTrainingFormsStudentSenteret = {yoga, pilates, step, tabata, styrke, zumba, basisball,
				spinning, bassengTime, roing};
		Resource ssClasses = model.createList(listOfTrainingFormsStudentSenteret);
		studentsenteret.addProperty(workoutClass, ssClasses);
		

		// Tid / dag / varighet / instruktør
		Property starttid = model.createProperty(owl + "start");
		Property varighet = model.createProperty(owl + "varighet");
		Property dag = model.createProperty(owl + "dag");
		Property instrukor = model.createProperty(owl + "instruktor");
		
		// flere properties
		Property isClass = model.createProperty(owl + "isClass");
		Property className = model.createProperty(owl + "className");
		
		XSDDateTime xsdDate = new XSDDateTime(GregorianCalendar.getInstance());
		
		// Mandag
		Resource yoga_SSM1 = model.createResource(owl + "yoga_SSM1");
		//yoga_SSM1.addProperty(isClass, yoga).addProperty(className, "yoga").addProperty(starttid, XSD.dateTime(07.30))
		//.addProperty(dag, mo).addProperty(varighet, "55 min").addProperty(instrukor, "Randi");
		
		Resource intervallPyramide_SSM  = model.createResource(owl + "intervallPyramide_SSM");
		intervallPyramide_SSM.addProperty(isClass, roing).addProperty(className, "intervallPyramide").addProperty(starttid, "08:430")
		.addProperty(dag, mo).addProperty(varighet, "60 min").addProperty(instrukor, "Eivind");	
		
		Resource pulsTopp_SSM  = model.createResource(owl + "pulsTopp_SSM");
		pulsTopp_SSM.addProperty(isClass, spinning).addProperty(className, "pulsTopp55").addProperty(starttid, "16:15")
		.addProperty(dag, mo).addProperty(varighet, "55 min").addProperty(instrukor, "Gina");
		
		Resource stepMoves_SSM  = model.createResource(owl + "stepMoves_SSM");
		stepMoves_SSM.addProperty(isClass, step).addProperty(className, "stepMoves").addProperty(starttid, "16:45")
		.addProperty(dag, mo).addProperty(varighet, "55 min").addProperty(instrukor, "Heidi");
		
		Resource spinningIntervall45_SSM = model.createResource(owl + "spinningIntervall45_SSM");
		spinningIntervall45_SSM.addProperty(isClass, spinning).addProperty(className, "intervall45").addProperty(starttid, "18:00")
		.addProperty(dag, mo).addProperty(varighet, "45 min").addProperty(instrukor, "Heidi");
		
		Resource tabata_SSM = model.createResource(owl + "tabata_SSM");
		tabata_SSM.addProperty(isClass, tabata).addProperty(className, "tabata").addProperty(starttid, "18:00")
		.addProperty(dag, mo).addProperty(varighet, "45 min").addProperty(instrukor, "Anna");
		
		Resource spinningIntervall55_SSM = model.createResource(owl + "spinningIntervall55_SSM");
		spinningIntervall55_SSM.addProperty(isClass, spinning).addProperty(className, "intervall55").addProperty(starttid, "19:00")
		.addProperty(dag, mo).addProperty(varighet, "55 min").addProperty(instrukor, "Anna");
		
		Resource yoga_SSM2 = model.createResource(owl + "yoga_SSM2");
		yoga_SSM2.addProperty(isClass, yoga).addProperty(className, "yoga").addProperty(starttid, "19:00")
		.addProperty(dag, mo).addProperty(varighet, "55 min").addProperty(instrukor, "");
		
		Resource aquaIntervall_SSM = model.createResource(owl + "aquaIntervall_SSM");
		aquaIntervall_SSM.addProperty(isClass, bassengTime).addProperty(className, "aquaIntervall").addProperty(starttid, "20:00")
		.addProperty(dag, mo).addProperty(varighet, "45 min").addProperty(instrukor, "Gina");
		
		Resource styrkeStang_SSM = model.createResource(owl + "styrkeStang_SSM");
		styrkeStang_SSM.addProperty(isClass, styrke).addProperty(className, "styrkeStang").addProperty(starttid, "20:00")
		.addProperty(dag, mo).addProperty(varighet, "55 min").addProperty(instrukor, "Astrid Maria");
		
		// Tirsdag
		Resource zumba_SSTi = model.createResource(owl + "zumba_SSTi");
		zumba_SSTi.addProperty(isClass, zumba).addProperty(className, "zumba").addProperty(starttid, "17:00").
		addProperty(dag, tu).addProperty(varighet, "55 min").addProperty(instrukor, "Jazmin");
		
		Resource intervall90_SSTi = model.createResource(owl + "intervall90_SSTi");
		intervall90_SSTi.addProperty(isClass, spinning).addProperty(className, "intervall90").addProperty(starttid, "17:30").
		addProperty(dag, tu).addProperty(varighet, "90 min").addProperty(instrukor, "Odin");
		
		Resource intervallPyramide_SSTi = model.createResource(owl + "intervallPyramide_SSTi");
		intervallPyramide_SSTi.addProperty(isClass, roing).addProperty(className, "intervallPyramide").addProperty(starttid, "19:00").
		addProperty(dag, tu).addProperty(varighet, "60 min").addProperty(instrukor, "Jørgen");
		
		Resource tabata_SSTi = model.createResource(owl + "tabata_SSTi");
		tabata_SSTi.addProperty(isClass, tabata).addProperty(className, "tabata").addProperty(starttid, "19:10")
		.addProperty(dag, tu).addProperty(varighet, "45 min").addProperty(instrukor, "Astrid Maria");
		
		Resource pulsTopp55_SSTi = model.createResource(owl + "pulsTopp55_SSTi");
		pulsTopp55_SSTi.addProperty(isClass, spinning).addProperty(className, "pulsTopp55").addProperty(starttid, "19:15").
		addProperty(dag, tu).addProperty(varighet, "55 min").addProperty(instrukor, "Emilie");
		
		// Onsdag
		Resource aquaIntervall_SSO = model.createResource(owl + "aquaIntervall_SSO");
		aquaIntervall_SSO.addProperty(isClass, bassengTime).addProperty(className, "aquaIntervall").addProperty(starttid, "15:00")
		.addProperty(dag, we).addProperty(varighet, "45 min").addProperty(instrukor, "Gina");
		
		Resource intervallPyramide_SSO = model.createResource(owl + "intervallPyramide_SSO");
		intervallPyramide_SSO.addProperty(isClass, roing).addProperty(className, "intervallPyramide").addProperty(starttid, "17:00")
		.addProperty(dag, we).addProperty(varighet, "60 min").addProperty(instrukor, "Eiving");
		
		Resource yoga_SSO = model.createResource(owl + "yoga_SSO");
		yoga_SSO.addProperty(isClass, yoga).addProperty(className, "yoga").addProperty(starttid, "18:00")
		.addProperty(dag, we).addProperty(varighet, "55 min").addProperty(instrukor, "Randi");
		
		Resource pulsTopp55_SSO = model.createResource(owl + "pulsTopp55_SSO");
		pulsTopp55_SSO.addProperty(isClass, spinning).addProperty(className, "pulsTopp55").addProperty(starttid, "19:00")
		.addProperty(dag, we).addProperty(varighet, "55 min").addProperty(instrukor, "Brynhild");
		
		Resource basisballRaw_SSO = model.createResource(owl + "basisballRaw_SSO");
		basisballRaw_SSO.addProperty(isClass, basisball).addProperty(className, "basisballRaw").addProperty(starttid, "19:10")
		.addProperty(dag, we).addProperty(varighet, "45 min").addProperty(instrukor, "Veronika");
		
		Resource pilates_SSO = model.createResource(owl + "pilates_SSO");
		pilates_SSO.addProperty(isClass, pilates).addProperty(className, "pilates").addProperty(starttid, "20:00")
		.addProperty(dag, we).addProperty(varighet, "55 min").addProperty(instrukor, "Catrine");
		
		// Torsdag
		Resource pulsTopp55_SSTo1 = model.createResource(owl + "pulsTopp55_SSTo1");
		pulsTopp55_SSTo1.addProperty(isClass, spinning).addProperty(className, "pulsTopp55").addProperty(starttid, "08:15")
		.addProperty(dag, th).addProperty(varighet, "55 min").addProperty(instrukor, "Gina");
		
		Resource yoga_SSTo = model.createResource(owl + "yoga_SSTo");
		yoga_SSTo.addProperty(isClass, styrke).addProperty(className, "yoga").addProperty(starttid, "12:15")
		.addProperty(dag, th).addProperty(varighet, "55 min").addProperty(instrukor, "Olga");
		
		Resource intervall55_SSTo = model.createResource(owl + "intervall55_SSTo");
		intervall55_SSTo.addProperty(isClass, spinning).addProperty(className, "intervall55").addProperty(starttid, "16:15")
		.addProperty(dag, th).addProperty(varighet, "55 min").addProperty(instrukor, "Espen");
		
		Resource pulsTopp55_SSTo2 = model.createResource(owl + "pulsTopp55_SSTo2");
		pulsTopp55_SSTo2.addProperty(isClass, spinning).addProperty(className, "pulsTopp55").addProperty(starttid, "17:30")
		.addProperty(dag, th).addProperty(varighet, "55 min").addProperty(instrukor, "Trudi");
		
		Resource styrkeStang_SSTo = model.createResource(owl + "styrkeStang_SSTo");
		styrkeStang_SSTo.addProperty(isClass, styrke).addProperty(className, "styrkeStang").addProperty(starttid, "18:15")
		.addProperty(dag, th).addProperty(varighet, "55 min").addProperty(instrukor, "Tonje N");
		
		Resource intervallPyramide_SSTo = model.createResource(owl + "intervallPyramide_SSTo");
		intervallPyramide_SSTo.addProperty(isClass, roing).addProperty(className, "intervallPyramide").addProperty(starttid, "19:00")
		.addProperty(dag, th).addProperty(varighet, "60 min").addProperty(instrukor, "Amandus");
		
		// Fredag
		Resource stepIntervall_SSF = model.createResource(owl + "stepIntervall_SSF");
		stepIntervall_SSF.addProperty(isClass, step).addProperty(className, "stepIntervall").addProperty(starttid, "16:00")
		.addProperty(dag, fr).addProperty(varighet, "55 min").addProperty(instrukor, "Linn E");
		
		Resource raceday75_SSF = model.createResource(owl + "raceday75_SSF");
		raceday75_SSF.addProperty(isClass, spinning).addProperty(className, "raceday75").addProperty(starttid, "17:15")
		.addProperty(dag, fr).addProperty(varighet, "75 min").addProperty(instrukor, "Linn E");
		
		Resource intervallPyramide_SSF = model.createResource(owl + "intervallPyramide_SSF");
		intervallPyramide_SSF.addProperty(isClass, roing).addProperty(className, "intervallPyramide").addProperty(starttid, "17:30")
		.addProperty(dag, fr).addProperty(varighet, "60 min").addProperty(instrukor, "Jørgen");
		
		// Lørdag
		Resource intervall55_SSL = model.createResource(owl + "intervall55_SSL");
		intervall55_SSL.addProperty(isClass, spinning).addProperty(className, "intervall55").addProperty(starttid, "13:00")
		.addProperty(dag, sa).addProperty(varighet, "55 min").addProperty(instrukor, "Ann Kristin M");
		
		// Søndag
		Resource pulsTopp55_SSS = model.createResource(owl + "pulsTopp55_SSS");
		pulsTopp55_SSS.addProperty(isClass, spinning).addProperty(className, "pulsTopp55").addProperty(starttid, "17:00")
		.addProperty(dag, su).addProperty(varighet, "55 min").addProperty(instrukor, "Linn E");
		
		Resource styrkeStang_SSS = model.createResource(owl + "styrkeStang_SSS");
		styrkeStang_SSS.addProperty(isClass, styrke).addProperty(className, "styrkeStang").addProperty(starttid, "18:15")
		.addProperty(dag, su).addProperty(varighet, "55 min").addProperty(instrukor, "Linn E");

		
	
		
		
		// Writing to file
		try {
			model.write(new FileOutputStream("StudentSenter.ttl"), "TURTLE");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		// Print
		//model.write(System.out, "RDF/XML");
		//model.close();
		
		// SPARQL query
	    System.out.println("Query");
	    dumpQueryResult(
	            model,
	            String.format(
	                    "prefix a: <http://owl.org/> "
	                    + "SELECT ?x ?p "
	                    + "WHERE { a:yoga ?x ?p}",
	                    	("StudentSenteret.ttl")));

		}


	    private static void dumpQueryResult(final Model model, final String queryString) {
	        Query query = QueryFactory.create(queryString);
	        QueryExecution qe = QueryExecutionFactory.create(query, model);
	        ResultSet results = qe.execSelect();
	        ResultSetFormatter.out(System.out, results, query);
	        qe.close();
	    }
	    
		
		/*System.out.println("--------------TURTLE------------------");
		model.write(System.out, "TURTLE");
		System.out.println("--------------N-TRIPLE------------------");
		model.write(System.out, "N-TRIPLE");
		System.out.println("--------------N3------------------");
		model.write(System.out, "N3");
		System.out.println("--------------JSON-LD------------------");
		model.write(System.out, "JSON-LD");
		System.out.println("--------------RDF/XML------------------");
		model.write(System.out, "RDF/XML");
		*/

}
