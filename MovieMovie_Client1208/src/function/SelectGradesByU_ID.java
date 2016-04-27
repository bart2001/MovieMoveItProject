package function;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.MyPageDTO;

public class SelectGradesByU_ID {

	String ID;
	List<MyPageDTO> list = null;

	public SelectGradesByU_ID() { // 생성자가 만들어지면 연결되도록
		list = new ArrayList<MyPageDTO>();
	}

	// 선택받은 영화정보를 담을 리스트를 만듣 메소드
	public List<MyPageDTO> list(String ID) {
		
		this.ID = ID;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		conn = ConnectResource.makeConnection(); // DB에 연결

		String sql = "SELECT M.M_NAME, M.GENRE, M.DIRECTOR, M.ACTOR, M.RELEASE_DATE, M.V_TIME, M.NATION, M.PRODUCTION, G.GRADE, G.COMMENTS FROM GRADE G, MOVIES M WHERE G.M_ID = M.M_ID AND U_ID = '"
				+ ID + "'";

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs == null) {
				System.out.println("rs가 비어있음");
			}

			while (rs.next()) {

				MyPageDTO mmdto = new MyPageDTO();

				mmdto.setMovie_name(rs.getString("M_NAME"));
				mmdto.setGENRE(rs.getString("GENRE"));
				mmdto.setDIRECTOR(rs.getString("DIRECTOR"));
				mmdto.setACTOR(rs.getString("ACTOR"));
				mmdto.setRELEASE_DATE(rs.getString("RELEASE_DATE"));
				mmdto.setV_TIME(rs.getInt("V_TIME"));
	            mmdto.setNATION(rs.getString("NATION"));
	            mmdto.setPRODUCTION(rs.getString("PRODUCTION"));            
	            mmdto.setGrade(rs.getDouble("GRADE"));
	            mmdto.setComments(rs.getString("COMMENTS"));	            

	            list.add(mmdto);			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;

	}
}
