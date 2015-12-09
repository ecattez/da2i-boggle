package boggle.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import boggle.gui.decorateur.DecorateurBoutonMenu;
import boggle.gui.ecran.EcranClassement;
import boggle.gui.ecran.EcranJeu;
import boggle.gui.ecran.EcranMenuPrincipal;
import boggle.gui.ecran.EcranNouvellePartie;


public class ConteneurPrincipal extends JPanel {
	
	private static ConteneurPrincipal mainPanel;
	
	public static ConteneurPrincipal getInstance() {
		if (mainPanel == null) {
			mainPanel = new ConteneurPrincipal();
		}
		return mainPanel;
	}

	public static final String ECRAN_MENU_PRINCIPAL = "MENU_PRINCIPAL";
	public static final String ECRAN_NOUVELLE_PARTIE = "NOUVELLE_PARTIE";
	public static final String ECRAN_JEU = "PARTIE";
	public static final String ECRAN_CLASSEMENT = "CLASSEMENT";

	public final JButton BOUTON_MENU_PRINCIPAL = new DecorateurBoutonMenu(new JButton("Menu Principal"));
	public final JButton BOUTON_NOUVELLE_PARTIE = new DecorateurBoutonMenu(new JButton("Nouvelle Partie"));
	public final JButton BOUTON_JOUER = new DecorateurBoutonMenu(new JButton("Jouer"));
	public final JButton BOUTON_CLASSEMENT = new DecorateurBoutonMenu(new JButton("Classement"));

	private CardLayout card = new CardLayout();
	private ConteneurBouton conteneurBouton = new ConteneurBouton();
	private ConteneurCarte conteneurCarte = new ConteneurCarte(); 

	private ConteneurPrincipal() {
		super(new BorderLayout());
		this.add(conteneurCarte, BorderLayout.CENTER);
		this.add(conteneurBouton, BorderLayout.SOUTH);
		this.initBoutons();
	}
	
	private void initBoutons() {
		BOUTON_MENU_PRINCIPAL.addActionListener(new ShowListener(ECRAN_MENU_PRINCIPAL));
		BOUTON_JOUER.addActionListener(new ShowListener(ECRAN_JEU));
		BOUTON_NOUVELLE_PARTIE.addActionListener(new ShowListener(ECRAN_NOUVELLE_PARTIE));
		BOUTON_CLASSEMENT.addActionListener(new ShowListener(ECRAN_CLASSEMENT));
	}
	
	public void first() {
		card.first(conteneurCarte);
	}
	
	public void last() {
		card.last(conteneurCarte);
	}
	
	public void next() {
		card.next(conteneurCarte);
	}
	
	public void previous() {
		card.previous(conteneurCarte);
	}
	
	public void show(String name) {
		card.show(conteneurCarte, name);
	}
	
	public void cacherTout() {
		conteneurBouton.afficher(false);
	}
	
	public void afficherTout() {
		conteneurBouton.afficher(true);
	}

	public void afficherBouton(JButton... bouton) {
		conteneurBouton.afficherBouton(bouton);
	}
	
	public void cacherBouton(JButton... bouton) {
		conteneurBouton.cacherBouton(bouton);
	}
	
	class ShowListener implements ActionListener {

		private String name;
		
		public ShowListener(String name) {
			this.name = name;
		}
		
		public void actionPerformed(ActionEvent e) {
			show(name);
		}
		
	}
	
	class ConteneurBouton extends JPanel {

		public ConteneurBouton() {
			super();
			this.add(BOUTON_NOUVELLE_PARTIE);
			this.add(BOUTON_JOUER);
			this.add(BOUTON_MENU_PRINCIPAL);
			this.add(BOUTON_CLASSEMENT);
			this.setBackground(Color.WHITE);
		}

		private void afficher(boolean visible){
			for(int i = 0 ; i < this.getComponentCount() ; i++) {
				this.getComponent(i).setVisible(visible);
			}
		}
		
		public void afficherBouton(JButton... bouton) {
			for(int i = 0 ; i < bouton.length ; i++) {
				bouton[i].setVisible(true);
			}
		}
		
		public void cacherBouton(JButton... bouton) {
			for(int i = 0 ; i < bouton.length ; i++) {
				bouton[i].setVisible(false);
			}
		}
		
		public void cacherTout() {
			this.afficher(false);
		}
		
		public void afficherTout() {
			this.afficher(true);
		}

	}

	class ConteneurCarte extends JPanel {

		public ConteneurCarte() {
			super(card);
			this.add(new EcranMenuPrincipal(ConteneurPrincipal.this), ECRAN_MENU_PRINCIPAL);
			this.add(new EcranNouvellePartie(ConteneurPrincipal.this), ECRAN_NOUVELLE_PARTIE);
			this.add(new EcranJeu(ConteneurPrincipal.this), ECRAN_JEU);
			this.add(new EcranClassement(ConteneurPrincipal.this), ECRAN_CLASSEMENT);
		}
		
	}

}

