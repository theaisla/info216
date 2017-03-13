package project216;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebsiteParser {

	public static void main(String[] args) throws IOException {
	
//
//		Document doc; 
//		doc = Jsoup.connect("https://www.satselixia.no/treningssentre/bergen/bergen-sats/").get();
//		
//		Elements link = doc.getElementsByTag("a");
//		for (Element e : link)
//			System.out.println("attr(abs:href)     " + e.attr("abs:href"));
//			String str = link.attr("href");
//			if (str.length()>0){
//				if(str.length()<4)
//					str = doc.baseUri()+str.substring(1);
//				else if (!str.substring(0, 4).equals("http"))
//					str = doc.baseUri()+str.substring(1);
//			}
//			System.out.println(str);
		
		File input = new File("Trene i Bergen - SATS - SATS ELIXIA.html");
		Document document = Jsoup.parse(input, "UTF-8", "http://example.com/");
		
	//	Document document = Jsoup.connect("Trene i Bergen - SATS - SATS ELIXIA.html").get();
		
		String [] tittel = new String [100];
		String [] tid = new String [100];
		String [] instruktør= new String [100];
		int i = 0;
		int k = 0;
		int l = 0;
		
			for (Element e : document.select(".mobile-container-fluid")){
				//div#content > p
//				String title = e.select("div.panel-heading > h4").text();
//				String time = e.select(".subtitle bold ng-binding").text();
	
				for (Element f : e.select("div.panel-heading > h4")){
				//	System.out.printf("%s15",f.text());
					tittel[i++]=f.text();
					
				}
				
				//for (Element f : e.select("div.panel-heading .subtitle bold ng-binding")){}
				for (Element f : e.select("div.panel-heading > p")){
					if (Character.isDigit(f.text().charAt(0))){
						tid[k++]=f.text();
					}
					else if (f.text().equals("Bergen SATS") || (f.text().equals(null)))
						continue;
					else
					instruktør[l++]=f.text();
				}
			}
			for (int j =0; j<tittel.length;j++)
			System.out.printf("%30s %30s %30s \n",tittel[j],tid[j],instruktør[j]);
	}

}
//System.out.println("Tittel: " + title + "  på tidspunkt " + time); //+ timeAndDuration + " med instruktør " + instructor );
//"subtitle bold ng-binding > p"