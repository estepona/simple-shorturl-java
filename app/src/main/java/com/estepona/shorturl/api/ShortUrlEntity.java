package com.estepona.shorturl.api;

public final class ShortUrlEntity {
  private final String url;
  private final String md5;
  private final String code;

  public ShortUrlEntity(String url, String md5, String code) {
    this.url = url;
    this.md5 = md5;
    this.code = code;
  }

  public String getUrl() {
    return url;
  }

  public String getMd5() {
    return md5;
  }

  public String getCode() {
    return code;
  }

  @Override
  public String toString() {
    return "ShortUrlEntity[" + "url=" + url + ", md5=" + md5 + ", code=" + code + "]";
  }
}
