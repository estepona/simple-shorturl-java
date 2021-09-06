package com.estepona.shorturl.api;

public final class ShortUrlEntity {
  private Long id;
  private String md5;
  private String url;
  private String code;

  public ShortUrlEntity(Long id, String md5, String url, String code) {
    this.id = id;
    this.md5 = md5;
    this.url = url;
    this.code = code;
  }

  public Long getId() {
    return id;
  }

  public String getMd5() {
    return md5;
  }

  public String getUrl() {
    return url;
  }

  public String getCode() {
    return code;
  }

  @Override
  public String toString() {
    return "ShortUrlEntity[" + "id=" + id + ",md5=" + md5 + ",url=" + url + ",code=" + code + "]";
  }
}
