package service;

import model.entity.locacao;
import model.repository.locadora.locacaoRepository;
import java.util.List;

public class locacaoService {
    private locacaoRepository locacaoRepository;

    public locacaoService(locacaoRepository locacaoRepository) {
        this.locacaoRepository = locacaoRepository;
    }

    // Método para alugar um filme
    public locacao alugarFilme(locacao novaLocacao) {
        // Verifica se o cliente já possui 2 locações em aberto
        List<locacao> locacoesEmAberto = locacaoRepository.listLocacoesEmAbertoPorCliente(novaLocacao.getCliente());
        if (locacoesEmAberto.size() >= 2) {
            throw new IllegalStateException("O cliente já possui 2 filmes alugados.");
        }

        return locacaoRepository.salvar(novaLocacao);
    }

    // Método para finalizar uma locação
    public boolean finalizarLocacao(int idLocacao) {
        return locacaoRepository.excluir(idLocacao);
    }

    // Método para buscar uma locação pelo ID
    public locacao getLocacao(int idLocacao) {
        return locacaoRepository.consultarPorId(idLocacao);
    }

    // Método para listar todas as locações
    public List<locacao> listarTodasLocacoes() {
        return locacaoRepository.consultarTodos();
    }
}
