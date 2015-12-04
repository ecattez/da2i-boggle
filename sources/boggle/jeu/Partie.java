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

import boggle.BoggleException;
import boggle.mots.ArbreLexical;
import boggle.mots.GrilleLettres;

/**
 * Représentation d'une partie générique de Boggle entre plusieurs joueurs.
 */
public class Partie implements Iterable<Joueur>, Runnable {
	
	public static final int DEFAULT_CHRONO = 60;
	public static final int DEFAULT_SCORECIBLE = 30;
	public static final int DEFAULT_TOURMAX = 5;
	public static final int[] DEFAULT_POINTS = { 1, 1, 2, 3, 5, 11 };
	
	private GrilleLettres grille;
	private ArbreLexical arbre;
	private Joueur[] joueurs;
	private boolean gagnant;
	private int tour;
	private int tourMax;
	private int scoreCible;
	private int chrono;
	// Les points sont un ensemble de 6 nombre.
	// Les points sont attribués à partir d'un mot de taille minimale
	private int[] points;
	private Sablier sablier;
	
	public Partie(GrilleLettres grille, ArbreLexical arbre, Joueur[] joueurs, int scoreCible, int tourMax, int chrono, int[] points) {
		if (points.length < 6) {
			throw new BoggleException("Il manque des points à attribuer pour la taille des mots");
		}
		this.grille = grille;
		this.arbre = arbre;
		this.joueurs = joueurs;
		this.gagnant = false;
		this.scoreCible = scoreCible;
		this.tour = 0;
		this.tourMax = tourMax;
		this.points = points;
		this.chrono = chrono;
	}
	
	public Partie(GrilleLettres grille, ArbreLexical arbre, Joueur[] joueur) {
		this(grille, arbre, joueur, DEFAULT_SCORECIBLE, DEFAULT_TOURMAX, DEFAULT_CHRONO, DEFAULT_POINTS);
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
	 * Vérifie si la partie est terminée
	 * 
	 * @return <code>true</code> si la partie est terminée, <code>false</code> sinon
	 */
	public boolean estTerminee() {
		return tour == tourMax || gagnant;
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
		int pt;
		int min = grille.tailleMinimale();
		for (String s : grille.getMots()) {
			taille = s.length();
			if (taille >= min && verifierMot(s)) {
				if (taille - min < points.length) {
					pt = points[taille - min];
				}
				else {
					pt = points[points.length - 1];
				}
				joueur.incScore(pt);
			}
		}
		gagnant = (joueur.getScore() >= scoreCible);
	}
	
	/**
	 * Démarre la partie
	 */
	public void run() {
		Iterator<Joueur> it = iterator();
		Joueur joueur = null;
		Joueur meilleur = null;
		while (!estTerminee()) {
			grille.secouer();
			System.out.println("Grille secouée.\n");
			joueur = it.next();
			System.out.println("Au tour de " + joueur.getName() + " (score: " + joueur.getScore() + ").\n");
			joueur.joue(grille, arbre, this);
			// On démarre le compte à rebours
			demarrerSablier();
			// La fin du tour se produit à la fin du compte à rebours
			// ou lorsque celui-ci est stoppé (appuyer sur Terminer)
			System.out.println("Fin du tour de " + joueur.getName() + ".\nCalcul des points en cours.\n");
			terminerTour(joueur);
			System.out.println("Résultat: " + joueur);
			// On stocke le meilleur joueur courant de la partie
			if (meilleur == null || meilleur.getScore() < joueur.getScore()) {
				meilleur = joueur;
			}
			incTour();
		}
		System.out.println("Vainqueur: " + meilleur);
	}
	
	/**
	 * Démarre le compte à rebours du tour du joueur courant
	 */
	public void demarrerSablier() {
		sablier = new Sablier(chrono);
		sablier.run();
	}
	
	/**
	 * Arrête le compte à rebours du tour du joueur courant
	 */
	public void stopperSablier() {
		if (!sablier.isOver()) {
			sablier.shutdown();
		}
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
