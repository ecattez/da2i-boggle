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
 * L'EcranManager est le conteneur qui gère la totalité des écrans du jeu.
 */
public class EcranManager extends JFrame {
	
	private static final long serialVersionUID = -5451190123766703896L;

	/**
	 * Enumération des boutons du menu
	 */
	public enum Bouton {
		
		MENU_PRINCIPAL("Menu Principal", Ecran.MENU_PRINCIPAL),
		NOUVELLE_PARTIE("Nouvelle partie", Ecran.NOUVELLE_PARTIE),
		JOUER("Jouer", Ecran.NOUVELLE_PARTIE),
		CLASSEMENTS("Classements", Ecran.CLASSEMENTS);
		
		// Le nom affiché dans le bouton
		private final String nom;
		// Le JButton associé
		private final JButton bouton;
		
		private Bouton(String name, final Ecran ecran) {
			this.nom = name;
			this.bouton = new BoutonMenu(new JButton(name));
			this.bouton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					EcranManager.getInstance().show(ecran);
				}
				
			});
		}
		
		/**
		 * Rend visible ou non le bouton
		 * 
		 * @param	visible
		 * 			la visibilité (booléen) du bouton
		 */
		public void setVisible(boolean visible) {
			bouton.setVisible(visible);
		}
		
		/**
		 * Cache le bouton à l'écran
		 */
		public void cacher() {
			setVisible(false);
		}
		
		/**
		 * Affiche le bouton à l'écran
		 */
		public void afficher() {
			setVisible(true);
		}
		
		/**
		 * Retourne le nom du bouton
		 */
		public String toString() {
			return nom;
		}
		
	}
	
	/**
	 * Enumération des écrans du jeu
	 */
	public enum Ecran {
		
		MENU_PRINCIPAL(new EcranMenuPrincipal()),
		NOUVELLE_PARTIE(new EcranNouvellePartie()),
		JEU(new EcranJeu()),
		CLASSEMENTS(new EcranClassements());
		
		// L'écran associé
		private final AbstractEcran ecranReel;
		
		private Ecran(AbstractEcran ecran) {
			this.ecranReel = ecran;
		}
		
		/**
		 * Retourne le label associé à l'écran
		 * 
		 * @return	le label de l'écran
		 */
		public String getLabel() {
			return name();
		}
		
		/**
		 * Transmet un objet à un écran
		 * 
		 * @param	o
		 * 			l'objet à transmettre
		 * @param	e
		 * 			l'écran qui va recevoir l'objet
		 */
		public void envoyer(Object o, Ecran e) {
			ecranReel.envoyer(o, e);
		}
		
		/**
		 * Reçoit un objet
		 * 
		 * @param	o
		 * 			l'objet reçu
		 */
		public void recevoir(Object o) {
			ecranReel.recevoir(o);
		}
		
	}
	
	public static EcranManager ecranManager;
	
	/**
	 * Instance unique de l'EcranManager
	 * 
	 * @return	l'instance unique d'EcranManager
	 */
	public static EcranManager getInstance() {
		if (ecranManager == null) {
			ecranManager = new EcranManager();
			ecranManager.first();
		}
		return ecranManager;
	}
	
	// La disposition des écrans de jeu
	private CardLayout cardLayout;
	// Le conteneur des écrans
	private JPanel conteneurPrincipal;
	// Le conteneur des boutons du menu
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
	
	/**
	 * Affiche le premier écran du cardLayout
	 */
	public void first() {
		cardLayout.first(conteneurPrincipal);
		repack();
	}
	
	/**
	 * Affiche le dernier écran du cardLayout
	 */
	public void last() {
		cardLayout.last(conteneurPrincipal);
		repack();
	}
	
	/**
	 * Affiche l'écran qui succède à l'écran courant
	 */
	public void next() {
		cardLayout.next(conteneurPrincipal);
		repack();
	}
	
	/**
	 * Affiche l'écran qui précéde l'écran courant
	 */
	public void previous() {
		cardLayout.previous(conteneurPrincipal);
		repack();
	}
	
	/**
	 * Affiche l'écran souhaité (en utilisant son label)
	 * 
	 * @param	ecran
	 * 			l'écran à afficher
	 */
	public void show(Ecran ecran) {
		cardLayout.show(conteneurPrincipal, ecran.getLabel());
		repack();
	}
	
	/**
	 * Cache tous les boutons du menu
	 */
	public void cacherBoutons() {
		for (Bouton b : Bouton.values()) {
			b.cacher();
		}
	}
	
	/**
	 * Cache tous les boutons voulus du menu
	 * 
	 * @param	boutons
	 * 			les boutons à cacher
	 */
	public void cacherBoutons(Bouton... boutons) {
		for (Bouton b : boutons) {
			b.cacher();
		}
	}
	
	/**
	 * Affiche tous les boutons du menu
	 */
	public void afficherBoutons() {
		for (Bouton b : Bouton.values()) {
			b.afficher();
		}
	}
	
	/**
	 * Affiche tous les boutons voulus du menu
	 * 
	 * @param	boutons
	 * 			les boutons à afficher
	 */
	public void afficherBoutons(Bouton... boutons) {
		for (Bouton b : boutons) {
			b.afficher();
		}
	}
	
	/**
	 * Redimensionne l'EcranManager pour toujours avoir l'écran le plus optimal
	 */
	public void repack() {
		this.pack();
	}
	
}
