package boggle.mots;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente une grille de dés.
 */
public abstract class Grille {
	
	public static final int DIMENSION_MIN = 3;
	
	private int dimension;
	
	/**
	 * Constructeur d'une grille
	 * 
	 * @param	dimension
	 * 			la taille de la grille (dimension * dimension)
	 */
	public Grille(int dimension) {
		this.dimension = dimension;
	}
	
	/**
	 * Retourne la dimension de la grille
	 * 
	 * @return	la dimension de la grille
	 */
	public int dimension() {
		return dimension;
	}
	
	/**
	 * Vérifie si les coordonnées (x,y) sont accessibles dans la grille
	 * 
	 * @param	c
	 * 			le couple de coordonnées (x,y)
	 * 
	 * @return	<code>true</code> si le couple (x,y) est contenu dans la grille
	 */
	public boolean contient(Coordonnees c) {
		int x = c.getX();
		int y = c.getY();
		return x >= 0 && x < dimension && y >= 0 && y < dimension;
	}
	
	/**
	 * Place un dé dans la grille
	 * 
	 * @param	d
	 * 			le dé à placer dans la grille
	 * @param	c
	 * 			les coordonnées du dé dans la grille
	 */
	public abstract void placer(De d, Coordonnees c);
	
	/**
	 * Retourne le dé de coordonnées (x,y)
	 * 
	 * @param	c
	 * 			les coordonnées du dé dans la grille
	 * 
	 * @return	une instance de dé
	 */
	public abstract De getDe(Coordonnees c);
	
	/**
	 * Vérifie si l'emplacement (x,y) contient un dé
	 * 
	 * @param	d
	 * 			le dé à placer dans la grille
	 * @param	c
	 * 			les coordonnées du dé dans la grille
	 * 
	 * @return	<code>true</code> s'il y a un dé en (x,y), <code>false</code> sinon
	 */
	public boolean contientDe(Coordonnees c) {
		return getDe(c) != null;
	}
	
	/**
	 * Vérifie si le dé de coordonnées (x,y) est déjà utilisé
	 * 
	 * @param	c
	 * 			les coordonnées du dé dans la ginterfacerille
	 * 
	 * @return	<code>true</code> si le dé est déjà utilisé, <false> sinon
	 */
	public boolean estUtilise(Coordonnees c) {
		return getDe(c).estUtilise();
	}
	
	/**
	 * Retourne la face visible du dé de coordonnées (x,y)
	 * 
	 * @param	c
	 * 			les coordonnées du dé dans la grille
	 * 
	 * @return	la face visible (String) du dé
	 */
	public String getFaceVisible(Coordonnees c) {
		return getDe(c).getFaceVisible();
	}
	
	/**
	 * Retourne un couple de coordonnées aléatoire compris dans la grille
	 * 
	 * @return	une instance de Coordonnees
	 */
	private Coordonnees getCoordonneesAleatoirement() {
		int x = (int) (Math.random() * dimension);
		int y = (int) (Math.random() * dimension);
		return new CoordonneesCartesiennes(x, y);
	}
	
	/**
	 * Secoue la grille pour mélanger les dés
	 */
	public void secouer() {
		int r = (int) (Math.random() + 1000);
		De d1, d2;
		Coordonnees c1, c2;
		for (int i=0; i < r; i++) {
			c1 = getCoordonneesAleatoirement();
			c2 = getCoordonneesAleatoirement();
			d1 = getDe(c1);
			d2 = getDe(c2);
			d1.lancer();
			d2.lancer();
			placer(d2, c1);
			placer(d1, c2);
		}
	}
	
	/**
	 * Vérifie si deux couples de coordonnées sont voisines
	 * 
	 * @param	c1
	 * 			le premier couple de coordonnées
	 * @param	c2
	 * 			le second couple de coordonnées
	 * 
	 * @return	<code>true</code> si les deux coordonnées sont voisines, <code>false</code> sinon
	 */
	public boolean sontVoisins(Coordonnees c1, Coordonnees c2) {
		return c1.estVoisinDe(c2);
	}
	
	/**
	 * Récupère la liste des voisins d'un dé dans la grille à partir de ses coordonnées passé en paramètre
	 * 
	 * @param	c
	 * 			le couple de coordonnées pour lequel il faut trouver les voisins
	 * 
	 * @return	la liste des voisins d'un dé de la grille
	 */
	public List<De> voisins(Coordonnees c) {
		List<De> des = new ArrayList<>();
		Coordonnees tmp;
		for (Coordonnees points : PointCardinal.values()) {
			tmp = c.ajoute(points);
			if (contient(tmp)) {
				des.add(getDe(tmp));
			}
		}
		return des;
	}
	
	/**
	 * Retourne un affichage au format texte de la grille
	 */
	public String toString() {
		String str = "";
		for (int y=0; y < dimension; y++) {
			for (int x=0; x < dimension; x++) {
				str += getDe(new CoordonneesCartesiennes(x, y));
				if (x < dimension - 1) {
					str += "\t";
				}
			}
			str += "\n";
		}
		return str;
	}

}
