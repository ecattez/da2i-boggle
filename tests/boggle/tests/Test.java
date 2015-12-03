package boggle.tests;

import javax.swing.JFrame;

import boggle.mots.De;
import boggle.mots.GrilleLettres;

public class Test {

	public static void main(String[] args) {
		GrilleLettres g = new GrilleLettres(4, De.creerDes("des-4x4.csv"));
		JFrame f = new JFrame();
		JObserver grid = new JObserver(g);
		g.addObserver(grid);
		f.setContentPane(grid);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		while (true) {
			g.secouer();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
