package com.estepona.shorturl.secret;

public class SixtyTwoEncoder {
  public static String encode(Long n) {
    String[] chars = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g",
        "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C",
        "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y",
        "Z" };
    String res = "";

    while (n != 0L) {
      Long div = n / 62L;
      Long rem = n % 62L;

      n = div;
      res += chars[rem.intValue()];
    }

    while (res.length() != 6) {
      res += chars[0];
    }

    return new StringBuilder(res).reverse().toString();
  }
}
