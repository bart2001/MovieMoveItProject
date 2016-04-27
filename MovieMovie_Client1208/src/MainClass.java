import function.ConnectResource;
import function.ListenMusic;
import gui.LoginFrame;

public class MainClass {

	public static void main(String[] args) {
		
		ListenMusic music = new ListenMusic();
		Thread th = new Thread(music);		
		
		new ConnectResource();		
		
		//LoginFrame lf = new LoginFrame(socket);
		new LoginFrame();
		
		// ���������Ҷ�
		th.run();
		th.setDaemon(true);
	}
}
