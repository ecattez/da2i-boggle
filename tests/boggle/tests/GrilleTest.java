package boggle.tests;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import boggle.jeu.Piko;
import boggle.mots.ArbreLexical;
import boggle.mots.De;
import boggle.mots.GrilleLettres;

public class GrilleTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final ArbreLexical arbre = ArbreLexical.lireMots("dict-fr.txt");
		final GrilleLettres grille = new GrilleLettres(4, De.creerDes("des-4x4.csv"));
		final JPanel container = new JPanel(new BorderLayout());
		final JObserver obs = new JObserver(grille);
		final JTextField text = new JTextField();
		
		text.addCaretListener(new CaretListener() {

			public void caretUpdate(CaretEvent e) {
				grille.rendreTout();
				grille.ecrire(text.getText().toUpperCase());
			}
			
		});
		
		container.add(obs, BorderLayout.CENTER);
		container.add(text, BorderLayout.SOUTH);
		
		JFrame f = new JFrame();
		f.setContentPane(container);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

}
