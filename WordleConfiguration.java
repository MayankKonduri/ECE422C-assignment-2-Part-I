package assignment2;

public class WordleConfiguration {
    // STATIC as these are the default that all the objects get and can default to
    private static final int DEFAULT_WORD_LENGTH = 5;
    private static final int DEFAULT_MAX_GUESSES = 6;
    private static final String DEFAULT_DICTIONARY_FILE_NAME = "assignment2/Dictionary.txt"; 

    // NON-STATIC because they belong to a specific object, and can be configured per WordleConfiguration object (if user is picky)

    private final int WORD_LENGTH;
    private final int MAX_GUESSES;
    private final String DICTIONARY_FILE_NAME;

    // DEFAULT CONSTRUCTOR which creates a default Wordle game as per instruction sheet specs.
    public WordleConfiguration(){
        this.WORD_LENGTH = DEFAULT_WORD_LENGTH;
        this.MAX_GUESSES = DEFAULT_MAX_GUESSES;
        this.DICTIONARY_FILE_NAME = DEFAULT_DICTIONARY_FILE_NAME;
    }

    // Allows a game to use custom settings (if the user wants to change)
    public WordleConfiguration(int WORD_LENGTH, int MAX_GUESSES, String DICTIONARY_FILE_NAME){
        // now need a lot of checks since we are letting users choose

        if(WORD_LENGTH < 1){
            throw new IllegalArgumentException("Need at least 1 letter to play Wordle");
        } else{
            this.WORD_LENGTH = WORD_LENGTH;
        }

        if(MAX_GUESSES < 1){
            throw new IllegalArgumentException("Need at least 1 guess to play Wordle");
        } else{
            this.MAX_GUESSES = MAX_GUESSES;
        }

        if(DICTIONARY_FILE_NAME == null || DICTIONARY_FILE_NAME.length() == 0){
           throw new IllegalArgumentException("Please provide a vailid dictionary file in order to play Wordle");
        } else{
            this.DICTIONARY_FILE_NAME = DICTIONARY_FILE_NAME;
        }
    }

    // Getters for Private Variables

    // Returns the number of letters in a word for this configuration
    public int getWordLength() {
        return WORD_LENGTH;
    }

    // Returns the maximum number of guesses for this configuration.
    public int getMaxGuesses() {
        return MAX_GUESSES;
    }

    // Returns the name of the dictionary file to pass to DictionaryHelper when creating the object
    public String getDictionaryFile() {
        return DICTIONARY_FILE_NAME;
    }
}
