package function;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectResource {
	static Connection conn = null;

	public ConnectResource() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver Loading Success!!");

			System.out.println("DB Connection Success!!");
			try {
				conn = DriverManager.getConnection("jdbc:oracle:thin:@211.238.142.168:1521:xe", "hr", "hr");
//				conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "hr", "hr");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static Connection makeConnection() {
		return conn;
	}
}
