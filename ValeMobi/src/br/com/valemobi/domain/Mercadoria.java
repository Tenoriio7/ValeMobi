package br.com.valemobi.domain;

public class Mercadoria {
	
	private Long codigo;
	private String tipoDeMercadoria;
	private String nome;
	private Integer quantidade;
	private Double preco;
	private String tipoDeNegocio;
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getTipoDeMercadoria() {
		return tipoDeMercadoria;
	}
	public void setTipoDeMercadoria(String tipoDeMercadoria) {
		this.tipoDeMercadoria = tipoDeMercadoria;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public String getTipoDeNegocio() {
		return tipoDeNegocio;
	}
	public void setTipoDeNegocio(String tipoDeNegocio) {
		this.tipoDeNegocio = tipoDeNegocio;
	} 
	

	

}
