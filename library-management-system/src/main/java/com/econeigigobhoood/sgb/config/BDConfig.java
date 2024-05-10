package com.econeigigobhoood.sgb.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BDConfig {
  private static final String URL = "jdbc:h2:~/test";
  private static final String USER = "sa";
  private static final String PASSWORD = "";

  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(URL, USER, PASSWORD);
  }

  public static void criarTabelaLivros() {
    try (Connection conn = getConnection();
         Statement stmt = conn.createStatement()) {

          String sql = "CREATE TABLE IF NOT EXISTS Livros ("
          + "IdLivro SERIAL PRIMARY KEY,"
          + "Nome VARCHAR(255),"
          + "Autor VARCHAR(255),"
          + "Paginas INT,"
          + "Status VARCHAR(50)"
          + ");";

      stmt.execute(sql);

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
