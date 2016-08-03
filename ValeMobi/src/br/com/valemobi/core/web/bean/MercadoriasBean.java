package br.com.valemobi.core.web.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.valemobi.domain.Mercadoria;

@ManagedBean
@ViewScoped
public class MercadoriasBean {
	
	private List<Mercadoria> listaMercadoria =  new ArrayList<>();
	private List<Mercadoria> listaMercadoriaFiltradas =  new ArrayList<>();
	private Mercadoria mercadoriaCadastro;
	public List<Mercadoria> getListaMercadoria() {
		return listaMercadoria;
	}
	public void setListaMercadoria(List<Mercadoria> listaMercadoria) {
		this.listaMercadoria = listaMercadoria;
	}
	public Mercadoria getMercadoriaCadastro() {
		if(mercadoriaCadastro ==null)
			mercadoriaCadastro = new  Mercadoria();
		return mercadoriaCadastro;
	}
	public void setMercadoriaCadastro(Mercadoria mercadoriaCadastro) {
		this.mercadoriaCadastro = mercadoriaCadastro;
	}
	public List<Mercadoria> getListaMercadoriaFiltradas() {
		return listaMercadoriaFiltradas;
	}
	public void setListaMercadoriaFiltradas(List<Mercadoria> listaMercadoriaFiltradas) {
		this.listaMercadoriaFiltradas = listaMercadoriaFiltradas;
	}

}
