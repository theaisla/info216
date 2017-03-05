package prosjekt;
	
	import java.io.FileOutputStream;

	import org.apache.jena.rdf.model.InfModel;
	import org.apache.jena.rdf.model.Model;
	import org.apache.jena.rdf.model.ModelFactory;
	import org.apache.jena.rdf.model.Resource;
	import org.apache.jena.rdf.model.Property;
	import org.apache.jena.sparql.vocabulary.FOAF;
	import org.apache.jena.vocabulary.RDFS;
	import org.apache.jena.vocabulary.OWL;

	public class StudentSenteret {
		
		public static void main(String[] args) {
			Model basemodel = ModelFactory.createDefaultModel();
			InfModel model = ModelFactory.createRDFSModel(basemodel);
			
			// Forkortelser
			String dbpedia = "http://dbpedia.org/resource/";
			String base = "http://example.org/";
			String schema = "http://schema.org/";
			String owl = "";
			
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
			studentsenteret.addProperty(postalAddress, "Parkveien 1, 5007 Bergen, Norge");
			studentsenteret.addProperty(telephone, "55545124");
			studentsenteret.addProperty(email, "studentsenteret@sib.no");
			
			//Åpningstider
			Property openingHours = model.createProperty(schema + "openingHours");
			Property mo = model.createProperty(schema + "Mandag");
			Property tu = model.createProperty(schema + "Tirsdag");
			Property we = model.createProperty(schema + "Onsdag");
			Property th = model.createProperty(schema + "Torsdag");
			Property fr = model.createProperty(schema + "Fredag");
			Property sa = model.createProperty(schema + "Lørdag");
			Property su = model.createProperty(schema + "Søndag");
			
			studentsenteret.addProperty(mo, "07:00 - 22:30");
			studentsenteret.addProperty(tu, "07:00 - 22:30");
			studentsenteret.addProperty(we, "07:00 - 22:30");
			studentsenteret.addProperty(th, "07:00 - 22:30");
			studentsenteret.addProperty(fr, "07:00 - 21:30");
			studentsenteret.addProperty(sa, "09:00 - 18:00");
			studentsenteret.addProperty(su, "10:00 - 20:00");
			
			Resource[]ssListOfOpeningHours = {mo, tu, we, th, fr, sa, su};
			Resource ssOpeningHours = model.createList(ssListOfOpeningHours);
			studentsenteret.addProperty(openingHours, ssOpeningHours);
			
			// Class type
			Resource spinningtime = model.createResource(owl + "spinning");
			Resource saltime = model.createResource(owl + "saltime");
			Resource bassengtime = model.createResource(owl + "bassengtimer");
			Resource rotime = model.createResource(owl + "rotime");
			
			
			// Classes 	
			//Property workoutClass = model.createProperty(owl + "workoutClass");
			
			Resource yoga = model.createResource(owl + "yoga");
			Resource pilates = model.createResource(owl + "pilates");
			
			Resource pulsTopp55 = model.createResource(owl + "pulstopp 55");
			Resource intervall45 = model.createResource(owl + "intervall 45");
			Resource intervall55 = model.createResource(owl + "intervall 55");
			
			Resource stepMoves = model.createResource(owl + "step moves");
			Resource tabata = model.createResource(owl + "tabata");
			Resource styrkeStang = model.createResource(owl + "styrke med stang");
			
			Resource aqualintervall = model.createResource(owl + "aqual intervall");
			
			Resource intervallP = model.createResource(owl + "intervall pyramide");

			Resource[]listOfTrainingFormsSIB = {yoga, pilates, };
			Resource ssClasses = model.createList(listOfTrainingFormsSIB);
			//studentsenteret.addProperty(workoutClass, ssClasses);
			

			//starttid
			Property starttid = model.createProperty(owl + "start");
			Property varighet = model.createProperty(owl + "varighet");
			stepMoves.addProperty(mo, "16:45");
			stepMoves.addProperty(varighet, "55 min");
			
			//varighet

			
			//Writing to file
	/*		try {
				model.write(new FileOutputStream("lab2.ttl"), "TURTLE");
			} catch (Exception e) {
				// TODO: handle exception
			}*/
			
			
			// Print
			model.write(System.out, "RDF/XML");
			model.close();
			
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
