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
		if (des == null) {
			throw new BoggleException("Il n'y a pas aucun dé à placer dans la grille.");
		}
		if (des.length < dimension * dimension) {
			throw new BoggleException("Il n'y a pas suffisamment de dés pour créer la grille.");
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
				des[n].addObserver(this);
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
	
}
