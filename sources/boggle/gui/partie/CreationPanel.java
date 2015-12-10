package boggle.gui.partie;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import boggle.BoggleException;
import boggle.jeu.Regles;

public class CreationPanel extends JPanel {
	
	private GridBagConstraints c;
	
	private Regles regles = new Regles();
	private JSpinner spinnerTailleMin = new JSpinner(new SpinnerNumberModel(regles.getTailleMin(), 3, 10, 1));;
	private JSpinner spinnerTourMax = new JSpinner(new SpinnerNumberModel(regles.getTourMax(), -1, 100, 1));;
	private JSpinner spinnerScoreCible = new JSpinner(new SpinnerNumberModel(regles.getScoreCible(), -1, 100, 1));;
	private JSpinner spinnerDureeSablier = new JSpinner(new SpinnerNumberModel(regles.getDureeSablier(), 1, 60 * 5, 1));;
	private JSpinner[] spinnerPoints;
	private JComboBox<Path> comboDes;
	private JComboBox<Path> comboDico;
	
	private JComboBox<Regles> charger = new JComboBox<>();
	private JButton sauvegarder = new JButton("Sauvegarder règles");
	private JButton demarrer = new JButton("Démarrer partie");

	public CreationPanel() {
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
		this.add(demarrer, contraintes(0, 8, 2, 1));
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
	}
	
	public Regles getRegles() {
		try {
			regles.setTailleMin(getValue(spinnerTailleMin));
			regles.setTourMax(getValue(spinnerTourMax));
			regles.setScoreCible(getValue(spinnerScoreCible));
			regles.setDureeSablier(getValue(spinnerDureeSablier));
			regles.setFichierDes((Path) comboDes.getSelectedItem());
			regles.setFichierDictionnaire((Path) comboDico.getSelectedItem());
			int[] pts = regles.getPoints();
			for (int i=0; i < pts.length; i++) {
				pts[i] = getValue(spinnerPoints[i]);
			}
			regles.setPoints(pts);
			return regles;
		} catch (BoggleException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
		return null;
	}
	
	private void initialiser() {
		Path root = Paths.get("config");
		charger.removeAllItems();
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
	
	private void charger() {
		spinnerTailleMin.setValue(regles.getTailleMin());
		spinnerTourMax.setValue(regles.getTourMax());
		spinnerScoreCible.setValue(regles.getScoreCible());
		spinnerDureeSablier.setValue(regles.getDureeSablier());
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
				if (!fichier.endsWith(".[.]+")) {
					fichier += ".config";
				}
				Regles regles = getRegles();
				regles.setTitre(fichier);
				regles.sauvegarder(Paths.get("config", fichier));
				charger.addItem(regles);
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
	
	private void initPoints() {
		spinnerPoints = new JSpinner[Regles.DEFAULT_POINTS.length];
		for (int i=0; i < spinnerPoints.length; i++) {
			spinnerPoints[i] = new JSpinner(new SpinnerNumberModel(regles.getPoints()[i], 1, 42, 1));
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
		comboDes.setSelectedItem(regles.getFichierDes());
		comboDico.setSelectedItem(regles.getFichierDictionnaire());
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		CreationPanel c = new CreationPanel();
		f.setContentPane(c);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

}
