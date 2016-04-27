package gui;
import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import dto.MembersDTO;
import dto.MoviesDTO;
//import function.ConnectResource;
import function.InsertGrade;
import function.SelectMovieNames;

@SuppressWarnings("serial")

public class GradesFrame extends JFrame {

	// 평점 및 영화 null값 체크
	GradesFrame gradeFrame = this;

	boolean m_check = false;
	boolean g_check = false;
	JComboBox<String> g_combobox = new JComboBox<String>();

	JButton InputBtn = null;
	JButton SearchBtn = null;
	JTextField textf = null;
	JTextField searchf = null;
	JLabel label = null;
	JLabel slabel = null;

	JLabel m_name_label = null;
	JLabel genre_label = null;
	JLabel director_label = null;
	JLabel actor_label = null;
	JLabel r_date_label = null;
	JLabel v_time_label = null;
	JLabel nation_label = null;
	JLabel production_label = null;
	JLabel poster_label = null;
	JPanel mlist_panel = null;
	JPanel search_panel = null;
	JPanel grade_panel = null;
	JPanel comment_panel = null;
	JPanel center_panel = null;
	JPanel info_panel = null;
	JPanel draw_panel = null;
	JPanel southPanel = null;
	Container contentpane = null;
	GridBagLayout grid = null;
	String u_id = "";
	Object m_obj[] = null;
	SelectMovieNames mnames = null;

	String text_mname;
	String text_genre;
	String text_director;
	String text_actor;
	String text_rdate;
	int text_vtime;
	String text_nation;
	String text_production;
	Blob poster;
	ImageIcon icon;

	MoviesDTO movie = new MoviesDTO();

	// JList m_list;
	JList<Object> jlist = null;
	List<MoviesDTO> list = null;
	JScrollPane scroll = null;

	String reple;
	MembersDTO member;
	MyPageFrame mypage;
	String m_name = "";
	DefaultListModel<Object> lm;

	int temp = 0;

	public GradesFrame getInstance() {
		return this;
	}

	public GradesFrame(MembersDTO member, MyPageFrame mypage) {

		this.member = member;
		this.u_id = member.getU_ID();

		this.mypage = mypage;

		setTitle("추가/수정");
		setBounds(100, 100, 900, 600);
		contentpane = getContentPane();

		scroll = new JScrollPane();
		lm = new DefaultListModel<Object>();

		g_combobox = new JComboBox<String>();

		InputBtn = new JButton("입력");
		SearchBtn = new JButton("검색");
		textf = new JTextField("", 15);
		searchf = new JTextField("", 15);

		mlist_panel = new JPanel(); 
		search_panel = new JPanel();
		grade_panel = new JPanel();
		comment_panel = new JPanel();
		info_panel = new JPanel(new GridLayout(8, 1));
		draw_panel = new JPanel();
		southPanel = new JPanel();

		southPanel.add(grade_panel);
		southPanel.add(comment_panel);
		
		label = new JLabel("평가");
		slabel = new JLabel("영화명 :");

		// 영화추가
		if (m_name.equals("")) {
			init();
		} else {
			init(m_name);
		}		
		
		// 검색 추가
		search_panel.add(slabel);
		search_panel.add(searchf);
		search_panel.add(SearchBtn);

		// 평점 추가
		g_combobox.addItem("평점");
		g_combobox.addItem("5.0");
		g_combobox.addItem("4.5");
		g_combobox.addItem("4.0");
		g_combobox.addItem("3.5");
		g_combobox.addItem("3.0");
		g_combobox.addItem("2.5");
		g_combobox.addItem("2.0");
		g_combobox.addItem("1.5");
		g_combobox.addItem("1.0");
		g_combobox.addItem("0.5");
		g_combobox.addItem("0.0");

		// 리스트 추가
		mlist_panel.add(scroll);

		// 인포라벨 생성
		m_name_label = new JLabel("영화명 : ");
		genre_label = new JLabel("장르: ");
		director_label = new JLabel("감독 : ");
		actor_label = new JLabel("주연 배우 : ");
		r_date_label = new JLabel("개봉일 : ");
		v_time_label = new JLabel("상영시간 : ");
		nation_label = new JLabel("나라");
		production_label = new JLabel("제작사 : ");
		poster_label = new JLabel();
		// 판넬에 인포 값 넣어주기
		info_panel.add(m_name_label);
		info_panel.add(genre_label);
		info_panel.add(director_label);
		info_panel.add(actor_label);
		info_panel.add(r_date_label);
		info_panel.add(v_time_label);
		info_panel.add(nation_label);
		info_panel.add(production_label);
		//info_panel.add(poster_label);
		info_panel.setSize(30, 15);

		// 코멘트 남기는곳
		southPanel.add(label);
		southPanel.add(g_combobox);
		southPanel.add(textf); // 평가창
		southPanel.add(InputBtn);

		add(search_panel, "North");
		
		
		center_panel = new JPanel();
		center_panel.setLayout(new GridLayout(1, 3, 0, 0));
		
		add(center_panel, "Center");
		center_panel.add(mlist_panel);
		center_panel.add(draw_panel);
		center_panel.add(info_panel);
				
/*		add(mlist_panel, "West");
		add(draw_panel, "Center");
		add(info_panel, "East");*/
		
		
		add(southPanel, "South");

		// 액션 리스너 추가
		jlist.addMouseListener(new MListListener());
		SearchBtn.addActionListener(new SearchBtnLisnter());
		InputBtn.addActionListener(new InputBtnListener());
		
		jlist.setModel(lm);
		scroll.setViewportView(jlist);

		setVisible(true);

	}

