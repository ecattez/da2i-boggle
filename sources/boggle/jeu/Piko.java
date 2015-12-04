package boggle.jeu;

import java.util.ArrayList;
import java.util.List;

public class Piko extends IA {
	
	public Piko() {
		super("Piko");
	}

	public void run() {
		List<String> mots = new ArrayList<String>();
		long start = System.currentTimeMillis();
		for (char c = 'A'; c <= 'Z'; c++) {
			if (arbre.motsCommencantPar(String.valueOf(c), mots)) {
				for (String mot : mots) {
					if (mot.length() >= grille.tailleMinimale() && grille.ecrire(mot)) {
						grille.stockerMot();
					}
				}
				mots.clear();
			}
		}
		System.out.println(System.currentTimeMillis() - start);
		System.out.println(grille.getMots());
		System.out.println(grille.getMots().size());
	}

}
