package boggle.gui.ecran;

import boggle.gui.Bucket;
import boggle.gui.ConteneurPrincipal;
import boggle.gui.partie.JPartie;
import boggle.jeu.Partie;

public class EcranJeu extends Ecran {
	
	private static final long serialVersionUID = -2902887394692862062L;
	
	private Partie partie;

	public EcranJeu(ConteneurPrincipal mainPanel) {
		super(mainPanel);
	}

	public void recharger() {
		mainPanel.cacherTout();
		mainPanel.afficherBouton(mainPanel.BOUTON_MENU_PRINCIPAL, mainPanel.BOUTON_NOUVELLE_PARTIE);
		if (partie == null || partie.estTerminee()) {
			partie = Bucket.getInstance().getPartie();
			this.removeAll();
			this.add(new JPartie(partie));
			new Thread(partie).start();
		}
	}

}
