package boggle.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import boggle.jeu.Partie;
import boggle.jeu.Regles;
import boggle.jeu.joueur.Humain;
import boggle.jeu.joueur.IAHardcore;
import boggle.jeu.joueur.Joueur;
import boggle.mots.ArbreLexical;
import boggle.mots.De;

public class NouvellePartie extends JPanel {

	 private JSpinner taille;
	 private JSpinner tour;
	 private JSpinner score;
	 private JSpinner time;
	 private JSpinner point;
	 private JSpinner point1;
	 private JSpinner point2;
	 private JSpinner point3;
	 private JSpinner point4;
	 private JSpinner point5;
	 private JComboBox<Path> des;
	 private JComboBox<Path> dic;
	 private JPanel ouest;
	 private JPanel centre;
	 private Regles regles;
	
	public NouvellePartie(){
		
		super(new BorderLayout());
		regles = new Regles("regles-4x4.config");
		this.setBackground(Color.GRAY);
		//this.setPreferredSize(new Dimension(1000,1000));
		
		this.ouest= new JPanel();
	
		
		ouest.setLayout(new BoxLayout(ouest, BoxLayout.PAGE_AXIS));
		ouest.add(new JoueurPanel());
		
		
		this.centre = new JPanel();
		
		centre.setLayout(new BorderLayout());
		centre.add(new PartiePanel());
		centre.setBorder(BorderFactory.createLineBorder(Color.CYAN));
		
		// ne fonctionne pas
		JScrollPane scroll = new JScrollPane(ouest);
		
		
		
		this.add(scroll,BorderLayout.WEST);
		this.add(centre,BorderLayout.CENTER);
	}
	
	

	public class JoueurPanel extends JPanel {
		
		private JTextField zoneTexte;
		private JComboBox typeJoueur;
		
