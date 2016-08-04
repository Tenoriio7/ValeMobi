package br.com.valemobi.core;
import br.com.valemobi.domain.EntidadeDominio;



public interface IFachada {

	public Object salvar(EntidadeDominio entidade);
	public Object alterar(EntidadeDominio entidade);
	public Object excluir(EntidadeDominio entidade);
	public Long salvarVenda(EntidadeDominio entidade);
	public Object consultar(EntidadeDominio entidade);
	
	
	
}
