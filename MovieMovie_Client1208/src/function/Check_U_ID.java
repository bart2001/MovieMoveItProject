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
			if (rs.next()) { // ���̵� �˻��ؼ� ������� ������
				// cnt�� ������ �ľ��ؼ�
				if (1 <= rs.getInt("cnt")) {
					check = false; // �̹�����
				} else {
					check = true; // ��밡��
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return check;
	}
}
