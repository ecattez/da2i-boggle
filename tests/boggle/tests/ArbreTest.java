package boggle.tests;

import java.util.ArrayList;
import java.util.List;

import boggle.mots.ArbreLexical;

public class ArbreTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArbreLexical root = ArbreLexical.creerArbre("config/dict-fr.txt");
		List<String> mots = new ArrayList<String>();
		root.motsCommencantPar("AMI", mots);
		System.out.println(mots);
	}

}
