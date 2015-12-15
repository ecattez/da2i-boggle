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
package boggle.gui.ecran;

import boggle.BoggleException;
import boggle.gui.ecran.EcranManager.Bouton;
import boggle.gui.partie.JPartie;
import boggle.jeu.Partie;

/**
 * Ecran qui gère le jeu (la partie courante)
 */
public class EcranJeu extends AbstractEcran {
	
	private static final long serialVersionUID = -2902887394692862062L;
	
	private Partie partie;

	public EcranJeu() {}

	// L'écran attend de recevoir une partie
	public void recevoir(Object o) {
		if (o instanceof Partie) {
			partie = (Partie) o;
		}
		else {
			throw new BoggleException("L'écran de jeu ne peut recevoir que des objets de type Partie");
		}
	}
	
	// Au rechargement, on crée l'objet JPartie qu'on ajoute à l'écran
	public void recharger() {
		cacherBoutons();
		afficherBoutons(Bouton.MENU_PRINCIPAL, Bouton.NOUVELLE_PARTIE);
		this.add(new JPartie(partie));
		new Thread(partie).start();
	}
	
	// Si on quitte l'écran avant la fin de la partie, on arrête celle-ci
	public void nettoyer() {
		if (partie != null && !partie.estTerminee()) {
			partie.forcerArret();
		}
		this.removeAll();
	}

}
