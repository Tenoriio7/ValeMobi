package br.com.valemobi.core.web.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.valemobi.core.impl.controle.Fachada;
import br.com.valemobi.core.util.FacesUtil;
import br.com.valemobi.domain.EntidadeDominio;
import br.com.valemobi.domain.Genero;
import br.com.valemobi.domain.Mercadoria;

@ManagedBean
@ViewScoped
public class MercadoriaBean {
	private Mercadoria mercadoriaCadastro;
	List<EntidadeDominio> listaMercadoria;
	List<EntidadeDominio> listaMercadoriasFiltradas ;
	List<EntidadeDominio> listaGeneros = new ArrayList<>();
	Fachada Fachada = new Fachada();
	private String acao;
	private Long codigo;

	public List<EntidadeDominio> getListaGeneros() {
		if (listaGeneros == null)
			listaGeneros = new ArrayList<>();
		return listaGeneros;
	}

	public void setListaGeneros(List<EntidadeDominio> listaGeneros) {
		this.listaGeneros = listaGeneros;
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

	public Mercadoria getMercadoriaCadastro() {
		if (mercadoriaCadastro == null)
			mercadoriaCadastro = new Mercadoria();
		return mercadoriaCadastro;
	}

	public void setMercadoriaCadastro(Mercadoria mercadoriaCadastro) {
		this.mercadoriaCadastro = mercadoriaCadastro;
	}

	public List<EntidadeDominio> getListaMercadoria() {
		
		return listaMercadoria;
	}

	public void setListaMercadoria(List<EntidadeDominio> listaMercadoria) {
		this.listaMercadoria = listaMercadoria;
	}

	public List<EntidadeDominio> getListaMercadoriasFiltradas() {
		
		return listaMercadoriasFiltradas;
	}

	public void setListaMercadoriasFiltradas(List<EntidadeDominio> listaMercadoriasFiltradas) {
		this.listaMercadoriasFiltradas = listaMercadoriasFiltradas;
	}

	public void salvar() {
		try {// Obtêm o command para executar a respectiva operação
			/*
			 * Executa o command que chamará a fachada para executar a operação
			 * requisitada o retorno é uma instância da classe resultado que
			 * pode conter mensagens derro ou entidades de retorno
			 */
			Fachada.salvar(mercadoriaCadastro);
			mercadoriaCadastro = new Mercadoria();

		} catch (RuntimeException ex) {

			FacesUtil.adicionarMSGError("Erro ao tentar incluir Genero:" + ex.getMessage());

		}

	}

	public void carregarPesquisa() {
		try {
			Fachada fachada = new Fachada();
			listaMercadoria = fachada.listar(new Mercadoria());
			System.out.println(listaMercadoria.get(0).getCodigo());
		} catch (RuntimeException ex) {

			FacesUtil.adicionarMSGError("Erro ao tentar listar os  Generos:" + ex.getMessage());

		}
	}

	public void carregarCadastro() {
		try {
			String valor = FacesUtil.getParam("merCod");
			if (valor != null) {
				Long codigo = Long.parseLong(valor);
				mercadoriaCadastro = (Mercadoria) Fachada.buscarGenerico(codigo, new Mercadoria());
			}

		} catch (RuntimeException ex) {

		}
	}

	public void excluir() {
		try {
			// Obtêm o command para executar a respectiva operação
			/*
			 * Executa o command que chamará a fachada para executar a operação
			 * requisitada o retorno é uma instância da classe resultado que
			 * pode conter mensagens derro ou entidades de retorno
			 */
			Fachada.excluir(mercadoriaCadastro);
			mercadoriaCadastro = new Mercadoria();

		} catch (RuntimeException ex) {

			FacesUtil.adicionarMSGError("Erro ao tentar remover Genero:" + ex.getMessage());

		}

	}

	public void editar() {
		try {
			// Obtêm o command para executar a respectiva operação
			/*
			 * Executa o command que chamará a fachada para executar a operação
			 * requisitada o retorno é uma instância da classe resultado que
			 * pode conter mensagens derro ou entidades de retorno
			 */
			Fachada.alterar(mercadoriaCadastro);
			mercadoriaCadastro = new Mercadoria();

		} catch (RuntimeException ex) {

			FacesUtil.adicionarMSGError("Erro ao tentar editar Genero:" + ex.getMessage());

		}

	}

	public void carregarPesquisaGenero() {
		try {

			listaGeneros = Fachada.listar(new Genero());
		} catch (RuntimeException ex) {

			FacesUtil.adicionarMSGError("Erro ao tentar listar os  Fornecedores:" + ex.getMessage());

		}
	}

}
