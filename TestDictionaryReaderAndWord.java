// --== CS400 File Header Information ==--
// Name: Reva Kumthekar
// Email: rkumthekar@wisc.edu
// Team: BG
// Role: Data Wrangler
// TA: Brianna Cochran
// Lecturer: Gary Dahl
// Notes to Grader: NONE

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.fail;
import java.io.FileReader;
import java.io.StringReader;
import java.util.List;

/**
 * This test class creates objects off of both DictionaryReader and WordInterface to see if the
 * implementations of these classes work as they should. There are 5 sets of tests on hard-coded words and 
 * definitions and 4 sets of test on an actual .txt file
 * @author Reva Kumthekar
 *
 */
public class TestDictionaryReaderAndWord { 

   DictionaryReaderInterface reader = new DictionaryReader();

   
  /**
   * This test reads in a word from the dictionary and tests whether the list of words
   * returned is of size it is 1. It fails if the size is not 1 or if an
   * exception occurs while reading in the words. This method uses JUnit to see the results
   * of the test
   */
   @Test
   public void testReadDictFile()
   {

     List<WordInterface> words = null;
     try{
       words = reader.readDictFile(new StringReader("word|definition\n"
     +"Optimism|a tendency to look on the more favorable side of events or conditions and to expect the most favorable outcome.\n"));
                                          

                  } catch(Exception e)
                  {
                          e.printStackTrace();
                          fail("exception was thrown"); //test failed
                  }
                  int expectedSize = 1;
                  assertEquals(expectedSize, words.size());


   }
  /**
   * This test reads in a word from the dictionary and tests whether the getWord() method will return
   * the proper value. It fails if the word is not correct or if an
   * exception occurs while reading in the words. This method uses JUnit to see the results
   * of the test
   */
  @Test
  public void testGetWord()
  {
          List<WordInterface> words = null;
          try{
            words = reader.readDictFile(new StringReader("word|definition\n"
                +"Optimism|a tendency to look on the more favorable side of events or conditions and to expect the most favorable outcome.\n"));
                                  

          } catch(Exception e)
          {
                  e.printStackTrace();
                  fail("exception was thrown"); //test failed
          }
          String expectedWord = "Optimism";
          assertEquals(expectedWord, words.get(0).getWord());
  }

  /**
   * This test reads in a word from the dictionary and tests whether the getMeaning() method will return
   * the proper value. It fails if the definition is not correct or if an
   * exception occurs while reading in the words. This method uses JUnit to see the results
   * of the test
   */
  @Test
  public void testGetMeaning()
  {

          List<WordInterface> words = null;
          try{
                  words = reader.readDictFile(new StringReader("word|definition\n"
                          +"Optimism|a tendency to look on the more favorable side of events or conditions and to expect the most favorable outcome.\n"));
                                  
          } catch(Exception e)
          {
                  e.printStackTrace();
                  fail("exception was thrown"); //test failed
          }
          String expectedDef = "a tendency to look on the more favorable side of events or conditions and to expect the most favorable outcome.";
          assertEquals(expectedDef, words.get(0).getMeaning());

  }
     
  
  /**
   * This test reads words from the dictionary file and tests whether the compareTo() method will return
   * the proper value. It fails if the compareTo() method returns the wrong value or if an
   * exception occurs while reading in the words. This method uses JUnit to see the results
   * of the test
   */
  @Test
  public void testCompareTo()
  {

          List<WordInterface> words = null;
          try{
                  words = reader.readDictFile(new StringReader("word|definition\n"
                                  +"Optimism| a tendency to look on the more favorable side of events or conditions and to expect the most favorable outcome.\n"
                                  +"Pessimism| the belief that the evil and pain in the world are not compensated for by goodness and happiness.\n"));
                                 
          } catch(Exception e)
          {
                  e.printStackTrace();
                  fail("exception was thrown"); //test failed
          }
          int expectedNum = -1;
          assertEquals(expectedNum, words.get(0).compareTo(words.get(1)));

  }

  /**
   * This test reads in a word from the dictionary and tests whether the toString() method will return
   * the correctly formatted string. It fails if the string is not correct or if an
   * exception occurs while reading in the words. This method uses JUnit to see the results
   * of the test
   */
  @Test
  public void testToString()
  {

          List<WordInterface> words = null;
          try{
                  words = reader.readDictFile(new StringReader("word|definition\n"
                                  +"Optimism|a tendency to look on the more favorable side of events or conditions and to expect the most favorable outcome.\n"));
                                 
          } catch(Exception e)
          {
                  e.printStackTrace();
                  fail("exception was thrown"); //test failed
          }
          String expectedString = "[Word: Optimism, Def: a tendency to look on the more favorable side of events or conditions and to expect the most favorable outcome.]";
          assertEquals(expectedString, words.get(0).toString());

  }
  
  /**
   * This test reads in a word from the TestDictionary file and tests whether the list of words
   * returned is of size it is 5. It fails if the size is not 5 or if an
   * exception occurs while reading in the words. This method uses JUnit to see the results
   * of the test
   */
  @Test
  public void testReadDictFileOnFile()
  {

    List<WordInterface> words = null;
    try{
      words = reader.readDictFile(new FileReader("/Users/revakumthekar/Downloads/TestDictionary.txt"));
                                         

                 } catch(Exception e)
                 {
                         e.printStackTrace();
                         fail("exception was thrown"); //test failed
                 }
                 int expectedSize = 5;
                 assertEquals(expectedSize, words.size());


  }
  /**
   * This test reads in the TestDictionary file and tests whether the getWord() method will return
   * the proper value. It fails if the word is not correct or if an
   * exception occurs while reading in the words. This method uses JUnit to see the results
   * of the test
   */
  @Test
  public void testGetWordOnFile()
  {
          List<WordInterface> words = null;
          try{
            words = reader.readDictFile(new FileReader("/Users/revakumthekar/Downloads/TestDictionary.txt"));
                                  

          } catch(Exception e)
          {
                  e.printStackTrace();
                  fail("exception was thrown"); //test failed
          }
          String expectedWord = "consider";
          //consider will be in position 1 in the array since it is sorted alphabetically before being stored
          assertEquals(expectedWord, words.get(1).getWord());
  }
  /**
   * This test reads in a word from the TestDictionary file and tests whether the toString() method will return
   * the correctly formatted string. It fails if the string is not correct or if an
   * exception occurs while reading in the words. This method uses JUnit to see the results
   * of the test
   */
  @Test
  public void testToStringonFile()
  {

          List<WordInterface> words = null;
          try{
            words = reader.readDictFile(new FileReader("/Users/revakumthekar/Downloads/TestDictionary.txt"));
                                 
          } catch(Exception e)
          {
                  e.printStackTrace();
                  fail("exception was thrown"); //test failed
          }
          String expectedString = "[Word: minute, Def: infinitely or immeasurably small]";
          assertEquals(expectedString, words.get(3).toString());

  }
  /**
   * This test reads words from the TestDictionary file and tests whether the compareTo() method will return
   * the proper value. It fails if the compareTo() method returns the wrong value or if an
   * exception occurs while reading in the words. This method uses JUnit to see the results
   * of the test
   */
  @Test
  public void testCompareToOnFile()
  {

          List<WordInterface> words = null;
          try{
            
            words = reader.readDictFile(new FileReader("/Users/revakumthekar/Downloads/TestDictionary.txt"));
            
          } catch(Exception e)
          {
                  e.printStackTrace();
                  fail("exception was thrown"); //test failed
          }
          int expectedNum = -12;
          assertEquals(expectedNum, words.get(0).compareTo(words.get(3)));

  }

}
