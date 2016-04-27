package gui;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.MoviesDTO;
import dto.MyPageDTO;
import function.SelectGradesByM_NAME;

public class SelectGradesByM_NAME_Frame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	
	String m_name;
	MoviesDTO movieInfo;
	List<MyPageDTO> mlist;

	// DB연결
	public SelectGradesByM_NAME_Frame getInstance() {
		return this;
	}

	public SelectGradesByM_NAME_Frame(String m_name) {
		
		movieInfo = new SelectGradesByM_NAME().movieInfo(m_name);
		mlist = new SelectGradesByM_NAME().mlist(m_name); // id 값에 대한 리스트 가져오기
		if (mlist.isEmpty()) {
			JOptionPane.showMessageDialog(null, "남긴 댓글이 없습니다.");

		} else {
			this.m_name = m_name;
			
			// JFrame 소속 메소드
			setTitle("무비무비");
			setBounds(100, 100, 800, 400);

			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(8,2));
			
			JLabel jLabel = new JLabel(m_name + "에 남겨진 평가입니다.");		
		//	add(jLabel, "North");
			JLabel jLabel_GENRE = new JLabel("장르  :  " + movieInfo.getGENRE());
			JLabel jLabel_DIRECTOR = new JLabel("감독  :  " + movieInfo.getDIRECTOR());
			JLabel jLabel_ACTOR = new JLabel("배우  :  " + movieInfo.getACTOR());
			JLabel jLabel_RELEASE_DATE = new JLabel("개봉일  :  " + movieInfo.getRELEASE_DATE());
			JLabel jLabel_V_TIME = new JLabel("상영시간  :  " + movieInfo.getV_TIME());
			JLabel jLabel_NATION = new JLabel("국가  :  " + movieInfo.getNATION());
			JLabel jLabel_PRODUCTION = new JLabel("제작사  :  " + movieInfo.getPRODUCTION());
			
			
			//M_NAME   GENRE   DIRECTOR   ACTOR   RELEASE_DATE   V_TIME   NATION   PRODUCTION
			
			panel.add(jLabel);
			panel.add(jLabel_GENRE);
			panel.add(jLabel_DIRECTOR);
			panel.add(jLabel_ACTOR);
			panel.add(jLabel_RELEASE_DATE);
			panel.add(jLabel_V_TIME);
			panel.add(jLabel_NATION);
			panel.add(jLabel_PRODUCTION);
		
			add(panel, "North");		
			

			// jScrollpane (표를 담을 공간 스크롤 판넬)
			
			String columnNames[] = { "회원명", "평점", "댓글" };
			if(mlist.isEmpty()){
				this.setVisible(false);
			}
			
			Object[][] rowData = new Object[mlist.size()][3];

			for (int i = 0; i < mlist.size(); i++) {
				MyPageDTO dto = mlist.get(i);
				rowData[i][0] = dto.getMovie_name();
				rowData[i][1] = dto.getGrade();
				rowData[i][2] = dto.getComments();
			}

			// DefaultTableModel을 선언하고 데이터 담기
			DefaultTableModel defaultTableModel = new DefaultTableModel(rowData, columnNames);

			// JTable에 DefaultTableModel을 담기
			JTable jTable = new JTable(defaultTableModel) {
				private static final long serialVersionUID = 1L;
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			
			// 테이블 셀 선택 불가능하게 하기
			jTable.setFocusable(false);
			jTable.setCellSelectionEnabled(false);

			// JScrollPane에 JTable을 담기
			JScrollPane jScrollPane = new JScrollPane(jTable);

		//	this.add(jLabel, "North");
			this.add(jScrollPane, "Center");
			this.setVisible(true);
		}

	}

}