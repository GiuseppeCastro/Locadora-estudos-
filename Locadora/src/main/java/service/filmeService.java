package service;

import java.util.List;

import exception.locadoraException;
import model.entity.filme;
import model.repository.locadora.filmeRepository;

public class filmeService {

	private filmeRepository filmeRepository = new filmeRepository();
	
    private void validarCamposObrigatoriosFilme(model.entity.filme filme) throws locadoraException {
        String mensagemValidacao = "";
        if (filme.getNome() == null || filme.getNome().isEmpty()) {
            mensagemValidacao += " - informe o nome \n";
        }
        if (filme.getTipo() == null || filme.getTipo().isEmpty()) {
            mensagemValidacao += " - informe o tipo do filme \n";
        }
        if (filme.getDuracao() == null){
            mensagemValidacao += " - informe a duracao do filme \n";
        }
        if (!mensagemValidacao.isEmpty()) {
            throw new locadoraException("Preencha o(s) seguinte(s) campo(s) \n " + mensagemValidacao);
        }
    }
    
    public filme salvarFilme(filme novoFilme) throws locadoraException {
        validarCamposObrigatoriosFilme(novoFilme);
        return filmeRepository.salvar(novoFilme);
    }

    public boolean atualizarFilme(filme filmeEditado) throws locadoraException {
        validarCamposObrigatoriosFilme(filmeEditado);
        return filmeRepository.alterar(filmeEditado);
    }

    public boolean excluirFilme(int id) throws locadoraException {
        return filmeRepository.excluir(id);
    }

    public filme consultarFilmePorId(int id) {
        return filmeRepository.consultarPorId(id);
    }

    public List<filme> consultarTodosFilmes() {
        return filmeRepository.consultarTodos();
    }
	
}
