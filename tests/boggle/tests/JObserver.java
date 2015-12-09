package boggle.tests;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;

import boggle.mots.Coordonnees;
import boggle.mots.CoordonneesCartesiennes;
import boggle.mots.Grille;


/*
 */
@Deprecated
public class JObserver extends JPanel implements Observer {
	
	class JPosition extends JButton {
		
		private Coordonnees c;
		
		public JPosition(Coordonnees c) {
			this.c = c;
			this.setText(g.getFaceVisible(c));
			this.setBackground(Color.WHITE);
		}
		
		protected void paintComponent(Graphics gph) {
			this.setBackground(Color.WHITE);
			this.setText(g.getFaceVisible(c));
			if (c.equals(g.getDernierePosition())) {
				this.setBackground(Color.ORANGE);
			}
			else if (g.estUtilise(c)) {
				this.setBackground(Color.RED);
			}
			super.paintComponent(gph);
		}
		
	}
	
	private Grille g;
	
	public JObserver(Grille g) {
		super();
		this.g = g;
		this.g.addObserver(this);
		int d = g.dimension();
		this.setLayout(new GridLayout(d,d));
		for (int y = 0; y < d; y++) {
			for (int x = 0; x < d; x++) {
				this.add(new JPosition(new CoordonneesCartesiennes(x, y)));
			}
		}
	}

	public void update(Observable obs, Object o) {
		this.repaint();
	}
	
}
