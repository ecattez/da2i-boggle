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
package boggle.jeu;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import boggle.mots.Grille;

/**
 * Représentation d'une partie générique de Boggle entre plusieurs joueurs.
 */
public abstract class Partie {
	
	private Map<Joueur, Grille> joueurGrille;
	private Iterator<Joueur> itJoueurs;
	private int tour;
	private int tourMax;
	
	public Partie(Joueur[] joueurs, int tourMax) {
		this.joueurGrille = new HashMap<>();
		this.itJoueurs = new JoueurIterator(joueurs);
		this.tour = 0;
		this.tourMax = tourMax;
	}
	
	public Joueur suivant() {
		return itJoueurs.next();
	}
	
	public Grille getGrille(Joueur j) {
		return joueurGrille.get(j);
	}
	
	public int tour() {
		return tour;
	}
	
	public int tourMax() {
		return tourMax;
	}

	public void resetTour() {
		tour = 0;
	}
	
	public void incTour() {
		tour++;
	}
	
	public abstract void jouer();
	
	public abstract void finTour();
	
	public abstract void finPartie();
	
	
	/**
	 * Implémentation d'un iterateur sur les joueurs.
	 */
	private class JoueurIterator implements Iterator<Joueur> {
		
		private Joueur[] joueurs;
		private int i = -1;
		
		public JoueurIterator(Joueur[] joueurs) {
			this.joueurs = joueurs;
		}
		
		/**
		 * Retourne toujours vrai tant qu'il y a des joueurs dans la partie
		 */
		public boolean hasNext() {
			return joueurs.length > 0;
		}
		
		/**
		 * Retourne le joueur succédant au joueur courant.
		 * Dans le cas du "dernier" joueur, on retourne au premier.
		 */
		public Joueur next() {
			return joueurs[++i % joueurs.length];
		}
		
	}

}
