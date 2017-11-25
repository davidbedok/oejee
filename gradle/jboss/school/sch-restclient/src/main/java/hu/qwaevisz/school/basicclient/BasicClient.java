package hu.qwaevisz.school.basicclient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BasicClient {

	private static final String REQUEST_PAYLOAD = "" //
			+ "<markcriteria>" //
			+ "  <subject>Programming</subject>" //
			+ "  <minimumgrade>1</minimumgrade>" //
			+ "  <maximumgrade>3</maximumgrade>" //
			+ "</markcriteria>";

	public static void main(String[] args) throws IOException {
		URL url = new URL("http://localhost:8080/school/api/student/marks/WI53085");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/xml");
		connection.setUseCaches(false);
		connection.setDoOutput(true);
		DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
		outputStream.writeBytes(REQUEST_PAYLOAD);
		outputStream.close();

		InputStream inputStream = connection.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		StringBuilder response = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			response.append(line);
		}
		reader.close();

		System.out.println(response);
	}

}
