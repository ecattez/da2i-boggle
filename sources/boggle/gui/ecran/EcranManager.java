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
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import boggle.gui.decorateur.BoutonMenu;

/**
 * Conteneur principal de tous les écrans.
 */
public class EcranManager extends JFrame {
	
	private static final long serialVersionUID = -5451190123766703896L;

	/**
	 * 
	 */
	public enum Bouton {
		
		MENU_PRINCIPAL("Menu Principal", Ecran.MENU_PRINCIPAL),
		NOUVELLE_PARTIE("Nouvelle partie", Ecran.NOUVELLE_PARTIE),
		JOUER("Jouer", Ecran.NOUVELLE_PARTIE),
		CLASSEMENTS("Classements", Ecran.CLASSEMENTS);
		
		private final String name;
		private final JButton bouton;
		
		private Bouton(String name, final Ecran ecran) {
			this.name = name;
			this.bouton = new BoutonMenu(new JButton(name));
			this.bouton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					EcranManager.getInstance().show(ecran);
				}
				
			});
		}
		
		public void setVisible(boolean visible) {
			bouton.setVisible(visible);
		}
		
		public void cacher() {
			setVisible(false);
		}
		
		public void afficher() {
			setVisible(true);
		}
		
		public String toString() {
			return name;
		}
		
	}
	
	/**
	 * 
	 */
	public enum Ecran {
		
		MENU_PRINCIPAL(new EcranMenuPrincipal()),
		NOUVELLE_PARTIE(new EcranNouvellePartie()),
		JEU(new EcranJeu()),
		CLASSEMENTS(new EcranClassements());
		
		private final AbstractEcran ecranReel;
		
		private Ecran(AbstractEcran ecran) {
			this.ecranReel = ecran;
		}
		
		public String getLabel() {
			return name();
		}
		
	}
	
	public static EcranManager ecranManager;
	
	public static EcranManager getInstance() {
		if (ecranManager == null) {
			ecranManager = new EcranManager();
			ecranManager.first();
		}
		return ecranManager;
	}
	
	private CardLayout cardLayout;
	private JPanel conteneurPrincipal;
	private JPanel conteneurBoutons;
	
	private EcranManager() {
		super("Boggle - Edouard CATTEZ & Alexandre VASTRA");
		this.cardLayout = new CardLayout();
		this.conteneurPrincipal = new JPanel(cardLayout);
		this.conteneurBoutons = new JPanel();
		for (Bouton bouton : Bouton.values()) {
			conteneurBoutons.add(bouton.bouton);
		}
		for (Ecran ecran : Ecran.values()) {
			conteneurPrincipal.add(ecran.ecranReel, ecran.getLabel());
		}
		this.conteneurBoutons.setBackground(Color.WHITE);
		this.add(conteneurPrincipal, BorderLayout.CENTER);
		this.add(conteneurBoutons, BorderLayout.SOUTH);
	}
	
	public void first() {
		cardLayout.first(conteneurPrincipal);
	}
	
	public void last() {
		cardLayout.last(conteneurPrincipal);
	}
	
	public void next() {
		cardLayout.next(conteneurPrincipal);
	}
	
	public void previous() {
		cardLayout.previous(conteneurPrincipal);
	}
	
	public void show(Ecran ecran) {
		cardLayout.show(conteneurPrincipal, ecran.getLabel());
	}
	
	public void cacherBoutons() {
		for (Bouton b : Bouton.values()) {
			b.cacher();
		}
	}
	
	public void cacherBoutons(Bouton... boutons) {
		for (Bouton b : boutons) {
			b.cacher();
		}
	}
	
	public void afficherBoutons() {
		for (Bouton b : Bouton.values()) {
			b.cacher();
		}
	}
	
	public void afficherBoutons(Bouton... boutons) {
		for (Bouton b : boutons) {
			b.afficher();
		}
	}
	
	public void repack() {
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
}