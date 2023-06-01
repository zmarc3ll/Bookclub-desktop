package com.example.bookclubdesktop;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DBHelper {
  public static String DB_DRIVER = "mysql";
  public static String DB_HOST = "localhost";
  public static String DB_PORT = "3306";
  public static String DB_NAME = "vizsga-konyvklub";
  public static String DB_USER = "root";
  public static String DB_PASS = "";

  Connection conn;

  public DBHelper() throws SQLException {
    String url = String.format("jdbc:%s://%s:%s/%s", DB_DRIVER, DB_HOST, DB_PORT, DB_NAME);
    conn = DriverManager.getConnection(url, DB_USER, DB_PASS);
  }

  public List<Member> readMembers() throws SQLException {
    List<Member> members = new ArrayList<>();
    String sql = "SELECT * FROM members";
    Statement statement = conn.createStatement();
    ResultSet result = statement.executeQuery(sql);
    while (result.next()) {
      int id = result.getInt("id");
      String name = result.getString("name");
      String gender = result.getString("gender");
      LocalDate birth_date = result.getDate("birth_date").toLocalDate();
      boolean banned = result.getBoolean("banned");
      Member member = new Member(id,name,gender,birth_date,banned);
      members.add(member);
    }
    return members;
  }
}
