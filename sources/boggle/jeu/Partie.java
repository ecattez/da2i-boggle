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

import java.util.Iterator;

import boggle.mots.ArbreLexical;
import boggle.mots.GrilleLettres;

/**
 * Représentation d'une partie générique de Boggle entre plusieurs joueurs.
 */
public class Partie implements Iterable<Joueur>, Runnable {
	
	public static final int DEFAULT_CHRONO = 60;
	public static final int DEFAULT_SCORECIBLE = 30;
	public static final int DEFAULT_TOURMAX = 5;
	
	private GrilleLettres grille;
	private ArbreLexical arbre;
	private Joueur[] joueurs;
	private Joueur vainqueur;
	private int tour;
	private int tourMax;
	private int scoreCible;
	private int chrono;
	private CompteARebours compteARebours;
	
	public Partie(GrilleLettres grille, ArbreLexical arbre, Joueur[] joueurs, int scoreCible, int tourMax, int chrono) {
		this.grille = grille;
		this.arbre = arbre;
		this.joueurs = joueurs;
		this.scoreCible = scoreCible;
		this.tour = 0;
		this.tourMax = tourMax;
		this.chrono = chrono;
	}
	
	public Partie(GrilleLettres grille, ArbreLexical arbre, Joueur[] joueur) {
		this(grille, arbre, joueur, DEFAULT_SCORECIBLE, DEFAULT_TOURMAX, DEFAULT_CHRONO);
	}
	
	/**
	 * Retourne le score à atteindre pour gagner
	 * 
	 * @return	le score qu'un joueur doit atteindre pour gagner
	 */
	public int scoreCible() {
		return scoreCible;
	}
	
	/**
	 * Retourne le numéro du tour courant
	 * 
	 * @return	le tour courant
	 */
	public int tour() {
		return tour;
	}
	
	/**
	 * Retourne le numéro du tour maximum
	 * 
	 * @return	le tour maximum
	 */
	public int tourMax() {
		return tourMax;
	}

	/**
	 * Remet le tour courant à 0
	 */
	public void resetTour() {
		tour = 0;
	}
	
	/**
	 * Incrémente le tour courant de 1
	 */
	public void incTour() {
		tour++;
	}
	
	/**
	 * Retourne le vainqueur de la partie
	 * 
	 * @return	le joueur vainqueur
	 */
	public Joueur getVainqueur() {
		return vainqueur;
	}
	
	/**
	 * Vérifie si la partie est terminée
	 * 
	 * @return <code>true</code> si la partie est terminée, <code>false</code> sinon
	 */
	public boolean estTerminee() {
		return tour == tourMax || vainqueur != null;
	}
	
	/**
	 * Retourne un itérateur sur les joueurs de la partie
	 */
	public Iterator<Joueur> iterator() {
		return new JoueurIterator();
	}
	
	/**
	 * Vérifie si l'arbre lexical contient le mot en paramètre
	 * 
	 * @param	mot
	 * 			le mot à vérifier
	 * 
	 * @return	<code>true</code> si le mot est dans l'arbre, <code>false</code> sinon
	 */
	public boolean verifierMot(String mot) {
		return arbre.contient(mot);
	}
	
	/**
	 * On termine le tour d'un joueur et on calcule son score.
	 * 
	 * @param	joueur
	 * 			le joueur pour lequel on termine le tour
	 */
	public void terminerTour(Joueur joueur) {
		int taille;
		int points;
		for (String s : grille.getMots()) {
			taille = s.length();
			points = 0;
			if (taille >= 3 && verifierMot(s)) {
				if (taille == 3 || taille == 4) {
					points = 1;
				}
				else if (taille == 5) {
					points = 2;
				}
				else if (taille == 6) {
					points = 3;
				}
				else if (taille == 7) {
					points = 5;
				}
				else if (taille < 7) {
					points = 11;
				}
				joueur.incScore(points);
			}
		}
		if (joueur.getScore() >= scoreCible) {
			vainqueur = joueur;
		}
	}
	
	/**
	 * Démarre la partie
	 */
	public void run() {
		Iterator<Joueur> it = iterator();
		Joueur joueur;
		while (!estTerminee()) {
			grille.secouer();
			joueur = it.next();
			joueur.joue(grille, arbre);
			// On démarre le compte à rebours
			demarrerCompteARebours();
			// La fin du tour se produit à la fin du compte à rebours
			// ou lorsque celui-ci est stoppé (appuyer sur Terminer)
			terminerTour(joueur);
		}
	}
	
	public void demarrerCompteARebours() {
		compteARebours = new CompteARebours(chrono);
		compteARebours.start();
	}
	
	public void stopperCompteARebours() {
		compteARebours.shutdown();
	}
	
	/**
	 * Implémentation d'un iterateur sur les joueurs.
	 */
	private class JoueurIterator implements Iterator<Joueur> {
		
		private int i = -1;
		
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

		public void remove() {
			// Rien à faire ici
		}
		
	}

}
