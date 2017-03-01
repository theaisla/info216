package info216lab5;


import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.sparql.vocabulary.FOAF;

import com.github.jsonldjava.core.JsonLdOptions;
import com.github.jsonldjava.core.JsonLdProcessor;
import com.github.jsonldjava.utils.JsonUtils;

public class JSONLDExpand {

     public static void main(String[] args) throws Exception {

         // we pretend this string comes from a regular JSON web API call
         String proxyJsonReponse = ""
                 + "{"
                 + "    \"name\" : \"Markus Lanthaler\", "
                 + "    \"workplaceHomepage\" :\"http://www.homepage.com/ML\", "
                 + "    \"address\" : {"
                 + "        \"streetAddress\" : \"Somestreet 123\", "
                 + "        \"cityAddress\" : \"ZIP-4567 Acity\" "
                 + "    }"
                 + "}";

         // parse the string into a JSON object
         Object jsonObj = JsonUtils.fromString(proxyJsonReponse);
         // System.out.println(JsonUtils.toPrettyString(jsonObj));

         // create a context object
         String baseIRI = "http://ex.org/base#";
         Map contextObj = new HashMap() {{
             put("@context", new HashMap() {{
                 put("name", FOAF.name.getURI());
                 put("workplaceHomepage", "@id");
                 put("address", baseIRI + "address");
                 put("streetAddress", baseIRI + "streetAddress");
                 put("cityAddress", baseIRI + "cityAddress");
             }});
         }};
         // note: without a mapping for "address" in the context,
         // expansion will not work for "street-" and "cityAdress",
         // because they are nested inside "address" in the JSON object

         // METHOD 1 (an explicit options object can give more control)

         // create and set an options object
         JsonLdOptions expandOptions = new JsonLdOptions(baseIRI);
         expandOptions.setExpandContext(contextObj);

         // expand the JSON object
         Object expandedObj = JsonLdProcessor.expand(jsonObj,
expandOptions);
         System.out.println(JsonUtils.toPrettyString(expandedObj));

//        // METHOD 2 (briefer, and some JSON-LD web APIs creates outputin this form)
//
//        // alternative 1
//        // contextObj.put("@graph", jsonObj);
//        // Object expandedObj = JsonLdProcessor.expand(contextObj);
//        // System.out.println(JsonUtils.toPrettyString(expandedObj));
//
//        // alternative 2
//        // ((Map) jsonObj).put("@context", contextObj.get("@context"));
//
//        // alternative 3
//        ((Map) jsonObj).putAll(contextObj);
//
//        // expand the JSON object
//        Object expandedObj = JsonLdProcessor.expand(jsonObj);
//        // System.out.println(JsonUtils.toPrettyString(expandedObj));

         // read the JSON object into a model
         Model model = ModelFactory.createDefaultModel();
         model.read(IOUtils.toInputStream(JsonUtils.toPrettyString(expandedObj),
        		 	"UTF-8"), baseIRI, "JSON-LD");
         model.write(System.out, "JSON-LD");

         JsonLdOptions defaultOptions = new JsonLdOptions();
//        Object flattenedExpandedObj =
JsonLdProcessor.flatten(contextObj, defaultOptions);
// System.out.println(JsonUtils.toPrettyString(flattenedExpandedObj));
     }
}
