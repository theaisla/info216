package project216;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class SIBscraper {

	public static void main(String[] args) throws IOException {
		
		
		Document document = Jsoup.connect("http://www.sib.no/no/trening/no/trening/no/trening/booking/booking_template").get();
		
		for (Element e : document.select("div.")){
			final String tittel = e.select("").text();
			System.out.println(tittel);
		}

	}

}


//Den innerste linken du må åpne for å komme til ønket node
//
//:<div id="content">
//->
//->
//  <p> ønsket sring </> dvs .first()