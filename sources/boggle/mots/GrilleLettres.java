package boggle.mots;

import boggle.BoggleException;


/**
 * Implémente une grille de dés.
 */
public class GrilleLettres implements Grille {
	
	private int dimension;
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
		if (dimension < DIMENSION_MIN) {
			throw new BoggleException("La dimension minimale d'une grille est " + DIMENSION_MIN);
		}
		if (des.length < dimension * dimension) {
			throw new BoggleException("Il n'y a pas suffisamment de dé pour cette grille.");
		}
		
		De.melange(des);
		this.dimension = dimension;
		this.grille = new De[dimension][dimension];	
		int n = 0;
		for (int y=0; y < dimension; y++) {
			for (int x=0; x < dimension; x++) {
				placer(des[n++], x, y);
			}
		}
	}
	
	public void placer(De d, int x, int y) {
		grille[y][x] = d ;
	}

	public De getDe(int x, int y) {
		return grille[y][x];
	}
	
	public boolean contientDe(int x, int y) {
		return getDe(x, y) != null;
	}

	public boolean estUtilise(int x, int y) {
		return getDe(x, y).estUtilise();
	}
	

	public String getFaceVisible(int x, int y) {
		return getDe(x, y).getFaceVisible();
	}

	public void secouer() {
		for(int y=0 ; y<dimension ; y++) {
			for(int x=0 ; x<dimension ; x++) {
				getDe(x, y).lancer();
			}
		}
	}	
	

}
