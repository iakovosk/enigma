package enigma;

import java.io.IOException;

/**
 * Instantiates the 3 Rotors and takes care of the encryption and rotor spin over the
 * 3 of them. It will happen on order I->II->III or III->II->I which has different results
 * and behaviour.
 * @author Iakovos
 *
 */
public class ThreeRotorsController {

	private Rotor rotorI;
	private Rotor rotorII;
	private Rotor rotorIII;


	/**
	 * Constructor for the controller of 3 rotors. We require this level of control 
	 * as encryption I->II->III is different from III->II->I and also each ring's spin
	 * can affect the next one's in line.
	 * 
	 * @param args The command line arguments. [4] is Rotor configuration
	 **/
	public ThreeRotorsController(String[] args) throws IOException {
		rotorI = Rotor.create(args[4], 1);
		rotorII = Rotor.create(args[4], 2);
		rotorIII = Rotor.create(args[4], 3);
	}


	/**
	 * Encrypts the letter as received from the Plugboard.
	 * Spins each rotor once it encrypts the letter and, if it was in notch position,
	 * then spins the next one also.
	 * 
	 * @param inputLetter The letter to be encrypted
	 * @returns The letter after being encrypted by rotors III->II->I
	 **/
	public char encryptIIItoI(char inputLetter) {
		boolean wasInNotch = rotorIII.spinRotorToNextPosition();
		if(wasInNotch) {
			wasInNotch = rotorII.spinRotorToNextPosition();
			if (wasInNotch) {
				rotorI.spinRotorToNextPosition();
			}
		}
		inputLetter = rotorIII.encrypt(inputLetter);

		inputLetter = rotorII.encrypt(inputLetter);

		inputLetter = rotorI.encrypt(inputLetter);

		return inputLetter;
	}


	/**
	 * Encrypts the letter as received from the Reflector.
	 * Spins each rotor once it encrypts the letter and, if it was in notch position,
	 * then spins the next one also.
	 * 
	 * @param inputLetter The letter to be encrypted
	 * @returns The letter after being encrypted by rotors I->II->III
	 **/

	public char encryptItoIII(char inputLetter) {

		inputLetter = rotorI.encryptReverseRotor(inputLetter);

		inputLetter = rotorII.encryptReverseRotor(inputLetter);

		inputLetter = rotorIII.encryptReverseRotor(inputLetter);

		return inputLetter;
	}

}
