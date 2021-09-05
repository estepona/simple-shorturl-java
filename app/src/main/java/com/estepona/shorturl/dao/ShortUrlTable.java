package com.estepona.shorturl.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.estepona.shorturl.api.ShortUrlEntity;

public final class ShortUrlTable {
  public static String name = "short_url";
  private String path = "short_url.db";
  private Connection connection = null;

  public ShortUrlTable() {
    try {
      connection = DriverManager.getConnection("jdbc:sqlite:" + path);
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
    var statementStringBuilder = new StringBuilder(1024);
    statementStringBuilder
      .append("CREATE TABLE " + name + "(")
      .append("id INTEGER PRIMARY KEY ASC,")
      .append("url STRING,")
      .append("md5 STRING,")
      .append("code STRING)");
    String statementString = statementStringBuilder.toString();

    try {
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);

      statement.executeUpdate(statementString);
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }

  public void drop() {
    String statementString = "DROP TABLE IF EXISTS " + name;

    try {
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);

      statement.executeUpdate(statementString);
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }

  public void insert(ShortUrlEntity entity) {
    var entityStringBuilder = new StringBuilder(1024);
    entityStringBuilder
      .append("null")
      .append(",")
      .append("'" + entity.getUrl() + "'")
      .append(",")
      .append("'" + entity.getMd5() + "'")
      .append(",")
      .append("'" + entity.getCode() + "'");
    String entityString = entityStringBuilder.toString();
    
    var insertStatementStringBuilder = new StringBuilder(1024);
    insertStatementStringBuilder
      .append("INSERT INTO " + name + " VALUES")
      .append("(" + entityString + ")");
    String insertStatementString = insertStatementStringBuilder.toString();

    try {
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);

      statement.executeUpdate(insertStatementString);
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }

  public void run() {
    drop();
    create();

    insert(new ShortUrlEntity(null, "123", "https://www.google.com", "sfvsafds"));
    insert(new ShortUrlEntity(null, "234", "https://www.amazon.com", "vwsafsas"));

    try {
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30); // set timeout to 30 sec.

      ResultSet rs = statement.executeQuery("select * from short_url");
      while (rs.next()) {
        // read the result set
        System.out.println("id = " + rs.getInt("id"));
        System.out.println("url = " + rs.getString("url"));
        System.out.println("md5 = " + rs.getString("md5"));
        System.out.println("code = " + rs.getString("code"));
      }
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }
}
