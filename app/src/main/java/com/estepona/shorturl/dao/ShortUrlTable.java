package com.estepona.shorturl.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class ShortUrlTable {
  private Connection connection = null;

  public void run() {
    try {
      // create a database connection
      connection = DriverManager.getConnection("jdbc:sqlite:short_url.db");
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30); // set timeout to 30 sec.

      statement.executeUpdate("drop table if exists short_url");
      statement.executeUpdate("create table short_url (id integer primary key asc, url string, md5 string, code string)");
      statement.executeUpdate("insert into short_url values(null, 'https://www.google.com', '123', 'sdfs')");
      statement.executeUpdate("insert into short_url values(null, 'https://www.amazon.com', '123', 'sdfsd')");
      ResultSet rs = statement.executeQuery("select * from short_url");
      while (rs.next()) {
        // read the result set
        System.out.println("id = " + rs.getInt("id"));
        System.out.println("url = " + rs.getString("url"));
        System.out.println("md5 = " + rs.getString("md5"));
        System.out.println("code = " + rs.getString("code"));
      }
    } catch (SQLException e) {
      // if the error message is "out of memory",
      // it probably means no database file is found
      System.err.println(e.getMessage());
    } finally {
      try {
        if (connection != null)
          connection.close();
      } catch (SQLException e) {
        // connection close failed.
        System.err.println(e.getMessage());
      }
    }
  }
}
