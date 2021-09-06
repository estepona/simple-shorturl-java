package com.estepona.shorturl.util;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class QueryParser {
  public static Map<String, String> parse(String query) {
    if (query == null) {
      return null;
    }

    String queryDecoded = URLDecoder.decode(query, StandardCharsets.UTF_8);

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
  }
}
