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

import boggle.jeu.Partie;
import boggle.mots.ArbreLexical;
import boggle.mots.Grille;

/**
 * Implémentation d'un joueur machine
 */
public abstract class IA extends Joueur implements Runnable {
	
	protected Grille grille;
	protected ArbreLexical arbre;
	protected Partie partie;
	
	public IA(String name) {
		super(name);
	}
	
	/**
	 * Force l'arrêt de l'IA si jamais ce n'est plus son tour de jeu
	 * mais qu'elle est tout de même en train de jouer
	 * 
	 * @return <code>true</code> si on force l'arrêt de l'IA, <code>false</code> sinon
	 */
	protected boolean forcerArret() {
		return !this.equals(partie.getJoueurCourant()) || partie.estTerminee();
	}
	
	public void joue(Grille grille, ArbreLexical arbre, Partie partie) {
		this.grille = grille;
		this.arbre = arbre;
		this.partie = partie;
		new Thread(this).start();
	}
	
	public boolean estHumain() {
		return false;
	}

}
