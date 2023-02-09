package enigma;

import java.util.HashMap;

/**
 * Class that represents a Plugboard. The Plugboard is simple and only substitutes between
 * pairs of letters (letterToLetterBinding).
 * @author Iakovos
 *
 */
public class Plugboard extends Substitution{

	/**
	 * Constructor for Plugboard
	 * @param letterToLetterBindingHashMap  The encryption pair of the Plugboard
	 */
	public Plugboard(HashMap<Character, Character> letterToLetterBindingHashMap) {
		super(letterToLetterBindingHashMap);
	}


	/**
	 * Factory method to call Plugboard constructor after pre-processing input arguments
	 * @param args The command line arguments. 
	 * @returns Plugboard object
	 */
	public static Plugboard create(String args) {
		return new Plugboard(stringToHashMapPlugboard(args));
	}

}
