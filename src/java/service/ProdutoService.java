package service;

import dao.ProdutoDAO;
import modelo.Produto;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutoService {

    private final ProdutoDAO produtoDAO;

    public ProdutoService() {
        this.produtoDAO = new ProdutoDAO();
    }

    public boolean inserir(Produto produto) throws SQLException {
        try {
            return produtoDAO.inserir(produto);
        } catch (SQLException e) {
            throw new SQLException("Erro ao inserir " + e.getMessage());
        }
    }

    public ArrayList<Produto> listar() throws SQLException {
        try {
            return produtoDAO.listar();
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar " + e.getMessage());
        }
    }

    public boolean deletar(long idProduto) throws SQLException {
        try {
            return produtoDAO.deletar(idProduto);
        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar " + e.getMessage());
        }
    }

    public boolean alterar(Produto produto) throws SQLException {
        try {
            return produtoDAO.alterar(produto);
        }catch (SQLException e){
            throw new SQLException("Erro ao alterar " + e.getMessage());
        }
    }
}
