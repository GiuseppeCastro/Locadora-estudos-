package service;

import java.util.List;

import exception.locadoraException;
import model.entity.cliente;
import model.repository.locadora.clienteRepository;

public class clienteService {

    private clienteRepository clienteRepository = new clienteRepository();
    
    private void validarCamposObrigatoriosCliente(cliente cliente) throws locadoraException {
        String mensagemValidacao = "";
        if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
            mensagemValidacao += " - informe o nome \n";
        }
        if (cliente.getCpf() == null || cliente.getCpf().isEmpty() || cliente.getCpf().length() != 11) {
            mensagemValidacao += " - informe o CPF \n";
        }
        if (cliente.getTelefone() == null || cliente.getTelefone().isEmpty()) {
            mensagemValidacao += " - informe o telefone \n";
        }
        if (!mensagemValidacao.isEmpty()) {
            throw new locadoraException("Preencha o(s) seguinte(s) campo(s) \n " + mensagemValidacao);
        }
    }

    public cliente salvarCliente(cliente novoCliente) throws locadoraException {
        validarCamposObrigatoriosCliente(novoCliente);
        return clienteRepository.salvar(novoCliente);
    }

    public boolean atualizarCliente(cliente clienteEditado) throws locadoraException {
        validarCamposObrigatoriosCliente(clienteEditado);
        return clienteRepository.alterar(clienteEditado);
    }

    public boolean excluirCliente(int id) throws locadoraException {
        return clienteRepository.excluir(id);
    }

    public cliente consultarClientePorId(int id) {
        return clienteRepository.consultarPorId(id);
    }

    public List<cliente> consultarTodosClientes() {
        return clienteRepository.consultarTodos();
    }

}

