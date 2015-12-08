package boggle.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JSpinner;
import javax.swing.JTextArea;

import javax.swing.JTextField;

public class NouvellePartie extends JPanel {

	private JPanel ouest;
	private JPanel centre;
	
	
	public NouvellePartie(){
		
		super(new BorderLayout());
		this.setBackground(Color.GRAY);
		//this.setPreferredSize(new Dimension(500,500));
		
		this.ouest= new JPanel();
	
		
		ouest.setLayout(new BoxLayout(ouest, BoxLayout.PAGE_AXIS));
		ouest.add(new JoueurPanel());
		
		
		this.centre = new JPanel();
		
		centre.setLayout(new BorderLayout());
		centre.add(new PartiePanel());
		centre.setBorder(BorderFactory.createLineBorder(Color.CYAN));
		
		
		
		
		
		this.add(ouest,BorderLayout.WEST);
		this.add(centre,BorderLayout.CENTER);
	}
	
	

	public class JoueurPanel extends JPanel {

		public JoueurPanel() {
		
		this.setBackground(Color.BLACK);
		JPanel choixListe = new JPanel();
		choixListe.setBackground(Color.BLUE);
		JButton plus = new JButton("+");
		JButton moins = new JButton("-");
		choixListe.add(moins);
		choixListe.add(plus);
		choixListe.add(new JTextField("Joueur 1"));
		String[] selection = {"humain" , "AI easy" , "AI hard"};
		JComboBox typeJoueur = new JComboBox(selection);
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
			
			JLabel tailleMin = new JLabel("Taille Minimal de la grille");
			c.weightx = 5;
			c.weighty = 10;
			c.gridx = 0;
			c.gridy = 0;
			c.insets = new Insets(7, 15, 15, 0);
			c.anchor = GridBagConstraints.BASELINE_LEADING;
		
			JSpinner taille = new JSpinner();
			GridBagConstraints t = new GridBagConstraints();
			t.weightx = 5;
			t.weighty = 10;
			t.gridx = 1;
			t.gridy = 0;
			t.insets = new Insets(7, 15, 15, 0);
			t.anchor = GridBagConstraints.BASELINE_LEADING;
			
			
			JPanel sud = new JPanel();
			sud.setBackground(Color.CYAN);
			
			
			JLabel tourMax = new JLabel("Nombre de tours maximum");
			GridBagConstraints c1 = new GridBagConstraints();
			c1.weightx = 5;
			c1.weighty = 10;
			c1.gridx = 0;
			c1.gridy = 1;
			c1.insets = new Insets(7, 15, 15, 0);
			c1.anchor = GridBagConstraints.BASELINE_LEADING;
			
			JSpinner tour = new JSpinner();
			GridBagConstraints t1 = new GridBagConstraints();
			t1.weightx = 5;
			t1.weighty = 10;
			t1.gridx = 1;
			t1.gridy = 1;
			t1.insets = new Insets(7, 15, 15, 0);
			t1.anchor = GridBagConstraints.BASELINE_LEADING;
			
			
			JLabel scoreCible = new JLabel("Score Cible");
			GridBagConstraints c2 = new GridBagConstraints();
			c2.weightx = 5;
			c2.weighty = 10;
			c2.gridx = 0;
			c2.gridy = 2;
			c2.insets = new Insets(7, 15, 15, 0);
			c2.anchor = GridBagConstraints.BASELINE_LEADING;
			
			JSpinner score = new JSpinner();
			GridBagConstraints s = new GridBagConstraints();
			s.weightx = 5;
			s.weighty = 10;
			s.gridx = 2;
			s.gridy = 2;
			s.insets = new Insets(7, 15, 15, 0);
			s.anchor = GridBagConstraints.BASELINE_LEADING;
			
			
			JLabel dureeSablier = new JLabel("Durée du sablier");
			GridBagConstraints c3 = new GridBagConstraints();
			c3.weightx = 5;
			c3.weighty = 10;
			c3.gridx = 0;
			c3.gridy = 3;
			c3.insets = new Insets(7, 15, 15, 0);
			c3.anchor = GridBagConstraints.BASELINE_LEADING;
			
			JSpinner time = new JSpinner();
			GridBagConstraints t2 = new GridBagConstraints();
			t2.weightx = 5;
			t2.weighty = 10;
			t2.gridx = 3;
			t2.gridy = 3;
			t2.insets = new Insets(7, 15, 15, 0);
			t2.anchor = GridBagConstraints.BASELINE_LEADING;
		
			JLabel points = new JLabel("Points");
			GridBagConstraints c4 = new GridBagConstraints();
			c4.weightx = 5;
			c4.weighty = 10;
			c4.gridx = 0;
			c4.gridy = 4;
			c4.insets = new Insets(7, 15, 15, 0);
			c4.anchor = GridBagConstraints.BASELINE_LEADING;
			
			JSpinner point = new JSpinner();
			GridBagConstraints p = new GridBagConstraints();
			p.weightx = 5;
			p.weighty = 10;
			p.gridx = 4;
			p.gridy = 4;
			p.insets = new Insets(7, 15, 15, 0);
			p.anchor = GridBagConstraints.BASELINE_LEADING;
			
			JLabel de = new JLabel("De");
			GridBagConstraints c5 = new GridBagConstraints();
			c5.weightx = 5;
			c5.weighty = 10;
			c5.gridx = 0;
			c5.gridy = 5;
			c5.insets = new Insets(7, 15, 15, 0);
			c5.anchor = GridBagConstraints.BASELINE_LEADING;
			
			JSpinner des = new JSpinner();
			GridBagConstraints d = new GridBagConstraints();
			d.weightx = 5;
			d.weighty = 10;
			d.gridx = 5;
			d.gridy = 5;
			d.insets = new Insets(7, 15, 15, 0);
			d.anchor = GridBagConstraints.BASELINE_LEADING;
			
			JLabel dictionnaire = new JLabel("Dictionnaire");
			GridBagConstraints c6 = new GridBagConstraints();
			c6.weightx = 5;
			c6.weighty = 10;
			c6.gridx = 0;
			c6.gridy = 6;
			c6.insets = new Insets(7, 15, 15, 0);
			c6.anchor = GridBagConstraints.BASELINE_LEADING;
			
			JSpinner dic = new JSpinner();
			GridBagConstraints d1 = new GridBagConstraints();
			d1.weightx = 5;
			d1.weighty = 10;
			d1.gridx = 6;
			d1.gridy = 6;
			d1.insets = new Insets(7, 15, 15, 0);
			d1.anchor = GridBagConstraints.BASELINE_LEADING;
			
	
			
			JButton boutonDemarrer = new JButton("Demarrer");
			/**boutonDemarrer.addActionListener(new ActionListener(){

				
				public void actionPerformed(ActionEvent e) {
					ConteneurPrincipal.card.show(conteneurCarte, ECRAN_JEU);
					
				}
				
			});**/
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
			centre.add(de,c5);
			centre.add(des,d);
			centre.add(dictionnaire,c6);
			centre.add(dic,d1);
			
			this.add(nord, BorderLayout.NORTH);
			this.add(sud, BorderLayout.SOUTH);
			this.add(centre, BorderLayout.CENTER);
		}
	}
}