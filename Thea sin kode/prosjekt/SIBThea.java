package prosjekt;

import java.io.FileOutputStream;

import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.vocabulary.RDFS;

public class SIBThea {
	
	public static void main(String[] args) {
		Model basemodel = ModelFactory.createDefaultModel();
		InfModel model = ModelFactory.createRDFSModel(basemodel);
		
		// Forkortelser
		String dbpedia = "http://dbpedia.org/resource/";
		String base = "http://example.org/";
		String schema = "http://schema.org/";
		
		// Properties
		Property postalAddress = model.createProperty(schema + "PostalAddress");
		Property phoneNr = model.createProperty(base + "phoneNumber");
		Property email = model.createProperty(base + "email");
		
		// SIB
		Resource SIB = model.createResource("www.sib.no");

		
		//Vektertorget
		Resource vektertorget = model.createResource(base + "vektertorget");
		
		vektertorget.addProperty(postalAddress, "Christiessgt. 13, 5015 Bergen, Norge");
		vektertorget.addProperty(phoneNr, "55545180");
		vektertorget.addProperty(email, "vektertorget.treningssenter@sib.no");


		
		
		// Give name
		cadeTracy.addProperty(FOAF.name, "Cade Tracy");
		inesD.addProperty(FOAF.name, "Ines Dominguez");
	
		// Relationship
		cadeTracy.addProperty(FOAF.knows, inesD);
		inesD.addProperty(FOAF.knows, cadeTracy);
		
		// Bachelor from
		Property bachelorFrom = model.createProperty(schema + "CollegeOrUniversity");
		cadeTracy.addProperty(bachelorFrom, "Berkley");
		inesD.addProperty(bachelorFrom, "University of Valencia");
	
		// Graduation year
		Property graduationYear = model.createProperty(base	+ "graduationYear");
		cadeTracy.addProperty(graduationYear, "2011");
		inesD.addProperty(graduationYear, "2015");
		
		// Address
		Property postalAddress = model.createProperty(schema + "PostalAddress");
		cadeTracy.addProperty(postalAddress, "1516 Henry Street, Berkely, California 94709, USA");
		inesD.addProperty(postalAddress, "Carrer de la Guardia Civil 20, 46020 Valencia, Spain");
		
		// Interests List		
		Property propIntrests = model.createProperty(base + "intrests");
		Resource resBikeriding = model.createResource(dbpedia + "BikeRiding");
		Resource resMusic = model.createResource(dbpedia + "Music");
		Resource resTravelling = model.createResource(dbpedia + "Travelling");
		Resource resBirds = model.createResource(dbpedia + "Birds");
		Resource resEcology = model.createResource(dbpedia + "Ecology");
		Resource resEnviroment = model.createResource(dbpedia + "Enviroment");
		Resource resPhotography = model.createResource(dbpedia + "Photography");

		Resource[]listOfInterestsInes = {resBikeriding, resMusic, resTravelling};
		Resource[]listOfInterestsCade = {resBirds, resEcology, resTravelling, resEnviroment, resPhotography};
		
		Resource inesIntersts = model.createList(listOfInterestsInes);
		Resource cadeIntersts = model.createList(listOfInterestsCade);

		inesD.addProperty(propIntrests, inesIntersts);
		cadeTracy.addProperty(propIntrests, cadeIntersts);
		
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
}
