// --== CS400 File Header Information ==--
// Name: Reva Kumthekar
// Email: rkumthekar@wisc.edu
// Team: BG
// Role: Data Wrangler
// TA: Brianna Cochran
// Lecturer: Gary Dahl
// Notes to Grader: NONE

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.zip.DataFormatException;

/**
 * Interface for the reader object that will read in the .txt file
 * 
 * @author Reva Kumthekar and Michael Lin (Blue team Data Wrangler)
 */
public interface DictionaryReaderInterface 
{
  public List<WordInterface> readDictFile(Reader fileReader) 
      throws IOException, FileNotFoundException, DataFormatException;
}
