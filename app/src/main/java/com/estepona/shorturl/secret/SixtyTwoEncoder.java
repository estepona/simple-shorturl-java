package com.estepona.shorturl.secret;

public class SixtyTwoEncoder {
  public static String encode(Long n) {
    String[] chars = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g",
      "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C",
      "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y",
      "Z"};
    StringBuilder res = new StringBuilder();

    while (n != 0L) {
      long div = n / 62L;
      long rem = n % 62L;

      n = div;
      res.append(chars[(int) rem]);
    }

    while (res.length() != 6) {
      res.append(chars[0]);
    }

    return new StringBuilder(res.toString()).reverse().toString();
  }
}
