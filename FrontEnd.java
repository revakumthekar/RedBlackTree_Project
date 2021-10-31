// --== CS400 File Header Information ==--
// Name: Akshay Joshi
// Email: akjoshi2@wisc.edu
// Team: BG Red
// Role: Front End Developer
// TA: Brianna Cochran
// Lecturer: Florian Heimerl
// Notes to Grader: N/A

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.zip.DataFormatException;
/**
 * This class instantiates and runs the back end. Creates a user interface where the user can search for words and their definitions, add words,
 * and find words starting with a certain letter.
 * @author Akshay Joshi
 *
 */
public class FrontEnd 
{
  private Backend b;
  private String mode;
  private boolean running = false;
  private Scanner sc;
  /**
   * The main method, creates the backend with the provided file dictionary.txt
   * @param args
   */
  public static void main(String[] args)
  {
    Backend b;                      
    try {
      b = new Backend(new FileReader("Dictionary.txt"));
      FrontEnd fe = new FrontEnd();
      fe.run(b);                                    
    }
    catch(FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (DataFormatException e) {
      e.printStackTrace();
    }
  }
  /**
   * Runs the backend, initializes the scanner
   * @param Backend
   */
  public void run(Backend b)
  {
    this.b = b;
    mode = "base";      //the program initially begins in base mode
    running = true;
    sc = new Scanner(System.in);
    System.out.println("Welcome to the Red Black Dictionary!");
    System.out.println("");
    displayGreeting(mode);              
    begin();
  }
  /**
   * Prompts for user input and sets the mode to the current mode.
   */
  public void begin()
  {
    boolean heard = false;
    String command = "";
    while (running)
    {
      heard = false;
      while (!heard)
      {
        System.out.print("> ");     //This makes the program print an arrow, letting the user know it is their turn to input
        while (!sc.hasNext())       //idling
        {
          
        }
        command = sc.nextLine().trim().toLowerCase();
        heard = true;
      }
      if (mode.equals("search"))
      {
        mode = searchMode(command);             //based on the mode, tells what handling method should be used
      }
      else if (mode.equals("addition"))
      {
        mode = additionMode(command);
      }
      else if (mode.equals("letter"))
      {
        mode = letterMode(command);
      }
      else
      {
        running = baseMode(command);
      }  
    }
  }
  /**
   * Displays greeting that reveals user command options specific to each mode
   * @param mode The mode that the user is currently in
   */
  public void displayGreeting(String mode)
  {
    if (mode.equals("base"))
    {
      System.out.println("Welcome to BASE mode");
      System.out.println("Press 'a' to enter addition mode and add words to the dictionary");
      System.out.println("Press 's' to enter search mode, where you can search for a word's definition");
      System.out.println("Press 'l' to enter letter mode, where you can search for list of words beginning with a certain letter");
      System.out.println("Press 'x' to exit the program");
      System.out.println("Type \"size\" to get the size of the dictionary");
    }
    else if (mode.equals("addition"))
    {
      System.out.println("Welcome to ADDITION mode");
      System.out.println("Press 'x' to return to base mode");
      System.out.println("Write the new word to be added");
    }
    else if (mode.equals("letter"))
    {
      System.out.println("Welcome to LETTER mode");
      System.out.println("Type a letter twice ('aa', 'bb', 'cc', ..., 'zz') to find a list of words beginning with that letter");
      //The reason we are typing the letter twice is so that it is clear that 'x' exits the program and 'xx' lists all words beginning with the letter x
      System.out.println("Press 'x' to return to base mode");
    }
    else if (mode.equals("search"))
    {
      System.out.println("Welcome to SEARCH mode");
      System.out.println("Type a word to search for its definition");
      System.out.println("Press 'x' to return to base mode");
    }
  }
  /**
   * Handles commands in base mode to exit program and change modes 
   * @param command The command to be handled
   * @return True if program is still active (if the user did not press x), False otherwise
   */
  public boolean baseMode(String command)
  {
    if (!command.matches("^[a-z]*$"))
    {
      System.out.println("Alphabetical input only please");        
      //this line present in all input handling methods
      //for all input, there should be no non-alphabetical characters
      displayGreeting(mode);
      return true;
    }
    if (command.equals("x"))
    {
      return false;
    }
    else if (command.equals("s"))
    {
      mode = "search";
      displayGreeting(mode);
    }
    else if (command.equals("a"))
    {
      mode = "addition";
      displayGreeting(mode);
    }
    else if (command.equals("l"))
    {
     mode = "letter";
     displayGreeting(mode);
    }
    else if (command.equals("size"))
    {
      System.out.println(b.getNumWords() + " Word-Definition pairs");
    }
    else if (!command.equals(""))
    {
      System.out.println("Invalid input, try again.");
      displayGreeting(mode);
    }
    return true;
  }
  /**
   * Handles commands in letter mode, where you can search for letters 
   * @param command The command to be handled
   * @return The new mode or letter (if the mode was not changed)
   */
  public String letterMode(String command)
  {
    if (!command.matches("^[a-z]*$"))
    {
      System.out.println("Alphabetical input only please");
      displayGreeting(mode);
      return "letter";
    }
    if (command.equals("x"))
    {
      displayGreeting("base");
      return "base";
    }
    else
    {
      if (command.length() == 2)
      {
        command = command.substring(0,1);
        if (command.compareTo("a") >= 0 && command.compareTo("z") <= 0)
        {
          String[] s = b.listOfWordsStartingWithLetter(command);
          if (s == null)
	  {
            System.out.println("No words in dictionary starting with " + command);
	    //In our example file this occurs with the letter X. It returns here so that it doesn't print the separator lines.
	    displayGreeting(mode);
	    return "letter";
	  }
	  for (String str: s)
          {
            System.out.println("---------------");         
            //This makes the entries easier to read, since sometimes there are a lot of words starting with a certain letter
            System.out.println(str);
          }
        }
        else
        {
          System.out.println("Invalid input");
          //Has to be "aa" or something that is two characters.
        }
      }
      else if (!command.equals(""))
      {
        System.out.println("Invalid input");

      }
    }
    System.out.println("---------------");
    displayGreeting("letter");
    return "letter";
  }
  /**
   * Handles commands in search mode, where you can search for the definition of a word
   * @param command The command to be handled
   * @return The new mode or search (if the mode was not changed)
   */
  public String searchMode(String command)
  {
    if (!command.matches("^[a-z]*$"))
    {
      System.out.println("Alphabetical input only please");
      displayGreeting(mode);
      return "search";
    }
    if (command.equals("x"))
    {
      displayGreeting("base");
      return "base";    
    }
    else
    {
      try {
        String s = b.findDefByWord(command);
        System.out.println("Word: " + command + ", Definition: " + s);
	//In this scenario, the command is the word, and the findDef method returns the def
	//We cconstruct a string to allow the user to see both the word and its definition
      }
      catch (NoSuchElementException e)
      {
        System.out.println("Invalid input. Search can only find words already in the dictionary.");
      }
    }
    displayGreeting("search");
    return "search";
  }
  /**
   * Handles commands in addition mode, where you can add words to the dictionary
   * @param command The command to be handled
   * @return The new mode or addition (if the mode was unchanged)
   */
  public String additionMode(String command)
  {
    if (!command.matches("^[a-z]*$"))
    {
      System.out.println("Alphabetical input only please");
      displayGreeting(mode);
      return "addition";
    }
    if (command.equals("x"))
    {
      displayGreeting("base");
      return "base";
    }
    else 
    {
      System.out.println("What is the definition for this word?");
      System.out.println("You can also type 'x' if you want to cancel this addition");
      System.out.print("> "); //Second prompt for definition, first one was for word
      String def = sc.nextLine();
      String newword = command + "|" + def;             
      //The frontend takes both the word and definition as separate prompts, buts puts them together with a pipe to be in the correct format for the backend method
      try
      {
        if (def.equals("x"))
        {
          displayGreeting("addition");
          return "addition";
        }
        if (b.addWord(newword))
        {
          System.out.println("Word and definition successfully added!");
        }
        else
        {
          System.out.println("Invalid input, word not added to dictionary");
        }
      }
      catch (IllegalArgumentException e)
      {
        System.out.println("Invalid input, word already is in this dictionary");
	//Thrown if you try to add a word that already exists in the dictionary to the dictionary
      }
    }
    displayGreeting("addition");
    return "addition";
  }
}
