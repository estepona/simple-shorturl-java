package com.estepona.shorturl.handler;

import com.estepona.shorturl.api.CreateShortUrlResponse;
import com.estepona.shorturl.api.ShortUrlEntity;
import com.estepona.shorturl.dao.ShortUrlTable;
import com.estepona.shorturl.service.UrlTransformerService;
import com.estepona.shorturl.util.QueryParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

public class CreateShortUrlHandler implements HttpHandler {
  private final String baseUrl;
  private final ShortUrlTable shortUrlTable = new ShortUrlTable();
  private final UrlTransformerService urlTransformerService = new UrlTransformerService();
  private final ObjectMapper om = new ObjectMapper();

  public CreateShortUrlHandler(String baseUrl) {
    this.baseUrl = baseUrl;
  }

  @Override
  public void handle(HttpExchange t) throws IOException {
    // get query
    Map<String, String> params = QueryParser.parse(t.getRequestURI().getRawQuery());
    System.out.println("req query: " + params.toString());
    String url = params.get("url");
    if (url == null) {
      System.out.println("url not found...");
      return;
    }

    // get body
    InputStream requestBodyInputStream = t.getRequestBody();
    ByteArrayOutputStream requestBodyTargetStream = new ByteArrayOutputStream();
    requestBodyInputStream.transferTo(requestBodyTargetStream);
    System.out.println("req body: " + requestBodyTargetStream);

    String shortUrl;

    // return code if exists
    String code = shortUrlTable.getCode(url);
    if (code != null) {
      shortUrl = baseUrl + code;
    } else {
      // otherwise, insert new row
      ShortUrlEntity entity = urlTransformerService.transform(url);
      shortUrl = baseUrl + entity.getCode();
    }

    CreateShortUrlResponse resp = new CreateShortUrlResponse(shortUrl);
    String respSerialized = om.writeValueAsString(resp) + "\n";

    t.sendResponseHeaders(200, respSerialized.length());
    OutputStream os = t.getResponseBody();
    os.write(respSerialized.getBytes());
    os.close();
  }
}
