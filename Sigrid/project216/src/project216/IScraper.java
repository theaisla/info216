package project216;

import java.io.IOException;
import java.sql.Time;

import org.apache.jena.rdf.model.Model;

public interface IScraper {

	
	/**
	 * Extracts the data from URL or file. 
	 * Data is used to add properties to the Resources
	 * 
	 * @param URL, the URL for the file
	 * @param isFile, is True if it is a file, false if it is a link
	 */
	void extractDataFromPage() throws IOException;
	
	
	/**
	 * Tries to create a resource. This is possible if it is of a set workout type.
	 * Otherwise there will be a default..
	 * 
	 * @param title, the title of the wourkout lesson
	 * @return True, if Resource was created
	 */
	boolean createResource(String title);
	
	/**
	 * Time converter
	 * 
	 * @param time, the time in string format
	 * @return the time in Time format
	 */
	Time convertTime(String time);
	
	/**
	 * A method that will allow queries to be ran on gym lessons
	 * 
	 * @param model, the model being used
	 * @param queryString 
	 */
	void dumpQueryResult(final Model model, final String queryString);

	/**
	 * 
	 * @return gym's opening hours
	 */
	String findOpeningHours();

	/**
	 * @return gym's address
	 */
	String findAddress();

	/**
	 * Temp solution to run queries
	 */
	void sparql();

	/**
	 *  
	 * @return The day the lesson ocurr on
	 */
	String getDay();
}