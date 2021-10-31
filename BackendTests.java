// --== CS400 File Header Information ==--
// Name: Karsey Renfert
// Email: krenfert@wisc.edu
// Team: BG red
// Role: Back end
// TA: Bri Cochran
// Lecturer: Florian
// Notes to Grader: n/a

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileReader;

import org.junit.jupiter.api.Test;

/*
 * This class contains a set of tests for the backend of the Dictionary Project
 */
public class BackendTests {

	/**
	 * This test attempts to add a word to the dictionary, then it verifies a
	 * successful outcome using a variety of other methods. Then, it tries to add
	 * duplicate words which should result in an error. If an error is not thrown,
	 * the method fails Finally, the method tries to add a null value to the
	 * dictionary which must return false.
	 */
	@Test
	public void testAddWord() {
		Backend backend;
		// Case 1
		try {
			backend = new Backend(new FileReader("dictionary.txt"));
			assertEquals(backend.addWord("TestWord|Test definition"), true);
			assertNotEquals(backend.findWord("TestWord"), null);
			assertEquals(backend.findWord("TestWord"), "TestWord");
			assertNotEquals(backend.findDefByWord("TestWord"), null);
			assertEquals(backend.findDefByWord("TestWord"), "Test definition");
		} catch (Exception e) {
			fail("An unexpected error occurred.");
		}

		// Case 2
		try {
			backend = new Backend(new FileReader("dictionary.txt"));
			backend.addWord("TestWord|Test definition");
			backend.addWord("TestWord|Test definition1");
			fail("Attempt to insert a duplicate word was successful.");
		} catch (Exception e) {
			// If it ends up here, it didn't insert a duplicate value. GOOD!
		}

		// Case 3
		try {
			backend = new Backend(new FileReader("dictionary.txt"));
			assertEquals(backend.addWord(null), false);
		} catch (Exception e) {
			// If it ends up here, it didn't insert a null value. GOOD!
		}
	}

	/**
	 * This method tries to find words that exist and words that do not exist. If it
	 * fails to find real words, the test fails. If it doesn't throw an exception
	 * when searching for a fake word, the test fails.
	 */
	@Test
	public void testFindWord() {
		Backend backend;
		// Case 1
		try {
			backend = new Backend(new FileReader("dictionary.txt"));
			assertNotEquals(backend.findWord("conduct"), null);
			assertNotEquals(backend.findWord("latitude"), null);
			assertNotEquals(backend.findWord("consider"), null);
			assertNotEquals(backend.findWord("property"), null);
		} catch (Exception e) {
			fail("An unexpected error occurred.");
		}

		// Case 2
		try {
			backend = new Backend(new FileReader("dictionary.txt"));
			backend.findWord("conduc");
			fail("Improper handling of a failed word search.");
		} catch (Exception e) {
			// If it ends up here, it threw an error while searching for a fake word. GOOD!
		}
	}

	/**
	 * This method tries to find definitions of words that exist and definitions of
	 * words that do not exist. If it fails to find real words, the test fails. If
	 * it doesn't throw an exception when searching for a fake word, the test fails.
	 */
	@Test
	public void testFindDefinitionByWord() {
		Backend backend;
		// Case 1
		try {
			backend = new Backend(new FileReader("dictionary.txt"));
			assertEquals(backend.findDefByWord("conduct"), "direct the course of; manage or control");
			assertEquals(backend.findDefByWord("latitude"), "freedom from normal restraints in conduct");
			assertEquals(backend.findDefByWord("consider"), "deem to be");
			assertEquals(backend.findDefByWord("property"),
					"a basic or essential attribute shared by members of a class");
		} catch (Exception e) {
			fail("An unexpected error occurred.");
		}

		// Case 2
		try {
			backend = new Backend(new FileReader("dictionary.txt"));
			backend.findDefByWord("conduc");
			fail("Improper handling of a failed word search.");
		} catch (Exception e) {
			// If it ends up here, it threw an error while searching for a fake word. GOOD!
		}
	}

	/**
	 * This method tries to find words that start with a letter. The
	 * listOfWordsStartingWithLetter function must be resistant to queries with more
	 * than one letter. The function must also return null when no results are
	 * found, so the Frontend can report no matches to the user.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testListOfWordsStartingWithLetter() {
		Backend backend;
		// Case 1
		try {
			backend = new Backend(new FileReader("dictionary.txt"));
			assertEquals(backend.listOfWordsStartingWithLetter("a"),
					new String[] { "approach", "apparent", "accord", "appoint" });
			// Case 2
			assertEquals(backend.listOfWordsStartingWithLetter("aaaa"),
					new String[] { "approach", "apparent", "accord", "appoint" });
			assertEquals(backend.listOfWordsStartingWithLetter("vvvvvvvvvvvvvvvvvvvvvvvvvv"),
					new String[] { "vacate", "vain" });
		} catch (Exception e) {
			fail("An unexpected error occurred.");
		}

		// Case 3
		try {
			backend = new Backend(new FileReader("dictionary.txt"));
			assertEquals(backend.listOfWordsStartingWithLetter("x"), null);
		} catch (Exception e) {
			fail("An unexpected error occurred.");
		}
	}

	/**
	 * This method verifies that words are counted correctly by the
	 * backend/red-black tree. It also adds words to account for all possibilities.
	 */
	@Test
	public void testGetNumWords() {
		Backend backend;
		try {
			// Case 1
			backend = new Backend(new FileReader("dictionary.txt"));
			assertEquals(backend.getNumWords(), 50);

			// Case 2
			backend.addWord("TestWord|Test definition");
			backend.addWord("TestWor1d|Test definition");
			assertEquals(backend.getNumWords(), 52);
		} catch (Exception e) {
			fail("An unexpected error occurred.");
		}
	}
}
