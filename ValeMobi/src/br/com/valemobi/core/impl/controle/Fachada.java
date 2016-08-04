package br.com.valemobi.core.impl.controle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.valemobi.core.IDAO;
import br.com.valemobi.core.IFachada;
import br.com.valemobi.core.DAO.GeneroDAO;
import br.com.valemobi.core.DAO.MercadoriaDAO;
import br.com.valemobi.core.util.FacesUtil;
import br.com.valemobi.domain.EntidadeDominio;
import br.com.valemobi.domain.Genero;
import br.com.valemobi.domain.Mercadoria;

public class Fachada implements IFachada {

	/**
	 * Mapa de DAOS, ser� indexado pelo nome da entidade O valor � uma inst�ncia
	 * do DAO para uma dada entidade;
	 */
	private static Map<String, IDAO> daos;

	/**
	 * Mapa para conter as regras de neg�cio de todas opera��es por entidade; O
	 * valor � um mapa que de regras de neg�cio indexado pela opera��o
	 */


	public Fachada() {
		/* Int�nciando o Map de	 DAOS */
		daos = new HashMap<String, IDAO>();
		/* Int�nciando o Map de Regras de Neg�cio */

		/* Criando inst�ncias dos DAOs a serem utilizados */
		
		GeneroDAO generoDAO = new GeneroDAO();
		MercadoriaDAO mercadoriaDAO =  new MercadoriaDAO();
		

		/* Adicionando cada dao no MAP indexando pelo nome da classe */
		daos.put(Genero.class.getName(), (IDAO) generoDAO);
		daos.put(Mercadoria.class.getName(), (IDAO) mercadoriaDAO);	

		/* Criando inst�ncias de regras de neg�cio a serem utilizados */
		//ValidadorDadosObrigatoriosFornecedor vrDadosObrigatoriosFornecedor = new ValidadorDadosObrigatoriosFornecedor();

		/*
		 * Criando uma lista para conter as regras de neg�cio de fornencedor
		 * quando a opera��o for Salvar
		 */
		//List<IStrategy> rnsSalvarFornecedor = new ArrayList<IStrategy>();
		
		/*
		 * Adicionando as regras a serem utilizadas na opera��o Salvar do
		 * fornecedor
		 */
		//rnsSalvarFornecedor.add(vrDadosObrigatoriosFornecedor);
		

		/*
		 * Cria o mapa que poder� conter todas as listas de regras de neg�cio
		 * espec�fica por opera��o do fornecedor
		 */
		/*
		 * Adiciona a listra de regras na opera��o Salvar no mapa do fornecedor
		 * (lista criada na linha 70)
		 */
		//rnsFornecedor.put("Salvar", rnsSalvarFornecedor);
		// usa a mesma rns por ser as mesmas regras de negocio
		//rnsFornecedor.put("Editar", rnsSalvarFornecedor);

		/*
		 * Adiciona o mapa(criado na linha 79) com as regras indexadas pelas
		 * opera��es no mapa geral indexado pelo nome da entidade
		 */
		//rns.put(Fornecedor.class.getName(), rnsFornecedor);

	}

	@Override
	public Object salvar(EntidadeDominio entidade) {
		String nmClasse = entidade.getClass().getName();
		System.out.println(entidade.getClass().getName());

			IDAO dao = daos.get(nmClasse);
			System.out.println("dao" + dao);
			try {
				dao.Salvar(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				FacesUtil.adicionarMSGInfo("Salvo com sucesso");
			} catch (SQLException e) {
				e.printStackTrace();

			}
			return "Opera��o realizada com sucesso";

	}

	@Override
	public Object alterar(EntidadeDominio entidade) {
		String nmClasse = entidade.getClass().getName();


			IDAO dao = daos.get(nmClasse);
			try {
				dao.Editar(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);

				FacesUtil.adicionarMSGInfo("Editado com sucesso");
			} catch (SQLException e) {
				e.printStackTrace();

			}

			return "Opera��o realizada com sucesso";
	}

	@Override
	public Object excluir(EntidadeDominio entidade) {
		String nmClasse = entidade.getClass().getName();


			IDAO dao = daos.get(nmClasse);
			try {
				dao.Excluir(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);

				FacesUtil.adicionarMSGInfo("Excluido com sucesso");
			} catch (SQLException e) {
				e.printStackTrace();

			}
			return "Opera��o realizada com sucesso";
	}

	public List<EntidadeDominio> listar(EntidadeDominio entidade) {
		List<EntidadeDominio> lista = null;
		String nmClasse = entidade.getClass().getName();

		IDAO dao = daos.get(nmClasse);
		try {

			lista = (dao.listar());
		} catch (RuntimeException e) {
			e.printStackTrace();

		}

		return lista;

	}


	public static EntidadeDominio buscarGenerico(Long codigo, EntidadeDominio entidade) {
		String nmClasse = entidade.getClass().getName();
		IDAO dao = daos.get(nmClasse);
		EntidadeDominio entidadeRetorno = null;
		entidadeRetorno = dao.buscarPorCodigo(codigo);
		return entidadeRetorno;

	}

	@Override
	public Long salvarVenda(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object consultar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}


}
