package boggle.tests;

import boggle.jeu.Humain;
import boggle.jeu.Joueur;
import boggle.jeu.Partie;
import boggle.mots.ArbreLexical;
import boggle.mots.De;
import boggle.mots.GrilleLettres;

public class TestPartie {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GrilleLettres grille = new GrilleLettres(4, De.creerDes("des-4x4.csv"));
		ArbreLexical arbre = ArbreLexical.lireMots("dict-fr.txt");
		Joueur[] joueurs = { new Humain("Edouard") };
		
		Partie partie = new Partie(grille, arbre, joueurs);
		new Thread(partie).run();

	}

}
