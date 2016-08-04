package br.com.valemobi.core.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import br.com.valemobi.core.IDAO;
import br.com.valemobi.core.factory.Conexao;
import br.com.valemobi.domain.EntidadeDominio;
import br.com.valemobi.domain.Genero;
import br.com.valemobi.domain.Mercadoria;

public class MercadoriaDAO implements IDAO {
	
	public Long Salvar(EntidadeDominio entidade) throws SQLException {
		if(!(entidade instanceof Mercadoria))
			return null;
		
		Mercadoria mercadoria = new Mercadoria();
		mercadoria = (Mercadoria) entidade;
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("INSERT INTO db_valemobi.tb_mercadoria(mer_codigo,mer_nome,mer_quantidade,mer_preco,mer_tipo,mer_tipo_negocio) ");
		
		sql.append("VALUES (?,?,?,?,?,?)");		
		Connection con = Conexao.getConnection();
		PreparedStatement pstm = (PreparedStatement) con.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);
		
		try {
			
			int i=0;
			pstm.setLong(++i, mercadoria.getCodigo());
			pstm.setString(++i,mercadoria.getNome());
			pstm.setLong(++i,mercadoria.getQuantidade());
			pstm.setDouble(++i,mercadoria.getPreco());
			pstm.setLong(++i,mercadoria.getTipoDeMercadoria().getCodigo());
			pstm.setString(++i, mercadoria.getTipoNegocio());
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet rset =  pstm.getGeneratedKeys();
		long retorno = 0;
		while(rset.next()){
			rset.getInt(1);
			retorno=(long) rset.getInt(1);
		}
		return retorno;

	}

	public List<EntidadeDominio> listar() {
			
		StringBuffer sql = new StringBuffer(); 
		sql.append("SELECT * FROM db_valemobi.tb_mercadoria;");			
		List<EntidadeDominio> lista = new ArrayList<>();
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = (PreparedStatement) con.prepareStatement(sql.toString());
			ResultSet rSet = pstm.executeQuery();
			
			while(rSet.next()) {
				
				Mercadoria mercadoria = new Mercadoria();
				mercadoria.setCodigo(rSet.getLong("mer_codigo"));
				mercadoria.setNome(rSet.getString("mer_nome"));
				mercadoria.setPreco(rSet.getDouble("mer_preco"));
				mercadoria.setQuantidade(rSet.getInt("mer_quantidade"));
				mercadoria.getTipoDeMercadoria().setCodigo((rSet.getLong("mer_tipo")));
				mercadoria.setTipoNegocio(rSet.getString("mer_tipo_negocio"));
				lista.add(mercadoria);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
		
	
	}
	
	public Mercadoria buscarPorCodigo(Long codigo) {

		StringBuffer sql = new StringBuffer(); 
		sql.append("SELECT * FROM db_valemobi.tb_mercadoria");
		sql.append(" where tb_mercadoria.mer_codigo =?");
		
		Mercadoria mercadoria = new  Mercadoria();
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = (PreparedStatement) con.prepareStatement(sql.toString());
			pstm.setLong(1, codigo);
			ResultSet rSet = pstm.executeQuery();
			
			while(rSet.next()){
				
				mercadoria.setCodigo(rSet.getLong("mer_codigo"));
				mercadoria.setNome(rSet.getString("mer_nome"));
				mercadoria.setPreco(rSet.getDouble("mer_preco"));
				mercadoria.setQuantidade(rSet.getInt("mer_quantidade"));
				mercadoria.getTipoDeMercadoria().setCodigo((rSet.getLong("mer_tipo")));
				mercadoria.setTipoNegocio(rSet.getString("mer_tipo_negocio"));
				
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return mercadoria;
 
	}
	
	
	public void Excluir(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Mercadoria))
			return;
		
		Mercadoria mercadoria = new Mercadoria();
		mercadoria = (Mercadoria) entidade;
		
		StringBuffer sql = new StringBuffer();
		sql.append("delete from db_valemobi.tb_mercadoria where mer_codigo = ?");
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = (PreparedStatement) con.prepareStatement(sql.toString());
			pstm.setLong(1, mercadoria.getCodigo());
			pstm.executeUpdate();
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
		
	
}
	
	
	
	public void Editar(EntidadeDominio entidade) {
		if(!(entidade instanceof Mercadoria))
			return;
		
		Mercadoria mercadoria = new Mercadoria();
		mercadoria = (Mercadoria) entidade;
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("UPDATE db_valemobi.tb_mercadoria set mer_codigo =? , mer_nome = ?, ");
		sql.append(" mer_quantidade =? , mer_preco = ? , mer_tipo = ?, mer_tipo_negocio= ?");
		sql.append("WHERE mer_codigo=?");

		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = (PreparedStatement) con.prepareStatement(sql.toString());
			int i=0;
			pstm.setLong(++i, mercadoria.getCodigo());
			pstm.setString(++i,mercadoria.getNome());
			pstm.setLong(++i,mercadoria.getQuantidade());
			pstm.setDouble(++i,mercadoria.getPreco());
			pstm.setLong(++i,mercadoria.getTipoDeMercadoria().getCodigo());
			pstm.setString(++i, mercadoria.getTipoNegocio());
			pstm.setLong(++i, mercadoria.getCodigo());
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



	@Override
	public EntidadeDominio consultar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
