/**
 * This file is part of da2i-boggle.
 *
 * da2i-boggle is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * da2i-boggle is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.				 
 * 
 * You should have received a copy of the GNU General Public License
 * along with da2i-boggle.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * @author Edouard CATTEZ <edouard.cattez@sfr.fr> (La 7 Production)
 */
package boggle.gui.partie;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import boggle.gui.classement.JClassement;
import boggle.gui.decorateur.BoutonPlatSansFocus;
import boggle.jeu.Classement;
import boggle.jeu.Partie;
import boggle.jeu.joueur.Joueur;
import boggle.mots.Grille;

/**
 * Représentation d'une partie de Boggle
 */
public class JPartie extends JPanel implements Observer {

	private static final long serialVersionUID = 7040160301203717007L;
	
	private Partie partie;
	private JGrille jGrille;
	private JSablier jSablier;
	private JMots jMots;
	private JClassement jClassement;
	
	private JLabel tourLabel;
	private JTextField zoneSaisie;
	
	private JButton ajouter;
	private JButton vider;
	private JButton terminer;
	
	private JLabel infoLabel;
	private JLabel scoreCibleLabel;
	
	public JPartie(final Partie partie) {
		super(new BorderLayout(10,10));
		
		final Grille grille = partie.getGrille();
		this.partie = partie;
		this.partie.addObserver(this);
		this.jGrille = new JGrille(grille);
		this.jSablier = new JSablier(partie.getSablier());
		this.jMots = new JMots(grille);
		this.jClassement = new JClassement(partie.getClassement());
		this.tourLabel = new JLabel();
		this.zoneSaisie = new JTextField();
		this.ajouter = new BoutonPlatSansFocus(new JButton("Ajouter"));
		this.vider = new BoutonPlatSansFocus(new JButton("Vider"));
		this.terminer = new BoutonPlatSansFocus(new JButton("Terminer"));
		this.infoLabel = new JLabel();
		this.scoreCibleLabel = new JLabel("Score cible : " + partie.getScoreCible());
		
		zoneSaisie.setPreferredSize(new Dimension(200,20));
		
		zoneSaisie.addCaretListener(new CaretListener() {

			public void caretUpdate(CaretEvent e) {
				grille.ecrire(zoneSaisie.getText().toUpperCase());
			}
			
		});
		
		zoneSaisie.addActionListener(new AjouterListener());
		
		ajouter.addActionListener(new AjouterListener());
		
		vider.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				grille.rendreTout();
				zoneSaisie.setText("");
			}
			
		});
		
		terminer.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				partie.stopperSablier();
			}
			
		});
		
		JPanel haut = new JPanel(new FlowLayout(FlowLayout.LEADING));
		haut.add(tourLabel);
		haut.add(jSablier);
		
		JPanel boutons = new JPanel();
		boutons.add(ajouter);
		boutons.add(vider);
		boutons.add(terminer);
		
		JPanel panelSaisie = new JPanel();
		panelSaisie.add(zoneSaisie);
		
		JPanel centre = new JPanel();
		centre.setLayout(new BoxLayout(centre, BoxLayout.PAGE_AXIS));
		centre.add(haut);
		centre.add(jGrille);
		centre.add(panelSaisie);
		centre.add(boutons);
		
		jMots.setPreferredSize(new Dimension(200, 300));
		jClassement.setPreferredSize(jMots.getPreferredSize());
		
		JPanel bas = new JPanel();
		bas.setLayout(new BorderLayout());
		bas.add(infoLabel, BorderLayout.WEST);
		if (partie.getScoreCible() > 0) {
			bas.add(scoreCibleLabel, BorderLayout.EAST);
		}
		this.add(jMots, BorderLayout.WEST);
		this.add(centre, BorderLayout.CENTER);
		this.add(jClassement, BorderLayout.EAST);
		this.add(bas, BorderLayout.SOUTH);
	}

	public void update(Observable obs, Object o) {
		if (partie.estTerminee()) {
			Classement classement = partie.getClassement();
			classement.sauvegarderMeilleursScores();
			classement.trier();
			infoLabel.setText(partie.getJoueurs()[0] + " a gagné cette partie.");
		}
		else {
			Joueur joueur = partie.getJoueurCourant();
			boolean enabled = joueur.estHumain();
			int tourMax = partie.getTourMax();
			tourLabel.setText("Tour de " + joueur.getNom());
			zoneSaisie.setText("");
			zoneSaisie.setEnabled(enabled);
			zoneSaisie.requestFocusInWindow();
			ajouter.setEnabled(enabled);
			vider.setEnabled(enabled);
			terminer.setEnabled(enabled);
			jGrille.setEnabled(enabled);
			if (tourMax < 1) {
				infoLabel.setText("Tour actuel : " + partie.tour());
			}
			else {
				infoLabel.setText("Tour actuel : " + partie.tour() + "/" + tourMax);
			}
		}
	}
	
	/**
	 * Evénement qui se produit lorsqu'un joueur souhaite ajouter un mot à sa liste de mots
	 */
	class AjouterListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			Grille grille = partie.getGrille();
			if (grille.getLettresUtilisees().length() >= grille.tailleMinimale()) {
				grille.stockerMot();
				grille.rendreTout();
				zoneSaisie.setText("");
			}
		}
		
	}

}
