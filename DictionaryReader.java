// --== CS400 File Header Information ==--
// Name: Reva Kumthekar
// Email: rkumthekar@wisc.edu
// Team: BG
// Role: Data Wrangler
// TA: Brianna Cochran
// Lecturer: Gary Dahl
// Notes to Grader: NONE

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.DataFormatException;

/**
 * This class implements the DictionaryReaderInterface to write the method that will read
 * in the file that is passed to it. 
 * 
 * @author Reva Kumthekar
 *
 */
public class DictionaryReader implements DictionaryReaderInterface
{

  /**
   * This method will parse through the file to separate each word and definition into an array and 
   * then add it to a list of words that will be returned at the end.
   * @return a list of words from the .txt file that is sorted alphabetically before returning
   */
  @Override
  public List<WordInterface> readDictFile(Reader fileReader)
      throws IOException, FileNotFoundException, DataFormatException 
  {
     BufferedReader reader = new BufferedReader(fileReader);
     List<WordInterface> listOfWords = new ArrayList<WordInterface>();
     String[] wordData;
     Word words;
     String data;
     int headerLength;
  
     try {
       data = reader.readLine();
       if(data == null)
       {
         return null; 
       }
         
      //splits the string we get with by the (|) symbol for the word and its definition
      wordData = data.split("\\|");
       
      //headerLength is what we will use to check for DataFormatExceptions
       headerLength = wordData.length;
       data = reader.readLine();
       while(data != null)
       {
         wordData = data.split("\\|");
         if(wordData.length != headerLength)
             throw new DataFormatException("Data is not formatted correctly.");
         words = new Word(wordData);
         
         //adds the word to the list
         listOfWords.add(words);
         
         //keeps going
         data = reader.readLine();
       }
       
     }catch (FileNotFoundException e) {
       e.getMessage();
       e.printStackTrace();
       System.out.println(e);
   } catch (IOException e2) {
       e2.getMessage();
       e2.printStackTrace();
       System.out.println(e2);
   } catch (DataFormatException e3) {
       e3.getMessage();
       e3.printStackTrace();
       System.out.println(e3);
   }
    reader.close();
    //sorts the words before returning
    Collections.sort(listOfWords);
    return listOfWords;
  }
  
}
