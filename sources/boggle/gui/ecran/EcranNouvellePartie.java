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
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import boggle.gui.Bucket;
import boggle.gui.NouvellePartie;
import boggle.gui.decorateur.DecorateurBoutonPlat;
import boggle.gui.ecran.EcranManager.Bouton;
import boggle.gui.partie.JoueurPanel;
import boggle.jeu.Partie;
import boggle.jeu.joueur.Humain;
import boggle.jeu.joueur.IAHardcore;
import boggle.jeu.joueur.Joueur;

public class EcranNouvellePartie extends AbstractEcran {

	private static final long serialVersionUID = 4790691387769043379L;

	private JPanel centre;
	private JPanel ouest;
	
	public EcranNouvellePartie() {
		super(new BorderLayout());
		this.add(new NouvellePartie());
		centre = new JPanel(new BorderLayout());
		ouest = new JPanel();
		ouest.setLayout(new BoxLayout(ouest, BoxLayout.Y_AXIS));
		ouest.add(new JoueurPanel());

		int size = ouest.getComponentCount();
		Joueur[] joueurs = new Joueur[size];
		for(int i = 0 ; i < size; i++) {
			JoueurPanel joueurPanel = (JoueurPanel) ouest.getComponent(i);
			if (joueurPanel.estHumain()) {
				joueurs[i] = new Humain(joueurPanel.getNomJoueur());
			} 
			else {
				joueurs[i] = new IAHardcore(joueurPanel.getNomJoueur());
			}
		}
		
//		this.add(new JScrollPane(ouest), BorderLayout.WEST);
//		this.add(centre, BorderLayout.CENTER);
	}
	
	public void recharger() {
		cacherBoutons();
		afficherBoutons(Bouton.MENU_PRINCIPAL, Bouton.DEMARRER_PARTIE);
		Partie partie = Bucket.getInstance().getPartie();
		if (partie != null && !partie.estTerminee()) {
			partie.forcerArret();
		}
	}

}
