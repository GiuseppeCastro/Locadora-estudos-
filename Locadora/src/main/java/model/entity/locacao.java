package model.entity;

import java.util.Date;


public class locacao {
    private Integer idLocacao;
    private String cliente;
    private String filme;
    private Date dataInicio;
    private Date dataFim;

    public locacao() {
        super();
    }

    public locacao(Integer idLocacao, String cliente, String filme, Date dataInicio, Date dataFim) {
        super();
        this.idLocacao = idLocacao;
        this.cliente = cliente;
        this.filme = filme;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public Integer getIdLocacao() {
        return idLocacao;
    }

    public void setIdLocacao(Integer idLocacao) {
        this.idLocacao = idLocacao;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getFilme() {
        return filme;
    }

    public void setFilme(String filme) {
        this.filme = filme;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }
}
