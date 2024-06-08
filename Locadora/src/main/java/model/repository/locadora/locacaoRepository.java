package model.repository.locadora;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.entity.locacao;
import model.entity.cliente;
import model.entity.filme;
import model.repository.Banco;
import model.repository.BaseRepository;

public class locacaoRepository implements BaseRepository<locacao> {

    @Override
    public locacao salvar(locacao novaLocacao) {
        String query = "INSERT INTO Locacao (idCliente, idFilme, dataInicio, dataFim) VALUES (?, ?, ?, ?)";
        Connection conn = Banco.getConnection();
        PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, query);
        try {
            stmt.setString(1, novaLocacao.getCliente());
            stmt.setString(2, novaLocacao.getFilme());
            stmt.setDate(3, new Date(novaLocacao.getDataInicio().getTime()));
            stmt.setDate(4, novaLocacao.getDataFim() != null ? new Date(novaLocacao.getDataFim().getTime()) : null);

            stmt.execute();
            ResultSet resultado = stmt.getGeneratedKeys();
            if (resultado.next()) {
                novaLocacao.setIdLocacao(resultado.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar Locacao");
            System.out.println("Erro: " + e.getMessage());
        } finally {
            Banco.closePreparedStatement(stmt);
            Banco.closeConnection(conn);
        }
        return novaLocacao;
    }

    @Override
    public boolean excluir(int idLocacao) {
        String query = "DELETE FROM Locacao WHERE idLocacao = ?";
        boolean excluiu = false;
        Connection conn = Banco.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idLocacao);
            excluiu = stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir Locacao");
            System.out.println("Erro: " + e.getMessage());
        } finally {
            Banco.closeConnection(conn);
        }

        return excluiu;
    }

    @Override
    public boolean alterar(locacao locacaoEditada) {
        String query = "UPDATE Locacao SET idCliente = ?, idFilme = ?, dataInicio = ?, dataFim = ? WHERE idLocacao = ?";
        boolean alterou = false;
        Connection conn = Banco.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, locacaoEditada.getCliente());
            stmt.setString(2, locacaoEditada.getFilme());
            stmt.setDate(3, new Date(locacaoEditada.getDataInicio().getTime()));
            stmt.setDate(4, locacaoEditada.getDataFim() != null ? new Date(locacaoEditada.getDataFim().getTime()) : null);
            stmt.setInt(5, locacaoEditada.getIdLocacao());
            alterou = stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar Locacao");
            System.out.println("Erro: " + e.getMessage());
        } finally {
            Banco.closeConnection(conn);
        }

        return alterou;
    }

    @Override
    public locacao consultarPorId(int idLocacao) {
        String query = "SELECT * FROM Locacao WHERE idLocacao = ?";
        locacao locacao = null;
        Connection conn = Banco.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idLocacao);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    locacao = new locacao();
                    locacao.setIdLocacao(rs.getInt("idLocacao"));
                    
                    // Fetch the Cliente object
                    cliente cliente = new cliente();
                    cliente.setIdCliente(rs.getInt("idCliente"));
                    locacao.setCliente(rs.getString("Cliente"));;
                    
                    // Fetch the Filme object
                    filme filme = new filme();
                    filme.setIdFilme(rs.getInt("idFilme"));
                    locacao.setFilme(rs.getString("Filme"));
                    
                    locacao.setDataInicio(rs.getDate("dataInicio"));
                    locacao.setDataFim(rs.getDate("dataFim"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar Locacao com o id: " + idLocacao);
            System.out.println("Erro: " + e.getMessage());
        } finally {
            Banco.closeConnection(conn);
        }

        return locacao;
    }

    @Override
    public ArrayList<locacao> consultarTodos() {
        String query = "SELECT * FROM Locacao";
        ArrayList<locacao> locacoes = new ArrayList<>();
        Connection conn = Banco.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                locacao locacao = new locacao();
                locacao.setIdLocacao(rs.getInt("idLocacao"));
                
                // Fetch the Cliente object
                cliente cliente = new cliente();
                cliente.setIdCliente(rs.getInt("idCliente"));
                locacao.setCliente(rs.getString("Cliente"));;
                
                // Fetch the Filme object
                filme filme = new filme();
                filme.setIdFilme(rs.getInt("idFilme"));
                locacao.setFilme(rs.getString("Filme"));
                
                locacao.setDataInicio(rs.getDate("dataInicio"));
                locacao.setDataFim(rs.getDate("dataFim"));
                
                locacoes.add(locacao);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar todas as Locacoes");
            System.out.println("Erro: " + e.getMessage());
        } finally {
            Banco.closeConnection(conn);
        }

        return locacoes;
    }
    
    public List<locacao> listLocacoesEmAbertoPorCliente(String idCliente) {
        String query = "SELECT * FROM Locacao WHERE idCliente = ? AND dataFim IS NULL";
        List<locacao> locacoesEmAberto = new ArrayList<>();
        Connection conn = Banco.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, idCliente);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    locacao locacao = new locacao();
                    locacao.setIdLocacao(rs.getInt("idLocacao"));
                    // Setar os demais atributos da locação...
                    locacoesEmAberto.add(locacao);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar locações em aberto por cliente: " + e.getMessage());
        } finally {
            Banco.closeConnection(conn);
        }

        return locacoesEmAberto;
    }

}


