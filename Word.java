// --== CS400 File Header Information ==--
// Name: Reva Kumthekar
// Email: rkumthekar@wisc.edu
// Team: BG
// Role: Data Wrangler
// TA: Brianna Cochran
// Lecturer: Gary Dahl
// Notes to Grader: NONE

/**
 * This class implements WordInterface and instantiates its methods that deal with each word and
 * definition pair with methods that get each component and interact with them.
 * @author revakumthekar
 *
 */
public class Word implements WordInterface
{
  //instance variables
  String word; 
  String def;
  
  /**
   * Constructor that initializes the word and definition
   * @param wordData the array that is passed into by the DictionaryReader class for the word and definition pair
   */
  public Word(String[] wordData)
  {
    this.word = wordData[0];
    this.def = wordData[1];
  }
  
  /**
   * This method gets the word from the pair and returns it
   * @return the word from the word and definition pair
   */
  @Override
  public String getWord() {
    
    return word;
  }

  /**
   * This method gets the definition from the pair and returns it
   * @return the definition from the word and definition pair
   */
  @Override
  public String getMeaning() {
    
    return def;
  }
  
  /**
   * This method compares a word to another alphabetically and returns a number based on where 
   * that first word stands
   * @return an integer based on where the word stands compared to another
   *    will return a negative number with an absolute value of how far away this.word is away from otherWord
   *         and if the word comes before otherWord alphabetically
   *    will return a positive number with an absolute value of how far away this.word is away from otherWord
   *         and if the word comes before otherWord alphabetically
   *    will return a 0 if the word is equal to the other word
   */ 
  @Override
  public int compareTo(WordInterface otherWord) 
  {
    
    return this.getWord().compareTo(otherWord.getWord());
    
  }
  
  /**
   * This method formats a string with the word and defintion and returns it
   * @return a formatted string with the word and definition separated properly
   */
  @Override
  public String toString()
  {
    String formattedString = "[Word: " + getWord() + ", Def: " + getMeaning() + "]";
    return formattedString;
        
  }

}
