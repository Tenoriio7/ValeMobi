package br.com.valemobi.core.web.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.valemobi.domain.Genero;

@ManagedBean
@ViewScoped
public class GeneroBean {
	private Genero GeneroCadastro;
	List<Genero> listaGeneros;
	List<Genero> listaGenerosFiltrados;
	private String acao;
	private Long codigo;
	private static Map<String, ICommand> commands;
	
	public GeneroBean() {
		
		commands = new HashMap<String, ICommand>();
		commands.put("Salvar", new SalvarCommand());
		commands.put("Excluir", new ExcluirCommand());
		commands.put("Editar", new AlterarCommand());
	}
	
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
	
	public List<Genero> getListaGeneros() {
		return listaGeneros;
	}
	
	public void setListaGeneros(List<Genero> listaGeneros) {
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
		{//Obt�m o command para executar a respectiva opera��o
			ICommand command = commands.get(acao);
			/*Executa o command que chamar� a fachada para executar a opera��o requisitada
			 * o retorno � uma inst�ncia da classe resultado que pode conter mensagens derro 
			 * ou entidades de retorno			 */
			command.execute(GeneroCadastro);
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
			//Obt�m o command para executar a respectiva opera��o
			ICommand command = commands.get(acao);
			/*Executa o command que chamar� a fachada para executar a opera��o requisitada
			 * o retorno � uma inst�ncia da classe resultado que pode conter mensagens derro 
			 * ou entidades de retorno			 */
			command.execute(GeneroCadastro);
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
			//Obt�m o command para executar a respectiva opera��o
			ICommand command = commands.get(acao);
			/*Executa o command que chamar� a fachada para executar a opera��o requisitada
			 * o retorno � uma inst�ncia da classe resultado que pode conter mensagens derro 
			 * ou entidades de retorno			 */
			command.execute(GeneroCadastro);
			GeneroCadastro = new Genero();
			
		}catch(RuntimeException ex)
		{
			
			FacesUtil.adicionarMSGError("Erro ao tentar editar Genero:"+ex.getMessage());
			
		}
		
	}

}
