package boggle.mots;

import java.util.List;


/**
 * Représente une grille de dés.
 */
public interface Grille {
	
	int DIMENSION_MIN = 3;
	
	/**
	 * Retourne la dimension de la grille
	 * 
	 * @return	la dimension de la grille
	 */
	public int dimension();
	
	/**
	 * Vérifie si les coordonnées (x,y) sont accessibles dans la grille
	 * 
	 * @param	x
	 * 			l'abscisse
	 * @param	y
	 * 			l'ordonnée
	 * 
	 * @return	<code>true</code> si la case (x,y) est contenu dans la grille
	 */
	public boolean contient(int x, int y);
	
	/**
	 * Vérifie si les coordonnées (x,y) sont accessibles dans la grille
	 * 
	 * @param	c
	 * 			le couple de coordonnées (x,y)
	 * 
	 * @return	<code>true</code> si le couple (x,y) est contenu dans la grille
	 */
	public boolean contient(Coordonnees c);
	
	/**
	 * Place un dé dans la grille
	 * 
	 * @param	d
	 * 			le dé à placer dans la grille
	 * @param	x
	 * 			l'abscisse du dé dans la grille
	 * @param	y
	 * 			l'ordonnée du dé dans la grille
	 */
	public void placer(De d, int x , int y);
	
	/**
	 * Place un dé dans la grille
	 * 
	 * @param	d
	 * 			le dé à placer dans la grille
	 * @param	c
	 * 			les coordonnées du dé dans la grille
	 */
	public void placer(De d, Coordonnees c);
	
	/**
	 * Retourne le dé de coordonnées (x,y)
	 * 
	 * @param	x
	 * 			l'abscisse du dé dans la grille
	 * @param	y
	 * 			l'ordonnée du dé dans la grille
	 * 
	 * @return	une instance de dé
	 */
	public De getDe(int x, int y);
	
	/**
	 * Retourne le dé de coordonnées (x,y)
	 * 
	 * @param	c
	 * 			les coordonnées du dé dans la grille
	 * 
	 * @return	une instance de dé
	 */
	public De getDe(Coordonnees c);
	
	/**
	 * Vérifie si l'emplacement (x,y) contient un dé
	 * 
	 * @param	d
	 * 			le dé à placer dans la grille
	 * @param	x
	 * 			l'abscisse du dé dans la grille
	 * @param	y
	 * 			l'ordonnée du dé dans la grille
	 * 
	 * @return	<code>true</code> s'il y a un dé en (x,y), <code>false</code> sinon
	 */
	public boolean contientDe(int x, int y);
	
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
	public boolean contientDe(Coordonnees c);
	
	/**
	 * Vérifie si le dé de coordonnées (x,y) est déjà utilisé
	 * 
	 * @param	x
	 * 			l'abscisse du dé dans la grille
	 * @param	y
	 * 			l'ordonnée du dé dans la grille
	 * 
	 * @return	<code>true</code> si le dé est déjà utilisé, <false> sinon
	 */
	public boolean estUtilise(int x, int y);
	
	/**
	 * Vérifie si le dé de coordonnées (x,y) est déjà utilisé
	 * 
	 * @param	c
	 * 			les coordonnées du dé dans la grille
	 * 
	 * @return	<code>true</code> si le dé est déjà utilisé, <false> sinon
	 */
	public boolean estUtilise(Coordonnees c);
	
	/**
	 * Retourne la face visible du dé de coordonnées (x,y)
	 * 
	 * @param	x
	 * 			l'abscisse du dé dans la grille
	 * @param	y
	 * 			l'ordonnée du dé dans la grille
	 * 
	 * @return	la face visible (String) du dé
	 */
	public String getFaceVisible(int x, int y);
	
	/**
	 * Retourne la face visible du dé de coordonnées (x,y)
	 * 
	 * @param	c
	 * 			les coordonnées du dé dans la grille
	 * 
	 * @return	la face visible (String) du dé
	 */
	public String getFaceVisible(Coordonnees c);
	
	/**
	 * Secoue la grille pour mélanger les dés
	 */
	public void secouer();
	
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
	public boolean sontVoisins(Coordonnees c1, Coordonnees c2);
	
	/**
	 * Récupère la liste des voisins du couple de coordonnées passé en paramètre
	 * 
	 * @param	x
	 * 			l'abscisse du dé dans la grille
	 * @param	y
	 * 			l'ordonnée du dé dans la grille
	 * 
	 * @return	la liste des voisins d'un dé dans la grille
	 */
	public List<De> voisins(int x, int y);
	
	/**
	 * Récupère la liste des voisins d'un dé dans la grille à partir de ses coordonnées passé en paramètre
	 * 
	 * @param	c
	 * 			le couple de coordonnées pour lequel il faut trouver les voisins
	 * 
	 * @return	la liste des voisins d'un dé de la grille
	 */
	public List<De> voisins(Coordonnees c);

}
