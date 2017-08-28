package com.discussion.portal.iter;


/*
 *
 * https://github.com/abhijitparida/bunk/blob/develop/app/src/main/java/app/abhijit/iter/data
 */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Component;

@Component
public class ConnectToUrl{

    private static final int TIMEOUT = 5000;
    private static final String USER_AGENT = System.getProperty("http.agent");

    public static String post(String url, String data) throws Exception {
        URL endPoint = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) endPoint.openConnection();
        connection.setRequestMethod("POST");
        connection.setConnectTimeout(TIMEOUT);
        connection.setReadTimeout(TIMEOUT);
        connection.setRequestProperty("User-Agent", USER_AGENT == null ? "" : USER_AGENT);
        connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
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
        System.out.println(response.toString());
        return response.toString();
    }

    public static String get(String url) throws Exception {
        URL endPoint = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) endPoint.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(TIMEOUT);
        connection.setReadTimeout(TIMEOUT);
        connection.setRequestProperty("User-Agent", USER_AGENT == null ? "" : USER_AGENT);
        connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        BufferedReader inStream = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inStreamLine;
        StringBuilder response = new StringBuilder();
        while ((inStreamLine = inStream.readLine()) != null) {
            response.append(inStreamLine);
        }
        inStream.close();
        System.out.println(response.toString());
        return response.toString();
    }
}
