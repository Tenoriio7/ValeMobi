package br.com.valemobi.domain;

public class Mercadoria  extends EntidadeDominio{
	
	private String nome;
	private Integer quantidade;
	private Double preco;
	private Genero tipoDeMercadoria;
	
	 public Genero getTipoDeMercadoria() {
		 if(tipoDeMercadoria == null)
			 tipoDeMercadoria =  new Genero();
		return tipoDeMercadoria;
	}
	 public void setTipoDeMercadoria(Genero tipoDeMercadoria) {
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
	

	

}
