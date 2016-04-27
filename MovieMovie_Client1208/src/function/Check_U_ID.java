package function;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Check_U_ID {
	Connection conn = null;

	public Check_U_ID() {
		conn = ConnectResource.makeConnection();
	}

	public boolean check(String id) {
		boolean check = false;

		try {

			PreparedStatement ps = conn.prepareStatement("select count(*) as cnt from members where u_id=?");
			ps.setString(1, id);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) { // 아이디를 검색해셔 결과값이 있으면
				// cnt의 개수를 파악해서
				if (1 <= rs.getInt("cnt")) {
					check = false; // 이미있음
				} else {
					check = true; // 허용가능
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return check;
	}
}
