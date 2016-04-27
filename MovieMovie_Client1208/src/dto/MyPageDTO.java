package dto;

public class MyPageDTO {

   private String M_NAME;   
   private double GRADE;
   private String COMMENT;
   
   private String GENRE;
   private String DIRECTOR;
   private String ACTOR;
   private String RELEASE_DATE;
   private int V_TIME;
   private String NATION;
   private String PRODUCTION;   

   
   public String getMovie_name() {
      return M_NAME;
   }

   public void setMovie_name(String movie_name) {
      this.M_NAME = movie_name;
   }

   public double getGrade() {
      return GRADE;
   }

   public void setGrade(double grade) {
      this.GRADE = grade;
   }

   public String getComments() {
      return COMMENT;
   }

   public void setComments(String comments) {
      this.COMMENT = comments;
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