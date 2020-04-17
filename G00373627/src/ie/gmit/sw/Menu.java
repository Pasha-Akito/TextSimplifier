package ie.gmit.sw;
import java.util.*;

public class Menu {

	private boolean running = true;
	private Scanner s = new Scanner(System.in);
	private String userInput;
	private Parser parse = new Parser();
	
	public Menu() {// constructor
		init();
	}

	private void init() { //when a new menu object is created run this method
		while (running) {//our condition for the programm running
			menuOptions(); //asks user to input text
			if (userInput.equalsIgnoreCase("Quit")) { //if Quit we change running to false
				System.out.println("Closing Program..."); //print statement
				running = false; //program is not running anymore
			} else {
				System.out.println(reduce(userInput)); //print out the string to the user from what we simplified in the reduce method
			}
		}
		System.exit(0); //closes the program
	}

	private String reduce(String text) { //we input text from user
		String[] words = text.split(" "); //break up the users text into an array
		StringBuilder sentence = new StringBuilder(); //using StringBuilder to concegate words together

		for (int i = 0; i < words.length; i++) { //first loop checking every word
		
			if (parse.googleContains(words[i])) { //if it is a google word, stringbuilder appends
				sentence.append(words[i] + " "); //append google word
				continue;
			} else if (parse.thesaurusContainsKey(words[i])) { //if the word is a thesaurus word, check if there is a google word
				String synonyms = parse.thesaurusGet(words[i]); //get the value and split all the words into an array
				String[] splitSynonyms = synonyms.split(","); //array of words

				for (int j = 0; j < splitSynonyms.length; j++) { //checking every word of map value for a google word
					if (parse.googleContains(splitSynonyms[j])) { //if it is a google word, change the word we will append to that google word
						words[i] = splitSynonyms[j];
						break;
					}
				}
				sentence.append(words[i] + " "); //adds word to sentence
			} else {
				sentence.append(words[i] + " "); //if not theasurus or google word add without changing
			}

		}
		return sentence.toString(); //return it as a string
	}

	private void menuOptions() {
		System.out.println(ConsoleColour.BLACK_BOLD);
		System.out.println("Type 'Quit' To Exit Program");
		System.out.println("Enter Text To Simplify>");
		userInput = s.nextLine(); //scanner reading the users input
	}

}
