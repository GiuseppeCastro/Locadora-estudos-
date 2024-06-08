package model.entity;

public class filme {
	private Integer idFilme;
	private String nome;
	private String tipo;
	private Integer duracao;
	
	public filme() {
        super();
    }
	
	public filme (Integer idFilme, String nome, String tipo, Integer duracao) {
		super();
		this.idFilme = idFilme;
		this.nome = nome;
		this.tipo = tipo;
		this.duracao = duracao;	
	}
	public Integer getIdFilme() {
		return idFilme;
	}
	public void setIdFilme(Integer idFilme) {
		this.idFilme = idFilme;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo (String tipo) {
		this.tipo = tipo;
	}
	public Integer getDuracao() {
		return duracao; 
	}
	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}
}


