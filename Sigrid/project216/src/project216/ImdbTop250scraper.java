package project216;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ImdbTop250scraper {

	public static void main(String[] args) throws IOException {
		Document document = Jsoup.connect("http://www.imdb.com/chart/top").get();
		
		for (Element e: document.select("table.chart.full-width tr")){
			
			final String title = e.select(".titleColumn").text();
			System.out.println("title: " + title);
		}

	}

}
