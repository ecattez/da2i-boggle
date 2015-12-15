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

import java.util.ArrayList;
import java.util.List;

/**
 * Implémentation d'une IA difficile à battre
 */
public class IAHardcore extends IA {
	
	public IAHardcore(String name) {
		super(name);
	}

	public void run() {
		List<String> mots = new ArrayList<String>();
		for (char c = 'A'; c <= 'Z'; c++) {
			if (arbre.motsCommencantPar(String.valueOf(c), mots)) {
				for (String mot : mots) {
					if (mot.length() >= grille.tailleMinimale() && grille.ecrire(mot)) {
						// On fait attendre l'IA une seconde pour qu'elle ne soit pas trop rapide à jouer
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						// Si le tour s'est fini plus tôt que prévu (sablier terminé)
						// on arrête tous les traitements
						if (forcerArret()) {
							return;
						}
						grille.stockerMot();
					}
				}
				mots.clear();
			}
		}
		partie.stopperSablier();
	}

}
