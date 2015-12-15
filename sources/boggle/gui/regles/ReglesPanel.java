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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import boggle.gui.decorateur.BoutonMenu;
import boggle.gui.ecran.EcranNouvellePartie;
import boggle.jeu.Regles;
import boggle.jeu.Regles.Regle;

/**
 * Panel qui permet de définir chaque règle d'une partie
 */
public class ReglesPanel extends JPanel {
	
	private static final long serialVersionUID = 8335947767765664236L;

	private GridBagConstraints cstr;
	
	private Regles regles;
	private JRegleIntSpinner spTailleMin, spTourMax, spScoreCible, spDureeSablier;
	private JReglePoints pPoints;
	private JReglesComboPath comboDes;
	private JReglesComboPath comboDico;
	
	private JReglesComboRegles charger;
	private JCheckBox tourIllimite, scoreIllimite;
	private JButton sauvegarder;
	private JButton demarrer;

	public ReglesPanel(final EcranNouvellePartie ecran) {
		super(new GridBagLayout());
		regles = new Regles();
		spTailleMin = new JRegleIntSpinner(regles, Regle.TAILLE_MIN, Regles.DEFAULT_TAILLE_MIN, 10);
		spTourMax = new JRegleIntSpinner(regles, Regle.TOUR_MAX, 1, 10);
		spScoreCible = new JRegleIntSpinner(regles, Regle.SCORE_CIBLE, 10, 500);
		spDureeSablier = new JRegleIntSpinner(regles, Regle.DUREE_SABLIER, Regles.DEFAULT_DUREE_SABLIER_MIN, 60 * 5);
		pPoints = new JReglePoints(regles);
		comboDes = new JReglesComboPath(regles, Regle.FICHIER_DES, "des-");
		comboDico = new JReglesComboPath(regles, Regle.FICHIER_DICO, "dict-");
		charger = new JReglesComboRegles(this);
		tourIllimite = new JCheckBox();
		scoreIllimite = new JCheckBox();
		sauvegarder = new BoutonMenu(new JButton("Sauvegarder règles"));
		demarrer = new BoutonMenu(new JButton("Démarrer partie"));
		cstr = new GridBagConstraints();
		
		int colv = 1;
		this.add(charger, contraintes(0, 0, 2, 1));
		this.add(sauvegarder, contraintes(1, 0, 2, 1));
		this.add(new JLabel("Taille minimale des mots"), contraintes(0, 1));
		this.add(spTailleMin, contraintes(colv, 1));
		this.add(new JLabel("Nombre de tours"), contraintes(0, 2));
		this.add(spTourMax, contraintes(colv, 2));
		this.add(tourIllimite, contraintes(colv + 1, 2));
		this.add(new JLabel("Tour illimité"), contraintes(colv + 2, 2));
		this.add(new JLabel("Score cible"), contraintes(0, 3));
		this.add(spScoreCible, contraintes(colv, 3));
		this.add(scoreIllimite, contraintes(colv + 1, 3));
		this.add(new JLabel("Score illimité"), contraintes(colv + 2, 3));
		this.add(new JLabel("Durée du sablier"), contraintes(0, 4));
		this.add(spDureeSablier, contraintes(colv, 4));
		this.add(new JLabel("Points"), contraintes(0, 5));
		this.add(pPoints, contraintes(1, 5, Regles.DEFAULT_NOMBRE_POINTS, 1));
		this.add(new JLabel("Des"), contraintes(0, 6));
		this.add(comboDes, contraintes(colv, 6));
		this.add(new JLabel("Dictionnaire"), contraintes(0, 7));
		this.add(comboDico, contraintes(colv, 7));
		this.add(demarrer, contraintes(colv, 8, 2, 1));
		
		tourIllimite.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (scoreIllimite.isSelected()) {
					tourIllimite.setSelected(false);
				}
				else {
					spTourMax.setEnabled(!tourIllimite.isSelected());
					int value = -1;
					if (spTourMax.isEnabled()) {
						value =  (int) spTourMax.getValue();
					}
					regles.setRegle(Regle.TOUR_MAX, value);
				}
			}
			
		});
		
		scoreIllimite.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (tourIllimite.isSelected()) {
					scoreIllimite.setSelected(false);
				}
				else {
					spScoreCible.setEnabled(!scoreIllimite.isSelected());
					int value = -1;
					if (spScoreCible.isEnabled()) {
						value =  (int) spScoreCible.getValue();
					}
					regles.setRegle(Regle.SCORE_CIBLE, value);
				}
			}
			
		});
		
		sauvegarder.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				sauvegarder();
			}
			
		});
		demarrer.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				final String initialText = demarrer.getText();
				demarrer.setText("Chargement...");
				SwingUtilities.invokeLater(new Runnable()  {

					public void run() {
						ecran.demarrer();
						demarrer.setText(initialText);
					}
					
				});
			}
			
		});
		
		if (charger.getItemCount() > 0) {
			changerReglesPar(charger.getItemAt(0));
		}
	}
	
	/**
	 * Retourne l'instance de Regles modifiée par la vue
	 * 
	 * @return	l'instance de Regles courante
	 */
	public Regles getReglesCourantes() {
		return regles;
	}
	
	/**
	 * Sauvegarde les règles définies par l'utilisateur
	 */
	public void sauvegarder() {
		String input = JOptionPane.showInputDialog(null, "Nom du fichier: (regles-", "NouvellesRegles");
		if (input != null) {
			if (input.trim().length() == 0) {
				JOptionPane.showMessageDialog(null, input + " n'est pas un nom de fichier correct.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				String fichier = "regles-" + input;
				if (!fichier.endsWith(".config")) {
					fichier += ".config";
				}
				regles.setTitre(fichier);
				regles.sauvegarder();
				int exists = charger.indexOf(fichier);
				Regles clone = regles.clone();
				if (exists == -1) {
					charger.addItem(clone);
					charger.setSelectedItem(clone);
				}
				else {
					int confirm = JOptionPane.showConfirmDialog(null,
							"Le fichier " + fichier + " existe déjà. Souhaitez-vous l'écraser ?",
							"Attention",
							JOptionPane.YES_OPTION);
					if (confirm == JOptionPane.YES_OPTION) {
						charger.removeItemAt(exists);
						charger.insertItemAt(clone, exists);
						charger.setSelectedIndex(exists);
					}
				}
			}
		}
	}
	
	// Définit des contraintes d'affichage
	private GridBagConstraints contraintes(int gridx, int gridy) {
		return contraintes(gridx, gridy, 1, 1);
	}
	
	// Définit des contraintes d'affichage
	private GridBagConstraints contraintes(int gridx, int gridy, int gridwidth, int gridheight) {
		cstr.gridx = gridx;
		cstr.gridy = gridy;
		cstr.gridwidth = gridwidth;
		cstr.gridheight = gridheight;
		cstr.insets = new Insets(7, 15, 15, 0);
		cstr.anchor = GridBagConstraints.BASELINE_LEADING;
		return cstr;
	}

	/**
	 * Change les valeurs des règles courante par d'autres
	 * en définissant les valeurs des différents composants du panel
	 * 
	 * @param	item
	 * 			les nouvelles règles à appliquer
	 */
	public void changerReglesPar(Regles item) {
		spTailleMin.setValue(item.getInt(Regle.TAILLE_MIN));
		spTourMax.setValue(item.getInt(Regle.TOUR_MAX));
		spScoreCible.setValue(item.getInt(Regle.SCORE_CIBLE));
		spDureeSablier.setValue(item.getInt(Regle.DUREE_SABLIER));
		pPoints.setValues(item.getIntArray(Regle.POINTS));
		comboDes.setSelectedItem(Paths.get(item.getString(Regle.FICHIER_DES)));
		comboDico.setSelectedItem(Paths.get(item.getString(Regle.FICHIER_DICO)));
	}
	

}
