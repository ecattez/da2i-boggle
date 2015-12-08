package boggle.gui.ecran;

import boggle.gui.ConteneurPrincipal;

public class EcranJeu extends Ecran {

	public EcranJeu(ConteneurPrincipal mainPanel) {
		super(mainPanel);
	}

	public void recharger() {
		mainPanel.cacherTout();
		mainPanel.afficherBouton(mainPanel.BOUTON_MENU_PRINCIPAL, mainPanel.BOUTON_NOUVELLE_PARTIE);
	}

}
