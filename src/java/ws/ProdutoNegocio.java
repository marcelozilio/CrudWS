/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import dao.ProdutoDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import modelo.Produto;

/**
 * REST Web Service
 *
 * @author marcelozilio
 */
@Path("produto")
public class ProdutoNegocio {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CrudWS
     */
    public ProdutoNegocio() {
    }

    /**
     * Retrieves representation of an instance of ws.ProdutoNegocio
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        return "CRUDZAO WS RESTFULL";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/listar")
    public String listar() {
        Gson gson = new Gson();
        List<Produto> produtos = null;
        try {
            produtos = new ProdutoDAO().listar();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoNegocio.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
        return gson.toJson(produtos);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/inserir")
    public boolean inserir(String jsonProd) {
        try {
            Produto prod = (Produto) new Gson().fromJson(jsonProd, Produto.class);
            return new ProdutoDAO().inserir(prod);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/alterar")
    public boolean alterar(String jsonProd) {
        try {
            Produto prod = (Produto) new Gson().fromJson(jsonProd, Produto.class);
            return new ProdutoDAO().alterar(prod);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/deletar/{idProduto}")
    public boolean deletar(@PathParam("idProduto") long idProduto) {
        try {
            return new ProdutoDAO().deletar(idProduto);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

}
