package enigma;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * A class that takes care of substitution pairs that will be extended by Rotor, Plugboard
 * and Reflector. Simply maintains the pairs of input->output bindings.
 * @author Iakovos
 *
 */
public class Substitution {

	/**
	 * HashMaps for A-Z <-> 1-26 mappings of actual component of our enigma machine, based on configuration
	 */
	protected HashMap<Character, Character> letterToLetterBinding, letterToLetterBindingInversed; 

	/**
	 * HashMaps for A-Z <-> 1-26 mappings: A is 1, B is 2 etc
	 */
	protected static HashMap<Character, Integer> letterToNumberBinding = new HashMap<>(); //converts A to Z to 1 to 26
	protected static HashMap<Integer, Character> numberToLetterBinding = new HashMap<>(); //converts 1 to 26 to A to Z


	/**
	 * Constructor of Substitution, creates the mappings between 
	 * letters and numbers: A-Z <-> 1-26
	 **/
	public Substitution() {
		letterToLetterBinding = new HashMap<>();
		int i=1;
		for (char ch = 'A'; ch <= 'Z'; ++ch) {
			letterToNumberBinding.put(Character.valueOf(ch), i); 
			numberToLetterBinding.put( i, Character.valueOf(ch)); 
			i++;
		}
	}


	/**
	 * Constructor of Substitution with letterToLetterBinding, creates the mappings between
	 * letters and numbers: A-Z <-> 1-26
	 * @param letterToLetterBinding of the component
	 **/
	public Substitution (HashMap<Character, Character> letterToLetterBinding){
		this();
		this.letterToLetterBinding = letterToLetterBinding;
	}


	/**
	 * Constructor of Substitution, Rotor specific, with letterToLetterBinding, creates the mappings between
	 * letters and numbers: A-Z <-> 1-26
	 * @param letterToLetterBindings of the rotor
	 * @param isRotor true if component is rotor
	 **/
	public Substitution (List<HashMap<Character, Character>> letterToLetterBindings, boolean isRotor){
		this(letterToLetterBindings.get(0));
		this.letterToLetterBinding = letterToLetterBindings.get(0);
		letterToLetterBindingInversed = new HashMap<>();
		letterToLetterBindingInversed = letterToLetterBindings.get(1);
	}


	/**
	 * Encrypts the letter given as input
	 * @param letterInput The letter to encrypt
	 * @return The result of the encryption on this component
	 */
	public char encrypt(char letterInput) {
		return this.letterToLetterBinding.get(letterInput);
	}


	/**
	 * Encrypts the letter given as input to the rotor, used for the electricity flow
	 * from the Reflector back towards the output
	 * @param charToEncrypt the letter to encrypt
	 * @return the result of the encryption on this component
	 */
	public char encryptReverseRotor(char letterInput) {
		return this.letterToLetterBindingInversed.get(letterInput);
	}


	/**
	 * Helper function that creates a hashMap of encryption input to output pairs for a Plugboard
	 * @param letterBindings the String of plugboard config pairs
	 * @return the resulting HashMap 
	 */
	protected static HashMap<Character, Character> stringToHashMapPlugboard(String letterBindings) {
		HashMap<Character, Character> letterToLetterBinding = new HashMap<>();
		for (char ch = 'A'; ch <= 'Z'; ++ch) {
			letterToLetterBinding.put(Character.valueOf(ch), Character.valueOf(ch)); 
		}

		for(int i = 0; i < letterBindings.length() - 2; i++) {
			letterToLetterBinding.put(letterBindings.charAt(i), letterBindings.charAt(i+1));
			letterToLetterBinding.put(letterBindings.charAt(i+1), letterBindings.charAt(i));
			i+=2;
		}
		return letterToLetterBinding;
	}


	/**
	 * Helper function that creates a hashMap of encryption input to output pairs for a Reflector
	 * @param letterBindings the String of reflector config String as in reflectors.txt file
	 * @return the resulting HashMap of pairs
	 */
	protected static HashMap<Character, Character> stringToHashMapReflector(String letterBinding){ 
		HashMap<Character, Character> letterToLetterBinding = new HashMap<>();
		char ch = 'A';

		for (int i = 0; i < letterBinding.length(); i++) {
			letterToLetterBinding.put(Character.valueOf(ch), letterBinding.charAt(i));
			ch++;
		}
		return letterToLetterBinding;
	}


	/**
	 * Helper function that creates a hashMap of encryption input to output pairs for a Rotor.
	 * For Rotor we maintain 2 hashMaps, key-value and value-key the electricity current flows 
	 * both ways and this will speed-up our substitution.
	 * @param letterBindings the String of Rotor config string as in rotors.txt file
	 * @return the resulting HashMap of pairs
	 */
	protected static List<HashMap<Character, Character>> stringToHashMapRotor(String letterBindings) {
		HashMap<Character, Character> letterToLetterBinding = new HashMap<>();
		HashMap<Character, Character> letterToLetterBindingInversed = new HashMap<>();
		char ch = 'A';
		for (int i = 0; i < letterBindings.length(); i++) {
			letterToLetterBinding.put(Character.valueOf(ch), letterBindings.charAt(i));
			letterToLetterBindingInversed.put(letterBindings.charAt(i), Character.valueOf(ch));
			ch++;
		}
		return Arrays.asList(letterToLetterBinding, letterToLetterBindingInversed) ;
	}


}