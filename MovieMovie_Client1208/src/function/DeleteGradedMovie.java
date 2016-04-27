package function;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteGradedMovie {
	Connection conn = null;

	public DeleteGradedMovie() {
		conn = ConnectResource.makeConnection();

	}

	public int delete(String movie_name, String id) {

		int cnt = 0;

		Statement stmt = null;

		// 쿼리
		String sql = "DELETE FROM GRADE WHERE M_ID = (SELECT M_ID FROM MOVIES WHERE M_NAME = '" + movie_name
				+ "') AND U_ID = '" + id + "'";
		

		try {
			stmt = conn.createStatement();
			cnt = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt; // cnt 개수만큼 삭제함
	}
}
