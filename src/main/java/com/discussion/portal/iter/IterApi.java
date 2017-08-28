package com.discussion.portal.iter;


import org.springframework.stereotype.Component;

/*
 *
 * https://github.com/abhijitparida/bunk/blob/develop/app/src/main/java/app/abhijit/iter/data
 */



import com.google.gson.JsonObject;

@Component
public class IterApi {

    private static final String API_ENDPOINT = "http://111.93.164.202:8282/CampusPortalSOA/";

    private static final String INSTITUTE_ID = "SOAUINSD1312A0000002";
    private static final String REGISTRATION_ID = "ITERRETD1612A0000002";

    public String fetchStudentId(String registrationNumber, String password) throws Exception {
        JsonObject request = new JsonObject();
        //request.addProperty("sid", "validate");
        //request.addProperty("instituteID", INSTITUTE_ID);
        request.addProperty("username", registrationNumber);
        request.addProperty("password", password);
        request.addProperty("MemberType", "S");
        return ConnectToUrl.post(API_ENDPOINT, "jdata=" + request.toString());
    }

    public String fetchStudentDetails(String studentId) throws Exception {
        JsonObject request = new JsonObject();
        request.addProperty("sid", "studentdetails");
        request.addProperty("instituteid", INSTITUTE_ID);
        request.addProperty("studentid", studentId);
        return ConnectToUrl.post(API_ENDPOINT, "jdata=" + request.toString());
    }

    public String fetchStudentSubjects(String studentId) throws Exception {
        JsonObject request = new JsonObject();
        request.addProperty("sid", "attendance");
        request.addProperty("instituteid", INSTITUTE_ID);
        request.addProperty("registrationid", REGISTRATION_ID);
        request.addProperty("studentid", studentId);
        return ConnectToUrl.post(API_ENDPOINT, "jdata=" + request.toString());
    }
}