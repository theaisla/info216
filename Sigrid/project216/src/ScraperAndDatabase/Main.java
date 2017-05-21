package CleanCode;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.jena.rdf.model.Model;


public class Main {

	public static void main(String[] args) throws IOException {


		String fileSATSELIXIA = "Booke gruppetrening på SATS ELIXIA - SATS ELIXIA.html";
		SATSELIXIA satsElixia = new SATSELIXIA(fileSATSELIXIA, true);
		
		String fileActic = "Gym Bergen - Actic Norge.html";
		ACTIC actic = new ACTIC(fileActic, true);
		
		SIBCity sibCity = new SIBCity();
		SIBStudentsenteret sibStud = new SIBStudentsenteret();
		
		Model modelUnion = satsElixia.model.union(sibCity.model);
			  modelUnion = modelUnion.union(sibStud.model);
			  modelUnion = modelUnion.union(actic.model);


		modelUnion.write(System.out, "TURTLE");

		try {
			modelUnion.write(new FileOutputStream("FindMyFitness.ttl"), "TURTLE");
			modelUnion.write(new FileOutputStream("FindMyFitness.xml"), "RDF/XML");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		
		
		
//		// SPARQL query
//	    System.out.println("Find all paths from A to B in exactly two steps");
//	    scraper.dumpQueryResult(
//	            scraper.model,
//	            String.format(
//	                    "prefix a: <http://example/Sats/> SELECT ?x ?p WHERE { a:91Yoga ?x ?p}",
//	                    	("City.ttl")));
//		
//			
//		//	WebsiteParserActic a = new WebsiteParserActic();	
		//	String filename = "Booking — SiB.html";
		//	ScrapeSIB ss = new ScrapeSIB(filename, true);
	}
}