package boggle.gui.regles;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import boggle.gui.decorateur.BoutonPlat;
import boggle.jeu.joueur.Humain;
import boggle.jeu.joueur.IAHardcore;
import boggle.jeu.joueur.Joueur;

/**
 * 
 */
public class JoueursPanel extends JScrollPane {
	
	private static final long serialVersionUID = -1676600753074020673L;
	
	private JPanel conteneur;
	
	public JoueursPanel() {
		super();
		conteneur = new JPanel();
		conteneur.setLayout(new BoxLayout(conteneur, BoxLayout.Y_AXIS));
		conteneur.add(new SimpleJoueurPanel());
		this.setViewportView(conteneur);
	}
	
	public int nbJoueurs() {
		return conteneur.getComponentCount();
	}
	
	public void ajouter() {
		conteneur.add(new SimpleJoueurPanel());
		conteneur.validate();
	}
	
	public void supprimer(Component component) {
		conteneur.remove(component);
		conteneur.validate();
	}
	
	public Joueur[] getTousLesJoueurs() {
		int size = nbJoueurs();
		Joueur[] joueurs = new Joueur[size];
		for(int i = 0 ; i < size; i++) {
			SimpleJoueurPanel joueurPanel = (SimpleJoueurPanel) conteneur.getComponent(i);
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
			comboBox = new JComboBox<String>();
			for (String str : type) {
				comboBox.addItem(str);
			}
			
			moins.addActionListener(new ActionListener() {
	
				public void actionPerformed(ActionEvent e) {
					if (nbJoueurs() > 1) {
						supprimer(SimpleJoueurPanel.this);
					}
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
