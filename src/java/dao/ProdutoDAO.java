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
import modelo.Produto;
import persistencia.ConexaoBanco;

/**
 * @author marcelozilio
 */
public class ProdutoDAO {

    public boolean inserir(Produto p) throws SQLException {
        Connection con = ConexaoBanco.getConexao();
        PreparedStatement pstm = null;

        String sql = "insert into produtos(idProduto, nome, valorCusto, quantidade) values(?,?,?,?)";

        try {
            pstm = con.prepareStatement(sql);

            pstm.setLong(1, p.getIdProduto());
            pstm.setString(2, p.getNome());
            pstm.setDouble(3, p.getValorCusto());
            pstm.setLong(4, p.getQuantidade());

            pstm.execute();
            
            return true;
        } catch (SQLException e) {
            throw new SQLException("Erro ao inserir " + e.getMessage());
        } finally {
            pstm.close();
            con.close();
        }        
    }

    public ArrayList<Produto> listar() throws SQLException {
        Connection con = ConexaoBanco.getConexao();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            String sql = "select * from produtos";
            pstm = con.prepareStatement(sql);

            rs = pstm.executeQuery(sql);

            ArrayList<Produto> produtos = new ArrayList<>();

            while (rs.next()) {
                Produto p = new Produto();

                p.setIdProduto(rs.getLong(1));
                p.setNome(rs.getString(2));
                p.setValorCusto(rs.getDouble(3));
                p.setQuantidade(rs.getLong(4));
                produtos.add(p);
            }
            return produtos;
        } catch (Exception e) {
            throw new SQLException("Erro ao buscar " + e.getMessage());
        } finally {
            rs.close();
            pstm.close();
            con.close();
        }
    }

    public boolean deletar(long idProduto) throws SQLException {
        Connection con = ConexaoBanco.getConexao();
        PreparedStatement pstm = null;
        try {
            String sql = "delete from produtos where idProduto=" + idProduto;
            pstm = con.prepareStatement(sql);
            pstm.execute();
            return true;
        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar " + e.getMessage());
        } finally {
            pstm.close();
            con.close();
        }        
    }

    public boolean alterar(Produto p) throws SQLException {
        Connection con = ConexaoBanco.getConexao();
        PreparedStatement pstm = null;
        
        try {
            String sql = "update produtos set "
                    + "nome='" + p.getNome() + "', "
                    + "valorCusto=" + p.getValorCusto() + ", "
                    + "quantidade=" + p.getQuantidade() + " where idProduto=" + p.getIdProduto();
            pstm = con.prepareStatement(sql);
            pstm.execute();
            return true;
        } catch (SQLException e) {
            throw new SQLException("Erro ao alterar " + e.getMessage());
        } finally {
            pstm.close();
            con.close();
        }        
    }
}