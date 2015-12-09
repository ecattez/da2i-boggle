package boggle.gui.ecran;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import boggle.gui.Bucket;
import boggle.gui.ConteneurPrincipal;
import boggle.jeu.Classement;
import boggle.jeu.Partie;
import boggle.jeu.Regles;
import boggle.jeu.joueur.IAHardcore;
import boggle.jeu.joueur.Joueur;

public class EcranMenuPrincipal extends Ecran {
	
	public EcranMenuPrincipal(ConteneurPrincipal mainPanel) {
		super(mainPanel, new BorderLayout());
		
		JPanel centre = new JPanel();
		JLabel titre = new JLabel("Boggle");
		JPanel bas = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		JLabel auteurs = new JLabel("Edouard CATTEZ - Alexandre VASTRA");
		
		titre.setFont(new Font("Arial", Font.BOLD, 100));
		titre.setForeground(Color.WHITE);
		
		centre.add(titre);
		centre.setBackground(new Color(191,59,1));
		
		auteurs.setForeground(Color.WHITE);
		
		bas.add(auteurs);
		bas.setBackground(new Color(95,29,0));
		
		this.add(centre, BorderLayout.CENTER);
		this.add(bas, BorderLayout.SOUTH);
	}

	public void recharger() {
		mainPanel.cacherTout();
		mainPanel.afficherBouton(mainPanel.BOUTON_JOUER);
		mainPanel.afficherBouton(mainPanel.BOUTON_CLASSEMENT);
		
		Partie partie = Bucket.getInstance().getPartie();
		if (partie == null || partie.estTerminee()) {
			Joueur[] joueurs = { new IAHardcore("Picault"), new IAHardcore("Beaufils") };
			Regles regles = new Regles("regles-4x4.config");
			partie = new Partie(regles, joueurs);
			
			Bucket.getInstance().push(joueurs);
			Bucket.getInstance().push(regles);
			Bucket.getInstance().push(partie);
			
			System.out.println(joueurs);
			System.out.println(regles);
		}
	}


}
