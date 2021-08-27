package com.estepona.shorturl;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.estepona.shorturl.handlers.CreateShortUrlHandler;
import com.sun.net.httpserver.HttpServer;

public class ShortUrlServer {
  public static void main(String[] args) {
    try {
      HttpServer httpServer = HttpServer.create(new InetSocketAddress("localhost", 8000), 0);
      httpServer.setExecutor(null);

      httpServer.createContext("/create", new CreateShortUrlHandler());

      httpServer.start();
      System.out.println("server started");
    } catch (IOException e) {
      e.printStackTrace(System.out);
    }
  }
}