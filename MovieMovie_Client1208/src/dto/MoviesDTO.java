package dto;

import java.sql.Blob;

public class MoviesDTO {

	private String M_ID;
	private String M_NAME;
	private String GENRE;
	private String DIRECTOR;
	private String ACTOR;
	private String RELEASE_DATE;
	private int V_TIME;
	private String NATION;
	private String PRODUCTION;
	private Blob poster;

	public Blob getPoster() {
		return poster;
	}

	public void setPoster(Blob blob) {
		this.poster = blob;
	}

	// Getter & Setter
	public String getM_ID() {
		return M_ID;
	}

	public void setM_ID(String m_ID) {
		M_ID = m_ID;
	}

	public String getM_NAME() {
		return M_NAME;
	}

	public void setM_NAME(String m_NAME) {
		M_NAME = m_NAME;
	}

	public String getGENRE() {
		return GENRE;
	}

	public void setGENRE(String gENRE) {
		GENRE = gENRE;
	}

	public String getDIRECTOR() {
		return DIRECTOR;
	}

	public void setDIRECTOR(String dIRECTOR) {
		DIRECTOR = dIRECTOR;
	}

	public String getACTOR() {
		return ACTOR;
	}

	public void setACTOR(String aCTOR) {
		ACTOR = aCTOR;
	}

	public String getRELEASE_DATE() {
		return RELEASE_DATE;
	}

	public void setRELEASE_DATE(String rELEASE_DATE) {
		RELEASE_DATE = rELEASE_DATE;
	}

	public int getV_TIME() {
		return V_TIME;
	}

	public void setV_TIME(int v_TIME) {
		V_TIME = v_TIME;
	}

	public String getNATION() {
		return NATION;
	}

	public void setNATION(String nATION) {
		NATION = nATION;
	}

	public String getPRODUCTION() {
		return PRODUCTION;
	}

	public void setPRODUCTION(String pRODUCTION) {
		PRODUCTION = pRODUCTION;
	}

}
