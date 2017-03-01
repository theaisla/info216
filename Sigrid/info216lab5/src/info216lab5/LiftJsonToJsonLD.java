package info216lab5;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.sparql.vocabulary.FOAF;

import com.fasterxml.jackson.core.JsonParseException;
import com.github.jsonldjava.core.JsonLdError;
import com.github.jsonldjava.core.JsonLdOptions;
import com.github.jsonldjava.core.JsonLdProcessor;
import com.github.jsonldjava.utils.JsonUtils;


public class LiftJsonToJsonLD {

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
	
	
	
	public static void main(String[] args) throws JsonParseException, IOException, JsonLdError {

		URL url = new URL ("http://api.geonames.org/astergdemJSON?formatted=true&lat=50.01&lng=10.2&username=demo&style=full");
		
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader ("file.ttl"));

		String filTekst = "";
		String ss = "";
		while ((ss=br.readLine())!=null){
			filTekst += ss;
			//filTekst+= "\n";
		}
		
		System.out.println(filTekst);
		
		
		
        String proxyJsonReponse = ""
                + "{"
                + "    \"name\" : \"Markus Lanthaler\", "
                + "    \"workplaceHomepage\" :\"http://www.homepage.com/ML\", "
                + "    \"address\" : {"
                + "        \"streetAddress\" : \"Somestreet 123\", "
                + "        \"cityAddress\" : \"ZIP-4567 Acity\" "
                + "    }"
                + "}";
        
        Object jsonObj = JsonUtils.fromString(filTekst);
       // Object jsonObj = JsonUtils.fromString(proxyJsonReponse);
        
		String baseIRI = "http://ex.org/base#";
		
		
		
        // create a context object
       // String baseIRI = "http://ex.org/base#";
        Map context = new HashMap() {{
            put("@context", new HashMap() {{
                put("name", FOAF.name.getURI());
                put("workplaceHomepage", "@id");
                put("address", baseIRI + "address");
                put("streetAddress", baseIRI + "streetAddress");
                put("cityAddress", baseIRI + "cityAddress");
            }});
        }};

		//lag og sett et options object
		JsonLdOptions expandedOptions = new JsonLdOptions(baseIRI);
		expandedOptions.setExpandContext(context);
		
		
		Object expandedObj = JsonLdProcessor.expand(jsonObj,expandedOptions);
		System.out.println(JsonUtils.toPrettyString(expandedObj));

		/*We will now make a Jena model from the JSON-LD object. 
		 * To do this, first create a new default Jena model. 
		 * Then convert the JSON-LD object to a string (use JsonUtils.toPrettyString).
		 * Then turn the string into an input stream (use IOUtils.toInputStream, with "UTF-8" as character set). 
		 * Then read the input stream into your Jena model (use model.read). 
		 * (There may be other ways to move from JSON object to Jena models, 
		 * but this is a simple and straightforward way to start.)
		 */
		
		Model model = ModelFactory.createDefaultModel();
		String obj = JsonUtils.toPrettyString(expandedObj);
	//	InputStream input = IOUtils.toInputStream(obj, "UTF-8");
		model.read(IOUtils.toInputStream(
				JsonUtils.toPrettyString(expandedObj),"UTF-8"), baseIRI, "JSON-LD");
		model.write(System.out, "JSON-LD");
	}

}
