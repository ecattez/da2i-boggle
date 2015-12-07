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
import java.util.Observable;

import boggle.jeu.joueur.Joueur;
import boggle.mots.ArbreLexical;
import boggle.mots.Grille;
import boggle.mots.GrilleLettres;

/**
 * Représentation d'une partie générique de Boggle entre plusieurs joueurs.
 * 
 * Note :
 * - si le score à atteindre est <= 0, la partie s'arrête quand le dernier tour est passé
 * - si le tour maximal atteignable est <= 0, la partie s'arrête quand le score à atteindre est passé
 */
public class Partie extends Observable implements Iterable<Joueur>, Runnable {
	
	public static final int DEFAULT_CHRONO = 60;
	public static final int DEFAULT_SCORECIBLE = 30;
	public static final int DEFAULT_TOURMAX = 5;
	public static final int[] DEFAULT_POINTS = { 1, 1, 2, 3, 5, 11 };
	
	private Regles regles;
	private Grille grille;
	private Sablier sablier;
	private Joueur[] joueurs;
	private boolean gagnant;
	private int tour;
	
	// Indice du joueur courant utilisé par l'iterateur.
	// Il se trouve ici car on en a besoin pour la méthode getJoueurCourant()
	private int i = -1;
	
	public Partie(Regles regles, Joueur[] joueurs) {
		this.regles = regles;
		this.grille = new GrilleLettres(regles.getTailleMin() + 1, regles.getDes());
		this.joueurs = joueurs;
		this.gagnant = false;
		this.tour = 1;
		this.sablier = new Sablier(regles.getDureeSablier());
	}
	
	/**
	 * Retourne la grille utilisée pour cette partie
	 * 
	 * @return	la grille (Grille) utilisée pour cette partie
	 */
	public Grille getGrille() {
		return grille;
	}
	
	/**
	 * Retourne le dictionnaire de mot utilisé pour cette partie
	 * 
	 * @return	le dictionnaire (ArbreLexical) utilisé pour cette partie
	 */
	public ArbreLexical getDictionnaire() {
		return regles.getDictionnaire();
	}
	
	/**
	 * Retourne les points attribués selon les mots choisis par le joueur
	 * 
	 * @return	le tableau des points à attribuer aux mots du joueur
	 */
	public int[] getPoints() {
		return regles.getPoints();
	}
	
	/**
	 * Retourne le score à atteindre pour gagner
	 * 
	 * @return	le score qu'un joueur doit atteindre pour gagner
	 */
	public int getScoreCible() {
		return regles.getScoreCible();
	}
	
	/**
	 * Retourne le numéro du tour maximum
	 * 
	 * @return	le tour maximum
	 */
	public int getTourMax() {
		return regles.getTourMax();
	}
	
	/**
	 * Retourne le sablier utilisé pour la partie
	 * 
	 * @return	le sablier (Sablier) utilisé
	 */
	public Sablier getSablier() {
		return sablier;
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
		return tour == getTourMax() || gagnant;
	}
	
	/**
	 * Retourne le joueur qui joue actuellement
	 * 
	 * @return	le joueur courant
	 */
	public Joueur getJoueurCourant() {
		return joueurs[i % joueurs.length];
	}
	
	/**
	 * Retourne tous les joueurs de la partie
	 */
	public Joueur[] getJoueur() {
		return joueurs;
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
		return getDictionnaire().contient(mot);
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
		int[] points = getPoints();
		Iterator<String> it = grille.getMots().iterator();
		String mot;
		while (it.hasNext()) {
			mot = it.next();
			taille = mot.length();
			if (taille >= min && verifierMot(mot)) {
				if (taille - min < points.length) {
					pt = points[taille - min];
				}
				else {
					pt = points[points.length - 1];
				}
				joueur.incScore(pt);
			}
		}
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
			joueur = it.next();
			// On notifie que la partie est passée à un nouveau tour de jeu
			setChanged();
			notifyObservers();
			joueur.joue(grille, getDictionnaire(), this);
			// On démarre le compte à rebours
			demarrerSablier();
			// La fin du tour se produit à la fin du compte à rebours
			// ou lorsque celui-ci est stoppé (appuyer sur Terminer)
			terminerTour(joueur);
			// On stocke le meilleur joueur courant de la partie
			if (meilleur == null || meilleur.getScore() < joueur.getScore()) {
				meilleur = joueur;
			}
			// Le joueur gagne la partie s'il y a un score cible à atteindre (donc > 0)
			// et qu'il a atteint voire dépassé ce score
			gagnant = (getScoreCible() > 0 && meilleur.getScore() >= getScoreCible());
			incTour();
		}
		System.out.println("Vainqueur: " + meilleur);
	}
	
	/**
	 * Démarre le compte à rebours du tour du joueur courant
	 */
	public void demarrerSablier() {
		sablier.reset();
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
