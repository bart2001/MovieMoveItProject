package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dto.MembersDTO;
import function.Login;

public class LoginFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	MembersDTO member;
	// ȸ����ȣ, �̸�, ���̵�, �н�����, ����, ����
	JLabel l1, l2, l3;
	JTextField id;
	JButton btn1, btn2;
	JTextField password;
	LoginFrame login = this;
	private String ID;
	public Socket sock;

	public boolean isFirst = false;

	public LoginFrame getInstance() {
		return this;
	}

	public String socket() {
		while (true) {
			InetSocketAddress socketAddress
			// = new InetSocketAddress(IP,port(���� ��Ʈ��ȣ));
			= new InetSocketAddress("211.238.142.168", 9000); // 164�� ��
//			= new InetSocketAddress("127.0.0.1", 9000);
			try {
				@SuppressWarnings("resource")
				Socket socket = new Socket();

				socket.connect(socketAddress, 10000);

				InetAddress inetaddr;
				if ((inetaddr = socket.getInetAddress()) != null) {
					System.out.println("���� : " + inetaddr);
				} else {
					System.out.println("�������");
				}

				String message = member.getU_ID();

				PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

				writer.println(message);
				writer.flush();

				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				// �����͸� �޾��ִ� �κ�
				String line = reader.readLine();// Receive
				if (line.equals("success")) {
					System.out.println("���� : " + inetaddr);
					return "success";

				} else if (line.equals("id")) {
					return "id";
				} else if (line.equals("ip")) {
					return "ip";
				} else {
					// ���� ����
					System.out.println("���ӽ���");
					return "error";
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/*
		 * while (true) { InetSocketAddress socketAddress // = new
		 * InetSocketAddress(IP,port(���� ��Ʈ��ȣ)); = new
		 * InetSocketAddress("211.238.142.164", 9000); // 164�� �� try { Socket
		 * sock = new Socket();
		 * 
		 * sock.connect(socketAddress, 10000);
		 * 
		 * InetAddress inetaddr; if ((inetaddr = sock.getInetAddress()) != null)
		 * { System.out.println("���� : " + inetaddr); } else {
		 * System.out.println("�������"); }
		 * 
		 * BufferedReader reader = new BufferedReader(new
		 * InputStreamReader(sock.getInputStream())); // �����͸� �޾��ִ� �κ� String
		 * message = reader.readLine();// Receive if (message.equals("success"))
		 * { System.out.println("���� : " + inetaddr); return "success";
		 * 
		 * } else { // ���� ���� System.out.println("���ӽ���"); return "error"; }
		 * 
		 * } catch (Exception e) { e.printStackTrace(); } }
		 */
	}

	public LoginFrame() {
		// ����

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("���񹫺�");

		setBounds(300, 300, 550, 300);
		setLayout(null);

		l1 = new JLabel("�α���");

		l2 = new JLabel("ID:");
		l3 = new JLabel("Password:");
		id = new JTextField();
		password = new JPasswordField();
		btn1 = new JButton("�α���");
		btn2 = new JButton("ȸ������");
		l1.setBounds(100, 30, 400, 30);
		l2.setBounds(80, 70, 200, 30);
		l3.setBounds(80, 110, 200, 30);
		id.setBounds(300, 70, 200, 30);
		password.setBounds(300, 110, 200, 30);
		btn1.setBounds(150, 160, 100, 30);
		btn2.setBounds(300, 160, 100, 30);
		add(l1);
		add(l2);
		add(id);
		add(l3);
		add(password);
		add(btn1);
		add(btn2);
		btn1.addActionListener(this);
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new JoinFrame();
			}
		});
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {

		if (id.getText().equals("admin") && password.getText().equals("admin")) {
			new AdminFrame();
		} else {
			member = new Login().login(id.getText(), password.getText());
			String message = socket();
			// ���Ϲ߻�
			System.out.println("���Ϲ߻�");
			System.out.println(message);
			if (message.equals("success")) {
				setID(id.getText());

				// �α���
				System.out.println("�α��οϷ�");

				new MyPageFrame(member, login);
			} else if (message.equals("id")) {
				setID(id.getText());

				// �α���
				System.out.println("id�ߺ�");
				JOptionPane.showMessageDialog(null, "id�ߺ� �α����Դϴ�.");
				new MyPageFrame(member, login);
			} else if (message.equals("ip")) {
				setID(id.getText());

				// �α���
				System.out.println("ip�ߺ�");
				JOptionPane.showMessageDialog(null, "ip�ߺ� �α����Դϴ�.");
				new MyPageFrame(member, login);
			} else {
				JOptionPane.showMessageDialog(null, "�ߺ� �α����Դϴ�.");
			}

		}
		id.setText("");
		password.setText("");

	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public void receive() {

	}

}
