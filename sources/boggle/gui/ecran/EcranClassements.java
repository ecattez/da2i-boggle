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

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import boggle.gui.classement.JClassement;
import boggle.gui.ecran.EcranManager.Bouton;
import boggle.jeu.Classement;

/**
 * L'écran qui affiche les différents classements selon la dimension de chaque grille
 */
public class EcranClassements extends AbstractEcran {
	
	private static final long serialVersionUID = 599000474435628090L;
	
	// Liste de tous les classements par taille de grille
	private List<Classement> classements;
	// Panel à onglets pour tous les classements
	private JTabbedPane pane;
	// Panel affiché quand il n'y a aucun classement dans le dossier Classement.CLASSEMENT_FOLDER
	private JPanel aucunClassement;
	
	public EcranClassements() {
		this.classements = new ArrayList<Classement>();
		this.setBackground(new Color(31,71,126));
		
		pane = new JTabbedPane();
		pane.setVisible(false);
		this.add(pane);
		
		JLabel info = new JLabel("Il n'y a actuellement aucun classement sauvegardé");
		info.setForeground(Color.WHITE);
		aucunClassement = new JPanel();
		aucunClassement.setBackground(null);
		aucunClassement.add(info);
		aucunClassement.setVisible(false);
		this.add(aucunClassement);
	}

	public void recharger() {
		cacherBoutons();
		afficherBoutons(Bouton.MENU_PRINCIPAL, Bouton.NOUVELLE_PARTIE);
		afficherClassements();
	}
	
	/**
	 * Affiche tous les classements disponibles dans le dossier Classement.CLASSEMENT_FOLDER
	 */
	public void afficherClassements() {
		classements.addAll(Classement.listerTous());
		Iterator<Classement> it = classements.iterator();
		Classement c;
		while (it.hasNext()) {
			c = it.next();
			pane.add(c.getTitle(), new JClassement(c));
		}
		if (pane.getComponentCount() > 0) {
			pane.setVisible(true);
		}
		else {
			aucunClassement.setVisible(true);
		}
	}

	public void nettoyer() {
		classements.clear();
		pane.removeAll();
		pane.setVisible(false);
		aucunClassement.setVisible(false);
	}
	
	public void recevoir(Object o) {
		// Rien à faire
	}

}
