package boggle.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ConteneurPrincipal extends JPanel {

	public static final String BOGGLE = "BOGGLE";
	public static final String NOUVELLE_PARTIE = "NOUVELLE_PARTIE";
	public static final String CONFIGURATION = "CONFIGURATION";
	public static final String ECRAN_JEU = "ECRAN_JEU";
	public static final String CLASSEMENT = "CLASSEMENT";

	private JButton nouvellePartie;
	private JButton menuPrincipal;
	private JButton jouer;
	private JButton configuration;
	private JButton suivant;
	private JButton precedent;
	private JButton classement;

	private CardLayout card;
	private ConteneurCarte conteneurCarte;
	private ConteneurBouton conteneurBouton;

	public ConteneurPrincipal() {
		super(new BorderLayout());
		conteneurBouton = new ConteneurBouton();
		conteneurCarte = new ConteneurCarte();
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

		public ConteneurBouton() {

			super();
			nouvellePartie = new JButton("Nouvelle Partie");
			menuPrincipal = new JButton("Menu Principale");
			jouer = new JButton("Jouer");
			configuration = new JButton("Configuration");
			suivant = new JButton("Suivant");
			precedent = new JButton("Précédent");
			classement = new JButton("classement");
			this.add(nouvellePartie);
			this.add(jouer);
			this.add(configuration);
			this.add(suivant);
			this.add(precedent);
			this.add(menuPrincipal);

			// jouer correspond a la configuration de la partie ( nb joueurs etc )
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
					conteneurBouton.cacherBouton(jouer);

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
					//conteneurBouton.afficherBouton(jouer,configuration,classement);
					


				}
			});

			nouvellePartie.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					card.show(conteneurCarte, NOUVELLE_PARTIE);

					conteneurBouton.afficherBouton(jouer,configuration,classement);

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

		public void afficherBouton(JButton... bouton) {
			for(int i = 0 ; i < bouton.length ; i++) {
				bouton[i].setVisible(true);
			}

		}

		public void afficherTout() {
			this.afficher(true);
		}
		// les "..." designe un tableau
		public void cacherBouton(JButton... bouton) {
		
			for(int i = 0 ; i < bouton.length ; i++) {
				bouton[i].setVisible(false);
			}
		}


	}

	public class EcranBoggle extends Ecran {

		public EcranBoggle() {
			super();
			this.setBackground(Color.CYAN);
			

		}

		public void recharger() {
			conteneurBouton.afficherBouton(jouer);
		}
	}

	public class EcranNouvellePartie extends Ecran {

		public EcranNouvellePartie() {
			super();
			this.setBackground(Color.ORANGE);

		}

		public void recharger() {
			conteneurBouton.cacherBouton(jouer);
		}
	}

	public abstract class Ecran extends JPanel {

		public abstract void recharger();

		public void setVisible(boolean visible)	{
			super.setVisible(visible);
			if(visible)
				recharger();
		}
	}
	
	
	
	public class EcranClassement extends Ecran {
		
		public EcranClassement() {
			super();
			this.setBackground(Color.GREEN);
		}

	
		public void recharger() {
			conteneurBouton.cacherBouton(jouer,suivant,precedent);
			
		}

	}

}
