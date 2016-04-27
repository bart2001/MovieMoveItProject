package function;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class ListenMusic implements Runnable {

	int a[] = new int[5];

	public int[] random(int[] a) {

		int r_num[] = new int[a.length];// 카드의 숫자
		boolean swit[] = new boolean[a.length]; // 카드의 범위
		int w, r;
		for (int i = 0; i < swit.length; i++) { // false로 초기화
			swit[i] = false;
		}

		w = 0;
		while (w < r_num.length) {
			r = (int) (Math.random() * 3) % 3; // 랜덤함수
			if (!swit[r]) {
				swit[r] = true; // 랜덤함수에서 나온 숫자만 true로 한다.
				r_num[w] = r; // r_num[0]부터 랜덤숫자+1을 해준다.
				w++;
			}
		}
		return r_num;

	}

	@SuppressWarnings({ "deprecation", "static-access" })
	@Override
	public void run() {
		try {
			Player p[] = new Player[3];
			int rand[] = new int[3];
			rand = random(rand);
			Thread th = null;

			for (int i = 0; i < 3; i++) {

				th = new Thread();
				th.run();
				th.sleep(2000);
				th.stop();

				p[i] = new Player(new FileInputStream("sound" + rand[i] + ".mp3"));
				p[i].play();

				if (th.isAlive() == false) {
					p[i].close();
				}
				if (i == 2) {
					i = 0;
				}
			}

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JavaLayerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
