package boggle.gui.partie;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import boggle.jeu.Sablier;

/**
 * Représente un sablier
 */
public class JSablier extends JLabel implements Observer {
	
	public JSablier(final Sablier sablier) {
		super(sablier.toString());
		sablier.addObserver(this);
	}

	public void update(Observable obs, Object o) {
		this.setText(obs.toString());
	}


}
