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
import model.entity.filme;
import service.filmeService;

@Path("/filmes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class filmeController {

    private final filmeService service = new filmeService();

    @GET
    @Path("/todos")
    public List<filme> listarFilmes() {
        return service.consultarTodosFilmes();
    }

    @GET
    @Path("/{id}")
    public filme buscarFilmePorId(@PathParam("id") int id) {
        return service.consultarFilmePorId(id);
    }

    @POST
    public filme cadastrarFilme(filme filme) throws locadoraException {
        return service.salvarFilme(filme);
    }

    @PUT
    public boolean atualizarFilme(filme filmeEditado) throws locadoraException {
        return service.atualizarFilme(filmeEditado);
    }

    @DELETE
    @Path("/{id}")
    public boolean deletarFilme(@PathParam("id") int id) throws locadoraException {
        return service.excluirFilme(id);
    }
}
