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

import java.util.Scanner;

import boggle.mots.ArbreLexical;
import boggle.mots.Coordonnees;
import boggle.mots.CoordonneesCartesiennes;
import boggle.mots.GrilleLettres;

/**
 * Implémentation d'un joueur humain
 */
public class Humain extends Joueur {
	
	public static Scanner sc = new Scanner(System.in);

	public Humain(String name) {
		super(name);
	}

	public void joue(GrilleLettres grille, ArbreLexical arbre) {
		String s;
		int x, y;
		do {
			System.out.println(grille);
			System.out.println(grille.getLettresUtilisees());
			System.out.print("Choisissez un (x,y): ");
			x = sc.nextInt();
			y = sc.nextInt();
			
			Coordonnees c = new CoordonneesCartesiennes(x, y);
			Coordonnees tete = grille.getDernierePosition();
			
			if (tete == null) {
				grille.utiliserDe(c);
			}
			// Le dé est le dernier choisit, on considère que le joueur annule sa dernière action, on dépile
			else if (c.equals(tete)) {
				grille.rendreDe(c);
			}
			else if (c.estVoisinDe(tete)) {
				// Le dé n'est pas utilisé et est voisin du dernier dé choisit, on l'ajoute à la pile
				if (!grille.estUtilise(c)) {
					grille.utiliserDe(c);
				}
			}
			
			System.out.println(grille.getLettresUtilisees());
			System.out.println(grille.getMots());
			
			System.out.println("+: ajouter, fin: terminer, c:continuer");
			s = sc.next();
			if (s.equals("+")) {
				grille.stockerMot();
				grille.rendreTout();
			}
		} while (!s.equals("fin"));
	}

}
