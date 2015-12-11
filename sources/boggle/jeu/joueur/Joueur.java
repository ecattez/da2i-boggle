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
package boggle.jeu.joueur;

import java.util.Observable;

import boggle.jeu.Partie;
import boggle.mots.ArbreLexical;
import boggle.mots.Grille;

/**
 * Représentation générique d'un joueur de Boggle
 */
public abstract class Joueur extends Observable implements Comparable<Joueur> {
	
	private String nom;
	private int score;
	
	// Constructeur utilisé surtout pour l'objet Classement
	// On peut retrouver le score du joueur stocké dans un fichier
	public Joueur(String nom, int score) {
		this.nom = nom;
		this.score = score;
	}
	
	public Joueur(String nom) {
		this(nom, 0);
	}
	
	// Notifie les observeurs que le joueur a changé
	private void update() {
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Retourne le nom du joueur
	 * 
	 * @return	le nom du joueur
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Retourne le score courant du joueur
	 * 
	 * @return	le score courant du joueur
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Change le score du joueur
	 * 
	 * @param	score
	 * 			le nouveau score du joueur
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	/**
	 * Réinitialise le score courant du joueur à 0
	 */
	public void resetScore() {
		this.score = 0;
		update();
	}
	
	/**
	 * Incrémente de n le score du joueur
	 * 
	 * @param	n
	 * 			l'incrémentation du score du joueur
	 */
	public void incScore(int n) {
		this.score += n;
		update();
	}
	
	/**
	 * Deux joueurs sont égaux s'ils ont le même nom
	 */
	public boolean equals(Object o) {
		Joueur j = (Joueur) o;
		return j != null && nom == j.nom;
	}
	
	/**
	 * Représentation textuelle du joueur
	 */
	public String toString() {
		return nom + " (score: " + score + ")";
	}
	
	/**
	 * Compare deux joueurs sur leur score de manière décroissante
	 */
	public int compareTo(Joueur joueur) {
		return joueur.score - score;
	}
	
	/**
	 * Le joueur effectue une action dans le Boggle
	 * 
	 * @param	grille
	 * 			la grille utilisée pour la partie du joueur
	 * @param	arbre
	 * 			l'arbre lexical utilisé pour la partie du joueur
	 * @param	partie
	 * 			la partie dans laquelle joue le joueur
	 */
	public abstract void joue(Grille grille, ArbreLexical arbre, Partie partie);
	
	/**
	 * Vérifie si le joueur est un utilisateur humain qui a besoin d'intéragir avec le jeu
	 * aux travers d'entrées/sorties telles que le clavier et la souris
	 * 
	 * @return	<code>true</code> si le joueur est un humain, <code>false</code> sinon
	 */
	public abstract boolean estHumain();
	
}
