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

import java.util.Scanner;

import boggle.jeu.Partie;
import boggle.mots.ArbreLexical;
import boggle.mots.Grille;

/**
 * Implémentation d'un joueur humain
 */
public class Humain extends Joueur {
	
	public static Scanner sc = new Scanner(System.in);

	public Humain(String nom) {
		super(nom);
	}
	
	public Humain(String nom, int score) {
		super(nom, score);
	}

	public void joue(Grille grille, ArbreLexical arbre, Partie partie) {
		// Rien à faire
	}

	public boolean estHumain() {
		return true;
	}
	
}
