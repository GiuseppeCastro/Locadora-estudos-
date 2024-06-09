package controller;

import java.util.List;

import exception.locadoraException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.cliente;
import service.clienteService;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class clienteController {

    private final clienteService service = new clienteService();

    @GET
    @Path("/todos")
    public List<cliente> listarClientes() {
        return service.consultarTodosClientes();
    }

    @GET
    @Path("/{id}")
    public cliente buscarClientePorId(@PathParam("id") int id) {
        return service.consultarClientePorId(id);
    }

    @POST
    public cliente cadastrarCliente(cliente cliente) throws locadoraException {
        return service.salvarCliente(cliente);
    }

    @PUT
    public boolean atualizarCliente(cliente clienteEditado) throws locadoraException {
        return service.atualizarCliente(clienteEditado);
    }

    @DELETE
    @Path("/{id}")
    public boolean deletarCliente(@PathParam("id") int id) throws locadoraException {
        return service.excluirCliente(id);
    }
}