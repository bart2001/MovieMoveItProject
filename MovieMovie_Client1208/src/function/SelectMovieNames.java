package function;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.MoviesDTO;
import oracle.jdbc.OracleResultSet;

public class SelectMovieNames {

	String u_id;
	String sql = null;
	List<MoviesDTO> m_list = null;
	List<MoviesDTO> m_list2 = null;

	public SelectMovieNames(String u_id) {
		m_list = new ArrayList<MoviesDTO>();
		m_list2 = new ArrayList<MoviesDTO>();
		this.u_id = u_id;
	};

	public List<MoviesDTO> m_list(String u_id) {

		// 사용자가 본 것 빼고 영화이름 가져오기
		sql = "SELECT m_name, genre, director, actor, release_date, v_time, nation, production, poster "
				+ "FROM MOVIES where m_name not in" + "(SELECT m_name FROM MOVIES m ,grade g where "
				+ "m.M_ID = g.M_ID and g.U_ID = '" + u_id + "') order by M_NAME";

		try {
			Connection con = null;
			con = ConnectResource.makeConnection();
			Statement ps = null;
			ResultSet rs = null;
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery(sql);

			if (rs == null) {
				System.out.println("rs 가 비어있음");
			}

			while (rs.next()) {
				MoviesDTO movie = new MoviesDTO();
				movie.setM_NAME(rs.getString("M_NAME"));			
				movie.setGENRE(rs.getString("GENRE"));
				movie.setDIRECTOR(rs.getString("DIRECTOR"));
				movie.setACTOR(rs.getString("ACTOR"));
				movie.setV_TIME(rs.getInt("V_TIME"));
				movie.setRELEASE_DATE(rs.getString("RELEASE_DATE"));
				movie.setNATION(rs.getString("NATION"));
				movie.setPRODUCTION(rs.getString("PRODUCTION"));
				movie.setPoster(((OracleResultSet)rs).getBlob("POSTER"));
				m_list.add(movie);

			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return m_list;

	}	

	public List<MoviesDTO> m_list2(String u_id, String m_name) {
		
		// 위 쿼리 조건 만족시킴과 동시에 사용자가 입력한 값에 해당하는 쿼리
		/*sql = "SELECT DISTINCT m_name, genre, director, actor, release_date, v_time, nation, production "
				+ " FROM MOVIES m ,grade g where m.M_ID = g.M_ID  and m.M_NAME like %'" + m_name
				+ "%' and m.m_name not in(SELECT m_name FROM MOVIES m ,grade g where m.M_ID = g.M_ID and g.U_ID = '"
				+ u_id + "') order BY m_name";*/
		sql = "SELECT M.M_NAME, M.GENRE, M.DIRECTOR, M.ACTOR, M.RELEASE_DATE, M.V_TIME, M.NATION, M.PRODUCTION, M.POSTER\n";
		sql += "FROM MOVIES M\n";
		sql += "WHERE M.M_NAME LIKE '%" + m_name + "%'\n";
		sql += "AND M.M_NAME NOT IN\n";
		sql += "(SELECT M.M_NAME\n";
		sql += "FROM MOVIES M, GRADE G\n";	
		sql += "WHERE M.M_ID = G.M_ID AND G.U_ID = '" + u_id + "')\n";	
		sql += "ORDER BY M.M_NAME\n";
		

		try {			
			Connection con = ConnectResource.makeConnection();
			Statement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(sql);

			if (rs == null) {
				System.out.println("rs 가 비어있음");
			}

			while (rs.next()) {
				MoviesDTO movie = new MoviesDTO();
				movie.setM_NAME(rs.getString("M_NAME"));				
				movie.setGENRE(rs.getString("GENRE"));
				movie.setDIRECTOR(rs.getString("DIRECTOR"));
				movie.setACTOR(rs.getString("ACTOR"));
				movie.setV_TIME(rs.getInt("V_TIME"));
				movie.setRELEASE_DATE(rs.getString("RELEASE_DATE"));
				movie.setNATION(rs.getString("NATION"));
				movie.setPRODUCTION(rs.getString("PRODUCTION"));
				movie.setPoster(((OracleResultSet)rs).getBlob("poster"));
				m_list2.add(movie);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		}
		return m_list2;

	}

}