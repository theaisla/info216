package CleanCode;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.validation.Schema;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.apache.jena.vocabulary.DC_10;
import org.apache.jena.vocabulary.XSD;
import org.apache.jena.vocabulary.OWL2;
import org.apache.jena.vocabulary.VCARD;
import project216.IScraper;


public class ScrapeFitnessNr1 {
	

		// Modellen som skal brukes i programmet
		Model model = ModelFactory.createDefaultModel();
		
		//liste med alle resources
		ArrayList<Resource> allResources = new ArrayList<Resource>();
		
		//liste med alle treningsformer
		ArrayList<Property> workoutTypes = new ArrayList<Property>();
		// URI
		String gymURI = "http://example/Sats/";
		
		//Dagen som gjelder for de ulike resourcene - blir lest inn for hver dag.
		String theDay;
		
		// Størrelse på listen av resurser - for å vise til riktig resurs 
		private int numResources=0;
		
		private Document document;
		
		
		
	 public ScrapeFitnessNr1(String filename , boolean isFile) throws IOException{
			
			if (isFile){
				File input = new File(filename);
				document = Jsoup.parse(input, "UTF-8", "http://example.com/");
			}
			else{
				document = Jsoup.connect(filename).get();
			}
			extractDataFromPage();
			//sparql();
		} 


		public void extractDataFromPage() throws IOException{
			

			//dokument og 
//			Property starttid = model.createProperty(gymURI + "Start");
//			Property varighet = model.createProperty(gymURI + "Varighet");
//			Property dag = model.createProperty(gymURI + "Dag");
//			Property dato = model.createProperty(gymURI + "Dato");
//			Property instruktoer = model.createProperty(gymURI + "Instruktoer");
//			Property treningssenter = model.createProperty(gymURI + "Treningssenter");
//			Property omraade = model.createProperty(gymURI + "Område");
			
			

//		outerloop: // gir muligheten til å hoppe til neste element dersom søk er ferdig for e
//			for (Element e: document.select(".bookWeek.clearfix li")){ //".ng-scope"
//				
//				String title = e.select(".activity").text();
//				System.out.println(title);
//				System.out.println(e.text());	
//			//	String title = e.select(".title.ng-binding").text();

				Elements elements = document.body().select("*");

				for (Element element : elements) {
				    System.out.println(element.text());
				}
		}
		
		/**
		 * Lager resurser ettersom at de finnes i arkivet workoutTypes - finnes de ikke skal en default verdi 
		 * ta over. 
		 * 
		 * @param title, tittelen til gruppetimen
		 * @return True dersom en resurs ble laget
		 */
		
		public boolean createResource(String title){
			
			Resource flextrening = model.createResource(gymURI + "Flex");
			Resource kondisjonstrening = model.createResource(gymURI + "kondisjon");
			Resource styrketrening = model.createResource(gymURI + "styrke");
			
			
			Property yoga = model.createProperty(gymURI + "/Yoga"); Property pilates = model.createProperty(gymURI + "/Pilates");
			Property cycling = model.createProperty(gymURI + "/Cycling"); Property mølle = model.createProperty(gymURI + "/Mølle");
			Property styrke = model.createProperty(gymURI + "/Styrke"); Property senior = model.createProperty(gymURI + "/Senior");
			Property tabata = model.createProperty(gymURI + "/Tabata"); Property run = model.createProperty(gymURI + "/Run");
			Property zumba = model.createProperty(gymURI + "/Zumba"); Property intervall = model.createProperty(gymURI + "/Intervall");
			Property dans = model.createProperty(gymURI + "/Dans/Dance"); Property sterk = model.createProperty(gymURI + "/Sterk");
			Property aqua = model.createProperty(gymURI + "/Aqua"); Property pump = model.createProperty(gymURI + "/Pump");
			Property stang = model.createProperty(gymURI + "/Stang"); Property step = model.createProperty(gymURI + "/Step");
			Property kettlebell = model.createProperty(gymURI + "/Kettlebell"); Property power = model.createProperty(gymURI + "/Power");
			Property leg = model.createProperty(gymURI + "/Leg"); Property trx = model.createProperty(gymURI + "/TRX");
			Property crosstraining = model.createProperty(gymURI + "/Crosstraining"); Property build = model.createProperty(gymURI + "/Build");
			Property shape = model.createProperty(gymURI + "/Shape"); Property strength = model.createProperty(gymURI + "/Strength");
			Property intensity = model.createProperty(gymURI + "/Intensity");
			
			
			yoga.addProperty(OWL2.topDataProperty, flextrening); pilates.addProperty(OWL2.topDataProperty, flextrening);
			cycling.addProperty(OWL2.topDataProperty, kondisjonstrening); mølle.addProperty(OWL2.topDataProperty, kondisjonstrening);
			styrke.addProperty(OWL2.topDataProperty, styrketrening); senior.addProperty(OWL2.topDataProperty, flextrening);
			
			
			
			
			
			workoutTypes.add(yoga); workoutTypes.add(cycling); workoutTypes.add(styrke); workoutTypes.add(tabata); workoutTypes.add(zumba); 
			workoutTypes.add(dans); workoutTypes.add(aqua); workoutTypes.add(trx); workoutTypes.add(stang); workoutTypes.add(kettlebell); 
			workoutTypes.add(leg); workoutTypes.add(pilates); workoutTypes.add(mølle); workoutTypes.add(senior); workoutTypes.add(run); 
			workoutTypes.add(intervall); workoutTypes.add(sterk); workoutTypes.add(pump); workoutTypes.add(step); workoutTypes.add(power); 
			workoutTypes.add(crosstraining); workoutTypes.add(build); workoutTypes.add(shape); workoutTypes.add(strength); 
			workoutTypes.add(intensity);// workoutTypes.add(yoga); workoutTypes.add(yoga); workoutTypes.add(yoga); workoutTypes.add(yoga); 
			

			String titleUpperCase = title.toUpperCase();

			for (int i=0; i<workoutTypes.size();i++)

			if	(titleUpperCase.contains(workoutTypes.get(i).getLocalName().toUpperCase())){
				
				Resource myResource = (Resource) model.createResource(gymURI + numResources + workoutTypes.get(i));//numresources for å få en unik URI
				allResources.add(myResource);
				myResource.addProperty(DC_10.title, title);
				numResources++;
				return true;
			}
			return false;
		}
		
		



		public Time convertTime(String time) {
			// TODO Auto-generated method stub
			return null;
		}


		
//		public void sparql(){
//		// SPARQL query
//	    dumpQueryResult(model,String.format(
//	    		"prefix a: <http://example/Sats> SELECT ?x ?p WHERE {?x ?yoga ?p}"));
	//


	    public void dumpQueryResult(final Model model,
	            final String queryString) {
	        Query query = QueryFactory.create(queryString);
	        QueryExecution qe = QueryExecutionFactory.create(query, model);
	        ResultSet results = qe.execSelect();
	        ResultSetFormatter.out(System.out, results, query);
	        qe.close();
	    }
		



}