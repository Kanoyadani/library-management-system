package com.econeigigobhoood.sgb.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface Tables {
    
    // Métodos para conexão, consulta, atualização e desconexão

    Connection conectar() throws SQLException;

    boolean hayConection();

    ResultSet executarSQL(String consultaSQL) throws SQLException;

    boolean executarAtualizacaoSQL(String comandoSQL) throws SQLException;

    void desconectar() throws SQLException;

}
