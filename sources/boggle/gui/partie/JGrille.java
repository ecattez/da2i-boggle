package boggle.gui.partie;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;

import boggle.mots.Coordonnees;
import boggle.mots.CoordonneesCartesiennes;
import boggle.mots.Grille;

/**
 * Représente une grille de dés
 */
public class JGrille extends JPanel implements Observer {
	
	private static final long serialVersionUID = -258981629780565455L;
	
	public static final Color DEFAULT_FONT_COLOR = Color.BLACK;
	public static final Color DEFAULT_COLOR = Color.WHITE;
	public static final Color COULEUR_DE_UTILISE = new Color(180,120,126);
	public static final Color COULEUR_DERNIER_DE_UTILISE = new Color(169,62,73);
	
	private Grille grille;
	
	public JGrille(Grille grille) {
		this(grille, grille.dimension());
	}
	
	private JGrille(Grille grille, int n) {
		super(new GridLayout(n, n, 5, 5));
		this.grille = grille;
		this.grille.addObserver(this);
		for (int y=0; y < n; y++) {
			for (int x=0; x < n; x++) {
				this.add(new JDe(new CoordonneesCartesiennes(x, y)));
			}
		}
		this.setPreferredSize(new Dimension(n * 64, n * 64));
	}
	
	public void update(Observable obs, Object o) {
		this.repaint();
	}
	
	// Rédéfinit la méthode setEnabled pour appeler la méthode setEnabled de chaque JDe
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		for (int i=0; i < this.getComponentCount(); i++) {
			this.getComponent(i).setEnabled(enabled);
		}
	}
	
	/**
	 * Représente en interne un dé
	 */
	class JDe extends JButton {
		
		private static final long serialVersionUID = 6952888330383232312L;
		
		private Coordonnees coord;
		
		public JDe(final Coordonnees coord) {
			this.coord = coord;
			this.setFont(new Font("Arial", Font.PLAIN, 15));
			this.setFocusable(false);
			this.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					if (grille.estDernierUtilise(coord)) {
						grille.rendreDe(coord);
					}
					else if (grille.getDernierePosition() == null || (grille.sontVoisins(coord, grille.getDernierePosition()) && !grille.estUtilise(coord))) {
						grille.utiliserDe(coord);
					}
				}
				
			});
		}
		
		protected void paintComponent(Graphics g) {
			this.setForeground(DEFAULT_FONT_COLOR);
			this.setBackground(DEFAULT_COLOR);
			this.setText(grille.getFaceVisible(coord));
			
			if (grille.estDernierUtilise(coord)) {
				this.setBackground(COULEUR_DERNIER_DE_UTILISE);
			}
			else if (grille.estUtilise(coord)) {
				this.setBackground(COULEUR_DE_UTILISE);
			}
			
			super.paintComponent(g);
		}
		
	}

}
