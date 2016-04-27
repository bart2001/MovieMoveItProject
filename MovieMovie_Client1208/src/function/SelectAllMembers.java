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

	// �����ڰ� ��������� ����ǵ���
	public SelectAllMembers() {

		conn = ConnectResource.makeConnection();
	}

	// ���ù��� ��ȭ������ ���� ����Ʈ�� ���� �޼ҵ�
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
