package function;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

import dto.MembersDTO;

public class Login {
	MembersDTO member = null;
	public Login() {
		
	}
	public MembersDTO login(String id, String password){

		try {
			String sql = "select * from members where u_id='"+id+"' and password='"+password+"'";
			Connection con = ConnectResource.makeConnection();
			Statement ps = con.prepareStatement(sql);			
			ResultSet rs = ps.executeQuery(sql);
			if (rs.next()) {
				member = new MembersDTO();
				member.setU_ID(id); 
				member.setPASSWORD(password); 
				member.setU_NAME(rs.getString("U_NAME")); 
				member.setBIRTHDATE(rs.getInt("BIRTHDATE")); 
				member.setSEX(rs.getString("SEX"));		
				
			} else {
				JOptionPane.showMessageDialog(null, "Incorrect Id or password..Try Again with correct detail");
				System.out.println(id);
				System.out.println(password);
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return member;
	}
}
