package boggle.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ConteneurPrincipal extends JPanel {

	public static final String BOGGLE = "BOGGLE";
	public static final String NOUVELLE_PARTIE = "NOUVELLE_PARTIE";
	public static final String CONFIGURATION = "CONFIGURATION";
	public static final String ECRAN_JEU = "ECRAN_JEU";
	public static final String CLASSEMENT = "CLASSEMENT";
	
	private CardLayout card;
	private ConteneurCarte conteneurCarte;
	private ConteneurBouton conteneurBouton;

	public ConteneurPrincipal() {
		super(new BorderLayout());
		conteneurCarte = new ConteneurCarte();
		conteneurBouton = new ConteneurBouton();
		this.add(conteneurCarte, BorderLayout.CENTER);
		this.add(conteneurBouton, BorderLayout.SOUTH);
	}

	public class ConteneurCarte extends JPanel {
		
		public ConteneurCarte() {
			super();
			card = new CardLayout();
			this.setLayout(card);
			this.add(new EcranBoggle(), BOGGLE);
			this.add(new EcranNouvellePartie(), NOUVELLE_PARTIE);
			this.add(new EcranJeu(), ECRAN_JEU);
			this.add(new EcranClassement(), CLASSEMENT);
			this.add(new EcranConfiguration(), CONFIGURATION);	
		}

	}

	public class ConteneurBouton extends JPanel {

		private JButton nouvellePartie;
		private JButton menuPrincipal;
		private JButton jouer;
		private JButton configuration;
		private JButton suivant;
		private JButton precedent;


		
		public ConteneurBouton() {

			super();
			nouvellePartie = new JButton("Nouvelle Partie");
			menuPrincipal = new JButton("Menu Principale");
			jouer = new JButton("Jouer");
			configuration = new JButton("Configuration");
			suivant = new JButton("Suivant");
			precedent = new JButton("Précédent");
			this.add(nouvellePartie);
			this.add(jouer);
			this.add(configuration);
			this.add(suivant);
			this.add(precedent);
			this.add(menuPrincipal);
		
			jouer.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					card.show(conteneurCarte, NOUVELLE_PARTIE);
					
				}
			});
			
			configuration.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					card.show(conteneurCarte, CONFIGURATION);

				}
			});
			
			suivant.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					card.next(conteneurCarte);
					conteneurBouton.cacherTout();

				}
			});

			precedent.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					card.previous(conteneurCarte);

				}
			});
			
			menuPrincipal.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					card.first(conteneurCarte);
					
				}
			});
			
			nouvellePartie.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					card.show(conteneurCarte, NOUVELLE_PARTIE);
					
				}
			});

		}
		
		private void afficher(boolean oui){
			for(int i = 0 ; i < this.getComponentCount() ; i++) {
				this.getComponent(i).setVisible(oui);
			}
			
		}
		public void cacherTout() {
			this.afficher(false);
		}
		
		public void afficherTout() {
			this.afficher(true);
		}
		// les "..." designe un tableau
		public void cacherBouton(JButton... b) {
			this.afficherTout();
			for(int i = 0 ; i < b.length ; i++) {
				b[i].setVisible(false);
			}
		}
	
	}
}
