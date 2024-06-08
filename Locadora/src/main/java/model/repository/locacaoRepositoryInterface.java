package model.repository;

import model.entity.locacao;
import java.util.List;

public interface locacaoRepositoryInterface extends BaseRepository<locacao> {
    List<locacao> listLocacoesEmAbertoPorCliente(String idCliente);
}