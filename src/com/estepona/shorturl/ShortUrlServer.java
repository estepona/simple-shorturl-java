package com.estepona.shorturl;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public class ShortUrlServer {
  public static void main(String[] args) {
    try {
      HttpServer httpServer = HttpServer.create(new InetSocketAddress("localhost", 8000), 0);
      httpServer.createContext("/create", new CreateShortUrlHandler());
      httpServer.setExecutor(null);
      httpServer.start();
    } catch (IOException e) {
      e.printStackTrace(System.out);
    }
  }
}