		public JoueurPanel() {
		
		this.setBackground(Color.BLACK);
		JPanel choixListe = new JPanel();
		choixListe.setBackground(Color.BLUE);
		JButton plus = new JButton("+");
		JButton moins = new JButton("-");
		choixListe.add(moins);
		choixListe.add(plus);
		zoneTexte = new JTextField("Joueur 1");
		choixListe.add(zoneTexte);
		String[] selection = {"humain" , "AI easy" , "AI hard"};
		typeJoueur = new JComboBox(selection);
		choixListe.add(typeJoueur);

		this.add(choixListe); 
		moins.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(ouest.getComponentCount()>1) {
				ouest.remove(JoueurPanel.this);
				ouest.validate();
				}
			}
		});
		
		plus.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				ouest.add(new JoueurPanel());
				ouest.validate();
				
			}
		});
		}
		
		public String recupText() {
			return	zoneTexte.getText();
		}
		
		public Object RecupTypeJoueur(){
			return typeJoueur.getSelectedItem();
		}
		
		
	}
	
	public class PartiePanel extends JPanel {
		
	
		
		
		public PartiePanel() {
			super(new BorderLayout());
			this.setBackground(Color.YELLOW);
			
			//EVITER DE FIXER DES TAILLES
			this.setPreferredSize(new Dimension(500,500));
			
			JPanel nord = new JPanel();
			nord.setBackground(Color.MAGENTA);
			
			JPanel centre = new JPanel(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			centre.setBackground(Color.PINK);
			
			JLabel tailleMin = new JLabel("Taille de la grille");
			c.weightx = 5;
			c.weighty = 5;
			c.gridx = 0;
			c.gridy = 0;
			c.insets = new Insets(7, 15, 15, 0);
			c.anchor = GridBagConstraints.BASELINE_LEADING;
		
			taille = new JSpinner(new SpinnerNumberModel(regles.getTailleMin(), 3, 10, 1));
			GridBagConstraints t = new GridBagConstraints();
			t.weightx = 5;
			t.weighty = 5;
			t.gridx = 4;
			t.gridy = 0;
			t.insets = new Insets(7, 15, 15, 0);
			t.anchor = GridBagConstraints.BASELINE_LEADING;
			
			
			JPanel sud = new JPanel();
			sud.setBackground(Color.CYAN);
			
			
			JLabel tourMax = new JLabel("Nombre de tours");
			GridBagConstraints c1 = new GridBagConstraints();
			c1.weightx = 5;
			c1.weighty = 5;
			c1.gridx = 0;
			c1.gridy = 1;
			c1.insets = new Insets(7, 15, 15, 0);
			c1.anchor = GridBagConstraints.BASELINE_LEADING;
			
			tour = new JSpinner(new SpinnerNumberModel(1, 1, regles.getTourMax(), 1));
			GridBagConstraints t1 = new GridBagConstraints();
			t1.weightx = 0;
			t1.weighty = 0;
			t1.gridx = 4;
			t1.gridy = 1;
			t1.insets = new Insets(7, 15, 15, 0);
			t1.anchor = GridBagConstraints.BASELINE_LEADING;
			
			
			JLabel scoreCible = new JLabel("Score Cible");
			GridBagConstraints c2 = new GridBagConstraints();
			c2.weightx = 5;
			c2.weighty = 5;
			c2.gridx = 0;
			c2.gridy = 2;
			c2.insets = new Insets(7, 15, 15, 0);
			c2.anchor = GridBagConstraints.BASELINE_LEADING;
			
			// voir pour modifier le pas du spinner en fonction du nombre de point obtenu par le plus petit mot realisable
			score = new JSpinner(new SpinnerNumberModel(0, 0, regles.getScoreCible(), 1));
			GridBagConstraints s = new GridBagConstraints();
			s.weightx = 5;
			s.weighty = 5;
			s.gridx = 4;
			s.gridy = 2;
			s.insets = new Insets(7, 15, 15, 0);
			s.anchor = GridBagConstraints.BASELINE_LEADING;
			
			
			JLabel dureeSablier = new JLabel("Durée du sablier");
			GridBagConstraints c3 = new GridBagConstraints();
			c3.weightx = 5;
			c3.weighty = 5;
			c3.gridx = 0;
			c3.gridy = 3;
			c3.insets = new Insets(7, 15, 15, 0);
			c3.anchor = GridBagConstraints.BASELINE_LEADING;
			
			time = new JSpinner();
			GridBagConstraints t2 = new GridBagConstraints();
			t2.weightx = 5;
			t2.weighty = 5;
			t2.gridx = 4;	 
			t2.gridy = 3;
			t2.insets = new Insets(7, 15, 15, 0);
			t2.anchor = GridBagConstraints.BASELINE_LEADING;
		
			JLabel points = new JLabel("Points");
			GridBagConstraints c4 = new GridBagConstraints();
			c4.weightx = 5;
			c4.weighty = 5;
			c4.gridx = 0;
			c4.gridy = 4;
			c4.insets = new Insets(7, 15, 15, 0);
			c4.anchor = GridBagConstraints.BASELINE_LEADING;
			
			point = new JSpinner();
			GridBagConstraints p = new GridBagConstraints();
			p.weightx = 5;
			p.weighty = 5;
			p.gridwidth = 1;
			p.gridx = 1;
			p.gridy = 4;
			p.insets = new Insets(7, 15, 15, 0);
			p.anchor = GridBagConstraints.BASELINE_LEADING;
			

			point1 = new JSpinner();
			GridBagConstraints p1 = new GridBagConstraints();
			p1.weightx = 5;
			p1.weighty = 5;
			p1.gridwidth = 1;
			p1.gridx = 3;
			p1.gridy = 4;
			p1.insets = new Insets(7, 15, 15, 0);
			p1.anchor = GridBagConstraints.BASELINE_LEADING;
			

			point2 = new JSpinner();
			GridBagConstraints p2 = new GridBagConstraints();
			p2.weightx = 5;
			p2.weighty = 5;
			p2.gridwidth = 1;
			p2.gridx = 4;
			p2.gridy = 4;
			p2.insets = new Insets(7, 15, 15, 0);
			p2.anchor = GridBagConstraints.BASELINE_LEADING;
			
			point3 = new JSpinner();
			GridBagConstraints p3 = new GridBagConstraints();
			p3.weightx = 5;
			p3.weighty = 5;
			p3.gridwidth = 1;
			p3.gridx = 5;
			p3.gridy = 4;
			p3.insets = new Insets(7, 15, 15, 0);
			p3.anchor = GridBagConstraints.BASELINE_LEADING;
			
			point4 = new JSpinner();
			GridBagConstraints p4 = new GridBagConstraints();
			p4.weightx = 5;
			p4.weighty = 5;
			p4.gridwidth = 1;
			p4.gridx = 6;
			p4.gridy = 4;
			p4.insets = new Insets(7, 15, 15, 0);
			p4.anchor = GridBagConstraints.BASELINE_LEADING;
			
			point5 = new JSpinner();
			GridBagConstraints p5 = new GridBagConstraints();
			p5.weightx = 5;
			p5.weighty = 5;
			p5.gridwidth = 1;
			p5.gridx = 6;
			p5.gridy = 4;
			p5.insets = new Insets(7, 15, 15, 0);
			p5.anchor = GridBagConstraints.BASELINE_LEADING;
			
			JLabel de = new JLabel("De");
			GridBagConstraints c5 = new GridBagConstraints();
			c5.weightx = 2;
			c5.weighty = 5;
			c5.gridx = 0;
			c5.gridy = 5;
			c5.insets = new Insets(7, 15, 15, 0);
			c5.anchor = GridBagConstraints.BASELINE_LEADING;
			
			des = new JComboBox<Path>();
			try (DirectoryStream<Path> folder = Files.newDirectoryStream(Paths.get("config"))) {
				Path fichier;
				for (Path chemin : folder) {
					fichier = chemin.getFileName();
					if (fichier.toString().startsWith("des-")) {
						des.addItem(chemin);
					}
				}
			} catch (IOException e) {
				// Erreur
			}
			
			GridBagConstraints d = new GridBagConstraints();
			d.weightx = 5;
			d.weighty = 5;
			d.gridx = 4;		
			d.gridy = 5;
			d.insets = new Insets(7, 15, 15, 0);
			d.anchor = GridBagConstraints.BASELINE_LEADING;
			
			JLabel dictionnaire = new JLabel("Dictionnaire");
			GridBagConstraints c6 = new GridBagConstraints();
			c6.weightx = 5;
			c6.weighty = 5;
			c6.gridx = 0;
			c6.gridy = 6;
			c6.insets = new Insets(7, 15, 15, 0);
			c6.anchor = GridBagConstraints.BASELINE_LEADING;
			
			
			dic = new JComboBox<Path>();
			try (DirectoryStream<Path> folder = Files.newDirectoryStream(Paths.get("config"))) {
				Path fichier;
				for (Path chemin : folder) {
					fichier = chemin.getFileName();
					if (fichier.toString().startsWith("dict-")) {
						dic.addItem(chemin);
					}
				}
			} catch (IOException e) {
				// Erreur
			}
		
			//	new JFileChooser(Paths.get("config").toString());
			GridBagConstraints d1 = new GridBagConstraints();
			d1.weightx = 5;
			d1.weighty = 5;
			d1.gridx = 4;
			d1.gridy = 6;
			d1.insets = new Insets(7, 15, 15, 0);
			d1.anchor = GridBagConstraints.BASELINE_LEADING;
			
	
			
			JButton boutonDemarrer = new JButton("Demarrer");
			boutonDemarrer.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent e) {
					int size = ouest.getComponentCount();
					Joueur[] joueurs = new Joueur[size];
					for(int i = 0 ; i < size; i++){
						JoueurPanel joueurPanel = (JoueurPanel)ouest.getComponent(i);
						if(joueurPanel.RecupTypeJoueur() == "humain") {
							joueurs[i] = new Humain(joueurPanel.recupText());
						} 
							joueurs[i] = new IAHardcore(joueurPanel.recupText());
					}
					
					// A changer
					regles.setDureeSablier(((int)taille.getValue()));
					regles.setScoreCible(((int)point.getValue()));
					regles.setTailleMin(((int)taille.getValue()));
					regles.setTourMax(((int)tour.getValue()));
					regles.setDictionnaire((ArbreLexical) dic.getSelectedItem());
					regles.setDes((De[]) des.getSelectedItem());
					
					
					Partie lancement = new Partie(null, joueurs);
					
				}
				
			});
			JButton boutonSauvConfig = new JButton("Sauvegarder Configuration");
			JButton boutonNomConfig = new JButton("Nom Configuration");
			JButton boutonImport = new JButton("Importer");
			
			nord.add(boutonNomConfig);
			nord.add(boutonImport);
			
			sud.add(boutonDemarrer);
			sud.add(boutonSauvConfig);
			
			
			
			centre.add(tailleMin,c);
			centre.add(taille,t);
			centre.add(tourMax,c1);
			centre.add(tour,t1);
			centre.add(scoreCible,c2);
			centre.add(score,s);
			centre.add(dureeSablier,c3);
			centre.add(time,t2);
			centre.add(points,c4);
			centre.add(point,p);
			centre.add(point1,p1);
			centre.add(point2,p2);
			centre.add(point3,p3);
			centre.add(point4,p4);
			centre.add(point5,p5);
			centre.add(de,c5);
			centre.add(des,d);
			centre.add(dictionnaire,c6);
			centre.add(dic,d1);
	
			
			/*Path dir = Paths.get("config", "dictionnaires");
			String[] dictionnaires = dir.toFile().list();*/
			//dic.showOpenDialog(centre);
			
			this.add(nord, BorderLayout.NORTH);
			this.add(sud, BorderLayout.SOUTH);
			this.add(centre, BorderLayout.CENTER);
		}
	}
}