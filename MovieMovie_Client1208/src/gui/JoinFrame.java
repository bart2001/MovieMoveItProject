package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dto.MembersDTO;
import function.InsertMember;
import function.Check_U_ID;
import function.Check_PASSWORD;

public class JoinFrame extends JFrame implements ActionListener {
	
	// 회원번호, 이름, 아이디, 패스워드, 성별, 나이	
	private static final long serialVersionUID = 1L;
	boolean isFirst = true;
	boolean idCheck = false;
	boolean passwordCheck = false;
	GridLayout gd = null;
	JButton id_check = null;
	JButton input = null;
	JButton back = null;
	JLabel id_lb = null;
	JLabel check_lb = null;
	JLabel password_lb = null;
	JLabel password2_lb = null;
	JLabel name_lb = null;
	JLabel birthdate_lb = null;
	JLabel sex_lb = null;

	JTextField id = null;
	JTextField password = null;
	JTextField password2 = null;
	JTextField name = null;
	JTextField birthdate = null;
	JComboBox<String> sex = null;
	String[] sexchoice = { "남", "여", "기타" };

	JPanel panel;
	JPanel panel1;

	public JoinFrame getInstance() {
		return this;
	}

	JoinFrame() {
		setTitle("무비무비");

		input = new JButton("입력");
		back = new JButton("취소");

		panel = new JPanel();

		panel.setLayout(null);
		id_lb = new JLabel();
		password_lb = new JLabel();
		name_lb = new JLabel();
		birthdate_lb = new JLabel();
		check_lb = new JLabel();
		sex_lb = new JLabel();
		input = new JButton("입력");
		back = new JButton("취소");
		password2_lb = new JLabel();
		id = new JTextField(20);
		password = new JPasswordField(20);
		password2 = new JPasswordField(20);
		name = new JTextField(20);
		birthdate = new JTextField(20);
		sex = new JComboBox<String>(sexchoice);
		id_check = new JButton("중복확인");

		id_lb.setText("아이디 :");
		password_lb.setText("비밀번호 :");
		password2_lb.setText("비밀번호  확인:");
		name_lb.setText("이름 :");
		birthdate_lb.setText("생년월일 :");
		sex_lb.setText("성별 :");

		id_lb.setBounds(20, 0, 100, 50);
		check_lb.setBounds(120, 40, 100, 50);
		password_lb.setBounds(20, 70, 100, 50);
		password2_lb.setBounds(20, 140, 100, 50);
		name_lb.setBounds(20, 210, 100, 50);
		birthdate_lb.setBounds(20, 280, 100, 50);
		sex_lb.setBounds(20, 350, 100, 50);

		id.setBounds(110, 20, 200, 30);
		id_check.setBounds(310, 20, 90, 30);
		password.setBounds(110, 90, 200, 30);
		password2.setBounds(110, 160, 200, 30);
		name.setBounds(110, 230, 200, 30);
		birthdate.setBounds(110, 300, 200, 30);
		sex.setBounds(110, 370, 200, 30);
		input.setBounds(110, 420, 80, 30);
		back.setBounds(210, 420, 80, 30);

		// panel.setLayout(gd);

		panel.add(id_lb);
		panel.add(id);
		panel.add(id_check);
		panel.add(check_lb);
		panel.add(password_lb);
		panel.add(password);
		panel.add(password2_lb);
		panel.add(password2);
		panel.add(name_lb);
		panel.add(name);
		panel.add(birthdate_lb);
		panel.add(birthdate);
		panel.add(sex_lb);
		panel.add(sex);
		panel.add(back);
		panel.add(input);

		input.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!idCheck) {
					JOptionPane.showMessageDialog(null, "중복검사를 하세요.");
				}
				passwordCheck = Check_PASSWORD.check(password.getText(), password2.getText());

				if (!passwordCheck) {
					JOptionPane.showMessageDialog(null, "비밀번호를 다시 입력하세요.");
				}

				/*
				 * boolean check = false; check =
				 */
				// 조건을 만족시키면
				if ((idCheck && passwordCheck) && (!name.equals("")) && (!birthdate.equals("")) && (!sex.equals(""))) {
					MembersDTO member = new MembersDTO();
					member.setU_ID(id.getText());
					member.setU_NAME(name.getText());
					member.setBIRTHDATE(Integer.parseInt(birthdate.getText()));
					member.setPASSWORD(password.getText());
					member.setSEX(sex.getSelectedItem().toString());
					new InsertMember().join(member);
					setVisible(false);
				}
				/*
				 * if(idCheck){ JOptionPane.showMessageDialog(new Component() {
				 * }, "가입을 축하합니다."); }else{ JOptionPane.showMessageDialog(new
				 * Component() { }, "정보에 이상이 있습니다."); }
				 */
			}
		});
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
			}
		});
		id_check.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// id검사

				idCheck = new Check_U_ID().check(id.getText());
				if (idCheck) {
					check_lb.setText("중복이 없습니다.");
				} else {
					check_lb.setText("중복이 있습니다.");
				}
			}
		});

		add(panel);

		 setBounds(300, 300, 440, 520);
		update(getGraphics());
		repaint();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
	}

}
