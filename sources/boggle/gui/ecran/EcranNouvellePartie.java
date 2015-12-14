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
import boggle.gui.Bucket;
import boggle.gui.ecran.EcranManager.Bouton;
import boggle.gui.ecran.EcranManager.Ecran;
import boggle.gui.regles.JoueursPanel;
import boggle.gui.regles.ReglesPanel;
import boggle.jeu.Partie;

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
	
	public void recharger() {
		cacherBoutons();
		afficherBoutons(Bouton.MENU_PRINCIPAL);
		Partie partie = Bucket.getInstance().getPartie();
		if (partie != null && !partie.estTerminee()) {
			partie.forcerArret();
		}
	}
	
	public void demarrer() {
		try {
			Partie partie = new Partie(reglesPanel.getReglesCourantes(), joueursPanel.getTousLesJoueurs());
			Bucket.getInstance().push(partie);
			switchTo(Ecran.JEU);
		} catch (BoggleException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
	}

}