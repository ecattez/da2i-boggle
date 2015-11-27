package boggle.mots;

import boggle.BoggleException;

/**
 * Implémente une grille de dés.
 */
public class GrilleLettres extends Grille {
	
	private De[][] grille;
	
	/**
	 * Constructeur pour instancier une grille de lettres
	 * 
	 * @param	dimension
	 * 			la taille de la grille (dimension x dimension)
	 * @param	des
	 * 			une liste de dés (ordonnée ou non) à ajouter à la grille
	 * 
	 * Note:
	 * On ajoute uniquement le nombre de dés qui peuvent être ajoutés dans la grille
	 * S'il y a moins de dé qu'il n'en faut dans la grille, on retourne une erreur
	 */
	public GrilleLettres(int dimension, De[] des) {
		super(dimension);
		if (dimension < DIMENSION_MIN) {
			throw new BoggleException("La dimension minimale d'une grille est " + DIMENSION_MIN);
		}
		if (des == null) {
			throw new BoggleException("Il n'y a pas aucun dé à placer dans la grille.");
		}
		if (des.length < dimension * dimension) {
			throw new BoggleException("Il n'y a pas suffisamment de dé pour cette grille.");
		}
		
		this.grille = new De[dimension][dimension];
		this.initialiser(des);
		this.secouer();
	}
	
	/**
	 * Initialise la grille avec un tableau de dés
	 * 
	 * @param	des
	 * 			le tableau de dé permettant d'initialiser la grille
	 */
	private void initialiser(De[] des) {
		De.melange(des);
		int n = 0;
		for (int y=0; y < dimension(); y++) {
			for (int x=0; x < dimension(); x++) {
				placer(des[n++], new CoordonneesCartesiennes(x, y));
			}
		}
	}
	
	public void placer(De de, Coordonnees c) {
		grille[c.getY()][c.getX()] = de;
	}
	
	public De getDe(Coordonnees c) {
		return grille[c.getY()][c.getX()];
	}
	
	public static void main(String[] args) {
		GrilleLettres g = new GrilleLettres(4, De.creerDes("des-4x4.csv"));
		System.out.println(g);
		System.out.println("On secoue...");
		g.secouer();
		System.out.println();
		System.out.println(g);
	}
	
}
