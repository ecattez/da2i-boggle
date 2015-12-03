package boggle.tests;

import boggle.mots.CoordonneesCartesiennes;
import boggle.mots.De;
import boggle.mots.GrilleLettres;

public class GrilleTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GrilleLettres grille = new GrilleLettres(4, De.creerDes("des-4x4.csv"));
		System.out.println(grille);
		System.out.println(grille.existe("L"));
	}

}
