package function;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import dto.MembersDTO;

public class InsertMember {
	Connection conn = null;

	public InsertMember() {
		
		conn = ConnectResource.makeConnection();
	}

	public void join(MembersDTO member) {

		Statement stmt = null;
		String sql = "insert into members(u_id, password, u_name, birthdate, sex)\n";
		sql += "values('" + member.getU_ID() + "', '" + member.getPASSWORD() + "', '" + member.getU_NAME() + "', '"
				+ member.getBIRTHDATE() + "' , '" + member.getSEX() + "')"; // ;은
		// 없음

		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql); // sql문을 던져 넣는다.
		} catch (SQLException e) {

			e.printStackTrace();
		} 

	}

}
