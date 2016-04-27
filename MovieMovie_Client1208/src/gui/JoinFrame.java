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
	
	// ȸ����ȣ, �̸�, ���̵�, �н�����, ����, ����	
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
	String[] sexchoice = { "��", "��", "��Ÿ" };

	JPanel panel;
	JPanel panel1;

	public JoinFrame getInstance() {
		return this;
	}

	JoinFrame() {
		setTitle("���񹫺�");

		input = new JButton("�Է�");
		back = new JButton("���");

		panel = new JPanel();

		panel.setLayout(null);
		id_lb = new JLabel();
		password_lb = new JLabel();
		name_lb = new JLabel();
		birthdate_lb = new JLabel();
		check_lb = new JLabel();
		sex_lb = new JLabel();
		input = new JButton("�Է�");
		back = new JButton("���");
		password2_lb = new JLabel();
		id = new JTextField(20);
		password = new JPasswordField(20);
		password2 = new JPasswordField(20);
		name = new JTextField(20);
		birthdate = new JTextField(20);
		sex = new JComboBox<String>(sexchoice);
		id_check = new JButton("�ߺ�Ȯ��");

		id_lb.setText("���̵� :");
		password_lb.setText("��й�ȣ :");
		password2_lb.setText("��й�ȣ  Ȯ��:");
		name_lb.setText("�̸� :");
		birthdate_lb.setText("������� :");
		sex_lb.setText("���� :");

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
					JOptionPane.showMessageDialog(null, "�ߺ��˻縦 �ϼ���.");
				}
				passwordCheck = Check_PASSWORD.check(password.getText(), password2.getText());

				if (!passwordCheck) {
					JOptionPane.showMessageDialog(null, "��й�ȣ�� �ٽ� �Է��ϼ���.");
				}

				/*
				 * boolean check = false; check =
				 */
				// ������ ������Ű��
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
				 * }, "������ �����մϴ�."); }else{ JOptionPane.showMessageDialog(new
				 * Component() { }, "������ �̻��� �ֽ��ϴ�."); }
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
				// id�˻�

				idCheck = new Check_U_ID().check(id.getText());
				if (idCheck) {
					check_lb.setText("�ߺ��� �����ϴ�.");
				} else {
					check_lb.setText("�ߺ��� �ֽ��ϴ�.");
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
