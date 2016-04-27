package gui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.MyPageDTO;
import function.SelectGradesByU_ID;

public class SelectGradesByU_ID_Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	List<MyPageDTO> list;

	public SelectGradesByU_ID_Frame getInstance() {
		return this;
	}

	public SelectGradesByU_ID_Frame(String id) {

		// JFrame 소속 메소드
		this.setTitle("무비무비");
		this.setBounds(100, 100, 800, 400);
		// 종료버튼을 누르면 닫을 수 있도록 함
		this.setLayout(new BorderLayout(0, 10));

		JLabel jLabel = new JLabel(id + "님이 본 영화입니다.");
		jLabel.setHorizontalAlignment(JLabel.CENTER);

		// jScrollpane (표를 담을 공간 스크롤 판넬)
		String columnNames[] = { "영화명", "장르", "감독", "영화배우", "개봉일", "상영시간", "제작국가", "제작사", "평점", "후기" };

		list = new SelectGradesByU_ID().list(id); // id 값에 대한 리스트 가져오기
		if(list.isEmpty()){
			this.setVisible(false);
		}
	    
		Object[][] rowData = new Object[list.size()][10];

		for (int i = 0; i < list.size(); i++) {
			
			MyPageDTO dto = list.get(i);
			
	         rowData[i][0] = dto.getMovie_name();
	         rowData[i][1] = dto.getGENRE();
	         rowData[i][2] = dto.getDIRECTOR();
	         rowData[i][3] = dto.getACTOR();
	         rowData[i][4] = dto.getRELEASE_DATE();
	         rowData[i][5] = dto.getV_TIME();
	         rowData[i][6] = dto.getNATION();
	         rowData[i][7] = dto.getPRODUCTION();
	         rowData[i][8] = dto.getGrade();
	         rowData[i][9] = dto.getComments();         
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

		this.add(jLabel, "North");
		this.add(jScrollPane, "Center");
		this.setVisible(true);
	}

}