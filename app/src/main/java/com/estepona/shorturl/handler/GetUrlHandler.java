package com.estepona.shorturl.handler;

import com.estepona.shorturl.dao.ShortUrlTable;
import com.estepona.shorturl.util.QueryParser;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class GetUrlHandler implements HttpHandler {
  private final ShortUrlTable shortUrlTable = new ShortUrlTable();

  @Override
  public void handle(HttpExchange t) throws IOException {
    // get uri
    String code = t.getRequestURI().toString().substring(1);
    System.out.println("req uri: " + code);

    // get query
    Map<String, String> params = QueryParser.parse(t.getRequestURI().getRawQuery());
    System.out.println("req query: " + params);

    // get body
    InputStream requestBodyInputStream = t.getRequestBody();
    ByteArrayOutputStream requestBodyTargetStream = new ByteArrayOutputStream();
    requestBodyInputStream.transferTo(requestBodyTargetStream);
    System.out.println("req body: " + requestBodyTargetStream);

    // return url if exists
    String url = shortUrlTable.getUrl(code);
    if (url == null) {
      t.sendResponseHeaders(404, 0);
      return;
    }

    // redirect
    t.getResponseHeaders().set("Location", url);
    t.sendResponseHeaders(302, 0);
  }
}
