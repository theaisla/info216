package project216;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import com.github.jsonldjava.utils.JsonUtils;

public class ConnectionToAPI {

	URL API;
	String key;

	public ConnectionToAPI(URL API, String key) {
		if (API == null || key == null)
			throw new IllegalStateException("API or key is zero");

		this.API = API;
		this.key = key;

		getJsonBody(API);
	}

	/**
	 * We access the web APIs in a rather simple way, because our focus in
	 * INFO216 is on Jena and JSONLD, not on web APIs in themselves.
	 */
	static Object getJsonBody(URL serverAddress) {
		Object jsonObject = null;
		HttpURLConnection connection = null;a

		try {
			// send GET request
			connection = null;
			connection = (HttpURLConnection) serverAddress.openConnection();
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
