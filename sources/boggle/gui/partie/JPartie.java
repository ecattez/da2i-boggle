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
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import boggle.gui.decorateur.DecorateurBoutonPlat;
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
	private JList<String> jListeMots;
	private JList<Joueur> jListeScores;
	
	private JLabel tourLabel;
	private JTextField zoneSaisie;
	
	private JButton ajouter;
	private JButton vider;
	private JButton terminer;
	
	public JPartie(final Partie partie) {
		super(new BorderLayout(10,10));
		
		final Grille grille = partie.getGrille();
		this.partie = partie;
		this.partie.addObserver(this);
		this.jGrille = new JGrille(grille);
		this.jSablier = new JSablier(partie.getSablier());
		this.jListeMots = new JList<String>(new ListMotsModel(grille));
		this.jListeScores = new JList<Joueur>(new ListJoueursModel(partie.getJoueurs()));
		this.tourLabel = new JLabel();
		this.zoneSaisie = new JTextField();
		this.ajouter = new DecorateurBoutonPlat(new JButton("Ajouter"));
		this.vider = new DecorateurBoutonPlat(new JButton("Vider"));
		this.terminer = new DecorateurBoutonPlat(new JButton("Terminer"));
		
		zoneSaisie.setPreferredSize(new Dimension(200,20));
		
		zoneSaisie.addCaretListener(new CaretListener() {

			public void caretUpdate(CaretEvent e) {
				grille.rendreTout();
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
		
		JPanel center = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.PAGE_AXIS));
		center.add(haut);
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
		Joueur joueur = partie.getJoueurCourant();
		boolean enabled = joueur.estHumain();
		tourLabel.setText("Tour de " + joueur.getNom());
		zoneSaisie.setText("");
		zoneSaisie.setEnabled(enabled);
		zoneSaisie.requestFocusInWindow();
		ajouter.setEnabled(enabled);
		vider.setEnabled(enabled);
		terminer.setEnabled(enabled);
		jGrille.setEnabled(enabled);
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
