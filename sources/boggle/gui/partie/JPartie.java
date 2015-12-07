package boggle.gui.partie;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import boggle.jeu.Partie;
import boggle.jeu.joueur.Humain;
import boggle.jeu.joueur.Joueur;
import boggle.mots.Grille;

/**
 * Représentation d'une partie de Boggle
 */
public class JPartie extends JPanel implements Observer {

	private Partie partie;
	private JGrille jGrille;
	private JSablier jSablier;
	private JList<String> jListeMots;
	private JList<Joueur> jListeScores;
	
	private JTextField zoneSaisie;
	
	private JButton ajouter;
	private JButton vider;
	private JButton terminer;
	
	public JPartie(final Partie partie) {
		super(new BorderLayout(10,10));
		
		partie.addObserver(this);
		
		final Grille grille = partie.getGrille();
		this.partie = partie;
		this.jGrille = new JGrille(grille);
		this.jSablier = new JSablier(partie.getSablier());
		this.jListeMots = new JList<String>(new ListMotsModel(grille));
		this.jListeScores = new JList<Joueur>(new ListJoueursModel(partie));
		this.zoneSaisie = new JTextField();
		this.ajouter = new JButton("Ajouter");
		this.vider = new JButton("Vider");
		this.terminer = new JButton("Terminer");
		
		zoneSaisie.setPreferredSize(new Dimension(200,20));
		
		zoneSaisie.addCaretListener(new CaretListener() {

			public void caretUpdate(CaretEvent e) {
				grille.rendreTout();
				grille.ecrire(zoneSaisie.getText().toUpperCase());
			}
			
		});
		
		ajouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (grille.getLettresUtilisees().length() >= grille.tailleMinimale()) {
					grille.stockerMot();
					grille.rendreTout();
					zoneSaisie.setText("");
				}
			}
		});
		
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
		
		JPanel boutons = new JPanel();
		boutons.add(ajouter);
		boutons.add(vider);
		boutons.add(terminer);
		
		JPanel panelSaisie = new JPanel();
		panelSaisie.add(zoneSaisie);
		
		JPanel center = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.PAGE_AXIS));
		center.add(jSablier);
		center.add(jGrille);
		center.add(panelSaisie);
		center.add(boutons);
		
		JScrollPane gauche = new JScrollPane(jListeMots);
		JScrollPane droite = new JScrollPane(jListeScores);
		
		gauche.setPreferredSize(new Dimension(200,300));
		droite.setPreferredSize(new Dimension(200,300));
		
		this.add(gauche, BorderLayout.WEST);
		this.add(center, BorderLayout.CENTER);
		this.add(droite, BorderLayout.EAST);
	}

	public void update(Observable obs, Object o) {
		boolean enabled = partie.getJoueurCourant() instanceof Humain;
		zoneSaisie.setEnabled(enabled);
		ajouter.setEnabled(enabled);
		vider.setEnabled(enabled);
		terminer.setEnabled(enabled);
	}

}
