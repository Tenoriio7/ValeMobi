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

public class GeneroDAO implements IDAO {
	
	public Long Salvar(EntidadeDominio entidade) throws SQLException {
		if(!(entidade instanceof Genero))
			return null;
		
		Genero genero = new Genero();
		genero = (Genero) entidade;
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("INSERT INTO db_valemobi.tb_genero(gen_codigo,gen_descricao) ");
		
		sql.append("VALUES (?,?)");		
		Connection con = Conexao.getConnection();
		PreparedStatement pstm = (PreparedStatement) con.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);
		
		try {
			
			int i=0;
			pstm.setLong(++i, genero.getCodigo());
			pstm.setString(++i,genero.getDescricao());
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ResultSet rset =  pstm.getGeneratedKeys();
		return rset.getLong(1);

	}

	public List<EntidadeDominio> listar() {
			
		StringBuffer sql = new StringBuffer(); 
		sql.append("SELECT * FROM db_valemobi.tb_genero;");			
		List<EntidadeDominio> lista = new ArrayList<>();
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = (PreparedStatement) con.prepareStatement(sql.toString());
			ResultSet rSet = pstm.executeQuery();
			
			while(rSet.next()) {
				
				Genero genero = new Genero();
				genero.setCodigo(rSet.getLong("gen_codigo"));
				genero.setDescricao(rSet.getString("gen_descricao"));			
				lista.add(genero);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
		
	
	}
	
	public Genero buscarPorCodigo(Long codigo) {

		StringBuffer sql = new StringBuffer(); 
		sql.append("SELECT * FROM db_valemobi.tb_genero");
		sql.append(" where tb_genero.gen_codigo =?");
		
		Genero genero = new  Genero();
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = (PreparedStatement) con.prepareStatement(sql.toString());
			pstm.setLong(1, codigo);
			ResultSet rSet = pstm.executeQuery();
			
			while(rSet.next()){
				genero.setCodigo(rSet.getLong("gen_codigo"));	
				genero.setDescricao(rSet.getString("gen_descricao"));
				
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return genero;
 
	}
	
	
	public void Excluir(EntidadeDominio entidade) {
		
		if(!(entidade instanceof Genero))
			return;
		
		Genero genero = new Genero();
		genero = (Genero) entidade;
		
		StringBuffer sql = new StringBuffer();
		sql.append("delete from db_valemobi.tb_genero where gen_codigo = ?");
		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = (PreparedStatement) con.prepareStatement(sql.toString());
			pstm.setLong(1, genero.getCodigo());
			pstm.executeUpdate();
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
		
	
}
	
	
	
	public void Editar(EntidadeDominio entidade) {
		if(!(entidade instanceof Genero))
			return;
		
		Genero genero = new Genero();
		genero = (Genero) entidade;
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("UPDATE db_valemobi.tb_genero set gen_codigo =? ,gen_descricao = ?");	
		sql.append("WHERE gen_codigo=?");

		
		Connection con = Conexao.getConnection();
		
		try {
			PreparedStatement pstm = (PreparedStatement) con.prepareStatement(sql.toString());
			int i=0;
			pstm.setLong(++i,genero.getCodigo());
			pstm.setString(++i,genero.getDescricao());
			pstm.setLong(++i,genero.getCodigo());
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws SQLException {
		GeneroDAO dao  =  new GeneroDAO();
		Genero gen =  new Genero();
		gen.setCodigo(1L);
		gen.setDescricao("teste3");
		dao.Excluir(gen);
	}


	@Override
	public EntidadeDominio consultar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
