package function;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.MembersDTO;

public class SelectAllMembers {
	
	Connection conn = null;
	MembersDTO mmdto;

	// 생성자가 만들어지면 연결되도록
	public SelectAllMembers() {

		conn = ConnectResource.makeConnection();
	}

	// 선택받은 영화정보를 담을 리스트를 만듣 메소드
	public List<MembersDTO> getMemberList() {

		List<MembersDTO> list = new ArrayList<MembersDTO>();		

		Statement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT U_ID, U_NAME, BIRTHDATE, SEX FROM MEMBERS";
		

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {				
				mmdto = new MembersDTO();
				mmdto.setU_ID(rs.getString("U_ID"));
				mmdto.setU_NAME(rs.getString("U_NAME"));
				mmdto.setBIRTHDATE(rs.getInt("BIRTHDATE"));
				mmdto.setSEX(rs.getString("SEX"));			

				list.add(mmdto);			
			}		
			

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;

	}
}
