import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LocationAPI {

	private static String locName;

	public static void main(String args[]) throws IOException, JSONException {
		getQuery();
	}

	private static void showLocationData(String url) throws IOException,
			JSONException {
		List<LocationData> list = getLocationDataFromJSON(url);
		if (list != null && list.size() > 0)
			showLocationDataCSV(list);
		else
			System.out.println("Location Not found!");
	}

	private static void showLocationDataCSV(List<LocationData> list) {
		CSVOutput csvOutput = new CSVOutput();
		if (locName != null)
			csvOutput.writeCsvFile(list, locName + ".csv");
		else
			csvOutput.writeCsvFile(list, "LocationData.csv");
	}

	private static List<LocationData> getLocationDataFromJSON(String url)
			throws IOException, JSONException {
		JSONLocationDataParser parser = new JSONLocationDataParser();

		JSONArray jsonArray = parser.getJsonFromUrl(url);
		if (jsonArray != null)
			return getLocationDataList(jsonArray);
		else
			return null;
	}

	private static String getQuery() throws IOException, JSONException {
		String query = null;
		String url = null;
		System.out.println("Enter location name: ");
		Scanner sc = new Scanner(System.in);
		query = sc.nextLine();
		
		if (query.length() > 0) {
			locName = query;
			url = "http://api.goeuro.com/api/v2/position/suggest/en/CITY_NAME";
			url = url.replaceAll("CITY_NAME", query.trim());
			showLocationData(url);
		} else {
			System.out.println("Please enter valid data!");
			getQuery();
		}
		return url;
	}

	private static List<LocationData> getLocationDataList(JSONArray jsonArr)
			throws JSONException {
		List<LocationData> locationDataList = new ArrayList<LocationData>();
		LocationData locationData;
		for (int i = 0; i < jsonArr.length(); i++) {
			locationData = new LocationData();
			JSONObject jsonObj = jsonArr.getJSONObject(i);

			locationData.setId(jsonObj.getString("_id"));
			locationData.setName(jsonObj.getString("name"));
			locationData.setType(jsonObj.getString("type"));
			locationData.setLatitude((Double) jsonObj.getJSONObject(
					"geo_position").get("latitude"));
			locationData.setLongitude((Double) jsonObj.getJSONObject(
					"geo_position").get("longitude"));
			locationDataList.add(locationData);
		}
		return locationDataList;
	}
}
