package com.estepona.shorturl.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class QueryParser {
  public static Map<String, String> parse(String query) {
    if (query == null) {
      return null;
    }

    try {
      String queryDecoded = URLDecoder.decode(query, "UTF-8");

      Map<String, String> result = new HashMap<>();
      for (String param : queryDecoded.split("&")) {
        String[] entry = param.split("=");
        if (entry.length > 1) {
          result.put(entry[0], entry[1]);
        } else {
          result.put(entry[0], "");
        }
      }

      return result;
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace(System.out);
      return null;
    }
  }
}
