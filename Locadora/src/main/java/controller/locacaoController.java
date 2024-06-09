package controller;

import java.util.List;

import exception.locadoraException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.locacao;
import model.repository.locadora.locacaoRepository;
import service.locacaoService;

@Path("/locacoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class locacaoController {

	private final locacaoService service = new locacaoService(new locacaoRepository());

    @GET
    @Path("/todas")
    public List<locacao> listarLocacoes() {
        return service.listarTodasLocacoes();
    }

    @GET
    @Path("/{id}")
    public locacao buscarLocacaoPorId(@PathParam("id") int id) {
        return service.getLocacao(id);
    }

    @POST
    public locacao alugarFilme(locacao locacao) throws locadoraException {
        return service.alugarFilme(locacao);
    }

    @DELETE
    @Path("/{id}")
    public boolean finalizarLocacao(@PathParam("id") int id) throws locadoraException {
        return service.finalizarLocacao(id);
    }
}
