package logmaker.lib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Requester {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final int MAX_CONNECT_TIME = 30000;

	private Requester() {}

	private static class Singleton {
		private static final Requester instance = new Requester();
	}

	public static Requester getInstance() {
		return Singleton.instance;
	}

	public String get(String requestURL) throws Exception {
		URL url = null;
		HttpURLConnection httpURLConnection = null;

		try {
			url = new URL(requestURL);
			httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setConnectTimeout(MAX_CONNECT_TIME);
		} catch (MalformedURLException e) {
			logger.error("Requester > requestGet(String requestURL) MalformedURLException - requestURL is " + requestURL);
			logger.error(e.getMessage());
			throw new Exception("Requester > requestGet(String requestURL) MalformedURLException - requestURL is " + requestURL);
		} catch (IOException e) {
			logger.error("Requester > requestGet(String requestURL) IOException - requestURL is " + requestURL);
			logger.error(e.getMessage());
			throw new Exception("Requester > requestGet(String requestURL) IOException - requestURL is " + requestURL);
		}

		return getData(httpURLConnection);
	}

	public String post(String requestURL, String postData) throws Exception {
		URL url = null;
		HttpURLConnection httpURLConnection = null;

		try {
			url = new URL(requestURL);
			httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setConnectTimeout(MAX_CONNECT_TIME);
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			httpURLConnection.setDoOutput(true);
		} catch (MalformedURLException e) {
			logger.error("Requester > requestPost(String requestURL, String postData) MalformedURLException - requestURL is " + requestURL);
			logger.error(e.getMessage());
			throw new Exception("Requester > requestPost(String requestURL, String postData) MalformedURLException - requestURL is " + requestURL);
		} catch (IOException e) {
			logger.error("Requester > requestPost(String requestURL, String postData) IOException - requestURL is " + requestURL);
			logger.error(e.getMessage());
			throw new Exception("Requester > requestPost(String requestURL, String postData) IOException - requestURL is " + requestURL);
		}

		return getData(httpURLConnection);
	}


	private String getData(HttpURLConnection httpURLConnection) throws Exception {
		int responseCode = 0;

		try {
			responseCode = httpURLConnection.getResponseCode();
		} catch(IOException e) {
			logger.error("Requester > getRequestData(HttpURLConnection httpURLConnection) IOException - responseCode error");
			logger.error(e.getMessage());
			throw new Exception("Requester > getRequestData(HttpURLConnection httpURLConnection) IOException - responseCode error");
		}

		InputStreamReader inputStreamReader = null;

		try {
			inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
		} catch(IOException e) {
			logger.error("Requester > getRequestData(HttpURLConnection httpURLConnection) IOException - inputStreamReader error");
			logger.error(e.getMessage());
			throw new Exception("Requester > getRequestData(HttpURLConnection httpURLConnection) IOException - inputStreamReader error");
		}

		switch(responseCode) {
			case HttpURLConnection.HTTP_OK:
				BufferedReader buffer = new BufferedReader(inputStreamReader);
				StringBuffer response = new StringBuffer();
				String strTemp = "";
				while (null != (strTemp = buffer.readLine())) {
					response.append(strTemp);
				}
				buffer.close();
				return response.toString();
			default:
				logger.error("Requester > getRequestData(HttpURLConnection httpURLConnection) HttpURLConnection is not HTTP_OK. responseCode is " + responseCode);
				throw new Exception("Requester > getRequestData(HttpURLConnection httpURLConnection) HttpURLConnection is not HTTP_OK. responseCode is " + responseCode);
		}
	}
}
