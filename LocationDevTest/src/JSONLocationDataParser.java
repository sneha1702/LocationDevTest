import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import org.json.JSONArray;
import org.json.JSONException;

public class JSONLocationDataParser {
	public JSONArray getJsonFromUrl(final String url) throws IOException,
			JSONException {

		InputStream is = new URL(url).openStream();
		JSONArray jsonArr;
		try {
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(is,
					Charset.forName("UTF-8")));
			StringBuilder builder = new StringBuilder();
			String line = null;
			while ((line = rd.readLine()) != null) {
				builder.append(line);
			}
			rd.close();
			String jsonText = builder.toString().trim();
			if(jsonText.equals("[]"))
				jsonArr=null;	
			else
				jsonArr = new JSONArray(jsonText);				
			return jsonArr;
		} finally {
			is.close();

		}
	}

}
