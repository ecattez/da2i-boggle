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
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import boggle.gui.ecran.EcranManager.Bouton;

/**
 * L'écran qui affiche le menu principal
 */
public class EcranMenuPrincipal extends AbstractEcran {
	
	private static final long serialVersionUID = -6774404505867511769L;
	
	public EcranMenuPrincipal() {
		super(new BorderLayout());
		
		JPanel centre = new JPanel();
		JLabel titre = new JLabel("Boggle");
		JPanel bas = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JLabel auteurs = new JLabel("Edouard CATTEZ - Alexandre VASTRA");
		
		titre.setFont(new Font("Arial", Font.BOLD, 100));
		titre.setForeground(Color.WHITE);
		
		centre.add(titre);
		centre.setBackground(new Color(191,59,1));
		
		auteurs.setForeground(Color.WHITE);
		
		bas.add(auteurs);
		bas.setBackground(new Color(95,29,0));
		
		this.add(centre, BorderLayout.CENTER);
		this.add(bas, BorderLayout.SOUTH);
	}

	public void recharger() {
		cacherBoutons();
		afficherBoutons(Bouton.JOUER, Bouton.CLASSEMENTS);
	}
	
	public void nettoyer() {
		// Rien à faire
	}
	
	public void recevoir(Object o) {
		// Rien à faire
	}


}
