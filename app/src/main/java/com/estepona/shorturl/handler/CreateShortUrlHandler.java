package com.estepona.shorturl.handler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import com.estepona.shorturl.service.UrlTransformerService;
import com.estepona.shorturl.util.QueryParser;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class CreateShortUrlHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {
        Map<String, String> params = QueryParser.parse(t.getRequestURI().getRawQuery());
        System.out.println("req query: " + params.toString());
        String url = params.get("url");
        if (url == null) {
            System.out.println("url not found...");
            return;
        }

        InputStream requestBodyInputStream = t.getRequestBody();
        ByteArrayOutputStream requestBodyTargetStream = new ByteArrayOutputStream();
        requestBodyInputStream.transferTo(requestBodyTargetStream);
        System.out.println("req body: " + requestBodyTargetStream.toString());

        String resp = UrlTransformerService.transform(url) + "\n";
        t.sendResponseHeaders(200, resp.length());

        OutputStream os = t.getResponseBody();
        os.write(resp.getBytes());
        os.close();
    }
}
