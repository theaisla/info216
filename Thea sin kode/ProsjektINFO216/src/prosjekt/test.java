package prosjekt;

import java.io.FileOutputStream;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

public class test {
	
	public static void main(String[] args) {
		Model basemodel = ModelFactory.createDefaultModel();
		InfModel model = ModelFactory.createRDFSModel(basemodel);
		
		// Forkortelser
		// String dbpedia = "http://dbpedia.org/resource/";
		String schema = "http://schema.org/";
		String owl = "http://owl.org/";
		
		
		Property mo = model.createProperty(schema + "Mandag");
		

		Resource treningssenter = model.createResource(owl + "treningssenter");

		// Studentsenteret
		Resource studentsenteret = model.createResource(owl + "studentsenteret");
		
		Property erType = model.createProperty(schema + "ertype");
		Property harSenter = model.createProperty(schema + "harsenter");
		studentsenteret.addProperty(erType, treningssenter);
		treningssenter.addProperty(harSenter, studentsenteret);
		
		
		// Properties
		Property postalAddress = model.createProperty(schema + "PostalAddress");
		studentsenteret.addProperty(postalAddress, "Parkveien1,5007Bergen,Norge");
		
		
		// Class type
		Resource styrketrening = model.createResource(owl + "styrketrening");
		
		Resource flex = model.createResource(owl + "flex");
		
		
		
		
		Property typeOfClass = model.createProperty(owl + "typeOfClass");
		Property hasClasses = model.createProperty(owl + "hasClasses");

		
		//saltimer
		Resource yoga = model.createResource(owl + "yoga"); yoga.addProperty(typeOfClass, flex); flex.addProperty(hasClasses, yoga);
		Resource pilates = model.createResource(owl + "pilates"); pilates.addProperty(typeOfClass, flex); flex.addProperty(hasClasses, pilates);

		//styrke
		Resource styrkeStang = model.createResource(owl + "styrkeMedStang"); styrkeStang.addProperty(typeOfClass, styrketrening); styrketrening.addProperty(hasClasses, styrkeStang);
		

		// Tid / dag / varighet / instruktør
		Property starttid = model.createProperty(owl + "start");
		Property varighet = model.createProperty(owl + "varighet");
		Property dag = model.createProperty(owl + "dag");
		
		Property isClass = model.createProperty(owl + "isClass");
		
		// Mandag
		
		Resource yogaSSM = model.createResource(owl + "yogaSSM"); yogaSSM.addProperty(isClass, yoga).addProperty(starttid, "07:30")
			.addProperty(dag, mo).addProperty(varighet, "55");
		
		Resource yogaSSM2 = model.createResource(owl + "yogaSSM2"); yogaSSM2.addProperty(isClass, yoga).addProperty(starttid, "18:00")
		.addProperty(dag, mo).addProperty(varighet, "55");
		
		Resource pilatesSSM = model.createResource(owl + "pilatesSSM"); pilatesSSM.addProperty(isClass, pilates).addProperty(starttid, "10:00")
		.addProperty(dag, mo).addProperty(varighet, "55");
		
		
		
		// Writing to file
		try {
			model.write(new FileOutputStream("test.ttl"), "TURTLE");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		System.out.println("--------------TURTLE------------------");
		model.write(System.out, "TURTLE");
		
	}

}
