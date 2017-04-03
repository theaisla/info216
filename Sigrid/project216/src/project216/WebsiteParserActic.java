package project216;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class WebsiteParserActic {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Document document = Jsoup.connect("http://www.actic.no/finn-sentre/gym-bergen/torggaten/?gclid=CjwKEAjwqZ7GBRC1srKSv9TV_iwSJADKTjaDAHlqxxA1i01iQ9ePmgEUtO1YD7ptoKCQep30blsrVhoC75rw_wcB").get();

		for (Element e : document.select(".day.u-size1of8")){
			
			//String instructor = f.select(".title").text();
		
//			if (e.text().endsWith("r"))
//				continue;
//			
			
			String [] splitted = e.text().split("\\.");
	//	String [] splitted1 = splitted[0].split("\\.");
			if (splitted.length>3)
			System.out.printf("%s	 	%40s		%s 		%s \n", splitted[0], splitted[1], splitted[2], splitted[3]);
			//System.out.println(splitted.length+"	" +e.text());
		}
	}

}
