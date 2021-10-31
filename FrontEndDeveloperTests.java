// --== CS400 File Header Information ==--
// Name: Akshay Joshi
// Email: akjoshi2@wisc.edu
// Team: BG
// Color: Red
// Role: Front End Developer
// TA: Brianna Cochran
// Lecturer: Florian Heimerl
// Notes to Grader: N/A
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.InputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.NoSuchElementException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
/*
 * This class contains a set of tests for the Front End of the Dictionary Project
 */
public class FrontEndDeveloperTests {

  /**
   * This test runs the front end and redirects its output to a string, passes in 'x' as a command,
   *  when the front end exists, the test succeeds. The test will not terminate if 'x' does not 
   *  exist, the test explicitly fails if front end not instantiated or exception occurs
   */
  @Test
  public void testXtoExit()
  {
    PrintStream standardOut = System.out;
    InputStream standardIn = System.in;
    try {
      //set the input stream to our input (x to test if the program exists)
      String input = "x";
      InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
      System.setIn(inputStreamSimulator);
      ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
      //sets the output to streamcaptor
      System.setOut(new PrintStream(outputStreamCaptor));
      Object frontend = new FrontEnd();
      ((FrontEnd) frontend).run(new Backend(new StringReader("TestDictionary.txt")));
      System.setOut(standardOut);
      System.setIn(standardIn);
      assertNotEquals(frontend, null);
    } catch (Exception e)
    {
      System.setOut(standardOut);
      System.setIn(standardIn);
      e.printStackTrace();
      fail();
    }
  }
  /**
   * This tests runs the front end and redirects its output to a string. The test passes if and only
   * if the correct error message is displayed as output from the program.
   */
  @Test
  public void testInvalidSearchException()
  {
    PrintStream standardOut = System.out;
    InputStream standardIn = System.in;
    try
    {
      //set the input stream to our input (x to test if the program exists)
      String input = "s" + System.lineSeparator() + "Banana" + System.lineSeparator() + "x" + System.lineSeparator() + "x";
      InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
      System.setIn(inputStreamSimulator);
      ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
      //sets the output to the stream captor to read output of front end
      System.setOut(new PrintStream(outputStreamCaptor));
      Object frontend = new FrontEnd();
      ((FrontEnd) frontend).run(new Backend(new FileReader("TestDictionary.txt")));
      System.setOut(standardOut);
      System.setIn(standardIn);
      String appOutput = outputStreamCaptor.toString();
      assertNotEquals(frontend, null);
      //The word banana does not exist in the TestDictionary.txt file, thus this will cause the program to output "Invalid Input"
      if (!appOutput.contains("Invalid input"))
      {
        fail();
      }
    }
    catch (Exception e)
    {
      // makes sure stdin and stdout are set correctly after we get non expected exception in test
      System.setOut(standardOut);
      System.setIn(standardIn);
      fail();
    }
  }
  /**
   * This test runs the front end and redirects its output to a string. It passes in s as a command
   *  to enter search mode, then searches for a word found in the example dictionary. If the front
   *  end exists and the output of the app contains the word and definition, the test passes
   */
  @Test
  public void testFrontEndSearch()
  {
    PrintStream standardOut = System.out;
    InputStream standardIn = System.in;
    try
    {
      //set the input stream to our input (x to test if the program exists)
      String input = "s" + System.lineSeparator() + "minute" + System.lineSeparator() + "x" + System.lineSeparator() + "x";
      InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
      System.setIn(inputStreamSimulator);
      ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
      //sets the output to the stream captor to read output of front end
      System.setOut(new PrintStream(outputStreamCaptor));
      Object frontend = new FrontEnd();
      ((FrontEnd) frontend).run(new Backend(new FileReader("TestDictionary.txt")));
      System.setOut(standardOut);
      System.setIn(standardIn);
      String appOutput = outputStreamCaptor.toString();
      assertNotEquals(frontend, null);
      //The word minute is present in the TestDictionary.txt file"
      if (!appOutput.contains("minute"))
      {
        System.out.println(appOutput);
        fail();
      }
      //This is the provided definition for minute in the TEstDictionary.txt file
      if (!appOutput.contains("infinitely or immeasurably small"))
      {
        fail();
      }
    }
    catch (Exception e)
    {
      // makes sure stdin and stdout are set correctly after exception thrown
      System.setOut(standardOut);
      System.setIn(standardIn);
      e.printStackTrace();
      fail();
    }
  }
  /**
   * This test runs the front end and redirects its output to a String, passes in a as a command
   * to enter addition mode, and adds a word to the dictionary. If the front end exists and the
   * output displays the message of successful addition, the test passes.
   */
  @Test
  public void testFrontEndAddition()
  {
    PrintStream standardOut = System.out;
    InputStream standardIn = System.in;
    try
    {
      //set the input stream to our input 
      String input = "a" + System.lineSeparator() + "Banana" + System.lineSeparator() + "yellow curved fruit" + System.lineSeparator() + "x" + System.lineSeparator() + "x";
      InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
      System.setIn(inputStreamSimulator);
      ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
      //sets the output to the stream captor to read output of front end
      System.setOut(new PrintStream(outputStreamCaptor));
      Object frontend = new FrontEnd();
      ((FrontEnd) frontend).run(new Backend(new FileReader("TestDictionary.txt")));
      System.setOut(standardOut);
      System.setIn(standardIn);
      String appOutput = outputStreamCaptor.toString();
      assertNotEquals(frontend, null);
      if (!appOutput.contains("Word and definition successfully added!"))
      {
        fail();
      }
    }
    catch (Exception e)
    {
      // makes sure stdin and stdout are set correctly after exception thrown
      System.setOut(standardOut);
      System.setIn(standardIn);
      fail();
    }
  }
  /**
   * This test tests the search for letters functionality of the program. Passes if method functionality is verified, fails otherwise. 
   * Runs the front end, redirects its output to a string and pases in a command to enter letter search mode and search for words beginning
   * with the letter 'a'. A test dictionary file is used, if output matches expected, the program passes the test
   */
  @Test
  public void testSearchForLetters()
  {
    PrintStream standardOut = System.out;
    InputStream standardIn = System.in;
    try
    {
      //set the input stream to our input 
      String input = "l" +  System.lineSeparator() + "aa" + System.lineSeparator() + "x" + System.lineSeparator() + "x";
      InputStream inputStreamSimulator = new ByteArrayInputStream(input.getBytes());
      System.setIn(inputStreamSimulator);
      ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
      //sets the output to the stream captor to read output of front end
      System.setOut(new PrintStream(outputStreamCaptor));
      Object frontend = new FrontEnd();
      ((FrontEnd) frontend).run(new Backend(new FileReader("TestDictionary.txt")));
      System.setOut(standardOut);
      System.setIn(standardIn);
      String appOutput = outputStreamCaptor.toString();
      assertNotEquals(frontend, null);
      System.out.println(appOutput);
      //Accord is a word beginning with a in TestDictionary.txt
      if (!appOutput.contains("accord"))
      {
        fail();
      }
    }
    catch (Exception e)
    {
      // makes sure stdin and stdout are set correctly after we get non expected exception in test
      e.printStackTrace();
      System.setOut(standardOut);
      System.setIn(standardIn);
      fail();
    }
  }
}

