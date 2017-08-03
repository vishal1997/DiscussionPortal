package com.discussion.portal.utils;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * 
 * @author Vishal
 *
 */
public class Json {
	
	public static Gson json = new GsonBuilder().setPrettyPrinting().create();
	
	/**
	 * 
	 * @param jsonElement
	 * @return
	 */
	public static String toJson(Object jsonElement) {
		return json.toJson(jsonElement);
	}
	
	public static Object fromJson(String jsonEle, Type obj) {
		return json.fromJson(jsonEle, obj);
	}
	
}
