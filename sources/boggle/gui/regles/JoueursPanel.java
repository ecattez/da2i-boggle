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
package boggle.gui.regles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import boggle.gui.decorateur.BoutonPlat;
import boggle.jeu.joueur.Humain;
import boggle.jeu.joueur.IAHardcore;
import boggle.jeu.joueur.Joueur;

/**
 * Panel qui définit l'ensemble des joueurs d'une partie
 */
public class JoueursPanel extends JPanel {
	
	private static final long serialVersionUID = -1676600753074020673L;
	
	public static final int JOUEUR_MAX = 10;
	
	public JoueursPanel() {
		super();
		this.setPreferredSize(new Dimension(300, 30 * JOUEUR_MAX));
		for (int i=0; i < 2; i++) {
			ajouter();
		}
		this.setBackground(new Color(255,215,0));
	}
	
	/**
	 * Retourne le nombre de joueurs actuellement définis
	 * 
	 * @return	le nombre de joueurs courant
	 */
	public int nbJoueurs() {
		return this.getComponentCount();
	}
	
	/**
	 * Ajoute un nouveau joueur dans le panel
	 */
	public void ajouter() {
		if (nbJoueurs() < JOUEUR_MAX) {
			this.add(new SimpleJoueurPanel());
			this.validate();
		}
	}
	
	/**
	 * Supprime un joueur du panel global
	 * 
	 * @param	joueurPanel
	 * 			le panel du joueur à supprimer
	 * 			
	 */
	public void supprimer(SimpleJoueurPanel joueurPanel) {
		if (nbJoueurs() > 1) {
			this.remove(joueurPanel);
			this.validate();
			this.repaint();
		}
	}
	
	/**
	 * Crée l'ensemble des joueurs à partir du panel représentant chaque joueur
	 * 
	 * @return	un tableau d'instances de Joueur (les joueurs de la partie)
	 */
	public Joueur[] getTousLesJoueurs() {
		int size = nbJoueurs();
		Joueur[] joueurs = new Joueur[size];
		for(int i = 0 ; i < size; i++) {
			SimpleJoueurPanel joueurPanel = (SimpleJoueurPanel) this.getComponent(i);
			if (joueurPanel.estHumain()) {
				joueurs[i] = new Humain(joueurPanel.getNomJoueur());
			} 
			else {
				joueurs[i] = new IAHardcore(joueurPanel.getNomJoueur());
			}
		}
		return joueurs;
	}
	
	/**
	 * Panel des informations relatives à un joueur
	 */
	class SimpleJoueurPanel extends JPanel {
	
		private static final long serialVersionUID = -8236754317603170769L;
		
		private JTextField textField;
		private JComboBox<String> comboBox;
		private String[] type = { "Humain", "IA" };
		
		public SimpleJoueurPanel() {
			super();
			JButton moins = new BoutonPlat(new JButton("-"));
			JButton plus = new BoutonPlat(new JButton("+"));
			textField = new JTextField("Nom du joueur");
			textField.setPreferredSize(new Dimension(110, 25));
			comboBox = new JComboBox<String>();
			comboBox.setBackground(Color.WHITE);
			for (String str : type) {
				comboBox.addItem(str);
			}
			
			moins.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent e) {
					supprimer(SimpleJoueurPanel.this);
				}				
				
			});
			
			plus.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent e) {
					ajouter();
				}
				
			});
			
			this.add(moins);
			this.add(plus);
			this.add(textField);
			this.add(comboBox);
			this.setBackground(null);
		}
		
		/**
		 * Vérifie si le joueur à créer est humain
		 * 
		 * @return	<code>true</code> si le joueur à créer est humain, <code>false</code> sinon
		 */
		public boolean estHumain() {
			return getType().equals("Humain");
		}
		
		/**
		 * Retourne le nom du joueur stocké dans le textfield du panel
		 * 
		 * @return	le nom du joueur
		 */
		public String getNomJoueur() {
			return textField.getText();
		}
		
		/**
		 * Retourne le type du joueur à créer (Humain ou IA)
		 * 
		 * @return	le type du joueur
		 */
		public String getType() {
			return (String) comboBox.getSelectedItem();
		}
	
	}
	
}
