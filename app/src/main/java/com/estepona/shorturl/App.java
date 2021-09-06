package com.estepona.shorturl;

import com.estepona.shorturl.handler.CreateShortUrlHandler;
import com.estepona.shorturl.handler.GetShortUrlHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class App {
	public static String protocol = "http";
	public static String hostname = "localhost";
	public static int port = 8000;

	public static void main(String[] args) {
		try {
			HttpServer httpServer = HttpServer.create(new InetSocketAddress(hostname, port), 0);
			httpServer.setExecutor(null);

			httpServer.createContext("/create", new CreateShortUrlHandler(protocol + "://" + hostname + ":" + port + "/"));
			httpServer.createContext("/", new GetShortUrlHandler());

			httpServer.start();
			System.out.println("server started");
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
	}
}
