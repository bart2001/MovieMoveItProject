package function;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.MoviesDTO;
import dto.MyPageDTO;

public class SelectGradesByM_NAME {

   MoviesDTO moviesDTO = null;
   List<MyPageDTO> mlist = null;
   public SelectGradesByM_NAME() { // 생성자가 만들어지면 연결되도록
   mlist = new ArrayList<MyPageDTO>();    
   }
   
/*   public String abc (int a){	 // 리턴을 꼭!
	   return "abc";
   } */ 
   
   public MoviesDTO movieInfo(String m_name) {   
	   
      Connection conn = null;
      Statement stmt = null;
      ResultSet rs = null;

      conn = ConnectResource.makeConnection(); // DB에 연결
      
      String sql = " SELECT * FROM MOVIES where m_name = '" + m_name + "' ";     
      
      try {
         stmt = conn.createStatement();
         rs = stmt.executeQuery(sql);
         if (rs == null) {
            System.out.println("rs가 비어있음");
         }      
         if  (rs.next()){
	        moviesDTO = new MoviesDTO(); 
	        moviesDTO.setM_NAME(rs.getString("M_NAME"));
	        moviesDTO.setGENRE(rs.getString("GENRE"));
	        moviesDTO.setDIRECTOR(rs.getString("DIRECTOR"));
	        moviesDTO.setACTOR(rs.getString("ACTOR"));
	        moviesDTO.setRELEASE_DATE(rs.getString("RELEASE_DATE"));
	        moviesDTO.setV_TIME(rs.getInt("V_TIME"));
	        moviesDTO.setNATION(rs.getString("NATION"));
	        moviesDTO.setPRODUCTION(rs.getString("PRODUCTION"));
                      
            System.out.println(rs.getString("GENRE"));
            System.out.println(rs.getString("DIRECTOR"));
            System.out.println(rs.getString("ACTOR"));
            System.out.println(rs.getString("RELEASE_DATE"));
            System.out.println(rs.getInt("V_TIME"));
            System.out.println(rs.getString("NATION"));
            System.out.println(rs.getString("PRODUCTION"));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }         
      return moviesDTO;
   }
   
   
   
   // 선택받은 영화정보를 담을 리스트를 만듣 메소드
   public List<MyPageDTO> mlist(String m_name) {
            
      Connection conn = null;
      Statement stmt = null;
      ResultSet rs = null;

      conn = ConnectResource.makeConnection(); // DB에 연결

      String sql = "SELECT  ME.U_ID, G.GRADE, G.COMMENTS  FROM GRADE G, MEMBERS ME, MOVIES M WHERE G.U_ID = ME.U_ID  AND G.M_ID = M.M_ID AND M_NAME = '"
            + m_name + "'";

      try {
         stmt = conn.createStatement();
         rs = stmt.executeQuery(sql);
         if (rs == null) {
            System.out.println("rs가 비어있음");
         }

         while (rs.next()) {
            MyPageDTO mmdto = new MyPageDTO();

            mmdto.setMovie_name(rs.getString("U_ID"));
            mmdto.setGrade(rs.getDouble("GRADE"));
            mmdto.setComments(rs.getString("COMMENTS"));
            System.out.println(rs.getString("U_ID"));
            System.out.println(rs.getDouble("GRADE"));
            System.out.println(rs.getString("COMMENTS"));

            mlist.add(mmdto);
         }

      } catch (SQLException e) {
         e.printStackTrace();
      }

      return mlist;
   }
}