package project216;

import java.io.IOException;

import CleanCode.ScrapeSats;

public class Main {

	public static void main(String[] args) throws IOException {
		//String satsFile = "Booke gruppetrening på SATS ELIXIA - SATS ELIXIA.html";
		String satsFile = "http://www.sib.no/no/trening/no/trening/no/trening/booking/booking_template";
		ScrapeSIB scraper = new ScrapeSIB(satsFile, false);

	}

}
