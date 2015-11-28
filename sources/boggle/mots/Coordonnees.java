/**
 * This file is part of da2i-boggle.
 *
 * da2i-boggle is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * da2i-boggle is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.				 
 * 
 * You should have received a copy of the GNU General Public License
 * along with da2i-boggle.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * @author Edouard CATTEZ <edouard.cattez@sfr.fr> (La 7 Production)
 */
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