	// 전체를 불러오는 메소드
	public void init() {

		mnames = new SelectMovieNames(u_id);

		list = mnames.m_list(u_id);

		m_obj = new Object[list.size()];

		for (int i = 0; i < list.size(); i++) {
			m_obj[i] = list.get(i).getM_NAME();
			lm.addElement(m_obj[i]);
		}
		System.out.println(lm.size());
		// jlist = new JList<Object>(m_obj);
		jlist = new JList<Object>();
		jlist.setModel(lm);
		scroll = new JScrollPane();
		scroll.setViewportView(jlist);

		System.out.println(list.size());

	}

	// 검색시 생성되는 영화리스트
	public void init(String m_name) {
		mnames = new SelectMovieNames(u_id);
		// scroll.remove(jlist);
		list = mnames.m_list2(u_id, m_name);

		System.out.println(m_name);

		lm.removeAllElements();

		m_obj = new Object[list.size()];
		for (int i = 0; i < list.size(); i++) {
			m_obj[i] = list.get(i).getM_NAME();
			lm.add(i, m_obj[i]);
		}

		System.out.println(lm.size());

		// jlist = new JList<Object>();
		

		/*jlist.revalidate();
		scroll.revalidate();

		mlist_panel.revalidate();*/
		
	}

	class MListListener extends MouseAdapter {

