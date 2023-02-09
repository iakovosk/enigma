package enigma;

import java.io.IOException;


public class Main {

	/**
	 * The main method.
	 * Based on the requirements of https://github.com/niwhede/enigma-workshop
	 * 
	 * Receives the configuration of enigma and input string 
	 * as args:
	 * Can be customised with the 5 different settings:

	 *	Ring order - the order of the 3 rotors
	 *	Initial position - the starting position of the rotors
	 *	Ring setting - the positioning of the wiring compared to the rotors themselves
	 *	Reflector - the reflector used to feed the output of the last rotor as an input of the same rotor
	 *	Plugboard Setting - the optional extra letter substitutions.

	 *	Example build configuration:
	 * ./enigma encode "THEQUICKBROWNFOXJUMPSOVERTHELAZYDOG" -r "I:1-1 II:1-3 III:1-5" -p "QA ED FG BO LP CS RT UJ HN ZW" -v
	 * where
	 * ./enigma encode "<String to encrypt>" -r "<rotor I's type>:<starting position>-<Ring Setting> <rotor II's type>:<starting position>-<Ring Setting> <rotor III type>:<starting position>-<Ring Setting>" -p "<The plugboard pairs>" -v
	 *
	 * @param args The command line arguments.
	 * @throws java.io.IOException when we can't read a file
	 **/
	public static void main(String[] args) throws IOException {

		//Configure the enigma machine based on the input parameters
		Configuration configuration = new Configuration(args);

		//Encrypt the word given as third argument
		String encryptedOutput = configuration.encryptString(args[2]);

		//Print encrypted message
		System.out.print(encryptedOutput);
	}

}
