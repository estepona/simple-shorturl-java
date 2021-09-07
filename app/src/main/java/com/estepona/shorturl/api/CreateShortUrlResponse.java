package com.estepona.shorturl.api;

public class CreateShortUrlResponse {
  private final String shortUrl;

  public CreateShortUrlResponse(String shortUrl) {
    this.shortUrl = shortUrl;
  }

  public String getShortUrl() {
    return shortUrl;
  }
}
