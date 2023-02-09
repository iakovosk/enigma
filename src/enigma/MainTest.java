package enigma;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * JUnit testing for the Enigma machine.
 * @author Iakovos
 *
 */
class MainTest {

	@Test
	public void mainTestTheBrownFox() throws IOException {
		String[] args = new String[]{"./enigma", "encode", "THEQUICKBROWNFOXJUMPSOVERTHELAZYDOG", "-r", "I:1-1 II:1-1 III:1-1", "-p", "AA", "-v"};
		Main.main(args);        

		String expectedMessage = "OPCILLAZFXLQTDNLGGLEKDIZOKQKGXIEZKD";

		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		Main.main(args);

		assertEquals(expectedMessage, outContent.toString());

	}


	@Test
	public void mainTestTheBrownFox3Times() throws IOException {
		String[] args = new String[]{"./enigma", "encode", "THEQUICKBROWNFOXJUMPSOVERTHELAZYDOGTHEQUICKBROWNFOXJUMPSOVERTHELAZYDOGTHEQUICKBROWNFOXJUMPSOVERTHELAZYDOGTHEQUICKBROWNFOXJUMPSOVERTHELAZYDOGTHEQUICKBROWNFOXJUMPSOVERTHELAZYDOGTHEQUICKBROWNFOXJUMPSOVERTHELAZYDOGTHEQUICKBROWNFOXJUMPSOVERTHELAZYDOGTHEQUICKBROWNFOXJUMPSOVERTHELAZYDOGTHEQUICKBROWNFOXJUMPSOVERTHELAZYDOGTHEQUICKBROWNFOXJUMPSOVERTHELAZYDOG", "-r", "I:1-1 II:1-3 III:1-5", "-p", "AA", "-v"};
		Main.main(args);        

		String expectedMessage = "KCAHIZUBWGXTRYDGQMQSAJHNFEYBWGOKQYQZRAZTZOYXYAESZJQYMYMLTUCUXJKGLDZUPZUGTSAZQHMGRDZNJAVLUCPBOQFEKUCOECFDIGCHCZMDSFKIPQSZNUIWQXYHZOLMIZZHZKEFQXJVCLMLALPPJRAQBPWBUKJPIFVKSILTUHWGPRWZGIQALESZLYTBKWAEEQSVJSLAWYWQCEVZNFFLBAIOHDKSQYHKOHIEGKBBPFHYKGRMOYMWBILQIVUAJLNIRLWTOPATCCARXXBUQCYOWNUBFOFYVJIXXBXDPGRMEXXUJAOBMBANSMKFILFKYMHIDSMKSNQOXHKTJTPVHKSUHBAXMT";

		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		Main.main(args);

		assertEquals(expectedMessage, outContent.toString());

	}


	/*
	 * This test fails as compared to the expected output based on
	 * https://www.101computing.net/enigma-machine-emulator/
	 * Suspecting a problem in the code of that emulator as the middle Rotor spins
	 * two consecutive times for the last 2 letters in input, which should *never* happen.
	 */
	@Test
	public void mainTestRandom() throws IOException {
		String[] args = new String[]{"./enigma", "encode", "AAABBBCCCDDDEEEFFFGGGHHHIIIJJJKKKLLLMMMNNNOOOPPPQ", "-r", "I:5-1 II:3-3 III:1-5", "-p", "AA", "-v"};
		Main.main(args);        

		String expectedMessage = "TMOEWFHOXZUFKCXJWXJNIUZWHTVIUOXAEQBZVBJAPYKJJRSHN";

		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		Main.main(args);

		assertEquals(expectedMessage, outContent.toString());

	}


	/*
	 * This test fails as compared to the expected output based on
	 * https://www.101computing.net/enigma-machine-emulator/
	 * See comment on mainTestRandom()
	 */

	@Test
	public void mainTestRandomLong() throws IOException {
		String[] args = new String[]{"./enigma", "encode", "AAABBBCCCDDDEEEFFFGGGHHHIIIJJJKKKLLLMMMNNNOOOPPPQQQRRRSSSTTTUUUVVVWWWXXXYYYZZZ", "-r", "I:5-1 II:3-3 III:1-5", "-p", "AA", "-v"};
		Main.main(args);        

		String expectedMessage = "TMOEWFHOXZUFKCXJWXJNIUZWHTVIUOXAEQBZVBJAPYKJJRSHNJUSDYFRPKZAXFGZOPKHRZGDFHVUJB";

		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		Main.main(args);

		assertEquals(expectedMessage, outContent.toString());

	}


}
