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
import boggle.mots.GrilleLettres;


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
			this.setText(g.getFaceVisible(c));
			super.paintComponent(gph);
		}
		
	}
	
	private GrilleLettres g;
	private int d;
	
	public JObserver(GrilleLettres g) {
		super();
		this.g = g;
		this.d = g.dimension();
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