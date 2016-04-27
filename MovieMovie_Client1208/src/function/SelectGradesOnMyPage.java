package function;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.MembersDTO;
import dto.MyPageDTO;

public class SelectGradesOnMyPage {
	Connection conn = null;

	public SelectGradesOnMyPage() { // 생성자가 만들어지면 연결되도록

		conn = ConnectResource.makeConnection();
	}

	// 선택받은 영화정보를 담을 리스트를 만듣 메소드
	public List<MyPageDTO> list(MembersDTO member) {

		List<MyPageDTO> list = new ArrayList<MyPageDTO>();
		String id = member.getU_ID();

		Statement stmt = null;
		ResultSet rs = null;
		// DB에 연결

		String sql = "SELECT * FROM MOVIES M, GRADE G WHERE M.M_ID = G.M_ID AND G.U_ID = '" + id        + "'";
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

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
				
				list.add(mmdto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;

	}
}
