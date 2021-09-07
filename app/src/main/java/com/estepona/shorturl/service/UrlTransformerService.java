package com.estepona.shorturl.service;

import com.estepona.shorturl.api.ShortUrlEntity;
import com.estepona.shorturl.dao.ShortUrlTable;
import com.estepona.shorturl.secret.MD5Hashing;
import io.seruco.encoding.base62.Base62;

public class UrlTransformerService {
  private final ShortUrlTable shortUrlTable = new ShortUrlTable();
  private final Base62 base62 = Base62.createInstance();

  public ShortUrlEntity transform(String url) {
    String md5 = MD5Hashing.hash(url);
    byte[] md5Encoded = base62.encode(md5.getBytes());
    String md5EncodedSubstring = new String(md5Encoded).substring(0, 6);
    ShortUrlEntity res = new ShortUrlEntity(null, md5, url, md5EncodedSubstring); // TODO

    shortUrlTable.insert(res);

    System.out.println(res);

    return res;
  }
}
