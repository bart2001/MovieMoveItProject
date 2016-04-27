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

	// ���� �� ��ȭ null�� üũ
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

		setTitle("�߰�/����");
		setBounds(100, 100, 900, 600);
		contentpane = getContentPane();

		scroll = new JScrollPane();
		lm = new DefaultListModel<Object>();

		g_combobox = new JComboBox<String>();

		InputBtn = new JButton("�Է�");
		SearchBtn = new JButton("�˻�");
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
		
		label = new JLabel("��");
		slabel = new JLabel("��ȭ�� :");

		// ��ȭ�߰�
		if (m_name.equals("")) {
			init();
		} else {
			init(m_name);
		}		
		
		// �˻� �߰�
		search_panel.add(slabel);
		search_panel.add(searchf);
		search_panel.add(SearchBtn);

		// ���� �߰�
		g_combobox.addItem("����");
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

		// ����Ʈ �߰�
		mlist_panel.add(scroll);

		// ������ ����
		m_name_label = new JLabel("��ȭ�� : ");
		genre_label = new JLabel("�帣: ");
		director_label = new JLabel("���� : ");
		actor_label = new JLabel("�ֿ� ��� : ");
		r_date_label = new JLabel("������ : ");
		v_time_label = new JLabel("�󿵽ð� : ");
		nation_label = new JLabel("����");
		production_label = new JLabel("���ۻ� : ");
		poster_label = new JLabel();
		// �ǳڿ� ���� �� �־��ֱ�
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

		// �ڸ�Ʈ ����°�
		southPanel.add(label);
		southPanel.add(g_combobox);
		southPanel.add(textf); // ��â
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

		// �׼� ������ �߰�
		jlist.addMouseListener(new MListListener());
		SearchBtn.addActionListener(new SearchBtnLisnter());
		InputBtn.addActionListener(new InputBtnListener());
		
		jlist.setModel(lm);
		scroll.setViewportView(jlist);

		setVisible(true);

	}

	// ��ü�� �ҷ����� �޼ҵ�
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

	// �˻��� �����Ǵ� ��ȭ����Ʈ
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

			// super.mouseClicked(e); �̰� ��� �������

			System.out.println(jlist.getSelectedValue());
			System.out.println(jlist.getSelectedIndex());
			
			// Ŭ���� �� �ε��� ã��
			int i = jlist.getSelectedIndex();

			// �ε��� ���� ��ȭ���� �־��ֱ�
			
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

			m_name_label.setText("��ȭ�� : " + text_mname);
			genre_label.setText("�帣 : " + text_genre);
			director_label.setText("���� : " + text_director);
			actor_label.setText("�ֿ� ��� : " + text_actor);
			r_date_label.setText("������ : " + text_rdate);
			v_time_label.setText("�󿵽ð� : " + Integer.toString(text_vtime));
			nation_label.setText("���� ����  : " + text_nation);
			production_label.setText("���ۻ�  : " + text_production);
			
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
				//System.out.println("�̹��� : " + img);
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

			System.out.println("InsertBtn ����");

			// ���� �Ǵ� ��ȭ���� ���� �ȵ��� ��
			if (jlist.isSelectionEmpty() == true || g_combobox.getSelectedItem() == "����") {
				System.out.println("empty true or ������ ���õ�");
				JOptionPane.showMessageDialog(null, "��ȭ �� ������ �Է��� �ּ���", "���", JOptionPane.WARNING_MESSAGE);
			}

			// �˻� �Ŀ� �߰��� ��
			if (jlist.isSelectionEmpty() == false && g_combobox.getSelectedItem() != "����") {
				InsertGrade g = new InsertGrade();
				System.out.println(u_id + jlist.getSelectedValue().toString().trim()
						+ Double.parseDouble(g_combobox.getSelectedItem().toString()) + textf.getText());
				g.grade(u_id, jlist.getSelectedValue().toString().trim(),
						Double.parseDouble(g_combobox.getSelectedItem().toString()), textf.getText());

				textf.setText("");

				// �Է� �� ������ Į�� ���� && �˻� - �Է� �� ����Ʈ ���� ������Ʈ
				
				// gradeFrame ���ٰ� �ٽ� �������ֱ� (��¿�� ����...�Ф�)
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

			System.out.println("SearchBtn ����");
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