/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Usuario;
import persistencia.ConexaoBanco;

/**
 * @author marcelozilio
 */
public class UsuarioDAO {

    public boolean inserir(Usuario u) throws SQLException {
        Connection con = ConexaoBanco.getConexao();
        PreparedStatement pstm = null;

        String sql = "insert into usuarios (idUsuario, login, senha) values (?,?,?)";

        try {
            pstm = con.prepareStatement(sql);

            pstm.setLong(1, u.getIdUsuario());
            pstm.setString(2, u.getLogin());
            pstm.setString(3, u.getSenha());

            pstm.execute();
            return true;
        } catch (SQLException e) {
            throw new SQLException("Erro ao inserir: " + e.getMessage());
        } finally {
            pstm.close();
            con.close();
        }
    }

    public List<Usuario> listar() throws SQLException {
        Connection con = ConexaoBanco.getConexao();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            String sql = "select * from usuarios";
            pstm = con.prepareStatement(sql);

            rs = pstm.executeQuery(sql);

            List<Usuario> usuarios = new ArrayList<>();

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getLong(1));
                u.setLogin(rs.getString(2));
                u.setSenha(rs.getString(3));
                usuarios.add(u);
            }
            return usuarios;
        } catch (SQLException e) {
            throw new SQLException("Erro ao listar: " + e.getMessage());
        } finally {
            rs.close();
            pstm.close();
            con.close();
        }
    }

    public boolean alterar(Usuario u) throws SQLException {
        Connection con = ConexaoBanco.getConexao();
        PreparedStatement pstm = null;

        try {
            String sql = "update usuarios set "
                    + "login='" + u.getLogin() + "', "
                    + "senha='" + u.getSenha() + "' "
                    + "where idUsuario=" + u.getIdUsuario();
            pstm = con.prepareStatement(sql);
            pstm.execute();
            return true;
        } catch (SQLException e) {
            throw new SQLException("Erro ao alterar: " + e.getMessage());
        } finally {
            pstm.close();
            con.close();
        }
    }

    public boolean deletar(long idUsuario) throws SQLException {
        Connection con = ConexaoBanco.getConexao();
        PreparedStatement pstm = null;

        try {
            String sql = "delete from usuarios where idUsuario=" + idUsuario;
            pstm = con.prepareStatement(sql);
            pstm.execute();
            return true;
        } catch (Exception e) {

        } finally {
            pstm.close();
            con.close();
        }
        return false;
    }

    public boolean autentica(Usuario usr) throws SQLException {
        try {
            List<Usuario> usuarios = listar();
            return usuarios.stream().anyMatch((u) -> (usr.getLogin().equals(u.getLogin()) && usr.getSenha().equals(u.getSenha())));
        } catch (Exception e) {
            throw new SQLException("Erro ao autenticar: " + e.getMessage());
        }
    }

}
