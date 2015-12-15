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

import java.awt.BorderLayout;

import javax.swing.JOptionPane;

import boggle.BoggleException;
import boggle.gui.ecran.EcranManager.Bouton;
import boggle.gui.ecran.EcranManager.Ecran;
import boggle.gui.regles.JoueursPanel;
import boggle.gui.regles.ReglesPanel;
import boggle.jeu.Partie;

/**
 * L'écran qui gère la création d'une nouvelle partie.
 * 
 * Dans celui-ci, on définit les joueurs et les règles de la partie
 */
public class EcranNouvellePartie extends AbstractEcran {

	private static final long serialVersionUID = 4790691387769043379L;

	private ReglesPanel reglesPanel;
	private JoueursPanel joueursPanel;
	
	public EcranNouvellePartie() {
		super(new BorderLayout());
		reglesPanel = new ReglesPanel(this);
		joueursPanel = new JoueursPanel();
		this.add(joueursPanel, BorderLayout.WEST);
		this.add(reglesPanel, BorderLayout.CENTER);
	}
	
	public void recevoir(Object o) {
		// Rien à faire
	}
	
	public void recharger() {
		cacherBoutons();
		afficherBoutons(Bouton.MENU_PRINCIPAL);
	}
	
	public void nettoyer() {
		// Rien à faire
	}
	
	/**
	 * Crée une instance de partie et l'envoie à l'écran de jeu
	 */
	public void demarrer() {
		try {
			Partie partie = new Partie(reglesPanel.getReglesCourantes(), joueursPanel.getTousLesJoueurs());
			switchTo(Ecran.JEU, partie);
		} catch (BoggleException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
