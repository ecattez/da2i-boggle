package boggle.mots ;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * La classe ArbreLexical permet de stocker de façon compacte et d'accéder rapidement à un ensemble de mots.
 */
public class ArbreLexical {

	public static final int TAILLE_ALPHABET = 26 ;
	
	private boolean estMot ; // vrai si le noeud courant est la fin d'un mot valide
	private ArbreLexical[] fils = new ArbreLexical[TAILLE_ALPHABET] ; // les sous-arbres

	/**
	 * Crée un arbre vide (sans aucun mot)
	 */
	public ArbreLexical() {}
	
	/**
	 * Retourne le véritable indice d'une majuscule
	 * 
	 * @param	lettre
	 * 			la lettre pour laquelle il faut trouver l'indice
	 * 
	 * @return	l'indice de la lettre passé en paramètre
	 */
	private int indexOf(char lettre) {
		return lettre - 'A';
	}
	
	/**
	 * Retourne la véritable lettre à l'indice en paramètre (et non sa valeur ASCII)
	 * 
	 * @param	i
	 * 			l'indice de la lettre à retrouver
	 * 
	 * @return	le caractère d'indice passé en paramètre
	 */
	private char charAt(int i) {
		return (char) (i + 'A');
	}

	/**
	 * Indique si le noeud courante est situé à l'extrémité d'un mot valide
	 */
	public boolean estMot() {
		return estMot ;
	}

	/**
	 * Place le mot spécifié dans l'arbre
	 * 
	 * @param	word
	 * 			le mot à ajouter dans l'arbre
	 * 
	 * @return	<code>true</code> si le mot a été ajouté, <code>false</code> sinon
	 */
	public boolean ajouter(String word) {
		if (word.length() == 0) {
			estMot = true;
			return true;
		}
		char c = Character.toUpperCase(word.charAt(0));
		int i = indexOf(c);
		if (fils[i] == null) {
			fils[i] = new ArbreLexical();
		}
		return fils[i].ajouter(word.substring(1));
	}

	/**
	 * Teste si l'arbre lexical contient le mot spécifié.
	 * 
	 * @param	word
	 * 			le mot qui peut se trouver dans l'arbre
	 * 
	 * @return	<code>true</code> si <code>word</code> est un mot (String) contenu dans l'arbre, <code>false</code> sinon.
	 */
	public boolean contient(String word) {
		if (word.length() == 0) {
			return estMot();
		}
		char c = Character.toUpperCase(word.charAt(0));
		ArbreLexical noeud = fils[indexOf(c)];
		return noeud == null ? false : noeud.contient(word.substring(1));
	}

	/**
	 * Ajoute à la liste <code>resultat<code> tous les mots de l'arbre commençant par le préfixe spécifié.
	 * 
	 * @param	prefixe
	 * 			le préfixe du mot
	 * @param	niveau
	 * 			le niveau de profondeur courant de l'arbre
	 * @param	resultat
	 * 			la liste des mots qui commence par le préfixe
	 *  
	 * @return	<code>true</code> si <code>resultat</code> a été modifié, <code>false</code> sinon.
	 */
	private boolean motsCommencantPar(String prefixe, int niveau, List<String> resultat) {
		if (niveau < prefixe.length()) {
			ArbreLexical suivant = fils[indexOf(prefixe.charAt(niveau))];
			if (suivant == null) {
				return false;
			}
			if (suivant.estMot()) {
				resultat.add(prefixe);
			}
			suivant.motsCommencantPar(prefixe, niveau + 1, resultat);
		}
		else {
			for (int i=0; i < fils.length; i++) {
				if (fils[i] == null) {
					continue;
				}
				if (fils[i].estMot()) {
					resultat.add(prefixe + charAt(i));
				}
				fils[i].motsCommencantPar(prefixe + charAt(i), niveau + 1, resultat);
			}
		}
		return resultat.size() > 0;
	}
	
	public boolean motsCommencantPar(String prefixe, List<String> resultat) {
		return motsCommencantPar(prefixe, 0, resultat);
	}

	/**
	 * Crée un arbre lexical qui contient tous les mots du fichier spécifié.
	 * 
	 * @param	fichier
	 * 			le nom du fichier à charger
	 */
	public static ArbreLexical lireMots(String fichier) {
		ArbreLexical root = new ArbreLexical();
		Path path = Paths.get("config", fichier);
		String word;
		try (Scanner sc = new Scanner(path)) {
			while (sc.hasNextLine()) {
				word = Normalizer.normalize(sc.nextLine(), Normalizer.Form.NFD);
				word = word.replaceAll("[^\\p{ASCII}]", "").toUpperCase();
				if (word.matches("[A-Z]+")) {
					root.ajouter(word);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return root;
	}
	
	public static void main(String[] args) {
		ArbreLexical root = new ArbreLexical();
		List<String> mots = new ArrayList<String>();
		root.ajouter("CHAT");
		root.ajouter("CHATON");
		root.ajouter("CHATEAU");
		root.ajouter("CHATOYANT");
		root.motsCommencantPar("CHAT", mots);
		
		System.out.println(mots);
		
//		for (char c = 'A' ; c <= 'Z'; c++) {
//			root.motsCommencantPar("", 0, mots);
//		}
//		System.out.println(mots.toString().replaceAll(",", "\n"));
	}
}
