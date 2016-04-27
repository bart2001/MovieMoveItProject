package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SelectMenuFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	MyPageFrame mypage = null;
	String U_ID = "";
	JTextArea area;
	JTextField textf;
	String search = "";
	String[] ch_item = { "검색조건", "영화명", "회원명" };
	SelectGradesByM_NAME_Frame smFrame;
	SelectGradesByU_ID_Frame snFrame;
	JComboBox<String> Box = new JComboBox<String>(ch_item);

	public SelectMenuFrame getInstance() {
		return this;
	}

	public SelectMenuFrame(MyPageFrame mypage) {

		setTitle("무비무비");
		setBounds(100, 100, 500, 300);
		setVisible(true);

		// Panel
		JPanel sPanel = new JPanel();
		JPanel sPanel2 = new JPanel();

		// ComboBox

		// textField
		textf = new JTextField(10);

		// Button
		JButton btn1 = new JButton("검색");
		btn1.addActionListener(new EventAction());

		sPanel.add(Box);
		sPanel2.add(textf);
		sPanel2.add(btn1);

		Container contentPane = getContentPane();

		contentPane.add(sPanel, BorderLayout.NORTH);
		contentPane.add(sPanel2, BorderLayout.SOUTH);
		this.mypage = mypage;

	}

	class EventAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			search = textf.getText();
			if (Box.getSelectedItem().toString().equals("영화명") && (!search.equals(""))) {
				smFrame = new SelectGradesByM_NAME_Frame(search);

			} else if (Box.getSelectedItem().toString().equals("회원명") && (!search.equals(""))) {
				snFrame = new SelectGradesByU_ID_Frame(search);

			} else {
				JOptionPane.showMessageDialog(null, "검색 대상을 설정해주세요");
				if (search.equals("")) {
					JOptionPane.showMessageDialog(null, "검색어를 입력해주세요. ");
				}
			}

		}
	}

}
