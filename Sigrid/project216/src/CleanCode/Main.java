package CleanCode;

import java.io.FileOutputStream;
import java.io.IOException;


public class Main {

	public static void main(String[] args) throws IOException {


		String filename = "Booke gruppetrening på SATS ELIXIA - SATS ELIXIA.html";
		
		ScrapeSats scraper = new ScrapeSats(filename, true);
		
		
//		System.out.println("TURTLE");
//		
		scraper.model.write(System.out, "TURTLE");
//		

		//Writing to file
//		try {
//			scraper.model.write(new FileOutputStream("City.ttl"), "TURTLE");
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		
		// SPARQL query
	    System.out.println("Find all paths from A to B in exactly two steps");
	    scraper.dumpQueryResult(
	            scraper.model,
	            String.format(
	                    "prefix a: <http://example/Sats/> SELECT ?x ?p WHERE { a:91Yoga ?x ?p}",
	                    	("City.ttl")));
		
			
		//	WebsiteParserActic a = new WebsiteParserActic();	
		//	String filename = "Booking — SiB.html";
		//	ScrapeSIB ss = new ScrapeSIB(filename, true);
	}
}