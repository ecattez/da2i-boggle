package boggle.mots;

/**
 * Représente une grille de dés.
 */
public interface Grille {
	
	int DIMENSION_MIN = 3;
	
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
	 * Secoue la grille pour mélanger les dés
	 */
	public void secouer();

}
