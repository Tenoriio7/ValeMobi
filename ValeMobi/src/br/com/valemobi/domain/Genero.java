package br.com.valemobi.domain;

public class Genero  extends EntidadeDominio {

	private Long codigo;
	
	private String descricao;

	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getCodigo() {
		if(codigo == null)
			codigo = 0L;
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

}
