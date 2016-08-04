package br.com.valemobi.core.web.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.valemobi.core.impl.controle.Fachada;
import br.com.valemobi.core.util.FacesUtil;
import br.com.valemobi.domain.EntidadeDominio;
import br.com.valemobi.domain.Genero;

@ManagedBean
@ViewScoped
public class GeneroBean {
	private Genero GeneroCadastro;
	List<EntidadeDominio> listaGeneros;
	List<Genero> listaGenerosFiltrados;
	Fachada Fachada =  new  Fachada();
	private String acao;
	private Long codigo;
	
	
	public String getAcao() {
		return acao;
	}
	public Long getCodigo() {
		return codigo;
	}
	public void setAcao(String acao) {
		this.acao = acao;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
		
	
	public Genero getGeneroCadastro() {
		if(GeneroCadastro ==null)
			GeneroCadastro= new Genero();
		
		return GeneroCadastro;
	}
	public void setGeneroCadastro(Genero GeneroCadastro) {
		this.GeneroCadastro = GeneroCadastro;
	}
	
	public List<EntidadeDominio> getListaGeneros() {
		return listaGeneros;
	}
	
	public void setListaGeneros(List<EntidadeDominio> listaGeneros) {
		this.listaGeneros = listaGeneros;
	}
	
	public List<Genero> getListaGenerosFiltrados() {
		return listaGenerosFiltrados;
	}
	public void setListaGenerosFiltrados(List<Genero> listaGenerosFiltrados) {
		this.listaGenerosFiltrados = listaGenerosFiltrados;
	}
	
	public void novo()
	{
		GeneroCadastro.setDescricao(null);
	}
	
	public void salvar()
	{
		try
		{//Obtêm o command para executar a respectiva operação
			/*Executa o command que chamará a fachada para executar a operação requisitada
			 * o retorno é uma instância da classe resultado que pode conter mensagens derro 
			 * ou entidades de retorno			 */
			Fachada.salvar(GeneroCadastro);
			GeneroCadastro = new Genero();
			
		}catch(RuntimeException ex)
		{
			
			FacesUtil.adicionarMSGError("Erro ao tentar incluir Genero:"+ex.getMessage());
			
		}
		
	}
	
	public void carregarPesquisa()
	{
		try
		{
			Fachada fachada =  new Fachada();
			listaGeneros = fachada.listar(new Genero());			
		}catch(RuntimeException ex)
		{
			
			FacesUtil.adicionarMSGError("Erro ao tentar listar os  Generos:"+ex.getMessage());
			
		}
	}
	
	public void carregarCadastro(){
		try{
			String valor = FacesUtil.getParam("genCod");
			if(valor != null)
			{
				Long codigo = Long.parseLong(valor);
				GeneroCadastro=(Genero) Fachada.buscarGenerico(codigo, new Genero());
			}
		
		} catch(RuntimeException ex){
			
		}
	}
	
	public void excluir()
	{
		try
		{
			//Obtêm o command para executar a respectiva operação
			/*Executa o command que chamará a fachada para executar a operação requisitada
			 * o retorno é uma instância da classe resultado que pode conter mensagens derro 
			 * ou entidades de retorno			 */
			Fachada.excluir(GeneroCadastro);
			GeneroCadastro = new Genero();
			
		}catch(RuntimeException ex)
		{
			
			FacesUtil.adicionarMSGError("Erro ao tentar remover Genero:"+ex.getMessage());
			
		}
		
	}
	
	
	public void editar()
	{
		try
		{
			//Obtêm o command para executar a respectiva operação
			/*Executa o command que chamará a fachada para executar a operação requisitada
			 * o retorno é uma instância da classe resultado que pode conter mensagens derro 
			 * ou entidades de retorno			 */
			Fachada.alterar(GeneroCadastro);
			GeneroCadastro = new Genero();
			
		}catch(RuntimeException ex)
		{
			
			FacesUtil.adicionarMSGError("Erro ao tentar editar Genero:"+ex.getMessage());
			
		}
		
	}

}
