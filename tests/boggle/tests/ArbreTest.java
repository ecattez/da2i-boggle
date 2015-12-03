package boggle.tests;

import java.util.ArrayList;
import java.util.List;

import boggle.mots.ArbreLexical;

public class ArbreTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArbreLexical root = new ArbreLexical();
		List<String> mots = new ArrayList<String>();
		root.ajouter("CHAT");
		root.ajouter("CHATON");
		root.ajouter("CHATEAU");
		root.ajouter("CHATOYANT");
		root.motsCommencantPar("CHAT", mots);
		
		System.out.println(mots);
	}

}
