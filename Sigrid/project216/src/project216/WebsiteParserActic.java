package project216;

import java.io.IOException;
import java.sql.Time;

import org.apache.jena.rdf.model.Model;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class WebsiteParserActic implements IScraper {

	public WebsiteParserActic () throws IOException{
		extractDataFromPage();
	}


	@Override
	public void extractDataFromPage() throws IOException {
		// TODO Auto-generated method stub
		Document document = Jsoup.connect("http://www.actic.no/finn-sentre/gym-bergen/torggaten/?gclid=CjwKEAjwqZ7GBRC1srKSv9TV_iwSJADKTjaDAHlqxxA1i01iQ9ePmgEUtO1YD7ptoKCQep30blsrVhoC75rw_wcB").get();

		for (Element e : document.select(".day.u-size1of8")){
			
			String instructor = e.select(".title").text();
			System.out.println(instructor);
			
			String [] splitted = e.text().split("\\.");
	//	String [] splitted1 = splitted[0].split("\\.");
			if (splitted.length>3)
			System.out.printf("%s	 	%40s		%s 		%s \n", splitted[0], splitted[1], splitted[2], splitted[3]);
			//System.out.println(splitted.length+"	" +e.text());
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
