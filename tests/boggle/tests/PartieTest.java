package boggle.tests;

import javax.swing.JFrame;
import javax.swing.JPanel;

import boggle.gui.partie.JPartie;
import boggle.jeu.Partie;
import boggle.jeu.Regles;
import boggle.jeu.joueur.IAHardcore;
import boggle.jeu.joueur.Joueur;

public class PartieTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Joueur[] joueur = { new IAHardcore("Edouard") };
		Regles regles = Regles.chargerRegles("config/regles-5x5.config");
		final Partie partie = new Partie(regles, joueur);
		JPanel container = new JPartie(partie);
		
		JFrame f = new JFrame();
		f.setContentPane(container);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		new Thread(partie).start();
	}

}
