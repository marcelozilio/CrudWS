package dao;

import modelo.Produto;
import persistencia.ConexaoBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author marcelozilio
 */
public class ProdutoDAO {

    public boolean inserir(Produto p) throws SQLException {
        Connection con = ConexaoBanco.getConexao();

        PreparedStatement pstm = con.prepareStatement(
                "insert into produtos(idProduto, nome, valorCusto, quantidade) values(?,?,?,?)");

        pstm.setLong(1, p.getIdProduto());
        pstm.setString(2, p.getNome());
        pstm.setDouble(3, p.getValorCusto());
        pstm.setLong(4, p.getQuantidade());

        pstm.execute();
        pstm.close();
        con.close();

        return true;
    }

    public ArrayList<Produto> listar() throws SQLException {
        Connection con = ConexaoBanco.getConexao();

        String sql = "select * from produtos";

        PreparedStatement pstm = con.prepareStatement(sql);

        ResultSet rs = pstm.executeQuery(sql);

        ArrayList<Produto> produtos = new ArrayList<>();

        while (rs.next()) {
            Produto produto = new Produto();
            produto.setIdProduto(rs.getLong(1));
            produto.setNome(rs.getString(2));
            produto.setValorCusto(rs.getDouble(3));
            produto.setQuantidade(rs.getLong(4));
            produtos.add(produto);
        }

        rs.close();
        pstm.close();
        con.close();

        return produtos;
    }

    public boolean deletar(long idProduto) throws SQLException {
        Connection con = ConexaoBanco.getConexao();
        PreparedStatement pstm = con.prepareStatement("delete from produtos where idProduto = " + idProduto);

        pstm.execute();
        pstm.close();
        con.close();

        return true;
    }

    public boolean alterar(Produto p) throws SQLException {
        String sql = String.format("update produtos set nome='%s', valorCusto=%s, quantidade=%d where idProduto=%d",
                p.getNome(), p.getValorCusto(), p.getQuantidade(), p.getIdProduto());

        Connection con = ConexaoBanco.getConexao();
        PreparedStatement pstm = con.prepareStatement(sql);

        pstm.execute();
        pstm.close();
        con.close();

        return true;
    }
}