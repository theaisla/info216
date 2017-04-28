package info216;

import java.io.FileOutputStream;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.vocabulary.VCARD;


public class lab2 {
	public static void main(String[] args) {
	/*
	 * ModelFactory (createDefaultModel), Model (createResource, createProperty,
	 * createLiteral, createStatement, add, createList, write, read,
	 * listStatements) StmtIterator (hasNext, nextStatement) Statement
	 * (getSubject, getPredicate, getObject) Resource (addProperty, addLiteral,
	 * removeProperties, removeAll) Property Literal RDFNode
	 */

		String iriBase = "http://no.uib.infomedia.info216/";
		String iriDbpedia = "http://dbpedia.org/resource/";
		

	// create an empty Model
	Model model = ModelFactory.createDefaultModel();
	//Model modelID = ModelFactory.createDefaultModel();
	Resource cadetracy = model.createResource("http://somewhere/CadeTracy");
	Resource inesdominguez = model.createResource("http://somewhere/InesDominguez");
	Property midleName = model.createProperty(iriBase + "midleName");
	cadetracy.addProperty(VCARD.N,
			model.createResource(iriBase + "Name").addProperty(VCARD.Given, "Cade").addProperty(VCARD.Family, "Tracy").addProperty(midleName, "Creighton").addProperty(VCARD.FN, "Cade Tracy"));
	
	inesdominguez.addProperty(VCARD.N,
			model.createResource().addProperty(VCARD.Given, "Ines").addProperty(VCARD.Family, "Dominguez").addProperty(midleName, "Maria").addProperty(VCARD.FN, "Ines Dominguez"));

	cadetracy.addProperty(FOAF.gender, "male");
	inesdominguez.addProperty(FOAF.gender, "female");
	Property propMet = model.createProperty(iriBase + "Met");
	cadetracy.addProperty(FOAF.knows, model.createResource().addProperty(FOAF.knows, inesdominguez).addProperty(propMet, "Paris"));
	inesdominguez.addProperty(FOAF.knows, model.createResource().addProperty(FOAF.knows, cadetracy).addProperty(propMet, "Paris"));
	Resource resVelencia = model.createProperty(iriDbpedia, "Velencia");
	Resource resCalefornia = model.createProperty(iriDbpedia, "Calefornia");
	Property propCity = model.createProperty(iriBase + "City");
	Property propAddress = model.createProperty(iriBase + "Address");

	Resource resAddress = model.createResource(iriBase + "Address").addProperty(VCARD.Country, "USA").addProperty(VCARD.Pcode, "94709")
			.addProperty(VCARD.Street, "1516 Henry Street").addProperty(propCity, resCalefornia);

	cadetracy.addProperty(propAddress,(RDFNode)resAddress);
	
	/*cadetracy.addProperty(VCARD.GEO,
					model.createResource().addProperty(VCARD.Country, "USA").addProperty(VCARD.Pcode, "94709")
							.addProperty(VCARD.Street, "1516 Henry Street").addProperty(propCity, resCalefornia));*/
	inesdominguez.addProperty(VCARD.GEO,
			model.createResource(iriBase + "address2").addProperty(VCARD.Country, "Spain").addProperty(VCARD.Pcode, "46020")
					.addProperty(VCARD.Street, "Carrer de la Guardia Civil 20").addProperty(propCity, resVelencia));

	
	Resource resUCB = model.createResource(iriBase + "UCB");
	Resource resUV = model.createResource(iriBase + "UV");
	Resource resBio = model.createResource(iriBase + "Biology");
	Resource resChem = model.createResource(iriBase + "Chemestry");
	Property propGraduatedYear = model.createProperty(iriBase + "GraduationYear");
	Property propGraduatedFrom = model.createProperty(iriBase + "graduatedFrom");
	Property propHasBScIn = model.createProperty(iriBase+ "hasBScIn");
	Property propHasMScIn = model.createProperty(iriBase+ "hasMScIn");
	cadetracy.addProperty(propHasBScIn, resBio).addProperty(propGraduatedFrom, resUCB).addProperty(propGraduatedYear, "2011");
	inesdominguez.addProperty(propHasMScIn, resChem).addProperty(propGraduatedFrom, resUV).addProperty(propGraduatedYear, "2015");
	
	Resource resCanada = model.createResource(iriDbpedia + "Canada");
	Resource resFrance = model.createResource(iriDbpedia + "France");
	Resource resPortugal = model.createResource(iriDbpedia + "Portugal");
	Resource resItaly = model.createResource(iriDbpedia + "Italy");
	Resource resGermany = model.createResource(iriDbpedia + "Germany");
	Resource resSweden = model.createResource(iriDbpedia + "Sweden");
	Resource resDanmark = model.createResource(iriDbpedia + "Danmark");
	Property propVisited = model.createProperty(iriBase + "visited");
	cadetracy.addProperty(propVisited, resCanada).addProperty(propVisited, resFrance);
	inesdominguez.addProperty(propVisited, resPortugal).addProperty(propVisited, resItaly).addProperty(propVisited, resGermany).addProperty(propVisited, resSweden)
	.addProperty(propVisited, resDanmark).addProperty(propVisited, resFrance);
	
	Property propIntrests = model.createProperty(iriBase + "intrests");
	Resource resBikeriding = model.createResource(iriDbpedia + "BikeRiding");
	Resource resMusic = model.createResource(iriDbpedia + "Music");
	Resource resTravelling = model.createResource(iriDbpedia + "Travelling");
	Resource resBirds = model.createResource(iriDbpedia + "Birds");
	Resource resEcology = model.createResource(iriDbpedia + "Ecology");
	Resource resEnviroment = model.createResource(iriDbpedia + "Enviroment");
	Resource resPhotography = model.createResource(iriDbpedia + "Photography");
	
	Resource[]listOfInterestsInes = {resBikeriding, resMusic, resTravelling};
	Resource[]listOfInterestsCade = {resBirds, resEcology, resTravelling, resEnviroment, resPhotography};
	
	Resource inesIntersts = model.createList(listOfInterestsInes);
	Resource cadeIntersts = model.createList(listOfInterestsCade);

	inesdominguez.addProperty(propIntrests, inesIntersts);
	cadetracy.addProperty(propIntrests, cadeIntersts);
	
	//Resource inesIntersts = model.createList().addProperty(propIntrests, resBikeriding).addProperty(propIntrests, resMusic).addProperty(propIntrests, resTravelling);
	//Resource cadeIntersts = model.createList().addProperty(propIntrests, resBirds).addProperty(propIntrests,resEcology).addProperty(propIntrests, resTravelling).addProperty(propIntrests, resEnviroment).addProperty(propIntrests, resPhotography);
	//inesdominguez.addLiteral(propIntrests, inesIntersts);
	//cadetracy.addProperty(propIntrests, cadeIntersts);

	
	//Writing to file
	try {
		model.write(new FileOutputStream("skriv.ttl"), "TURTLE");
	} catch (Exception e) {
		// TODO: handle exception
	}
	
System.out.println("--------------TURTLE------------------");
	model.write(System.out, "TURTLE");
	System.out.println("--------------N-TRIPLE------------------");
	model.write(System.out, "N-TRIPLE");
	System.out.println("--------------N3------------------");
	model.write(System.out, "N3");
	System.out.println("--------------JSON-LD------------------");
	model.write(System.out, "JSON-LD");
	System.out.println("--------------RDF/XML------------------");
	model.write(System.out, "RDF/XML");
	}

	
}
