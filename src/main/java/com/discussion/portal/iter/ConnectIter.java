package com.discussion.portal.iter;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URL;
import java.util.Formatter;
import java.net.HttpURLConnection;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import com.discussion.portal.common.Constants.StatusCode;

/**
 * Credit : Abhijit Parida
 * 
 * @author Vishal
 *
 */

@Component
public class ConnectIter {

    private static final int TIMEOUT = 5000;
    private static final String USER_AGENT = "Mozilla/5.0";

    public String connect(String username, String password) {
    	
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));

        String iterLogin = "http://111.93.164.203/CampusPortalSOA/login";
        String studentInfo = "http://111.93.164.203/CampusPortalSOA/studentinfo";
        
        @SuppressWarnings("resource")
		String data = new Formatter()
        				.format("{\"username\":\"%s\",\"password\":\"%s\"}", username, password)
        				.toString();

        String loginResponse;
		try {
			
			loginResponse = post(iterLogin, data);
			if(loginResponse.contains("error")) {
				return StatusCode.INVALID;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			
			loginResponse = post(studentInfo, "");
	        JSONObject jsonObject = new JSONObject(loginResponse);
	        return jsonObject.getString("detail").replace("[", "").replace("]", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return StatusCode.ERROR;
    }

    public String post(String url, String data) throws Exception {
    	
        URL endPoint = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) endPoint.openConnection();
        
        connection.setRequestMethod("POST");
        connection.setConnectTimeout(TIMEOUT);
        connection.setReadTimeout(TIMEOUT);
        connection.setRequestProperty("User-Agent", USER_AGENT == null ? "" : USER_AGENT);
        connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);
        
        DataOutputStream outStream = new DataOutputStream(connection.getOutputStream());
        outStream.writeBytes(data);
        outStream.flush();
        outStream.close();
        
        BufferedReader inStream = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inStreamLine;
        StringBuilder response = new StringBuilder();
        while ((inStreamLine = inStream.readLine()) != null) {
            response.append(inStreamLine);
        }
        inStream.close();
        return response.toString();
    }
}