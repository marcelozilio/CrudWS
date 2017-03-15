/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author marcelozilio
 */
public class ConexaoBanco {

    private static final String URL = "jdbc:mysql://localhost/crud";
    private static final String USER = "root";
    private static final String SENHA = "";

    public static Connection getConexao() throws SQLException {
        Connection c = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection(URL, USER, SENHA);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return c;
    }
}
