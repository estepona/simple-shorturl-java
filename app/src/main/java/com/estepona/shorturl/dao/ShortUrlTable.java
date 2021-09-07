package com.estepona.shorturl.dao;

import com.estepona.shorturl.api.ShortUrlEntity;

import java.sql.*;

public final class ShortUrlTable {
  public static String name = "short_url";
  public static String path = "short_url.db";
  private Connection connection = null;

  public ShortUrlTable() {
    try {
      connection = DriverManager.getConnection("jdbc:sqlite:" + path);
      create();
    } catch (SQLException e) {
      // if the error message is "out of memory",
      // it probably means no database file is found
      System.err.println(e.getMessage());
    }
  }

  public void close() {
    try {
      if (connection != null) {
        connection.close();
      }
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }

  public void create() {
    var queryBuilder = new StringBuilder(1024);
    queryBuilder
      .append("CREATE TABLE IF NOT EXISTS " + name + " (")
      .append("url STRING PRIMARY KEY,")
      .append("md5 VARCHAR(32),")
      .append("code VARCHAR(6))");
    String query = queryBuilder.toString();

    // index cannot be created because code might not be unique

    try {
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);

      statement.executeUpdate(query);
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }

  public void drop() {
    String query = "DROP TABLE IF EXISTS " + name;

    try {
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);

      statement.executeUpdate(query);
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }

  public String getCode(String url) {
    String query = "SELECT code FROM " + name + " WHERE url = '" + url + "'";

    try {
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);

      ResultSet rs = statement.executeQuery(query);
      rs.next();
      return rs.getString(1);
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
    return null;
  }

  public String getUrl(String code) {
    String query = "SELECT url FROM " + name + " WHERE code = '" + code + "'";

    try {
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);

      ResultSet rs = statement.executeQuery(query);
      rs.next();
      return rs.getString(1);
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
    return null;
  }

  public void insert(ShortUrlEntity entity) {
    var queryBuilder = new StringBuilder(1024);
    queryBuilder
      .append("'" + entity.getUrl() + "'")
      .append(",")
      .append("'" + entity.getMd5() + "'")
      .append(",")
      .append("'" + entity.getCode() + "'");
    String query = queryBuilder.toString();

    var insertStatementStringBuilder = new StringBuilder(1024);
    insertStatementStringBuilder
      .append("INSERT INTO " + name + " VALUES")
      .append("(" + query + ")");
    String insertStatementString = insertStatementStringBuilder.toString();

    try {
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);

      statement.executeUpdate(insertStatementString);
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }
}
