// --== CS400 File Header Information ==--
// Name: Reva Kumthekar
// Email: rkumthekar@wisc.edu
// Team: BG
// Role: Data Wrangler
// TA: Brianna Cochran
// Lecturer: Gary Dahl
// Notes to Grader: NONE

/**
 * Interface for word objects that will come from the .txt file
 * 
 * @author Reva Kumthekar and Michael Lin (Blue team Data Wrangler)
 */
public interface WordInterface extends Comparable<WordInterface>
{
  public String getWord();
  public String getMeaning();
  public String toString();
  public int compareTo(WordInterface otherWord);
}
