package prosjekt;

import java.io.FileOutputStream;

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
		
		Property typeOfClass = model.createProperty(owl + "typeOfClass");
		
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
		Resource yoga = model.createResource(owl + "yoga"); yoga.addProperty(typeOfClass, saltime);
		Resource pilates = model.createResource(owl + "pilates"); pilates.addProperty(typeOfClass, saltime);
		Resource step = model.createResource(owl + "step"); step.addProperty(typeOfClass, saltime);
		Resource tabata = model.createResource(owl + "tabata"); tabata.addProperty(typeOfClass, saltime);
		Resource styrke = model.createResource(owl + "styrke"); styrke.addProperty(typeOfClass, saltime);
		Resource zumba = model.createResource(owl + "zumba"); zumba.addProperty(typeOfClass, saltime);
		Resource basisball = model.createResource(owl + "basisball"); basisball.addProperty(typeOfClass, saltime);
		
		//spinning
		Resource spinning = model.createResource(owl + "spinning"); spinning.addProperty(typeOfClass, spinningtime);
		
		//basseng
		Resource bassengTrening = model.createResource(owl + "bassengTrening"); bassengTrening.addProperty(typeOfClass, bassengtime);
		
		//rotime
		Resource roing = model.createResource(owl + "roIntervall"); roing.addProperty(typeOfClass, rotime);

		//liste
		Resource[]listOfTrainingFormsStudentSenteret = {yoga, pilates, step, tabata, styrke, zumba, basisball,
				spinning, bassengTrening, roing};
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
		
		// Mandag
		Resource yoga_SSM1 = model.createResource(owl + "yoga_SSM1");
		yoga_SSM1.addProperty(isClass, yoga).addProperty(className, "yoga").addProperty(starttid, "07:30")
		.addProperty(dag, mo).addProperty(varighet, "55 min").addProperty(instrukor, "Randi");
		
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
		aquaIntervall_SSM.addProperty(isClass, bassengTrening).addProperty(className, "aquaIntervall").addProperty(starttid, "20:00")
		.addProperty(dag, mo).addProperty(varighet, "45 min").addProperty(instrukor, "Gina");
		
		Resource styrkeStang_SSM = model.createResource(owl + "styrkeStang_SSM");
		styrkeStang_SSM.addProperty(isClass, styrke).addProperty(className, "styrkeStang").addProperty(starttid, "20:00")
		.addProperty(dag, mo).addProperty(varighet, "55 min").addProperty(instrukor, "Astrid Maria");
		
		// Tirsdag
		zumba.addProperty(starttid, "17:00").addProperty(dag, tu).addProperty(varighet, "55 min").addProperty(instrukor, "Jazmin");
		intervall90.addProperty(starttid, "17:30").addProperty(dag, tu).addProperty(varighet, "90 min").addProperty(instrukor, "Odin");
		intervallPyramide.addProperty(starttid, "19:00").addProperty(dag, tu).addProperty(varighet, "60 min").addProperty(instrukor, "Jørgen");
		tabata.addProperty(starttid, "19:10").addProperty(dag, tu).addProperty(varighet, "45 min").addProperty(instrukor, "Astrid Maria");
		pulsTopp55.addProperty(starttid, "19:15").addProperty(dag, tu).addProperty(varighet, "55 min").addProperty(instrukor, "Emilie");
		
		// Onsdag
		aquaIntervall.addProperty(starttid, "15:00").addProperty(dag, we).addProperty(varighet, "45 min").addProperty(instrukor, "Gina");
		intervallPyramide.addProperty(starttid, "17:00").addProperty(dag, we).addProperty(varighet, "60 min").addProperty(instrukor, "Eiving");
		yoga.addProperty(starttid, "18:00").addProperty(dag, we).addProperty(varighet, "55 min").addProperty(instrukor, "Randi");
		pulsTopp55.addProperty(starttid, "19:00").addProperty(dag, we).addProperty(varighet, "55 min").addProperty(instrukor, "Brynhild");
		basisballRaw.addProperty(starttid, "19:10").addProperty(dag, we).addProperty(varighet, "45 min").addProperty(instrukor, "Veronika");
		pilates.addProperty(starttid, "20:00").addProperty(dag, we).addProperty(varighet, "55 min").addProperty(instrukor, "Catrine");
		
		// Torsdag
		pulsTopp55.addProperty(starttid, "08:15").addProperty(dag, th).addProperty(varighet, "55 min").addProperty(instrukor, "Gina");
		yoga.addProperty(starttid, "12:15").addProperty(dag, th).addProperty(varighet, "55 min").addProperty(instrukor, "Olga");
		intervall55.addProperty(starttid, "16:15").addProperty(dag, th).addProperty(varighet, "55 min").addProperty(instrukor, "Espen");
		pulsTopp55.addProperty(starttid, "17:30").addProperty(dag, th).addProperty(varighet, "55 min").addProperty(instrukor, "Trudi");
		styrkeStang.addProperty(starttid, "18:15").addProperty(dag, th).addProperty(varighet, "55 min").addProperty(instrukor, "Tonje N");
		intervallPyramide.addProperty(starttid, "19:00").addProperty(dag, th).addProperty(varighet, "60 min").addProperty(instrukor, "Amandus");
		
		// Fredag
		stepIntervall.addProperty(starttid, "16:00").addProperty(dag, fr).addProperty(varighet, "55 min").addProperty(instrukor, "Linn E");
		raceday75.addProperty(starttid, "17:15").addProperty(dag, fr).addProperty(varighet, "75 min").addProperty(instrukor, "Linn E");
		intervallPyramide.addProperty(starttid, "17:30").addProperty(dag, fr).addProperty(varighet, "60 min").addProperty(instrukor, "Jørgen");
		
		// Lørdag
		intervall55.addProperty(starttid, "13:00").addProperty(dag, sa).addProperty(varighet, "55 min").addProperty(instrukor, "Ann Kristin M");
		
		// Søndag
		pulsTopp55.addProperty(starttid, "17:00").addProperty(dag, su).addProperty(varighet, "55 min").addProperty(instrukor, "Linn E");
		styrkeStang.addProperty(starttid, "18:15").addProperty(dag, su).addProperty(varighet, "55 min").addProperty(instrukor, "Linn E");

		
	
		
		
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
