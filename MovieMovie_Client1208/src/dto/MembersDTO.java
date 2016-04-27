package dto;

public class MembersDTO {

	private String U_ID;
	private String PASSWORD;
	private String U_NAME;
	private int BIRTHDATE;
	private String SEX;

	// GETTER & SETTER
	public String getU_ID() {
		return U_ID;
	}

	public void setU_ID(String u_ID) {
		this.U_ID = u_ID;
	}

	public String getPASSWORD() {
		return PASSWORD;
	}

	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}

	public String getU_NAME() {
		return U_NAME;
	}

	public void setU_NAME(String u_NAME) {
		U_NAME = u_NAME;
	}

	public int getBIRTHDATE() {
		return BIRTHDATE;
	}

	public void setBIRTHDATE(int bIRTHDATE) {
		BIRTHDATE = bIRTHDATE;
	}

	public String getSEX() {
		return SEX;
	}

	public void setSEX(String sEX) {
		SEX = sEX;
	}

}
