package com.estepona.shorturl.service;

import com.estepona.shorturl.api.ShortUrlEntity;
import com.estepona.shorturl.dao.ShortUrlTable;
import com.estepona.shorturl.secret.MD5Hashing;
import com.estepona.shorturl.secret.SixtyTwoEncoder;

public class UrlTransformerService {
  private ShortUrlTable shortUrlTable = new ShortUrlTable();

  public ShortUrlEntity transform(String url) {
    long id = shortUrlTable.getLastId() + 1;

    String md5 = MD5Hashing.hash(url);
    String code = SixtyTwoEncoder.encode(id);
    ShortUrlEntity res = new ShortUrlEntity(null, md5, url, code); // TODO

    shortUrlTable.insert(res);
    
    System.out.println(res.toString());

    return res;
  }
}
