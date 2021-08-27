package com.estepona.shorturl.handlers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class CreateShortUrlHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {
        InputStream requestBodyInputStream = t.getRequestBody();
        ByteArrayOutputStream requestBodyTargetStream = new ByteArrayOutputStream();
        requestBodyInputStream.transferTo(requestBodyTargetStream);
        System.out.println("req body: " + requestBodyTargetStream.toString());

        String resp = "Create ShortUrl";
        t.sendResponseHeaders(200, resp.length());

        OutputStream os = t.getResponseBody();
        os.write(resp.getBytes());
        os.close();
    }
}
