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
	// 회원번호, 이름, 아이디, 패스워드, 성별, 나이
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
			// = new InetSocketAddress(IP,port(서버 포트번호));
			= new InetSocketAddress("211.238.142.168", 9000); // 164가 나
//			= new InetSocketAddress("127.0.0.1", 9000);
			try {
				@SuppressWarnings("resource")
				Socket socket = new Socket();

				socket.connect(socketAddress, 10000);

				InetAddress inetaddr;
				if ((inetaddr = socket.getInetAddress()) != null) {
					System.out.println("연결 : " + inetaddr);
				} else {
					System.out.println("연결실패");
				}

				String message = member.getU_ID();

				PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

				writer.println(message);
				writer.flush();

				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				// 데이터를 받아주는 부분
				String line = reader.readLine();// Receive
				if (line.equals("success")) {
					System.out.println("접속 : " + inetaddr);
					return "success";

				} else if (line.equals("id")) {
					return "id";
				} else if (line.equals("ip")) {
					return "ip";
				} else {
					// 접속 실패
					System.out.println("접속실패");
					return "error";
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/*
		 * while (true) { InetSocketAddress socketAddress // = new
		 * InetSocketAddress(IP,port(서버 포트번호)); = new
		 * InetSocketAddress("211.238.142.164", 9000); // 164가 나 try { Socket
		 * sock = new Socket();
		 * 
		 * sock.connect(socketAddress, 10000);
		 * 
		 * InetAddress inetaddr; if ((inetaddr = sock.getInetAddress()) != null)
		 * { System.out.println("연결 : " + inetaddr); } else {
		 * System.out.println("연결실패"); }
		 * 
		 * BufferedReader reader = new BufferedReader(new
		 * InputStreamReader(sock.getInputStream())); // 데이터를 받아주는 부분 String
		 * message = reader.readLine();// Receive if (message.equals("success"))
		 * { System.out.println("접속 : " + inetaddr); return "success";
		 * 
		 * } else { // 접속 실패 System.out.println("접속실패"); return "error"; }
		 * 
		 * } catch (Exception e) { e.printStackTrace(); } }
		 */
	}

	public LoginFrame() {
		// 소켓

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("무비무비");

		setBounds(300, 300, 550, 300);
		setLayout(null);

		l1 = new JLabel("로그인");

		l2 = new JLabel("ID:");
		l3 = new JLabel("Password:");
		id = new JTextField();
		password = new JPasswordField();
		btn1 = new JButton("로그인");
		btn2 = new JButton("회원가입");
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
			// 소켓발생
			System.out.println("소켓발생");
			System.out.println(message);
			if (message.equals("success")) {
				setID(id.getText());

				// 로그인
				System.out.println("로그인완료");

				new MyPageFrame(member, login);
			} else if (message.equals("id")) {
				setID(id.getText());

				// 로그인
				System.out.println("id중복");
				JOptionPane.showMessageDialog(null, "id중복 로그인입니다.");
				new MyPageFrame(member, login);
			} else if (message.equals("ip")) {
				setID(id.getText());

				// 로그인
				System.out.println("ip중복");
				JOptionPane.showMessageDialog(null, "ip중복 로그인입니다.");
				new MyPageFrame(member, login);
			} else {
				JOptionPane.showMessageDialog(null, "중복 로그인입니다.");
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
