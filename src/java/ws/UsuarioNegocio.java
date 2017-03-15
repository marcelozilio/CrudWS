/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import dao.UsuarioDAO;
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
import modelo.Usuario;

/**
 * REST Web Service
 * @author marcelozilio
 */
@Path("usuario")
public class UsuarioNegocio {

    @Context
    private UriInfo context;

    public UsuarioNegocio() {
    }

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
        List<Usuario> usuarios = null;
        try {
            usuarios = new UsuarioDAO().listar();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoNegocio.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
        return gson.toJson(usuarios);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/inserir")
    public boolean inserir(String jsonUsr) {
        try {
            Usuario usr = (Usuario) new Gson().fromJson(jsonUsr, Usuario.class);
            return new UsuarioDAO().inserir(usr);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    } 
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/alterar")
    public boolean alterar(String jsonUsr) {
        try {
            Usuario usr = (Usuario) new Gson().fromJson(jsonUsr, Usuario.class);
            return new UsuarioDAO().alterar(usr);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
    
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/deletar/{idUsuario}")
    public boolean deletar(@PathParam("idUsuario") long idUsuario){
        try {
            return new UsuarioDAO().deletar(idUsuario);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/login")
    public boolean autenticaLogin(String json){
        try {
            Usuario usr = (Usuario) new Gson().fromJson(json, Usuario.class);
            return new UsuarioDAO().autentica(usr);
        } catch (JsonSyntaxException | SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
    
}
