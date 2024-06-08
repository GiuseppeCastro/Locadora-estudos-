package model.repository.locadora;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.entity.filme;
import model.repository.Banco;
import model.repository.BaseRepository;

public class filmeRepository implements BaseRepository<filme> {

	@Override
	public filme salvar(filme novoFilme) {
		String query = "INSERT INTO Filme (nome, tipo, duracao) VALUES (?, ?, ?)";
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, query);	
		try {
			stmt.setString(1, novoFilme.getNome() );
			stmt.setString(2, novoFilme.getTipo());
			stmt.setInt(2, novoFilme.getDuracao());
			
			stmt.execute();
			ResultSet resultado = stmt.getGeneratedKeys();
					if (resultado.next()) {
						novoFilme.setIdFilme(resultado.getInt(1));
					}
		} catch(SQLException e) {
			System.out.println ("Erro ao salvar Filme");
			System.out.println ("Erro: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);
		}
		return novoFilme;
	}

	@Override
	public boolean excluir(int idFilme) {
		  String query = "DELETE FROM Filme WHERE id = ?";
	        boolean excluiu = false;
	        Connection conn = Banco.getConnection();

	        try (PreparedStatement stmt = conn.prepareStatement(query)) {
	            stmt.setInt(1, idFilme);
	            excluiu = stmt.executeUpdate() > 0;
	        } catch (SQLException e) {
	            System.out.println("Erro ao excluir Filme");
	            System.out.println("Erro: " + e.getMessage());
	        } finally {
	            Banco.closeConnection(conn);
	        }

	        return excluiu;
	}

	@Override
	public boolean alterar(filme filmeEditado) {
		String query = "UPDATE Cliente SET nome = ?, tipo = ?, duracao = ? WHERE id = ?";
        boolean alterou = false;
        Connection conn = Banco.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, filmeEditado.getNome());
            stmt.setString(2, filmeEditado.getTipo());
            stmt.setInt(3, filmeEditado.getDuracao());
            alterou = stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar Filme");
            System.out.println("Erro: " + e.getMessage());
        } finally {
            Banco.closeConnection(conn);
        }

        return alterou;
	}

	@Override
    public filme consultarPorId(int idFilme) {
        String query = "SELECT * FROM Filme WHERE idFilme = ?";
        filme filme = null;
        Connection conn = Banco.getConnection();

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idFilme);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    filme = new filme();
                    filme.setIdFilme(rs.getInt("idFilme"));
                    filme.setNome(rs.getString("nome"));
                    filme.setTipo(rs.getString("tipo"));
                    filme.setDuracao(rs.getInt("duracao"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar filme com o id: " + idFilme);
            System.out.println("Erro: " + e.getMessage());
        } finally {
            Banco.closeConnection(conn);
        }

        return filme;
	}

	@Override
	public ArrayList<filme> consultarTodos() {
		 ArrayList<filme> filmes = new ArrayList<>();
	        String query = "SELECT * FROM Filme";
	        Connection conn = Banco.getConnection();

	        try (PreparedStatement stmt = conn.prepareStatement(query);
	             ResultSet rs = stmt.executeQuery()) {

	            while (rs.next()) {
	                filme filme = new filme();
	                filme.setIdFilme(rs.getInt("id"));
	                filme.setNome(rs.getString("nome"));
	                filme.setTipo(rs.getString("tipo"));
	                filme.setDuracao(rs.getInt("duracao"));
	                filmes.add(filme);
	            }
	        } catch (SQLException e) {
	            System.out.println("Erro ao consultar todos os filmes");
	            System.out.println("Erro: " + e.getMessage());
	        } finally {
	            Banco.closeConnection(conn);
	        }

	        return filmes;
	}

}
