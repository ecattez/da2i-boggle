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

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import boggle.BoggleException;
import boggle.gui.decorateur.DecorateurBoutonMenu;
import boggle.jeu.Regles;
import boggle.jeu.Regles.Regle;

public class ReglesPanel extends JPanel {
	
	private static final long serialVersionUID = 8335947767765664236L;

	private GridBagConstraints c;
	
	private Regles regles = new Regles();
	private JSpinner spinnerTailleMin = new JSpinner(new SpinnerNumberModel(3, 3, 10, 1));;
	private JSpinner spinnerTourMax = new JSpinner(new SpinnerNumberModel(10, -1, 100, 1));;
	private JSpinner spinnerScoreCible = new JSpinner(new SpinnerNumberModel(50, -1, 100, 1));;
	private JSpinner spinnerDureeSablier = new JSpinner(new SpinnerNumberModel(60, 30, 60 * 5, 1));
	private JSpinner[] spinnerPoints;
	private JComboBox<Path> comboDes;
	private JComboBox<Path> comboDico;
	
	private JComboBox<Regles> charger = new JComboBox<>();
	private JButton sauvegarder = new DecorateurBoutonMenu(new JButton("Sauvegarder règles"));
	private JButton demarrer = new DecorateurBoutonMenu(new JButton("Démarrer partie"));

	public ReglesPanel(final EcranNouvellePartie ecran) {
		super(new GridBagLayout());
		int colv = 2;
		
		this.c = new GridBagConstraints();
		this.initialiser();
		
		JPanel panelPoints = new JPanel(new FlowLayout(FlowLayout.LEADING));
		for (JSpinner point : spinnerPoints) {
			panelPoints.add(point);
		}
		
		this.add(charger, contraintes(0, 0, 2, 1));
		this.add(sauvegarder, contraintes(1, 0, 2, 1));
		this.add(new JLabel("Taille minimale des mots"), contraintes(0, 1));
		this.add(spinnerTailleMin, contraintes(colv, 1));
		this.add(new JLabel("Nombre de tours"), contraintes(0, 2));
		this.add(spinnerTourMax, contraintes(colv, 2));
		this.add(new JLabel("Score cible"), contraintes(0, 3));
		this.add(spinnerScoreCible, contraintes(colv, 3));
		this.add(new JLabel("Durée du sablier"), contraintes(0, 4));
		this.add(spinnerDureeSablier, contraintes(colv, 4));
		this.add(new JLabel("Points"), contraintes(0, 5));
		this.add(panelPoints, contraintes(1, 5, spinnerPoints.length, 1));
		this.add(new JLabel("Des"), contraintes(0, 6));
		this.add(comboDes, contraintes(colv, 6));
		this.add(new JLabel("Dictionnaire"), contraintes(0, 7));
		this.add(comboDico, contraintes(colv, 7));
		this.add(demarrer, contraintes(2, 8, 2, 1));
		
		charger.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				regles = (Regles) e.getItem();
				charger();
			}
			
		});
		sauvegarder.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				sauvegarder();
			}
			
		});
		demarrer.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				ecran.demarrer();
			}
			
		});
	}
	
	public Regles getReglesCourantes() {
		return regles = construireRegles();
	}
	
	public Regles construireRegles() {
		Regles clone = regles.clone();
		clone.setRegle(Regle.TAILLE_MIN, getValue(spinnerTailleMin));
		clone.setRegle(Regle.TOUR_MAX, getValue(spinnerTourMax));
		clone.setRegle(Regle.SCORE_CIBLE, getValue(spinnerScoreCible));
		clone.setRegle(Regle.DUREE_SABLIER, getValue(spinnerDureeSablier));
		clone.setRegle(Regle.FICHIER_DES, comboDes.getSelectedItem().toString());
		clone.setRegle(Regle.FICHIER_DICO, comboDico.getSelectedItem().toString());
		int[] pts = clone.getIntArray(Regle.POINTS);
		for (int i=0; i < pts.length; i++) {
			pts[i] = getValue(spinnerPoints[i]);
		}
		clone.setRegle(Regle.POINTS, pts);
		return clone;
	}
	
	private void initialiser() {
		Path root = Paths.get("config");
		try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(root)) {
			for (Path path : directoryStream) {
				if (path.getFileName().toString().startsWith("regles-")) {
					charger.addItem(Regles.chargerRegles(path));
				}
			}
		} catch (IOException e) {
			throw new BoggleException("Une erreur s'est produite à la lecture du dossier " + root + "\n" + e);
		}
		charger();
	}
	
	private void initPoints() {
		int[] values = regles.getIntArray(Regle.POINTS);
		spinnerPoints = new JSpinner[values.length];
		for (int i=0; i < spinnerPoints.length; i++) {
			spinnerPoints[i] = new JSpinner(new SpinnerNumberModel(values[i], 1, 42, 1));
		}
	}
	
	private void initCombo() {
		Path root = Paths.get("config");
		comboDes = new JComboBox<>();
		comboDico = new JComboBox<>();
		try (DirectoryStream<Path> folder = Files.newDirectoryStream(root)) {
			Path fichier;
			for (Path chemin : folder) {
				fichier = chemin.getFileName();
				if (fichier.toString().startsWith("des-")) {
					comboDes.addItem(chemin);
				}
				else if (fichier.toString().startsWith("dict-")) {
					comboDico.addItem(chemin);
				}
			}
		} catch (IOException e) {
			throw new BoggleException("Une erreur s'est produite à la lecture du dossier " + root + "\n" + e);
		}
		regles.setRegle(Regle.FICHIER_DES, comboDes.getSelectedItem().toString());
		regles.setRegle(Regle.FICHIER_DICO, comboDico.getSelectedItem().toString());
	}
	
	private void charger() {
		spinnerTailleMin.setValue(regles.getInt(Regle.TAILLE_MIN));
		spinnerTourMax.setValue(regles.getInt(Regle.TOUR_MAX));
		spinnerScoreCible.setValue(regles.getInt(Regle.SCORE_CIBLE));
		spinnerDureeSablier.setValue(regles.getInt(Regle.DUREE_SABLIER));
		initCombo();
		initPoints();
	}
	
	public void sauvegarder() {
		String input = JOptionPane.showInputDialog("Nom du fichier: (regles-");
		if (input != null) {
			if (input.trim().length() == 0) {
				JOptionPane.showMessageDialog(this, input + " n'est pas un nom de fichier correct.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				String fichier = "regles-" + input;
				if (!fichier.endsWith(".config")) {
					fichier += ".config";
				}
				regles = construireRegles();
				regles.setTitre(fichier);
				regles.sauvegarder();
				charger.addItem(regles);
				charger.setSelectedItem(regles);
			}
		}
	}
	
	public GridBagConstraints contraintes(int gridx, int gridy) {
		return contraintes(gridx, gridy, 1, 1);
	}
	
	public GridBagConstraints contraintes(int gridx, int gridy, int gridwidth, int gridheight) {
		c.gridx = gridx;
		c.gridy = gridy;
		c.gridwidth = gridwidth;
		c.gridheight = gridheight;
		c.insets = new Insets(7, 15, 15, 0);
		c.anchor = GridBagConstraints.BASELINE_LEADING;
		return c;
	}
	
	private int getValue(JSpinner s) {
		return (int) s.getValue();
	}

}
