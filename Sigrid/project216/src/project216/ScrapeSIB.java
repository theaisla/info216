package project216;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;

import javax.lang.model.util.Elements;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ScrapeSIB implements IScraper {
	
	// Modellen som skal brukes i programmet
	Model model = ModelFactory.createDefaultModel();
	
	//liste med alle resources
	ArrayList<Resource> allResources = new ArrayList<Resource>();
	
	//liste med alle treningsformer
	ArrayList<Property> workoutTypes = new ArrayList<Property>();
	// URI
	String gymURI = "http://example/Sats";
	
	//Dagen som gjelder for de ulike resourcene - blir lest inn for hver dag.
	String theDay;
	
	// Størrelse på listen av resurser - for å vise til riktig resurs 
	private int numResources=0;
	
	private Document document;

	private String filename;

	public ScrapeSIB (String filename, Boolean isFile) throws IOException{
		
		
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
	
	@Override
	public void extractDataFromPage() throws IOException {
//		for (Element e: document.select("PGridRow.row.PassModelContainer.ui-state-disabled.X")){ //".ng-scope"
		for (Element e: document.select(".container-fluid.GroupTraining_container")){ //".ng-scope"
					
			System.out.println(e.text());
		}

	}

	@Override
	public boolean createResource(String title) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Time convertTime(String time) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void dumpQueryResult(Model model, String queryString) {
		// TODO Auto-generated method stub

	}

	@Override
	public String findOpeningHours() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findAddress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sparql() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getMonth() {
		// TODO Auto-generated method stub
		return 0;
	}

}
