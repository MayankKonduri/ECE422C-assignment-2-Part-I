package assignment2;

public class WordleGame extends GameTemplate{
    
    // Stores the configuration for this specific game (the user defines what type of game he wants to play in the main file)
    private final WordleConfiguration config;

    // value from 'WordleConfiguration' to set locally for easy retrieval
    private final int WORD_LENGTH;

    // This dictionary object is necessary to generate a random name to guess
    private final DictionaryHelper dictionary;

    // this is like starting 'play game,' as you have defined settings and started to actually play, and defined the game object, and the variables state
    public WordleGame(WordleConfiguration config){
        
        super(config.getMaxGuesses());

        // for easy access, attain values upon instantiation
        this.config = config; // the user tunes the configuration for this Wordle game

        WORD_LENGTH = config.getWordLength();

        this.dictionary = new DictionaryHelper(config.getDictionaryFile(), WORD_LENGTH); // passes the file name and the word length set for this game mode
        this.secret_word = randomCode(); // // upon instantation of game object, a secret code is created randomly
    }

    // ---------- OVERRIDING METHODS ----------

    // This method 'accessess' the secret word for this Wordle game out of the dictionary file, as the 'dictionary' object should already have a method to do so.
    @Override
    public char[] randomCode(){
        return dictionary.generateRandomWord().toCharArray(); // not necessary, but need to comply with the signature set by parent
    }


    // Returns the instructions for the current Worlde game object (the main can print this to the users so they know what configs they are playing under)
    @Override 
    public String getInstructions(){
        return "Guess the " + WORD_LENGTH + "-letter secret word. Your guess must also be a real word from the dictionary.\n" + "Feedback will be one symbol per letter: C = correct letter in the correct spot, P = letter is in the secret word but is in the wrong spot, A = letter is not in the secret word.";
    }

    // before we can validate, we can quickly check whether the guess was valid
    @Override 
    public String validateGuess(String user_input){
        // Remember that we mentioned that the dictionary is normalized to all UPPERCASE, so we have to first standardize the user's input
        String guess = user_input.toUpperCase();

        // Empty input is not a valid Wordle guess, but this is not in parent class as empty input might be 'skip' in another game
        if(guess.isEmpty()){
            return "Please Try Again. Reason: Your guess cannot be empty.";
        }

        // first check is making sure that the user's input is the same length as the secret word (if not, automatically false)
        if(guess.length() != WORD_LENGTH){
            return "Please Try Again. Reason: Guess must contain exactly " + WORD_LENGTH + " letters.";
        }

        // user's guess must ONLY contain alphabets
        for(int i = 0; i < guess.length(); i++){
            if(!(Character.isLetter(guess.charAt(i)))){
                // this is one cool thing I learned from the 9/24 lecture, that wrapper classes have static methods that can just take letters and then verify their 'variable type'
                return "Please Try Again. Reason: {" + guess.charAt(i) + "} is not a letter.";
            }
        }

        // last check is that the guess must be part of the dictionary of words
        if(!dictionary.contains(guess)){
            return "Please Try Again. Reason: {" + guess + "} is not a word in the dictionary that was provided.";
        }

        return null; // if all good, return null, and then the code that called this method can check if the return value is null, and if so, can conclude that the guess is valid
    }

    // I was debating to let validateGuess call this, but that would interfere with the fact that "invalid guesses do not consume an attempt"
    @Override
    public String scoreGuess(String user_input){

        // Remember that we mentioned that the dictionary is normalized to all UPPERCASE, so we have to first standardize the user's input
        String guess = user_input.toUpperCase();

        WordleFeedback w_f = new WordleFeedback(secret_word, guess); // char[] and String as inputs
    
        if(w_f.getCorrectLetters() == WORD_LENGTH){
            setWon(true); // set the parent class's game_won status to true, as the user has guessed the entire word correctly
        }

        return w_f.toString(); // overriding the toString() method inherited from Object Class to return a custom String
    }
}
