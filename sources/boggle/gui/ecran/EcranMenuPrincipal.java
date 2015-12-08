package boggle.gui.ecran;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import boggle.gui.ConteneurPrincipal;

public class EcranMenuPrincipal extends Ecran {
	
	public EcranMenuPrincipal() {
		super(new BorderLayout());
		
		JPanel centre = new JPanel();
		JLabel titre = new JLabel("Boggle");
		titre.setFont(new Font("Arial", Font.BOLD, 100));
		centre.add(titre);
		
		JPanel bas = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		bas.add(new JLabel("Auteurs: Edouard CATTEZ - Alexandre VASTRA"));
		this.add(centre, BorderLayout.CENTER);
		this.add(bas, BorderLayout.SOUTH);
	}

	public void recharger() {
//		mainPanel.cacherTout();
//		mainPanel.afficherBouton(mainPanel.BOUTON_JOUER);
//		mainPanel.afficherBouton(mainPanel.BOUTON_CLASSEMENT);
	}


}
