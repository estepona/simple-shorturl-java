package com.estepona.shorturl.secret;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;

public class MD5Hashing {
	public static String hash(String url) {
		HashCode md5 = Hashing.md5().hashString(url, Charsets.UTF_8);

		return md5.toString();
	}
}
