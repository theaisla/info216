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

public class StudentSenteretSIB {
	
	
	public static void main(String[] args) {
		Model basemodel = ModelFactory.createDefaultModel();
		InfModel model = ModelFactory.createRDFSModel(basemodel);
		
		// Forkortelser
		// String dbpedia = "http://dbpedia.org/resource/";
		String schema = "http://schema.org/";
		String owl = "http://owl.org/";
		
		// Properties
		Property postalAddress = model.createProperty(schema + "PostalAddress");
		Property telephone = model.createProperty(schema + "telephone");
		Property email = model.createProperty(schema + "email");
		
		// SIB
		Resource SIB = model.createResource(owl + "SIB");

		// Studentsenteret
		Resource studentsenteret = model.createResource(owl + "studentsenteret");
		
		// Relationship
		SIB.addProperty(FOAF.knows, studentsenteret); 
		studentsenteret.addProperty(FOAF.knows, SIB);
		
		// Contact info
		studentsenteret.addProperty(postalAddress, "Parkveien1,5007Bergen,Norge");
		studentsenteret.addProperty(telephone, "55545124");
		studentsenteret.addProperty(email, "studentsenteret@sib.no");
		
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
		//Property workoutClass = model.createProperty(owl + "workoutClass");
		
		//saltimer
		Resource yoga = model.createResource(owl + "yoga"); yoga.addProperty(typeOfClass, saltime);
		Resource pilates = model.createResource(owl + "pilates"); pilates.addProperty(typeOfClass, saltime);
		Resource stepMoves = model.createResource(owl + "stepMoves"); stepMoves.addProperty(typeOfClass, saltime);
		Resource stepIntervall = model.createResource(owl + "stepIntervall"); stepIntervall.addProperty(typeOfClass, saltime);
		Resource tabata = model.createResource(owl + "tabata"); tabata.addProperty(typeOfClass, saltime);
		Resource styrkeStang = model.createResource(owl + "styrkeMedStang"); styrkeStang.addProperty(typeOfClass, saltime);
		Resource zumba = model.createResource(owl + "zumba"); zumba.addProperty(typeOfClass, saltime);
		Resource basisballRaw = model.createResource(owl + "basisballRaw"); basisballRaw.addProperty(typeOfClass, saltime);
		
		//spinning
		Resource pulsTopp55 = model.createResource(owl + "pulstopp55"); pulsTopp55.addProperty(typeOfClass, spinningtime);
		Resource intervall45 = model.createResource(owl + "intervall45"); intervall45.addProperty(typeOfClass, spinningtime);
		Resource intervall55 = model.createResource(owl + "intervall55"); intervall55.addProperty(typeOfClass, spinningtime);
		Resource intervall90 = model.createResource(owl + "intervall55"); intervall90.addProperty(typeOfClass, spinningtime);
		Resource raceday75 = model.createResource(owl + "raceday75"); raceday75.addProperty(typeOfClass, spinningtime);
		
		//basseng
		Resource aquaIntervall = model.createResource(owl + "aquaIntervall"); aquaIntervall.addProperty(typeOfClass, bassengtime);
		
		//spinning
		Resource intervallPyramide = model.createResource(owl + "intervallPyramide"); intervallPyramide.addProperty(typeOfClass, rotime);

		//liste
		Resource[]listOfTrainingFormsSIB = {yoga, pilates, };
		Resource ssClasses = model.createList(listOfTrainingFormsSIB);
		//studentsenteret.addProperty(workoutClass, ssClasses);
		

		// Tid / dag / varighet / instruktør
		Property starttid = model.createProperty(owl + "start");
		Property varighet = model.createProperty(owl + "varighet");
		Property dag = model.createProperty(owl + "dag");
		Property instrukor = model.createProperty(owl + "instruktor");
		
		// Mandag
		yoga.addProperty(starttid, "07:30").addProperty(dag, mo).addProperty(varighet, "55 min").addProperty(instrukor, "Randi");
		intervallPyramide.addProperty(starttid, "08:430").addProperty(dag, mo).addProperty(varighet, "60 min").addProperty(instrukor, "Eivind");
		pulsTopp55.addProperty(starttid, "16:15").addProperty(dag, mo).addProperty(varighet, "55 min").addProperty(instrukor, "Gina");
		stepMoves.addProperty(starttid, "16:45").addProperty(dag, mo).addProperty(varighet, "55 min").addProperty(instrukor, "Heidi");
		intervall45.addProperty(starttid, "18:00").addProperty(dag, mo).addProperty(varighet, "45 min").addProperty(instrukor, "Heidi");
		tabata.addProperty(starttid, "18:00").addProperty(dag, mo).addProperty(varighet, "45 min").addProperty(instrukor, "Anna");
		intervall55.addProperty(starttid, "19:00").addProperty(dag, mo).addProperty(varighet, "55 min").addProperty(instrukor, "Anna");
		yoga.addProperty(starttid, "19:00").addProperty(dag, mo).addProperty(varighet, "55 min").addProperty(instrukor, "");
		aquaIntervall.addProperty(starttid, "20:00").addProperty(dag, mo).addProperty(varighet, "45 min").addProperty(instrukor, "Gina");
		styrkeStang.addProperty(starttid, "20:00").addProperty(dag, mo).addProperty(varighet, "55 min").addProperty(instrukor, "Astrid Maria");
		
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

