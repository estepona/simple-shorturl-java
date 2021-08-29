package com.estepona.shorturl.service;

import com.estepona.shorturl.api.ShortUrlEntity;
import com.estepona.shorturl.secret.MD5Hashing;
import com.estepona.shorturl.secret.SixtyTwoEncoder;

public class UrlTransformerService {
  // TODO: remove after having a database
  private static Long idCounter = 0L;

  public static ShortUrlEntity transform(String url) {
    String md5 = MD5Hashing.hash(url);
    String code = SixtyTwoEncoder.encode(idCounter);
    ShortUrlEntity res = new ShortUrlEntity(idCounter++, md5, url, code);
    
    System.out.println(res.toString());

    return res;
  }
}
