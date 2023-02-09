package enigma;

import java.io.IOException;
import java.util.HashMap;

/**
 * Class that represents a Reflector. The Reflector is simple and only substitutes between
 * pairs of letters(letterToLetterBinding)
 * @author Iakovos
 *
 */
public class Reflector extends Substitution {

	/**
	 * Constructor for Reflector
	 * @param letterToLetterBindingHashMap The encryption pair of the Reflector
	 * @throws java.io.IOException when we can't read a file
	 */
	public Reflector(HashMap<Character, Character> letterToLetterBinding) throws IOException {
		super(letterToLetterBinding);
	}


	/**
	 * Factory method to call Reflector constructor after pre-processing input arguments.
	 * Reads the reflector configuration from file
	 * @param args The command line arguments. 
	 * @returns Reflector object
	 */
	public static Reflector create() throws IOException {
		FileRead fileRead = new FileRead();
		String reflectorParams = fileRead.readSettings("Reflector", "B");
		String reflectorBinding = reflectorParams.split(" ")[1];

		return new Reflector(stringToHashMapReflector(reflectorBinding));
	}

}
