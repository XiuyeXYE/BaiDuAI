/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.xy.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import java.lang.reflect.Type;

/**
 * Json工具类.
 */
public class GsonUtils {
//    private static Gson gson = new GsonBuilder().create();
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static String toJson(Object value) {
        return gson.toJson(value);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) throws JsonParseException {
        return gson.fromJson(json, classOfT);
    }

    public static <T> T fromJson(String json, Type typeOfT) throws JsonParseException {
        return (T) gson.fromJson(json, typeOfT);
    }
    
    public static String format(String str) {
    	
    	JsonParser jp = new JsonParser();
    	JsonElement je = jp.parse(str);
    	
    	return gson.toJson(je);
    	
    	
    }
    
    
}
