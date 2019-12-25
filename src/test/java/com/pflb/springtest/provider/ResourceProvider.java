package com.pflb.springtest.provider;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class ResourceProvider {
    public static String getAsString(String resource) {
        InputStreamReader isr = new InputStreamReader(ResourceProvider.class.getResourceAsStream(resource));
        BufferedReader br = new BufferedReader(isr);
        return br.lines().collect(Collectors.joining());
    }
}
