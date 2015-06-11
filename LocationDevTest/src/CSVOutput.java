import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVOutput {
	private static final String FILE_HEADER = "id,name,type,latitude,longitude";
	private static final String NEW_LINE_SEPARATOR = "\n";
	private static final String COMMA_DELIMITER = ",";
	private static final String FILE_NAME = "csvfile.csv";

	public void writeCsvFile(List<LocationData> dataList, String filename) {
		FileWriter fileWriter = null;
		System.out.println("filename "+ filename);
		
		try {
			
			fileWriter = new FileWriter(filename);
			fileWriter.append(FILE_HEADER.toString());
			// Add a new line separator after the header
			fileWriter.append(NEW_LINE_SEPARATOR);
			// Write a new LocationData object list to the CSV file
			for (LocationData data : dataList) {
				fileWriter.append(String.valueOf(data.getId()));

				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(data.getName()));

				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(data.getType()));

				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(data.getLatitude()));

				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(data.getLongitude()));

				fileWriter.append(NEW_LINE_SEPARATOR);
			}
			System.out.println("CSV file name: "+ filename);
			System.out.println("CSV file was created successfully !!!");
		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {

			try {

				fileWriter.flush();

				fileWriter.close();

			} catch (IOException e) {

				System.out
						.println("Error while flushing/closing fileWriter !!!");

				e.printStackTrace();

			}

		}
	}

}
