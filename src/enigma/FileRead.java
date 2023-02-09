package enigma;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Class for reading configuration of the Enigma Rotors and Reflector from files 
 * under data folder
 * @author Iakovos
 *
 */
public class FileRead {

	/**
	 * Reads rotors and reflectors configuration from file
	 * 
	 * @param componentType Of type Rotor or Reflector
	 * @param type Hardcoded enigma Rotor and Reflector types:
	 * I or II or III or IV or V and A or B or C respectively
	 * @throws java.io.IOException when we can't read a file
	 */	
	public String readSettings(String componentType, String type) throws IOException {
		try {
			Scanner scanner;
			if (componentType == "Rotor") {
				scanner = new Scanner(Paths.get("data/rotors.txt"));

			}else {
				scanner = new Scanner(Paths.get("data/reflectors.txt"));
			}			
			String output = "Type not found";

			String input = scanner.nextLine();
			while (scanner.hasNext()) {
				if (type.equals(input.split(":")[0])) {
					output = input.split(":")[1];
				}

				input = scanner.nextLine();
			}
			scanner.close();
			return output;
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			return "Catastrophic failure when accessing the file";
		} 


	}
}
