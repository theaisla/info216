package project216;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class SIBscraper {

	public static void main(String[] args) throws IOException {
		
		
//		String filename = "Booking — SiB.html";
//		File input = new File(filename);
//		Document document = Jsoup.parse(input, "UTF-8", "http://example.com/");
		Document document = Jsoup.connect("https://nr1fitness.no/fyllingsdalen/timeplan/").get();
		for (Element e : document.select(".booking-selects")){
			//String tittel = e.select("").text();
			System.out.println(e.text());
		}

	}

}


//Den innerste linken du må åpne for å komme til ønket node
//
//:<div id="content">
//->
//->
//  <p> ønsket sring </> dvs .first()