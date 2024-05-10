package com.econeigigobhoood.sgb.controller;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.econeigigobhoood.sgb.model.Livro;
import com.econeigigobhoood.sgb.model.Tables;

public  class Controller implements Tables {
    private Connection conexion;
       
    // ====================================
    // ========= DATABASE CONFIG ==========
    // ====================================
    @Override
    public boolean hayConection() {
        return (conexion != null);
    }

    @Override
    public Connection conectar() throws SQLException {
        try {
            Class.forName("org.h2.Driver");
            // Estabelece a conexão com o banco de dados H2 em memória
            conexion = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return conexion;
    }

    @Override
    public ResultSet executarSQL(String consultaSQL) throws SQLException {
        Statement sql = conexion.createStatement();
        return sql.executeQuery(consultaSQL);
    }

    @Override
    public boolean executarAtualizacaoSQL(String comandoSQL) throws SQLException {
        PreparedStatement sql = conexion.prepareStatement(comandoSQL);
        System.out.println(sql);
        return sql.executeUpdate() != 0;
    }

    @Override
    public void desconectar() {
        try{
            if (conexion != null) {
            conexion.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // ============================================
    // ======= OPERAÇÕES NO BANCO DE DADOS ========
    // ============================================
    public void limpaBancoH2() {
        try (Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        Statement st = conn.createStatement()) {
            st.execute("DROP ALL OBJECTS DELETE FILES");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // ===============================================
    // ========= OPERAÇÕES NO ESTOQUE GERAL ==========
    // ===============================================
    //Conexão de MainMenu para Controller
    // Método de cadastro de livro no banco de dados
    public void insertarLivro(Livro entidade) {
        try {
            conectar();
            String consulta = "INSERT INTO Livros (Nome, Autor, Paginas, Status) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1, entidade.getNome());
            statement.setString(2, entidade.getAutor());
            statement.setInt(3, entidade.getPaginas());
            statement.setString(4, "Em estoque");

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectar(); 
        }
    }

    public void excluiLivro(int id) {
        String query = "DELETE FROM Livros WHERE IdLivro = ?";
        
        try {
            conectar();
            PreparedStatement stmt = conexion.prepareStatement(query);
            stmt.setInt(1, id);

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectar(); 
        }
    }

    public Livro buscaLivro(int id) {
        String query = "SELECT * FROM Livros WHERE IdLivro = ?";
        
        try {
            conectar();
            PreparedStatement stmt = conexion.prepareStatement(query);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("Nome");
                String autor = rs.getString("Autor");
                int paginas = rs.getInt("Paginas");
                String status = rs.getString("Status");

                Livro livro = new Livro(id, nome, autor, paginas, status);
                
                return livro;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectar(); 
        }
        return null;
    }

    public void atualizarLivro(Livro entidade) {
        String query = "UPDATE Livros SET Nome = ?, Autor = ?, Paginas = ? WHERE IdLivro = ?";

        try {
            conectar();
            PreparedStatement stmt = conexion.prepareStatement(query);
            stmt.setString(1, entidade.getNome());
            stmt.setString(2, entidade.getAutor());
            stmt.setInt(3, entidade.getPaginas());
            stmt.setInt(4, entidade.getIdlivro());

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectar();
        }
    }

    public List<Livro> listaLivros() {
        List<Livro> livros = new ArrayList<Livro>();
        String query = "SELECT * FROM Livros";

        try {
            conectar();
            PreparedStatement stmt = conexion.prepareStatement(query);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("IdLivro");
                String nome = rs.getString("Nome");
                String autor = rs.getString("Autor");
                int paginas = rs.getInt("Paginas");
                String status = rs.getString("Status");

                Livro livro = new Livro (id, nome, autor, paginas, status);

                livros.add (livro);
            }

            return livros;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectar();
        }
        return null;
    }

    // ============================================
    // ========= OPERAÇÕES EM EMPRESTADO ==========
    // ============================================
    public void emprestaLivro(int id) {
        String query = "UPDATE Livros SET Status = ? WHERE IdLivro = ?";
        
        try {
            conectar();
            PreparedStatement statement = conexion.prepareStatement(query);

            statement.setString(1, "Emprestado");
            statement.setInt(2, id);

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectar(); 
        }
    }

    public void devolveLivro(int id) {
        String query = "UPDATE Livros SET Status = ? WHERE IdLivro = ?";
        
        try {
            conectar();
            PreparedStatement statement = conexion.prepareStatement(query);

            statement.setString(1, "Em estoque");
            statement.setInt(2, id);

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectar(); 
        }
    }

    public List<Livro> listaLivrosEmprestado() {
        List<Livro> livros = new ArrayList<Livro>();
        String query = "SELECT * FROM Livros WHERE Status = ?";

        try {
            conectar();
            PreparedStatement stmt = conexion.prepareStatement(query);
            stmt.setString(1, "Emprestado");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("IdLivro");
                String nome = rs.getString("Nome");
                String autor = rs.getString("Autor");
                int paginas = rs.getInt("Paginas");
                String status = rs.getString("Status");

                Livro livro = new Livro (id, nome, autor, paginas, status);

                livros.add (livro);
            }
            return livros;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            desconectar();
        }
        return null;
    }
}
