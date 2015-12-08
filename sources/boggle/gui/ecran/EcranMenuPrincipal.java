package boggle.gui.ecran;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import boggle.gui.ConteneurPrincipal;

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
	}


}
