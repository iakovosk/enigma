package enigma;

import java.io.IOException;

/** Class that takes care of the instantiation and initial configuration of the Enigma 
 * machine based on the input from the command-line args and the files under /data path. Also 
 * calls the encryption method for the right sequence: Plugboard -> Rotors III to I ->
 * Reflector -> Rotors I to III.
 *  
 * @author Iakovos
 *
 */
public class Configuration {

	private Plugboard plugboard;
	private ThreeRotorsController threeRotorsController;
	private Reflector reflector;


	/**
	 * Creates the enigma machine components and configures them 
	 * based on the input arguments: Rotor types,
	 * starting positions and ring settings.
	 * 
	 * @param args The command line arguments. [4] is Rotor configuration,[6] is plugboard
	 * @throws java.io.IOException when we can't read a file
	 **/
	public Configuration(String[] args) throws IOException {
		
		this.plugboard = Plugboard.create(args[6]);

		this.threeRotorsController = new ThreeRotorsController(args);

		this.reflector = Reflector.create();
	}


	/**
	 * Encrypts the string given as input. Breaks it down and calls encryptLetter for each
	 * letter in the String.
	 * 
	 * @param stringToEncrypt The String to encrypt.
	 * @return The encrypted string
	 **/
	public String encryptString(String stringToEncrypt) {
		StringBuilder encryptedString = new StringBuilder();
		//		String encryptedString = "";
		for(int i=0; i<stringToEncrypt.length(); i++) {
			encryptedString.append(String.valueOf(encryptLetter(stringToEncrypt.charAt(i))));
		}
		return encryptedString.toString();
	}


	/**
	 * Encrypts the letter(character) given as input:
	 * First in Plugboard, then rotors III->II->I, then reflector,
	 * then rotors I->II->III
	 * 
	 * @param inputLetter The letter to encrypt.
	 * @return The encrypted letter
	 **/
	public char encryptLetter(char inputLetter) {
		char outputLetter;

		outputLetter = plugboard.encrypt(inputLetter);

		outputLetter = threeRotorsController.encryptIIItoI(outputLetter);

		outputLetter = reflector.encrypt(outputLetter);

		outputLetter = threeRotorsController.encryptItoIII(outputLetter);

		return outputLetter;
	}
}
