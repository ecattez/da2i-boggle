package boggle.tests;

import javax.swing.JFrame;

import boggle.mots.CoordonneesCartesiennes;
import boggle.mots.De;
import boggle.mots.GrilleLettres;

public class GrilleTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GrilleLettres grille = new GrilleLettres(4, De.creerDes("des-4x4.csv"));
		JObserver obs = new JObserver(grille);
		JFrame f = new JFrame();
		f.setContentPane(obs);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		System.out.println(grille);
		
		char[] lettres = "BOGERENT".toCharArray();
		String mot = "";
		for (char c : lettres) {
			mot += c;
			grille.ecrire(mot);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
