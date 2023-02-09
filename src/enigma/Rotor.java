package enigma;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Class that represents a Rotor and takes care of the encryption, its notchPosition and currentPosition
 * @author Iakovos
 *
 */
public class Rotor extends Substitution{

	private int rotorOffset; //The ring setting of the machine
	private int currentPosition;
	private char notchPosition;

	/**
	 * Constructor for Rotor
	 * @param letterToLetterBindings
	 * @param rotorOffset The ring setting of the machine
	 * @param startingPosition Starting position of the rotor as in initial config
	 * @param notchPosition Notch position of the rotor
	 */
	private Rotor(List<HashMap<Character, Character>> letterToLetterBindings, int rotorOffset, int startingPosition, char notchPosition) throws IOException {
		super(letterToLetterBindings, true);
		this.rotorOffset = rotorOffset;
		this.currentPosition = startingPosition;
		this.notchPosition = notchPosition;
	}


	/**
	 * Returns the current rotor position
	 * @return the current rotor position
	 */
	protected int getRotorPosition() {
		return this.currentPosition;
	}


	/**
	 * Factory methd for creating a Rotor after parsing the right configuration from input args
	 * @param rotorArgs args[4] of input args
	 * @param rotorIndex Index of the current rotor, 1 for I, 2 for II or 3 for III
	 * @return Rotor object
	 * @throws IOException
	 */
	public static Rotor create(String rotorArgs, int rotorIndex) throws IOException{ //factory design pattern
		String rotorParams = rotorArgs.split(" ")[rotorIndex-1];
		String rotorType = rotorParams.split(":")[0];
		String startingPosition = (rotorParams.split(":")[1]).split("-")[0];
		String rotorOffset = (rotorParams.split("-")[1]).split(" ")[0];

		FileRead fileRead = new FileRead();
		String rotorLetterBindingAndNotch = fileRead.readSettings("Rotor", rotorType);
		char notchPosition = rotorLetterBindingAndNotch.split("#")[1].charAt(0);
		return new Rotor(stringToHashMapRotor(rotorLetterBindingAndNotch.split(" ")[1]), Integer.valueOf(rotorOffset), Integer.valueOf(startingPosition), notchPosition);
	}


	/**
	 * Encrypts the letter given as input to the Rotor. Output depends on the configuration of the rotor
	 * @param the letter to encrypt
	 * @return the result of the encryption in the Rotor
	 */
	public char encrypt(char charToEncrypt) {
		char charAtInputOfRotor = convertOuterToInner(charToEncrypt); //returns the char the electricity will see when entering the rotor
		int numberAtOutput = letterToNumberBinding.get(super.encrypt(charAtInputOfRotor)) - (this.currentPosition-this.rotorOffset);
		numberAtOutput = numberAtOutput % 26;
		//improve this somehow? 
		while(numberAtOutput<=0) {
			numberAtOutput += 26;
		}

		return (numberToLetterBinding.get(numberAtOutput));
	}


	/**
	 * Encrypts the letter given as input to the rotor, used for the electricity flow
	 * from the Reflector back towards the output
	 * @param charToEncrypt the letter to encrypt
	 * @return the result of the encryption in the rotor
	 */
	public char encryptReverseRotor(char charToEncrypt) {
		char charAtInputOfRotor = convertOuterToInnerLeftEnd(charToEncrypt); //returns the char the electricity will see when entering the rotor
		int numberAtRightOutput = letterToNumberBinding.get(super.encryptReverseRotor(charAtInputOfRotor)) - (this.currentPosition - this.rotorOffset);
		numberAtRightOutput = numberAtRightOutput % 26;
		while(numberAtRightOutput<=0) {
			numberAtRightOutput += 26;
		}
		return numberToLetterBinding.get(numberAtRightOutput);
	}


	/**
	 * Spins the Rotor. If it goes beyond letter Z, then spins to A.
	 * @param
	 * @returns true if it was in notch position, false otherwise
	 **/
	public boolean spinRotorToNextPosition () {
		this.currentPosition++;
		if(this.currentPosition > 26) {
			this.currentPosition = this.currentPosition % 26; //26(Z) remains 26, 27 turns into 1
		}
		return (((this.currentPosition-1) == letterToNumberBinding.get(this.notchPosition)) ?  true :  false);
	}


	/**
	 * Helper method to return the inner letter of a ring based on the input letter
	 * @param the letter of the ring at the input of the electric current
	 * @return the equivalent letter on the inside of the ring
	 */
	private char convertOuterToInner(char charToEncrypt) {
		char charAtRotorEntrance;
		int actualLetterAtEntrance = (letterToNumberBinding.get(charToEncrypt) + (this.currentPosition - this.rotorOffset))%26;
		charAtRotorEntrance = numberToLetterBinding.get(actualLetterAtEntrance > 0 ? actualLetterAtEntrance : (actualLetterAtEntrance + 26));
		return charAtRotorEntrance;
	}


	/**
	 * Helper method to return the inner letter of a ring based on the input letter when
	 * the current flows from the left side of the rotor, i.e after the Reflector
	 * @param the letter of the ring at the input of the electric current
	 * @return the equivalent letter on the inside of the ring
	 */
	private char convertOuterToInnerLeftEnd(char charToEncrypt) {
		char charAtRotorLeftEntrance;
		int numAtRotorLeftEntrance = (letterToNumberBinding.get(charToEncrypt) + (this.currentPosition - this.rotorOffset))%26;
		charAtRotorLeftEntrance = numberToLetterBinding.get(numAtRotorLeftEntrance > 0 ? numAtRotorLeftEntrance : (numAtRotorLeftEntrance + 26));
		return charAtRotorLeftEntrance;
	}
}

