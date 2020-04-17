package ie.gmit.sw;

import java.io.*;
import java.util.*;

public class Parser {

	private Collection<String> googleWord = new TreeSet<>(); //Using a collection for google since all values are unique
	private Map<String, String> thesaurusWord = new HashMap<>(); //Key and Value. Word and Line
	private final String GOOGLE_FILE_PATH = "./google-1000.txt", THESAURUS_FILE_PATH = "./MobyThesaurus2.txt"; //Final since paths will always be the same 

	public Parser() {
		parseGoogle(GOOGLE_FILE_PATH);
		parseThesaurus(THESAURUS_FILE_PATH);
	}

	private void parseGoogle(String file) { // adding google 1000 words to map and collection
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String word; // reading each google word

			while ((word = br.readLine()) != null) { // Keep going until we reach the end of the file
				googleWord.add(word); //Time complexity O(1)
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("File could not be found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error while reading file");
			e.printStackTrace();
		}
	}

	
	private void parseThesaurus(String file) { // file = MobyThesaurus
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line; // reading line of text

			while ((line = br.readLine()) != null) {
				String[] word = line.split(",");
				thesaurusWord.put(word[0], line); //time complexity O(1)
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("File could not be found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error while reading file");
			e.printStackTrace();
		}

	}
	
	public boolean googleContains(String words) {
		return googleWord.contains(words); //time complexity O(log n)
	}
	
	public boolean thesaurusContainsKey(String words) {
		return thesaurusWord.containsKey(words); //time complexity O(1)
	}
	
	public String thesaurusGet(String words) {
		return thesaurusWord.get(words.toString()); //time complexity O(1)
	}

}
