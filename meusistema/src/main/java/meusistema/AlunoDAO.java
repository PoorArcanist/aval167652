package meusistema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class AlunoDAO {
	private String sql = "SELECT * FROM aluno";
	Connection con;
	
	public AlunoDAO() {
		
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/aluno",
					"postgres", "univel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	public List<Aluno> getTodos(){
		List<Aluno> lista = new ArrayList<Aluno>();
		try (PreparedStatement ps = con
					.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			
			while (rs.next()) {
				Aluno a = new Aluno();
				a.setId(rs.getInt(1));
				a.setNome(rs.getString(2));
				a.setIdade(rs.getInt(3));
				lista.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	public void insere(Aluno a) {
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO aluno VALUES (" + a.getId() + "," + a.getNome() + "," + a.getId() + ")");
			ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void atualiza(int id, Aluno a) {
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM aluno WHERE id = " + id);
			ps.executeQuery();
			ps = con.prepareStatement("INSERT INTO aluno VALUES (" + a.getId() + "," + a.getNome() + "," + a.getId() + ")");
			ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void exclui(int id) {
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("DELETE FROM aluno WHERE id = " + id);
			ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
		

	

}
