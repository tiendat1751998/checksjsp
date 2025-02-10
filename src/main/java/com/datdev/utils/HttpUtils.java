package com.datdev.utils;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpUtils {
    private String value;

    public HttpUtils() {

    }

    public HttpUtils(String value) {
        this.value = value;
    }

    public <T> T toModel(Class<T> tClass) {
        try {
            return new ObjectMapper().readValue(value, tClass);
        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }

    public static HttpUtils Of(BufferedReader bufferedReader) {
        StringBuilder sb = new StringBuilder();
        try {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
        return new HttpUtils(sb.toString());
    }
}
