package CleanCode;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.jena.rdf.model.Resource;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class SatsScraper {

	public static void main(String[] args) throws IOException {


		String filename = "Booke gruppetrening på SATS ELIXIA - SATS ELIXIA.html";
		
		ScrapeSats scraper = new ScrapeSats(filename, true);
	//	WebsiteParserActic a = new WebsiteParserActic();
		
//		String filename = "Booking — SiB.html";
	//	ScrapeSIB ss = new ScrapeSIB(filename, true);
	}
}