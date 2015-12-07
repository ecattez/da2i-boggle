package boggle.gui.partie;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import boggle.jeu.Sablier;

/**
 * Représente un sablier
 */
public class JSablier extends JLabel implements Observer {
	
	private static final long serialVersionUID = 7770571884846643267L;

	public JSablier(final Sablier sablier) {
		super(sablier.toString());
		this.setForeground(Color.RED);
		sablier.addObserver(this);
	}

	public void update(Observable obs, Object o) {
		this.setText(obs.toString());
	}


}
