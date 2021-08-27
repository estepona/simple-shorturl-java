package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class MyHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {
        InputStream is = t.getRequestBody();
        System.out.println(is.toString());

        String resp = "This is the response";
        t.sendResponseHeaders(200, resp.length());

        OutputStream os = t.getResponseBody();
        os.write(resp.getBytes());
        os.close();
    }
}
