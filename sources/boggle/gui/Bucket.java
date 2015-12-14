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
package boggle.gui;

import java.util.Observable;

import boggle.jeu.Classement;
import boggle.jeu.Partie;
import boggle.jeu.Regles;
import boggle.jeu.joueur.Joueur;

/**
 * Le Bucket est un singleton qui collecte des informations à distribuer
 * entre divers objets (contrôleurs, vues...).
 * 
 * Cet objet est intéressant pour servir de passerelles entre des écrans complémentaires.
 * Exemple: l'écran qui crée la partie, et celui qui l'affiche.
 * - le créateur envoie la partie dans le Bucket
 * - l'afficheur récupère la partie depuis le Bucket
 */
public class Bucket extends Observable {
	
	private static Bucket bucket;
	
	public static Bucket getInstance() {
		if (bucket == null) {
			bucket = new Bucket();
		}
		return bucket;
	}
	
	private Regles regles;
	private Partie partie;
	private Joueur[] joueurs;
	private Classement classement;
	
	private Bucket() {}
	
	// Notifie les observeurs que le bucket a changé
	private void update() {
		setChanged();
		notifyObservers();
	}
	
	public void push(Regles regles) {
		this.regles = regles;
		update();
	}
	
	public void push(Partie partie) {
		this.partie = partie;
		update();
	}
	
	public void push(Joueur[] joueurs) {
		this.joueurs = joueurs;
		update();
	}
	
	public void push(Classement classement) {
		this.classement = classement;
		update();
	}
	
	public Regles getRegles() {
		return this.regles;
	}
	
	public Partie getPartie() {
		return this.partie;
	}
	
	public Joueur[] getJoueurs() {
		return this.joueurs;
	}
	
	public Classement getClassement() {
		return this.classement;
	}
	

}
