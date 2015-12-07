package boggle.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;

import boggle.mots.Coordonnees;
import boggle.mots.Grille;
import boggle.mots.GrilleLettres;


public class JGrille extends JPanel implements Observer {

	private Coordonnees coord;
	private Grille grille;
	public JGrille (Grille pGrille) {
		super();
		this.setLayout(new GridLayout());
		for(int y = 0 ; y < pGrille.dimension() ; y++ ) {
			for(int x = 0 ; x < pGrille.dimension() ; x++ ) {
				this.add(new JDe(coord));
			}
		}
	}
	public void update(Observable o, Object arg) {
		this.repaint();
	}
	class JDe extends JButton{
		private Coordonnees c ;

		public JDe (Coordonnees c) {
			this.c = c ;
			
		} 

		protected void paintComponent(Graphics gph) {
			
		}

	}
}