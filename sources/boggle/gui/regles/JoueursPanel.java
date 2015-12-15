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
import java.awt.Component;
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
 * 
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
	
	public int nbJoueurs() {
		return this.getComponentCount();
	}
	
	public void ajouter() {
		if (nbJoueurs() < JOUEUR_MAX) {
			this.add(new SimpleJoueurPanel());
			this.validate();
		}
	}
	
	public void supprimer(Component component) {
		if (nbJoueurs() > 1) {
			this.remove(component);
			this.validate();
			this.repaint();
		}
	}
	
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
		
		public boolean estHumain() {
			return getType().equals("Humain");
		}
		
		public String getNomJoueur() {
			return textField.getText();
		}
		
		public String getType() {
			return (String) comboBox.getSelectedItem();
		}
	
	}
	
}
