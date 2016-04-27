package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.MembersDTO;
import dto.MyPageDTO;
import function.DeleteGradedMovie;
import function.GetAnalysis;
import function.SelectGradesByU_ID;
import function.SelectGradesOnMyPage;

public class MyPageFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	String U_ID = "";
	String M_NAME = "";
	MembersDTO member = null;
	JTable table;
	DefaultTableModel defaultTableModel;
	JScrollPane scrollPane;
	MyPageFrame mypage = this;
	String grade = "";
	String director = "";
	String actor = "";
	String genre = "";
	String nation = "";
	String RecommendedMovie = "";
	String totalmovie = "";
	String totaltime = "";
	GetAnalysis getAnalysis = null;
	Calendar calendar = Calendar.getInstance();
	int year = calendar.get(Calendar.YEAR);
	
	JLabel AnalysisLabel_1;
	JLabel AnalysisLabel_2;
	JLabel AnalysisLabel_3;
	JLabel AnalysisLabel_4;
	JLabel AnalysisLabel_5;
	JLabel AnalysisLabel_6;
	JLabel AnalysisLabel_7;
	JLabel AnalysisLabel_8;
	
	

	public MyPageFrame getInstance() {
		return this;
	}

	// 생성자
	public MyPageFrame(MembersDTO member, LoginFrame login) {

		this.member = member;
		this.U_ID = member.getU_ID();

		// JFrame
		this.setTitle("무비무비");
		this.setBounds(20, 20, 1000, 600);
		this.setLayout(new BorderLayout(0, 10));

		JPanel NorthPanel = new JPanel();
		JScrollPane CenterScrollPane = new JScrollPane();
		JPanel SouthPanel = new JPanel();
				
		// NorthPanel: MemberPanel 회원정보(좌), AnalysisPanel 통계정보(우)
		NorthPanel.setLayout(new GridLayout(1, 2));
		JPanel MemberPanel = new JPanel();
		JPanel AnalysisPanel = new JPanel();		

		MemberPanel.setLayout(new GridLayout(4, 1));
		JLabel IDLabel = new JLabel("ID: " + member.getU_ID());
		JLabel NameLabel = new JLabel("이름: " + member.getU_NAME());
		JLabel AgeLabel = new JLabel("연령: " + (year-member.getBIRTHDATE() + 1));		
		JLabel SexLabel = new JLabel("성별: " + member.getSEX());
		MemberPanel.add(IDLabel);
		MemberPanel.add(NameLabel);
		MemberPanel.add(AgeLabel);
		MemberPanel.add(SexLabel);		

		AnalysisPanel.setLayout(new GridLayout(8, 1));
		
		List<MyPageDTO> list_Check = null;
		SelectGradesByU_ID sel = new SelectGradesByU_ID();
		list_Check = sel.list(U_ID);
		
		if (list_Check.isEmpty()) {
		} else {
			insertAnalysis(U_ID);
		}
		
		AnalysisLabel_1 = new JLabel();
		AnalysisLabel_2 = new JLabel();
		AnalysisLabel_3 = new JLabel();
		AnalysisLabel_4 = new JLabel();
		AnalysisLabel_5 = new JLabel();
		AnalysisLabel_6 = new JLabel();
		AnalysisLabel_7 = new JLabel();
		AnalysisLabel_8 = new JLabel();
				
		AnalysisPanel.add(AnalysisLabel_1);
		AnalysisPanel.add(AnalysisLabel_2);
		AnalysisPanel.add(AnalysisLabel_3);
		AnalysisPanel.add(AnalysisLabel_4);
		AnalysisPanel.add(AnalysisLabel_5);
		AnalysisPanel.add(AnalysisLabel_6);
		AnalysisPanel.add(AnalysisLabel_7);
		AnalysisPanel.add(AnalysisLabel_8);

		add(NorthPanel, "North");

		NorthPanel.add(MemberPanel);
		NorthPanel.add(AnalysisPanel);

		// Center
		table = new JTable();
		
		defaultTableModel = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column){  
		          return false;  
		      }			
		};
		
		table.setModel(defaultTableModel);

		table.addMouseListener(new MouseHandler());		
		
		CenterScrollPane.setViewportView(table);
		
		
		this.add(CenterScrollPane, "Center");

		// SouthPanel: '추가' 및 '조회' 버튼
		JButton insert_btn = new JButton("추가");
		JButton select_btn = new JButton("검색");

		insert_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new GradesFrame(member, mypage);
			}
		});

		select_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SelectMenuFrame(mypage);
			}
		});

		SouthPanel.add(insert_btn);
		SouthPanel.add(select_btn);

		this.add(SouthPanel, "South");
		
		// 테이블 셀 선택 불가능하게 하기
		table.setFocusable(false);
		table.setCellSelectionEnabled(false);

		//table.getGraphics();
		
		// tablemodel(테이블 데이터) 생성 및 갱신
		init(member);		
		
		this.setVisible(true);				
	}

	
	// tablemodel(테이블 데이터) 생성 및 갱신
	public void init(MembersDTO member) {
		
		// 리스트 가져오기
		SelectGradesOnMyPage selectMyPage = new SelectGradesOnMyPage();

		List<MyPageDTO> list = selectMyPage.list(member); // id 값에 대한 리스트 가져오기

		String columnNames[] = { "영화명", "장르", "감독", "영화배우", "개봉일", "상영시간", "제작국가", "제작사", "평점", "후기" };

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
		
		String U_ID = member.getU_ID();		
		
		
		
		defaultTableModel.setDataVector(rowData, columnNames);		
		
		getAnalysis = new GetAnalysis(U_ID);

		List<MyPageDTO> list_Check = null;
	      SelectGradesByU_ID sel = new SelectGradesByU_ID();
	      list_Check = sel.list(U_ID);
	      
	      if (list_Check.isEmpty()) {
	      } else {
	         insertAnalysis(U_ID);
	      }
		
		
		AnalysisLabel_1.setText(U_ID + "님의 영화 취향 분석");
		AnalysisLabel_2.setText(totaltime);
		AnalysisLabel_3.setText(totalmovie);
		AnalysisLabel_4.setText(genre);
		AnalysisLabel_5.setText(nation);
		AnalysisLabel_6.setText(actor);
		AnalysisLabel_7.setText(director);
		AnalysisLabel_8.setText(RecommendedMovie);
		
	}
	
	public void insertAnalysis(String u_id) {
		
		getAnalysis = new GetAnalysis(U_ID);

		grade = "평균 평가등급 : " + getAnalysis.getAvggrade(U_ID);
		director = (getAnalysis.getDirector(U_ID).get(0).getName() + " 감독의 영화를 재밌게 보셨네요." + " " + "총 "
				+ getAnalysis.getDirector(U_ID).get(0).getValue() + " 편의 영화를 관람하셨습니다.");

		actor = ("배우 " + getAnalysis.getActor(U_ID).get(0).getName() + " 의 영화를 재밌게 보셨네요." + " " + "총 "
				+ getAnalysis.getActor(U_ID).get(0).getValue() + " 편의 영화를 관람하셨습니다.");
		if(getAnalysis.getRecommendedMovie(U_ID).isEmpty()){
			
		}else{
		RecommendedMovie = (getAnalysis.getRecommendedMovie(U_ID).get(0) + ", 한 번 보시는 건 어때요?");
		}
		genre = (getAnalysis.getGenre(U_ID).get(0).getName() + " 장르의 영화를 재밌게 보셨네요." + "총 " + getAnalysis.getGenre(U_ID).get(0).getValue()
				+ " 편의 영화를 관람하셨습니다.");
		nation = (getAnalysis.getNation(U_ID).get(0).getName() + " 영화를 재밌게 보셨네요." + "총 " + getAnalysis.getNation(U_ID).get(0).getValue()
				+ " 편의 영화를 관람하셨습니다.");
		totalmovie = "평생 " + getAnalysis.getTotalmovie(u_id) + "편의 영화를 보셨습니다.";
		totaltime = "평생 " + getAnalysis.getTotaltime(u_id) + "분 동안 영화를 보셨습니다.";

	}

	class MouseHandler extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			System.out.println("클릭");

			table = (JTable) e.getSource();
			int row = table.getSelectedRow();

			System.out.println("삭제할 테이블 클릭");
			M_NAME = (String) table.getValueAt(row, 0);
			System.out.println(M_NAME);
			System.out.println(U_ID);

			new DeleteMoviePopup();
		}
	}

	class DeleteMoviePopup extends JFrame implements ActionListener {

		private static final long serialVersionUID = 1L;

		public DeleteMoviePopup() {

			setBounds(200, 200, 340, 200);
			this.setLayout(new BorderLayout(0, 10));
			setLayout(null);

			JLabel label = new JLabel("정말 삭제 하시겠습니까?");
			JButton btn1 = new JButton("확인"); // 삭제를 실행해주는 버튼
			JButton btn2 = new JButton("취소"); // 창을 끄는 버튼

			btn1.setBounds(50, 100, 100, 30);
			btn2.setBounds(180, 100, 100, 30);

			label.setBounds(50, 50, 200, 30);

			label.setHorizontalAlignment(JLabel.CENTER);

			add(label, "North");
			add(btn1);
			add(btn2);

			btn1.addActionListener(this);
			btn2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					M_NAME = "";
					dispose();
				}
			});

			setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			int cnt = new DeleteGradedMovie().delete(M_NAME, U_ID);
			System.out.println(cnt + "개 삭제하였음");
			dispose();
			init(member);			
		}

	}

}
