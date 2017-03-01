package info216lab5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.github.andrewoma.dexx.collection.HashMap;
import com.github.jsonldjava.utils.JsonUtils;


import com.github.jsonldjava.utils.JsonUtils;

public class APIstuff {

	  /**    We access the web APIs in a rather simple way, because our focus in INFO216
    is on Jena and JSONLD, not on web APIs in themselves. 
*/

static Object getJsonBody(URL serverAddress) {
   Object jsonObject = null;
   HttpURLConnection connection = null;

   try {
       // send GET request
       connection = null;
       connection = (HttpURLConnection)serverAddress.openConnection();
       connection.setRequestMethod("GET");
       connection.setDoOutput(true);
       connection.setReadTimeout(10000);
       connection.connect();

       // parse JSON reponse
       jsonObject = JsonUtils.fromInputStream(connection.getInputStream());

   } catch (MalformedURLException e) {
       e.printStackTrace();
   } catch (ProtocolException e) {
       e.printStackTrace();
   } catch (IOException e) {
       e.printStackTrace();
   } finally {
       // close the connection
       connection.disconnect();
       connection = null;
   }

   return jsonObject;
}
	
public static void main (String[]args) throws JsonGenerationException, IOException{
	URL url = new URL ("http://api.geonames.org/astergdemJSON?formatted=true&lat=50.01&lng=10.2&username=demo&style=full");
	
	BufferedReader br = new BufferedReader(new FileReader ("file.txt"));

	String s = "";
	String ss = "";
	while ((ss=br.readLine())!=null){
		s += ss;
		s+= "\n";
	}

	System.out.println(s);
	
	
	
	
}
	
}
