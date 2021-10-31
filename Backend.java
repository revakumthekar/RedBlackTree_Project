// --== CS400 File Header Information ==--
// Name: Karsey Renfert
// Email: krenfert@wisc.edu
// Team: BG red
// Role: Back end
// TA: Bri Cochran
// Lecturer: Florian
// Notes to Grader: n/a

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;

public class Backend {
	RedBlackTree<WordInterface> rbt;

	public Backend(Reader fileReader) throws FileNotFoundException, IOException, DataFormatException {
		// Read all data in
		DictionaryReader dr = new DictionaryReader();
		List<WordInterface> words = dr.readDictFile(fileReader);
		// Initialize the primary data type
		rbt = new RedBlackTree<WordInterface>();

		for (WordInterface word : words) {
			try {
				// Attempt to insert the new word
				rbt.insert(word);
			} catch(NullPointerException npe) {
				// This should not happen, but we must be safe
				System.out.println("Invalid word: data cannot be null!");
				npe.printStackTrace();
				System.exit(1);
			} catch(IllegalArgumentException iae) {
				// This should not happen, but we must be safe
				System.out.println("Invalid word: duplicates are not allowed!");
				iae.printStackTrace();
				System.exit(1);
			}
		}

	}

	public boolean addWord(String word){
		if (word == null || word.length() < 1) {
			// This should prevent NPE
			return false;
		}
		Word w = new Word(word.split("\\|"));
		
		try {
			rbt.insert(w);
		} catch(Exception e) {
			// Catch the error and report failure up the chain of command
			return false;
		}
		return true;
	}

	public String findWord(String word) {
		// Recursion!
		return findWordHelper(word, rbt.root);
	}

	public String findWordHelper(String target, RedBlackTree.Node<WordInterface> current) {
		if (current.data.getWord().equals(target)) {
			// Check the current node and return if valid (base case)
			return current.data.getWord();
		}
		
		// Else, check the children using standard traversal techniques (recursive case)
		if (current.data.getWord().compareTo(target) > 0 && current.leftChild != null) {
			return findWordHelper(target, current.leftChild);
		} else if (current.rightChild != null) {
			return findWordHelper(target, current.rightChild);
		}
		
		// We've reached a leaf node and haven't found any word that matches.  Doh!
		throw new NoSuchElementException();
	}

	public int getNumWords() {
		return rbt.size();
	}

	public String findDefByWord(String word) {
		// Recursion!
		return findDefByWordHelper(word, rbt.root);
	}

	public String findDefByWordHelper(String target, RedBlackTree.Node<WordInterface> current) {
		if (current.data.getWord().equals(target)) {
			// Check the current node and return if valid (base case)
			return current.data.getMeaning();
		}
		
		// Else, check the children using standard traversal techniques (recursive case)
		if (current.data.getWord().compareTo(target) > 0 && current.leftChild != null) {
			return findDefByWordHelper(target, current.leftChild);
		} else if (current.rightChild != null) {
			return findDefByWordHelper(target, current.rightChild);
		}
		
		// So we've reached a leaf node and haven't found the word the user wants.
		throw new NoSuchElementException();
	}

	public String[] listOfWordsStartingWithLetter(String letter) {
		// Recursion!
		String list = findManyWordsHelper(letter, rbt.root, "");
		
		// Prevent mistakes, handle empty result sets here:
		if(list.length() == 0)
			return null;
		
		// Prepare the data for delivery:
		String[] result = list.split(",");
		return result;
	}

	public String findManyWordsHelper(String target, RedBlackTree.Node<WordInterface> current, String words) {
		if (current.data.getWord().charAt(0) == target.charAt(0)) {
			// If the current node is valid, add it to the list
			words = words + current.data.getWord() + ",";
		}

		// ALSO check the children (recursive case)
		if (current.leftChild != null) {
			words = findManyWordsHelper(target, current.leftChild, words);
		}
		if (current.rightChild != null) {
			words = findManyWordsHelper(target, current.rightChild, words);
		}
		
		// Return all words we've found
		return words;
	}

}
