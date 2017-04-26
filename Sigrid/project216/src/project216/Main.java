package project216;

import java.io.IOException;

import CleanCode.ScrapeSats;

public class Main {

	public static void main(String[] args) throws IOException {
		String satsFile = "Booke gruppetrening på SATS ELIXIA - SATS ELIXIA.html";
		ScrapeSats scraper = new ScrapeSats(satsFile, true);

	}

}
