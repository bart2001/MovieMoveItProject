package function;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteMember {
	
	Connection conn = null;
	
	public DeleteMember() {
		conn = ConnectResource.makeConnection();

	}

	public int delete(String id) {

		int cnt = 0;

		Statement stmt = null;

		// 쿼리
		String sql = "DELETE FROM MEMBERS WHERE U_ID = '" + id + "'";	

		try {
			stmt = conn.createStatement();
			cnt = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt; // cnt 개수만큼 삭제함		
	}
	
}
