package com.discussion.portal;


import com.google.gson.JsonObject;

public class IterApi {

    private static final String API_ENDPOINT = "http://111.93.164.202:8282/CampusLynxSOA/CounsellingRequest?refor=StudentOnlineDetailService";

    private static final String INSTITUTE_ID = "SOAUINSD1312A0000002";
    private static final String REGISTRATION_ID = "ITERRETD1612A0000002";

    public static String fetchStudentId(String registrationNumber) throws Exception {
        JsonObject request = new JsonObject();
        request.addProperty("sid", "validate");
        request.addProperty("instituteID", INSTITUTE_ID);
        request.addProperty("studentrollno", registrationNumber);
        return ConnectToURL.post(API_ENDPOINT, "jdata=" + request.toString());
    }

    public static String fetchStudentDetails(String studentId) throws Exception {
        JsonObject request = new JsonObject();
        request.addProperty("sid", "studentdetails");
        request.addProperty("instituteid", INSTITUTE_ID);
        request.addProperty("studentid", studentId);
        return ConnectToURL.post(API_ENDPOINT, "jdata=" + request.toString());
    }

    public static String fetchStudentSubjects(String studentId) throws Exception {
        JsonObject request = new JsonObject();
        request.addProperty("sid", "attendance");
        request.addProperty("instituteid", INSTITUTE_ID);
        request.addProperty("registrationid", REGISTRATION_ID);
        request.addProperty("studentid", studentId);
        return ConnectToURL.post(API_ENDPOINT, "jdata=" + request.toString());
    }
}