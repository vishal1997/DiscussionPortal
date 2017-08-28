package com.discussion.portal.iter;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.URL;
import java.util.List;
import java.net.HttpURLConnection;
import org.springframework.stereotype.Component;
import com.google.gson.JsonObject;

@Component
public class HttpUrlConnectionExample {

  private List<String> cookies;
  private HttpURLConnection conn;

  private final String USER_AGENT = "Mozilla/5.0";

  public void fetchStudentId(String username, String password) throws Exception {

	String url = "http://111.93.164.202:8282/CampusPortalSOA";
	String iter = "http://111.93.164.202:8282/CampusPortalSOA/studentinfo";

	HttpUrlConnectionExample http = new HttpUrlConnectionExample();

	// make sure cookies is turn on
	CookieHandler.setDefault(new CookieManager());

	String postParams = http.getFormParams(username, password);

	// 2. Construct above post's content and then send a POST request for
	// authentication
	http.sendPost(url, postParams);

	// 3. success then go to iter.
	http.GetPageContent(iter);
	//System.out.println(result);
  }

  private void sendPost(String url, String postParams) throws Exception {

	URL obj = new URL(url);
	conn = (HttpURLConnection) obj.openConnection();

	// Acts like a browser

	conn.setUseCaches(false);
	conn.setRequestMethod("POST");
	conn.setRequestProperty("Host", "http://111.93.164.202:8282");
	conn.setRequestProperty("User-Agent", USER_AGENT);
	conn.setRequestProperty("Accept",
		"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
	conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
	for (String cookie : this.cookies) {
		conn.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
	}
	conn.setRequestProperty("Connection", "keep-alive");
	conn.setRequestProperty("Referer", "http://111.93.164.202:8282/CampusPortalSOA/index");
	conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	conn.setRequestProperty("Content-Length", Integer.toString(postParams.length()));

	conn.setDoOutput(true);
	conn.setDoInput(true);

	// Send post request
	DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
	wr.writeBytes(postParams);
	wr.flush();
	wr.close();

	int responseCode = conn.getResponseCode();
	System.out.println("\nSending 'POST' request to URL : " + url);
	System.out.println("Post parameters : " + postParams);
	System.out.println("Response Code : " + responseCode);

	BufferedReader in =
             new BufferedReader(new InputStreamReader(conn.getInputStream()));
	String inputLine;
	StringBuffer response = new StringBuffer();

	while ((inputLine = in.readLine()) != null) {
		response.append(inputLine);
	}
	in.close();
	//System.out.println(response.toString());

  }

  public String getFormParams(String username, String password)
		throws UnsupportedEncodingException {

	System.out.println("Extracting form's data...");

	JsonObject request = new JsonObject();
    request.addProperty("username", username);
    request.addProperty("password", password);
    request.addProperty("MemberType", "S");
	

	return request.toString();
  }

  public List<String> getCookies() {
	return cookies;
  }

  public void setCookies(List<String> cookies) {
	this.cookies = cookies;
  }
  

  private String GetPageContent(String url) throws Exception {

	URL obj = new URL(url);
	conn = (HttpURLConnection) obj.openConnection();

	// default is GET
	conn.setRequestMethod("GET");

	conn.setUseCaches(false);

	// act like a browser
	conn.setRequestProperty("User-Agent", USER_AGENT);
	conn.setRequestProperty("Accept",
		"application/json ,plain/text,");
	conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
	if (cookies != null) {
		for (String cookie : this.cookies) {
			conn.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
		}
	}
	
	
	int responseCode = conn.getResponseCode();
	System.out.println("\nSending 'GET' request to URL : " + url);
	System.out.println("Response Code : " + responseCode);

	BufferedReader in =
            new BufferedReader(new InputStreamReader(conn.getInputStream()));
	String inputLine;
	StringBuffer response = new StringBuffer();

	while ((inputLine = in.readLine()) != null) {
		response.append(inputLine);
	}
	in.close();

	// Get the response cookies
	setCookies(conn.getHeaderFields().get("Set-Cookie"));
	System.out.println(response.toString());
	return response.toString();

  }

}
