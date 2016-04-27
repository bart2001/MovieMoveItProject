package function;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.AnalysisDTO;

public class GetAnalysis {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	Statement stmt = null;
	ResultSetMetaData md;
	Vector<String> in;
	Vector<String> title;
	JTable table;
	DefaultTableModel model;

	public GetAnalysis(String u_id) {
		conn = ConnectResource.makeConnection();
	}

	public String getTotaltime(String u_id) {
		String totaltime = null;
		try {
			// 쿼리
			String sql = "SELECT SUM(DISTINCT M.V_TIME) AS VTIME  FROM MEMBERS ME, MOVIES M, GRADE G\n";
			sql += "WHERE  ME.U_ID = G.U_ID AND M.M_ID = G.M_ID\n";
			sql += "AND G.U_ID = '" + u_id + "'";

			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery(sql);

			while (rs.next()) {
				totaltime = rs.getString("VTIME");
				System.out.println(totaltime);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("Totaltime이 입력됩니다");
		return totaltime;
	}

	public String getAvggrade(String id) {
		String avggrade = null;

		try {
			// 쿼리
			String sql = "";
			sql = "SELECT G.U_ID, ROUND(AVG(GRADE),2) FROM GRADE G WHERE G.U_ID = '" + id + "' GROUP BY G.U_ID";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery(sql);
			while (rs.next()) {
				avggrade = rs.getString("ROUND(AVG(GRADE),2)");
				System.out.println(avggrade);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Avggrade이 입력됩니다");
		return avggrade;
	}

	public ArrayList<AnalysisDTO> getGenre(String id) {
		ArrayList<AnalysisDTO> GenreSet = new ArrayList<>();
		AnalysisDTO genre = null;

		String sql = "";
		sql = "SELECT M.GENRE, COUNT(M.GENRE) FROM GRADE G, MOVIES M WHERE G.M_ID = M.M_ID AND U_ID = '" + id
				+ "' GROUP BY M.GENRE HAVING COUNT(M.GENRE)= (SELECT MAX(COUNT) FROM (SELECT GENRE, COUNT(M.GENRE) COUNT FROM GRADE G, MOVIES M WHERE G.M_ID = M.M_ID AND U_ID = '"
				+ id + "' GROUP BY M.GENRE))";

		try {
			// 쿼리
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery(sql);

			while (rs.next()) {
				genre = new AnalysisDTO();
				genre.setName(rs.getString("GENRE"));
				genre.setValue(rs.getString("COUNT(M.GENRE)"));
				GenreSet.add(genre);
			}
			rs = ps.executeQuery(sql);

			System.out.println(genre);

		} catch (

		SQLException e)

		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("genre가 입력됩니다");
		return GenreSet;
	}

	public ArrayList<AnalysisDTO> getActor(String id) {
		ArrayList<AnalysisDTO> ActorSet = new ArrayList<>();
		AnalysisDTO actor = null;

		String sql = "";
		sql = "SELECT M.ACTOR, COUNT(M.ACTOR) AS COUNTED \n";
		sql += "FROM GRADE G, MOVIES M WHERE G.M_ID = M.M_ID AND U_ID = '" + id + "' \n";
		sql += "GROUP BY M.ACTOR HAVING COUNT(M.ACTOR)= (SELECT MAX(COUNT) FROM \n";
		sql += "(SELECT M.ACTOR, COUNT(M.ACTOR) AS COUNT FROM GRADE G, MOVIES M \n";
		sql += "WHERE G.M_ID = M.M_ID AND U_ID = '" + id + "' GROUP BY M.ACTOR))";

		try {
			// 쿼리
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery(sql);

			while (rs.next()) {
				actor = new AnalysisDTO();
				actor.setName(rs.getString("ACTOR"));
				actor.setValue(rs.getString("COUNTED"));
				ActorSet.add(actor);
			}
			rs = ps.executeQuery(sql);

			System.out.println(actor);

		} catch (SQLException e)

		{
			e.printStackTrace();
		}
		System.out.println("actor가 입력됩니다");
		return ActorSet;
	}

	public ArrayList<AnalysisDTO> getDirector(String id) {
		ArrayList<AnalysisDTO> DirectorSet = new ArrayList<>();
		AnalysisDTO director = null;

		String sql = "";
		sql = "SELECT M.DIRECTOR, COUNT(M.DIRECTOR) AS COUNTED \n";
		sql += "FROM GRADE G, MOVIES M WHERE G.M_ID = M.M_ID AND U_ID = '" + id + "' \n";
		sql += "GROUP BY M.DIRECTOR HAVING COUNT(M.DIRECTOR)= (SELECT MAX(COUNT) FROM \n";
		sql += "(SELECT M.DIRECTOR, COUNT(M.DIRECTOR) AS COUNT FROM GRADE G, MOVIES M \n";
		sql += "WHERE G.M_ID = M.M_ID AND U_ID = '" + id + "' GROUP BY M.DIRECTOR))";

		try {
			// 쿼리
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {

				director = new AnalysisDTO();
				director.setName(rs.getString("DIRECTOR"));
				director.setValue(rs.getString("COUNTED"));
				DirectorSet.add(director);
			}

			System.out.println(director);

		} catch (SQLException e)

		{

			e.printStackTrace();
		}
		System.out.println("director가 입력됩니다");
		return DirectorSet;
	}

	public ArrayList<AnalysisDTO> getNation(String id) {
		ArrayList<AnalysisDTO> NationSet = new ArrayList<>();
		AnalysisDTO nation = null;

		String sql = "";
		sql = "SELECT M.NATION, COUNT(M.NATION) AS COUNTED \n";
		sql += "FROM GRADE G, MOVIES M WHERE G.M_ID = M.M_ID AND U_ID = '" + id + "' \n";
		sql += "GROUP BY M.NATION HAVING COUNT(M.NATION)= (SELECT MAX(COUNT) FROM \n";
		sql += "(SELECT M.NATION, COUNT(M.NATION) AS COUNT FROM GRADE G, MOVIES M \n";
		sql += "WHERE G.M_ID = M.M_ID AND U_ID = '" + id + "' GROUP BY M.NATION))";

		try {
			// 쿼리
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			md = rs.getMetaData();

			while (rs.next()) {
				nation = new AnalysisDTO();
				nation.setName(rs.getString("NATION"));
				nation.setValue(rs.getString("COUNTED"));
				NationSet.add(nation);
			}

			System.out.println(nation);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("nation가 입력됩니다");
		return NationSet;
	}

	public ArrayList<String> getFrequentgrade(String id) {
		ArrayList<String> grade = new ArrayList<>();

		String sql = "";
		sql = "SELECT G.GRADE FROM GRADE G WHERE G.U_ID = '" + id + "' \n";
		sql += "GROUP BY G.GRADE HAVING COUNT(G.GRADE) = (SELECT MAX(COUNTED) \n";
		sql += "  FROM (SELECT COUNT(G.GRADE) AS COUNTED FROM GRADE G  WHERE G.U_ID = '" + id + "' \n";
		sql += "GROUP BY G.GRADE))";

		try {
			// 쿼리
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {

				grade.add(rs.getString("GRADE"));

			}

			System.out.println(grade);

		} catch (SQLException e)

		{
			e.printStackTrace();
		}
		System.out.println("frequentgrade가 입력됩니다");
		return grade;
	}

	public String getTotalmovie(String u_id) {
		String totalmovie = null;
		try {
			// 쿼리
			String sql = "SELECT COUNT(M_ID) FROM GRADE WHERE U_ID = '" + u_id + "'";

			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery(sql);
			while (rs.next()) {
				totalmovie = rs.getString("COUNT(M_ID)");
				System.out.println(totalmovie);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		System.out.println("Totalmovie가 입력됩니다");
		return totalmovie;
	}

	public ArrayList<String> getRecommendedMovie(String u_id) {
		ArrayList<String> RecommendedMovie = new ArrayList<>();

		try {
			String sql = "SELECT DISTINCT M.M_NAME FROM GRADE G, " + "MOVIES M WHERE G.M_ID = M.M_ID AND G.M_ID NOT IN "
					+ "(SELECT G.M_ID FROM GRADE G WHERE G.U_ID = '" + u_id + "') AND U_ID IN "
					+ "(SELECT G.U_ID FROM GRADE G WHERE G.M_ID IN " + "(SELECT G.M_ID FROM GRADE G WHERE G.U_ID = '"
					+ u_id + "') AND G.U_ID != " + "'" + u_id + "'GROUP BY G.U_ID HAVING COUNT(G.U_ID) > 4)";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery(sql);

			RecommendedMovie = new ArrayList<String>();

			while (rs.next()) {

				RecommendedMovie.add(rs.getString("M_NAME"));

			}
			System.out.println(RecommendedMovie);
		} catch (SQLException e)

		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("friendmovie가 입력됩니다");
		return RecommendedMovie;

	}

}
