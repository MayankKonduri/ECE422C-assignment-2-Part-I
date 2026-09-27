package assignment2;

import java.io.*; // provides classes for Input / Output (helps load the Dictionary file)
import java.util.*; // gives classes like ArrayList, and helpful for Datastructures, and Dictionary.txt is one large text file (also has scanner to facilitate user input)

public class DictionaryHelper{
    private ArrayList<String> words_in_dictionary; // to store all the words from the .txt file, so we can check if a guess is valid using a .contains()
    // quick note... only load the words of the same size and the configuration, as the spec mentions that all guesses have to be of the same size of secret word
    
    private Random r; // this is used so that we can choose a random word from the pool.

    public DictionaryHelper(String fileName, int wordlength){ // gives the helper class the name of the input file, and what the config has set up for the setting of the words
        words_in_dictionary = new ArrayList<>();
        r = new Random();

        // I learned that we have to use try-catch when using files, because the program might come across an error when trying to open a file, and the try block of the code is to try opening the file, aand the catch is all the potential exceptions that should be handled

        try{
            Scanner sc = new Scanner(new File(fileName)); // loading the file, and we can use sc to read the next word from the file.

            while(sc.hasNextLine()){ // as long as the file has a next line with content
                
                // we need to standardize everything to uppercase for easier logic...
                String word_in_dictionary = sc.nextLine().toUpperCase(); // gets the next word in the dictionary, and converts to Uppercase to standardize

                if(!(word_in_dictionary == null || word_in_dictionary.length() == 0)){ // avoid spaces or null words
                    if(word_in_dictionary.length() == wordlength && word_in_dictionary.matches("[A-Z]+")){ // checks whether the word in the dictionary is of the correct size as game setting, and also if it only contain alphabets, and no punctuation like apostrophe(s)
                        // we only add to the valid pool of words if match the game setting...
                        words_in_dictionary.add(word_in_dictionary);
                    }
                }
            }

            sc.close(); // suggestion from AI

        } catch (FileNotFoundException e){
            // Scanner sc = new Scanner(new File(fileName)); THIS IS THE LINE WE HAVE A FALLBACK FOR
            // we simply let the user know we could not open the file (possibly because of a path error)
            throw new IllegalArgumentException("Dictionary file was not able to be successfully loaded");
        }

        // now that we have loaded all the words in the dictionary, and we need an extra layer to load the dictionary, and THEN let the user know what is the max size they can choose, we have to just shoot an error if they chose a size too large
        if(words_in_dictionary.size() == 0){
            throw new IllegalArgumentException("There are no words of size {" + wordlength + "} in the dictionary file provided");
        }
    }

    public boolean contains(String word){
        return words_in_dictionary.contains(word.toUpperCase()); // this is assuming there is a preliminary check in the game logic that size matches, and only alphabets are in the word. we just have to let them know if this word is in the dictionary file
    }

    // Selecting a random word to become the secret word for the game.
    public String generateRandomWord(){
        int index_of_word = r.nextInt(words_in_dictionary.size()); // the random index should be withing the indices of the vaild words arraylist
        return words_in_dictionary.get(index_of_word);
    }
}