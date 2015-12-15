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

public class EcranClassements extends AbstractEcran {
	
	private static final long serialVersionUID = 599000474435628090L;
	
	private List<Classement> classements;
	
	public EcranClassements() {
		this.classements = new ArrayList<Classement>();
		this.afficherClassements();
		this.setBackground(new Color(31,71,126));
	}

	public void recharger() {
		cacherBoutons();
		afficherBoutons(Bouton.MENU_PRINCIPAL, Bouton.NOUVELLE_PARTIE);
		afficherClassements();
	}
	
	public void afficherClassements() {
		classements.clear();
		classements.addAll(Classement.listerTous());
		JTabbedPane pane = new JTabbedPane();
		Iterator<Classement> it = classements.iterator();
		Classement c;
		while (it.hasNext()) {
			c = it.next();
			pane.add(c.getTitle(), new JClassement(c));
		}
		this.removeAll();
		if (pane.getComponentCount() > 0) {
			this.add(pane);
		}
		else {
			JPanel aucunClassement = new JPanel();
			aucunClassement.add(new JLabel("Il n'y a actuellement aucun classement sauvegardé"));
			this.add(aucunClassement);
		}
	}

	public void nettoyer() {
		// Rien à faire
	}
	
	public void recevoir(Object o) {
		// Rien à faire
	}

}
