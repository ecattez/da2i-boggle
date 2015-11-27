package boggle.mots;

/**
 * Représente des coordonnées dans un repère à 2 dimensions.
 */
public interface Coordonnees {
	
	/**
	 * Retourne l'abscisse du couple de coordonnées (x,y)
	 * 
	 * @return l'abscisse de l'instance de Coordonnees courante
	 */
	int getX();
	
	/**
	 * Retourne l'ordonnée du couple de coordonnées (x,y)
	 * 
	 * @return l'ordonnée de l'instance de Coordonnees courante
	 */
	int getY();
	
	/**
	 * Additionne un couple de coordonnées et l'instance courante
	 * 
	 * @param	c
	 * 			le couple a additionner avec l'instance courante
	 * 
	 * @return	une nouvelle instance de Coordonnees représentant l'ajout des coordonnées entres-elles
	 */
	Coordonnees ajoute(Coordonnees c);
	
	/**
	 * Vérifie si deux coordonnées sont voisines
	 * 
	 * @param	c
	 * 			la coordonnées pour la comparaison
	 * 
	 * @return	<code>true</code> si les coordonnées sont voisines, <code>false</code> sinon
	 */
	boolean estVoisinDe(Coordonnees c);

}
