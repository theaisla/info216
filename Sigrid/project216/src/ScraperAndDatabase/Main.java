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
			throw new IllegalStateException(" kan ikke skrive modellen til fil ");
		}
	}
}