		@Override
		public void mousePressed(MouseEvent e) {

			// super.mouseClicked(e); 이거 없어도 상관없음

			System.out.println(jlist.getSelectedValue());
			System.out.println(jlist.getSelectedIndex());
			
			// 클릭한 곳 인덱스 찾자
			int i = jlist.getSelectedIndex();

			// 인덱스 별로 영화정보 넣어주기
			
			text_mname = null;
			text_genre = null;
			text_director = null;
			text_actor = null;
			text_rdate = null;
			text_vtime = 0;
			text_nation = null;
			text_production = null;
			poster = null;			
			
			text_mname = list.get(i).getM_NAME();
			text_genre = list.get(i).getGENRE();
			text_director = list.get(i).getDIRECTOR();
			text_actor = list.get(i).getACTOR();
			text_rdate = list.get(i).getRELEASE_DATE();
			text_vtime = list.get(i).getV_TIME();
			text_nation = list.get(i).getNATION();
			text_production = list.get(i).getPRODUCTION();
			poster = list.get(i).getPoster();
			System.out.println(text_genre);

			m_name_label.setText("영화명 : " + text_mname);
			genre_label.setText("장르 : " + text_genre);
			director_label.setText("감독 : " + text_director);
			actor_label.setText("주연 배우 : " + text_actor);
			r_date_label.setText("개봉일 : " + text_rdate);
			v_time_label.setText("상영시간 : " + Integer.toString(text_vtime));
			nation_label.setText("제작 국가  : " + text_nation);
			production_label.setText("제작사  : " + text_production);
			
			try {
				byte[] allBytesInBlob;
				allBytesInBlob = poster.getBytes(1, (int) poster.length());

				// Image image
				// InputStream is = poster.getBinaryStream();

				// BufferedImage img = is.read(allBytesInBlob);

				//BufferedImage img = ImageIO.read(new ByteArrayInputStream(allBytesInBlob));

				//Graphics2D graphics2d = img.createGraphics();

				// Toolkit toolkit =
				Image ic = Toolkit.getDefaultToolkit().createImage(allBytesInBlob);
				// icon = new ImageIcon(img);

				// paintComponents(img.getGraphics());
				// graphics2d.drawImage(img, 100, 100, 100, 100, (ImageObserver)
				// this);
				poster_label.setVisible(false);
				//System.out.println("이미지 : " + img);
				// poster_label.setIcon((Icon) img);
				icon = new ImageIcon(ic);
				poster_label = new JLabel();				
				poster_label.revalidate();
				
				poster_label.setIcon(icon);
				poster_label.setBounds(0, 0, 100, 100);
				// poster_label.setIcon(icon);
				poster_label.revalidate();
				poster_label.setVisible(true);
				draw_panel.add(poster_label);
				
				draw_panel.revalidate();
				
				
				//gradeFrame.invalidate();
				

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			gradeFrame.revalidate();
		}

	}

	class InputBtnListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			System.out.println("InsertBtn 눌림");

			// 평점 또는 영화명이 선택 안됐을 때
			if (jlist.isSelectionEmpty() == true || g_combobox.getSelectedItem() == "평점") {
				System.out.println("empty true or 평점이 선택됨");
				JOptionPane.showMessageDialog(null, "영화 및 평점을 입력해 주세요", "경고", JOptionPane.WARNING_MESSAGE);
			}

			// 검사 후에 추가할 떄
			if (jlist.isSelectionEmpty() == false && g_combobox.getSelectedItem() != "평점") {
				InsertGrade g = new InsertGrade();
				System.out.println(u_id + jlist.getSelectedValue().toString().trim()
						+ Double.parseDouble(g_combobox.getSelectedItem().toString()) + textf.getText());
				g.grade(u_id, jlist.getSelectedValue().toString().trim(),
						Double.parseDouble(g_combobox.getSelectedItem().toString()), textf.getText());

				textf.setText("");

				// 입력 후 삭제된 칼럼 적용 && 검색 - 입력 후 리스트 새로 업데이트
				
				// gradeFrame 껐다가 다시 생성해주기 (어쩔수 없이...ㅠㅠ)
				gradeFrame.dispose();
				gradeFrame = new GradesFrame(member, mypage);

				/*gradeFrame.setVisible(false);
				gradeFrame = null;
				m_name = "";
				gradeFrame = new GradesFrame(member, mypage);
				gradeFrame.setVisible(true);
				gradeFrame.getInstance().revalidate();*/

				mypage.getInstance().init(member);
				/*mypage.getInstance().defaultTableModel.fireTableChanged(null);
				mypage.getInstance().table.getGraphics();
				mypage.getInstance().setVisible(true);
*/
			}
		}
	}

	class SearchBtnLisnter implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			System.out.println("SearchBtn 눌림");
			String m_name = searchf.getText();
			System.out.println(m_name);

			init(m_name);

			/*
			 * jlist = new JList<Object>(m_obj);
			 * 
			 * 
			 * scroll = null; scroll = new JScrollPane(jlist);
			 * jlist.revalidate(); scroll.revalidate();
			 * 
			 * gradeFrame.setVisible(false); gradeFrame = null; gradeFrame = new
			 * GradesFrame(member, mypage); gradeFrame.setVisible(true);
			 * gradeFrame.getInstance().revalidate();
			 */

		}
	}
}