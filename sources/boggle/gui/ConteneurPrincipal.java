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
	public static final String ECRAN_JEU = "ECRAN_JEU";
	public static final String CLASSEMENT = "CLASSEMENT";

	private JButton nouvellePartie;
	private JButton menuPrincipal;
	private JButton jouer;
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
		//card.first(Container parent)
		//card.previous(parent)
		//card.next(parent)
		//card.show(parent, name)
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
		}
	}
	

	public class ConteneurBouton extends JPanel {

		public ConteneurBouton() {

			super();
			nouvellePartie = new JButton("Nouvelle Partie");
			menuPrincipal = new JButton("Menu Principale");
			jouer = new JButton("Jouer");
			classement = new JButton("classement");
			this.add(nouvellePartie);
			this.add(jouer);
			this.add(menuPrincipal);
			this.add(classement);

			// jouer correspond a la configuration de la partie ( nb joueurs etc )
			jouer.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					card.show(conteneurCarte, NOUVELLE_PARTIE);
					
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

					

				}
			});
			
			classement.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
				
					card.show(conteneurCarte, CLASSEMENT);
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

		public void afficherToutbg() {
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
			this.recharger();

		}

		public void recharger() {
			conteneurBouton.cacherBouton(menuPrincipal,nouvellePartie);
		}
	}

	public class EcranNouvellePartie extends Ecran {

		public EcranNouvellePartie() {
			super();
			this.setBackground(Color.GREEN);
			this.add(new NouvellePartie());
		}

		public void recharger() {
			conteneurBouton.cacherBouton(nouvellePartie);
		}
	}

	public abstract class Ecran extends JPanel {

		public Ecran(){}
		public Ecran(LayoutManager layout){
			super(layout);
		}
		
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
			this.setBackground(Color.RED);
			
		}

	
		public void recharger() {
			conteneurBouton.cacherBouton(jouer);
			
		}

	}

}

